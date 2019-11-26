package com.loncom.ismac.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.loncom.ismac.bean.DataPack;
import com.loncom.ismac.bean.DevheadBean;
import com.loncom.ismac.bean.Objc;
import com.loncom.ismac.bean.xml.BaseItemDevXml;
import com.loncom.ismac.bean.xml.ClassroomXml;
import com.loncom.ismac.bean.xml.Command;
import com.loncom.ismac.bean.xml.DevheadXml;
import com.loncom.ismac.bean.xml.DevvouXml;
import com.loncom.ismac.bean.xml.GroupXml;
import com.loncom.ismac.bean.xml.RootDevXml;
import com.loncom.ismac.bean.xml.RootXml;
import com.loncom.ismac.bean.xml.XmlEdiParser;
import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.jdbc.PropertyReader;
import com.loncom.ismac.logs.Logs;
import com.loncom.ismac.lservice.bean.Service;
import com.loncom.ismac.scanning.ClasspathPackageScanner;
import com.loncom.ismac.scanning.PackageClass;
import com.loncom.ismac.scanning.PackageClassTable;
import com.loncom.ismac.scanning.PackageScanner;
import com.loncom.ismac.service.IBaseService;
import com.loncom.ismac.service.impl.BaseServiceImpl;
import com.loncom.ismac.soket.service.impl.BaseSocketClient;
import com.loncom.ismac.soket.service.impl.LoncomipDataSocketClient;
import com.loncom.ismac.soket.service.impl.LoncomipDataSocketClientV1;
import com.loncom.ismac.soket.service.impl.LoncomipDataSocketClientV2;
import com.loncom.ismac.task.TaskQuartz;
import com.loncom.ismac.thread.AutoProcesserData;
import com.loncom.ismac.user.bean.Dictionaries;
import com.loncom.ismac.user.bean.UserBean;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.CMD;
import com.loncom.ismac.util.FileUtil;
import com.loncom.ismac.util.JaxbUtil;
import com.loncom.ismac.util.UtilTime;
import com.loncom.ismac.util.UtilTimeThread;
import com.loncom.ismac.util.UtilTool;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })

public class AppContext {
	private static Log logger = LogFactory.getLog(AppContext.class);

	static IBaseService baseservice = new BaseServiceImpl();

	private static Map<String, Object> SID = new HashMap<String, Object>();
	public static Properties prop = PropertyReader.getProperties("config.properties");
	public static Properties proptbale = PropertyReader.getProperties("tablename.properties");

	// 消息队列
	public static LinkedBlockingQueue<Object> dataQueueVouData = new LinkedBlockingQueue<Object>();
	// 历史消息队列
	public static LinkedBlockingQueue<DataPack> hisQueueVouData = new LinkedBlockingQueue<DataPack>();
	// 第一级 楼栋 第二级楼层 第三级办公室和教室区分
	public static Map<String, Map<String, Map<String, List<Objc>>>> group = new HashMap<String, Map<String, Map<String, List<Objc>>>>();
	/**
	 * 设备列表
	 */
	private static Map<String, DevheadBean> devhead = new HashMap<String, DevheadBean>();
	// 用户权限
	private static Map<String, UserBean> userMap = new HashMap<String, UserBean>();
	// 页面操作权限
	private static Map<String, String> menurole = new HashMap<String, String>();
	/*
	 * // 需要加载的菜单 private static List<String> lodsysmenu = new
	 * ArrayList<String>();
	 */
	// 数据服务连接对象集合
	private static List<Service> service = new ArrayList<Service>();
	// 数据服务对象缓存
	private static Map<String, Map<String, BaseSocketClient>> socketall = new HashMap<String, Map<String, BaseSocketClient>>();

	// 数据服务线程池
	private static ExecutorService ServicePool = Executors.newCachedThreadPool();
	public static String webroot = "";// web根目录
	// 接口业务类集合
	private static List<Object> packagelist = new ArrayList<Object>();
	// 数据库业务集合缓存
	private static List<Object> packgeTableList = new ArrayList<Object>();
	// public static Map<String,String> AGENTBM=new HashMap<String,String>();

	public static String getPropSet(String key) {
		return UtilTool.obj2Str(AppContext.prop.getProperty(key));
	}

	public static String getPropSet(String key, String defalut) {
		return UtilTool.obj2Str(AppContext.prop.getProperty(key, defalut));
	}

