package com.loncom.ismac.user.service;

import java.util.List;
import java.util.Map;

import com.loncom.ismac.service.IBaseService;
import com.loncom.ismac.user.bean.Sysmenu;


public interface ISysMenuService extends IBaseService<Sysmenu> {
	List<Map<String, Object>> getSysMenuDev();
	/**
	 * 根据角色加载权限
	 * @param roleid
	 * @return
	 * @throws Exception
	 */
	List<Map<String,String>> QueryRoleMenu(String roleid)throws Exception;
}
