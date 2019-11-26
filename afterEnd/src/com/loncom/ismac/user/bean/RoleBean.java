package com.loncom.ismac.user.bean;
import com.loncom.ismac.annotation.Attachment;
import com.loncom.ismac.annotation.Table;
import com.loncom.ismac.bean.PageBean;

@Table(NAME="role")
public class RoleBean  {
	@Attachment(UPDATE = true,DELETE=true)
	private String id;//ID
	private String name;//角色名称
	private String type;//是否管理员
	private String details;//描述
	private String state;//是否启用

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "{name}=" + name + ", {type}=" + type + ", {details}=" + details;
	}

}