	public static void sysInit() throws Exception {

		Thread a = new AutoProcesserData();
		a.setPriority(10);
		a.start();

		/*
		 * Thread his=new HisProcesser(); his.setPriority(10); his.start();
		 */

		PackageScanner scan = new ClasspathPackageScanner("com.loncom.ismac.servlet");
		packagelist = scan.getFullyQualifiedClassNameList(new PackageClass());
		// 开启扫描数据库实体模型
		scan = new ClasspathPackageScanner("com.loncom.ismac.bean");
		packgeTableList = scan.getFullyQualifiedClassNameList(new PackageClassTable());

		InitUser(); // 初始化用户权限队列
		InitService();// 初始化服务
		InitTable(); // 历史数据等
		InitCompetence();// 页面操作权限
	}

	/**
	 * 页面操作权限
	 * 
	 * @throws Exception
	 */
	public static void InitCompetence() throws Exception {
		Dictionaries obj = new Dictionaries();
		List list = baseservice.query(obj);
		for (Object object : list) {
			obj = (Dictionaries) object;
			String comid = AppContext.menurole.get(obj.getPid());
			if (comid != null) {
				if (comid.equals("")) {
					comid += obj.getName();
				} else {
					comid += "," + obj.getName();
				}
				AppContext.menurole.put(obj.getPid(), comid);
			} else {
				AppContext.menurole.put(obj.getId(), "");
			}
		}
	}

	private static void InitTable() {
		// TODO Auto-generated method stub
		try {
			isHisDevTable();
			isClassTable();
		} catch (Exception e) {
			e.printStackTrace();
		} // 判断是否有历史表
		TaskQuartz task = new TaskQuartz();// 任务调度
		task.executes();// 启动任务调度
	}

