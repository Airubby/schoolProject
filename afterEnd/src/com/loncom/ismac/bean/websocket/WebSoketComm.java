package com.loncom.ismac.bean.websocket;

public class WebSoketComm {
	
	private String key;//唯一对应
	private int commstart;//状态  -1失联 0正常 >1异常
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getCommstart() {
		return commstart;
	}
	public void setCommstart(int commstart) {
		this.commstart = commstart;
	}
	

}
