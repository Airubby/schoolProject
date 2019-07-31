package com.loncom.ismac.test;

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
public class Dataroot1 {
	@XmlElement(name = "dev")
	@XStreamImplicit(itemFieldName = "dev")
	private List<DevheadXml1> dev = new ArrayList<DevheadXml1>();

	public List<DevheadXml1> getDev() {
		return dev;
	}

	public void setDev(List<DevheadXml1> dev) {
		this.dev = dev;
	}

	

}
