package com.loncom.ismac.soket.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import com.loncom.ismac.logs.Logs;
import com.loncom.ismac.lservice.bean.Service;

/**
 * 收发客户端Socket
 * 
 * @author youtao
 *
 */
@SuppressWarnings("unused")
public class LoncomipDataAddOutClient extends BaseSocketClient {
	// 心跳包

	private static Byte[] heartBeat = new Byte[] {};
	private static BufferedWriter out = null;
	private static BufferedReader in = null;
	private Thread th = null;

	public Thread initConnect(final Service object) {
		/*this.setReConnectTime(object.getReconnecttime());
		// this.setIp(object.getIp());
		// this.setPort(object.getPort());
		// this.setp
		if (getSocket() == null || (getStatusCode() == 0 || getStatusCode() == 3)) {
			// 启动线程，初始化连接
			th = new Thread() {
				public void run() {
					Logs.log("发送数据服务编号:" + object.getAgentbm());
					while (getStatusCode() != 2) {
						try {

							setStatusCode(1);
							setSocket(new Socket());
							SocketAddress sa = new InetSocketAddress(object.getIp(),
									Integer.parseInt(object.getPort1()));
							getSocket().connect(sa, Integer.parseInt(object.getSocketconnecttimeout()));
							getSocket().setKeepAlive(true);// 开启保持活动状态的套接字
							getSocket().setTcpNoDelay(true);
							getSocket().setSoTimeout(10000);// 设置超时时间
							out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
							in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
							object.setComstate(getStatusCode() + "");

							subMessage(object);

						} catch (Exception e0) {
							try {
								object.setComstate(getStatusCode() + "");

								closeSocket();
								// Logs.log("数据服务编号:"+object.getAgentbm()+"发送数据异步接口，重连失败,"+getReConnectTime()+"ms秒后重新尝试Asyn..."
								// + this.hashCode());
								Thread.sleep(getReConnectTime());
								// Logs.SysLog("数据发送服务","系统","数据服务编号:"+object.getAgentbm()+"发送数据异步接口，重连失败,"+getReConnectTime()+"ms秒后重新尝试Asyn..",
								// "");
							} catch (InterruptedException e2) {
								e2.printStackTrace();
								break;
							}
						}
					}
				};
			};
			this.setThreadobj(th);
			return th;
		}*/
		return null;
	}

	public void closeSocket() {
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

	public void destory() {
		setStatusCode(4);
		closeSocket();
	}

	/**
	 * 发送数据,如果网络连接异常，则重新初始化连接
	 * 
	 * 
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public synchronized String sendData(String xml, Service object) throws Exception {
		setSocket(new Socket());
		SocketAddress sa = new InetSocketAddress(object.getIp(), Integer.parseInt(object.getPort1()));
		getSocket().connect(sa, Integer.parseInt(object.getSocketconnecttimeout()));
		getSocket().setKeepAlive(true);
		getSocket().setTcpNoDelay(true);
		getSocket().setSoTimeout(10000);
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
		in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
		setStatusCode(2);

		object.setComstate(getStatusCode() + "");

		String response = "";
		if (getStatusCode() == 2)
			try {
				out.write(xml);
				out.flush();
//				System.out.println(object.getIp());
//				System.out.println(object.getSocketconnecttimeout());

				StringBuffer sb = new StringBuffer();
				try {
					do {
						String inx = in.readLine();
						if (inx == null) {
							throw new Exception("连接已断开");
						}
						sb.append(inx + "\n");
					} while (sb.indexOf("</root>") == -1);
				} catch (InterruptedIOException ioex) {
					Logs.log("接收消息超时：" + ioex.getLocalizedMessage());
					throw new RuntimeException("接收消息超时：" + ioex.getLocalizedMessage());
				}
				

				if ((sb.length() > 0) && (sb.indexOf("<?xml") > -1))
					response = sb.substring(sb.indexOf("<?xml"));
				closeSocket();
			} catch (Exception e) {
				e.printStackTrace();
				setStatusCode(3);
				closeSocket();
				Logs.log(e.getLocalizedMessage());
				throw new RuntimeException("同步接口发送数据失败：socket异常，系统正在尝试重新连接，请稍后操作!");
			}
		else if (getStatusCode() != 1) {
			closeSocket();
		}
		if (getStatusCode() != 2) {
			throw new Exception("socket连接失败，正在尝试重新建立连接");
		}
		closeSocket();
		return response;
	}

}
