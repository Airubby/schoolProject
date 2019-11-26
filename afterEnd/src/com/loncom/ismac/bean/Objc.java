package com.loncom.ismac.bean;

import java.util.ArrayList;
import java.util.List;

import com.loncom.ismac.bean.xml.BaseItemDevXml;

public class Objc {
	private String id;//教室或者办公室ID
	private String name;//房间名称
	private List<BaseItemDevXml> item = new ArrayList<BaseItemDevXml>();//所有设备集合
	private String devid;//配电设备ID
	private String pointid;//配电设备测点ID
	private String serverid;// 采集器ID
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
	public List<BaseItemDevXml> getItem() {
		return item;
	}
	public void setItem(List<BaseItemDevXml> item) {
		this.item = item;
	}
	public String getDevid() {
		return devid;
	}
	public void setDevid(String devid) {
		this.devid = devid;
	}
	public String getPointid() {
		return pointid;
	}
	public void setPointid(String pointid) {
		this.pointid = pointid;
	}
	public String getServerid() {
		return serverid;
	}
	public void setServerid(String serverid) {
		this.serverid = serverid;
	}
	@Override
	public String toString() {
		return "Objc [id=" + id + ", name=" + name + ", item=" + item + ", devid=" + devid + ", pointid=" + pointid
				+ ", serverid=" + serverid + "]";
	}
	
	

}
