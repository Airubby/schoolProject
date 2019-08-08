package com.loncom.ismac.test;

import java.text.ParseException;
import java.util.List;

import com.loncom.ismac.bean.PageBean;
import com.loncom.ismac.bean.User;
import com.loncom.ismac.service.IBaseService;
import com.loncom.ismac.service.impl.BaseServiceImpl;
import com.loncom.ismac.syslog.bean.Syslog;

public class Test {

	public static void main(String[] args) throws ParseException {
		//UtilTime.getTimes("2019-03-27 17:21:43").getTime();
		IBaseService service=new BaseServiceImpl();
		try {
			Syslog syslg=new Syslog();
			syslg.setOpe_id("00102");
		 PageBean list=	service.queryPage(syslg);
		 System.out.println(list.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
