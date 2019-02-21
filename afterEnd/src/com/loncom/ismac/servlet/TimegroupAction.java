package com.loncom.ismac.servlet;

import java.util.ArrayList;
import java.util.List;

import com.loncom.ismac.annotation.MethodInfo;
import com.loncom.ismac.annotation.Modular;
import com.loncom.ismac.timegroup.bean.TimeDetail;
import com.loncom.ismac.timegroup.bean.TimeGroup;
import com.loncom.ismac.timegroup.service.ITimegroupService;
import com.loncom.ismac.timegroup.service.impl.TimegroupServiceImpl;
import com.loncom.ismac.util.BaseUtil;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 时间相关模块
 * @author XieGuangJun
 */
@Modular(MODULARNAME = "时间配置模块")
public class TimegroupAction extends BaseServlet {
	ITimegroupService service = new TimegroupServiceImpl();
//	private List<MorphDynaBean> item=new ArrayList<MorphDynaBean>();
	private List<TimeDetail> item=new ArrayList<TimeDetail>();
	private List<TimeGroup> timeGroup=new ArrayList<TimeGroup>();
	private TimeGroup obj=new TimeGroup();
	private TimeDetail dobj=new TimeDetail();
	private String id;
	
	@SuppressWarnings("unchecked")
	@MethodInfo(METHOD = "/time/add", LOGSNAME = "新增时间组信息")
	public String addtimegroup() throws Exception{
		obj.setId(BaseUtil.getUUID());
		baseservice.Add(obj);
		for (TimeDetail timeDetail : item) {
			timeDetail.setId(BaseUtil.getUUID());
			timeDetail.setTimegroupid(obj.getId());
			baseservice.Add(timeDetail);
		}
		return "true";
	}
	
	@MethodInfo(METHOD = "/time/query", LOGSNAME = "获取时间组信息")
	public String querytimegroup() throws Exception{
		String name=getRequest().getParameter("name");
		if(BaseUtil.isNotNull(name)) {
			obj.setName(name);
		}
		timeGroup=baseservice.query(obj);
		for (TimeGroup timeGroup : timeGroup) {
			 dobj.setTimegroupid(timeGroup.getId());
			 item=baseservice.query(dobj);
			 timeGroup.setTimeDeteil(item);
		}
		
		return JSONArray.fromObject(timeGroup).toString();
	}
	
	@MethodInfo(METHOD = "/time/detail", LOGSNAME = "查询时间组详情")
	public String querydetail() throws Exception{
		String id=getRequest().getParameter("id");
		if(BaseUtil.isNotNull(id)) {
			obj.setId(id);
		}
		timeGroup=baseservice.query(obj);
		for (TimeGroup timeGroup : timeGroup) {
			 dobj.setTimegroupid(timeGroup.getId());
			 item=baseservice.query(dobj);
			 timeGroup.setTimeDeteil(item);
		}
		if(timeGroup.size()>0) {
			setObj(timeGroup.get(0));
			return JSONObject.fromObject(obj).toString();
		}else {
			return null;
		}
		
	}
	
	@SuppressWarnings("unused")
	@MethodInfo(METHOD = "/time/update", LOGSNAME = "更新时间组详情")
	public String updatetimegroup() throws Exception{
		baseservice.update(obj);
		for (TimeDetail timeDetail : item) {
			timeDetail.setTimegroupid(obj.getId());
			baseservice.delete(timeDetail);
		}
		for (TimeDetail timeDetail : item) {
			timeDetail.setId(BaseUtil.getUUID());
			timeDetail.setTimegroupid(obj.getId());
			baseservice.Add(timeDetail);
		}
		return "true";
	}

	@MethodInfo(METHOD = "/time/delete", LOGSNAME = "删除时间组")
	public String deletetimegroup() throws Exception{
//		obj.setId(id);
//		baseservice.delete(obj);
		if(BaseUtil.isNotNull(id)) {
			String[] ids=id.split(",");
			for(int i=0;i<ids.length;i++) {
				obj.setId(ids[i]);
				dobj.setTimegroupid(ids[i]);
				baseservice.delete(obj);
				baseservice.delete(dobj);
			}
		}
		
		return "true";
	}
	
	public TimeDetail getDobj() {
		return dobj;
	}

	public void setDobj(TimeDetail dobj) {
		this.dobj = dobj;
	}

	public List<TimeDetail> getItem() {
		return item;
	}

	public void setItem(List<TimeDetail> item) {
		this.item=item;
	}
	public TimeGroup getObj() {
		return obj;
	}
	public void setObj(TimeGroup obj) {
		this.obj = obj;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
