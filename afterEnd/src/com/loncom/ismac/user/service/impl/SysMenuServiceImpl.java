package com.loncom.ismac.user.service.impl;

import java.util.List;
import java.util.Map;

import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.service.impl.BaseServiceImpl;
import com.loncom.ismac.user.bean.Sysmenu;
import com.loncom.ismac.user.service.ISysMenuService;

public class SysMenuServiceImpl extends BaseServiceImpl<Sysmenu> implements ISysMenuService {


	
	public  List<Map<String, Object>> getSysMenuDev(){
		List<Map<String, Object>> list = this.getSqlList(
				"select a.id,a.`Name`,b.`Name`as typename,b.Devtypeimgurld,b.Devtypeimgurlx,b.ParentId,b.ID as typeid from mgrobj a,mgrobjtype b, brandinfo "
						+ "c where a.MgrObjTypeId<>'50' and a.MgrObjTypeId=c.template_code and c.id=b.ID ");
		return list;
	}

	public List<Map<String,String>> QueryRoleMenu(String roleid) throws Exception {
		List<Map<String, String>> list=getSqlListS("select  sys_sysmenu.ID,sys_sysmenu.MenuName,sys_sysmenu.MenuSrc,sys_sysmenu.MenuLoga,sys_sysmenu.ParentID,sys_sysmenu.MenuIndex,sys_rolel.roperid,sys_sysmenu.fullname,sys_sysmenu.`code` from rolel as sys_rolel ,sysmenu as sys_sysmenu where sys_rolel.RoleID='"+roleid+"' and sys_sysmenu.ID=SysMenuID ORDER BY sys_sysmenu.ParentID,sys_sysmenu.MenuIndex", DB.SYS);
		return list;
	}

	
}
