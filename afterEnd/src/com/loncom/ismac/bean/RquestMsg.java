package com.loncom.ismac.bean;


public class RquestMsg {
	private String err_msg;// 返回内容
	private String err_code;// 是否请求成功0 1
	

	public String getErr_msg() {
		return err_msg;
	}

	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	/*public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
*/
	
}
