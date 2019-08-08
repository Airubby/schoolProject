package com.loncom.ismac.alarm.bean;

import com.loncom.ismac.annotation.Attachment;
import com.loncom.ismac.annotation.Table;

@Table(NAME = "alarmthreshold")
public class AlarmThresholdBean {
	@Attachment(DELETE = true, UPDATE = true)
	private String id;// 编码
	private String model; // 是否启用 1 启用， 0 不启用
	private String dayvalue;// 日告警阀值
	private String monthvalue; // 月告警阀值

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDayvalue() {
		return dayvalue;
	}

	public void setDayvalue(String dayvalue) {
		this.dayvalue = dayvalue;
	}

	public String getMonthvalue() {
		return monthvalue;
	}

	public void setMonthvalue(String monthvalue) {
		this.monthvalue = monthvalue;
	}

	@Override
	public String toString() {
		return "dayvalue=" + dayvalue + ", monthvalue="
				+ monthvalue + "";
	}

	
}
