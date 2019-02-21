package com.loncom.ismac.soket.service.impl;

import java.net.Socket;

import com.loncom.ismac.bean.SocketBean;
import com.loncom.ismac.lservice.bean.Service;
import com.loncom.ismac.soket.service.ISocket;

public class BaseSocketClient extends SocketBean implements ISocket {

	public Thread initConnect(Service object) {
		return null;
	}

	public String sendData(String xml , Service service) throws Exception {
		return null;
	}

	public void subMessage(Service object) {

	}

	public void stopSocket() {
		setStatusCode(5);
		closeSocket();

	}

	public void startSocket() {
		setStatusCode(3);

	}

	public Thread getSocketThread() {

		return this.getSocketThread();
	}

	public Socket getSerivceSocket() {
		return getSocket();
	}

	@Override
	public void destroy() {
		if (threadobj != null) {
			threadobj.interrupt();
		}
	}

	@Override
	public void closeSocket() {

	}


}
