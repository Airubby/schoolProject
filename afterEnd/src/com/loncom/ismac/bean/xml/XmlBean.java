package com.loncom.ismac.bean.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


//设置生成的xml的根节点的名称
@XmlRootElement(name = "root")
// 设置根据字段还是方法生成
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(name="Command")
	private Command Command;

	public Command getCommand() {
		return Command;
	}

	public void setCommand(Command command) {
		Command = command;
	}

	/*public static void main(String[] args) {
		
		
		
		 * sydw.setHospitalid("001"); sydw.setHospitalname("11");
		 * sydw.setAddr("dfffffffffffffffff"); sydw.setTel("111111111");
		 
		try {
			IBaseService base=new BaseServiceImpl();
			Xml sydw = new Xml();
			Command com = new Command();
			List list=new ArrayList();
			DevheadXml devhend=new DevheadXml();
			DevvouXml devvou=new DevvouXml();
			DeviceVouListXml devicevou=new DeviceVouListXml();
			//devhend.setDevname("1212");
			
			//list.add(devhend);
			//devhend=new DevheadXml();
			//devhend.setDevname("233333");
			list=base.query(devhend);
			com.setDevhead(list);
			
			list=new ArrayList();
			list=base.query(devvou);
			devicevou.setDevvoulist(list);
			
			com.setDevicelist(devicevou);
			sydw.setCommand(com);
			
			
			
			 * String xml=JaxbUtil.toXml(sydw); YwYyghSydw sydw1 = new
			 * YwYyghSydw(); sydw1=JaxbUtil.fromXml(xml,YwYyghSydw.class);
			 * 
			 
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
}
