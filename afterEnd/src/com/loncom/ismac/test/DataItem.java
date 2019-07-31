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
public class DataItem {
	@XmlElement(name = "item")
	@XStreamImplicit(itemFieldName = "item")
	private List<DataItemXml> item = new ArrayList<DataItemXml>();

	public List<DataItemXml> getItem() {
		return item;
	}

	public void setItem(List<DataItemXml> list) {
		this.item = list;
	}

	
	

}
