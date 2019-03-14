package com.loncom.ismac.bean.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
@XStreamAlias("root")
public class RootDevXml {

	private DeviceHeadListXml deviceheadlist;

	private SysParaXml syspara = new SysParaXml();

	public SysParaXml getSyspara() {
		return syspara;
	}

	public void setSyspara(SysParaXml syspara) {
		this.syspara = syspara;
	}

	public DeviceHeadListXml getDeviceheadlist() {
		return deviceheadlist;
	}

	public void setDeviceheadlist(DeviceHeadListXml deviceheadlist) {
		this.deviceheadlist = deviceheadlist;
	}
}
