package com.loncom.ismac.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.loncom.ismac.bean.DataPack;
import com.loncom.ismac.bean.DevheadBean;
import com.loncom.ismac.bean.xml.ClassroomXml;
import com.loncom.ismac.bean.xml.DevheadXml;
import com.loncom.ismac.bean.xml.DevvouXml;
import com.loncom.ismac.bean.xml.GroupXml;
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
import com.loncom.ismac.thread.HisProcesser;
import com.loncom.ismac.user.bean.UserBean;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.CMD;
import com.loncom.ismac.util.FileUtil;
import com.loncom.ismac.util.JaxbUtil;
import com.loncom.ismac.util.UtilTime;
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
	/**
	 * 设备列表
	 */
	private static Map<String, DevheadBean> devhead = new HashMap<String, DevheadBean>();
	//用户权限
	private static Map<String,UserBean> userMap=new HashMap<String,UserBean>();
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
		
		Thread his=new HisProcesser();
		his.setPriority(10);
		his.start();

		PackageScanner scan = new ClasspathPackageScanner("com.loncom.ismac.servlet");
		packagelist = scan.getFullyQualifiedClassNameList(new PackageClass());
		// 开启扫描数据库实体模型
		scan = new ClasspathPackageScanner("com.loncom.ismac.bean");
		packgeTableList = scan.getFullyQualifiedClassNameList(new PackageClassTable());
		
		InitUser();  //初始化用户权限队列
		InitService();// 初始化服务
		InitTable();  //历史数据等
	}

	private static void InitTable() {
		// TODO Auto-generated method stub
		try {
			isHisDevTable();
			isClassTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //判断是否有历史表
		TaskQuartz task = new TaskQuartz();// 任务调度
		task.executes();// 启动任务调度
	}

	private static void isHisDevTable() throws Exception {
		// 历史表
		String table=String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD());
		int count=baseservice.getCount(String.format(CMD.IS_HIS_BASE, table), DB.HIS);
		if(count<=0) {
			baseservice.exeSql(String.format(CMD.CREATE_HIS_DEV, table), DB.HIS);
		}
		
	}

	private static void isClassTable() throws Exception {
		List<ClassroomXml> classlist=new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist=new ArrayList<ClassroomXml>();
		IBaseService baseservice= new BaseServiceImpl();
		String rpttable="";
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				classlist=BaseUtil.getService(groupXml.getGroupno(), "classroom", null, null);
				dormlist=BaseUtil.getService(groupXml.getGroupno(), "officeroom", null, null);
				for (ClassroomXml classroomXml : classlist) {
					rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
					int count=baseservice.getCount(String.format(CMD.IS_HIS_BASE, rpttable), DB.HIS);
					if(count<=0) {
						baseservice.exeSql(String.format(CMD.CREATE_RPT_DEV, rpttable), DB.HIS);
					}
				}
				for (ClassroomXml classroomXml : dormlist) {
					rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
					int count=baseservice.getCount(String.format(CMD.IS_HIS_BASE, rpttable), DB.HIS);
					if(count<=0) {
						baseservice.exeSql(String.format(CMD.CREATE_RPT_DEV, rpttable), DB.HIS);
					}
				}
			}
		}
		// 教室表
		
		
	}

	
	/**
	 * 初始化用户
	 * @throws Exception 
	 */
	public static void InitUser() throws Exception {
		UserBean userBean=new UserBean();
		List userList=new ArrayList<>();
		userList=baseservice.query(userBean);
		for (Object object : userList) {
			userBean=(UserBean) object;
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
				if (BaseUtil.isNotNull(object.getXmlurl())) {
					/*
					 * String classpath = this..getResource("/").getPath().replaceFirst("/", "");
					 * String docRoot = classpath.replaceAll("WEB-INF/classes/", "upload");
					 */

					// String url =
					// UtilFile.readToString("D://笼空/JAVA项目/apache-tomcat-7.0.53/wtpwebapps/iSmacSite/xml/"+
					// object.getXmlurl());
					String xmlurl = Thread.currentThread().getContextClassLoader().getResource("/").toURI().getPath();
					System.out.println();
					/*
					 * if (XmlEdiParser.class.getClassLoader().getResource( "../../xml/" +
					 * object.getXmlurl()) != null) {
					 */
					String url = FileUtil
							.readToString(xmlurl.replaceAll("WEB-INF/classes/", "xml/") + object.getXmlurl());
					RootXml root = XmlEdiParser.parseRootData(url);
					if (root != null) {
						object.setGroupcontrol(root.getGroupcontrol());
						InitDev(root);
						String context = JaxbUtil.toXml(root);
						System.out.println(context);

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
		 * ServicePool.execute(tcp.initConnect(object)); Map<String, BaseSocketClient>
		 * map=new HashMap<String, BaseSocketClient>(); map.put(CMD.TCP_DATA, tcp);
		 * tcp=new LoncomipDataAddOutClient();
		 * ServicePool.execute(tcp.initConnect(object)); map.put(CMD.TCP_OUT, tcp);
		 * socketall.put(object.getId(), map);
		 */
		// InitFictitiousDev(object.getAgentbm());

	}

	public static void InitDev(RootXml root) {
		if (root.getDeviceheadlist() != null) {
			DevheadBean devhead = null;
			for (DevheadXml devheadx : root.getDeviceheadlist().getDevhead()) {
				devhead = new DevheadBean();
				// devhead.setAgentbm(object.getGroupcontrol().getSyspara().get);
				devhead.setDevname(devheadx.getDevname());
				devhead.setMgrobjid(devheadx.getMgrobjid());
				devhead.setAgentbm(root.getSyspara().getMap().get("DWBM"));
				getDevhead().put(root.getSyspara().getMap().get("DWBM") + "_" + devhead.getMgrobjid(), devhead);
			}
		}
		if (root.getDevicevoulist() != null) {
			for (DevvouXml devvoux : root.getDevicevoulist().getDevvoulist()) {
				DevheadBean devheadBean = getDevhead()
						.get(root.getSyspara().getMap().get("DWBM") + "_" + devvoux.getMgrobjid());
				if (devheadBean != null) {
					devheadBean.getItem().put(devheadBean.getMgrobjid() + "_" + devvoux.getId(), devvoux);
				}
			}
		}
	}

	/**
	 * 更新数据
	 * 
	 * @param datalist
	 * @throws Exception
	 */
	public static void UpdateDevvouValue(List<DataPack> datalist) {
		for (DataPack statePack : datalist) {
			DevheadBean devhead = getDevhead().get(statePack.getAgentbm() + "_" + statePack.getMgrobjid());
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
	
	

}
