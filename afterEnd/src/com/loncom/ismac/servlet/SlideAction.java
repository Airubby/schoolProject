package com.loncom.ismac.servlet;

import com.loncom.ismac.annotation.MethodInfo;
import com.loncom.ismac.annotation.Modular;
import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.slide.bean.SlideBean;
import com.loncom.ismac.util.BaseUtil;

import net.sf.json.JSONArray;

@Modular(MODULARNAME="幻灯片模块")
public class SlideAction extends BaseServlet  {
	private SlideBean obj=new SlideBean();
	/**
	 * 获取所有 
	 * @return
	 * @throws Exception
	 */
	@MethodInfo(METHOD="/slide/query",ISLOG=false)
	public String query()throws Exception{
		return JSONArray.fromObject(baseservice.query(obj)).toString();
	}
	
	/**
	 * 新增幻灯片
	 * @return
	 * @throws Exception
	 */
	@MethodInfo(METHOD="/slide/add",LOGSNAME="新增幻灯片")
	public String add()throws Exception{
		
		obj.setId(BaseUtil.getUUID());
		obj.setUrl(getRequest().getParameter("url"));
		baseservice.Add(obj);
		setOtioncontent("路径:"+obj.getUrl());
		return null;
	}
	
	/**
	 * 删除幻灯片
	 * @return
	 * @throws Exception
	 */
	@MethodInfo(METHOD = "/slide/delete",ISLOG=false)
	public String delete() throws Exception{
		String id=getRequest().getParameter("id");
		obj.setId(id);
		baseservice.delete(obj);
		return null;
	}
	
}
