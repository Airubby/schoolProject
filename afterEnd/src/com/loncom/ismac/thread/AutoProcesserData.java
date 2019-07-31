package com.loncom.ismac.thread;

import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.util.BaseUtil;

/**
 * Project: lonweb Version:1.0 Package: thread File: Processer.java
 * 
 * Functional Description:
 * 
 * Notes 告警处理
 * 
 * Revision History create by leijun, 2013-12-20 上午11:22:09
 * 
 * lon, Inc. Copyright (C): 2013
 */
public class AutoProcesserData extends Thread {
	public static boolean isRunning = true;
	/**
	 * 
	 */
	public AutoProcesserData() {
		System.out.println("数据处理线程创建");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (isRunning) {
			try { 
			
				Object xml =AppContext.dataQueueVouData.take();
				if(xml instanceof byte[]) {
					BaseUtil.dataAnalysis((byte[]) xml);
				}
				//System.out.println("报文数量"+AppContext.dataQueueVouData.size());
				if( AppContext.dataQueueVouData.size()<100 ){//当数据小于100 释放一次CPU资源
					Thread.sleep(10);//先释放资源，避免cpu占用过高
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
