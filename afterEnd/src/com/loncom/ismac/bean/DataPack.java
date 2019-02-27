package com.loncom.ismac.bean;
/**
 * 实时数据解析实体类
 * @author youtao
 *
 */
@SuppressWarnings({  "unused"})

public class DataPack {

	
	/**
	 * 采集器编码
	 */
	private String agentbm;

	/**
	 * 设备ID
	 */
	private String mgrobjid;

	/**
	 * 测点ID
	 */
	private String propertyId;

	/**
	 * 数据类  CommStatus 通讯  Digital 状态 Analogy 数字量 
	 */
	private String datachar;


	 /**
	  * 采集的值
	  */
	private String value;

	 /**
	  * 采集时间
	  */
	private String marktime;

	/**
	 * 唯一值
	 */
	private String key;
	
	
	

	public String getKey() {
		return this.getAgentbm()+"_"+this.getMgrobjid()+"_"+this.getPropertyId();
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAgentbm() {
		return agentbm;
	}

	public void setAgentbm(String agentbm) {
		this.agentbm = agentbm;
	}

	public String getMgrobjid() {
		return mgrobjid;
	}

	public void setMgrobjid(String mgrobjid) {
		this.mgrobjid = mgrobjid;
	}

	

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getDatachar() {
		return datachar;
	}

	public void setDatachar(String datachar) {
		this.datachar = datachar;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMarktime() {
		return marktime;
	}

	public void setMarktime(String marktime) {
		this.marktime = marktime;
	}



	
	
}

