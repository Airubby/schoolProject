package com.loncom.ismac.alarm.bean;

import com.loncom.ismac.annotation.Attachment;
import com.loncom.ismac.annotation.Table;

@Table(NAME="alarm")
public class AlarmBean {
	@Attachment(DELETE=true,UPDATE=true)
	private String id;// 编码
	private String calssName;// 教室名称
	private String code;// 教室编码
	private String createTime;// 告警时间
	private String type;// 告警类型，1: 日告警；2：月告警
	private String roomType;  //  classroom：教室，officeroom：办公室
	private String desc;// 告警内容
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCalssName() {
		return calssName;
	}
	public void setCalssName(String calssName) {
		this.calssName = calssName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "AlarmBean [id=" + id + ", calssName=" + calssName + ", code=" + code + ", createTime=" + createTime
				+ ", type=" + type + ", desc=" + desc + "]";
	}
	
	
	
}
