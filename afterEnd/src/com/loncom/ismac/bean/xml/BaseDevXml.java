package com.loncom.ismac.bean.xml;

import javax.xml.bind.annotation.XmlAttribute;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class BaseDevXml {
	@XStreamAsAttribute
	private String devid;//设备ID 
	@XStreamAsAttribute
	private String stateid;//状态ID
	@XStreamAsAttribute
	private String control; //控制ID
	@XStreamAsAttribute
	private String colsecmd;//发送串

	@XmlAttribute(name = "devid")
	public String getDevid() {
		return devid;
	}

	public void setDevid(String devid) {
		this.devid = devid;
	}

	@XmlAttribute(name = "stateid")
	public String getStateid() {
		return stateid;
	}

	public void setStateid(String stateid) {
		this.stateid = stateid;
	}

	@XmlAttribute(name = "control")
	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public String getColsecmd() {
		return colsecmd;
	}

	public void setColsecmd(String colsecmd) {
		this.colsecmd = colsecmd;
	}

}
