package com.loncom.ismac.util;

/**
 * Project: lonweb Version:1.0 Package: com.lon.util File: CMD.java
 * 
 * Functional Description: 实时请求报文命令标志，用于区分不同命令，调用不同服务接口 Notes
 * 
 * Revision History create by leijun, 2013-12-19 上午11:18:39
 * 
 * lon, Inc. Copyright (C): 2013
 */
public class CMD {
	public static String syncGetSysStatus = "getSysStatus";
	public static String syncGetAllData = "getAllData";
	public static String syncGetAllAlarm = "getAllAlarm";
	public static String asynSubAllData = "subAllData";
	public static String asynSubAllAlarm = "subAllAlarm";
	public static String syncCfmOneAlarm = "cfmOneAlarm";// 确认一条报警
	public static String eventQuery = "eventQuery";// 告警信息查询
	public static String securitysetting = "securitysetting";// 安全设置

	public static String loadmpuehis = "loadmpuehis";// 加载最近4小时的mpue历史数据
	public static String getrealmpue = "getrealmpue";// 获取实时mpue值
	public static String getitpower = "getitpower";// 加载it功耗
	public static String getcoolchanneltemp = "getcoolchanneltemp";// 加载冷通道温度信息
	public static String getbatterybardata = "getbatterybardata";// 加载hbdc电池组状态图数据
	public static String getcabinetinfo = "getcabinetinfo";// 加载机柜信息
	public static String getcabinetalarminfo = "getcabinetalarminfo";// 加载机柜告警位图信息
	public static String geteventhis = "geteventhis";// 获取告警历史记录
	public static String getreportdata = "getreportdata";// 加载历史曲线数据
	public static String SELECT = "select";
	public static String UPDATE = "update";
	public static String INSERT = "insert";
	public static String DELETE = "delete";
	public static String GETERRACCESS = "geterraccess";// 获取门禁异常数据
	public static String GETACCESSJURISDICTION = "getaccessjurisdiction";// 获取门禁权限
	public static String ADDACCESSJURISDICTION = "addaccessjurisdiction";// 添加和修改
	public static String DELETEACCESSJURISDICTION = "deleteaccessjurisdiction";// 删除
	public static String GETTIMEDETAIL = "gettimedetail";
	public static String DELETEACCESSTIMEGOUNP = "deleteaccesstimegounp";

	public static String VIDEOCODE = "002%s";
	public static String FCODE = "003%s";

	public static int PAGE_SIZE = 20;

	public static int UUIDNUMBER = 5;

	public final static String IS_HIS_BASE="select count(1) from information_schema.tables where table_schema='ismacnx' and table_name='%s'";
	public final static String HIS_DEVTABLE = "HISDEV%s";
	public final static String HIS_DEVINSERT = "INSERT INTO %s (MGROBJID, POINTID, VALUE,TIME) VALUES ";// 历史表新增格式模板

	public final static String HIS_DEVINSERT_VALUES = " ('%s','%s','%s','%s'),";// 历史表新增数据模板

	public final static String CREATE_HIS_DEV=" CREATE TABLE %s ( "+
    		"  `id` int(11) NOT NULL AUTO_INCREMENT,"+
    		"  `mgrobjid` varchar(50) DEFAULT NULL,"+
    		"  `pointid` varchar(50) DEFAULT NULL,"+
    		"  `value` varchar(20) DEFAULT NULL,"+
    		"  `time` datetime DEFAULT NULL,"+
    	"	  PRIMARY KEY (`id`)"+
    	"	)";
	// 历史能效
	public final static String HIS_ENERGY_HEAD = "INSERT INTO %s (NAME, VALUE, HDATA,ENERGYID) VALUES ";
	public final static String HIS_ENERGY_CONTENT = " ('%s','%s','%s','%s','%s'),";
	public final static String HIS_ENERGY_CREATE = "CREATE TABLE %s (" + " ID INTEGER IDENTITY(1, 1) DEFAULT NULL,"
			+ " NAME VARCHAR(50) DEFAULT NULL," + " VALUE  VARCHAR(50) DEFAULT NULL," + " HDATA  datetime DEFAULT NULL,"
			+ " ENERGYID VARCHAR(50) DEFAULT NULL" + ")";
	public final static String HIS_ENERGY_TABLE = "HISENERGYDEV";

	public final static String HIS_PUE = "HIS_PUE";
	public final static String HIS_CREATE = "CREATE TABLE LONCOM.HIS_PUE (" + " ID INTEGER IDENTITY(1, 1) NOT NULL  ,"
			+ "  ENERGYID VARCHAR(255) DEFAULT NULL," + " PUE VARCHAR(255) DEFAULT NULL,"
			+ "  HDATE datetime DEFAULT NULL," + "  NOT CLUSTER PRIMARY KEY  (ID)" + ")";
	public final static String HIS_PRICE = "HIS_PRICE";
	public final static String CREATE_HHIS_PRICE = "CREATE TABLE LONCOM.HIS_PRICE ("
			+ " ID INTEGER IDENTITY(1, 1) NOT NULL  ," + " ENERGY VARCHAR(11) DEFAULT NULL,"
			+ " UNITPRICE VARCHAR(11) DEFAULT NULL," + " TITME VARCHAR(255) DEFAULT NULL,"
			+ " ENERGYID VARCHAR(50) DEFAULT NULL," + " NOT CLUSTER PRIMARY KEY  (ID)" + ")";
	
