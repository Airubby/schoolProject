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
		
		String pageIndex=getRequest().getParameter("pageIndex");
		String pageSize=getRequest().getParameter("pageSize");
		String userid=getRequest().getParameter("userid");
		/*syslog=(Syslog) JSONObject.toBean(JSONObject.fromObject(value),Syslog.class);*/
		syslog.setPageIndex(Integer.parseInt(pageIndex));
		syslog.setPageSize(Integer.parseInt(pageSize));
		syslog.setUserid(userid);
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
