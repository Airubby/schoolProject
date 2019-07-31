package com.loncom.ismac.lservice.bean;

import com.loncom.ismac.annotation.Attachment;
import com.loncom.ismac.annotation.Table;
import com.loncom.ismac.bean.xml.GroupControlXml;
import com.loncom.ismac.bean.xml.RootDevXml;
import com.loncom.ismac.util.BaseUtil;

/**
 * 数据服务实体类
 * 
 * @author youtao
 *
 */
@Table(NAME = "service")
public class Service {
	@Attachment(DELETE = true, UPDATE = true)
	private String id; // 编码
	private String name;//采集器名称
	private String agentbm;// 服务编号
	private String ip;// 服务IP
	private String port;// 收数据服务端口号
	private String port1;// 发送数据服务端口号
	private String protocol;// 服务解析协议
	private String time;// 服务时间
	private String state;// 是否启用 1启用 0不启用
	private String socketconnecttimeout; // 连接超时，毫秒
	private String reconnecttime; // 重连时间，毫秒
	private String sotimeout;//发送超时时间
	private String sysxml;//XML配置文件XML
	private String devxml;//XML配置文件XML
	@Attachment(ISENABLE = false)
	private GroupControlXml groupcontrol=new GroupControlXml();//XML配置文件
	@Attachment(ISENABLE = false)
	private String comstate;// 0未初始化,1正在初始化，2已经连接，3断开,4销毁 5停用
	private String addrid;// 地点id
	@Attachment(ISENABLE = false)
	private String addname;// 地点名称
	@Attachment(ISENABLE = false)
	private int aircount;// 空调数量
	@Attachment(ISENABLE = false)
	private int aironcount;// 空调开机数量
	@Attachment(ISENABLE = false)
	private String key_aironcount = "";//空调开机key
	@Attachment(ISENABLE = false)
	private int aircolsecount;// 空调关机数量
	@Attachment(ISENABLE = false)
	private String key_aircolsecount = "";//空调关机key
	@Attachment(ISENABLE = false)
	private RootDevXml  rootdev;  //设备

	public RootDevXml getRootdev() {
		return rootdev;
	}

	public void setRootdev(RootDevXml rootdev) {
		this.rootdev = rootdev;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgentbm() {
		return agentbm;
	}

	public void setAgentbm(String agentbm) {
		this.agentbm = agentbm;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getComstate() {
		return comstate;
	}

	public void setComstate(String comstate) {
		this.comstate = comstate;
	}

	public String getSocketconnecttimeout() {
		return socketconnecttimeout;
	}

	public void setSocketconnecttimeout(String socketconnecttimeout) {
		this.socketconnecttimeout = socketconnecttimeout;
	}

	public String getReconnecttime() {
		return reconnecttime;
	}

	public void setReconnecttime(String reconnecttime) {
		this.reconnecttime = reconnecttime;
	}

	public String getSotimeout() {
		return sotimeout;
	}

	public void setSotimeout(String sotimeout) {
		this.sotimeout = sotimeout;
	}

	public String getPort1() {
		return port1;
	}

	public void setPort1(String port1) {
		this.port1 = port1;
	}

	public String getAddrid() {
		return addrid;
	}

	public void setAddrid(String addrid) {
		this.addrid = addrid;
	}

	public String getAddname() {
		return "";
	}

	public void setAddname(String addname) {
		this.addname = addname;
	}

	public GroupControlXml getGroupcontrol() {
		
		return groupcontrol;
	}

	public void setGroupcontrol(GroupControlXml groupcontrol) {
		this.groupcontrol = groupcontrol;
	}

	

	public String getSysxml() {
		return sysxml;
	}

	public void setSysxml(String sysxml) {
		this.sysxml = sysxml;
	}

	public String getDevxml() {
		return devxml;
	}

	public void setDevxml(String devxml) {
		this.devxml = devxml;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAircount() {
		return aircount;
	}

	public void setAircount(int aircount) {
		this.aircount = aircount;
	}

	public int getAironcount() {
		return aironcount;
	}

	public void setAironcount(int aironcount) {
		this.aironcount = aironcount;
	}

	public String getKey_aironcount() {
		return getAgentbm()+"_aironcount";
	}

	public void setKey_aironcount(String key_aironcount) {
		this.key_aironcount = key_aironcount;
	}

	public int getAircolsecount() {
	
		return getAircount()-getAironcount();
	}

	public void setAircolsecount(int aircolsecount) {
		this.aircolsecount = aircolsecount;
	}

	public String getKey_aircolsecount() {
		return getAgentbm()+"_aircolsecount";
	}

	public void setKey_aircolsecount(String key_aircolsecount) {
		this.key_aircolsecount = key_aircolsecount;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", agentbm=" + agentbm + ", ip=" + ip + ", port=" + port
				+ ", port1=" + port1 + ", protocol=" + protocol + ", time=" + time + ", state=" + state
				+ ", socketconnecttimeout=" + socketconnecttimeout + ", reconnecttime=" + reconnecttime + ", sotimeout="
				+ sotimeout + ", sysxml=" + sysxml + ", devxml=" + devxml + ", groupcontrol=" + groupcontrol
				+ ", comstate=" + comstate + ", addrid=" + addrid + ", addname=" + addname + ", aircount=" + aircount
				+ ", aironcount=" + aironcount + ", key_aironcount=" + key_aironcount + ", aircolsecount="
				+ aircolsecount + ", key_aircolsecount=" + key_aircolsecount + "]";
	}

	



	
}
