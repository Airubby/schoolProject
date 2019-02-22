package com.loncom.ismac.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树形对象带门禁和带摄像机,以及报表类型树
 * 
 * @author youtao
 *
 */
public class zTreeByOther {

	private String name;
	private String id;
	private boolean open;
	private String type;
	private Map<String,String> map=new HashMap<String, String>();
	private List<zTreeByOther> children=new ArrayList<zTreeByOther>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<zTreeByOther> getChildren() {
		return children;
	}

	public void setChildren(List<zTreeByOther> children) {
		this.children = children;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	

}
