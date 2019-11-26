package com.loncom.ismac.user.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebServiceSysmenu {
	private String id;//编号
	private String path;//路径
	private String name;//菜单名称ID
	private String menuname; //菜单名称
	private String pid;//父id
	private Map<String, String> meta = new HashMap<String, String>();
	private String component;//菜单物理地址
	private List<WebServiceSysmenu> children = new ArrayList<WebServiceSysmenu>();

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public Map<String, String> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, String> meta) {
		this.meta = meta;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public List<WebServiceSysmenu> getChildren() {
		return children;
	}

	public void setChildren(List<WebServiceSysmenu> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	

}
