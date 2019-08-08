package com.loncom.ismac.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.loncom.ismac.annotation.Attachment;
import com.loncom.ismac.annotation.Table;
import com.loncom.ismac.bean.PageBean;
import com.loncom.ismac.bean.User;
import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.jdbc.DBAccess;
import com.loncom.ismac.logs.Logs;
import com.loncom.ismac.service.IBaseService;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.CMD;

/**
 * * @author 作者 E-mail:
 * 
 * @date 创建时间：2017年7月20日 上午10:58:27
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@SuppressWarnings({ "unchecked", "unused" })

public class BaseServiceImpl<T> implements IBaseService<T> {
	private static Log logger = LogFactory.getLog(BaseServiceImpl.class);

	public List<Map<String, Object>> getSqlList(String sql) {

		DBAccess dba = new DBAccess(DB.SYS);
		List<Map<String, Object>> list2 = null;
		try {
			list2 = DBAccess.pkRs2LM(dba.preExecuteQuery(sql, new Object[] {}));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dba.close();
		}
		return list2;
	}

	public List<Map<String, Object>> getSqlList(String sql, String db) {

		DBAccess dba = new DBAccess(db);
		List<Map<String, Object>> list2 = null;
		try {
			list2 = DBAccess.pkRs2LM(dba.preExecuteQuery(sql, new Object[] {}));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dba.close();
		}
		return list2;
	}

	public List<Map<String, String>> getSqlListS(String sql) throws Exception {

		DBAccess dba = new DBAccess(DB.SYS);
		List<Map<String, String>> list2 = null;
		try {
			list2 = DBAccess.pkRs2LMS(dba.preExecuteQuery(sql, new Object[] {}));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("执行数据失败" + sql);
		} finally {
			dba.close();
		}
		return list2;
	}

	public List<Map<String, String>> getSqlListS(String sql, String db) throws Exception {

		DBAccess dba = new DBAccess(db);
		List<Map<String, String>> list2 = null;
		try {
			list2 = DBAccess.pkRs2LMS(dba.preExecuteQuery(sql, new Object[] {}));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("执行数据失败" + sql);
		} finally {
			dba.close();
		}
		return list2;
	}

	public List<Object> getSqlListBean(String sql, Object obj) throws Exception {

		DBAccess dba = new DBAccess(DB.SYS);
		List<Object> list2 = null;
		try {
			list2 = DBAccess.ListBean(dba.preExecuteQuery(sql, new Object[] {}), obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("执行数据失败" + sql);
		} finally {
			dba.close();
		}
		return list2;
	}

	public List<Object> getSqlListBean(String sql, Object[] parms, Object obj) throws Exception {

		DBAccess dba = new DBAccess(DB.SYS);
		List<Object> list2 = null;
		try {
			list2 = DBAccess.ListBean(dba.preExecuteQuery(sql, parms), obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("执行数据失败" + sql);
		} finally {
			dba.close();
		}
		return list2;
	}

	public List<Object> getSqlListBeanApp(String sql, Object obj) throws Exception {

		DBAccess dba = new DBAccess(DB.SYS);
		List<Object> list2 = null;
		try {
			list2 = DBAccess.ListBeanApp(dba.preExecuteQuery(sql, new Object[] {}), obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("执行数据失败" + sql);
		} finally {
			dba.close();
		}
		return list2;
	}

	public List<Object> getSqlListBeanApp(String sql, Object obj, String db) throws Exception {

		DBAccess dba = new DBAccess(db);
		List<Object> list2 = null;
		try {
			list2 = DBAccess.ListBeanApp(dba.preExecuteQuery(sql, new Object[] {}), obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("执行数据失败" + sql);
		} finally {
			dba.close();
		}
		return list2;
	}

	public List<Object> getSqlListBean(String sql, Object obj, String db) throws Exception {

		DBAccess dba = new DBAccess(db);
		List<Object> list2 = null;
		try {
			list2 = DBAccess.ListBean(dba.preExecuteQuery(sql, new Object[] {}), obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("执行数据失败" + sql);
		} finally {
			dba.close();
		}
		return list2;
	}

	public List<Map<String, Object>> getTableList(String TableName) throws Exception {
		DBAccess dba = new DBAccess(DB.SYS);
		List<Map<String, Object>> list2 = null;
		String sql = "select * from " + TableName + " ";
		try {
			list2 = DBAccess.pkRs2LM(dba.preExecuteQuery(sql, new Object[] {}));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("执行数据失败" + sql);
		} finally {
			dba.close();
		}
		return list2;
	}

	@Override
	public boolean Add(T obj) throws Exception {
		List<Map<String, Object>> list = BaseUtil.setAllComponentsName(obj, CMD.INSERT);
		String table = "";
		if (obj.getClass().isAnnotationPresent(Table.class) == true) {
			Table xs = obj.getClass().getAnnotation(Table.class);
			table = xs.NAME();
		}
		String sqli = "insert into " + table + " (";
		String sqlcoluem = "(";
		String key = "";
		for (Map<String, Object> map : list) {
			key = map.get("key") + "".toUpperCase();
			sqli += "" + key + " ,";
			if (map.get("value") instanceof String) {
				if (BaseUtil.isNotNull(map.get("value") + "")) {
					sqlcoluem += "'" + map.get("value") + "',";
				} else {
					sqlcoluem += "null,";
				}

			} else {
				sqlcoluem += map.get("value") + ",";
			}
		}
		int index = sqli.lastIndexOf(",");
		sqli = sqli.substring(0, index);
		sqli += " ) values ";
		index = sqlcoluem.lastIndexOf(",");
		sqlcoluem = sqlcoluem.substring(0, index);
		sqlcoluem += " );";
		exeSql(sqli + sqlcoluem);

		return true;
	}

	public boolean exeSql(String sql) throws Exception {
		DBAccess dba = new DBAccess(DB.SYS);

		try {
			logger.debug("--sql语句：" + sql);
			return dba.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("执行数据失败" + sql);
		} finally {
			dba.close();
		}
	}

	public boolean exeSql(String sql, String db) throws Exception {
		DBAccess dba = new DBAccess(db);

		try {
			logger.debug("--sql语句：" + sql);
			return dba.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("执行数据失败" + sql);
		} finally {
			dba.close();
		}
	}

	public boolean update(T obj) throws Exception {
		List<Map<String, Object>> list = BaseUtil.setAllComponentsName(obj, CMD.UPDATE);
		String table = "";
		if (obj.getClass().isAnnotationPresent(Table.class) == true) {
			Table xs = obj.getClass().getAnnotation(Table.class);
			table = xs.NAME();
		}
		String sqli = " update  " + table + " set ";
		String where = " where ";
		boolean bs = false;
		for (Map<String, Object> map : list) {
			bs = false;
			Attachment attachment = (Attachment) map.get("attachment");
			if (attachment != null) {
				if (attachment.UPDATE()) {
					where += "" + map.get("key") + "='" + map.get("value") + "' and ";
					bs = true;
				}
			}
			if (!bs) {
				sqli += " " + map.get("key") + "=";
				if (map.get("value") instanceof String) {
					sqli += "'" + map.get("value") + "',";
				} else {
					sqli += map.get("value") + ",";
				}
			}

		}
		int index = sqli.lastIndexOf(",");
		sqli = sqli.substring(0, index);
		index = where.lastIndexOf("and");
		where = where.substring(0, index);
		logger.debug("--sql语句：" + sqli + where);
		return exeSql(sqli + where);
	}

	@Override
	public List<T> querysql(String sql, T obj) throws Exception {

		List<T> listobj = new ArrayList<T>();
		listobj = (List<T>) getSqlListBean(String.format(sql, sql), obj);
		return listobj;
	}

	public List<T> query(T obj) throws Exception {
		List<Map<String, Object>> list = BaseUtil.setAllComponentsName(obj, CMD.SELECT);
		String table = "";
		String sql = "";
		String order = "";
		if (obj.getClass().isAnnotationPresent(Table.class) == true) {
			Table xs = obj.getClass().getAnnotation(Table.class);
			table = xs.NAME();
			if (!"".equals(xs.ORDER())) {
				order = xs.ORDER();
			}
		}
		StringBuffer buffer = new StringBuffer();
		List<T> listobj = new ArrayList<T>();
		buffer.append(" select * %s from  ");
		buffer.append(table + " %s ");
		String where = " where  ";
		for (Map<String, Object> map : list) {
			Attachment attachment = (Attachment) map.get("attachment");
			if (attachment != null) {

				if (attachment.ISENABLE()) {
					if (map.get("value") instanceof String) {
						if ("like".equals(attachment.KEY())) {
							where += String.format(attachment.TEMPLATE(), map.get("key"), "%" + map.get("value") + "%")
									+ " and ";
						} else if ("in".equals(attachment.KEY())) {

							if (attachment.ISNULL()) {
								where += map.get("key") + " in (" + map.get("value") + ") or  " + map.get("key")
										+ " is null  or " + map.get("key") + "='0' and   ";
							} else {
								where += map.get("key") + " in (" + map.get("value") + ") and ";
							}

						} else {
							where += map.get("key") + "='" + map.get("value") + "' and ";
						}
					} else {
						where += map.get("key") + "='" + map.get("value") + "' and ";
					}
				} else if ("<".equals(attachment.KEY()) || ">".equals(attachment.KEY())) {
					where += map.get("key") + " " + attachment.KEY() + " '" + map.get("value") + "' and ";
				} else if (!"".equals(attachment.SQL())) {
					sql += " , (" + attachment.SQL() + ") as " + map.get("key");
				}
			} else {
				where += map.get("key") + "='" + map.get("value") + "' and ";
			}

		}

		int index = where.lastIndexOf("and");
		if (index != -1) {
			where = where.substring(0, index);
			// buffer.append(where);

		} else {
			where = "";
		}
		buffer.append(order);
		if (obj instanceof PageBean) {
			int count = getCount(buffer.toString(), DB.SYS);
			((PageBean) obj).setCount(count);
			sql = BaseUtil.joinPageQuerySql(String.format(buffer.toString(), sql, where), (PageBean) obj);
		} else {
			sql = String.format(buffer.toString(), sql, where);
		}

		listobj = (List<T>) getSqlListBean(sql, obj);
		return listobj;
	}

	public PageBean queryPage(T obj) throws Exception {
		PageBean page = new PageBean();
		List<Map<String, Object>> list = BaseUtil.setAllComponentsName(obj, CMD.SELECT);
		String table = "";
		String sql = "";
		String order = "";
		if (obj.getClass().isAnnotationPresent(Table.class) == true) {
			Table xs = obj.getClass().getAnnotation(Table.class);
			table = xs.NAME();
			if (!"".equals(xs.ORDER())) {
				order = xs.ORDER();
			}
		}
		StringBuffer buffer = new StringBuffer();
		List<T> listobj = new ArrayList<T>();
		buffer.append(" select %s %s from  ");
		buffer.append(table + " %s ");
		String where = " where  ";
		Object[] parms =null;
		List lists = new ArrayList();
		for (Map<String, Object> map : list) {
			Attachment attachment = (Attachment) map.get("attachment");
			if (attachment != null) {

				if (attachment.ISENABLE()) {
					if (map.get("value") instanceof String) {
						if ("like".equals(attachment.KEY())) {
							where += String.format(attachment.TEMPLATE(), map.get("key")) + " and ";
							lists.add("%" + map.get("value") + "%");
						} else if ("in".equals(attachment.KEY())) {
							where += map.get("key") + " in (?) and ";
							lists.add(map.get("value"));
						} else {
							where += map.get("key") + "=? and ";
							lists.add(map.get("value"));
						}
					} else {
						where += map.get("key") + "=? and ";
						lists.add(map.get("value"));
					}
				} else if ("<".equals(attachment.KEY()) || ">".equals(attachment.KEY())) {
					where += map.get("key") + " " + attachment.KEY() + " ? and ";
					lists.add(map.get("value"));
				} else if (!"".equals(attachment.SQL())) {
					sql += " , (" + attachment.SQL() + ") as " + map.get("key");
				} /*
					 * else{ if(obj instanceof PageBean){
					 * if("Starindex".equals(map.get("key"))){
					 * System.out.println(map.get("value")); }else
					 * if("Size".equals(map.get("key"))){
					 * System.out.println(map.get("value")); } } }
					 */
			} else {
				where += map.get("key") + "=? and ";
				lists.add(map.get("value"));

			}

		}

		int index = where.lastIndexOf("and");
		if (index != -1) {
			where = where.substring(0, index);
		} else {
			where = "";
		}
		buffer.append(order);
		 parms =new Object[lists.size()];
		lists.toArray(parms);
		if (obj instanceof PageBean) {
			int count = getCount(String.format(buffer.toString(), "count(1)", "", where), parms, DB.SYS);
			page.setCount(count);
			page.setPageSize(((PageBean) obj).getPageSize());
			page.setPageIndex(((PageBean) obj).getPageIndex());
			lists.add(page.getStarindex());
			lists.add(page.getPageSize());
			sql = BaseUtil.joinPageQuerySql(String.format(buffer.toString(), " * ", sql, where), (PageBean) obj);
		} else {
			sql = String.format(buffer.toString(), sql, where);
		}
		 parms =new Object[lists.size()];
		lists.toArray(parms);
		listobj = (List<T>) getSqlListBean(sql, parms, obj);
		page.setItems(listobj);
		return page;
	}

	public List<T> queryapp(T obj) throws Exception {
		List<Map<String, Object>> list = BaseUtil.setAllComponentsName(obj, CMD.SELECT);
		String table = "";
		String sql = "";
		String order = "";
		if (obj.getClass().isAnnotationPresent(Table.class) == true) {
			Table xs = obj.getClass().getAnnotation(Table.class);
			table = xs.NAME();
			if (!"".equals(xs.ORDER())) {
				order = xs.ORDER();
			}
		}
		StringBuffer buffer = new StringBuffer();
		List<T> listobj = new ArrayList<T>();
		buffer.append(" select * %s from ");
		buffer.append(table);
		String where = " where ";
		for (Map<String, Object> map : list) {
			Attachment attachment = (Attachment) map.get("attachment");
			if (attachment != null) {
				if (attachment.ISENABLE()) {
					if (map.get("value") instanceof String) {
						if ("like".equals(attachment.KEY())) {
							where += String.format(attachment.TEMPLATE(), map.get("key"), "%" + map.get("value") + "%")
									+ " and ";
						} else if ("in".equals(attachment.KEY())) {
							where += map.get("key") + " in (" + map.get("value") + ") and ";
						} else {
							where += map.get("key") + "='" + map.get("value") + "' and ";
						}
					} else {
						where += map.get("key") + "='" + map.get("value") + "' and ";
					}
				} else if ("<".equals(attachment.KEY()) || ">".equals(attachment.KEY())) {
					where += map.get("key") + " " + attachment.KEY() + " '" + map.get("value") + "' and ";
				} else if (!"".equals(attachment.SQL())) {
					sql += " , (" + attachment.SQL() + ") as " + map.get("key");
				}
			} else {
				where += map.get("key") + "='" + map.get("value") + "' and ";
			}

		}

		int index = where.lastIndexOf("and");
		if (index != -1) {
			where = where.substring(0, index);
			buffer.append(where);

		}
		buffer.append(order);

		listobj = (List<T>) getSqlListBeanApp(String.format(buffer.toString(), sql), obj);
		return listobj;
	}

	@Override
	public boolean delete(T obj) throws Exception {
		List<Map<String, Object>> list = BaseUtil.setAllComponentsName(obj, CMD.DELETE);
		String table = "";
		if (obj.getClass().isAnnotationPresent(Table.class) == true) {
			Table xs = obj.getClass().getAnnotation(Table.class);
			table = xs.NAME();
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("delete from  ");
		buffer.append(table);
		String where = " where ";
		int count = 0;
		for (Map<String, Object> map : list) {
			Attachment attachment = (Attachment) map.get("attachment");
			if (attachment != null && attachment.DELETE()) {
				String[] ze = map.get("value").toString().split(",");
				String size = "";
				if (ze.length > 0) {
					for (String string : ze) {
						size += "'" + string + "',";
					}
					count = size.lastIndexOf(",");
					size = size.substring(0, count);
				} else {
					size += "'" + map.get("value") + "'";
				}
				if (BaseUtil.isNotNull(map.get("value") + "")) {
					where += String.format(map.get("key") + " in (%s)", size) + " and ";
				}
			}

		}
		count = where.lastIndexOf("and");
		if (count != -1) {
			where = where.substring(0, count);
		}
		buffer.append(where);
		logger.debug("--sql语句：" + buffer.toString());
		return exeSql(buffer.toString());
	}

	public boolean OnlyVerification(String table, String coluem, String value) {
		DBAccess dba = new DBAccess(DB.SYS);
		Map<String, User> list = new HashMap<String, User>();
		List<Map<String, String>> list2 = null;
		String sql1 = "select * from " + table + " where " + coluem + "=?";
		try {
			list2 = DBAccess.pkRs2LMS(dba.preExecuteQuery(sql1, new Object[] { value }));
			if (list2.size() > 0) {
				return false;
			}
		} catch (Exception e) {
			Logs.writeErrLog("唯一验证SQL", e.getCause());
			return false;
		} finally {
			dba.close();
		}

		return true;
	}

	public int getCount(String sql, String db) {
		DBAccess dba = new DBAccess(db);
		int count = 0;
		try {
			count = dba.getCount(sql);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dba.close();
		}

		return count;

	}

	public int getCount(String sql, Object[] parms, String db) {
		DBAccess dba = new DBAccess(db);
		int count = 0;
		try {
			count = dba.getCount(sql, parms);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dba.close();
		}

		return count;

	}

	@Override
	public String oriQuery(String sql, String db) throws Exception {
		DBAccess dba = new DBAccess(db);
		try {
			logger.debug("--sql语句：" + sql);
			return dba.getStr(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("执行数据失败" + sql);
		} finally {
			dba.close();
		}
	}

	@Override
	public boolean deleteDev(String mgrobjid, String agentbm) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
