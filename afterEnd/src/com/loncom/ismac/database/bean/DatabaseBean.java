package com.loncom.ismac.database.bean;

import com.loncom.ismac.annotation.Table;

@Table(NAME="databaselog")
public class DatabaseBean {
	private String id;// 编码
	private String time;// 备份时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "DatabaseBean [id=" + id + ", time=" + time + "]";
	}
	
}
