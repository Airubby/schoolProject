package com.loncom.ismac.timegroup.bean;

import com.loncom.ismac.annotation.Attachment;
import com.loncom.ismac.annotation.Table;

@Table(NAME="timedetail")
public class TimeDetail {
	@Attachment(UPDATE=true)
	private String id;
	private String timeweek;
	private String begintime;
	private String endtime;
	@Attachment(DELETE=true)
	private String timegroupid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTimeweek() {
		return timeweek;
	}
	public void setTimeweek(String timeweek) {
		this.timeweek = timeweek;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getTimegroupid() {
		return timegroupid;
	}
	public void setTimegroupid(String timegroupid) {
		this.timegroupid = timegroupid;
	}
	
}
