package com.loncom.ismac.servlet;

import javax.servlet.annotation.WebServlet;

import com.loncom.ismac.annotation.MethodInfo;
import com.loncom.ismac.syslog.bean.Syslog;
import com.loncom.ismac.util.BaseUtil;

import net.sf.json.JSONObject;



@WebServlet("/SyslogAction")
public class SyslogAction extends BaseServlet{
	
	Syslog syslog=new Syslog();
	
	@MethodInfo(METHOD="/syslog/query",LOGSNAME="系统日志查询",ISLOG=false)
	public String query() throws Exception{
		String value=getStringFromStream(getRequest());
		syslog=(Syslog) JSONObject.toBean(JSONObject.fromObject(value),Syslog.class);
		if(!BaseUtil.isNotNull(syslog.getOpe_id())){
	    	syslog.setOpe_id("00102");
	    }
		return JSONObject.fromObject(baseservice.queryPage(syslog)).toString();
	}

	public Syslog getSyslog() {
		return syslog;
	}

	public void setSyslog(Syslog syslog) {
		this.syslog = syslog;
	}
	
	

}
