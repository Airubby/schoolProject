package com.loncom.ismac.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.formula.functions.T;

import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.bean.Objc;
import com.loncom.ismac.bean.xml.ClassroomXml;
import com.loncom.ismac.bean.xml.Command;
import com.loncom.ismac.service.IMainService;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.CMD;
import com.loncom.ismac.util.UtilTimeThread;
import com.loncom.ismac.util.UtilTool;

public class MainServiceImpl extends BaseServiceImpl<T> implements IMainService {

	@Override
	public Map<String,Object> queryGroup(String group,String floor) throws Exception {
		Map<String, Map<String, List<Objc>>> groupmap = AppContext.group.get(group);// 获取楼宇下所有办公室及教室
		String daytable=String.format(CMD.DAY_DEVTABLE, UtilTimeThread.format(new Date(), "yyyyMMdd"));//获取今日报表
		String monthtable=String.format(CMD.MONTH_DEVTABLE, UtilTimeThread.format(new Date(), "yyyyMM"));//获取当月报表名称
		Map<String,Object> map=new HashMap<String, Object>();
		StringBuffer classbuffer=new StringBuffer();//教室SQL条件串
		StringBuffer officebuffer=new StringBuffer();//办公室SQL条件串
		List<Objc> classroom = new ArrayList<Objc>();//教室集合
		List<Objc> office = new ArrayList<Objc>();//教室集合
		List classroomlist=new ArrayList<>();  //教室的数据列表
		List officelist=new ArrayList<>();  //办公室的数据列表
		List maplist=new ArrayList<>();  //教室办公室数据列表
		String classvalue="0";//教室今日用电
		String officevalue="0";//办公室今日用电
		String classmonthvalue="0"; //教室当月用电
		String officemonthvalue="0"; //办公室当月用电
		String monthvalue="0";//当月用电
		List<Map<String, Object>> list=null;
		if(BaseUtil.isNotNull(floor)){
			AppContext.getMapClassooffice(groupmap.get(floor),classroom,office);//获取楼层的
		}else{
			AppContext.getMapClassooffice(groupmap,classroom,office);//获取楼栋的
		}
		String sql="select ifnull(sum(convert(value,DECIMAL(10,2))),0) as `value` from %s where %s"; //执行SQL
		if(classroom.size()>0 || office.size()>0) {
			for (Objc objc : classroom) {
				if(BaseUtil.isNotNull(objc.getDevid())&&BaseUtil.isNotNull(objc.getPointid())) {
					classbuffer.append(" (mgrobjid='"+objc.getDevid()+"' and pointid='"+objc.getPointid()+"' ) or"); //拼接教室查询条件
				}
				maplist.add(getValue(objc));
			}
			if(BaseUtil.isNotNull(classbuffer.toString())){
				classbuffer.delete(classbuffer.length()-2, classbuffer.length());//移除多余OR
				list=getSqlList(String.format(sql, daytable,classbuffer.toString()));//执行查询教室当天总用电
				if(list.size()>0){
					classvalue=list.get(0).get("VALUE")+"";
				}
			}
			
			for (Objc objc : office) {
				if(BaseUtil.isNotNull(objc.getDevid())&&BaseUtil.isNotNull(objc.getPointid())) {
					officebuffer.append(" (mgrobjid='"+objc.getDevid()+"' and pointid='"+objc.getPointid()+"' ) or"); //拼接办公室查询条件
				}
				maplist.add(getValue(objc));
			}
			if(BaseUtil.isNotNull(officebuffer.toString())){
				officebuffer.delete(officebuffer.length()-2, officebuffer.length());//移除多余OR
				list=getSqlList(String.format(sql, daytable,officebuffer.toString()));//执行查询办公室当天总用电
				if(list.size()>0){
					officevalue=list.get(0).get("VALUE")+"";
				}
			}
			
			if(BaseUtil.isNotNull(classbuffer.toString()) || BaseUtil.isNotNull(officebuffer.toString())){
				String sqls="";
				if(BaseUtil.isNotNull(classbuffer.toString())){
					String s=classbuffer.toString();
					list=getSqlList(String.format(sql, monthtable,s));
					if(list.size()>0) {
						classmonthvalue=list.get(0).get("VALUE")+"";
					}
					sqls=s+" or ";
					classbuffer.setLength(0);
				}
				if(BaseUtil.isNotNull(officebuffer.toString())){
					String s=officebuffer.toString();
					list=getSqlList(String.format(sql, monthtable,s));
					if(list.size()>0) {
						officemonthvalue=list.get(0).get("VALUE")+"";
					}
					sqls+=s;
					officebuffer.setLength(0);
				}else{
					sqls=sqls.substring(0,sqls.lastIndexOf("or"));
				}
				
				list=getSqlList(String.format(sql, monthtable,sqls));//查询办公室和教室这个月的总用电
				if(list.size()>0){
					monthvalue=list.get(0).get("VALUE")+"";
				}
			}
		}
		
		map.put("classvalue", classvalue);//当天教室用电
		map.put("officevalue", officevalue);//当天办公室用电
		map.put("psum", UtilTool.cutFloat2(UtilTool.parseFloat(classvalue) + UtilTool.parseFloat(officevalue)+""));//当天总用电
		map.put("classmvalue", classmonthvalue);
		map.put("officemvalue", officemonthvalue);
		map.put("monthsum",monthvalue);//当月总用电
		map.put("list", maplist);  //数据列表
		return map;
	}
	
	public static Map<String, Object> getValue(Objc objc ){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("code", objc.getId());
		map.put("classname", objc.getName());
		map.put("serviceid", objc.getServerid());
		String lamp="0",air = "0";
		for(int i=0;i<objc.getItem().size();i++) {
			if(BaseUtil.isNotNull(objc.getItem().get(i).getPointid())) {
				String dev=objc.getItem().get(i).getPointid();
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
				if(objc.getItem().get(i).getDev().equals("airstate")) { //只用第一个判断
					air=BaseUtil.getDevvouValue(devid.split(",")[0],pointid.split(",")[0]);
					map.put("airDevid", devid.split(",")[0]);
					map.put("airStateid", pointid.split(",")[0]);  
					map.put("airClosecmd", "airClosecmd");
					map.put("air",air.equals("0")?false:true);
					map.put("airdev", air);
					map.put("airstate_key", devid.split(",")[0]+"_"+pointid.split(",")[0]);
				}else if(objc.getItem().get(i).getDev().equals("temp")) {//humituredev，环境温度，多个控制显示值取一个
					map.put("humituredev", BaseUtil.getDevvouValue(devid.split(",")[0],pointid.split(",")[0]));
					map.put("temp_key", devid.split(",")[0]+"_"+pointid.split(",")[0]);
				}else if(objc.getItem().get(i).getDev().equals("linestate")) {//红外，有无人
					map.put("infrareddev", BaseUtil.getDevvouValue(devid,pointid));
					map.put("linestate_key", devid+"_"+pointid);
				}else if(objc.getItem().get(i).getDev().equals("lampstate")) {//照明
					lamp=BaseUtil.getDevvouValue(devid,pointid);
					map.put("lamp", lamp.equals("0")?false:true);
					map.put("lampdev", lamp);
				}else if(objc.getItem().get(i).getDev().equals("aircontrol")) {  // 关空调指令
					map.put("aircontrol_devid", devid);
					map.put("aircontrol_pointid", pointid);
				}else if(objc.getItem().get(i).getDev().equals("roompower")) {//实时功耗
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
				}
				
				
			}
		}
		return map;
	}

}









