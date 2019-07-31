package com.loncom.ismac.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

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

public class RptDevQuartz implements Job {
	Object obj = null;
	IBaseService baseservice = new BaseServiceImpl();

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		obj = new RptDevQuartz();
		try {
			task();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void task() throws Exception {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = UtilTimeThread.format(new Date(), "yyyy-MM-dd HH:mm:ss");
//		String now ="2019-07-25 16:21:00";
		System.out.println("执行了抽取报表"+now);
//		 String now ="2019-04-28 11:16:00";
		Date date = sdf.parse(now);
		String nowBefore = UtilTime.getNowBeforeMin(date, -10);
		List<ClassroomXml> classlist = new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist = new ArrayList<ClassroomXml>();
		IBaseService baseservice = new BaseServiceImpl();
		String rpttable = "";

		for (Service service : AppContext.getService()) {
			for (int i = 0; i < service.getGroupcontrol().getGroup().size(); i++) {
				GroupXml groupXml=service.getGroupcontrol().getGroup().get(i);
				if(groupXml.getClassroomgroup().getItem()!=null) {
					for (ClassroomXml classroomXml: groupXml.getClassroomgroup().getItem()) {
						if(!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable = String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							int count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, rpttable), DB.HIS);
							if (count <= 0) {
								BaseUtil.createRPT(rpttable);
							}
							if (classroomXml.getBaseItem() != null) {
								for (int j = 0; j < classroomXml.getBaseItem().size(); j++) {
									if (classroomXml.getBaseItem().get(j).getDev().equals("powerdegree")) {
										String dev = classroomXml.getBaseItem().get(j).getPointid();
										String mid = "", pid = "", value = "";
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
												String val = getValue(nowBefore, now, devid, pointid);
												if (BaseUtil.isNotNull(val))
													value = UtilTool.parseFloat(value) + UtilTool.parseFloat(val) + "";
											}
										} else {
											mid = dev.split(",")[0];
											pid = dev.split(",")[1];
											value = getValue(nowBefore, now, mid, pid);
										}
										if (BaseUtil.isNotNull(value))
											putData(rpttable, now, mid, pid, UtilTool.cutFloat2(UtilTool.parseFloat(value) + ""), i);
									}
								}
							}
						}
					}
				}
				if(groupXml.getOfficegroup().getItem()!=null) {
					for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
						if(!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable = String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							int count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, rpttable), DB.HIS);
							if (count <= 0) {
								BaseUtil.createRPT(rpttable);
							}
							if (classroomXml.getBaseItem() != null) {
								for (int j = 0; j < classroomXml.getBaseItem().size(); j++) {
									if (classroomXml.getBaseItem().get(j).getDev().equals("powerdegree")&&
											BaseUtil.isNotNull(classroomXml.getBaseItem().get(j).getPointid())) {
										String dev = classroomXml.getBaseItem().get(j).getPointid();
										String mid = "", pid = "", value = "";
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
												String val = getValue(nowBefore, now, devid, pointid);
												if (BaseUtil.isNotNull(val))
													value = UtilTool.parseFloat(value) + UtilTool.parseFloat(val) + "";
											}
										} else {
											mid = dev.split(",")[0];
											pid = dev.split(",")[1];
											value = getValue(nowBefore, now, mid, pid);
										}
										if (BaseUtil.isNotNull(value))
											putData(rpttable, now, mid, pid, UtilTool.cutFloat2(UtilTool.parseFloat(value) + ""), i);
									}
								}
							}
						}
					}
				}


			}
		}

	}

	/**
	 * 获取十分钟之前的值
	 * 
	 * @param devtable
	 *            查询表
	 * @param nowBefore
	 *            十分钟前的时间
	 * @param now
	 *            当前时间
	 * @param devid
	 *            设备id
	 * @param pointid
	 *            属性id
	 * @param rpttable
	 *            报表
	 * @return
	 * @throws Exception
	 */
	public String getValue( String nowBefore, String now, String devid, String pointid) throws Exception {
		IBaseService baseservice = new BaseServiceImpl();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String devtable="";
//		String value="";
//		Date nowDate=sdf.parse(now);
//		Date beforeDate=sdf.parse(nowBefore);
//		value=checkValue(beforeDate,nowBefore,devid,pointid);
		devtable = String.format(CMD.HIS_DEVTABLE,UtilTimeThread.format(new Date(), "yyyyMMdd"));
		int devcount = baseservice.getCount(String.format(CMD.IS_HIS_BASE, devtable), DB.HIS);
		if(devcount>0) {
			String MAXsql="select COALESCE(MAX(convert(value,decimal(10,2))),'0') as value from "
					+ devtable + " WHERE time >= '"+ nowBefore + "' and time <= '"+ now + "' and mgrobjid='" + devid + "' and pointid='" + pointid+"'";
			List<Map<String, Object>> maxlist = baseservice.getSqlListS(MAXsql, DB.HIS);
			String MINsql="select COALESCE(MIN(convert(value,decimal(10,2))),'0') as value from "
					+ devtable + " WHERE time >= '"+ nowBefore + "' and time <= '"+ now + "' and mgrobjid='" + devid + "' and pointid='" + pointid+"'";
			List<Map<String, Object>> minlist = baseservice.getSqlListS(MINsql, DB.HIS);
			if(maxlist.size()>0&&minlist.size()>0&&
					BaseUtil.isNotNull(maxlist.get(0).get("VALUE")+"")&&
					BaseUtil.isNotNull(minlist.get(0).get("VALUE")+"")&&!"0".equals(minlist.get(0).get("VALUE")+"")) {
				
				String maxvalue = maxlist.get(0).get("VALUE") + "";
				String minvalue = minlist.get(0).get("VALUE") + "";
				String val =UtilTool.cutFloat2(UtilTool.parseFloat(maxvalue) - UtilTool.parseFloat(minvalue) + "");
				if(!val.equals("0")&&!val.equals("0.00")) {
					System.out.println(MAXsql);
					System.out.println(MINsql);
					System.out.println("val："+val);
					return val;
				}else {
					return null;
				}
				
			}
			Logs.logsync("MAX:SQL"+MAXsql+"MIN:SQL"+MINsql+"Max:"+maxlist.toString() + "Min:"+minlist.toString());
		}
		return null;
	}
	
	public String checkValue(Date beforeDate, String nowBefore, String devid, String pointid) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int num=0;
		for(int i=0;i<15;i++) {
			String time=UtilTime.getNowBeforeDay(beforeDate, num);
			Date date=sdf.parse(time);
			String table = String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD(date));
			int count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, table), DB.HIS);
			if (count > 0) {
				String checksql="select COALESCE(MAX(VALUE),'0') as value from "
						+ table + " WHERE time <= '"+ nowBefore + "' and mgrobjid='" + devid + "' and pointid='" + pointid+"'";
				List<Map<String, Object>> checklist = baseservice.getSqlListS(checksql, DB.HIS);
				if(checklist.size()>0 && !checklist.get(0).get("VALUE").equals("0")&&BaseUtil.isNotNull(checklist.get(0).get("VALUE")+"")) {
					return checklist.get(0).get("VALUE") + "";
				}
			}
			num=num-1;
		}
		return "0";
	}
	
	public void putData(String rpttable, String now, String mid, String pid, String value, int i) throws Exception {
		System.out.println("插入数据"+value+"devid:"+mid+"pointid:"+pid);
		IBaseService baseservice = new BaseServiceImpl();
		int num = BaseUtil.floorNum();
		String arr[] = new String[num];
		for (int n = 0; n < num; n++) {
			arr[n] = "0";
		}
		arr[i] = value;
		String values = "('" + mid + "','" + pid + "','" + value + "','" + now + "','" + arr[i] + "','";
		String invalue = "";
		for (int m = 0; m < num; m++) {
			if (m == num - 1) {
				values += arr[m] + "')";
			} else {
				values += arr[m] + "','";
			}
			invalue += ",value" + m;
		}
		String INSERT_RPT_TABLE = "INSERT INTO %s (MGROBJID, POINTID, VALUE,TIME,ALLVALUE %s) VALUES ";
		baseservice.exeSql(String.format(INSERT_RPT_TABLE, rpttable, invalue) + values, DB.HIS);
	}

}
