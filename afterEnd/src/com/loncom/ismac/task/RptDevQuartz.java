package com.loncom.ismac.task;

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
import com.loncom.ismac.bean.xml.GroupXml;
import com.loncom.ismac.jdbc.DB;
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
		int count = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = new Date();
		String now = UtilTimeThread.format(dt, "yyyy-MM-dd HH:mm:00");
		String today = UtilTimeThread.format(dt, "yyyy-MM-dd 00:00:00");
		// System.out.println("执行了抽取报表"+now);
		Date date = sdf.parse(now);
		String nowBefore = UtilTime.getNowBeforeMin(date, -120); // 这个地方的时间为，定时器设置的时间的2倍，迭代求期间的最大最小值 -120
		List<ClassroomXml> classlist = new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist = new ArrayList<ClassroomXml>();
		IBaseService baseservice = new BaseServiceImpl();
		String rpttable = "";
		String daytable = String.format(CMD.DAY_DEVTABLE, UtilTimeThread.format(dt, "yyyyMMdd"));

		count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, daytable), DB.HIS);// 获取当天日报表是否存在
		if (count <= 0) {// 如果不存在进入方法
			BaseUtil.createRPT(daytable);// 创建日报表
			String monthjtable = UtilTime.getNowBeforeMonth(new Date(), -7);// 删除当前时间前面7天的日报表
			count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, monthjtable), DB.HIS);// 查询表是否存在
			if (count > 0) {// 如果不存在 不执行删除指令
				baseservice.exeSql("drop table " + monthjtable + "");// 删除表
			}
		}
		for (Service service : AppContext.getService()) {
			for (int i = 0; i < service.getGroupcontrol().getGroup().size(); i++) {
				GroupXml groupXml = service.getGroupcontrol().getGroup().get(i);
				if (groupXml.getClassroomgroup().getItem() != null) {
					for (ClassroomXml classroomXml : groupXml.getClassroomgroup().getItem()) {
						if (!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable = String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, rpttable), DB.HIS);// 判断教室报表是否存在
							if (count <= 0) {// 如果不存在
								BaseUtil.createRPT(rpttable);// 创建教室表
							}
							if (classroomXml.getBaseItem() != null) {
								for (int j = 0; j < classroomXml.getBaseItem().size(); j++) {
									if (classroomXml.getBaseItem().get(j).getDev().equals("powerdegree")
											&& BaseUtil.isNotNull(classroomXml.getBaseItem().get(j).getPointid())) {
										String dev = classroomXml.getBaseItem().get(j).getPointid();
										String mid = "", pid = "", value = "", orback = "0", mback = "0", orvalue = "",
												mvalue = "", dbvalue = "", mdbvalue = "";
										if (dev.indexOf("_") != -1) {
											String[] pointidarr = dev.split("_");
											for (int k = 0; k < pointidarr.length; k++) {
												String devid = pointidarr[k].split(",")[0];
												String pointid = pointidarr[k].split(",")[1];
												String val = "";
												// 第一个是电量仪，第二个是pdu
												if (k == 0) {
													mid += devid;
													pid += pointid;
													orvalue = BaseUtil.rptgetDevvouValue(devid, pointid);
												} else {
													mid += "_" + devid;
													pid += "_" + pointid;
													mvalue = BaseUtil.rptgetDevvouValue(devid, pointid);
													mdbvalue = getValue(rpttable, "MVALUE");
													if ("0".equals(mvalue) || "0.0".equals(mvalue)|| "0.00".equals(mvalue)) {
														mback = "0";
													} else {
														if(mdbvalue!=null) {
															if (UtilTool.parseFloat(mvalue) - UtilTool.parseFloat(mdbvalue) > 0) {
																mback = UtilTool.parseFloat(mvalue)- UtilTool.parseFloat(mdbvalue)+ "";
															}
														}else {
															mback="0.00";
														}
														
													}
												}
											}

										} else {
											mid = dev.split(",")[0];
											pid = dev.split(",")[1];
											orvalue = BaseUtil.rptgetDevvouValue(mid, pid);
										}
										dbvalue = getValue(rpttable, "ORVALUE");
										if ("0".equals(orvalue) || "0.0".equals(orvalue) || "0.00".equals(orvalue)) {
											orback = "0";
										} else {
											if(dbvalue!=null) {
												if (UtilTool.parseFloat(orvalue) - UtilTool.parseFloat(dbvalue) > 0) {
													orback = UtilTool.parseFloat(orvalue) - UtilTool.parseFloat(dbvalue)+ "";
												}
											}else {
												orback = "0.00";
											}
											
										}
										if (orback.equals("0") && mback.equals("0")) {
											value = null;
										} else {
											value = UtilTool.parseFloat(orback) + UtilTool.parseFloat(mback) + "";
										}
										if (BaseUtil.isNotNull(value)) {
											putData(rpttable, now, mid, pid,UtilTool.cutFloat2(UtilTool.parseFloat(value) + ""), i, orvalue,mvalue);
											putData(daytable, now, mid, pid,UtilTool.cutFloat2(UtilTool.parseFloat(value) + ""), i, orvalue,mvalue);
										}
									}
								}
							}
						}
					}
				}
				if (groupXml.getOfficegroup().getItem() != null) {
					for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
						if (!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable = String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, rpttable), DB.HIS);
							if (count <= 0) {
								BaseUtil.createRPT(rpttable);
							}
							if (classroomXml.getBaseItem() != null) {
								for (int j = 0; j < classroomXml.getBaseItem().size(); j++) {
									if (classroomXml.getBaseItem().get(j).getDev().equals("powerdegree")
											&& BaseUtil.isNotNull(classroomXml.getBaseItem().get(j).getPointid())) {
										String dev = classroomXml.getBaseItem().get(j).getPointid();
										String mid = "", pid = "", value = "", orback = "0", mback = "0", // 中间计算存储值
												orvalue = "", mvalue = "", // 当前实时值
												dbvalue = "", mdbvalue = ""; // 数据库中前面的值
										if (dev.indexOf("_") != -1) {
											String[] pointidarr = dev.split("_");
											for (int k = 0; k < pointidarr.length; k++) {
												String devid = pointidarr[k].split(",")[0];
												String pointid = pointidarr[k].split(",")[1];
												String val = "";
												if (k == 0) {
													mid += devid;
													pid += pointid;
													orvalue = BaseUtil.rptgetDevvouValue(devid, pointid);
												} else {
													mid += "_" + devid;
													pid += "_" + pointid;
													mvalue = BaseUtil.rptgetDevvouValue(devid, pointid);
													mdbvalue = getValue(rpttable, "MVALUE");
													if ("0".equals(mvalue) || "0.0".equals(mvalue)|| "0.00".equals(mvalue)) {
														mback = "0";
													} else {
														if(mdbvalue!=null) {
															if (UtilTool.parseFloat(mvalue) - UtilTool.parseFloat(getValue(rpttable, "MVALUE")) > 0) {
																mback = UtilTool.parseFloat(mvalue)- UtilTool.parseFloat(mdbvalue)+ "";
															}
														}else {
															mback="0.00";//第一次的时候会为空，赋值0.00
														}
														
													}
												}
											}
										} else {
											mid = dev.split(",")[0];
											pid = dev.split(",")[1];
											orvalue = BaseUtil.rptgetDevvouValue(mid, pid);
										}
										dbvalue = getValue(rpttable, "ORVALUE");
										if ("0".equals(orvalue) || "0.0".equals(orvalue) || "0.00".equals(orvalue)) {
											orback = "0";
										} else {
											if(dbvalue!=null) {
												if (UtilTool.parseFloat(orvalue)- UtilTool.parseFloat(dbvalue) > 0) {
													orback = UtilTool.parseFloat(orvalue) - UtilTool.parseFloat(dbvalue)+ "";
												}
											}else {
												orback="0.00"; //第一次的时候会为空，赋值0.00
											}
										}
										if (orback.equals("0") && mback.equals("0")) {
											value = null;
										} else {
											value = UtilTool.parseFloat(orback) + UtilTool.parseFloat(mback) + "";
										}
										if (BaseUtil.isNotNull(value)) {
											putData(rpttable, now, mid, pid,UtilTool.cutFloat2(UtilTool.parseFloat(value) + ""), i, orvalue,mvalue);
											putData(daytable, now, mid, pid,UtilTool.cutFloat2(UtilTool.parseFloat(value) + ""), i, orvalue,mvalue);
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

	public String getValue(String tablename, String key) throws Exception {
		String sql = "select * from %s ORDER BY time DESC LIMIT 0,1";
		sql = String.format(sql, tablename);
		List<Map<String, Object>> list = baseservice.getSqlList(sql);
		if (list.size() > 0) {
			if(list.get(0).get(key)!=null&&!list.get(0).get(key).equals("")&&!list.get(0).get(key).equals("0")&&!list.get(0).get(key).equals("0.0")&&!list.get(0).get(key).equals("0.00")) {
				return list.get(0).get(key) + "";
			}
		}
		return null;
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
	 * @param today
	 *            今天的时间，抽取今天用电量用
	 * @return
	 * @throws Exception
	 */
	public String getValue(String nowBefore, String now, String devid, String pointid, String today, String devtable)
			throws Exception {

		String MAXsql = "select COALESCE(MAX(convert(value,decimal(10,2))),'0') as value from " + devtable
				+ " WHERE time >= '" + nowBefore + "' and time <= '" + now + "' and mgrobjid='" + devid
				+ "' and pointid='" + pointid + "'";
		List<Map<String, Object>> maxlist = baseservice.getSqlListS(MAXsql, DB.HIS);
		String MINsql = "select COALESCE(MIN(convert(value,decimal(10,2))),'0') as value from " + devtable
				+ " WHERE time >= '" + nowBefore + "' and time <= '" + now + "' and mgrobjid='" + devid
				+ "' and pointid='" + pointid + "'";
		List<Map<String, Object>> minlist = baseservice.getSqlListS(MINsql, DB.HIS);
		if (maxlist.size() > 0 && maxlist.get(0).get("VALUE") != null && !maxlist.get(0).get("VALUE").equals("0")) {
			String maxvalue = maxlist.get(0).get("VALUE") + "";
			if (minlist.size() > 0 && minlist.get(0).get("VALUE") != null && !minlist.get(0).get("VALUE").equals("0")) {
				String minvalue = minlist.get(0).get("VALUE") + "";
				String val = UtilTool.cutFloat2(UtilTool.parseFloat(maxvalue) - UtilTool.parseFloat(minvalue) + "");
				if (!val.equals("0") && !val.equals("0.00")) {
					return val;
				} else {
					return null;
				}
			}

		}
		return null;
	}

	public void setTodayValue(String val, String today, String devid, String pointid) throws Exception {
		String checksql = "select * from todayPower WHERE time='" + today + "' and mgrobjid='" + devid
				+ "' and pointid='" + pointid + "'";
		List<Map<String, Object>> list = baseservice.getSqlListS(checksql, DB.HIS);
		System.out.println("devid:" + devid + "，pointid:" + pointid);
		if (list.size() > 0 && list.get(0).get("ID") != null) {
			System.out.println("UPDATE:" + devid + "_" + pointid);
			String UPDATE_TABLE = "UPDATE todayPower SET `VALUE`='" + val + "' WHERE time='" + today
					+ "' and mgrobjid='" + devid + "' and pointid='" + pointid + "'";
			baseservice.exeSql(UPDATE_TABLE, DB.HIS);
		} else {
			System.out.println("insert:" + devid + "_" + pointid);
			String INSERT_TABLE = "INSERT INTO todayPower (`MGROBJID`, `POINTID`,`TIME`, `VALUE`) VALUES ('" + devid
					+ "','" + pointid + "','" + today + "','" + val + "')";
			baseservice.exeSql(INSERT_TABLE, DB.HIS);
		}
	}

	public void putData(String rpttable, String now, String mid, String pid, String value, int i, String orvalue,
			String mvalue) throws Exception {
		IBaseService baseservice = new BaseServiceImpl();
		int num = BaseUtil.floorNum();// 获取有几栋楼
		String arr[] = new String[num];
		for (int n = 0; n < num; n++) {
			arr[n] = "0";
		}
		arr[i] = value;
		String values = "('" + mid + "','" + pid + "','" + value + "','" + now + "','" + arr[i] + "','" + orvalue
				+ "','" + mvalue + "','";
		String invalue = "";
		for (int m = 0; m < num; m++) {
			if (m == num - 1) {
				values += arr[m] + "')";
			} else {
				values += arr[m] + "','";
			}
			invalue += ",value" + m;
		}
		String INSERT_RPT_TABLE = "INSERT INTO %s (MGROBJID, POINTID, VALUE,TIME,ALLVALUE,ORVALUE,MVALUE %s) VALUES ";
		if (mid.equals("801811_801710") && pid.equals("3050618001_3082318043")) {
			System.out.println(String.format(INSERT_RPT_TABLE, rpttable, invalue) + values);
		}
		baseservice.exeSql(String.format(INSERT_RPT_TABLE, rpttable, invalue) + values, DB.HIS);
	}

}
