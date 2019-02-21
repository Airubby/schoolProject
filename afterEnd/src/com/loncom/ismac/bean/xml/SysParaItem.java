package com.loncom.ismac.bean.xml;

import javax.xml.bind.annotation.XmlAttribute;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("item")
public class SysParaItem {
	@XStreamAsAttribute
	private String mpara;
	@XStreamAsAttribute
	private String mchar;

	@XmlAttribute(name = "mpara")
	public String getMpara() {
		return mpara;
	}

	public void setMpara(String mpara) {
		this.mpara = mpara;
	}
	@XmlAttribute(name = "mchar")
	public String getMchar() {
		return mchar;
	}

	public void setMchar(String mchar) {
		this.mchar = mchar;
	}

}
