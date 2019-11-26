package com.loncom.ismac.bean.xml;

import javax.xml.bind.annotation.XmlAttribute;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Group")
public class GroupXml {
	@XStreamAsAttribute
	private String groupno;
	@XStreamAsAttribute
	private String groupname;
	@XStreamAsAttribute
	private String groupfloor;  //有几层楼
	@XStreamAsAttribute
	private String details;//每栋楼
	@XStreamAsAttribute
	private String acreage;//建筑面积
	@XStreamAsAttribute
	private String countnumber;//总人数
	@XStreamAsAttribute
	private String classroomcount;//教室总数
	@XStreamAsAttribute
	private String dormcount;//办公室总数
	
	private ClassroomgroupXml classroomgroup=new ClassroomgroupXml();//教室对象
	
	private OfficegroupXml officegroup=new OfficegroupXml();//办公室对象
	/*private AirconditionsXml airconditions = new AirconditionsXml();
	private TemperaturesXml temperatures = new TemperaturesXml();*/

	@XmlAttribute(name = "groupno")
	public String getGroupno() {
		return groupno;
	}

	public void setGroupno(String groupno) {
		this.groupno = groupno;
	}
	@XmlAttribute(name = "groupname")
	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	@XmlAttribute(name = "groupfloor")
	public String getGroupfloor() {
		return groupfloor;
	}

	public void setGroupfloor(String groupfloor) {
		this.groupfloor = groupfloor;
	}

	@XmlAttribute(name = "details")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@XmlAttribute(name = "acreage")
	public String getAcreage() {
		return acreage;
	}

	public void setAcreage(String acreage) {
		this.acreage = acreage;
	}
	@XmlAttribute(name = "countnumber")
	public String getCountnumber() {
		return countnumber;
	}
	
	
	@XmlAttribute(name = "classroomcount")
	public String getClassroomcount() {
		return classroomcount;
	}

	public void setClassroomcount(String classroomcount) {
		this.classroomcount = classroomcount;
	}
	@XmlAttribute(name = "dormcount")
	public String getDormcount() {
		return dormcount;
	}

	public void setDormcount(String dormcount) {
		this.dormcount = dormcount;
	}

	public void setCountnumber(String countnumber) {
		this.countnumber = countnumber;
	}

	public ClassroomgroupXml getClassroomgroup() {
		return classroomgroup;
	}

	public void setClassroomgroup(ClassroomgroupXml classroomgroup) {
		this.classroomgroup = classroomgroup;
	}

	public OfficegroupXml getOfficegroup() {
		return officegroup;
	}

	public void setOfficegroup(OfficegroupXml officegroup) {
		this.officegroup = officegroup;
	}
	

}
