package com.loncom.ismac.bean.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
@XStreamAlias("officegroup")
public class OfficegroupXml {

	@XStreamImplicit(itemFieldName = "officegroup")
	private List<ClassroomXml> item = new ArrayList<ClassroomXml>();
	@XmlElement(name = "officegroup")
	public List<ClassroomXml> getItem() {
		return item;
	}

	public void setItem(List<ClassroomXml> item) {
		this.item = item;
	}
}
