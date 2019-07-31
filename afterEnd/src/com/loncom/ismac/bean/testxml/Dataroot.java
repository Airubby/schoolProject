package com.loncom.ismac.bean.testxml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XmlAccessorType(XmlAccessType.FIELD)
@XStreamAlias("dataroot")
@XmlRootElement(name = "dataroot")
public class Dataroot {
	/*@XmlElement(name = "dev")
	private DevheadXml devhead;

	public DevheadXml getDevhead() {
		return devhead;
	}

	public void setDevhead(DevheadXml devhead) {
		this.devhead = devhead;
	}*/

	@XmlElement(name = "point")
	@XStreamImplicit(itemFieldName = "devvou")
	private List<DevvouXml> devvou = new ArrayList<DevvouXml>();

	public List<DevvouXml> getDevvou() {
		return devvou;
	}

	public void setDevvou(List<DevvouXml> devvou) {
		this.devvou = devvou;
	}

	
}
