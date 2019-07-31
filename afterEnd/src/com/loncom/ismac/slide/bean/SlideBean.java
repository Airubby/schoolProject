package com.loncom.ismac.slide.bean;

import com.loncom.ismac.annotation.Attachment;
import com.loncom.ismac.annotation.Table;

@Table(NAME="slide")
public class SlideBean {
	@Attachment(DELETE=true,UPDATE=true)
	private String id;// 编码
	private String url;//图片
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
