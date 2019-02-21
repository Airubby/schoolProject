package com.loncom.ismac.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RquestMap extends RquestMsg {

	private List<Map<String, String>> data=new ArrayList<Map<String, String>>();

	public List<Map<String, String>> getData() {
		return data;
	}

	public void setData(List<Map<String, String>> data) {
		this.data = data;
	}
	
}
