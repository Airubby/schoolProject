package com.loncom.ismac.syslog.bean;

import com.loncom.ismac.annotation.Attachment;
import com.loncom.ismac.annotation.Table;
import com.loncom.ismac.bean.PageBean;

@Table(NAME="syslog",ORDER="order by CreateTime desc")
public class Syslog extends PageBean{
	private String id;//编码
	private String ope_id;//操作类型等级
	@Attachment(KEY = "like")
	private String userid;//用户名称
	private String details;//操作详情
	private String createtime;//创建时间
	@Attachment(ISENABLE=false,KEY=">",COLUMNNAME="createtime")
	private String begintime;//起始时间
	@Attachment(ISENABLE=false,KEY="<",COLUMNNAME="createtime")
	private String endtime;//结束时间
	private String interfaceurl;//访问路径
	private String ip;//IP地址
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOpe_id() {
		return ope_id;
	}
	public void setOpe_id(String ope_id) {
		this.ope_id = ope_id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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
	public String getInterfaceurl() {
		return interfaceurl;
	}
	public void setInterfaceurl(String interfaceurl) {
		this.interfaceurl = interfaceurl;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
