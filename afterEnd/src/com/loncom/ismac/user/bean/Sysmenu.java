package com.loncom.ismac.user.bean;

import com.loncom.ismac.annotation.Table;
import com.loncom.ismac.bean.PageBean;

@Table(NAME="sysmenu")
public class Sysmenu extends PageBean {

private String id;
private String menuname;
private String menusrc;
private String menuloga;
private String parentid;
private String menuindex;
private String devtypeid;
private String fullname;
private String code;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getMenuname() {
	return menuname;
}
public void setMenuname(String menuname) {
	this.menuname = menuname;
}
public String getMenusrc() {
	return menusrc;
}
public void setMenusrc(String menusrc) {
	this.menusrc = menusrc;
}
public String getMenuloga() {
	return menuloga;
}
public void setMenuloga(String menuloga) {
	this.menuloga = menuloga;
}
public String getParentid() {
	return parentid;
}
public void setParentid(String parentid) {
	this.parentid = parentid;
}
public String getMenuindex() {
	return menuindex;
}
public void setMenuindex(String menuindex) {
	this.menuindex = menuindex;
}
public String getDevtypeid() {
	return devtypeid;
}
public void setDevtypeid(String devtypeid) {
	this.devtypeid = devtypeid;
}
public String getFullname() {
	return fullname;
}
public void setFullname(String fullname) {
	this.fullname = fullname;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}


}