	// Rpt设备历史
	public final static String RPT_DEVTABLE = "RPT%s";
	public final static String RPT_COLUMN = " %s VARCHAR(50) DEFAULT NULL,";
//	public final static String CREATE_RPT_DEV = " CREATE TABLE %s ( " + "  ID INTEGER IDENTITY(1, 1) NOT NULL ,"
//			+ "  UUID VARCHAR(50) DEFAULT NULL," + "  HDATA VARCHAR(50) DEFAULT NULL," + "%s"
//			+ "	  NOT CLUSTER PRIMARY KEY  (ID)" + "	)";
	public final static String CREATE_RPT_DEV=" CREATE TABLE %s ( "+
    		"  `id` int(11) NOT NULL AUTO_INCREMENT,"+
    		"  `mgrobjid` varchar(50) DEFAULT NULL,"+
    		"  `pointid` varchar(50) DEFAULT NULL,"+
    		"  `value` varchar(20) DEFAULT NULL,"+
    		"  `time` datetime DEFAULT NULL,"+
    		"  `onevalue` varchar(20) DEFAULT NULL,"+
    		"  `twovalue` varchar(20) DEFAULT NULL,"+
    		"  `allvalue` varchar(20) DEFAULT NULL,"+
    	"	  PRIMARY KEY (`id`)"+
    	"	)";

	public final static String INSERT_RPT_TABLE = "INSERT INTO %s (MGROBJID, POINTID, VALUE,TIME,ONEVALUE,TWOVALUE,ALLVALUE) VALUES ";
	public final static String INSERT_RPT_VALUES = "('%s','%s',%s,%s,%s,%s,%s)";
	// 空调群控RPT报表

	public final static String IS_BASE = " select count(1) from `INFORMATION_SCHEMA`.`TABLES` where table_name ='%s'";
	public final static String AIR_RPTNAME = "RPT%s";
	public final static String AIR_NEW_RPT = "CREATE TABLE %s ( " + "  `ID` int(11) NOT NULL AUTO_INCREMENT ,"
			+ "  `uuid` varchar(50) DEFAULT NULL,"
			+ "  `hdata` varchar(50) DEFAULT NULL, `devid` varchar(50) DEFAULT NULL,"
			+ "  `state` varchar(50) DEFAULT NULL,"
			+ "  `stateonsec` int(11) DEFAULT NULL, `stateoffsec` int(11) DEFAULT NULL,`aironcount` int(11) DEFAULT NULL,"
			+ "	  PRIMARY KEY (`ID`)" + "	)";

	public final static String AIR_RPTADD = "INSERT INTO rpt%s ( `uuid`, `hdata`, `devid`, `state`, `stateonsec`, `stateoffsec`,`aironcount`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s','%s')";
	public final static String HIS_QUERYW = "select stateonsec from rpt%s where devid='%s' and hdata>='%s' and hdata<='%s' GROUP BY stateonsec DESC LIMIT 0,1";
	public final static String HIS_QUERYW_S = "select stateonsec from rpt%s where devid='%s'  and hdata<='%s' GROUP BY stateonsec DESC LIMIT 0,1";

	public final static String HIS_QUERYSTART = "SELECT hdata as TIME,AVG(%s) as VALUE FROM rpt%s WHERE hdata >= '%s' AND  hdata <= '%s' and  DATE_FORMAT(hdata, '%s') %s = 0 GROUP BY DATE_FORMAT(	hdata,		'%s')";

	public final static String USER_UPDATASTATE="UPDATE `user` SET  `state`='%s' WHERE id in(%s)";
	public final static String CMD_XML = "XML";
	public final static String CMD_MODIFY = "Modify";

