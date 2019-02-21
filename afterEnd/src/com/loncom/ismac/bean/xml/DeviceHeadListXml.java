package com.loncom.ismac.bean.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("deviceheadlist")
public class DeviceHeadListXml {
	@XStreamImplicit(itemFieldName = "devhead")
	private List<DevheadXml> devhead=new ArrayList<DevheadXml>();

	
	public List<DevheadXml> getDevhead() {
		return devhead;
	}

	public void setDevhead(List<DevheadXml> devhead) {
		this.devhead = devhead;
	}
	 
}
