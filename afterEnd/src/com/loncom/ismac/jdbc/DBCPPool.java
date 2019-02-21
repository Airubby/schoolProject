package com.loncom.ismac.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import com.loncom.ismac.logs.Logs;
import com.loncom.ismac.util.UtilTool;
import com.sun.istack.internal.logging.Logger;

public class DBCPPool {
	static Logger logger = Logger.getLogger(DBCPPool.class);
	private static Map<String, BasicDataSource> dataSources = new HashMap<String, BasicDataSource>();

	public DBCPPool() {
	}

	public static void init() {
		try {
			init_dataSource("sys");
			init_dataSource("report");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void destory(){
		try {
			for(Map.Entry<String,BasicDataSource> entity:dataSources.entrySet()){
				if(!entity.getValue().isClosed()){
					entity.getValue().close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void init_dataSource(String sourceFlag) throws Exception {
		BasicDataSource dataSource = null;
		String driverClassName = UtilTool.getProperty(sourceFlag
				+ "_driverClass");
		Properties p = new Properties();
		p.setProperty("driverClassName", driverClassName);
		p.setProperty("url", UtilTool.getProperty(sourceFlag + "_url"));

		if (!"org.sqlite.JDBC".equals(driverClassName)) {
			// sqlite数据库不需要设置用户名密码
			p.setProperty("password", UtilTool.getProperty(sourceFlag
					+ "_password"));
			p.setProperty("username", UtilTool.getProperty(sourceFlag
					+ "_username"));
		}else{
			System.setProperty("sqlite.purejava", "true");
		}
		p.setProperty("maxActive", UtilTool.getProperty("maxActive"));
		p.setProperty("maxIdle", UtilTool.getProperty("maxIdle"));
		p.setProperty("maxWait", UtilTool.getProperty("maxWait"));
		p.setProperty("removeAbandonedTimeout", UtilTool
				.getProperty("removeAbandonedTimeout"));
		p.setProperty("removeAbandoned", UtilTool
				.getProperty("removeAbandoned"));
		p.setProperty("characterEncoding", "UTF-8");
		p.setProperty("useUnicode", "true");
		//<property name= "testOnBorrow" value="true" />    
        //<property name= "testOnReturn" value="false" />    
        //<property name= "validationQuery" value="select 1" /
		p.setProperty("testOnBorrow", "true");
		p.setProperty("testOnReturn", "false");
		p.setProperty("validationQuery", "select 1");
		dataSource = (BasicDataSource) BasicDataSourceFactory
				.createDataSource(p);
		dataSources.put(sourceFlag, dataSource);
	}

	public static synchronized Connection getConnection(String sourceFlag) {
		try {
			if (!dataSources.containsKey(sourceFlag)) {
				init_dataSource(sourceFlag);
			}
			BasicDataSource dataSource = dataSources.get(sourceFlag);
			Connection conn = null;
			if (dataSource != null) {
				conn = dataSource.getConnection();
			}
			return conn;
		} catch (Exception e) {
			Logs.log("获取数据库连接失败:"+e.getLocalizedMessage());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			test();
		}
	}

	public static void test() {
		Connection conn = null;
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		try {
			conn = DBCPPool.getConnection(DB.SYS);
			System.out.println(conn.hashCode());
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select getdate()");
			while (rs.next()) {
				// System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
