package com.loncom.ismac.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.loncom.ismac.annotation.MethodInfo;
import com.loncom.ismac.annotation.Modular;
import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.bean.RquestObject;
import com.loncom.ismac.user.bean.RoleBean;
import com.loncom.ismac.user.bean.RolelBean;
import com.loncom.ismac.user.bean.SysMenuServiceBean;
import com.loncom.ismac.user.bean.TreeService;
import com.loncom.ismac.user.bean.UserBean;
import com.loncom.ismac.user.bean.WebServiceSysmenu;
import com.loncom.ismac.user.service.ISysMenuService;
import com.loncom.ismac.user.service.impl.SysMenuServiceImpl;
import com.loncom.ismac.util.BaseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Modular(MODULARNAME="用户模块")
public class RoleAction extends BaseServlet {
	private RoleBean model = new RoleBean();
	private RolelBean rolel = new RolelBean();
	/* private Rooraddr rooraddr = new Rooraddr(); */
	private List<RolelBean> item = new ArrayList<RolelBean>();
	/* private List<Rooraddr> itemr = new ArrayList<Rooraddr>(); */
	private ISysMenuService menuservice = new SysMenuServiceImpl();

	public RoleAction() {
		setModel(model);
	}

	/**
	 * 新增角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@MethodInfo(METHOD = "/role/add", LOGSNAME = "add")
	public String add() throws Exception {
		String id = BaseUtil.getUUID();
		model.setId(id);
		baseservice.Add(model);
		for (RolelBean rolel2 : item) {
			rolel2.setRoleid(id);
			rolel2.setId(BaseUtil.getUUID());
			baseservice.Add(rolel2);
		}
		setOtioncontent("新增角色：" + model.toString());
		return "";
	}
	
	/**
	 * 新增角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@MethodInfo(METHOD = "/role/query", LOGSNAME = "查询角色列表")
	public String query() throws Exception {
		String name=getRequest().getParameter("name");
		if(BaseUtil.isNotNull(name)) {
			model.setName(name);
		}
		
		return JSONArray.fromObject(baseservice.query(model)).toString();
	}
	
	
	/**
	 * 查看详情
	 * 
	 * @return
	 * @throws Exception
	 */
	@MethodInfo(METHOD = "/role/details", LOGSNAME = "查询角色列表")
	public String details() throws Exception {
	/*	String id=getRequest().getParameter("id");
		model.setId(id);*/
		 List list=baseservice.query(model);
		 if(list.size()>0){
			return  JSONObject.fromObject(list.get(0)).toString();
		 }
		
		return null;
	}
	

	/**
	 * 根据角色获取菜单权限
	 * 
	 * @return
	 * @throws Exception
	 */
	@MethodInfo(METHOD = "/role/rolemenu", LOGSNAME = "rolemenu", ISLOG = false)
	public Object queryRoleMenuTree() throws Exception {
		String roleid = getRequest().getParameter("id");
		List<Map<String, String>> map = menuservice.QueryRoleMenu(roleid);
		List<String> list = new ArrayList<String>();
		for (Map<String, String> map2 : map) {
			/*if (AppContext.getLodsysmenu().contains(map2.get("ID"))
					|| AppContext.getLodsysmenu().contains(map2.get("PARENTID"))) {*/
				if (BaseUtil.isNotNull(map2.get("ROPERID"))) {
					String[] split = map2.get("ROPERID").split(",");
					if (!BaseUtil.isNotNull(map2.get("CODE"))) {
						for (String string : split) {
							list.add(map2.get("ID") + "" + string);
						}
					} else {
						list.add(map2.get("ID"));
					}
				}
			/*}*/
		}
		return list;
	}

