package com.loncom.ismac.bean.testxml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;


public class DevvouXml {
	@XStreamAsAttribute
	private String orderno;
	@XStreamAsAttribute
	private String agentbm;
	@XStreamAsAttribute
	private String devname;
	@XStreamAsAttribute
	private String state;
	@XStreamAsAttribute
	private String mgrobjid;
	@XStreamAsAttribute
	private String id;
	@XStreamAsAttribute
	private String ch;
	@XStreamAsAttribute
	private String wrkstate;
	@XStreamAsAttribute
	private String debug;
	@XStreamAsAttribute
	private String secondid;
	@XStreamAsAttribute
	private String datachar;
	@XStreamAsAttribute
	private String rechis;
	@XStreamAsAttribute
	private String report;
	@XStreamAsAttribute
	private String eventalarm;
	@XStreamAsAttribute
	private String uneventalarm;
	@XStreamAsAttribute
	private String emaxtimes;
	@XStreamAsAttribute
	private String noevent;
	@XStreamAsAttribute
	private String value;
	@XStreamAsAttribute
	private String tm;
	@XStreamAsAttribute
	private String unit;
	@XStreamAsAttribute
	private String vformat;
	@XStreamAsAttribute
	private String defvalue;
	@XStreamAsAttribute
	private String maxalarm;
	@XStreamAsAttribute
	private String maxalarm2;
	@XStreamAsAttribute
	private String maxalarm3;
	@XStreamAsAttribute
	private String maxalarm4;
	@XStreamAsAttribute
	private String maxevent;
	@XStreamAsAttribute
	private String minalarm;
	@XStreamAsAttribute
	private String minalarm2;
	@XStreamAsAttribute
	private String minalarm3;
	@XStreamAsAttribute
	private String minalarm4;
	@XStreamAsAttribute
	private String minevent;
	@XStreamAsAttribute
	private String deviationpara;
	@XStreamAsAttribute
	private String alarmrang;
	@XStreamAsAttribute
	private String stride;
	@XStreamAsAttribute
	private String stridemarginvalue;
	@XStreamAsAttribute
	private String maxcontentdo;
	@XStreamAsAttribute
	private String maxcontentundo;
	@XStreamAsAttribute
	private String mincontentdo;
	@XStreamAsAttribute
	private String mincontentundo;
	@XStreamAsAttribute
	private String state0;
	@XStreamAsAttribute
	private String state1;
	@XStreamAsAttribute
	private String actionalarm;
	@XStreamAsAttribute
	private String actionevent;
	@XStreamAsAttribute
	private String actioncontent;
	@XStreamAsAttribute
	private String tx;
	@XStreamAsAttribute
	private String masterid;
	@XStreamAsAttribute
	private String debugdata;
	@XStreamAsAttribute
	private String calib;
	@XStreamAsAttribute
	private String valuepara;
	@XStreamAsAttribute
	private String bgpos;
	@XStreamAsAttribute
	private String len;
	@XStreamAsAttribute
	private String notimes;
	@XStreamAsAttribute
	private String rang;
	@XStreamAsAttribute
	private String timeno;
	@XStreamAsAttribute
	private String times;
	@XStreamAsAttribute
	private String rxtime;
	@XStreamAsAttribute
	private String crctype;
	@XStreamAsAttribute
	private String maxvalue;
	@XStreamAsAttribute
	private String minvalue;
	@XStreamAsAttribute
	private String publishmsg;
	@XStreamAsAttribute
	private String enablekpi;
	@XStreamAsAttribute
	private String signal_group;
	@XStreamAsAttribute
	private String indicator_group;

	@XmlAttribute(name = "orderno")
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	@XmlAttribute(name = "agentbm")
	public String getAgentbm() {
		return agentbm;
	}

	public void setAgentbm(String agentbm) {
		this.agentbm = agentbm;
	}

	@XmlAttribute(name = "devname")
	public String getDevname() {
		return devname;
	}

	public void setDevname(String devname) {
		this.devname = devname;
	}

