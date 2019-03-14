package com.loncom.ismac.bean.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
@XStreamAlias("dev")
public class DevheadXml {
	@XStreamAsAttribute
	private String devname;
	@XStreamAsAttribute
	private String mgrobjid;
	@XStreamAsAttribute
	private String com;
	@XStreamAsAttribute
	private String comsetting;
	@XStreamAsAttribute
	private String datatype;
	@XStreamAsAttribute
	private String init;
	@XStreamAsAttribute
	private String txend;
	@XStreamAsAttribute
	private String state;
	@XStreamAsAttribute
	private String devtypeid;
	@XStreamAsAttribute
	private String maintenancestate;
	@XStreamAsAttribute
	private String teststate;
	
	@XStreamImplicit(itemFieldName = "point")
	private List<DevvouXml> point=new ArrayList<DevvouXml>();
	

	public List<DevvouXml> getPoint() {
		return point;
	}

	public void setPoint(List<DevvouXml> point) {
		this.point = point;
	}

	@XmlAttribute(name = "devname")
	public String getDevname() {
		return devname;
	}

	public void setDevname(String devname) {
		this.devname = devname;
	}

	@XmlAttribute(name = "com")
	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	@XmlAttribute(name = "comsetting")
	public String getComsetting() {
		return comsetting;
	}

	public void setComsetting(String comsetting) {
		this.comsetting = comsetting;
	}

	@XmlAttribute(name = "datatype")
	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	@XmlAttribute(name = "init")
	public String getInit() {
		return init;
	}

	public void setInit(String init) {
		this.init = init;
	}

	@XmlAttribute(name = "txend")
	public String getTxend() {
		return txend;
	}

	public void setTxend(String txend) {
		this.txend = txend;
	}

	@XmlAttribute(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@XmlAttribute(name = "devtypeid")
	public String getDevtypeid() {
		return devtypeid;
	}

	public void setDevtypeid(String devtypeid) {
		this.devtypeid = devtypeid;
	}

	@XmlAttribute(name = "maintenancestate")
	public String getMaintenancestate() {
		return maintenancestate;
	}

	public void setMaintenancestate(String maintenancestate) {
		this.maintenancestate = maintenancestate;
	}

	@XmlAttribute(name = "teststate")
	public String getTeststate() {
		return teststate;
	}

	public void setTeststate(String teststate) {
		this.teststate = teststate;
	}
	@XmlAttribute(name = "mgrobjid")
	public String getMgrobjid() {
		return mgrobjid;
	}

	public void setMgrobjid(String mgrobjid) {
		this.mgrobjid = mgrobjid;
	}
	

}
