package com.loncom.ismac.timegroup.bean;

import java.util.ArrayList;
import java.util.List;

import com.loncom.ismac.annotation.Attachment;
import com.loncom.ismac.annotation.Table;

@Table(NAME="timegroup")
public class TimeGroup {
	@Attachment(DELETE=true,UPDATE=true)
	private String id; //
	private String name;  //名称
	private String start_time; //开始时间
	private String end_time; //结束时间
	@Attachment(ISENABLE=false)
	private List<TimeDetail> timeDeteil=new ArrayList<TimeDetail>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public List<TimeDetail> getTimeDeteil() {
		return timeDeteil;
	}
	public void setTimeDeteil(List<TimeDetail> timeDeteil) {
		this.timeDeteil = timeDeteil;
	}

}
