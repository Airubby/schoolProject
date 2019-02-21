package com.loncom.ismac.bean;

import java.util.HashMap;
import java.util.Map;

import com.loncom.ismac.bean.xml.DevvouXml;

public class DevheadBean implements Cloneable {
	private String orderno;//编码
	private String agentbm;//机房编码
    private String mgrobjid;//设备ID
    private String state;//
    private String statecomm="1";//通讯状态 0通讯成功  1通讯失败
    private String statec;//设备状态
    private String devname;//设备名称
    private String addrid;//地点ID
    private String mgrobjtypeid;//设备类型
    private Map<String, DevvouXml> item=new HashMap<String, DevvouXml>();//属性集合
	public String getMgrobjid() {
		return mgrobjid;
	}
	public void setMgrobjid(String mgrobjid) {
		this.mgrobjid = mgrobjid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDevname() {
		return devname;
	}
	public void setDevname(String devname) {
		this.devname = devname;
	}
	public Map<String, DevvouXml> getItem() {
		return item;
	}
	public void setItem(Map<String, DevvouXml> item) {
		this.item = item;
	}
	public String getAgentbm() {
		return agentbm;
	}
	public void setAgentbm(String agentbm) {
		this.agentbm = agentbm;
	}
	@Override  
    public Object clone() throws CloneNotSupportedException  
    {  
        return super.clone();  
    }
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getAddrid() {
		return addrid;
	}
	public void setAddrid(String addrid) {
		this.addrid = addrid;
	}
	public String getMgrobjtypeid() {
		return mgrobjtypeid;
	}
	public void setMgrobjtypeid(String mgrobjtypeid) {
		this.mgrobjtypeid = mgrobjtypeid;
	}
	public String getStatec() {
		return statec;
	}
	public void setStatec(String statec) {
		this.statec = statec;
	}
	public String getStatecomm() {
		return statecomm;
	}
	public void setStatecomm(String statecomm) {
		this.statecomm = statecomm;
	}  
	
}
