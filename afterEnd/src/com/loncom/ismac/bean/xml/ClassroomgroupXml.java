package com.loncom.ismac.bean.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("classroomgroup")
public class ClassroomgroupXml {
	
	@XStreamAsAttribute
	private String name;
	
	@XStreamAsAttribute
	private String roomno;
	
	@XStreamImplicit(itemFieldName = "classroom")
	private List<ClassroomXml> item = new ArrayList<ClassroomXml>();
	
	
	@XmlElement(name = "classroom")
	public List<ClassroomXml> getItem() {
		return item;
	}

	public void setItem(List<ClassroomXml> item) {
		this.item = item;
	}
	
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute(name="roomno")
	public String getRoomno() {
		return roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	
	
	
}
