package com.loncom.ismac.soket.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.logs.Logs;
import com.loncom.ismac.lservice.bean.Service;
@SuppressWarnings("unused")

public class LoncomipDataSocketClientV1 extends BaseSocketClient{

	private  PrintWriter out = null;
	private  BufferedReader in = null;
    private Thread th=null;
	// 初始化连接
	public  Thread initConnect(final Service object) {
		this.setReConnectTime(object.getReconnecttime());
		if (getSocket() == null || (getStatusCode() == 0 || getStatusCode() == 3)) {
			// 启动线程，初始化连接
		return th=new Thread() {
				public void run() {
					Logs.log("连接数据服务编号:"+object.getAgentbm());
					while (getStatusCode() != 4) {
						if(getStatusCode()!=5){

						try {
							setStatusCode(1);
							setSocket(new Socket());
							SocketAddress sa = new InetSocketAddress(object.getIp(), Integer.parseInt(object.getPort()));
							getSocket().connect(sa,Integer.parseInt(object.getSocketconnecttimeout()));
							getSocket().setKeepAlive(true);// 开启保持活动状态的套接字
							getSocket().setTcpNoDelay(true);
							getSocket().setSoTimeout(Integer.parseInt(object.getSotimeout()));// 设置超时时间
							out = new PrintWriter(getSocket().getOutputStream(),
									true);
						in =  new BufferedReader(new InputStreamReader(
									getSocket().getInputStream(), "gb2312"));
							setStatusCode(2);
							Logs.log("实时数据异步接口，网络连接成功");
							
							while (getStatusCode() != 2) {
								Thread.sleep(1000);
							}
							object.setComstate(getStatusCode()+"");

						   // AppContext.getStaticalarm().clear();
							subMessage(object);
							
						} catch (Exception e0) {
							try {
								object.setComstate(getStatusCode()+"");

								closeSocket();
								Logs.log("数据服务编号:"+object.getAgentbm()+"实时数据异步接口，重连失败,"+getReConnectTime()+"ms秒后重新尝试Asyn..."
										+ this.hashCode());
								Thread.sleep(getReConnectTime());
								throw new RuntimeException("数据服务编号:"+object.getAgentbm()+"实时数据异步接口，重连失败,"+getReConnectTime()+"ms秒后重新尝试Asyn..");

							} catch (InterruptedException e2) {
								e2.printStackTrace();
								break ;
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
		if (getSocket() != null) {
			try {
				getSocket().close();
				setSocket(null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public  void destory() {
		closeSocket();
		setStatusCode(4);
	}

	

	/**
	 * 获取A包和Ｂ包
	 * 
	 * @param xml
	 * @return
	 */
	public synchronized void subMessage(Service object) {
		// TODO 订阅报文
		// System.out.println("=======" + statusCode);
		if (getStatusCode() == 2) {
			try {
				StringBuffer sb = new StringBuffer();
				while (true) {
					// 重复接受
					String inx = in.readLine();
					if (inx == null) {
						throw new Exception("连接已断开");
					}
					sb.append(inx);
					if (sb.indexOf("^^") != -1) {
						// 已接受完整报文
						Logs.logasyn("收到数据包:" + sb.toString());
						// 将报文放入解析队列
						String xml=sb.toString();
						//xml=xml.replace("&&&","===");
						String [] s=sb.toString().split("##");
						String bao=s.length>0?s[0]:"";
						AppContext.dataQueueVouData.put(sb.toString());
						/*if("A".equals(bao)){//是A包还是B包数据
							// 将报文放入解析队列
							//AppContext.dataQueueVouData.put(sb.toString());//实时数据解析队列			
						}else if("B".equals(bao)){
							// 将报文放入解析队列
							//AppContext.dataQueueAlarm.put(sb.toString());//告警数据解析队列		
					    }*/
						//AppContext.dataQueueAlarm.put(xml);
						sb.setLength(0);
					}
					
					
				}
			} catch (Exception e) {
				setStatusCode(3);
				closeSocket();
				Logs.log("实时数据异步接口，连接断开，正在尝试重新连接!" + e.getLocalizedMessage());
				throw new RuntimeException("发送失败：socket异常，正在尝试重新连接!");
			}
		} else {
			closeSocket();
			throw new RuntimeException("实时数据异步接口状态异常,重新初始");
		}
	}
	
}