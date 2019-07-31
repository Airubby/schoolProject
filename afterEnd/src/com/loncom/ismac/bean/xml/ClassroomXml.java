package com.loncom.ismac.bean.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("classroom")
public class ClassroomXml {
	private String serverid;// 采集器ID
	@XStreamAsAttribute
	private String code;// 编码
	@XStreamAsAttribute
	private String classname;// 教室名称
	@XStreamAsAttribute
	private String floor;// 教室所在楼层
	@XStreamAsAttribute
	private String model;// 模式 0手动 1自动
	@XStreamAsAttribute
	private String times;// 在自动的情况下times秒监测一下
	@XStreamAsAttribute
	private String timegroup;// 时间组
	@XStreamAsAttribute
	private String openair;// 开空调温度
	@XStreamAsAttribute
	private String closeair;// 关空调温度
	@XStreamAsAttribute
	private String element;// 关联的从元素

	@XStreamImplicit(itemFieldName = "item")
	private List<BaseItemDevXml> baseItem = new ArrayList<BaseItemDevXml>();

	/*
	 * private AirdevXml airdev = new AirdevXml();//空调
	 * 
	 * private AmmeterDevXml ammeterdev = new AmmeterDevXml();//电表
	 * 
	 * private HumitureDevXml humituredev = new HumitureDevXml();//温湿度
	 * 
	 * private InfraredDevXml infrareddev = new InfraredDevXml();//红外
	 * 
	 * private LampDevXml lampdev = new LampDevXml();//电灯
	 */
	@XmlAttribute(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlAttribute(name = "classname")
	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	@XmlAttribute(name = "floor")
	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	@XmlAttribute(name = "model")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@XmlAttribute(name = "times")
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	@XmlAttribute(name = "timegroup")
	public String getTimegroup() {
		return timegroup;
	}

	public void setTimegroup(String timegroup) {
		this.timegroup = timegroup;
	}

	@XmlAttribute(name = "openair")
	public String getOpenair() {
		return openair;
	}

	public void setOpenair(String openair) {
		this.openair = openair;
	}

	@XmlAttribute(name = "closeair")
	public String getCloseair() {
		return closeair;
	}

	public void setCloseair(String closeair) {
		this.closeair = closeair;
	}

	@XmlAttribute(name = "element")
	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	@XmlElement(name = "item")
	public List<BaseItemDevXml> getBaseItem() {
		return baseItem;
	}

	public void setBaseItem(List<BaseItemDevXml> baseItem) {
		this.baseItem = baseItem;
	}

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	/*
	 * public AirdevXml getAirdev() { return airdev; }
	 * 
	 * public void setAirdev(AirdevXml airdev) { this.airdev = airdev; }
	 * 
	 * public AmmeterDevXml getAmmeterdev() { return ammeterdev; }
	 * 
	 * public void setAmmeterdev(AmmeterDevXml ammeterdev) { this.ammeterdev =
	 * ammeterdev; }
	 * 
	 * public HumitureDevXml getHumituredev() { return humituredev; }
	 * 
	 * public void setHumituredev(HumitureDevXml humituredev) { this.humituredev =
	 * humituredev; }
	 * 
	 * public InfraredDevXml getInfrareddev() { return infrareddev; }
	 * 
	 * public void setInfrareddev(InfraredDevXml infrareddev) { this.infrareddev =
	 * infrareddev; }
	 * 
	 * public LampDevXml getLampdev() { return lampdev; }
	 * 
	 * public void setLampdev(LampDevXml lampdev) { this.lampdev = lampdev; }
	 */
}
