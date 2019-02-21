package com.loncom.ismac.logs;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.jdbc.DBAccess;
import com.loncom.ismac.util.UtilTime;
import com.loncom.ismac.util.UtilTool;



/**
 * Project: lonweb Version:1.0 Package: com.lon.log File: Log.java
 * 
 * Functional Description:
 * 
 * Notes
 * 
 * Revision History create by leijun, 2013-12-20 上午11:40:50
 * 
 * lon, Inc. Copyright (C): 2013
 */
public class Logs {
	private static Log sysLog = LogFactory.getLog(SysException.class);// 系统日志
	private static Log asynLog = LogFactory.getLog(AsynEDIException.class);// 异步报文接口日志
	private static Log syncLog = LogFactory.getLog(SyncEDIException.class);// 同步报文接口日志

	/**
	 * 写同步报文日志
	 * 
	 * @param message
	 */
	public static void logsync(String message) {
		syncLog.debug(message);
	}

	/**
	 * 写异步报文日志
	 * 
	 * @param message
	 */
	public static void logasyn(String message) {
		asynLog.debug(message);
	}

	/**
	 * 写系统异常日志
	 * 
	 * @param message
	 */
	public static void log(String message) {
		sysLog.debug(message);
	}

	/**
	 * 写异常日志
	 * 
	 * @param e
	 */
	public static void log(Exception e) {
		sysLog.debug(transformException(e));
	}

	public static void main(String[] args) {
		try {
			try {
				for (int i = 0; i < 100; i++) {
					System.out.println(UtilTime.ymdhms_f.parse("2015-1-31 23:59:59").getTime()+":"+UtilTime.ymdhms_f.parse("2015-01-31 23:59:59").getTime());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 从异常对象中抽取日志信息
	 * 
	 * @param e
	 * @return
	 */
	public static String transformException(Throwable e) {
		try {
			String result = String
					.format(
							"Exception Message: At %1$tY-%1$tm-%1$te %1$tH:%1$tM:%1$tS =>Error Type: %2$s",
							new Date(), e.toString()+"\n");
			StackTraceElement[] stackTrace = e.getStackTrace();
			for(StackTraceElement item:stackTrace){
				result += " =>" + item.toString() + "\n";
			}
			if (e.getCause() != null)
				result += " =>Caused By: " + e.getCause().toString();
			return result;
		} catch (Exception ex) {
			return e.getMessage();
		}
	}

	// 写操作日志
	public static void writeOptLog( String optcmd, String opttitle,
			String optcontents) {
		DBAccess dba = new DBAccess(DB.SYS);
		try {
			String sqllog = "insert into m_operatelog(optcmd,opttitle,optcontent,opttime) values(?,?,?,?)";
			dba.preExecuteUpdate(sqllog, new Object[] { optcmd, opttitle,
					optcontents, UtilTime.getNow() });
		} catch (Exception e) {
			Logs.log(e);
		} finally {
			dba.close();
		}
	}
	/**
	 * 日志记录
	 * @param openid 操作名称
	 * @param userid 用户名称
	 * @param details 操作详情
	 * @param interfaceurl 访问接口路径
	 */
	
	public static void SysLog(String openid,String userid,String details,String interfaceurl,String ip){
		DBAccess dba = new DBAccess(DB.SYS);
		try {
			String sqllog = "insert into SYSLOG (OPE_ID,USERID,DETAILS,CREATETIME,INTERFACEURL,IP) values (?,?,?,?,?,?)";
			dba.preExecuteUpdate(sqllog, new Object[] { openid, userid,
					details, UtilTime.getNow(),interfaceurl,ip });
		} catch (Exception e) {
			Logs.log(e);
		} finally {
			dba.close();
		}
		
	}

	// 写异常日志
	public static void writeErrLog( String optcmd, String opttitle,
			Exception e) {
		DBAccess dba = new DBAccess(DB.SYS);
		try {
			String errmsg = transformException(e);
			if(UtilTool.isNotNull(errmsg)&&errmsg.length()>1000)
				errmsg=errmsg.substring(0,1000);
			String sqllog = "insert into m_errlog(type,optcmd,opttitle,errmsg,errtime) values(?,?,?,?,?)";
			dba.preExecuteUpdate(sqllog, new Object[] { "mdcweb",
					UtilTool.obj2Str(optcmd), UtilTool.obj2Str(opttitle),
					errmsg, UtilTime.getNow() });
		} catch (Exception e0) {
			System.out.println("写入异常日志失败！");
			Logs.log(e0);
		} finally {
			dba.close();
		}
	}

	// 写异常日志
	public static void writeErrLog(Exception e) {
		DBAccess dba = new DBAccess(DB.SYS);
		try {
			String errmsg = transformException(e);
			if(UtilTool.isNotNull(errmsg)&&errmsg.length()>1000)
				errmsg=errmsg.substring(0,1000);
			String sqllog = "insert into m_errlog(type,errmsg,errtime) values(?,?,?)";
			dba.preExecuteUpdate(sqllog, new Object[] { "mdcweb", errmsg,
					UtilTime.getNow() });
		} catch (Exception e0) {
			System.out.println("写入异常日志失败！");
			Logs.log(e0);
		} finally {
			dba.close();
		}
	}

	// 写异常日志
	public static void writeErrLog(String opttitle, Throwable e) {
		DBAccess dba = new DBAccess(DB.SYS);
		try {
			String errmsg = transformException(e);
			if(UtilTool.isNotNull(errmsg)&&errmsg.length()>1000)
				errmsg=errmsg.substring(0,1000);
			String sqllog = "insert into m_errlog(type,opttitle,errmsg,errtime) values(?,?,?,?)";
			dba.preExecuteUpdate(sqllog, new Object[] { "mdcweb", opttitle,
					errmsg, UtilTime.getNow() });
		} catch (Exception e0) {
			System.out.println("写入异常日志失败！");
			Logs.log(e0);
		} finally {
			dba.close();
		}
	}
}
