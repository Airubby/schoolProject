package com.loncom.ismac.bean.xml;

import javax.xml.bind.annotation.XmlAttribute;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("item")
public class BaseItemDevXml {
	@XStreamAsAttribute
	private String dev;//设备
	@XStreamAsAttribute
	private String pointid;//设备ID,测点ID
	
	
	@XmlAttribute(name = "dev")
	public String getDev() {
		return dev;
	}

	public void setDev(String dev) {
		this.dev = dev;
	}
	@XmlAttribute(name = "pointid")
	public String getPointid() {
		return pointid;
	}

	public void setPointid(String pointid) {
		this.pointid = pointid;
	}

}
