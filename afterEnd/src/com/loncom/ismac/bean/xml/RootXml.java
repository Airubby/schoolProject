package com.loncom.ismac.bean.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
@XStreamAlias("root")
public class RootXml {

	private GroupControlXml groupcontrol = new GroupControlXml();

	public GroupControlXml getGroupcontrol() {
		return groupcontrol;
	}

	public void setGroupcontrol(GroupControlXml groupcontrol) {
		this.groupcontrol = groupcontrol;
	}

}