	/**
	 * 根据角色获取菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	@MethodInfo(METHOD = "/role/rolequery", LOGSNAME = "rolequery", ISLOG = false)
	public Object RoleQuery() throws Exception {
		String roleid = getRequest().getParameter("id");
		System.out.println("#########################");
		List<Map<String, String>> map = menuservice.QueryRoleMenu(roleid);
		List<WebServiceSysmenu> listbean = new ArrayList<WebServiceSysmenu>();
		WebServiceSysmenu menubean = null;
		for (Map<String, String> sysmeuninfo : map) {

			boolean bl = true;
			menubean = new WebServiceSysmenu();
			if (!sysmeuninfo.get("PARENTID").equals("0"))
				if (listbean.size() > 0) {
					RecursionTree(listbean, sysmeuninfo, menubean, listbean);
					bl = false;
				}
			if (bl) {
				/*if (AppContext.getLodsysmenu().contains(sysmeuninfo.get("ID"))
						|| AppContext.getLodsysmenu().contains(sysmeuninfo.get("PARENTID"))) {*/
					menubean.setName(sysmeuninfo.get("FULLNAME"));
					menubean.setMenuname(sysmeuninfo.get("MENUNAME"));
					menubean.setPath("/site/" + sysmeuninfo.get("FULLNAME"));
					menubean.setComponent(sysmeuninfo.get("MENUSRC"));
					menubean.getMeta().put("show", "true");
					menubean.getMeta().put("type", "nav");
					menubean.getMeta().put("limits", getRolel(sysmeuninfo.get("ROPERID")));
					menubean.getMeta().put("icon", sysmeuninfo.get("MENULOGA"));
					menubean.setId(sysmeuninfo.get("ID"));
					menubean.setPid(sysmeuninfo.get("PARENTID"));
					listbean.add(menubean);
				/*}*/
			}
		}
		return listbean;
	}

	public void RecursionTree(List<WebServiceSysmenu> list, Map<String, String> sysmeuninfo, WebServiceSysmenu menubean,
			List<WebServiceSysmenu> listbean) {
		for (WebServiceSysmenu treeService : list) {
			WebServiceSysmenu treeService1 = treeService;
			// System.out.println(sysmeuninfo.get("PARENTID").substring(0,
			// 2)+":"+treeService.getId()+":"+sysmeuninfo.get("PARENTID").lastIndexOf(treeService.getId()));

			/*if (AppContext.getLodsysmenu().contains(sysmeuninfo.get("ID"))
					|| AppContext.getLodsysmenu().contains(sysmeuninfo.get("PARENTID"))
					|| AppContext.getLodsysmenu().contains(treeService1.getId())
					|| AppContext.getLodsysmenu().contains(treeService1.getPid())) {*/
				if (sysmeuninfo.get("PARENTID").equals(treeService1.getId())) {
					String limits = getRolel(sysmeuninfo.get("ROPERID"));
					String code = sysmeuninfo.get("CODE");
					menubean.setId(sysmeuninfo.get("ID"));
					menubean.setName(sysmeuninfo.get("MENUNAME"));
					menubean.setName(sysmeuninfo.get("FULLNAME"));
					menubean.setPath(treeService1.getPath() + "/" + sysmeuninfo.get("FULLNAME"));
					menubean.setComponent(sysmeuninfo.get("MENUSRC"));
					menubean.setPid(sysmeuninfo.get("PARENTID"));
					if (BaseUtil.isNotNull(code)) {
						menubean.getMeta().put("type", "fun");
						if (limits.lastIndexOf(code) > -1) {
							menubean.getMeta().put("show", "true");
						} else {
							menubean.getMeta().put("show", "false");
						}
						treeService1 = BaseUtil.getWebServiceSys(listbean, treeService.getPid());
						
						// System.out.println(code+":"+treeService1.getId());
					} else {
						menubean.getMeta().put("show", "true");
						menubean.getMeta().put("type", "nav");
					}
					menubean.getMeta().put("limits", limits);
					menubean.getMeta().put("icon", sysmeuninfo.get("MENULOGA"));
					/*treeService1.getChildren().add(menubean);*/
					if (treeService1 == null) {
						list.add(menubean);
					}else{
						menubean.getMeta().put("limits", limits);
						menubean.getMeta().put("icon", sysmeuninfo.get("MENULOGA"));
						treeService1.getChildren().add(menubean);
					}
					
					break;
				} else {
					if (treeService.getChildren().size() > 0) {
						RecursionTree(treeService.getChildren(), sysmeuninfo, menubean, listbean);
					}
				}
			}
	/*	}*/

	}
	
	
	/**
	 * 获取菜单树
	 * 
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	@MethodInfo(METHOD = "/role/sysmenutree", LOGSNAME = "sysmenutree", ISLOG = false)
	public Object querymenu() throws Exception {
		SysMenuServiceBean sysmenuservice = new SysMenuServiceBean();// 系统菜单业务模型
		List<TreeService> treelist = new ArrayList<TreeService>();
		sysmenuservice.setEnabled("1");
		List<SysMenuServiceBean> list = baseservice.query(sysmenuservice);
		TreeService tree = null;
		for (SysMenuServiceBean object : list) {
		//	System.out.println(object.getId().substring(0, 2)+"id:"+object.getId());
			/*if (AppContext.getLodsysmenu().contains(object.getId().substring(0, 2))
					|| AppContext.getLodsysmenu().contains(object.getParentid())) {*/
			boolean bl = true;
			tree = new TreeService();
			if (!object.getParentid().equals("0"))
				//if (!Common.isNull(object.getCode())) {
					if (treelist.size() > 0) {
						RecursionTree(treelist, object, tree);
						bl = false;
					}
			//	}
			if (bl) {
				if (!BaseUtil.isNotNull(object.getCode())) {
					tree.setId(object.getId());
					tree.setLabel(object.getMenuname());
					tree.setChildren(new ArrayList<TreeService>());
					tree.getMap().put("type", object.getType());
					if (object.getType().equals("1")) {
						TreeService tree1 = new TreeService();
						tree1.setId(object.getId() + "00401");
						tree1.getMap().put("vids", "00401");
						tree1.getMap().put("vid", object.getId());
						tree1.setLabel("可读");
						tree.getChildren().add(tree1);
						tree1 = new TreeService();
						tree1.setId(object.getId() + "00402");
						tree1.getMap().put("vids", "00402");
						tree1.setLabel("可写");
						tree1.getMap().put("vid", object.getId());
						tree.getChildren().add(tree1);
					}
					treelist.add(tree);
				}
			}
			}

		
		return treelist;

	}

	public void RecursionTree(List<TreeService> list, SysMenuServiceBean addr, TreeService tree) {
		for (TreeService treeService : list) {
			if (addr.getParentid().equals(treeService.getId())) {
				if (addr.getType().equals("1")) {
					tree.setId(addr.getId());
					tree.setLabel(addr.getMenuname());
					tree.getMap().put("type", addr.getType());
					if (addr.getType().equals("1")) {
						TreeService tree1 = new TreeService();
						tree1.setId(addr.getId() + "00401");
						tree1.getMap().put("vid", addr.getId());
						tree1.getMap().put("vids", "00401");
						tree1.setLabel("可读");
						tree.getChildren().add(tree1);
						tree1 = new TreeService();
						tree1.setId(addr.getId() + "00402");
						tree1.getMap().put("vid", addr.getId());
						tree1.getMap().put("vids", "00402");
						tree1.setLabel("可写");
						tree.getChildren().add(tree1);
					}
					treeService.getChildren().add(tree);
				}else if(addr.getType().equals("2")) {
					tree = new TreeService();
					tree.setId(addr.getId() );
					tree.getMap().put("vid","00401,00402");
					tree.getMap().put("code", addr.getCode());
					tree.setLabel(addr.getMenuname());
					treeService.getChildren().add(tree);
				}
				/*treeService.getChildren().add(tree);
				
					TreeService tree1 = new TreeService();
					tree1 = new TreeService();
					tree1.setId(addr.getId() );
					tree1.getMap().put("code", addr.getCode());
					tree1.setLabel(addr.getMenuname());
					tree.getChildren().add(tree1);
					treeService.getChildren().add(tree);*/
				
				break;
			} else {
				if (treeService.getChildren().size() > 0) {
					RecursionTree(treeService.getChildren(), addr, tree);
				}
			}
		}

	}

	/**
	 * 根据角色获取权限
	 * 
	 * @param roperid
	 * @return
	 */
	public String getRolel(String roperid) {
		if (BaseUtil.isNotNull(roperid)) {
			String[] size = roperid.split(",");
			roperid = "";
			for (int i = 0; i < size.length; i++) {
				if (i == 0) {
					roperid += AppContext.getMenurole().get(size[i]);
				} else {
					roperid += "," + AppContext.getMenurole().get(size[i]);
				}
			}
		}
		return roperid;
	}

	/**
	 * 根据角色获取站点域
	 * 
	 * @return
	 * @throws Exception
	 *//*
		 * @MethodInfo(METHOD = "/role/addrquery", LOGSNAME = "根据角色获取站点权限") public
		 * String addrquery() throws Exception { String roleid =
		 * getRequest().getParameter("roleid"); rooraddr.setRoleid(roleid);
		 * rooraddr.setHalf("0"); return
		 * net.sf.json.JSONArray.fromObject(baseservice.query(rooraddr)). toString(); }
		 */

	/**
	 * 修改角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@MethodInfo(METHOD = "/role/update", LOGSNAME = "update")
	public Object update() throws Exception {
		baseservice.update(model);
		rolel.setRoleid(model.getId());
		baseservice.delete(rolel);
		for (RolelBean rolel2 : item) {
			rolel2.setId(BaseUtil.getUUID());
			rolel2.setRoleid(model.getId());
			baseservice.Add(rolel2);
		}
		AppContext.InitCompetence();// 同步角色菜单操作权限

		return true;
	}

	/**
	 * 删除角色
	 * 
	 * @return
	 * @throws Exception
	 */

	@MethodInfo(METHOD = "/role/delete", LOGSNAME = "delete")
	public Object delete() throws Exception {
		String id = getRequest().getParameter("id");
		RquestObject remsg = new RquestObject();
		if (id.equals("1")) {
			remsg.setErr_code("1");
			remsg.setErr_msg("该角色为系统初始角色不能删除");
			return remsg;
		}
		UserBean user = new UserBean();
		user.setRoleid(id);
		List list = baseservice.query(user);
		if (list.size() == 0) {
			model.setId(id);
			baseservice.delete(model);
			rolel.setRoleid(id);
			baseservice.delete(rolel);
			setOtioncontent("删除角色:" + model.getId());
			remsg.setErr_code("0");
			remsg.setErr_msg("删除成功");
			remsg.setData("true");
		} else {

			remsg.setErr_code("1");
			remsg.setErr_msg("角色已经被用户绑定不可删除!");
		}
		return remsg;
	}

	public RolelBean getRolel() {
		return rolel;
	}

	public void setRolel(RolelBean rolel) {
		this.rolel = rolel;
	}

	public List<RolelBean> getItem() {
		return item;
	}

	public void setItem(List<RolelBean> item) {

		this.item = item;

	}

	public RoleBean getModel() {
		return model;
	}

	public void setModel(RoleBean model) {
		this.model = model;
	}

	/*
	 * public List<Rooraddr> getItemr() { return itemr; }
	 * 
	 * public void setItemr(List<MorphDynaBean> itemr) {
	 * 
	 * for (MorphDynaBean morphDynaBean : itemr) { rooraddr = new Rooraddr();
	 * rooraddr.setAddrid(morphDynaBean.get("id") + "");
	 * rooraddr.setHalf(morphDynaBean.get("half")+""); this.itemr.add(rooraddr); } }
	 */

	

}
