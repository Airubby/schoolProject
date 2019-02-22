package com.loncom.ismac.servlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.loncom.ismac.annotation.MethodInfo;
import com.loncom.ismac.annotation.Modular;
import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.bean.RquestObject;
import com.loncom.ismac.user.bean.UserBean;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.CMD;
import com.loncom.ismac.util.UtilTime;
import com.ndktools.javamd5.core.MD5;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 用户业务类
 * @author XieGuangJun
 *
 */
@Modular(MODULARNAME="用户模块")
public class UserAction extends BaseServlet {
	
	private UserBean obj=new UserBean();
	MD5 md5 = new MD5();
	List<UserBean> userList=new ArrayList<UserBean>();
	RquestObject remsg = new RquestObject();
	
	/**
	 * 登录接口
	 * @return
	 * @throws Exception
	 */
	@MethodInfo(METHOD="/user/login",ISLOG=false,LOGSNAME="登录")
	public RquestObject login()throws Exception{
		String psword = md5.getMD5ofStr(obj.getPsword());
		obj.setPsword(psword);
		userList=baseservice.query(obj);
		if(userList.size()>0) {
			obj=userList.get(0);
			if("3".equals(obj.getRoleid())) {
				remsg.setErr_code("1");
				remsg.setErr_msg("此用户无法登陆系统!");
			}else {
				if("1".equals(obj.getState())) {
					long startTime=UtilTime.getTimes(obj.getTime_start()).getTime();
					long endTime=UtilTime.getTimes(obj.getTime_end()).getTime();
					long time=new Date().getTime();
					if(startTime<time && time<endTime) {
						remsg.setErr_code("0");
						remsg.setErr_msg("登录成功!");
						remsg.setData(JSONObject.fromObject(obj).toString());
					}else {
						remsg.setErr_code("1");
						remsg.setErr_msg("此用户已过期!");
					}
				}
			}
		}else {
			remsg.setErr_code("1");
			remsg.setErr_msg("用户不存在或密码错误!");
		}
		return remsg;
	}
	
	@MethodInfo(METHOD="/user/out",LOGSNAME="退出系统")
	public String out() throws Exception{
		AppContext.getSID().remove(getRequest().getSession().getId());
		return "true";
	}
	
	@MethodInfo(METHOD="/user/add",LOGSNAME="新增")
	public String add() throws Exception{
		obj.setId(BaseUtil.getUUID());
		obj.setState("1");
		obj.setPsword(md5.getMD5ofStr(obj.getPsword()));
		baseservice.Add(obj);
		return null;
	}
	
	@MethodInfo(METHOD="/user/query",LOGSNAME="分页查询用户")
	public String query() throws Exception{
		//JSONObject.fromObject(object);
		String name=getRequest().getParameter("name");
		if(BaseUtil.isNotNull(name)) {
			obj.setName(name);
		}
		return JSONArray.fromObject(baseservice.query(obj)).toString();
	}
	@MethodInfo(METHOD = "/user/synuserid", LOGSNAME = "异步验证账号是否可用", ISLOG = false)
	public RquestObject synagentbm() throws Exception {
		String userid = getRequest().getParameter("userid");
		obj.setUserid(userid);
		userList=baseservice.query(obj);
		if(userList.size()>0) {
			remsg.setErr_code("1");
			remsg.setErr_msg("用户名称已被占用!");
		}else {
			remsg.setErr_code("0");
			remsg.setErr_msg("可以使用!");
		}
		return remsg;
	}
	@MethodInfo(METHOD = "/user/delete", ISLOG = true, LOGSNAME = "删除")
	public String delete() throws Exception{
		String id=getRequest().getParameter("id");
		obj.setId(id);
		baseservice.delete(obj);
		return null;
	}
	@MethodInfo(METHOD = "/user/details", LOGSNAME = "详情", ISLOG = false)
	public String details() throws Exception {
		String id = getRequest().getParameter("id");
		obj.setId(id);
		userList = baseservice.query(obj);
		if (userList.size() > 0) {
			obj = userList.get(0);
			return JSONObject.fromObject(obj).toString();
		}
		return null;
	}
	@MethodInfo(METHOD = "/user/update", ISLOG = true, LOGSNAME = "修改")
	public String update() throws Exception {
		if (obj.getPsword().length() < 30) {
			obj.setPsword(md5.getMD5ofStr(obj.getPsword()));
		}
		baseservice.update(obj);
		return null;
	}
	
	@MethodInfo(METHOD = "/user/updatestate", ISLOG = false, LOGSNAME = "修改状态")
	public String updatestate() throws Exception {
		String ids = getRequest().getParameter("ids");
		String state = getRequest().getParameter("state");
		if (BaseUtil.isNotNull(ids)) {
			ids = "'" + ids.replace(",", "','") + "'";
		}
		baseservice.exeSql(String.format(CMD.USER_UPDATASTATE, state, ids));
		return null;
	}
	

	public UserBean getObj() {
		return obj;
	}

	public void setObj(UserBean obj) {
		this.obj = obj;
	}
	

}
