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

public class LoncomipDataSocketClient extends BaseSocketClient {
	private  PrintWriter out = null;
	private  InputStream in = null;
	private Thread th=null;
	// 初始化连接
	public   Thread initConnect(final Service object) {
		//initParams();
		//this.setReConnectTime(object.getReconnecttime());
	//	this.setSocketconnecttimeout(object.getSocketconnecttimeout());
		if (getSocket() == null || (getStatusCode() == 0 || getStatusCode() == 3)) {
			// 启动线程，初始化连接
		 th=new Thread() {
				public void run() {
					Logs.log("连接数据服务编号:"+object.getAgentbm());
					while (getStatusCode() != 4) {
						setReConnectTime(object.getReconnecttime());
							if(getStatusCode()!=5){
								try {
									System.out.println("连接数据服务编号:"+object.getAgentbm());
									setStatusCode(1);
									setSocket(new Socket());
									SocketAddress sa = new InetSocketAddress(object.getIp(), Integer.parseInt(object.getPort()));
									getSocket().connect(sa,Integer.parseInt(object.getSocketconnecttimeout()));
									getSocket().setKeepAlive(true);// 开启保持活动状态的套接字
									getSocket().setTcpNoDelay(true);
									getSocket().setSoTimeout(Integer.parseInt(object.getSotimeout()));// 设置超时时间
									out = new PrintWriter(getSocket().getOutputStream(),
											true);
									in = getSocket().getInputStream();
									setStatusCode(2);
									Logs.log("实时数据异步接口，网络连接成功");
									
									while (getStatusCode() != 2) {
										Thread.sleep(1000);
									}
									object.setComstate(getStatusCode()+"");
									
									subMessage(object);
									
								} catch (Exception e0) {
									try {
										object.setComstate(getStatusCode()+"");
										closeSocket();
									//	Logs.log("数据服务编号:"+object.getAgentbm()+"实时数据异步接口，重连失败,"+getReConnectTime()+"ms秒后重新尝试Asyn..."
									//			+ this.hashCode());
										Thread.sleep(getReConnectTime());
										//Logs.SysLog("数据服务","系统","数据服务编号:"+object.getAgentbm()+"实时数据异步接口，重连失败,"+getReConnectTime()+"ms秒后重新尝试Asyn..", "");
									} catch (InterruptedException e2) {
										
										e2.printStackTrace();
										break;
									}
								}
						}else{
							try {
								System.out.println(1111);
								object.setComstate(getStatusCode()+"");
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				};
			};
			setThreadobj(th);
			return th;
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
	public  void destroy() {
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
		// System.out.println("=======" + statusCode);
		ByteBuffer buffer=null;
		if (getStatusCode() == 2) {
			try {
				int lengths=4;
				boolean istop=false;
				
				while (true) {
					// 重复接受
					byte [] bufIn=new byte[lengths];
					int inx=-1;
					if(bufIn.length==4){
						if(istop){
							inx= in.read(bufIn,0,bufIn.length);
							lengths=BaseUtil.getIntByte(bufIn, 0)-8;
							if(lengths<0){
								lengths=0;
							}
							buffer=ByteBuffer.allocate(lengths+8);
							buffer.put(new byte[]{-1, -1, -1, -1});
							buffer.put(bufIn);
						}else{
						 inx= in.read(bufIn,0,bufIn.length);
						 String ff=Integer.toHexString(BaseUtil.byteArrayToInt(bufIn, 0));
						 if("ffffffff".equals(ff)){
						 istop=true;
						 }
						}
					}else{
						inx= in.read(bufIn,0,bufIn.length);
						buffer.put(BaseUtil.subBytes(bufIn, 0, inx));
						lengths=lengths-inx;
					}
					if (inx ==-1) {
						throw new Exception("连接已断开");
					}
					if(lengths==0){
						String ee=Integer.toHexString(BaseUtil.byteArrayToInt(buffer.array(), buffer.array().length-4));
						if("eeeeeeee".equals(ee))
						AppContext.dataQueueVouData.put(buffer.array());
						lengths=4;
						istop=false;
						buffer.clear();
						buffer=null;
					}
				}
			} catch (Exception e) {
				if(getStatusCode()!=5){
					setStatusCode(3);
					closeSocket();
					//BaseUtil.removeDevValue();//清除所有设备测点数据
				}
				
				object.setComstate("0");
				Logs.log("数据服务编号:"+object.getAgentbm()+"实时数据异步接口，连接断开，正在尝试重新连接!" + e.getLocalizedMessage());
				throw new RuntimeException("数据服务:"+object.getAgentbm()+"发送失败：socket异常，正在尝试重新连接!");
			}
		} else {
			closeSocket();
			object.setComstate("0");
			throw new RuntimeException("实时数据异步接口状态异常,重新初始");
		}
	}	
}
