package com.loncom.ismac.service;

import java.util.List;
import java.util.Map;

import com.loncom.ismac.bean.PageBean;



/** * @author  作者 E-mail: 
* @date 创建时间：2017年7月20日 上午10:56:51 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public interface IBaseService<T> {

	/**
	 * 查询表信息
	 * @param TableName 查询表名称
	 * @return 返回表数据
	 */
	List<Map<String, Object>> getTableList(String TableName)  throws Exception;
	/**
	 * 查询表信息
	 * @param TableName 查询SQL语句
	 * @return 返回表数据
	 */
	List<Map<String, Object>> getSqlList(String sql)  throws Exception;
	/**
	 * 查询信息
	 * @param sql
	 * @param obj
	 * @return 返回对象
	 * @throws Exception 
	 */
	 List<Object> getSqlListBean(String sql,Object obj) throws Exception ;
	 /**
		 * 查询信息
		 * @param sql
		 * @param obj
		 * @return 返回对象
		 * @throws Exception 
		 */
		 List<Object> getSqlListBean(String sql,Object[] parms,Object obj) throws Exception ;
	 
	 /**
		 * 查询信息
		 * @param sql
		 * @param obj
		 * @return 返回对象
		 * @throws Exception 
		 */
		 List<Object> getSqlListBean(String sql,Object obj,String db) throws Exception ;
	
	/**
	 * 通用新增数据方法
	 * @param obj
	 * @return
	 */
	boolean Add(T obj)throws Exception;
	
	/**
	 * 通用修改数据方法
	 * @param obj
	 * @return
	 */
	boolean update(T obj)throws Exception;
	
	/**
	 * 通用查询数据方法
	 * @param obj
	 * @return
	 */
	List<T> query(T obj)throws Exception;
	/**
	 * 通用查询数据方法
	 * @param obj
	 * @return
	 */
	PageBean queryPage(T obj)throws Exception;
	
	/**
	 * 通用删除用户
	 * @param obj
	 * @return
	 */
	boolean delete(T obj)throws Exception;
	
	/**
	 * 执行SQL 语句
	 * @param sql
	 * @return
	 */
	boolean exeSql(String sql)  throws Exception;
	/**
	 * 执行SQL 语句
	 * @param sql
	 * @return
	 */
	boolean exeSql(String sql,String db)  throws Exception;
	
	/**
	 * 执行SQL 语句
	 * @param sql
	 * @return
	 */
	List<T> querysql(String sql,T obj) throws Exception;
	
	/**
	 * 验证
	 * @param table
	 * @param coluem
	 * @param value
	 * @return
	 */
	 boolean OnlyVerification(String table, String coluem, String value) throws Exception;
	
	 List<Map<String, String>> getSqlListS(String sql,String db)throws Exception;
	 
	 /**
	  * 获取总数
	  * @param sql
	  * @param db
	  * @return
	  */
	 int getCount(String sql,String db);
	 
	 int getCount(String sql,Object[]  parms,String db);
	 /**
	  * 普通查询
	  * @param sql
	  * @return
	  */
	 String oriQuery(String sql,String db) throws Exception;
	boolean deleteDev(String mgrobjid, String agentbm) throws Exception;
	
	/**
	 * 通用查询数据方法APP
	 * @param obj
	 * @return
	 */
	List<T> queryapp(T obj)throws Exception;
}
