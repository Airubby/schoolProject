package com.loncom.ismac.bean;

import java.util.List;

public class Rquest<T> {

	private String cmd;//指令判断
	
	private List<T> data;//数据

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}



	

	
	
	
}
