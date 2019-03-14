package com.loncom.ismac.bean.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;



import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/*@XmlRootElement(name = "groupcontrol")
@XmlAccessorType(XmlAccessType.FIELD)*/
@XStreamAlias("groupcontrol")
public class GroupControlXml {
	@XStreamAsAttribute
	private String name;
	
	
	@XStreamImplicit(itemFieldName = "group")
	private List<GroupXml> group = new ArrayList<GroupXml>();

	
	
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GroupXml> getGroup() {
		return group;
	}

	public void setGroup(List<GroupXml> group) {
		this.group = group;
	}	

}
