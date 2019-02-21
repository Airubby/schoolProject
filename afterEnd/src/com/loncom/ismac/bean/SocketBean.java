package com.loncom.ismac.bean;

import java.net.Socket;

/**
 * Socket通讯实体类
 * @author youtao
 *
 */
public class SocketBean  {
	protected  int statusCode = 0;// socket状态码，0未初始化,1正在初始化，2已经连接，3断开,4销毁  5停用
	
	protected String  socketconnecttimeout; // 连接超时，毫秒
	protected  int reConnectTime;//重连时间，毫秒
	protected  Socket socket = null;
	protected Thread threadobj=null;
	protected String ip; //Ip地址
	protected String port; //port端口号
	protected String port1; //port端口号
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public int getReConnectTime() {
		return reConnectTime;
	}
	public void setReConnectTime(String reConnectTime) {
		this.reConnectTime = Integer.parseInt(reConnectTime);
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public Thread getThreadobj() {
		return threadobj;
	}
	public void setThreadobj(Thread threadobj) {
		this.threadobj = threadobj;
	}
	public void setReConnectTime(int reConnectTime) {
		this.reConnectTime = reConnectTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getPort1() {
		return port1;
	}
	public void setPort1(String port1) {
		this.port1 = port1;
	}
	public String getSocketconnecttimeout() {
		return socketconnecttimeout;
	}
	public void setSocketconnecttimeout(String socketconnecttimeout) {
		this.socketconnecttimeout = socketconnecttimeout;
	}
	
}
