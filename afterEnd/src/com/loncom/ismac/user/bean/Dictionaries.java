package com.loncom.ismac.user.bean;

import com.loncom.ismac.annotation.Table;
import com.loncom.ismac.bean.PageBean;
@Table(NAME = "dictionaries")
public class Dictionaries   {
	private String id;
	private String name;
	private String pid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
	
}
