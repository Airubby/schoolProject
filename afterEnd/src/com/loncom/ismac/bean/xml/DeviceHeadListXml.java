package com.loncom.ismac.bean.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("deviceheadlist")
public class DeviceHeadListXml {
	@XStreamImplicit(itemFieldName = "dev")
	private List<DevheadXml> dev=new ArrayList<DevheadXml>();

	public List<DevheadXml> getDev() {
		return dev;
	}

	public void setDev(List<DevheadXml> dev) {
		this.dev = dev;
	}

	

	 
}
