package com.loncom.ismac.servlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loncom.ismac.annotation.MethodInfo;
import com.loncom.ismac.annotation.Modular;
import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.bean.DataPack;
import com.loncom.ismac.bean.zTreeByOther;
import com.loncom.ismac.bean.xml.ClassroomXml;
import com.loncom.ismac.bean.xml.ClassroomgroupXml;
import com.loncom.ismac.bean.xml.GroupXml;
import com.loncom.ismac.bean.xml.OfficegroupXml;
import com.loncom.ismac.bean.xml.RootXml;
import com.loncom.ismac.bean.xml.XmlEdiParser;
import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.lservice.bean.Service;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.CMD;
import com.loncom.ismac.util.FileUtil;
import com.loncom.ismac.util.UtilTime;
import com.loncom.ismac.util.UtilTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Modular(MODULARNAME="数据服务模块")
public class ServiceAction extends BaseServlet {
	
	ClassroomXml obj=new ClassroomXml();
	
	public static Map<String, Object> querychange(ClassroomXml classroomXml){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("code", classroomXml.getCode());
		map.put("classname", classroomXml.getClassname());
		map.put("model", classroomXml.getModel());
		map.put("times", classroomXml.getTimes());
		map.put("timegroup", classroomXml.getTimegroup());
		//空调
		String air=BaseUtil.getDevvouValue(classroomXml.getAirdev().getDevid(),classroomXml.getAirdev().getStateid());
		map.put("airDevid", classroomXml.getAirdev().getDevid());
		map.put("airStateid", classroomXml.getAirdev().getStateid());  
		map.put("airClosecmd", classroomXml.getAirdev().getColsecmd());
		map.put("air",air.equals("0")?false:true);
		map.put("airdev", air);
		//实时功耗
		map.put("ammeterdev", BaseUtil.getDevvouValue(classroomXml.getAmmeterdev().getDevid(),classroomXml.getAmmeterdev().getStateid()));
		//humituredev，环境温度
		map.put("humituredev", BaseUtil.getDevvouValue(classroomXml.getHumituredev().getDevid(),classroomXml.getHumituredev().getStateid()));
		//红外，有无人
		map.put("infrareddev", BaseUtil.getDevvouValue(classroomXml.getInfrareddev().getDevid(),classroomXml.getInfrareddev().getStateid()));
		//电灯，照明状态
		String lamp=BaseUtil.getDevvouValue(classroomXml.getLampdev().getDevid(),classroomXml.getLampdev().getStateid());
		map.put("lampDevid", classroomXml.getLampdev().getDevid());
		map.put("lampStateid", classroomXml.getLampdev().getStateid());  
		map.put("lampClosecmd", classroomXml.getLampdev().getColsecmd());
		map.put("lamp", lamp.equals("0")?false:true);
		map.put("lampdev", lamp);
		//模式 0手动 1自动  手动且开着的可以编辑，其它不能编辑 ；true禁止编辑，false不禁止编辑
		map.put("airtype", (classroomXml.getModel().equals("0")&&!air.equals("0"))?false:true); 
		map.put("lamptype", (classroomXml.getModel().equals("0")&&!lamp.equals("0"))?false:true); 
		return map;
	}
	public static Map<String, Object> detailchange(ClassroomXml classroomXml){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("code", classroomXml.getCode());
		map.put("model", classroomXml.getModel());
		map.put("times", classroomXml.getTimes());
		map.put("timegroup", classroomXml.getTimegroup());
		return map;
	}
	public static Map<String, Object> airchange(ClassroomXml classroomXml){
		Map<String,Object> map=new HashMap<String,Object>();
		String air=BaseUtil.getDevvouValue(classroomXml.getAirdev().getDevid(),classroomXml.getAirdev().getStateid());
		map.put("air",air.equals("0")?false:true);
		return map;
	}
	/**
	 * 获取学校的人数教室数办公室数面积数
	 * @return
	 */
	public static Map<String, Object> schoolItem(){
		Map<String,Object> map=new HashMap<String,Object>();
		int classroomcount=0,dormcount=0,countnumber=0;
		float acreage=0;
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				acreage+=UtilTool.parseFloat(groupXml.getAcreage());
				classroomcount+=UtilTool.parseInt(groupXml.getClassroomcount());
				dormcount+=UtilTool.parseInt(groupXml.getDormcount());
				countnumber+=UtilTool.parseInt(groupXml.getCountnumber());
			}
		}
		map.put("classroomcount", classroomcount);  //教室数
		map.put("dormcount", dormcount);  //办公室数
		map.put("countnumber", countnumber); // 总人数
		map.put("acreage", acreage); //面积数
		return map;
		
	}
	/**
	 * 获取总能耗及教室或办公室的排序
	 */
	public static Map<String, Object> typeSort(List<Map<String, Object>> list){
		List<ClassroomXml> classlist=new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist=new ArrayList<ClassroomXml>();
		List<Map<String, Object>> alist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> clist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> dlist=new ArrayList<Map<String, Object>>();
		classlist=BaseUtil.getService(null, "classroom", null, null);
		dormlist=BaseUtil.getService(null, "officeroom", null, null);
		Map<String, Object> map=new HashMap<String,Object>();
		float classroom = 0,officeroom=0,allpower=0;
		for (Map<String, Object> map2 : list) {
			
			
			for (ClassroomXml classroomXml : classlist) {
				if(classroomXml.getAmmeterdev().getDevid().equals(map2.get("MGROBJID"))) {
					allpower+=Float.parseFloat((String) map2.get("VALUE"));
					classroom+=Float.parseFloat((String) map2.get("VALUE"));
					map2.put("classname", classroomXml.getClassname());
					clist.add(map2);
					alist.add(map2);
				}
			}
			for (ClassroomXml classroomXml : dormlist) {
				if(classroomXml.getAmmeterdev().getDevid().equals(map2.get("MGROBJID"))) {
					allpower+=Float.parseFloat((String) map2.get("VALUE"));
					officeroom+=Float.parseFloat((String) map2.get("VALUE"));
					map2.put("classname", classroomXml.getClassname());
					dlist.add(map2);
					alist.add(map2);
				}
			}
		}
		map.put("allpower", UtilTool.cutFloat2(allpower+""));
		map.put("classroom", UtilTool.cutFloat2(classroom+""));
		map.put("officeroom", UtilTool.cutFloat2(officeroom+""));
		map.put("classList", clist);
		map.put("dormlist", dlist);
		map.put("alllist", alist);
		return map;
	}
	
	public static Map<String, Object> allSort(List<Map<String, Object>> list){
		List<ClassroomXml> classlist=new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist=new ArrayList<ClassroomXml>();
		List<Map<String, Object>> alist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> clist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> dlist=new ArrayList<Map<String, Object>>();
		
		List<Map<String, Object>> tableTitle=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> tableBody=new ArrayList<Map<String, Object>>();
		Map<String, Object> mapTitle=new HashMap<String,Object>();
		
		List<GroupXml> group=new ArrayList<GroupXml>();
		Map<String, Object> map=new HashMap<String,Object>();
		for(GroupXml groupXml:BaseUtil.getFloor()) {
			mapTitle=new HashMap<String,Object>();
			mapTitle.put("prop","lou"+groupXml.getGroupno());
			mapTitle.put("label", groupXml.getGroupname());
			mapTitle.put("minWidth", 10);
			tableTitle.add(mapTitle);
			
			classlist=BaseUtil.getService(groupXml.getGroupno(), "classroom", null, null);
			dormlist=BaseUtil.getService(groupXml.getGroupno(), "officeroom", null, null);
			for(Map<String, Object> map2 : list) {
				for (ClassroomXml classroomXml : classlist) {
					if(classroomXml.getAmmeterdev().getDevid().equals(map2.get("MGROBJID"))) {
						String tvalue=UtilTool.cutFloat2(UtilTool.parseFloat(map2.get("VALUE")+"")+"");
						map2.put("lou"+groupXml.getGroupno(), tvalue);
						map2.put("allpower", tvalue);
						tableBody.add(map2);
					}
				}
				for (ClassroomXml classroomXml : dormlist) {
					if(classroomXml.getAmmeterdev().getDevid().equals(map2.get("MGROBJID"))) {
						String tvalue=UtilTool.cutFloat2(UtilTool.parseFloat(map2.get("VALUE")+"")+"");
						map2.put("lou"+groupXml.getGroupno(), tvalue);
						map2.put("allpower", tvalue);
						tableBody.add(map2);
					}
				}
			}
		}
		map.put("tableTitle", tableTitle);
		map.put("tableBody", tableBody);
		return map;
	}
	
	/**
	 * 获取所有 
	 * @return
	 * @throws Exception
	 */
	@MethodInfo(METHOD="/service/query")
	public String query()throws Exception{
		String classname=getRequest().getParameter("name");
		String groupno=getRequest().getParameter("groupno");
		String classno=getRequest().getParameter("classno");
		List<ClassroomXml> list=new ArrayList<ClassroomXml>();
		list=BaseUtil.getService(groupno, classno, classname, null);
		List list1=new ArrayList<>();
		String str=getRoleAddrList();
		
		for (ClassroomXml classroomXml : list) {
			if(str.equals("0")) {
				list1.add(querychange(classroomXml));
			}else if(!str.equals("-1")){
				List<String> arr=Arrays.asList(str.split(","));
				if(arr.contains(classroomXml.getCode())) {
					list1.add(querychange(classroomXml));
				}
			}
		}
		
		System.out.println(str);
		return JSONArray.fromObject(list1).toString();
	}
	/**
	 * 获取详情
	 * @return
	 * @throws Exception
	 */
	@MethodInfo(METHOD="/service/detail")
	public String detail() throws Exception{
		String code=getRequest().getParameter("code");
		List list=new ArrayList<>();
		list=BaseUtil.getService(null, null, null, code);
		if(list.size()>0) {
			return JSONObject.fromObject(detailchange((ClassroomXml) list.get(0))).toString();
		}else {
			return null;
		}	
	}
	
	/**
	 * 更新信息
	 * @return
	 * @throws Exception
	 */
	@MethodInfo(METHOD="/service/update")
	public String update() throws Exception{
		String code=getRequest().getParameter("code");
		String model=getRequest().getParameter("model");
		String times=getRequest().getParameter("times");
		String timegroup=getRequest().getParameter("timegroup");
		List<ClassroomXml> list=new ArrayList<ClassroomXml>();
		list=BaseUtil.getService(null, null, null, code);
		list.get(0).setModel(model);
		list.get(0).setTimes(times);
		list.get(0).setTimegroup(timegroup);
		
		Service service=AppContext.getService().get(0);
		String xml = getRequest().getSession().getServletContext().getRealPath("/xml/" + service.getXmlurl());
		String url = FileUtil.readToString(xml);
		RootXml root = XmlEdiParser.parseRootData(url);
		
		root.setGroupcontrol(service.getGroupcontrol());
		BaseUtil.ObjToXMl(root,xml);
		return null;
	}
	
	/**
	 * 获取top用电量，带累计用电量
	 */
	@SuppressWarnings("deprecation")
	@MethodInfo(METHOD="/service/top")
	public String queryTop() throws Exception{
		String type=getRequest().getParameter("type");
		String startTime=getRequest().getParameter("startTime");
		String endTime=getRequest().getParameter("endTime");
		String sql = null;
		String devtable=null;
		List dateList=new ArrayList<>();
		List<Map<String, Object>> alllist = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> backlist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> returnlist=new ArrayList<Map<String, Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> topMap=new HashMap<String,Object>();
		
		dateList=UtilTime.getDatesBetweenTwoDate(startTime, endTime);
		System.out.println(dateList);
		String[] first=((String) dateList.get(0)).split("\\s");
		String[] last=((String) dateList.get(dateList.size()-1)).split("\\s");
		if(first[0].equals(last[0])) {
			devtable=String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD(UtilTime.stringToDate(dateList.get(0)+"")));
			int count=baseservice.getCount(String.format(CMD.IS_HIS_BASE, devtable), DB.HIS);
			if(count>0) {
				sql="select * from %s";
				sql=String.format(sql,devtable);
				try {
					List<Map<String, Object>> list = baseservice.getSqlListS(sql,DB.HIS);
					if (list != null && list.size() > 0) {
						alllist.addAll(list);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}else {
			for(int i=0;i<dateList.size();i++) {
				devtable=String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD(UtilTime.stringToDate(dateList.get(i)+"")));
				int count=baseservice.getCount(String.format(CMD.IS_HIS_BASE, devtable), DB.HIS);
				if(count>0) {
					if(i==0) {
						sql="select * from %s where time > '%s'";
						sql=String.format(sql,devtable,dateList.get(i));
					}else if(i==dateList.size()-1) {
						sql="select * from %s where time < '%s'";
						sql=String.format(sql,devtable,dateList.get(i));
					}else {
						sql="select * from %s";
						sql=String.format(sql,devtable);
					}
					try {
						List<Map<String, Object>> list = baseservice.getSqlListS(sql,DB.HIS);
						if (list != null && list.size() > 0) {
							alllist.addAll(list);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
		
		System.out.println(alllist);
		backlist=BaseUtil.downSort(alllist, "VALUE");
		Map<String, Object> backmap=typeSort(backlist);
		System.out.println(backmap);
		map.put("allPower",backmap.get("allpower"));
		List<Map<String, Object>> classList=new ArrayList<Map<String, Object>>();
		if(BaseUtil.isNotNull(type)) {
			//根据类型获取top10
			if(type.equals("classroom")) {
				classList=(List) backmap.get("classList");
			}
			if(type.equals("officeroom")) {
				classList=(List) backmap.get("dormlist");
			}
		}else {
			//所有所有的top10
			classList=(List) backmap.get("alllist");
		}
		for (Map<String, Object> map1 : classList) {
			topMap=new HashMap<String,Object>();
			topMap.put("classname", map1.get("classname"));
			topMap.put("value", map1.get("VALUE"));
			float rate=(Float.parseFloat(map1.get("VALUE")+"")/Float.parseFloat(backmap.get("allpower")+""))*100;
			topMap.put("rate",UtilTool.cutFloat2(rate+"")) ;
			returnlist.add(topMap);
		}
		map.put("top", returnlist);
		return JSONObject.fromObject(map).toString();
	}
	
	/**
	 * 获取用电数据，按时间查询返回总用电量，教学楼，实验楼
	 */
	@SuppressWarnings("deprecation")
	@MethodInfo(METHOD="/service/allInfo")
	public String queryInfo() throws Exception{
		String type=getRequest().getParameter("type");
		String startTime=getRequest().getParameter("startTime");
		String endTime=getRequest().getParameter("endTime");
		String sql = null;
		String devtable=null;
		List dateList=new ArrayList<>();
		List<Map<String, Object>> alllist = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> backlist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> returnlist=new ArrayList<Map<String, Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> topMap=new HashMap<String,Object>();
		
		dateList=UtilTime.getDatesBetweenTwoDate(startTime, endTime);
		System.out.println(dateList);
		String[] first=((String) dateList.get(0)).split("\\s");
		String[] last=((String) dateList.get(dateList.size()-1)).split("\\s");
		if(first[0].equals(last[0])) {
			devtable=String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD(UtilTime.stringToDate(dateList.get(0)+"")));
			int count=baseservice.getCount(String.format(CMD.IS_HIS_BASE, devtable), DB.HIS);
			if(count>0) {
				sql="select * from %s";
				sql=String.format(sql,devtable);
				try {
					List<Map<String, Object>> list = baseservice.getSqlListS(sql,DB.HIS);
					if (list != null && list.size() > 0) {
						alllist.addAll(list);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		}else {
			for(int i=0;i<dateList.size();i++) {
				devtable=String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD(UtilTime.stringToDate(dateList.get(i)+"")));
				int count=baseservice.getCount(String.format(CMD.IS_HIS_BASE, devtable), DB.HIS);
				if(count>0) {
					if(i==0) {
						sql="select * from %s where time > '%s'";
						sql=String.format(sql,devtable,dateList.get(i));
					}else if(i==dateList.size()-1) {
						sql="select * from %s where time < '%s'";
						sql=String.format(sql,devtable,dateList.get(i));
					}else {
						sql="select * from %s";
						sql=String.format(sql,devtable);
					}
					try {
						List<Map<String, Object>> list = baseservice.getSqlListS(sql,DB.HIS);
						if (list != null && list.size() > 0) {
							alllist.addAll(list);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
		
		Map<String, Object> backmap=allSort(alllist);
		List<Map<String, Object>> tableBody=new ArrayList<Map<String, Object>>();
		tableBody=(List) backmap.get("tableBody");
		List<Map<String, Object>> tableTitle=new ArrayList<Map<String, Object>>();
		tableTitle=(List) backmap.get("tableTitle");
		map.put("tableBody", tableBody);
		map.put("tableTitle", tableTitle);
		return JSONObject.fromObject(map).toString();
	}
	/**
	 * 获取人均面积教室办公楼占比用电量，
	 */
	@MethodInfo(METHOD="/service/rate")
	public String queryRate() throws Exception{
		Map<String,Object> mapItem=new HashMap<String,Object>();
		mapItem=schoolItem();
		String devtable=String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD());
		System.out.println(devtable);
		String sql="select * from %s";
		List<Map<String, Object>> map= baseservice.getSqlListS(String.format(sql,devtable),DB.HIS);
		Map<String, Object> backmap=typeSort(map);
		System.out.println(backmap);

		List nlist=new ArrayList<>();
		List vlist=new ArrayList<>();
		Map<String,Object> map1=new HashMap<String,Object>();
		
		
		nlist.add("教室");
		nlist.add("办公室");
		Map<String,Object> cmap=new HashMap<String,Object>();
		cmap.put("name", "教室");
		cmap.put("value", backmap.get("classroom"));
		Map<String,Object> dmap=new HashMap<String,Object>();
		dmap.put("name", "办公室");
		dmap.put("value", backmap.get("officeroom"));
		vlist.add(cmap);
		vlist.add(dmap);
		
		String a=UtilTool.cutFloat4(UtilTool.parseFloat(backmap.get("allpower")+"")/UtilTool.parseFloat(mapItem.get("acreage")+"")+"");
		String p=UtilTool.cutFloat4(UtilTool.parseFloat(backmap.get("allpower")+"")/UtilTool.parseFloat(mapItem.get("countnumber")+"")+"");
		
		map1.put("allPower", backmap.get("allpower"));
		map1.put("areaPower", a);
		map1.put("peoplePower", p);
		map1.put("name", nlist);
		map1.put("value", vlist);
		
		return JSONObject.fromObject(map1).toString();
	}
	
	/**
	 * 关空调，电灯
	 */
	@MethodInfo(METHOD="/service/switchOrder")
	public String switchOrder() throws Exception{
		String devid=getRequest().getParameter("devid");
		String stateid=getRequest().getParameter("stateid");
		String closecmd=getRequest().getParameter("closecmd");
		Service service=AppContext.getService().get(0);
		String xml="<?xml version='1.0' encoding='gb2312'?>"+
					"<root>"+
					"<Command type='Control' moid='"+devid+"' id='"+stateid+"' value='"+closecmd+"'/>"+
					"</root>";	
		
		String rquestxml = getTcpclient().sendData(xml, service);
		return null;
	}
	
	/**
	 * 校园概况信息
	 * @return
	 * @throws Exception
	 */
	@MethodInfo(METHOD="/service/queryOverview")
	public String queryOverview() throws Exception{
		List list=new ArrayList<>();
		List<ClassroomXml> classlist=new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist=new ArrayList<ClassroomXml>();
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> mapItem=new HashMap<String,Object>();
		float classair=0,dormair=0;
		
		mapItem=schoolItem();
		classlist=BaseUtil.getService(null, "classroom", null, null);
		for (ClassroomXml classroomXml : classlist) {
			Map<String, Object> classmap=airchange(classroomXml);
			if((boolean) classmap.get("air")) {
				classair++;
			}
		}
		dormlist=BaseUtil.getService(null, "officeroom", null, null);
		for (ClassroomXml classroomXml : dormlist) {
			Map<String, Object> classmap=airchange(classroomXml);
			if((boolean) classmap.get("air")) {
				dormair++;
			}
		}
		float c=(UtilTool.parseFloat(classair+"")/UtilTool.parseFloat(mapItem.get("classroomcount")+""))*100;
		float d=(UtilTool.parseFloat(dormair+"")/UtilTool.parseFloat(mapItem.get("dormcount")+""))*100;
		map.put("classair", UtilTool.parseFloat(UtilTool.cutFloat2(c+"")));  //教室运行的空调比
		map.put("dormair", UtilTool.parseFloat(UtilTool.cutFloat2(d+"")));  //办公室运行的空调比
		map.put("classroomcount", mapItem.get("classroomcount"));  //教室数
		map.put("dormcount", mapItem.get("dormcount"));  //办公室数
		map.put("countnumber", mapItem.get("countnumber")); // 总人数
		map.put("acreage", mapItem.get("acreage")); //面积数
		
		return JSONObject.fromObject(map).toString();
	}
	
	
	/**
	 * 获取楼宇的树形接口
	 */
	@MethodInfo(METHOD="/service/tree")
	public String queryTree() throws Exception{
		zTreeByOther tree=new zTreeByOther();
		zTreeByOther ctree=new zTreeByOther();
		zTreeByOther dtree=new zTreeByOther();
		zTreeByOther ptree=new zTreeByOther();
		List<zTreeByOther> backlist = new ArrayList<zTreeByOther>();
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				List<zTreeByOther> list=new ArrayList<zTreeByOther>();
				List<zTreeByOther> classlist=new ArrayList<zTreeByOther>();
				List<zTreeByOther> dormlist=new ArrayList<zTreeByOther>();
				tree=new zTreeByOther();
				ctree=new zTreeByOther();
				dtree=new zTreeByOther();
				tree.setId(groupXml.getGroupno());
				tree.setName(groupXml.getGroupname());//楼宇
				Map<String, String> map=new HashMap<String,String>();
				map.put("topname", groupXml.getGroupname());
				
				ctree.setId(groupXml.getClassroomgroup().getRoomno());
				ctree.setName(groupXml.getClassroomgroup().getName());//教室
				for (ClassroomXml classroomXml : groupXml.getClassroomgroup().getItem()) {
					ptree=new zTreeByOther();
					ptree.setId(classroomXml.getCode());
					ptree.setName(classroomXml.getClassname());
					ptree.setMap(map);
					classlist.add(ptree);
				}
				
				dtree.setId(groupXml.getOfficegroup().getRoomno());
				dtree.setName(groupXml.getOfficegroup().getName()); //办公室
				for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
					ptree=new zTreeByOther();
					ptree.setId(classroomXml.getCode());
					ptree.setName(classroomXml.getClassname());
					ptree.setMap(map);
					dormlist.add(ptree);
				}
//				if(groupXml.getOfficegroup()!=null && groupXml.getOfficegroup().getItem().size()>0) {
//					dtree.setId(groupXml.getOfficegroup());
//				}
				
				ctree.setChildren(classlist);
				dtree.setChildren(dormlist);
				list.add(ctree);
				list.add(dtree);
				tree.setChildren(list);
				backlist.add(tree);
			}
		}
		return JSONArray.fromObject(backlist).toString();
	}
	
	
	/**
	 * 获取楼信息,select选择框
	 * @return
	 * @throws Exception
	 */
	@MethodInfo(METHOD="/service/queryFloor")
	public String queryFloor() throws Exception{
		List list=new ArrayList<>();
		for(GroupXml groupXml:BaseUtil.getFloor()) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("groupno",groupXml.getGroupno());
			map.put("groupname",groupXml.getGroupname());
			list.add(map);
		}
//		for(Service service : AppContext.getService()) {
//			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
//				Map<String,Object> map=new HashMap<String,Object>();
//				map.put("groupno",groupXml.getGroupno());
//				map.put("groupname",groupXml.getGroupname());
//				list.add(map);
//			}
//		}
		return JSONArray.fromObject(list).toString();
	}

	public ClassroomXml getObj() {
		return obj;
	}

	public void setObj(ClassroomXml obj) {
		this.obj = obj;
	}
	
	
	
	
}
