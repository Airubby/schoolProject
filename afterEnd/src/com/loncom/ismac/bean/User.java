package com.loncom.ismac.bean;

import java.io.Serializable;
@SuppressWarnings({ "serial" })

public class User extends PageBean implements Serializable {
	
	private String id;
	private String name;
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
	
}
