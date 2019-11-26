package com.loncom.ismac.user.bean;

import java.util.ArrayList;
import java.util.List;

import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.util.BaseUtil;

public class SysmenuBean {
	private String id;
	private String url;
	private String name;
	private String icon;
	private String roperid;
	private String fullname;
	private List<SysmenuBean> item=new ArrayList<SysmenuBean>();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<SysmenuBean> getItem() {
		return item;
	}

	public void setItem(List<SysmenuBean> item) {
		this.item = item;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoperid() {
		
		if(BaseUtil.isNotNull(roperid)){
			String[] size=roperid.split(",");
			roperid="";
			for (int i = 0; i < size.length; i++) {
				if(i==0){
					roperid+=AppContext.getMenurole().get(size[i]);
				}else{
					roperid+=","+AppContext.getMenurole().get(size[i]);
				}
			}
		}
		return roperid;
	}

	public void setRoperid(String roperid) {
		this.roperid = roperid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}
