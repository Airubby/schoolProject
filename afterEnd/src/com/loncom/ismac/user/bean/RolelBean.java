package com.loncom.ismac.user.bean;

import com.loncom.ismac.annotation.Attachment;
import com.loncom.ismac.annotation.Table;
import com.loncom.ismac.bean.PageBean;

@Table(NAME = "rolel")
public class RolelBean extends PageBean {
	@Attachment(UPDATE=true)
	private String id;//唯一编号
	@Attachment(DELETE=true)
	private String roleid;//角色ID 
	private String sysmenuid;//菜单ID
	private String roperid;//操作功能项
	private String half;//是否是半选状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getSysmenuid() {
		return sysmenuid;
	}

	public void setSysmenuid(String sysmenuid) {
		this.sysmenuid = sysmenuid;
	}

	public String getRoperid() {
		return roperid;
	}

	public void setRoperid(String roperid) {
		this.roperid = roperid;
	}

	public String getHalf() {
		return half;
	}

	public void setHalf(String half) {
		this.half = half;
	}

}
