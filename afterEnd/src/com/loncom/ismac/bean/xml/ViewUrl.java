package com.loncom.ismac.bean.xml;

import javax.xml.bind.annotation.XmlAttribute;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("viewurl")
public class ViewUrl {
	@XStreamAsAttribute
	private String src;

	@XmlAttribute(name = "src")
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
	
}
