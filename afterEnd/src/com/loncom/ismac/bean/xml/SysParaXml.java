package com.loncom.ismac.bean.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
@XStreamAlias("syspara")
public class SysParaXml {
	@XStreamImplicit(itemFieldName = "item")
	private  List<SysParaItem> itemlist = new ArrayList<SysParaItem>();
	@XmlTransient
	private Map<String, String> map = new HashMap<String, String>();
	
	@XmlElement(name="item")
	public List<SysParaItem> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<SysParaItem> itemlist) {
		this.itemlist = itemlist;
	}

	@XmlTransient
	public Map<String, String> getMap() {
		map = new HashMap<String, String>();
		if(this.itemlist.size()>0){
			for (SysParaItem sysParaItem : itemlist) {
				map.put(sysParaItem.getMpara(), sysParaItem.getMchar());
			}
		}
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

}
