package com.loncom.ismac.test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
@XStreamAlias("item")
public class DataItemXml {
	@XStreamAsAttribute
	private String dev;
	@XStreamAsAttribute
	private String pointid;
	
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
