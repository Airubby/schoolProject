package com.loncom.ismac.servlet;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.loncom.ismac.application.AppContext;

/**
 * 龙控一体化微型控制器内容启动管理器
 * 
 * @author Administrator
 * 
 */
public class LonServletContent implements ServletContextListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @seejavax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		//AppContext.destory();
		try {
			// DriverManager.deregisterDriver(DriverManager.getDriver("org.sqlite.JDBC"));
			// DriverManager.deregisterDriver(DriverManager.getDriver("com.mysql.jdbc.Driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		//
		AppContext.webroot = sce.getServletContext()
				.getRealPath(File.separator);
		try {
			AppContext.sysInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("初始化上下文");
	}

}
