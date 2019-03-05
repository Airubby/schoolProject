package com.loncom.ismac.servlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPackagePropertiesMarshaller;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.loncom.ismac.annotation.MethodInfo;
import com.loncom.ismac.annotation.Modular;
import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.bean.zTreeByOther;
import com.loncom.ismac.bean.xml.ClassroomXml;
import com.loncom.ismac.bean.xml.GroupXml;
import com.loncom.ismac.bean.xml.RootXml;
import com.loncom.ismac.bean.xml.XmlEdiParser;
import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.lservice.bean.Service;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.CMD;
import com.loncom.ismac.util.ExcelExportUtil;
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
		String sql = "",sql1 = "",tsql="",asql="";
		String devtable=null;
		List dateList=new ArrayList<>();
		List<Map<String, Object>> alllist = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> rlist=new ArrayList<Map<String, Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> rmap=new HashMap<String,Object>();
		
		List<ClassroomXml> classlist=BaseUtil.getService(null, "classroom", null, null);
		List<ClassroomXml> dormlist=BaseUtil.getService(null, "officeroom", null, null);
		String classmgrobjid="", classpointid="", officemgrobjid="",officepointid="";
		for (ClassroomXml classroomXml : classlist) {
			classmgrobjid+="'"+classroomXml.getAmmeterdev().getDevid()+"',";
			classpointid+="'"+classroomXml.getAmmeterdev().getStateid()+"',";
		}
		for (ClassroomXml classroomXml : dormlist) {
			officemgrobjid+="'"+classroomXml.getAmmeterdev().getDevid()+"',";
			officepointid+="'"+classroomXml.getAmmeterdev().getStateid()+"',";
		}	
		String mgrobjid="",pointid="";
		if(BaseUtil.isNotNull(type)) {
			//根据类型获取top10
			if(type.equals("classroom")) {
				mgrobjid=classmgrobjid;
				pointid=classpointid;
			}
			if(type.equals("officeroom")) {
				mgrobjid=officemgrobjid;
				pointid=officepointid;
			}
		}else {
			mgrobjid=classmgrobjid+officemgrobjid;
			pointid=classpointid+officepointid;
		}
		mgrobjid=mgrobjid.substring(0, mgrobjid.length()-1);
		pointid=pointid.substring(0, pointid.length()-1);
		
		dateList=UtilTime.getDatesBetweenTwoDate(startTime, endTime);
		System.out.println(dateList);
		String[] first=((String) dateList.get(0)).split("\\s");
		String[] last=((String) dateList.get(dateList.size()-1)).split("\\s");
		if(first[0].equals(last[0])) {
			devtable=String.format(CMD.HIS_DEVTABLE, UtilTime.getStyingYMD(dateList.get(0)+""));
			int count=baseservice.getCount(String.format(CMD.IS_HIS_BASE, devtable), DB.HIS);
			if(count>0) {
				tsql="select * from %s where time > '%s' and time < '%s' and mgrobjid in(%s) and pointid in(%s)";
				tsql=String.format(tsql,devtable,dateList.get(0),dateList.get(1),mgrobjid,pointid);
			}
		}else {
			String table=String.format(CMD.HIS_DEVTABLE, UtilTime.getStyingYMD(dateList.get(0)+""));
			int firstcount=baseservice.getCount(String.format(CMD.IS_HIS_BASE, table), DB.HIS);
			for(int i=0;i<dateList.size();i++) {
				devtable=String.format(CMD.HIS_DEVTABLE, UtilTime.getStyingYMD(dateList.get(i)+""));
				int count=baseservice.getCount(String.format(CMD.IS_HIS_BASE, devtable), DB.HIS);
				if(count>0) {
					if(i==0&&firstcount>0) {
						sql="select * from %s where time > '%s' and mgrobjid in(%s) and pointid in(%s) ";
						sql=String.format(sql,devtable,dateList.get(i),mgrobjid,pointid);
						tsql+=sql;
					}else if(i==dateList.size()-1) {
						sql="union select * from %s where time < '%s' and mgrobjid in(%s) and pointid in(%s)";
						sql=String.format(sql,devtable,dateList.get(i),mgrobjid,pointid);
						tsql+=sql;
					}else {
						if(sql.length()>0) {
							sql="union select * from %s where mgrobjid in(%s) and pointid in(%s) ";
						}else {//选择的时间段开始时间是没有表的情况下
							sql="select * from %s where mgrobjid in(%s) and pointid in(%s) ";
						}
						sql=String.format(sql,devtable,mgrobjid,pointid);
						tsql+=sql;
					}
				}
			}
		}
		try {
			asql="select mgrobjid,pointid,cast(sum(a.value) as decimal(10,2)) as value from("+tsql+") a group by mgrobjid,pointid order by value desc limit 10";
			sql1="select cast(sum(a.value) as decimal(10,2)) as value from("+tsql+") a";
			alllist = baseservice.getSqlListS(asql,DB.HIS);
			list = baseservice.getSqlListS(sql1,DB.HIS);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(alllist.size()>0) {
			for(int i=0;i<alllist.size();i++) {
				for (ClassroomXml classroomXml : classlist) {
					if(classroomXml.getAmmeterdev().getDevid().equals(alllist.get(i).get("MGROBJID"))
							&&classroomXml.getAmmeterdev().getStateid().equals(alllist.get(i).get("POINTID"))) {
						rmap=new HashMap<String,Object>();
						rmap.put("classname",classroomXml.getClassname());
						rmap.put("value",alllist.get(i).get("VALUE"));
						float rate=(Float.parseFloat(alllist.get(i).get("VALUE")+"")/Float.parseFloat(list.get(0).get("VALUE")+""))*100;
						rmap.put("rate",UtilTool.cutFloat2(rate+"")) ;
						rlist.add(rmap);
					}
				}
				for (ClassroomXml classroomXml : dormlist) {
					if(classroomXml.getAmmeterdev().getDevid().equals(alllist.get(i).get("MGROBJID"))
							&&classroomXml.getAmmeterdev().getStateid().equals(alllist.get(i).get("POINTID"))) {
						rmap=new HashMap<String,Object>();
						rmap.put("classname",classroomXml.getClassname());
						rmap.put("value",alllist.get(i).get("VALUE"));
						float rate=(Float.parseFloat(alllist.get(i).get("VALUE")+"")/Float.parseFloat(list.get(0).get("VALUE")+""))*100;
						rmap.put("rate",UtilTool.cutFloat2(rate+"")) ;
						rlist.add(rmap);
					}
				}	
			}
		}
		map.put("data", rlist);
		map.put("all",list.size()>0?list.get(0).get("VALUE"):0);
		return JSONObject.fromObject(map).toString();
	}

	/**
	 * 获取table的title展示
	 */
	@MethodInfo(METHOD="/service/tableTitle")
	public String queryTitle() throws Exception{
		List<Map<String, Object>> tableTitle=new ArrayList<Map<String, Object>>();
		Map<String, Object> mapTitle=new HashMap<String,Object>();
		int num=0;
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				mapTitle=new HashMap<String,Object>();
				num+=1;
				if(num==1) {
					mapTitle.put("prop","ONEVALUE");
				}else {
					mapTitle.put("prop","TWOVALUE");
				}
				mapTitle.put("label", groupXml.getGroupname());
				mapTitle.put("minWidth", 10);
				tableTitle.add(mapTitle);
			}
		}
		return JSONArray.fromObject(tableTitle).toString();
	}
	
	/**
	 * 获取用电数据，按时间查询table信息,line线型图
	 */
	@MethodInfo(METHOD="/service/tableInfo")
	public String queryTable() throws Exception{
		String startTime=getRequest().getParameter("startTime");
		String endTime=getRequest().getParameter("endTime");
		List<ClassroomXml> classlist=new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist=new ArrayList<ClassroomXml>();
		String sql="",rpttable="",allsql = "",tsql="";
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				classlist=BaseUtil.getService(groupXml.getGroupno(), "classroom", null, null);
				dormlist=BaseUtil.getService(groupXml.getGroupno(), "officeroom", null, null);
				for(ClassroomXml classroomXml : classlist) {
					rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
					sql=" select * from %s where time >= '%s' and time < '%s' UNION";
					sql=String.format(sql,rpttable,startTime,endTime);
					allsql+=sql;
				}
				for (ClassroomXml classroomXml : dormlist) {
					rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
					sql=" select * from %s where time >= '%s' and time < '%s' UNION";
					sql=String.format(sql,rpttable,startTime,endTime);
					allsql+=sql;
				}
			}
		}
		allsql=allsql.substring(0, allsql.length()-5);
		tsql="select cast(sum(a.onevalue) as decimal(10,2)) as onevalue,cast(sum(a.twovalue) as decimal(10,2)) as twovalue,cast(sum(a.allvalue) as decimal(10,2)) as allvalue, time "
				+ "from ("+allsql+") a group by a.time";
		
		List<Map<String, Object>> list = baseservice.getSqlListS(tsql,DB.HIS);
		System.out.println(list);
		return JSONArray.fromObject(list).toString();
	}
	
	/**
	 * 获取人均面积教室办公楼占比用电量，
	 */
	@MethodInfo(METHOD="/service/rate")
	public String queryRate() throws Exception{
		List<Map<String, Object>> clist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> olist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> alist=new ArrayList<Map<String, Object>>();
		Map<String,Object> mapItem=new HashMap<String,Object>();
		Map<String,Object> map=new HashMap<String,Object>();
		mapItem=schoolItem();
		
		List<ClassroomXml> classlist=BaseUtil.getService(null, "classroom", null, null);
		List<ClassroomXml> dormlist=BaseUtil.getService(null, "officeroom", null, null);
		String classmgrobjid="", classpointid="", officemgrobjid="",officepointid="",mgrobjid="",pointid="";
		for (ClassroomXml classroomXml : classlist) {
			classmgrobjid+="'"+classroomXml.getAmmeterdev().getDevid()+"',";
			classpointid+="'"+classroomXml.getAmmeterdev().getStateid()+"',";
		}
		for (ClassroomXml classroomXml : dormlist) {
			officemgrobjid+="'"+classroomXml.getAmmeterdev().getDevid()+"',";
			officepointid+="'"+classroomXml.getAmmeterdev().getStateid()+"',";
		}	
		classmgrobjid=classmgrobjid.substring(0, classmgrobjid.length()-1);
		classpointid=classpointid.substring(0, classpointid.length()-1);
		officemgrobjid=officemgrobjid.substring(0, officemgrobjid.length()-1);
		officepointid=officepointid.substring(0, officepointid.length()-1);
		mgrobjid=classmgrobjid+","+officemgrobjid;
		pointid=classpointid+","+officepointid;
		
		String sql="",csql="",osql="",asql="";
		String devtable=String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD());
		int count=baseservice.getCount(String.format(CMD.IS_HIS_BASE, devtable), DB.HIS);
		if(count>0) {
			sql="select * from %s where mgrobjid in(%s) and pointid in(%s)";
			csql=String.format(sql,devtable,classmgrobjid,classpointid);
			osql=String.format(sql,devtable,officemgrobjid,officepointid);
			asql=String.format(sql,devtable,mgrobjid,pointid);
			csql="select cast(sum(a.value) as decimal(10,2)) as value from("+csql+") a";
			osql="select cast(sum(a.value) as decimal(10,2)) as value from("+osql+") a";
			asql="select cast(sum(a.value) as decimal(10,2)) as value from("+asql+") a";
			
		}
		try {
			clist = baseservice.getSqlListS(csql,DB.HIS);
			olist = baseservice.getSqlListS(osql,DB.HIS);
			alist = baseservice.getSqlListS(asql,DB.HIS);
		} catch (Exception e) {
			// TODO: handle exception
		}
		List nlist=new ArrayList<>();
		List vlist=new ArrayList<>();
		nlist.add("教室");
		nlist.add("办公室");
		Map<String,Object> cmap=new HashMap<String,Object>();
		cmap.put("name", "教室");
		cmap.put("value",clist.size()>0?clist.get(0).get("VALUE"):0);
		Map<String,Object> dmap=new HashMap<String,Object>();
		dmap.put("name", "办公室");
		dmap.put("value", olist.size()>0?olist.get(0).get("VALUE"):0);
		vlist.add(cmap);
		vlist.add(dmap);
		System.out.println(alist.get(0).get("VALUE"));
		String a=UtilTool.cutFloat4(UtilTool.parseFloat(alist.get(0).get("VALUE")+"")/UtilTool.parseFloat(mapItem.get("acreage")+"")+"");
		String p=UtilTool.cutFloat4(UtilTool.parseFloat(alist.get(0).get("VALUE")+"")/UtilTool.parseFloat(mapItem.get("countnumber")+"")+"");
		
		map.put("allPower", alist.size()>0?alist.get(0).get("VALUE"):0);
		map.put("areaPower", a);
		map.put("peoplePower", p);
		map.put("name", nlist);
		map.put("value", vlist);
		
		return JSONObject.fromObject(map).toString();
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

	/**
	 * 导出数据
	 * @return
	 */
	@MethodInfo(METHOD="/service/export")
	public SXSSFWorkbook exportPower() throws Exception{
		List<Class> l=new ArrayList<Class>();
   		l.add(SXSSFWorkbook.class);
		l.add(ZipPackagePropertiesMarshaller.class);
		l.add(OPCPackage.class);
		l.add(javax.xml.stream.FactoryConfigurationError.class);
		l.add(javax.xml.parsers.FactoryConfigurationError.class);
   	  
   		SXSSFWorkbook wb = new SXSSFWorkbook(100);
		String begintime = getRequest().getParameter("startTime");
		String endtime = getRequest().getParameter("endTime");
		ExcelExportUtil.excelPower(wb, begintime, endtime);
		return wb;
	}
	
	public ClassroomXml getObj() {
		return obj;
	}

	public void setObj(ClassroomXml obj) {
		this.obj = obj;
	}
	
	
	
	
}
