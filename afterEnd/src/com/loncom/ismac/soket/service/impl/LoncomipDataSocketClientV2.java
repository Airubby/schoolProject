package com.loncom.ismac.soket.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.logs.Logs;
import com.loncom.ismac.lservice.bean.Service;
import com.loncom.ismac.util.BaseUtil;
@SuppressWarnings("unused")

public class LoncomipDataSocketClientV2 extends BaseSocketClient {
	public static int statusCode = 0;// socket状态码，0未初始化,1正在初始化，2已经连接，3断开,4销毁
	private static String ip = null;
	private static int port;
	private static int socketConnectTimeOut;// 连接超时，毫秒
	private static int reConnectTime;//重连时间，毫秒
	private static Socket socket = null;
	private static PrintWriter out = null;
	private static InputStream in = null;
	 

	// 初始化连接
	public  Thread initConnect(final Service object) {
		initParams();
		System.out.println("启动数据接收器");
		if (socket == null || (statusCode == 0 || statusCode == 3)) {
			// 启动线程，初始化连接
		return 	threadobj=new Thread() {
				public void run() {
					System.out.println("启动数据接收器1");
					while (statusCode != 4) {
						if(getStatusCode()!=5){
						System.out.println("启动数据接收器2");
						try {
							statusCode = 1;
							// System.out.println("异步接口，正在连接socket....");
							socket = new Socket();
							SocketAddress sa = new InetSocketAddress(ip, port);
							socket.connect(sa, socketConnectTimeOut);
							socket.setKeepAlive(true);// 开启保持活动状态的套接字
							socket.setTcpNoDelay(true);
							socket.setSoTimeout(10000);// 设置超时时间
							out = new PrintWriter(socket.getOutputStream(),
									true);
							in = socket.getInputStream();
							statusCode = 2;
							Logs.log("实时数据异步接口，网络连接成功");
							// TODO 订阅消息
							
							while (statusCode != 2) {
								Thread.sleep(1000);
							}
							
						    //AppContext.getStaticalarm().clear();
						   /* Map<String,String> map=new HashMap<String,String>();
						    map.put("cmd", "systype");
						    BaseUtil.setMsg(JSONObject.fromObject(map).toString());*/
							subMessage(object);
							
						} catch (Exception e0) {
							try {
								closeSocket();
								Logs.log("实时数据异步接口，重连失败,"+reConnectTime+"ms秒后重新尝试Asyn..."
										+ this.hashCode());
								Thread.sleep(reConnectTime);
							} catch (InterruptedException e2) {
								e2.printStackTrace();
							}
						}
					}else{
						try {
							object.setComstate(getStatusCode()+"");
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					}
				};
			};
		}
		return null;
	}

	public  void closeSocket() {
		if (in != null) {
			try {
				in.close();
				in = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (out != null) {
			try {
				out.close();
				out = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (socket != null) {
			try {
				socket.close();
				socket = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public  void destory() {
		closeSocket();
		statusCode = 4;
	}

	/**
	 * 初始化参数
	 */
	private static void initParams() {
		if (ip == null) {
			ip = AppContext.getPropSet("serverSocketIp");
			//port = Integer.parseInt(AppContext.syspara.get("SockPort"));
			port = Integer.parseInt("12345");
			socketConnectTimeOut = Integer.parseInt(AppContext
					.getPropSet("socketConnectTimeOut"));
			reConnectTime=Integer.parseInt(AppContext
					.getPropSet("reConnectTime","60000"));
		}
	}

	/**
	 * 获取A包和Ｂ包
	 * 
	 * @param xml
	 * @return
	 */
	public synchronized void subMessage(Service object) {
	
	}

	
	
	
	
}
