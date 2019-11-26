package com.loncom.ismac.task;

import java.util.Date;

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

public class MonthQuartz implements Job {
	IBaseService baseservice = new BaseServiceImpl();
	@Override
	public void execute(JobExecutionContext arg0) {
		String rpttable="";
		String monthtable= String.format(CMD.MONTH_DEVTABLE, UtilTime.getYM());
		String begintime = UtilTime.getBeginOfDay();
		String endtime = UtilTime.getEndtime(new Date());
		String sql="";
		StringBuffer buffer=new StringBuffer();
		try{
		int count = baseservice.getCount(String.format(CMD.IS_HIS_BASE, monthtable), DB.HIS);
		if (count <= 0) {
			baseservice.exeSql(String.format(CMD.MONTH_CREATE, monthtable));
		}
		for (Service service : AppContext.getService()) {
			buffer.setLength(0);
			for (int i = 0; i < service.getGroupcontrol().getGroup().size(); i++) {
				GroupXml groupXml = service.getGroupcontrol().getGroup().get(i);
				if (groupXml.getClassroomgroup().getItem() != null) {
					for (ClassroomXml classroomXml : groupXml.getClassroomgroup().getItem()) {
						rpttable = String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
						sql="SELECT mgrobjid,pointid,sum(convert(value,DECIMAL(10,3))) as `value` ,DATE_FORMAT(time,'%s') as `time`  from %s where   time>'%s' and time<'%s' UNION ALL ";
						sql=String.format(sql, "%Y-%m-%d 00:00:00",rpttable,begintime,endtime);
						buffer.append(sql);
					}
				}
				if (groupXml.getOfficegroup().getItem() != null) {
					for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
						if (!BaseUtil.isNotNull(classroomXml.getElement())) {
							rpttable = String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
							sql="SELECT mgrobjid,pointid,sum(convert(value,DECIMAL(10,3))) as `value` ,DATE_FORMAT(time,'%s') as `time`  from %s where   time>'%s' and time<'%s' UNION ALL ";
							sql=String.format(sql, "%Y-%m-%d 00:00:00",rpttable,begintime,endtime);
							buffer.append(sql);
						}
					}
				}
			}
			
			if(BaseUtil.isNotNull(buffer.toString())){
				buffer.delete(buffer.toString().length()-11, buffer.toString().length());
				sql=String.format(CMD.MONTH_INSERT, monthtable,buffer.toString());
				baseservice.exeSql(sql);
			}
			
		}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
