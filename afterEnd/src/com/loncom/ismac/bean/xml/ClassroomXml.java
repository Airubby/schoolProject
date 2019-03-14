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
	@XStreamAsAttribute
	private String code;// 编码
	@XStreamAsAttribute
	private String classname;// 教室名称
	@XStreamAsAttribute
	private String model;// 模式 0手动 1自动
	@XStreamAsAttribute
	private String times;// 在自动的情况下times秒监测一下
	@XStreamAsAttribute
	private String timegroup;// 时间组
	
	@XStreamImplicit(itemFieldName = "item")
	private List<BaseItemDevXml> baseItem = new ArrayList<BaseItemDevXml>();
	
	/*private AirdevXml airdev = new AirdevXml();//空调

	private AmmeterDevXml ammeterdev = new AmmeterDevXml();//电表

	private HumitureDevXml humituredev = new HumitureDevXml();//温湿度

	private InfraredDevXml infrareddev = new InfraredDevXml();//红外

	private LampDevXml lampdev = new LampDevXml();//电灯
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
	
	@XmlElement(name = "item")
	public List<BaseItemDevXml> getBaseItem() {
		return baseItem;
	}

	public void setBaseItem(List<BaseItemDevXml> baseItem) {
		this.baseItem = baseItem;
	}

	
	
	
	
	/*public AirdevXml getAirdev() {
		return airdev;
	}

	public void setAirdev(AirdevXml airdev) {
		this.airdev = airdev;
	}

	public AmmeterDevXml getAmmeterdev() {
		return ammeterdev;
	}

	public void setAmmeterdev(AmmeterDevXml ammeterdev) {
		this.ammeterdev = ammeterdev;
	}

	public HumitureDevXml getHumituredev() {
		return humituredev;
	}

	public void setHumituredev(HumitureDevXml humituredev) {
		this.humituredev = humituredev;
	}

	public InfraredDevXml getInfrareddev() {
		return infrareddev;
	}

	public void setInfrareddev(InfraredDevXml infrareddev) {
		this.infrareddev = infrareddev;
	}

	public LampDevXml getLampdev() {
		return lampdev;
	}

	public void setLampdev(LampDevXml lampdev) {
		this.lampdev = lampdev;
	}
*/
}
