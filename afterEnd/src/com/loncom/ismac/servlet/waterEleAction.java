package com.loncom.ismac.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loncom.ismac.alarm.bean.AlarmBean;
import com.loncom.ismac.alarm.bean.AlarmThresholdBean;
import com.loncom.ismac.annotation.MethodInfo;
import com.loncom.ismac.annotation.Modular;
import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.util.BaseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Modular(MODULARNAME="水电接入")
public class waterEleAction extends BaseServlet {
	
	@MethodInfo(METHOD="/waterele/query",ISLOG=false)
	public String query()throws Exception{
		List list=new ArrayList();
		String sql="SELECT a.useelec, b.* from m_roomelec a LEFT OUTER JOIN m_roominfo b on a.roomid=b.roomid";
		list = baseservice.getSqlListS(sql,DB.SER);
		return JSONArray.fromObject(list).toString();
	}

}
