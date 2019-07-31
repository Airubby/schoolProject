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
		map.put("serverid", classroomXml.getServerid());
		map.put("classname", classroomXml.getClassname());
		map.put("model", classroomXml.getModel());
		map.put("times", classroomXml.getTimes());
		map.put("timegroup", classroomXml.getTimegroup());
		map.put("serviceid", classroomXml.getServerid());
		//空调
		String lamp=null,air = null;
		for(int i=0;i<classroomXml.getBaseItem().size();i++) {
			if(BaseUtil.isNotNull(classroomXml.getBaseItem().get(i).getPointid())) {
				String dev=classroomXml.getBaseItem().get(i).getPointid();
				String devid = "",pointid="";
				if(dev.indexOf("_")!=-1||dev.indexOf(";")!=-1) {
					String [] pointidarr=dev.split("_").length>1?dev.split("_"):dev.split(";");
					for(int k=0;k<pointidarr.length;k++) {
						String did=pointidarr[k].split(",")[0];
						String pid=pointidarr[k].split(",")[1];
						if(k==0) {
							devid+=did;
							pointid+=pid;
						}else {
							devid+=","+did;
							pointid+=","+pid;
						}
					}
				}else {
					devid=dev.split(",")[0];
					pointid=dev.split(",")[1];
				}
				if(classroomXml.getBaseItem().get(i).getDev().equals("airstate")) { //只用第一个判断
					air=BaseUtil.getDevvouValue(devid.split(",")[0],pointid.split(",")[0]);
					map.put("airDevid", devid.split(",")[0]);
					map.put("airStateid", pointid.split(",")[0]);  
					map.put("airClosecmd", "airClosecmd");
					map.put("air",air.equals("0")?false:true);
					map.put("airdev", air);
					map.put("airstate_key", devid.split(",")[0]+"_"+pointid.split(",")[0]);
				}else if(classroomXml.getBaseItem().get(i).getDev().equals("temp")) {//humituredev，环境温度，多个控制显示值取一个
					map.put("humituredev", BaseUtil.getDevvouValue(devid.split(",")[0],pointid.split(",")[0]));
					map.put("temp_key", devid.split(",")[0]+"_"+pointid.split(",")[0]);
				}else if(classroomXml.getBaseItem().get(i).getDev().equals("linestate")) {//红外，有无人
					map.put("infrareddev", BaseUtil.getDevvouValue(devid,pointid));
					map.put("linestate_key", devid+"_"+pointid);
				}else if(classroomXml.getBaseItem().get(i).getDev().equals("lampstate")) {//照明
					lamp=BaseUtil.getDevvouValue(devid,pointid);
					map.put("lampDevid", devid);
					map.put("lampStateid", pointid);
					map.put("lampClosecmd", "lampClosecmd");
					map.put("lamp", lamp.equals("0")?false:true);
					map.put("lampdev", lamp);
					map.put("lampstate_key", devid+"_"+pointid);
				}else if(classroomXml.getBaseItem().get(i).getDev().equals("aircontrol")) {  // 关空调指令
					map.put("aircontrol_devid", devid);
					map.put("aircontrol_pointid", pointid);
				}else if(classroomXml.getBaseItem().get(i).getDev().equals("aircontrolopen")) { //开空调
					map.put("aircontrolopen_devid", devid);
					map.put("aircontrolopen_pointid", pointid);
				}else if(classroomXml.getBaseItem().get(i).getDev().equals("powercontrol")) { //关灯
					map.put("powercontrol_devid", devid);
					map.put("powercontrol_pointid", pointid);
				}else if(classroomXml.getBaseItem().get(i).getDev().equals("powercontrolopen")) { //开灯
					map.put("powercontrolopen_devid", devid);
					map.put("powercontrolopen_pointid", pointid);
				}else if(classroomXml.getBaseItem().get(i).getDev().equals("roompower")) {//实时功耗
//					map.put("ammeterdev", UtilTool.cutFloat2(BaseUtil.getDevvouValue(devid,pointid)));
					map.put("roompower_key", devid+"_"+pointid);
					if(devid.split(",").length>1) {
						String r="0";
						String[] arr=devid.split(",");
						for(int j=0;j<arr.length;j++) {
							map.put("roompower_key"+j, arr[j]+"_"+pointid.split(",")[j]);
							map.put("roompower"+j, BaseUtil.getDevvouValue(arr[j],pointid.split(",")[j]));
							r=UtilTool.parseFloat(r)+UtilTool.parseFloat(BaseUtil.getDevvouValue(arr[j],pointid.split(",")[j])+"")+"";
						}
						map.put("roompower_num", arr.length);
						map.put("roompower", r.equals("0.0")||r.equals("0.00")||r.equals("0")?"":UtilTool.cutFloat2(r));
					}else {
						map.put("roompower_num", 1);
						map.put("roompower", UtilTool.cutFloat2(BaseUtil.getDevvouValue(devid,pointid)));
					}
				}else if(classroomXml.getBaseItem().get(i).getDev().equals("powerdegree")) {  //总用电量
					map.put("powerdegree_key", devid+"_"+pointid);
					if(devid.split(",").length>1) {
						String r="0";
						String[] arr=devid.split(",");
						for(int j=0;j<arr.length;j++) {
							map.put("powerdegree_key"+j, arr[j]+"_"+pointid.split(",")[j]);
							map.put("powerdegree"+j, BaseUtil.getDevvouValue(arr[j],pointid.split(",")[j]));
							r=UtilTool.parseFloat(r)+UtilTool.parseFloat(BaseUtil.getDevvouValue(arr[j],pointid.split(",")[j])+"")+"";
						}
						map.put("powerdegree_num", arr.length);
						map.put("powerdegree", r.equals("0.0")||r.equals("0.00")||r.equals("0")?"":UtilTool.cutFloat2(r));
						
					}else {
						map.put("powerdegree_num", 1);
						map.put("powerdegree", UtilTool.cutFloat2(BaseUtil.getDevvouValue(devid,pointid)));
					}
					
				}
			}
		}
		//模式 0手动 1自动  手动且开着的可以编辑，其它不能编辑 ；true禁止编辑，false不禁止编辑 &&!air.equals("0")  开空调前端不下发
		map.put("airtype", classroomXml.getModel().equals("0")?false:true); 
		map.put("lamptype", classroomXml.getModel().equals("0")?false:true); 
		return map;
		
	}
	public static Map<String, Object> detailchange(ClassroomXml classroomXml){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("code", classroomXml.getCode());
		map.put("model", classroomXml.getModel());
		map.put("times", classroomXml.getTimes());
		map.put("timegroup", classroomXml.getTimegroup());
		map.put("openair", classroomXml.getOpenair());
		map.put("closeair", classroomXml.getCloseair());
		map.put("serviceid", classroomXml.getServerid());
		return map;
	}
	public static Map<String, Object> airchange(ClassroomXml classroomXml){
		Map<String,Object> map=new HashMap<String,Object>();
		for(int i=0;i<classroomXml.getBaseItem().size();i++) {
			if(classroomXml.getBaseItem().get(i).getDev().equals("airstate")&&BaseUtil.isNotNull(classroomXml.getBaseItem().get(i).getPointid())) {
				String air=BaseUtil.getDevvouValue(classroomXml.getBaseItem().get(i).getPointid().split(",")[0],classroomXml.getBaseItem().get(i).getPointid().split(",")[1]);
				map.put("air",air.equals("0")?false:true);
				map.put("serviceid",classroomXml.getServerid());
			}
		}
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
		for (GroupXml groupXml : AppContext.getService().get(0).getGroupcontrol().getGroup()) {
			acreage+=UtilTool.parseFloat(groupXml.getAcreage());
			classroomcount+=UtilTool.parseInt(groupXml.getClassroomcount());
			dormcount+=UtilTool.parseInt(groupXml.getDormcount());
			countnumber+=UtilTool.parseInt(groupXml.getCountnumber());
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
		String floor=getRequest().getParameter("floor");
		String classno=getRequest().getParameter("classno");
		List<ClassroomXml> list=new ArrayList<ClassroomXml>();
		list=BaseUtil.getService(groupno, classno,floor, classname, null);
		List list1=new ArrayList<>();
		String str=getRoleAddrList();
		for (ClassroomXml classroomXml : list) {
			if(classroomXml.getBaseItem()!=null) {
				if(str.equals("0")) { //0  admin获取所有
					list1.add(querychange(classroomXml));
				}else if(!str.equals("-1")){
					List<String> arr=Arrays.asList(str.split(","));
					if(arr.contains(classroomXml.getCode())) {
						list1.add(querychange(classroomXml));
					}
				}
			}
			
		}
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
		list=BaseUtil.getService(null, null,null, null, code);
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
		String openair=getRequest().getParameter("openair");
		String closeair=getRequest().getParameter("closeair");
		String serviceid=getRequest().getParameter("serviceid");
		
		List<ClassroomXml> list=new ArrayList<ClassroomXml>();
		list=BaseUtil.getService(null,null, null, null, code);
		list.get(0).setModel(model);
		list.get(0).setTimes(times);
		list.get(0).setTimegroup(timegroup);
		list.get(0).setOpenair(openair);
		list.get(0).setCloseair(closeair);
		
		Service service=BaseUtil.getService(serviceid);
		
		
		String sysxml="<?xml version='1.0' encoding='gb2312'?>"+
				"<root>"+
				"<Command type='Modify'>"+
				"<Field name='code' value='"+code+"'/>"+
				"<Field name='model' value='"+model+"'/>"+
				"<Field name='times' value='"+times+"'/>"+
				"<Field name='timegroup' value='"+timegroup+"'/>"+
				"<Field name='openair' value='"+openair+"'/>"+
				"<Field name='closeair' value='"+closeair+"'/>"+
				"</Command>"+
				"</root>";	
		String rquestxml = getTcpclient().sendData(sysxml, service);
		System.out.println(rquestxml);
		if(rquestxml.indexOf("data=\"true\"")!=-1) {
			String xml = getRequest().getSession().getServletContext().getRealPath("/xml/" + service.getSysxml());
			String url = FileUtil.readToString(xml);
			RootXml root = XmlEdiParser.parseRootData(url);
			
			root.setGroupcontrol(service.getGroupcontrol());
			BaseUtil.ObjToXMl(root,xml);
		}else {
			throw new RuntimeException("修改失败!");
		}
		return null;
	}
	
	/**
	 * 首页获取实时top
	 */
	@MethodInfo(METHOD="/service/indextop")
	public String queryIndexTop() throws Exception{
		List<Map<String, Object>> dataList=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> rList=new ArrayList<Map<String, Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		String value="";
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				List<ClassroomXml> list=groupXml.getClassroomgroup().getItem();
				if(list!=null)
				for(int i=0;i<list.size();i++) {
					for(int j=0;j<list.get(i).getBaseItem().size();j++) {
						if(list.get(i).getBaseItem().get(j).getDev().equals("roompower")&&BaseUtil.isNotNull(list.get(i).getBaseItem().get(j).getPointid())) {
							map=new HashMap<String,Object>();
							String dev=list.get(i).getBaseItem().get(j).getPointid();
							String devid = "",pointid="";
							if(dev.indexOf("_")!=-1) {
								String[] arr=dev.split("_");
								for(int k=0;k<arr.length;k++) {
									value=UtilTool.parseFloat(value)+UtilTool.parseFloat(BaseUtil.getDevvouValue(arr[k].split(",")[0],arr[k].split(",")[1])+"")+"";
								}
							}else {
								value=BaseUtil.getDevvouValue(dev.split(",")[0],dev.split(",")[1]);
							}
							map.put("classname", list.get(i).getClassname());
							map.put("value", value);
							dataList.add(map);
						}
					}
					
				}
			}
		}
		rList=BaseUtil.downSort(dataList, "value");
		return JSONArray.fromObject(rList).toString();
	}
	
	

	/**
	 * 获取table的title展示
	 */
	@MethodInfo(METHOD="/service/tableTitle")
	public String queryTitle() throws Exception{
		List<Map<String, Object>> tableTitle=new ArrayList<Map<String, Object>>();
		Map<String, Object> mapTitle=new HashMap<String,Object>();
		int num=0;
		for(int i=0;i<AppContext.getService().get(0).getGroupcontrol().getGroup().size();i++) {
			mapTitle=new HashMap<String,Object>();
			mapTitle.put("prop","VALUE"+i);
			mapTitle.put("label", AppContext.getService().get(0).getGroupcontrol().getGroup().get(i).getGroupname());
			mapTitle.put("minWidth", 10);
			tableTitle.add(mapTitle);
		}
		return JSONArray.fromObject(tableTitle).toString();
	}
	
	/**
	 * 获取用电数据，按时间查询table信息,line线型图,top信息
	 */
	@MethodInfo(METHOD="/service/tableInfo")
	public String queryTable() throws Exception{
		String startTime=getRequest().getParameter("startTime");
		String endTime=getRequest().getParameter("endTime");
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> rmap=new HashMap<String,Object>();
		List<Map<String, Object>> rtoplist=new ArrayList<Map<String, Object>>();
		
		List<ClassroomXml> classlist=new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist=new ArrayList<ClassroomXml>();
		String sql="",rpttable="",allsql = "",tablesql="",topsql="",countsql="";
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				if(groupXml.getClassroomgroup().getItem()!=null) {
					for (ClassroomXml classroomXml: groupXml.getClassroomgroup().getItem()) {
						if(!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							sql=" select * from %s where time >= '%s' and time < '%s' UNION";
							sql=String.format(sql,rpttable,startTime,endTime);
							allsql+=sql;
						}
						classlist.add(classroomXml);
					}
				}
				if(groupXml.getOfficegroup().getItem()!=null) {
					for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
						if(!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							sql=" select * from %s where time >= '%s' and time < '%s' UNION";
							sql=String.format(sql,rpttable,startTime,endTime);
							allsql+=sql;
						}
						dormlist.add(classroomXml);
					}
				}
			}
		}
		
		List<Map<String, Object>> tablelist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> toplist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> countlist=new ArrayList<Map<String, Object>>();
		
		int num=BaseUtil.floorNum();
		String numsql="";
		for(int i=0;i<num;i++) {
			numsql+="cast(sum(a.value"+i+") as decimal(10,2)) as value"+i+",";
		}
		
		if(allsql!="") {
			allsql=allsql.substring(0, allsql.length()-5);
			tablesql="select "+numsql+"cast(sum(a.allvalue) as decimal(10,2)) as allvalue, time "
					+ "from ("+allsql+") a group by a.time";
			topsql="select mgrobjid,pointid,cast(sum(a.allvalue) as decimal(10,2)) as allvalue, time "
					+ "from ("+allsql+") a group by a.mgrobjid,a.pointid ORDER BY allvalue DESC LIMIT 10";
			countsql="select cast(sum(a.allvalue) as decimal(10,2)) as allvalue "
					+ "from ("+allsql+") a";
			tablelist = baseservice.getSqlListS(tablesql,DB.HIS);
			toplist = baseservice.getSqlListS(topsql,DB.HIS);
			countlist = baseservice.getSqlListS(countsql,DB.HIS);
		}
		
		if(toplist.size()>0) {
			for(int i=0;i<toplist.size();i++) {
				for (ClassroomXml classroomXml : classlist) {
					if(classroomXml.getBaseItem()!=null&&!BaseUtil.isNotNull(classroomXml.getElement()))
					for(int j=0;j<classroomXml.getBaseItem().size();j++) {
						if(classroomXml.getBaseItem().get(j).getDev().equals("powerdegree")&&BaseUtil.isNotNull(classroomXml.getBaseItem().get(j).getPointid())) {
							String dev=classroomXml.getBaseItem().get(j).getPointid();
							String mid = "",pid="";
							if(dev.indexOf("_")!=-1) {
								String [] pointidarr=dev.split("_");
								for(int k=0;k<pointidarr.length;k++) {
									String devid=pointidarr[k].split(",")[0];
									String pointid=pointidarr[k].split(",")[1];
									if(k==0) {
										mid+=devid;
										pid+=pointid;
									}else {
										mid+="_"+devid;
										pid+="_"+pointid;
									}
								}
							}else {
								mid=dev.split(",")[0];
								pid=dev.split(",")[1];
							}
							if(mid.equals(toplist.get(i).get("MGROBJID"))&&pid.equals(toplist.get(i).get("POINTID"))) {
								rmap=new HashMap<String,Object>();
								rmap.put("classname",classroomXml.getClassname());
								rmap.put("value",toplist.get(i).get("ALLVALUE"));
								float rate=(Float.parseFloat(toplist.get(i).get("ALLVALUE")+"")/Float.parseFloat(countlist.get(0).get("ALLVALUE")+""))*100;
								rmap.put("rate",UtilTool.cutFloat2(rate+"")) ;
								rtoplist.add(rmap);
							}
						}
					}
				}
				for (ClassroomXml classroomXml : dormlist) {
					if(classroomXml.getBaseItem()!=null&&!BaseUtil.isNotNull(classroomXml.getElement()))
					for(int j=0;j<classroomXml.getBaseItem().size();j++) {
						if(classroomXml.getBaseItem().get(j).getDev().equals("powerdegree")&&BaseUtil.isNotNull(classroomXml.getBaseItem().get(j).getPointid())) {
							String dev=classroomXml.getBaseItem().get(j).getPointid();
							String mid = "",pid="";
							if(dev.indexOf("_")!=-1) {
								String [] pointidarr=dev.split("_");
								for(int k=0;k<pointidarr.length;k++) {
									String devid=pointidarr[k].split(",")[0];
									String pointid=pointidarr[k].split(",")[1];
									if(k==0) {
										mid+=devid;
										pid+=pointid;
									}else {
										mid+="_"+devid;
										pid+="_"+pointid;
									}
								}
							}else {
								mid=dev.split(",")[0];
								pid=dev.split(",")[1];
							}
							if(mid.equals(toplist.get(i).get("MGROBJID"))&&pid.equals(toplist.get(i).get("POINTID"))) {
								rmap=new HashMap<String,Object>();
								rmap.put("classname",classroomXml.getClassname());
								rmap.put("value",toplist.get(i).get("ALLVALUE"));
								float rate=(Float.parseFloat(toplist.get(i).get("ALLVALUE")+"")/Float.parseFloat(countlist.get(0).get("ALLVALUE")+""))*100;
								rmap.put("rate",UtilTool.cutFloat2(rate+"")) ;
								rtoplist.add(rmap);
							}
						}
					}
				}	
			}
		}
		
		map.put("count", countlist.get(0).get("ALLVALUE"));
		map.put("table", tablelist);
		map.put("top", rtoplist);
		
		return JSONObject.fromObject(map).toString();
	}
	
	/**
	 * 获取用电数据，top信息
	 */
	@MethodInfo(METHOD="/service/topInfo")
	public String queryTop() throws Exception{
		String startTime=getRequest().getParameter("startTime");
		String endTime=getRequest().getParameter("endTime");
		Map<String, Object> map=new HashMap<String,Object>();
		
		List<Map<String, Object>> rclasstopylist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> rclasstopnlist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> rofficetopylist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> rofficetopnlist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> rtoplist=new ArrayList<Map<String, Object>>();
		
		
		List<ClassroomXml> classlist=new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist=new ArrayList<ClassroomXml>();
		List<ClassroomXml> alllist=new ArrayList<ClassroomXml>();
		String sql="",rpttable="";
		//classtopysql:能耗；classtopnsql：节能
		String classsql="",officesql="",classtopysql="",classtopnsql="",officetopysql="",officetopnsql="",allsql="",alltopsql="";
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				if(groupXml.getClassroomgroup().getItem()!=null) {
					for (ClassroomXml classroomXml: groupXml.getClassroomgroup().getItem()) {
						if(!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							sql=" select * from %s where time >= '%s' and time < '%s' UNION";
							sql=String.format(sql,rpttable,startTime,endTime);
							classsql+=sql;
							allsql+=sql;
						}
						classlist.add(classroomXml);
						alllist.add(classroomXml);
					}
				}
				if(groupXml.getOfficegroup().getItem()!=null) {
					for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
						if(!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							sql=" select * from %s where time >= '%s' and time < '%s' UNION";
							sql=String.format(sql,rpttable,startTime,endTime);
							officesql+=sql;
							allsql+=sql;
						}
						dormlist.add(classroomXml);
						alllist.add(classroomXml);
					}
				}
			}
		}
		List<Map<String, Object>> classtopylist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> classtopnlist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> officetopylist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> officetopnlist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> alltoplist=new ArrayList<Map<String, Object>>();
		
		if(classsql!="") {
			classsql=classsql.substring(0, classsql.length()-5);
			classtopysql="select mgrobjid,pointid,cast(sum(a.allvalue) as decimal(10,2)) as allvalue, time "
					+ "from ("+classsql+") a group by a.mgrobjid,a.pointid ORDER BY allvalue DESC LIMIT 10";
			classtopnsql="select mgrobjid,pointid,cast(sum(a.allvalue) as decimal(10,2)) as allvalue, time "
					+ "from ("+classsql+") a group by a.mgrobjid,a.pointid ORDER BY allvalue ASC LIMIT 10";
			classtopylist=baseservice.getSqlListS(classtopysql,DB.HIS);
			classtopnlist=baseservice.getSqlListS(classtopnsql,DB.HIS);
		}
		if(officesql!="") {
			officesql=officesql.substring(0, officesql.length()-5);
			officetopysql="select mgrobjid,pointid,cast(sum(a.allvalue) as decimal(10,2)) as allvalue, time "
					+ "from ("+officesql+") a group by a.mgrobjid,a.pointid ORDER BY allvalue DESC LIMIT 10";
			officetopnsql="select mgrobjid,pointid,cast(sum(a.allvalue) as decimal(10,2)) as allvalue, time "
					+ "from ("+officesql+") a group by a.mgrobjid,a.pointid ORDER BY allvalue ASC LIMIT 10";
			officetopylist=baseservice.getSqlListS(officetopysql,DB.HIS);
			officetopnlist=baseservice.getSqlListS(officetopnsql,DB.HIS);
		}
		if(allsql!="") {
			allsql=allsql.substring(0, allsql.length()-5);
			alltopsql="select mgrobjid,pointid,cast(sum(a.allvalue) as decimal(10,2)) as allvalue, time "
					+ "from ("+allsql+") a group by a.mgrobjid,a.pointid ORDER BY allvalue DESC";
			alltoplist=baseservice.getSqlListS(alltopsql,DB.HIS);
		}
		
		if(classtopylist.size()>0) {
			rclasstopylist=topHandle(classlist,classtopylist);
		}
		if(classtopnlist.size()>0) {
			rclasstopnlist=topHandle(classlist,classtopnlist);
		}
		if(officetopylist.size()>0) {
			rofficetopylist=topHandle(dormlist,officetopylist);
		}
		if(classtopylist.size()>0) {
			rofficetopnlist=topHandle(dormlist,officetopnlist);
		}
		if(alltoplist.size()>0) {
			rtoplist=topHandle(alllist,alltoplist);
		}
		map.put("classtopy", rclasstopylist);
		map.put("classtopn", rclasstopnlist);
		map.put("officetopy", rofficetopylist);
		map.put("officetopn", rofficetopnlist);
		map.put("alltop", rtoplist);
		
		return JSONObject.fromObject(map).toString();
	}
	/**
	 * 处理top
	 * @param classlist
	 * @param toplist
	 * @return
	 */
	public static List<Map<String, Object>> topHandle(List<ClassroomXml> classlist,  List<Map<String, Object>> toplist) {
		Map<String, Object> rmap=new HashMap<String,Object>();
		List<Map<String, Object>> rtoplist=new ArrayList<Map<String, Object>>();
		for(int i=0;i<toplist.size();i++) {
			for (ClassroomXml classroomXml : classlist) {
				if(classroomXml.getBaseItem()!=null&&!BaseUtil.isNotNull(classroomXml.getElement()))
				for(int j=0;j<classroomXml.getBaseItem().size();j++) {
					if(classroomXml.getBaseItem().get(j).getDev().equals("powerdegree")&&BaseUtil.isNotNull(classroomXml.getBaseItem().get(j).getPointid())) {
						String dev=classroomXml.getBaseItem().get(j).getPointid();
						String mid = "",pid="";
						if(dev.indexOf("_")!=-1) {
							String [] pointidarr=dev.split("_");
							for(int k=0;k<pointidarr.length;k++) {
								String devid=pointidarr[k].split(",")[0];
								String pointid=pointidarr[k].split(",")[1];
								if(k==0) {
									mid+=devid;
									pid+=pointid;
								}else {
									mid+="_"+devid;
									pid+="_"+pointid;
								}
							}
						}else {
							mid=dev.split(",")[0];
							pid=dev.split(",")[1];
						}
						if(mid.equals(toplist.get(i).get("MGROBJID"))&&pid.equals(toplist.get(i).get("POINTID"))) {
							rmap=new HashMap<String,Object>();
							rmap.put("classname",classroomXml.getClassname());
							rmap.put("value",toplist.get(i).get("ALLVALUE"));
							rtoplist.add(rmap);
						}
					}
				}
			}
		}
		return rtoplist;
	}
	
	/**
	 * 获取用电数据，获取多天的教室总能耗，办公室总能耗按天求和展示
	 */
	@MethodInfo(METHOD="/service/moreDayInfo")
	public String queryMoreDay() throws Exception{
		String startTime=getRequest().getParameter("startTime");
		String endTime=getRequest().getParameter("endTime");
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> rmap=new HashMap<String,Object>();
		List<Map<String, Object>> rtoplist=new ArrayList<Map<String, Object>>();
		
		List<ClassroomXml> classlist=new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist=new ArrayList<ClassroomXml>();
		String sql="",rpttable="",allsql = "",tablesql="",topsql="",countsql="";
		String classsql="",officesql="",tclasssql="",tofficesql="";
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				if(groupXml.getClassroomgroup().getItem()!=null) {
					for (ClassroomXml classroomXml: groupXml.getClassroomgroup().getItem()) {
						if(!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							sql=" select * from %s where time >= '%s' and time < '%s' UNION";
							sql=String.format(sql,rpttable,startTime,endTime);
							classsql+=sql;
						}
					}
				}
				if(groupXml.getOfficegroup().getItem()!=null) {
					for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
						if(!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							sql=" select * from %s where time >= '%s' and time < '%s' UNION";
							sql=String.format(sql,rpttable,startTime,endTime);
							officesql+=sql;
						}
					}
				}
			
			}
		}
		List<Map<String, Object>> tclasslist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> tofficelist=new ArrayList<Map<String, Object>>();
		int num=BaseUtil.floorNum();
		String numsql="";
		for(int i=0;i<num;i++) {
			numsql+="cast(sum(value"+i+") as decimal(10,2)) as value"+i+",";
		}
		
		if(classsql!="") {
			classsql=classsql.substring(0, classsql.length()-5);
			tclasssql="select cast(sum(b.allvalue) as decimal(10,2)) as value, time  from ("
					+" select "+numsql+"cast(sum(a.allvalue) as decimal(10,2)) as allvalue, DATE_FORMAT(a.time,'%m-%d') as time "
					+ "from ("+classsql+") a group by a.time"
					+ ") b group by b.time";
			tclasslist = baseservice.getSqlListS(tclasssql,DB.HIS);
		}
		if(officesql!="") {
			officesql=officesql.substring(0, officesql.length()-5);
			tofficesql="select cast(sum(b.allvalue) as decimal(10,2)) as value, time  from ("
					+" select "+numsql+"cast(sum(a.allvalue) as decimal(10,2)) as allvalue, DATE_FORMAT(a.time,'%m-%d') as time "
					+ "from ("+officesql+") a group by a.time"
					+ ") b group by b.time";
			tofficelist = baseservice.getSqlListS(tofficesql,DB.HIS);
		}
		
		map.put("classList", tclasslist);
		map.put("officeList", tofficelist);
		
		return JSONObject.fromObject(map).toString();
	}
	
	/**
	 * 获取用电数据，获取教室区域能耗，教室类型能耗,折线用电量展示
	 */
	@MethodInfo(METHOD="/service/typeInfo")
	public String queryTypeInfo() throws Exception{
		String startTime=getRequest().getParameter("startTime");
		String endTime=getRequest().getParameter("endTime");
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> rmap=new HashMap<String,Object>();
		List<Map<String, Object>> rtoplist=new ArrayList<Map<String, Object>>();
		
		String sql="",rpttable="",allsql = "",tallsql="";
		String linesql="",classsql="",officesql="",tclasssql="",tofficesql="";
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				if(groupXml.getClassroomgroup().getItem()!=null) {
					for (ClassroomXml classroomXml: groupXml.getClassroomgroup().getItem()) {
						if(!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							sql=" select * from %s where time >= '%s' and time < '%s' UNION";
							sql=String.format(sql,rpttable,startTime,endTime);
							allsql+=sql;
							classsql+=sql;
						}
					}
				}
				if(groupXml.getOfficegroup().getItem()!=null) {
					for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
						if(!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							sql=" select * from %s where time >= '%s' and time < '%s' UNION";
							sql=String.format(sql,rpttable,startTime,endTime);
							allsql+=sql;
							officesql+=sql;
						}
					}
				}
				
			}
		}
		
		List<Map<String, Object>> talllist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> tclasslist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> tofficelist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> linelist=new ArrayList<Map<String, Object>>();
		
		int num=BaseUtil.floorNum();
		String numsql="";
		for(int i=0;i<num;i++) {
			numsql+="cast(sum(a.value"+i+") as decimal(10,2)) as value"+i+",";
		}
		
		if(allsql!="") {
			allsql=allsql.substring(0, allsql.length()-5);
			tallsql="select "+numsql+"cast(sum(allvalue) as decimal(10,2)) as allvalue "
					+ "from ("+allsql+") a";  //返回一条按楼分及总的数据
			linesql="select "+numsql+"cast(sum(a.allvalue) as decimal(10,2)) as allvalue, time "
					+ "from ("+allsql+") a group by a.time";
			talllist = baseservice.getSqlListS(tallsql,DB.HIS);
			linelist = baseservice.getSqlListS(linesql,DB.HIS);
		}
		if(classsql!="") {
			classsql=classsql.substring(0, classsql.length()-5);
			tclasssql="select "+numsql+"cast(sum(allvalue) as decimal(10,2)) as allvalue "
					+ "from ("+classsql+") a";  
			tclasslist = baseservice.getSqlListS(tclasssql,DB.HIS);
		}
		if(officesql!="") {
			officesql=officesql.substring(0, officesql.length()-5);
			tofficesql="select "+numsql+"cast(sum(allvalue) as decimal(10,2)) as allvalue "
					+ "from ("+officesql+") a";  
			tofficelist = baseservice.getSqlListS(tofficesql,DB.HIS);
		}
		
		map.put("counttype", talllist);
		map.put("classlist", tclasslist);
		map.put("officelist", tofficelist);
		map.put("linelist", linelist);
		
		return JSONObject.fromObject(map).toString();
	}
	
	
	/**
	 * 根据时间选择显示时间段的用电信息
	 */
	@MethodInfo(METHOD="/service/moreInfo")
	public String queryMore() throws Exception{
		String startTime=getRequest().getParameter("startTime");
		String endTime=getRequest().getParameter("endTime");
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> rmap=new HashMap<String,Object>();
		List<Map<String, Object>> rtoplist=new ArrayList<Map<String, Object>>();
		
		List<ClassroomXml> classlist=new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist=new ArrayList<ClassroomXml>();
		String sql="",rpttable="",allsql = "",tablesql="",topsql="",countsql="";
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				if(groupXml.getClassroomgroup().getItem()!=null) {
					for (ClassroomXml classroomXml: groupXml.getClassroomgroup().getItem()) {
						if(!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							sql=" select * from %s where time >= '%s' and time < '%s' UNION";
							sql=String.format(sql,rpttable,startTime,endTime);
							allsql+=sql;
						}
						classlist.add(classroomXml);
					}
				}
				if(groupXml.getOfficegroup().getItem()!=null) {
					for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
						if(!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							sql=" select * from %s where time >= '%s' and time < '%s' UNION";
							sql=String.format(sql,rpttable,startTime,endTime);
							allsql+=sql;
						}
						dormlist.add(classroomXml);
					}
				}
			}
		}
		
		List<Map<String, Object>> toplist=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> countlist=new ArrayList<Map<String, Object>>();
		
		int num=BaseUtil.floorNum();
		String numsql="";
		for(int i=0;i<num;i++) {
			numsql+="cast(sum(a.value"+i+") as decimal(10,2)) as value"+i+",";
		}
		
		if(allsql!="") {
			allsql=allsql.substring(0, allsql.length()-5);
			topsql="select mgrobjid,pointid,cast(sum(a.allvalue) as decimal(10,2)) as allvalue, time "
					+ "from ("+allsql+") a group by a.mgrobjid,a.pointid ORDER BY allvalue DESC ";
			countsql="select cast(sum(a.allvalue) as decimal(10,2)) as allvalue "
					+ "from ("+allsql+") a";
			toplist = baseservice.getSqlListS(topsql,DB.HIS);
			countlist = baseservice.getSqlListS(countsql,DB.HIS);
		}
		
		if(toplist.size()>0) {
			for(int i=0;i<toplist.size();i++) {
				for (ClassroomXml classroomXml : classlist) {
					if(classroomXml.getBaseItem()!=null&&!BaseUtil.isNotNull(classroomXml.getElement()))
					for(int j=0;j<classroomXml.getBaseItem().size();j++) {
						if(classroomXml.getBaseItem().get(j).getDev().equals("powerdegree")&&BaseUtil.isNotNull(classroomXml.getBaseItem().get(j).getPointid())) {
							String dev=classroomXml.getBaseItem().get(j).getPointid();
							String mid = "",pid="";
							if(dev.indexOf("_")!=-1) {
								String [] pointidarr=dev.split("_");
								for(int k=0;k<pointidarr.length;k++) {
									String devid=pointidarr[k].split(",")[0];
									String pointid=pointidarr[k].split(",")[1];
									if(k==0) {
										mid+=devid;
										pid+=pointid;
									}else {
										mid+="_"+devid;
										pid+="_"+pointid;
									}
								}
							}else {
								mid=dev.split(",")[0];
								pid=dev.split(",")[1];
							}
							if(mid.equals(toplist.get(i).get("MGROBJID"))&&pid.equals(toplist.get(i).get("POINTID"))) {
								rmap=new HashMap<String,Object>();
								rmap.put("classname",classroomXml.getClassname());
								rmap.put("value",toplist.get(i).get("ALLVALUE"));
								float rate=(Float.parseFloat(toplist.get(i).get("ALLVALUE")+"")/Float.parseFloat(countlist.get(0).get("ALLVALUE")+""))*100;
								rmap.put("rate",UtilTool.cutFloat2(rate+"")) ;
								rtoplist.add(rmap);
							}
						}
					}
				}
				for (ClassroomXml classroomXml : dormlist) {
					if(classroomXml.getBaseItem()!=null&&!BaseUtil.isNotNull(classroomXml.getElement()))
					for(int j=0;j<classroomXml.getBaseItem().size();j++) {
						if(classroomXml.getBaseItem().get(j).getDev().equals("powerdegree")&&BaseUtil.isNotNull(classroomXml.getBaseItem().get(j).getPointid())) {
							String dev=classroomXml.getBaseItem().get(j).getPointid();
							String mid = "",pid="";
							if(dev.indexOf("_")!=-1) {
								String [] pointidarr=dev.split("_");
								for(int k=0;k<pointidarr.length;k++) {
									String devid=pointidarr[k].split(",")[0];
									String pointid=pointidarr[k].split(",")[1];
									if(k==0) {
										mid+=devid;
										pid+=pointid;
									}else {
										mid+="_"+devid;
										pid+="_"+pointid;
									}
								}
							}else {
								mid=dev.split(",")[0];
								pid=dev.split(",")[1];
							}
							if(mid.equals(toplist.get(i).get("MGROBJID"))&&pid.equals(toplist.get(i).get("POINTID"))) {
								rmap=new HashMap<String,Object>();
								rmap.put("classname",classroomXml.getClassname());
								rmap.put("value",toplist.get(i).get("ALLVALUE"));
								float rate=(Float.parseFloat(toplist.get(i).get("ALLVALUE")+"")/Float.parseFloat(countlist.get(0).get("ALLVALUE")+""))*100;
								rmap.put("rate",UtilTool.cutFloat2(rate+"")) ;
								rtoplist.add(rmap);
							}
						}
					}
				}	
			}
		}
		
		map.put("top", rtoplist);
		
		return JSONObject.fromObject(map).toString();

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
		
		List<ClassroomXml> classlist=new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist=new ArrayList<ClassroomXml>();
		String classmgrobjid="", classpointid="", officemgrobjid="",officepointid="",mgrobjid="",pointid="";
		String value="",cvalue="",ovalue="";
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				if(groupXml.getClassroomgroup().getItem()!=null) {
					for (ClassroomXml classroomXml: groupXml.getClassroomgroup().getItem()) {
						if(classroomXml.getBaseItem() != null&&!BaseUtil.isNotNull(classroomXml.getElement())) {
							for(int i=0;i<classroomXml.getBaseItem().size();i++) {
								if(classroomXml.getBaseItem().get(i).getDev().equals("roompower")&&BaseUtil.isNotNull(classroomXml.getBaseItem().get(i).getPointid())) {
									String dev=classroomXml.getBaseItem().get(i).getPointid();
									if(BaseUtil.isNotNull(dev)) {
										String val="";
										if(dev.indexOf("_")!=-1) {
											String[] arr=dev.split("_");
											for(int k=0;k<arr.length;k++) {
												val=UtilTool.parseFloat(val)+UtilTool.parseFloat(BaseUtil.getDevvouValue(arr[k].split(",")[0],arr[k].split(",")[1])+"")+"";
											}
										}else {
											val=BaseUtil.getDevvouValue(dev.split(",")[0],dev.split(",")[1]);
										}
										cvalue=UtilTool.cutFloat2(UtilTool.parseFloat(cvalue)+UtilTool.parseFloat(val)+"");
										value=UtilTool.cutFloat2(UtilTool.parseFloat(value)+UtilTool.parseFloat(val)+"");
									}
								}
							}
						}
						classlist.add(classroomXml);
					}
				}
				if(groupXml.getOfficegroup().getItem()!=null) {
					for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
						if(classroomXml.getBaseItem() != null&&!BaseUtil.isNotNull(classroomXml.getElement())) {
							for(int i=0;i<classroomXml.getBaseItem().size();i++) {
								if(classroomXml.getBaseItem().get(i).getDev().equals("roompower")&&BaseUtil.isNotNull(classroomXml.getBaseItem().get(i).getPointid())) {
									String dev=classroomXml.getBaseItem().get(i).getPointid();
									if(BaseUtil.isNotNull(dev)) {
										String val="";
										if(dev.indexOf("_")!=-1) {
											String[] arr=dev.split("_");
											for(int k=0;k<arr.length;k++) {
												val=UtilTool.parseFloat(val)+UtilTool.parseFloat(BaseUtil.getDevvouValue(arr[k].split(",")[0],arr[k].split(",")[1])+"")+"";
											}
										}else {
											val=BaseUtil.getDevvouValue(dev.split(",")[0],dev.split(",")[1]);
										}
										ovalue=UtilTool.cutFloat2(UtilTool.parseFloat(ovalue)+UtilTool.parseFloat(val)+"");
										value=UtilTool.cutFloat2(UtilTool.parseFloat(value)+UtilTool.parseFloat(val)+"");
									}
								}
							}
						}
						dormlist.add(classroomXml);
					}
				}
			}
		}
		List nlist=new ArrayList<>();
		List vlist=new ArrayList<>();
		nlist.add("教室");
		nlist.add("办公室");
		Map<String,Object> cmap=new HashMap<String,Object>();
		cmap.put("name", "教室");
		cmap.put("value",cvalue);
		Map<String,Object> dmap=new HashMap<String,Object>();
		dmap.put("name", "办公室");
		dmap.put("value", ovalue);
		vlist.add(cmap);
		vlist.add(dmap);
		String a=UtilTool.cutFloat4(UtilTool.parseFloat(value)/UtilTool.parseFloat(mapItem.get("acreage")+"")+"");
		String p=UtilTool.cutFloat4(UtilTool.parseFloat(value)/UtilTool.parseFloat(mapItem.get("countnumber")+"")+"");
		
		map.put("allPower", value);
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
        String serviceid=getRequest().getParameter("serviceid");
		Service service=BaseUtil.getService(serviceid);
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
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				if(groupXml.getClassroomgroup().getItem()!=null) {
					for (ClassroomXml classroomXml: groupXml.getClassroomgroup().getItem()) {
						if(classroomXml.getBaseItem() != null)	{
							Map<String, Object> classmap=airchange(classroomXml);
							if((boolean) classmap.get("air")) {
								classair++;
							}
						}
					}
				}
				if(groupXml.getOfficegroup().getItem()!=null) {
					for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
						if(classroomXml.getBaseItem() != null)	{
							Map<String, Object> classmap=airchange(classroomXml);
							if((boolean) classmap.get("air")) {
								dormair++;
							}
						}
					}
				}
			}
		}
		
		float c=(UtilTool.parseFloat(classair+"")/UtilTool.parseFloat(mapItem.get("classroomcount")+""))*100;
		float d=(UtilTool.parseFloat(dormair+"")/UtilTool.parseFloat(mapItem.get("dormcount")+""))*100;
		map.put("classair", UtilTool.cutFloat2(c+""));  //教室运行的空调比
		map.put("dormair", UtilTool.cutFloat2(d+""));  //办公室运行的空调比
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
				if(groupXml.getClassroomgroup().getItem()!=null && groupXml.getClassroomgroup().getItem().size()>0) {
					for (ClassroomXml classroomXml : groupXml.getClassroomgroup().getItem()) {
						ptree=new zTreeByOther();
						ptree.setId(classroomXml.getCode());
						ptree.setName(classroomXml.getClassname());
						ptree.setMap(map);
						classlist.add(ptree);
					}
				}
				
				
				dtree.setId(groupXml.getOfficegroup().getRoomno());
				dtree.setName(groupXml.getOfficegroup().getName()); //办公室
				if(groupXml.getOfficegroup().getItem()!=null && groupXml.getOfficegroup().getItem().size()>0) {
					for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
						ptree=new zTreeByOther();
						ptree.setId(classroomXml.getCode());
						ptree.setName(classroomXml.getClassname());
						ptree.setMap(map);
						dormlist.add(ptree);
					}
				}

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
			map.put("groupfloor", groupXml.getGroupfloor());
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
