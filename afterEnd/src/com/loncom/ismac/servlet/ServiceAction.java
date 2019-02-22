package com.loncom.ismac.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loncom.ismac.annotation.MethodInfo;
import com.loncom.ismac.annotation.Modular;
import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.bean.zTreeByOther;
import com.loncom.ismac.bean.xml.ClassroomXml;
import com.loncom.ismac.bean.xml.ClassroomgroupXml;
import com.loncom.ismac.bean.xml.GroupXml;
import com.loncom.ismac.bean.xml.OfficegroupXml;
import com.loncom.ismac.bean.xml.RootXml;
import com.loncom.ismac.bean.xml.XmlEdiParser;
import com.loncom.ismac.lservice.bean.Service;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.FileUtil;
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
		for (ClassroomXml classroomXml : list) {
			list1.add(querychange(classroomXml));
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
		int classroomcount=0,dormcount=0,countnumber=0;
		float acreage=0,classair=0,dormair=0;
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				acreage+=UtilTool.parseFloat(groupXml.getAcreage());
				classroomcount+=UtilTool.parseInt(groupXml.getClassroomcount());
				dormcount+=UtilTool.parseInt(groupXml.getDormcount());
				countnumber+=UtilTool.parseInt(groupXml.getCountnumber());
			}
		}
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
		float c=(Float.parseFloat(classair+"")/Float.parseFloat(classroomcount+""))*100;
		float d=(Float.parseFloat(dormair+"")/Float.parseFloat(dormcount+""))*100;
		map.put("classair", UtilTool.parseFloat(UtilTool.cutFloat2(c+"")));  //教室运行的空调比
		map.put("dormair", UtilTool.parseFloat(UtilTool.cutFloat2(d+"")));  //办公室运行的空调比
		map.put("classroomcount", classroomcount);  //教室数
		map.put("dormcount", dormcount);  //办公室数
		map.put("countnumber", countnumber); // 总人数
		map.put("acreage", acreage); //面积数
		
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
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("groupno",groupXml.getGroupno());
				map.put("groupname",groupXml.getGroupname());
				list.add(map);
			}
		}
		return JSONArray.fromObject(list).toString();
	}

	public ClassroomXml getObj() {
		return obj;
	}

	public void setObj(ClassroomXml obj) {
		this.obj = obj;
	}
	
	
	
	
}
