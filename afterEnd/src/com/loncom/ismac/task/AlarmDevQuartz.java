package com.loncom.ismac.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.loncom.ismac.alarm.bean.AlarmThresholdBean;
import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.bean.xml.ClassroomXml;
import com.loncom.ismac.bean.xml.ClassroomgroupXml;
import com.loncom.ismac.bean.xml.Command;
import com.loncom.ismac.bean.xml.GroupXml;
import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.logs.Logs;
import com.loncom.ismac.lservice.bean.Service;
import com.loncom.ismac.service.IBaseService;
import com.loncom.ismac.service.impl.BaseServiceImpl;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.CMD;
import com.loncom.ismac.util.UtilTime;
import com.loncom.ismac.util.UtilTimeThread;
import com.loncom.ismac.util.UtilTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AlarmDevQuartz implements Job {
	Object obj = null;
	IBaseService baseservice = new BaseServiceImpl();
	AlarmThresholdBean alarm=new AlarmThresholdBean();
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		obj = new AlarmDevQuartz();
		try {
			task();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void task() throws Exception {
		// TODO Auto-generated method stub
		String now = UtilTimeThread.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String daybefore = UtilTimeThread.format(new Date(), "yyyy-MM-dd 00:00:00");
		String monthbefore = UtilTimeThread.format(new Date(), "yyyy-MM-01 00:00:00");
		List qlist=baseservice.query(alarm);
		if(qlist.size()<=0) {
			return;
		}
		Map<String, Object> alarmObj=JSONObject.fromObject(qlist.get(0));
		if(alarmObj.get("model").equals("1")) {
			String rpttable="",code="";
			for (Service service : AppContext.getService()) {
				for(GroupXml groupXml:service.getGroupcontrol().getGroup()) {
					String towername=groupXml.getGroupname();
					if(groupXml.getClassroomgroup().getItem()!=null) {
						for (ClassroomXml classroomXml: groupXml.getClassroomgroup().getItem()) {
							String classname=classroomXml.getClassname();
							if(!BaseUtil.isNotNull(classroomXml.getElement())&&classroomXml.getBaseItem() != null) {
								rpttable = String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
								code=classroomXml.getCode();
								for (int j = 0; j < classroomXml.getBaseItem().size(); j++) {
									if (classroomXml.getBaseItem().get(j).getDev().equals("powerdegree")&&
											BaseUtil.isNotNull(classroomXml.getBaseItem().get(j).getPointid())) {
										String dev = classroomXml.getBaseItem().get(j).getPointid();
										String mid = "", pid = "",dayvalue="",monthvalue="";
										if (dev.indexOf("_") != -1) {
											String[] pointidarr = dev.split("_");
											for (int k = 0; k < pointidarr.length; k++) {
												String devid = pointidarr[k].split(",")[0];
												String pointid = pointidarr[k].split(",")[1];
												if (k == 0) {
													mid += devid;
													pid += pointid;
												} else {
													mid += "_" + devid;
													pid += "_" + pointid;
												}
											}
										} else {
											mid = dev.split(",")[0];
											pid = dev.split(",")[1];
										}
										dayvalue = getValue(rpttable,daybefore,now, mid, pid);
										if (BaseUtil.isNotNull(dayvalue)) {
											if(UtilTool.parseFloat(dayvalue)-UtilTool.parseFloat(alarmObj.get("dayvalue")+"")>0) {
												String desc=towername+"/"+classname+",今日已用电:"+UtilTool.parseFloat(dayvalue)+
														",限值:"+alarmObj.get("dayvalue");
												putData(code,classname,daybefore,monthbefore,now,"1","classroom",desc);
											}
										}
										monthvalue=getValue(rpttable,monthbefore,now,mid,pid);
										if (BaseUtil.isNotNull(monthvalue)) {
											if(UtilTool.parseFloat(monthvalue)-UtilTool.parseFloat(alarmObj.get("monthvalue")+"")>0) {
												String desc=towername+"/"+classname+"本月已用电:"+UtilTool.parseFloat(monthvalue)+
														",限值:"+alarmObj.get("monthvalue");
												putData(code,classname,daybefore,monthbefore,now,"2","classroom",desc);
											}
										}
									}
								}
							}
						}
					}
					
					if(groupXml.getOfficegroup().getItem()!=null) {
						for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
							String classname=classroomXml.getClassname();
							if(!BaseUtil.isNotNull(classroomXml.getElement())&&classroomXml.getBaseItem() != null) {
								rpttable = String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
								code=classroomXml.getCode();
								for (int j = 0; j < classroomXml.getBaseItem().size(); j++) {
									if (classroomXml.getBaseItem().get(j).getDev().equals("powerdegree")&&
											BaseUtil.isNotNull(classroomXml.getBaseItem().get(j).getPointid())) {
										String dev = classroomXml.getBaseItem().get(j).getPointid();
										String mid = "", pid = "",dayvalue="",monthvalue="";
										if (dev.indexOf("_") != -1) {
											String[] pointidarr = dev.split("_");
											for (int k = 0; k < pointidarr.length; k++) {
												String devid = pointidarr[k].split(",")[0];
												String pointid = pointidarr[k].split(",")[1];
												if (k == 0) {
													mid += devid;
													pid += pointid;
												} else {
													mid += "_" + devid;
													pid += "_" + pointid;
												}
											}
										} else {
											mid = dev.split(",")[0];
											pid = dev.split(",")[1];
										}
										dayvalue = getValue(rpttable,daybefore,now, mid, pid);
										if (BaseUtil.isNotNull(dayvalue)) {
											if(UtilTool.parseFloat(dayvalue)-UtilTool.parseFloat(alarmObj.get("dayvalue")+"")>0) {
												String desc=towername+"/"+classname+",今日已用电:"+UtilTool.parseFloat(dayvalue)+
														",限值:"+alarmObj.get("dayvalue");
												putData(code,classname,daybefore,monthbefore,now,"1","officeroom",desc);
											}
										}
										monthvalue=getValue(rpttable,monthbefore,now,mid,pid);
										if (BaseUtil.isNotNull(monthvalue)) {
											if(UtilTool.parseFloat(monthvalue)-UtilTool.parseFloat(alarmObj.get("monthvalue")+"")>0) {
												String desc=towername+"/"+classname+"本月已用电:"+UtilTool.parseFloat(monthvalue)+
														",限值:"+alarmObj.get("monthvalue");
												putData(code,classname,daybefore,monthbefore,now,"2","officeroom",desc);
											}
										}
									}
								}
							}
						}
					}
					
				}
			}
		}
	}
	
	public String getValue(String rpttable, String nowBefore, String now, String devid, String pointid) throws Exception {
		String sql= "select cast(sum(allvalue) as decimal(10,2)) as allvalue from "+rpttable+" WHERE time >= '"+nowBefore+"' and time <= '"+now+"'";
		List<Map<String, Object>> list = baseservice.getSqlListS(sql, DB.HIS);
		if(list.size()>0) {
			String val = list.get(0).get("ALLVALUE") + "";
			return val;
		}
		return null;
	}
	
	/*
	 * code:教室编码 判断告警是否存在用，
	 * beforetime: 判断告警是否存在的开始时间
	 * */
	public void putData(String code,String classname, String daybefore,String monthbefore, String time, String type,String roomtype,String desc) throws Exception {
		String beforetime="";
		if(type.equals("1")) {
			beforetime=daybefore;
		}else {
			beforetime=monthbefore;
		}
		String judgeSql="select * from alarm where code='"+code+"'and type='"+type+"' and createTime >= '"+beforetime+"' and createTime <= '"+time+"'";
		List<Map<String, Object>> list=baseservice.getSqlListS(judgeSql,DB.HIS);
		if(list.size()<=0) {
			String sql="insert into alarm (`code`,`classname`,`createTime`,`type`,`roomType`,`desc`) values ('%s','%s','%s','%s','%s','%s')";
			baseservice.exeSql(String.format(sql,code, classname, time,type,roomtype,desc), DB.HIS);
		}
		
//		String sql="insert into alarm (`code`,`classname`,`createTime`,`type`,`roomType`,`desc`) values ('%s','%s','%s','%s','%s','%s')";
//		baseservice.exeSql(String.format(sql,code, classname, time,type,roomtype,desc), DB.HIS);
		
		String sendSql="select * from alarm where createTime >= '"+daybefore+"' and createTime <= '"+time+"'";
		List<Map<String, Object>> backlist=baseservice.getSqlListS(sendSql,DB.HIS);
		
		List sendlist=new ArrayList<>();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("key", "alarmAll");
		map.put("value", backlist.size());
		sendlist.add(map);
		
		JSONArray json2=JSONArray.fromObject(sendlist);
		BaseUtil.setMsg(json2.toString());
		
		
	}
	
	

	public AlarmThresholdBean getAlarm() {
		return alarm;
	}

	public void setAlarm(AlarmThresholdBean alarm) {
		this.alarm = alarm;
	}
	
	

}
