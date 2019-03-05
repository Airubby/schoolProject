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

public class RptDevQuartz implements Job{
	Object obj=null;
	IBaseService baseservice= new BaseServiceImpl();
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		obj=new RptDevQuartz();
		try {
			task();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void task() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("执行了抽取报表");
		String sql;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now=UtilTime.getNow();
//		String now ="2019-02-28 17:35:00";
		Date date=sdf.parse(now);
		String nowBefore=UtilTime.getNowBeforeMin(date, -10);
		String devtable=String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD());
		
		
		List<ClassroomXml> classlist=new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist=new ArrayList<ClassroomXml>();
		IBaseService baseservice= new BaseServiceImpl();
		String rpttable="";
		int num=0;
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				num+=1;
				classlist=BaseUtil.getService(groupXml.getGroupno(), "classroom", null, null);
				dormlist=BaseUtil.getService(groupXml.getGroupno(), "officeroom", null, null);
				for (ClassroomXml classroomXml : classlist) {
					rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
					int count=baseservice.getCount(String.format(CMD.IS_HIS_BASE, rpttable), DB.HIS);
					if(count<=0) {
						baseservice.exeSql(String.format(CMD.CREATE_RPT_DEV, rpttable), DB.HIS);
					}
					String devid=classroomXml.getAmmeterdev().getDevid();
					String pointid=classroomXml.getAmmeterdev().getStateid();
					sql="select id,mgrobjid,pointid,time, avg(value) as value from %s "
							+ "where time >= '%s' and time < '%s' and mgrobjid='%s' and pointid='%s'"
							+ "group by mgrobjid,pointid";
					sql=String.format(sql,devtable,nowBefore,now,devid,pointid);
					try {
						List<Map<String, Object>> list = baseservice.getSqlListS(sql,DB.HIS);
						String values="";
						if (list != null && list.size() > 0) {
							for (Map<String, Object> map : list) {
								if(num==1) {
									values+="('"+map.get("MGROBJID")+"','"+map.get("POINTID")+"','"+map.get("VALUE")+"','"+now+"','"+map.get("VALUE")+"','0','"+map.get("VALUE")+"'),";
								}else {
									values+="('"+map.get("MGROBJID")+"','"+map.get("POINTID")+"','"+map.get("VALUE")+"','"+now+"','0','"+map.get("VALUE")+"','"+map.get("VALUE")+"'),";
								}
							}
							values=values.substring(0, values.length()-1);
							System.out.println(values);
							baseservice.exeSql(String.format(CMD.INSERT_RPT_TABLE, rpttable)+values, DB.HIS);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				for (ClassroomXml classroomXml : dormlist) {
					rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
					int count=baseservice.getCount(String.format(CMD.IS_HIS_BASE, rpttable), DB.HIS);
					if(count<=0) {
						baseservice.exeSql(String.format(CMD.CREATE_RPT_DEV, rpttable), DB.HIS);
					}
					String devid=classroomXml.getAmmeterdev().getDevid();
					String pointid=classroomXml.getAmmeterdev().getStateid();
					sql="select id,mgrobjid,pointid,time, avg(value) as value from %s "
							+ "where time >= '%s' and time < '%s' and mgrobjid='%s' and pointid='%s'"
							+ "group by mgrobjid,pointid";
					sql=String.format(sql,devtable,nowBefore,now,devid,pointid);
					try {
						List<Map<String, Object>> list = baseservice.getSqlListS(sql,DB.HIS);
						String values="";
						if (list != null && list.size() > 0) {
							for (Map<String, Object> map : list) {
								if(num==1) {
									values+="('"+map.get("MGROBJID")+"','"+map.get("POINTID")+"','"+map.get("VALUE")+"','"+now+"','"+map.get("VALUE")+"','0','"+map.get("VALUE")+"'),";
								}else {
									values+="('"+map.get("MGROBJID")+"','"+map.get("POINTID")+"','"+map.get("VALUE")+"','"+now+"','0','"+map.get("VALUE")+"','"+map.get("VALUE")+"'),";
								}
							}
							values=values.substring(0, values.length()-1);
							System.out.println(values);
							baseservice.exeSql(String.format(CMD.INSERT_RPT_TABLE, rpttable)+values, DB.HIS);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
		
		
//		String sql;
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String now=UtilTime.getNow();
////		String now ="2019-02-28 17:35:00";
//		Date date=sdf.parse(now);
//		String nowBefore=UtilTime.getNowBeforeMin(date, -10);
//		String devtable=String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD());
//		String rpttable=String.format(CMD.RPT_DEVTABLE, UtilTime.getYMD());
//		int count=baseservice.getCount(String.format(CMD.IS_HIS_BASE, rpttable), DB.HIS);
//		if(count<=0) {
//			baseservice.exeSql(String.format(CMD.CREATE_RPT_DEV, rpttable),DB.HIS);
//		}
//		sql="select id,mgrobjid,pointid,time, avg(value) as value from %s where time >= '%s' and time < '%s' group by mgrobjid,pointid";
//		sql=String.format(sql,devtable,nowBefore,now);
//		try {
//			List<Map<String, Object>> list = baseservice.getSqlListS(sql,DB.HIS);
//			String values="";
//			if (list != null && list.size() > 0) {
//				for (Map<String, Object> map : list) {
//					values+="('"+map.get("MGROBJID")+"','"+map.get("POINTID")+"','"+map.get("VALUE")+"','"+now+"'),";
//				}
//				values=values.substring(0, values.length()-1);
//				System.out.println(values);
//				baseservice.exeSql(String.format(CMD.INSERT_RPT_TABLE, rpttable)+values, DB.HIS);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
	}

}
