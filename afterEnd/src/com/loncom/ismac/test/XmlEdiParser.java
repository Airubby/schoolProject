package com.loncom.ismac.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.loncom.ismac.test.Dataroot1;
import com.loncom.ismac.logs.Logs;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.FileUtil;
import com.loncom.ismac.util.JaxbUtil;
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
	public static Dataroot1 parseDataroot1Data(String response) {
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
		System.out.println(groupcontrol);
		return groupcontrol;
	}
	
	public static void ObjToXMl1(Dataroot1 groupxml, String filepath) throws IOException, JAXBException {
		if (groupxml != null) {
			String content = JaxbUtil.toXml(groupxml);
			FileUtil.writeFileContent(filepath, content);
		}
	}
	
	public static void main(String[] args) {
		try {
			/*
			 * if (XmlEdiParser.class.getClassLoader().getResource( "../../xml/" +
			 * object.getXmlurl()) != null) {
			 */
			String port2="21001";
			String port1="21000";
			String port="21002";
			String url = FileUtil.readToString("D:/111/test3xiang.xml");
			String ip= FileUtil.readToString("D:/111/all3xiang.txt");
			String[] listip=ip.split(",");
			Dataroot1 datarot=parseDataroot1Data(url);
			List<DevheadXml1> list=new ArrayList<DevheadXml1>();
			List<DevheadXml1> list1=new ArrayList<DevheadXml1>();
			for (int i = 0; i <listip.length ; i++) {
				Dataroot1 datarot1=parseDataroot1Data(url);
				
				list1=new ArrayList<DevheadXml1>();
				String _ip=listip[i].trim();
				String[] ips=_ip.split("\\.");
				String str=ips[2]+ips[3];
				String comsetting="";
				if(BaseUtil.isNotNull(port)) {
					comsetting=_ip+":"+port;
				}else {
					comsetting=_ip;
				}
				for (int j = 0; j < datarot1.getDev().size(); j++) {
					datarot1.getDev().get(j).setDevid(str+(j+1));	
					String name=datarot1.getDev().get(j).getDevname();
					if(name.equals("干接点")) {
						datarot1.getDev().get(j).setComsetting(_ip+":"+port1);
					}else {
						datarot1.getDev().get(j).setComsetting(comsetting);
					}
					datarot1.getDev().get(j).setDevname(_ip+"_"+name);
					list1.add(datarot1.getDev().get(j));
				}
				list.addAll(list1);
			}
			datarot.setDev(list);
			ObjToXMl1(datarot,"d:/111/devvou2.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