	private static void isHisDevTable() throws Exception {
		// 历史表
//		String table = String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD());
//		int count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, table), DB.HIS);
//		if (count <= 0) {
//			baseservice.exeSql(String.format(CMD.CREATE_HIS_DEV, table), DB.HIS);
//		}
		//日表
		String daytable=String.format(CMD.DAY_DEVTABLE, UtilTimeThread.format(new Date(), "yyyyMMdd"));
		int count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, daytable), DB.HIS);//获取当天日报表是否存在
		if (count <= 0) {//如果不存在进入方法
			BaseUtil.createRPT(daytable);//创建日报表
		}
		
		 daytable=String.format(CMD.MONTH_DEVTABLE, UtilTimeThread.format(new Date(), "yyyyMM"));
		 count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, daytable), DB.HIS);//获取当天日报表是否存在
		if (count <= 0) {//创建月报表
           baseservice.exeSql(String.format(CMD.MONTH_CREATE, daytable));
		}
		
		daytable=String.format(CMD.YEAR_DEVTABLE, UtilTimeThread.format(new Date(), "yyyy"));
		 count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, daytable), DB.HIS);//获取当天日报表是否存在
		if (count <= 0) {//创建年报表
			baseservice.exeSql(String.format(CMD.MONTH_CREATE, daytable));
		}
	}

	private static void isClassTable() throws Exception {
		List<ClassroomXml> classlist = new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist = new ArrayList<ClassroomXml>();
		IBaseService baseservice = new BaseServiceImpl();
		String rpttable = "";
		Map<String, List<Objc>> floormap;
		Map<String, Map<String, List<Objc>>> groupmap;
		List<Objc> list = new ArrayList<Objc>();
		Objc objc;// 存储机房对象
		BaseItemDevXml item = null;// 设备对象
		String devpoint = "";
		for (Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				groupmap = group.get(groupXml.getGroupno());
				if (groupmap == null) {
					group.put(groupXml.getGroupno(), new HashMap());
					groupmap = group.get(groupXml.getGroupno());
					// groupmap.put(groupXml.getGroupno(), new HashMap<String,
					// List<Objc>>());
				}
				if (groupXml.getClassroomgroup().getItem() != null) {
					for (ClassroomXml classroomXml : groupXml.getClassroomgroup().getItem()) {
						if (!BaseUtil.isNotNull(classroomXml.getElement())) {
							item = getItemObj(classroomXml.getBaseItem(), "powerdegree");
							floormap = groupmap.get(classroomXml.getFloor());
							devpoint = getSplitDevpoint(item.getPointid());
							if (floormap == null) {
								groupmap.put(classroomXml.getFloor(), new HashMap());
								objc = new Objc();
								objc.setId(classroomXml.getCode());
								objc.setName(classroomXml.getClassname());
								objc.setItem(classroomXml.getBaseItem());
								objc.setServerid(service.getId());
								objc.setDevid(BaseUtil.isNotNull(item.getPointid())?devpoint.split(",")[0]:"");
								objc.setPointid(BaseUtil.isNotNull(item.getPointid())?devpoint.split(",")[1]:"");
								list = new ArrayList<Objc>();
								list.add(objc);
								groupmap.get(classroomXml.getFloor()).put("1", list);
							} else {
								objc = new Objc();
								objc.setId(classroomXml.getCode());
								objc.setName(classroomXml.getClassname());
								objc.setServerid(service.getId());
								objc.setDevid(BaseUtil.isNotNull(item.getPointid())?devpoint.split(",")[0]:"");
								objc.setPointid(BaseUtil.isNotNull(item.getPointid())?devpoint.split(",")[1]:"");
								objc.setItem(classroomXml.getBaseItem());
								if (floormap.get("1") == null) {
									list = new ArrayList<Objc>();
									list.add(objc);
									groupmap.get(classroomXml.getFloor()).put("1", list);
								} else {
									floormap.get("1").add(objc);
								}
							}
							rpttable = String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							int count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, rpttable), DB.HIS);
							if (count <= 0) {
								BaseUtil.createRPT(rpttable);
							}
						}
					}
				}
				if (groupXml.getOfficegroup().getItem() != null) {
					for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
						if (!BaseUtil.isNotNull(classroomXml.getElement())) {
							item = getItemObj(classroomXml.getBaseItem(), "powerdegree");
							floormap = groupmap.get(classroomXml.getFloor());
							devpoint = getSplitDevpoint(item.getPointid());
							if (floormap == null) {
								groupmap.put(classroomXml.getFloor(), new HashMap());
								objc = new Objc();
								objc.setId(classroomXml.getCode());
								objc.setName(classroomXml.getClassname());
								objc.setDevid(BaseUtil.isNotNull(item.getPointid())?devpoint.split(",")[0]:"");
								objc.setPointid(BaseUtil.isNotNull(item.getPointid())?devpoint.split(",")[1]:"");
								objc.setItem(classroomXml.getBaseItem());
								list = new ArrayList<Objc>();
								list.add(objc);
								groupmap.get(classroomXml.getFloor()).put("2", list);
							} else {
								objc = new Objc();
								objc.setId(classroomXml.getCode());
								objc.setName(classroomXml.getClassname());
								objc.setDevid(BaseUtil.isNotNull(item.getPointid())?devpoint.split(",")[0]:"");
								objc.setPointid(BaseUtil.isNotNull(item.getPointid())?devpoint.split(",")[1]:"");
								objc.setItem(classroomXml.getBaseItem());
								if (floormap.get("2") == null) {
									list = new ArrayList<Objc>();
									list.add(objc);
									groupmap.get(classroomXml.getFloor()).put("2", list);
								} else {
									floormap.get("2").add(objc);
								}
							}
							rpttable = String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							int count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, rpttable), DB.HIS);
							if (count <= 0) {
								BaseUtil.createRPT(rpttable);
							}
						}
					}
				}
			}
		}
	/*
		group.get("1").get("2");*/
		//List<Objc> classroom = new ArrayList<Objc>();
		//List<Objc> office = new ArrayList<Objc>();
		//get(group.get("1"),classroom,office);
	}

	/**
	 * 指定获取设别对象
	 * 
	 * @param list
	 * @param key
	 * @return
	 */

	public static BaseItemDevXml getItemObj(List<BaseItemDevXml> list, String key) {
		for (BaseItemDevXml baseItemDevXml : list) {
			if (key.equals(baseItemDevXml.getDev())) {
				return baseItemDevXml;
			}
		}
		return null;
	}

	/**
	 * 设备ID与设备测点的拆分
	 * 
	 * @param value
	 * @return
	 */
	public static String getSplitDevpoint(String value) {
		String devid = "";
		String pointid = "";
		if (BaseUtil.isNotNull(value)) {
			String[] splitdev = value.split("_");

			for (String string : splitdev) {
				String[] pointids = string.split(",");
				devid += pointids[0] + "_";
				pointid += pointids[1] + "_";
			}
			if (BaseUtil.isNotNull(devid)) {
				devid = devid.substring(0, devid.lastIndexOf("_"));
			}
			if (BaseUtil.isNotNull(pointid)) {
				pointid = pointid.substring(0, pointid.lastIndexOf("_"));
			}
		}
		return devid + "," + pointid;
	}

	/**
	 * 初始化用户
	 * 
	 * @throws Exception
	 */
	public static void InitUser() throws Exception {
		userMap = new HashMap<String, UserBean>();
		UserBean userBean = new UserBean();
		List userList = new ArrayList<>();
		userList = baseservice.query(userBean);
		for (Object object : userList) {
			userBean = (UserBean) object;
			userMap.put(userBean.getUserid(), userBean);
		}
	}

	/**
	 * 初始化获取数据服务
	 * 
	 * @throws Exception
	 */
	public static void InitService() {

		// IBaseService<Service> baseservice=new BaseServiceImpl<Service>();
		Service obj = new Service();
		// obj.setState("1");
		try {
			service = baseservice.query(obj);
			for (Service object : service) {
				if (BaseUtil.isNotNull(object.getSysxml()) && BaseUtil.isNotNull(object.getDevxml())) {
					/*
					 * String classpath =
					 * this..getResource("/").getPath().replaceFirst("/", "");
					 * String docRoot = classpath.replaceAll("WEB-INF/classes/",
					 * "upload");
					 */

					// String url =
					// UtilFile.readToString("D://笼空/JAVA项目/apache-tomcat-7.0.53/wtpwebapps/iSmacSite/xml/"+
					// object.getXmlurl());
					String xmlurl = Thread.currentThread().getContextClassLoader().getResource("/").toURI().getPath();
				
					/*
					 * if (XmlEdiParser.class.getClassLoader().getResource(
					 * "../../xml/" + object.getXmlurl()) != null) {
					 */
					String url = FileUtil
							.readToString(xmlurl.replaceAll("WEB-INF/classes/", "xml/" + object.getSysxml()));

					RootXml root = XmlEdiParser.parseRootData(url);
					url = FileUtil.readToString(xmlurl.replaceAll("WEB-INF/classes/", "xml/" + object.getDevxml()));

					RootDevXml rootdev = XmlEdiParser.parseRootDevData(url);
					object.setRootdev(rootdev);
					if (root != null) {
						object.setGroupcontrol(root.getGroupcontrol());
						InitDev(rootdev);
						String context = JaxbUtil.toXml(root);

						/* } */
					}
				}
				AddService(object);
				if (BaseUtil.isNotNull(object.getAgentbm())) {
					// NewRptAir(object.getAgentbm());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logs.SysLog("00103", "系统", e.getMessage(), "", "127.0.0.1");
		}
	}

	/**
	 * 新增采集服务
	 * 
	 * @param object
	 */

	public static void AddService(Service object) {
		BaseSocketClient tcp = null;
		if ("V1".equals(object.getProtocol())) {// 协议切换 默认为V3
			tcp = new LoncomipDataSocketClientV1();
		} else if ("V2".equals(object.getProtocol())) {
			tcp = new LoncomipDataSocketClientV2();
		} else {
			tcp = new LoncomipDataSocketClient();
		}
		ServicePool.execute(tcp.initConnect(object));
		Map map = new HashMap();
		map.put("DATA", tcp);
		socketall.put(object.getId(), map);
		/*
		 * tcp = new LoncomipDataAddOutClient();
		 * 
		 * map.put("OUT", tcp); socketall.put(object.getId(), map);
		 */
		/*
		 * ServicePool.execute(tcp.initConnect(object)); Map<String,
		 * BaseSocketClient> map=new HashMap<String, BaseSocketClient>();
		 * map.put(CMD.TCP_DATA, tcp); tcp=new LoncomipDataAddOutClient();
		 * ServicePool.execute(tcp.initConnect(object)); map.put(CMD.TCP_OUT,
		 * tcp); socketall.put(object.getId(), map);
		 */
		// InitFictitiousDev(object.getAgentbm());

	}
	/**
	 * 获取
	 * @param map
	 * @param classroom
	 * @param office
	 */
	public static void getMapClassooffice(Map map,List<Objc> classroom,List<Objc> office){
		if(map!=null && map.size()>0) {
			Iterator<Entry> Itgroup = map.entrySet().iterator();
			Iterator<Entry<String, List<Objc>>> itflor = null;
			Entry<String, List<Objc>> entryflor = null;
			 List<Objc> list=null;
			while (Itgroup.hasNext()) {//把Map里面所有教室和办公室获取出来
				Entry<String, Map> entrygroup = Itgroup.next();
				if(entrygroup.getValue() instanceof Map){
					getMapClassooffice(entrygroup.getValue(),classroom,office);
				}else if(entrygroup.getValue() instanceof List){
					list=(List<Objc>) entrygroup.getValue();
						if ("1".equals(entrygroup.getKey())) {
							classroom.addAll(list);
						} else {
							office.addAll(list);
						}
					
				}
			}
		}
	}

	public static void InitDev(RootDevXml root) {
		if (root.getDevicelist() != null) {
			DevheadBean devhead = null;
			for (DevheadXml devheadx : root.getDevicelist().getDev()) {
				devhead = new DevheadBean();
				// devhead.setAgentbm(object.getGroupcontrol().getSyspara().get);
				devhead.setDevname(devheadx.getDevname());
				devhead.setMgrobjid(devheadx.getDevid());
				devhead.setAgentbm(root.getSyspara().getMap().get("DWBM"));

				for (DevvouXml devvouxml : devheadx.getPoint()) {
					devvouxml.setMgrobjid(devheadx.getDevid());
					devhead.getItem().put(devheadx.getDevid() + "_" + devvouxml.getId(), devvouxml);
				}
				getDevhead().put(devheadx.getDevid(), devhead);
			}
		}
		/*
		 * if (root.getDevicevoulist() != null) { for (DevvouXml devvoux :
		 * root.getDevicevoulist().getDevvoulist()) { DevheadBean devheadBean =
		 * getDevhead() .get(root.getSyspara().getMap().get("DWBM") + "_" +
		 * devvoux.getMgrobjid()); if (devheadBean != null) {
		 * devheadBean.getItem().put(devheadBean.getMgrobjid() + "_" +
		 * devvoux.getId(), devvoux); } } }
		 */
	}

	/**
	 * 更新数据
	 * 
	 * @param datalist
	 * @throws Exception
	 */
	public static void UpdateDevvouValue(List<DataPack> datalist) {
		for (DataPack statePack : datalist) {
			DevheadBean devhead = getDevhead().get(statePack.getMgrobjid());
			if (devhead != null) {
				DevvouXml devvou = devhead.getItem().get(statePack.getMgrobjid() + "_" + statePack.getPropertyId());
				// System.out.println("设备ID数据更新:"+statePack.getMgrobjid());
				if (devvou != null) {
					if ("CommStatus".equals(devvou.getDatachar())) {
						if ("1".equals(statePack.getValue())) {
							devvou.setValue("通讯中断");
							statePack.setValue("通讯中断");
						} else {
							devvou.setValue("正常");
							statePack.setValue("正常");
						}
					} else {
						devvou.setValue(statePack.getValue());
						if(statePack.getValue()!=null&&!statePack.getValue().equals("0")&&
						!statePack.getValue().equals("0.0")&&!statePack.getValue().equals("0.00")
						&&!statePack.getValue().equals("")) {
							devvou.setBackvalue(statePack.getValue());
						}
					}
				}
			}

		}
	}

	public static Map<String, Object> getSID() {
		return SID;
	}

	public static void setSID(Map<String, Object> sID) {
		SID = sID;
	}

	public static List<Object> getPackgeTableList() {
		return packgeTableList;
	}

	public static void setPackgeTableList(List<Object> packgeTableList) {
		AppContext.packgeTableList = packgeTableList;
	}

	public static List<Object> getPackagelist() {
		return packagelist;
	}

	public static void setPackagelist(List<Object> packagelist) {
		AppContext.packagelist = packagelist;
	}

	public static Map<String, DevheadBean> getDevhead() {
		return devhead;
	}

	public static void setDevhead(Map<String, DevheadBean> devhead) {
		AppContext.devhead = devhead;
	}

	public static List<Service> getService() {
		return service;
	}

	public static void setService(List<Service> service) {
		AppContext.service = service;
	}

	public static Map<String, Map<String, BaseSocketClient>> getSocketall() {
		return socketall;
	}

	public static void setSocketall(Map<String, Map<String, BaseSocketClient>> socketall) {
		AppContext.socketall = socketall;
	}

	public static Map<String, UserBean> getUserMap() {
		return userMap;
	}

	public static void setUserMap(Map<String, UserBean> userMap) {
		AppContext.userMap = userMap;
	}

	public static Map<String, String> getMenurole() {
		return menurole;
	}

	public static void setMenurole(Map<String, String> menurole) {
		AppContext.menurole = menurole;
	}

	/*
	 * public static List<String> getLodsysmenu() { return lodsysmenu; }
	 * 
	 * public static void setLodsysmenu(List<String> lodsysmenu) {
	 * AppContext.lodsysmenu = lodsysmenu; }
	 */

}
