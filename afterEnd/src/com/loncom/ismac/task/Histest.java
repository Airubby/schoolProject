package com.loncom.ismac.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.bean.DataPack;
import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.service.IBaseService;
import com.loncom.ismac.service.impl.BaseServiceImpl;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.CMD;
import com.loncom.ismac.util.UtilTime;

public class Histest implements Job {
	
	private static int comitcount = 37;
	
	Object obj = null;
	IBaseService baseservice = new BaseServiceImpl();

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		obj = new Histest();
		try {
			task();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void task() throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("test写历史数据");
		DataPack datapack=new DataPack();
		comitcount+=1;
		datapack.setAgentbm("1");
		datapack.setMgrobjid("801391");
		datapack.setPropertyId("3050618001");
		datapack.setKey(comitcount+"");
		datapack.setDatachar("1231");
		datapack.setValue(comitcount+"");
		if(comitcount%10==0) {
			datapack.setMarktime("");
		}else {
			datapack.setMarktime(UtilTime.getNow());
		}
		AppContext.hisQueueVouData.put(datapack);
//		System.out.println(AppContext.hisQueueVouData.size());
		
		

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
	public String getValue( String nowBefore, String now, String devid, String pointid)
			throws Exception {
		IBaseService baseservice = new BaseServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String devtable="";
		String value="";
		Date nowDate=sdf.parse(now);
		Date beforeDate=sdf.parse(nowBefore);
//		if(UtilTime.getYMD(nowDate).equals(UtilTime.getYMD(beforeDate))) {//两个时间是同一天
//			value=checkValue(beforeDate,nowBefore,devid,pointid);
//		}else { //两个时间不是同一天
//			value=checkValue(beforeDate,nowBefore,devid,pointid);
//		}
		value=checkValue(beforeDate,nowBefore,devid,pointid);
		devtable = String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD(nowDate));
		int devcount = baseservice.getCount(String.format(CMD.IS_HIS_BASE, devtable), DB.HIS);
		if(devcount>0) {
			String sql="select COALESCE(MAX(VALUE),'0') as value from "
					+ devtable + " WHERE time >= '"+ nowBefore + "' and time <= '"+ now + "' and mgrobjid='" + devid + "' and pointid='" + pointid+"'";
			List<Map<String, Object>> checklist = baseservice.getSqlListS(sql, DB.HIS);
			if(checklist.size()>0 && !checklist.get(0).get("VALUE").equals("0")&&BaseUtil.isNotNull(checklist.get(0).get("VALUE")+"")) {
				String nowvalue = checklist.get(0).get("VALUE") + "";
				return Float.parseFloat(nowvalue)-Float.parseFloat(value)+"";
			}
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
