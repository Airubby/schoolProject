package com.loncom.ismac.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.logs.Logs;
import com.loncom.ismac.service.IBaseService;
import com.loncom.ismac.service.impl.BaseServiceImpl;
import com.loncom.ismac.util.CMD;
import com.loncom.ismac.util.UtilTime;
@SuppressWarnings({"unused","rawtypes","unchecked"})
public class DelTablesQuartz implements Job {
     Object obj=null;
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		 obj=new DelTablesQuartz();
		try {
			task();
		} catch (Exception e) {
			Logs.SysLog("系统", "系统", "执行删除报表任务出错"+e.getMessage(), "DelTablesQuartz","127.0.0.1");
		}//启动任务
	}

	
	public void task() throws Exception{
		IBaseService service = new BaseServiceImpl();
		List<String> hisList = new ArrayList<>();
		List<String> rptList = new ArrayList<>();
		
		int hisdataday = Integer.parseInt(AppContext.getPropSet("RptDay"));
		String sql = String.format(CMD.SELECT_DBTABLES, "note_xw");
		List<Map<String, Object>> sqlList = service.getSqlList(sql);
		for (Map map : sqlList) {
			Iterator<Map.Entry<Object, Object>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Object, Object> entry = it.next();
				String tableName = entry.getValue().toString();
				if (tableName.contains("hisdev")) {
					hisList.add(tableName);
				} else if (tableName.contains("rpt") && !tableName.contains("_")) {
					rptList.add(tableName);
				}
			}
		}
		//操作删除HIS 表
		if(hisList.size()>0) {
			for (int i = 0; i < hisList.size(); i++) {
				String sourDate = UtilTime.strToDateFormat(hisList.get(i).substring(6));
				int intervalDays = UtilTime.intervalDays(sourDate, UtilTime.getNowYMD());
				if (intervalDays == hisdataday) {
					service.exeSql(String.format(CMD.DELETE_DBTABLES, hisList.get(i)), DB.SYS);
				}
			}
		}
		//操作删除RPT表里面的数据
		if(rptList.size()>0) {
			for (int i = 0; i < rptList.size(); i++) {
				String expiredDate = UtilTime.expiredDate(new Date(), hisdataday);
				service.exeSql(String.format(CMD.DELETE_DBTABLESDATA, rptList.get(i), expiredDate + CMD.BEGINOFDAY,
						expiredDate + CMD.ENDOFDAY), DB.SYS);
			}
		}
	}
}