	@XmlAttribute(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@XmlAttribute(name = "mgrobjid")
	public String getMgrobjid() {
		return mgrobjid;
	}

	public void setMgrobjid(String mgrobjid) {
		this.mgrobjid = mgrobjid;
	}

	@XmlAttribute(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute(name = "ch")
	public String getCh() {
		System.out.println(ch);
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	@XmlAttribute(name = "wrkstate")
	public String getWrkstate() {
		return wrkstate;
	}

	public void setWrkstate(String wrkstate) {
		this.wrkstate = wrkstate;
	}

	@XmlAttribute(name = "debug")
	public String getDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}

	@XmlAttribute(name = "secondid")
	public String getSecondid() {
		return secondid;
	}

	public void setSecondid(String secondid) {
		this.secondid = secondid;
	}

	@XmlAttribute(name = "datachar")
	public String getDatachar() {
		return datachar;
	}

	public void setDatachar(String datachar) {
		this.datachar = datachar;
	}

	@XmlAttribute(name = "rechis")
	public String getRechis() {
		return rechis;
	}

	public void setRechis(String rechis) {
		this.rechis = rechis;
	}

	@XmlAttribute(name = "report")
	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	@XmlAttribute(name = "eventalarm")
	public String getEventalarm() {
		return eventalarm;
	}

	public void setEventalarm(String eventalarm) {
		this.eventalarm = eventalarm;
	}

	@XmlAttribute(name = "uneventalarm")
	public String getUneventalarm() {
		return uneventalarm;
	}

	public void setUneventalarm(String uneventalarm) {
		this.uneventalarm = uneventalarm;
	}

	@XmlAttribute(name = "emaxtimes")
	public String getEmaxtimes() {
		return emaxtimes;
	}

	public void setEmaxtimes(String emaxtimes) {
		this.emaxtimes = emaxtimes;
	}

	@XmlAttribute(name = "noevent")
	public String getNoevent() {
		return noevent;
	}

	public void setNoevent(String noevent) {
		this.noevent = noevent;
	}

	@XmlAttribute(name = "value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@XmlAttribute(name = "tm")
	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	@XmlAttribute(name = "unit")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@XmlAttribute(name = "vformat")
	public String getVformat() {
		return vformat;
	}

	public void setVformat(String vformat) {
		this.vformat = vformat;
	}

	@XmlAttribute(name = "defvalue")
	public String getDefvalue() {
		return defvalue;
	}

	public void setDefvalue(String defvalue) {
		this.defvalue = defvalue;
	}

	@XmlAttribute(name = "maxalarm")
	public String getMaxalarm() {
		return maxalarm;
	}

	public void setMaxalarm(String maxalarm) {
		this.maxalarm = maxalarm;
	}

	@XmlAttribute(name = "maxalarm2")
	public String getMaxalarm2() {
		return maxalarm2;
	}

	public void setMaxalarm2(String maxalarm2) {
		this.maxalarm2 = maxalarm2;
	}

	@XmlAttribute(name = "maxalarm3")
	public String getMaxalarm3() {
		return maxalarm3;
	}

	public void setMaxalarm3(String maxalarm3) {
		this.maxalarm3 = maxalarm3;
	}

	@XmlAttribute(name = "maxalarm4")
	public String getMaxalarm4() {
		return maxalarm4;
	}

	public void setMaxalarm4(String maxalarm4) {
		this.maxalarm4 = maxalarm4;
	}

	@XmlAttribute(name = "maxevent")
	public String getMaxevent() {
		return maxevent;
	}

	public void setMaxevent(String maxevent) {
		this.maxevent = maxevent;
	}

	@XmlAttribute(name = "minalarm")
	public String getMinalarm() {
		return minalarm;
	}

	public void setMinalarm(String minalarm) {
		this.minalarm = minalarm;
	}

	@XmlAttribute(name = "minalarm2")
	public String getMinalarm2() {
		return minalarm2;
	}

	public void setMinalarm2(String minalarm2) {
		this.minalarm2 = minalarm2;
	}

	@XmlAttribute(name = "minalarm3")
	public String getMinalarm3() {
		return minalarm3;
	}

	public void setMinalarm3(String minalarm3) {
		this.minalarm3 = minalarm3;
	}

	@XmlAttribute(name = "minalarm4")
	public String getMinalarm4() {
		return minalarm4;
	}

	public void setMinalarm4(String minalarm4) {
		this.minalarm4 = minalarm4;
	}

	@XmlAttribute(name = "minevent")
	public String getMinevent() {
		return minevent;
	}

	public void setMinevent(String minevent) {
		this.minevent = minevent;
	}

	@XmlAttribute(name = "deviationpara")
	public String getDeviationpara() {
		return deviationpara;
	}

	public void setDeviationpara(String deviationpara) {
		this.deviationpara = deviationpara;
	}

	@XmlAttribute(name = "alarmrang")
	public String getAlarmrang() {
		return alarmrang;
	}

	public void setAlarmrang(String alarmrang) {
		this.alarmrang = alarmrang;
	}

	@XmlAttribute(name = "stride")
	public String getStride() {
		return stride;
	}

	public void setStride(String stride) {
		this.stride = stride;
	}

	@XmlAttribute(name = "stridemarginvalue")
	public String getStridemarginvalue() {
		return stridemarginvalue;
	}

	public void setStridemarginvalue(String stridemarginvalue) {
		this.stridemarginvalue = stridemarginvalue;
	}

	@XmlAttribute(name = "maxcontentdo")
	public String getMaxcontentdo() {
		return maxcontentdo;
	}

	public void setMaxcontentdo(String maxcontentdo) {
		this.maxcontentdo = maxcontentdo;
	}

	@XmlAttribute(name = "maxcontentundo")
	public String getMaxcontentundo() {
		return maxcontentundo;
	}

	public void setMaxcontentundo(String maxcontentundo) {
		this.maxcontentundo = maxcontentundo;
	}

	@XmlAttribute(name = "mincontentdo")
	public String getMincontentdo() {
		return mincontentdo;
	}

	public void setMincontentdo(String mincontentdo) {
		this.mincontentdo = mincontentdo;
	}

	@XmlAttribute(name = "mincontentundo")
	public String getMincontentundo() {
		return mincontentundo;
	}

	public void setMincontentundo(String mincontentundo) {
		this.mincontentundo = mincontentundo;
	}

	@XmlAttribute(name = "state0")
	public String getState0() {
		return state0;
	}

	public void setState0(String state0) {
		this.state0 = state0;
	}

	@XmlAttribute(name = "state1")
	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	@XmlAttribute(name = "actionalarm")
	public String getActionalarm() {
		return actionalarm;
	}

	public void setActionalarm(String actionalarm) {
		this.actionalarm = actionalarm;
	}

	@XmlAttribute(name = "actionevent")
	public String getActionevent() {
		return actionevent;
	}

	public void setActionevent(String actionevent) {
		this.actionevent = actionevent;
	}

	@XmlAttribute(name = "actioncontent")
	public String getActioncontent() {
		return actioncontent;
	}

	public void setActioncontent(String actioncontent) {
		this.actioncontent = actioncontent;
	}

	@XmlAttribute(name = "tx")
	public String getTx() {
		return tx;
	}

	public void setTx(String tx) {
		this.tx = tx;
	}

	@XmlAttribute(name = "masterid")
	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	@XmlAttribute(name = "debugdata")
	public String getDebugdata() {
		return debugdata;
	}

	public void setDebugdata(String debugdata) {
		this.debugdata = debugdata;
	}

	@XmlAttribute(name = "calib")
	public String getCalib() {
		return calib;
	}

	public void setCalib(String calib) {
		this.calib = calib;
	}

	@XmlAttribute(name = "valuepara")
	public String getValuepara() {
		return valuepara;
	}

	public void setValuepara(String valuepara) {
		this.valuepara = valuepara;
	}

	@XmlAttribute(name = "bgpos")
	public String getBgpos() {
		return bgpos;
	}

	public void setBgpos(String bgpos) {
		this.bgpos = bgpos;
	}

	@XmlAttribute(name = "len")
	public String getLen() {
		return len;
	}

	public void setLen(String len) {
		this.len = len;
	}

	@XmlAttribute(name = "notimes")
	public String getNotimes() {
		return notimes;
	}

	public void setNotimes(String notimes) {
		this.notimes = notimes;
	}

	@XmlAttribute(name = "rang")
	public String getRang() {
		return rang;
	}

	public void setRang(String rang) {
		this.rang = rang;
	}

	@XmlAttribute(name = "timeno")
	public String getTimeno() {
		return timeno;
	}

	public void setTimeno(String timeno) {
		this.timeno = timeno;
	}

	@XmlAttribute(name = "times")
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	@XmlAttribute(name = "rxtime")
	public String getRxtime() {
		return rxtime;
	}

	public void setRxtime(String rxtime) {
		this.rxtime = rxtime;
	}

	@XmlAttribute(name = "crctype")
	public String getCrctype() {
		return crctype;
	}

	public void setCrctype(String crctype) {
		this.crctype = crctype;
	}

	@XmlAttribute(name = "maxvalue")
	public String getMaxvalue() {
		return maxvalue;
	}

	public void setMaxvalue(String maxvalue) {
		this.maxvalue = maxvalue;
	}

	@XmlAttribute(name = "minvalue")
	public String getMinvalue() {
		return minvalue;
	}

	public void setMinvalue(String minvalue) {
		this.minvalue = minvalue;
	}

	@XmlAttribute(name = "publishmsg")
	public String getPublishmsg() {
		return publishmsg;
	}

	public void setPublishmsg(String publishmsg) {
		this.publishmsg = publishmsg;
	}

	@XmlAttribute(name = "enablekpi")
	public String getEnablekpi() {
		return enablekpi;
	}

	public void setEnablekpi(String enablekpi) {
		this.enablekpi = enablekpi;
	}

	@XmlAttribute(name = "signal_group")
	public String getSignal_group() {
		return signal_group;
	}

	public void setSignal_group(String signal_group) {
		this.signal_group = signal_group;
	}

	@XmlAttribute(name = "indicator_group")
	public String getIndicator_group() {
		return indicator_group;
	}

	public void setIndicator_group(String indicator_group) {
		this.indicator_group = indicator_group;
	}

	//@Override
	public String toString() {
		return "DevvouXml [ch=" + ch + "]";
	}

}
