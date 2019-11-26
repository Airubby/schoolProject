package com.loncom.ismac.user.bean;

import com.loncom.ismac.annotation.Table;

/**
 * 主页菜单业务类
 * @author youtao
 *
 */
@Table(NAME="sysmenu",ORDER=" order by id asc")
public class SysMenuServiceBean {
	private String id;//编码
	private String menuname;//菜单名称
	private String menusrc;//菜单图片
	private String menuloga;//菜单logo
	private String parentid;//父级ID
	private String menuindex;//排序
	private String enabled;//是否停用
	private String type;//1最子集页面
	private String code;//功能码

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

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
