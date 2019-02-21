package com.loncom.ismac.soket.service;

import java.net.Socket;

import com.loncom.ismac.lservice.bean.Service;

/**
 * TCP协议适配层
 * @author youtao
 *
 */
public interface ISocket  {
	/**
	 * 初始化连接
	 * @return 
	 */
	 Thread initConnect(Service object);
	 
	 /**
	  * 发送数据,如果网络连接异常，则重新初始化连接
	  */
	 String sendData(String xml , Service service) throws Exception;
	 
	 /**
	  * 收到数据
	  * @param object
	  * @return
	  * @throws Exception
	  */
	 void subMessage(Service object) ;
	 
	 /**
	  * 暂定Socket连接
	  */
	 void stopSocket();
	 
	 /**
	  * 启动Socket连接
	  */
	 void startSocket();
	 
	 /**
	  * 获取服务线程
	  * @return
	  */
	 Thread getSocketThread();
	 
	 /**
	  * 获取服务Sokcet
	  * @return
	  */
	 Socket getSerivceSocket();
	 
	 /**
	  * 销毁服务
	  */
	 void destroy();
	 
	 
	 /**
	  * 关闭所有Soket连接
	  */
	 void closeSocket();
	 
	 
}
