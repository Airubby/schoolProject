package com.loncom.ismac.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.loncom.ismac.bean.xml.RootXml;
import com.loncom.ismac.logs.Logs;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.FileUtil;
import com.loncom.ismac.util.JaxbUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlEditItem {


	
	/**
	 * 解析单个设备数据报文,可用于同步和异步两种情况
	 * 
	 * @param response
	 * @param ediType
	 *            正确的报文类型
	 * @return
	 */
	public static  DataItem parseDataroot1Data(String response) {
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(DataItem.class);
		DataItem groupcontrol = null;
		// 解析
		try {
			if (response != null) {
				Document document = DocumentHelper.parseText(response.replaceFirst("gb2312", "UTF-8"));
				// 检查报文类型
				List<?> list1 = document.selectNodes("//dataroot");
				for (Object obj : list1) {
					Element e = (Element) obj;
					groupcontrol = (DataItem) xstream.fromXML(e.asXML());
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
	
	 /* 对象转换XML
	 * 
	 * @param groupxml
	 * @param filepath
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	public static void ObjToXMl(DataItem datarot, String filepath) throws IOException, JAXBException {
		if (datarot != null) {
			String content = JaxbUtil.toXml(datarot);
			System.out.println(content);
			FileUtil.writeFileContent(filepath, content);
		}
	}
	
	public static void main(String[] args) {
		try {
			String url = FileUtil.readToString("D:/111/sysconfig.xml");
			String ip= FileUtil.readToString("D:/111/devconfig3xiang.txt");
			String[] listip=ip.split(",");
			DataItem datarot=parseDataroot1Data(url);
			System.out.println(datarot);
			List<DataItemXml> list=new ArrayList<DataItemXml>();
			List<DataItemXml> list1=new ArrayList<DataItemXml>();
			for (int i = 0; i <listip.length ; i++) {
				DataItem dataitem=parseDataroot1Data(url);
				
				list1=new ArrayList<DataItemXml>();
				String _ip=listip[i].trim();
				String[] ips=_ip.split("\\.");
				String str=ips[2]+ips[3];
				for(int j=0;j<dataitem.getItem().size();j++) {
					String s=dataitem.getItem().get(j).getPointid().split("_")[1];
					dataitem.getItem().get(j).setPointid(str+s);
					list1.add(dataitem.getItem().get(j));
				}
				list.addAll(list1);
			}
			datarot.setItem(list);
			ObjToXMl(datarot,"d:/111/item1.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