	public final static String CMD_MODIFY_XML="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
	+"<root> "
			+" <Command type=\"Modify\"> "
			+" <Field name=\"%s\" value=\"%s\"/>"
	+"  </Command> "+"</root>";
	/**************************** END **********************************/
	// RPT报表记录
	public final static String INSERT_RPTCONFIG = "INSERT INTO LONCOM.REPCONFIG ( HDATE, RPTTABLE, NAME, REPROTID) VALUES ('%s','%s','%s','%s')";
	public final static String BEGINOFDAY = " 00:00:00";
	public final static String ENDOFDAY = " 23:59:59";
	public final static String INSERT_RPT = "INSERT INTO %s ( MGROBJID, POINTID, TIME, COLLECT, AVG, MAX, MIN, CFZ) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')";
	public final static String SELECT_IFNULL = "SELECT IFNULL((%s),0)";
	public final static String SELECT_REPORTCONFIG = "SELECT NAME FROM REPORTCONFIG WHERE REPORTCONFIG.ID = '%s'";
	public final static String SELECT_RPTORDERNOQJ = "SELECT %s FROM %s WHERE HDATA BETWEEN '%s' AND '%s' ";
	public final static String SELECT_RPTORDERNOJZ = "SELECT %s FROM %s WHERE HDATA BETWEEN '%s' AND '%s' ORDER BY %s %s limit 0,1";
	public final static String SELECT_RPTORDERNOCF = "SELECT %s FROM %s WHERE HDATA = '%s'";
	public final static String SELECT_DBTABLES = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='%s'";
	public final static String DELETE_DBTABLES = "DROP TABLE %s";
	public final static String DELETE_DBTABLESDATA = "DELETE FROM %s WHERE HDATA BETWEEN '%s' AND '%s'";
	public final static String SELECT_DAYRPTSUM = "SELECT SUM(p%s) FROM %s WHERE hdata>='%s' AND hdata<='%s'";
	public final static String SELECT_DAYRPTAVG = "SELECT AVG(p%s) FROM %s WHERE hdata>='%s' AND hdata<='%s'";
	public final static String SELECT_DAYRPTMIN = "SELECT MIN(p%s) FROM %s WHERE hdata>='%s' AND hdata<='%s'";
	public final static String SELECT_DAYRPTMAX = "SELECT MAX(p%s) FROM %s WHERE hdata>='%s' AND hdata<='%s'";
	public final static String SELECT_DAYRPTCFZ = "SELECT p%s FROM %s WHERE hdata='%s'";

	public final static String CREATE_RPT = " CREATE TABLE  %s ( " + "  ID INTEGER IDENTITY(1, 1) NOT NULL  ,"
			+ "  MGROBJID VARCHAR(50) DEFAULT NULL," + "  POINTID VARCHAR(50) DEFAULT NULL,"
			+ "  TIME VARCHAR(50) DEFAULT NULL," + "  COLLECT VARCHAR(50) DEFAULT NULL,"
			+ "  AVG VARCHAR(50) DEFAULT NULL," + "  MAX VARCHAR(50) DEFAULT NULL," + "  MIN VARCHAR(50) DEFAULT NULL,"
			+ "  CFZ VARCHAR(50) DEFAULT NULL," + "	NOT CLUSTER PRIMARY KEY  (ID)" + "	)";
	public static String ADD = "add";

	// 告警发送

	public final static String INSERT_ALARMSENDOUT = "INSERT INTO loncom.alarmsendout (USE_ID, CONTENT, OUTTIME, ISOUT, ORDERNO, TYPE) VALUES";

	public final static String INSERT_ALARMSENDOUT_VALUES = "('%s','%s', '%s', '%s', '%s', '%s')";

	// 获取时间
	public final static String BEGINTIME = "begintime";
	public final static String ENDTIME = "endtime";
	// TCP包
	public final static int TCP_ALLDATA = 0x00;// 全部数据
	public final static int TCP_REALTIME = 0x01;// 实时数据包
	public final static int TCP_COMDATA = 0x02;// 通讯数据包
	public final static int TCP_EVENTDATA = 0x03;// 事件数据包
	public final static int TCP_DEVTYPEDATA = 0x04;// 设备状态数据包
	public final static int TCP_HEARTBEATDATA = 0x11;// 心跳数据包
	public final static int TCP_UPDATEDATA = 0x21;// 属性变更
	public final static int TCP_VERSION = 0x0201;// 版本号
	public final static int TCP_STARTFLAG = 0xFFFFFFFF;// 开始标志;
	public final static int TCP_ENDFLAG = 0xEEEEEEEE;// 结束标识

	// Sokcet
	public final static String TCP_DATA = "DATA";
	public final static String TCP_OUT = "OUT";

	// 告警云盒配置
	/** 邮件发送地址 */
	public final static String CLOUDBOXEMAILURL = "%s://%s/api/alarmout?ApiKey=%s&action=email";
	/** 邮件配置设置地址 */
	public final static String CLOUDBOXEMAILSETTINGURL = "%s://%s/api/alarmout?ApiKey=%s&action=configure";
	/** 短信配置地址 */
	public final static String CLOUDBOXSMSURL = "%s://%s/api/alarmout?ApiKey=%s&action=sms";
	/** 语音发送地址 */
	public final static String CLOUDBOXTTSURL = "%s://%s/api/alarmout?ApiKey=%s&action=tts";
	/** 声光发送地址 */
	public final static String CLOUDBOXACOUSTOURL = "%s://%s/api/alarmout?ApiKey=%s&action=do";

	// 消息组件
	/** 邮件发送地址 */
	public final static String MESSAGECOMPONENTEMAILURL = "%s://%s:%s/mail/sendMail";
	public final static String MESSAGECOMPONENTSMSURL = "%s://%s:%s/sms/sendSMS";

	public static String LevelName(String key) {

		if ("1".equals(key)) {
			return "提示";
		} else if ("2".equals(key)) {
			return "一般";
		} else if ("3".equals(key)) {
			return "重要";
		} else if ("4".equals(key)) {
			return "紧急";
		}
		return "";

	}

	public static void main(String[] args) {
		String i = "";
		System.out.println(Float.parseFloat(i));
	}
}
