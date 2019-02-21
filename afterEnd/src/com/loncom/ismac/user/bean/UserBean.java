package com.loncom.ismac.user.bean;

import com.loncom.ismac.annotation.Attachment;
import com.loncom.ismac.annotation.Table;
import com.loncom.ismac.bean.PageBean;

@Table(NAME="user")
public class UserBean extends PageBean {
	@Attachment(DELETE=true,UPDATE=true)
	private String id;// 编码
	private String name;// 用户名称
	private String userid;// 用户ID
	private String roleid;// 角色ID
	@Attachment(ISENABLE=false)
	private String rolename;
	private String psword;// 用户密码
	private String phone;// 手机号码
	private String email;// 邮箱
	private String time_start;// 起始时间
	private String time_end;// 结束时间
	private String state;// 是否启用
	private String addrorrole;//可以访问站点权限
	private String details;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getPsword() {
		return psword;
	}
	public void setPsword(String psword) {
		this.psword = psword;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddrorrole() {
		return addrorrole;
	}
	public void setAddrorrole(String addrorrole) {
		this.addrorrole = addrorrole;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", name=" + name + ", userid=" + userid + ", roleid=" + roleid + ", rolename="
				+ rolename + ", psword=" + psword + ", phone=" + phone + ", email=" + email + ", time_start="
				+ time_start + ", time_end=" + time_end + ", state=" + state + ", addrorrole=" + addrorrole
				+ ", details=" + details + "]";
	}
	
	
	
	
	
}
