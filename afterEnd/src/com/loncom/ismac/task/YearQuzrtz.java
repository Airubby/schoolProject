package com.loncom.ismac.task;

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
/**
 * 年报表
 * @author youtao
 *
 */
public class YearQuzrtz implements Job {
	IBaseService baseservice = new BaseServiceImpl();
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		 String yeartable=String.format(CMD.YEAR_DEVTABLE, UtilTime.getY());//年报表的名称
		 String monthtable= String.format(CMD.MONTH_DEVTABLE, UtilTime.getYM());//抽取月报表名称
		 String sql="";
		 try {
			    int count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, yeartable), DB.HIS);
				if (count <= 0) {
					baseservice.exeSql(String.format(CMD.MONTH_CREATE, yeartable));
				}
			    sql=String.format(CMD.MONTH_INSERT, yeartable,"select mgrobjid,pointid,SUM(`value`) as `value`,DATE_FORMAT(time,'%Y-%m-01 00:00:00') as `time` from "+monthtable+" GROUP BY mgrobjid");
			    baseservice.exeSql(sql);
				
		} catch (Exception e) {
			e.printStackTrace();
 		}
	}

}
