package com.loncom.ismac.bean.xml;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.loncom.ismac.bean.testxml.Dataroot;
import com.loncom.ismac.bean.tm.Dataroot1;
import com.loncom.ismac.logs.Logs;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.FileUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlEdiParser {

	/**
	 * 解析单个设备数据报文,可用于同步和异步两种情况
	 * 
	 * @param response
	 * @param ediType
	 *            正确的报文类型
	 * @return
	 */
	public static GroupControlXml parseDevData(String response) {
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(GroupControlXml.class);
		GroupControlXml groupcontrol = null;
		// 解析
		try {
			if (response != null) {
				Document document = DocumentHelper.parseText(response.replaceFirst("gb2312", "UTF-8"));
				// 检查报文类型
				List<?> list1 = document.selectNodes("//groupcontrol");
				for (Object obj : list1) {
					Element e = (Element) obj;
					groupcontrol = (GroupControlXml) xstream.fromXML(e.asXML());
				}
			} else {
				Logs.logsync("报文解析，接受到空的系统状态报文2!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logs.logsync("报文解析，报文结构异常:" + e.getLocalizedMessage() + "\n报文:" + response);
		}
		return groupcontrol;
	}
	
	
	/**
	 * 解析单个设备数据报文,可用于同步和异步两种情况
	 * 
	 * @param response
	 * @param ediType
	 *            正确的报文类型
	 * @return
	 */
	public static  RootXml parseRootData(String response) {
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(RootXml.class);
		RootXml groupcontrol = null;
		// 解析
		try {
			if (response != null) {
				Document document = DocumentHelper.parseText(response.replaceFirst("gb2312", "UTF-8"));
				// 检查报文类型
				List<?> list1 = document.selectNodes("//root");
				for (Object obj : list1) {
					Element e = (Element) obj;
					groupcontrol = (RootXml) xstream.fromXML(e.asXML());
				}
			} else {
				Logs.logsync("报文解析，接受到空的系统状态报文2!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logs.logsync("报文解析，报文结构异常:" + e.getLocalizedMessage() + "\n报文:" + response);
		}
		return groupcontrol;
	}
	
	
	/**
	 * 解析单个设备数据报文,可用于同步和异步两种情况
	 * 
	 * @param response
	 * @param ediType
	 *            正确的报文类型
	 * @return
	 */
	public static  RootDevXml parseRootDevData(String response) {
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(RootDevXml.class);
		RootDevXml groupcontrol = null;
		// 解析
		try {
			if (response != null) {
				Document document = DocumentHelper.parseText(response.replaceFirst("gb2312", "UTF-8"));
				// 检查报文类型
				List<?> list1 = document.selectNodes("//root");
				for (Object obj : list1) {
					Element e = (Element) obj;
					groupcontrol = (RootDevXml) xstream.fromXML(e.asXML());
				}
			} else {
				Logs.logsync("报文解析，接受到空的系统状态报文2!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logs.logsync("报文解析，报文结构异常:" + e.getLocalizedMessage() + "\n报文:" + response);
		}
		return groupcontrol;
	}
	
	/**
	 * 解析单个设备数据报文,可用于同步和异步两种情况
	 * 
	 * @param response
	 * @param ediType
	 *            正确的报文类型
	 * @return
	 */
	public static  Dataroot parseDevvouData(String response) {
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(Dataroot.class);
		Dataroot groupcontrol = null;
		// 解析
		try {
			if (response != null) {
				Document document = DocumentHelper.parseText(response.replaceFirst("gb2312", "UTF-8"));
				// 检查报文类型
				List<?> list1 = document.selectNodes("//dataroot");
				for (Object obj : list1) {
					Element e = (Element) obj;
					groupcontrol = (Dataroot) xstream.fromXML(e.asXML());
				}
			} else {
				Logs.logsync("报文解析，接受到空的系统状态报文2!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logs.logsync("报文解析，报文结构异常:" + e.getLocalizedMessage() + "\n报文:" + response);
		}
		return groupcontrol;
	}
	
	
	/**
	 * 解析单个设备数据报文,可用于同步和异步两种情况
	 * 
	 * @param response
	 * @param ediType
	 *            正确的报文类型
	 * @return
	 */
	public static  Dataroot1 parseDataroot1Data(String response) {
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(Dataroot1.class);
		Dataroot1 groupcontrol = null;
		// 解析
		try {
			if (response != null) {
				Document document = DocumentHelper.parseText(response.replaceFirst("gb2312", "UTF-8"));
				// 检查报文类型
				List<?> list1 = document.selectNodes("//dataroot");
				for (Object obj : list1) {
					Element e = (Element) obj;
					groupcontrol = (Dataroot1) xstream.fromXML(e.asXML());
				}
			} else {
				Logs.logsync("报文解析，接受到空的系统状态报文2!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logs.logsync("报文解析，报文结构异常:" + e.getLocalizedMessage() + "\n报文:" + response);
		}
		return groupcontrol;
	}
	
	
	
	public static void main(String[] args) {
		try {
			/*
			 * if (XmlEdiParser.class.getClassLoader().getResource( "../../xml/" +
			 * object.getXmlurl()) != null) {
			 */
			String url = FileUtil
					.readToString("D:/111/tm.xml");
			System.out.println(url);
			Dataroot1 datarot=parseDataroot1Data(url);
			BaseUtil.ObjToXMl(datarot,"d:/111/devvou2.xml");
			System.out.println(11111111);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
