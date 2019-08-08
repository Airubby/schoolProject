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


@Modular(MODULARNAME="告警模块")
public class AlarmAction extends BaseServlet {
	
	private AlarmThresholdBean obj=new AlarmThresholdBean();
	
	@MethodInfo(METHOD="/alarm/query",ISLOG=false)
	public String query()throws Exception{
		String createTime=getRequest().getParameter("createTime");
		List<AlarmBean> list=new ArrayList<AlarmBean>();
		String sql="select * from alarm where createTime >= '%s'";
		sql=String.format(sql,createTime);
		list = baseservice.getSqlListS(sql,DB.HIS);
		return JSONArray.fromObject(list).toString();
	}
	
	@MethodInfo(METHOD="/alarm/count",ISLOG=false)
	public String count()throws Exception{
		String createTime=getRequest().getParameter("createTime");
		List<AlarmBean> list=new ArrayList<AlarmBean>();
		String sql="select * from alarm where createTime >= '%s'";
		sql=String.format(sql,createTime);
		list = baseservice.getSqlListS(sql,DB.HIS);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("key", "alarmAll");
		map.put("value", list.size());
		return JSONObject.fromObject(map).toString();
	}
	
	@MethodInfo(METHOD="/alarm/threshold",ISLOG=false)
	public String threshold()throws Exception{
		if(baseservice.query(obj).size()>0) {
			return JSONObject.fromObject(baseservice.query(obj).get(0)).toString();
		}else {
			return null;
		}
	}
	@MethodInfo(METHOD="/alarm/updateThreshold",LOGSNAME="告警",ISLOG=true)
	public String updatethreshold()throws Exception{
		obj.setModel(getRequest().getParameter("model"));
		obj.setDayvalue(getRequest().getParameter("dayvalue"));
		obj.setMonthvalue(getRequest().getParameter("monthvalue"));
		if(BaseUtil.isNotNull(getRequest().getParameter("id"))) {
			obj.setId(getRequest().getParameter("id"));
			baseservice.update(obj);
		}else {
			obj.setId("1");
			baseservice.Add(obj);
		}
		setOtioncontent(obj.toString());
		return null;
	}

	public AlarmThresholdBean getObj() {
		return obj;
	}

	public void setObj(AlarmThresholdBean obj) {
		this.obj = obj;
	}
	
	

}
