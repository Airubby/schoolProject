package com.loncom.ismac.bean.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("devicevoulist")
public class DeviceVouListXml {
	
	@XStreamImplicit(itemFieldName = "devvou")
	private List<DevvouXml> devvoulist;
	@XmlElement(name="devvou")
	public List<DevvouXml> getDevvoulist() {
		return devvoulist;
	}

	public void setDevvoulist(List<DevvouXml> devvoulist) {
		this.devvoulist = devvoulist;
	}
	
}
