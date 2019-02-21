package com.loncom.ismac.bean.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Command {

	private String type = "AddDevice";
	private RootXml root;

	public Command() {
	}

	public Command(String type) {
		super();
		this.type = type;
	}

	@XmlAttribute(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RootXml getRoot() {
		return root;
	}

	public void setRoot(RootXml root) {
		this.root = root;
	}
	
	

	

	
	
	
}
