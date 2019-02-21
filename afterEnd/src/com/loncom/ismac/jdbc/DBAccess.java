package com.loncom.ismac.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.UtilTimeThread;
import com.loncom.ismac.util.UtilTool;
/**
 * 
 * @author leijun
 */
@SuppressWarnings("unused")

public class DBAccess {
	private static int openNum = 0;
	private static int closeNum = 0;
	static Logger logger = Logger.getLogger(DBAccess.class);
	private static boolean debug = true;
	private Connection conn;
	private List<Statement> stmtList = null;
	private List<ResultSet> rsList = null;
	private Statement batchStmt = null;
	private PreparedStatement batchPreStmt = null;
	private String source;
	public String getSource() {
		return source;
	}
	// 默认
	public DBAccess(String source) {
		try {
			
			this.source=source;
			this.stmtList = new ArrayList<Statement>();
			this.rsList = new ArrayList<ResultSet>();
			this.setConn();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 重置bean
	public void news() {
		try {
			this.close();
			this.stmtList = new ArrayList<Statement>();
			this.rsList = new ArrayList<ResultSet>();
			this.setConn();
			if (debug)
				logger.debug("db access tool re-init");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 设置提交模式
	public void setAutoCommit(boolean b) {
		if (this.conn == null)
			this.setConn();
		try {
			this.conn.setAutoCommit(b);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 通过id获取一条记录
	public Map<String, Object> getEntityBean(String sql, String id) {
		this.doLog(sql, "");
		ResultSet rs = preExecuteQuery(sql, id);
		List<Map<String, Object>> list1 = DBAccess.pkRs2LM(rs);
		if (list1 != null && !list1.isEmpty())
			return list1.get(0);
		return null;
	}

	/**
	 * 将Map<String,Object>转换成Map<String,String>
	 * @param map
	 * @return
	 */
	public static Map<String,String> mapObj2mapStr(Map<String, Object> map){
		if(map==null){
			return null;
		}
		Map<String, String> rMap=new HashMap<String, String>();
		for(Map.Entry<String, Object> entity:map.entrySet()){
			rMap.put(entity.getKey(),UtilTool.obj2Str(entity.getValue()));
		}
		return rMap;
	}
	
	/**
	 * 将结果封装成List<Map<String, Object>>
	 * @param rs
	 * @return
	 */
	public static List<Map<String, Object>> pkRs2LM(ResultSet rs) {
		List<Map<String, Object>> list = null;
		if (rs != null)
			try {
				list = new ArrayList<Map<String, Object>>();
				String[] head = packageTitle(rs);
				while (rs.next()) {
					Map<String, Object> map = new LinkedHashMap<String, Object>();
					for (String title : head) {
						map.put(title.toUpperCase(), rs.getObject(title)+"");
					}
					list.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	}
	
	
	public static List<Object> ListBean(ResultSet rs,Object obj) {
		List<Object> list = null;
		if (rs != null)
			try {
				list = new ArrayList<Object>();
				String[] head = packageTitle(rs);
				while (rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					for (String title : head) {
						if(rs.getObject(title) instanceof java.sql.Timestamp){
							java.sql.Timestamp value=(Timestamp) rs.getObject(title);
//							map.put(title, UtilTool.isNull(value.toString())==true?"":UtilTime.transDate5Hw(value.toString()) );
							map.put(title, UtilTool.isNull(value.toString())==true?"":UtilTimeThread.format(UtilTimeThread.parse(value.toString(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
						}else{
							map.put(title, UtilTool.isNull(rs.getObject(title))==true?"":rs.getObject(title)+"");
						}
						
						
						
						
					}
					Object objs=BaseUtil.setAllComponentsName(obj.getClass(), map);
					list.add(objs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	}
	
	
	/**
	 * 
	 * @param rs
	 * @param obj
	 * @return
	 */
	public static List<Object> ListBeanApp(ResultSet rs,Object obj) {
		List<Object> list = null;
		if (rs != null)
			try {
				list = new ArrayList<Object>();
				String[] head = packageTitle(rs);
				while (rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					for (String title : head) {
						
						if(rs.getObject(title) instanceof java.sql.Timestamp){
							java.sql.Timestamp value=(Timestamp) rs.getObject(title);
							map.put(title, UtilTool.isNull(value.toString())==true?"":value.toString());
						}else{
							map.put(title, UtilTool.isNull(rs.getObject(title))==true?"":rs.getObject(title)+"");
						}
						
						
						
						
					}
					Object objs=BaseUtil.setAllComponentsNameApp(obj.getClass(), map);
					list.add(objs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	}
	
	/**
	 * 将结果封装成List<Map<String, String>>
	 * @param rs
	 * @return
	 */
	public static List<Map<String, String>> pkRs2LMS(ResultSet rs) {
		List<Map<String, String>> list = null;
		if (rs != null)
			try {
				list = new ArrayList<Map<String, String>>();
				String[] head = packageTitle(rs);
				while (rs.next()) {
					Map<String, String> map = new LinkedHashMap<String, String>();
					for (String title : head) {
						map.put(title.toUpperCase(), rs.getString(title));
					}
					list.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	}
	/**
	 * 将结果集封装成Map<String, String>
	 * @param rs
	 * @return
	 */	
	public static Map<String, String> pkRs2MS(ResultSet rs) {
		Map<String, String> map=null;
		if (rs != null)
			try {
				String[] head = packageTitle(rs);
				while (rs.next()) {
					map = new HashMap<String, String>();
					for (String title : head) {
						map.put(title.toUpperCase(), rs.getString(title));
					}
					return map;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return map;
	}

	// 封装结果集 List<Object[]>
	public static List<Object[]> pkRs2LA(ResultSet rs) {
		List<Object[]> list = null;
		if (rs != null)
			try {
				list = new ArrayList<Object[]>();
				String[] head = packageTitle(rs);
				while (rs.next()) {
					Object[] data = new Object[head.length];
					for (int i = 0; i < head.length; i++) {
						data[i] = rs.getObject(i + 1);
					}
					list.add(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	}
	
	public static List<String[]> pkRs2LAS(ResultSet rs) {
		List<String[]> list = null;
		if (rs != null)
			try {
				list = new ArrayList<String[]>();
				String[] head = packageTitle(rs);
				while (rs.next()) {
					String[] data = new String[head.length];
					for (int i = 0; i < head.length; i++) {
						data[i] = UtilTool.obj2Str(rs.getString(i + 1));
					}
					list.add(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	}
	
	public static List<String> pkRs2LAS1(ResultSet rs) {
		List<String> list = null;
		if (rs != null)
			try {
				list = new ArrayList<String>();
				while (rs.next()) {
					list.add(rs.getString(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	}
	
	public static Set<String> pkRs2Set(ResultSet rs) {
		Set<String> list = null;
		if (rs != null)
			try {
				list = new HashSet<String>();
				while (rs.next()) {
					list.add(rs.getString(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	}

	private static int batchIndex=0;
	public void addBatch(String sql) {
		try {
			this.doLog(sql, "");
			if (this.batchStmt == null) {
				this.batchStmt = this.conn.createStatement();
				this.stmtList.add(this.batchStmt);
			}
			this.batchStmt.addBatch(sql);
//			batchIndex++;
//			if(batchIndex>1000){
//				executeBatch();
//				batchIndex=0;
//			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void addBatch(List<String> sqlList) {
		try {
			for (String sql : sqlList) {
				this.doLog(sql, "");
				if (this.batchStmt == null) {
					this.batchStmt = this.conn.createStatement();
					this.stmtList.add(this.batchStmt);
				}
				this.batchStmt.addBatch(sql);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void clearBatch() {
		try {
			if (this.batchStmt == null)
				this.batchStmt.clearBatch();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int[] executeBatch() {
		try {
			if (this.batchStmt != null)
				return this.batchStmt.executeBatch();
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean existRecord(String sql, Object[] parms) {
		String sql1 = "select count(*) from (" + sql + ")";
		int num = getCount(sql1, parms);
		return num > 0;
	}

	public boolean existRecord(String sql, String parms) {
		String sql1 = "select count(*) from (" + sql + ") a";
		int num = getCount(sql1, parms);
		return num > 0;
	}
	
	public boolean existTable(String tableName){
		try {
			executeQuery("select 1 from "+tableName+" LIMIT 1");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getStr(String sql, String parms) {
		String result = "";
		List<Object[]> list = pkRs2LA(this.preExecuteQuery(sql, parms));
		if (list != null && list.size() > 0) {
			Object[] obj = list.get(0);
			result = null2Str(obj[0]);
		}
		return result;
	}

	public String getStr(String sql) {
		String result = "";
		List<Object[]> list = pkRs2LA(this.executeQuery(sql));
		if (list != null && list.size() > 0) {
			Object[] obj = list.get(0);
			result = null2Str(obj[0]);
		}
		return result;
	}

	public String getStr(String sql, Object[] parms) {
		String result = "";
		List<Object[]> list = pkRs2LA(this.preExecuteQuery(sql, parms));
		if (list != null && list.size() > 0) {
			Object[] obj = list.get(0);
			result = null2Str(obj[0]);
		}
		return result;
	}

	public String null2Str(Object obj) {
		if (obj == null || "".equals(obj)) {
			return "";
		}
		return obj.toString();
	}

	public ResultSet executeQuery(String sql) {
		this.doLog(sql, "");
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			this.stmtList.add(stmt);
			rs = stmt.executeQuery(sql);
			this.rsList.add(rs);
			return rs;
		} catch (Exception e) {
			closeAndRemove(stmt, rs);
			throw new RuntimeException(e);
		}
	}

	public int getCount(String sql) {
		return getInt(this.executeQuery(sql));
	}

	private int getInt(ResultSet rs) {
		try {
			if (rs != null)
				while (rs.next()) {
					return Integer.parseInt(rs.getObject(1).toString());
				}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return 0;
	}

	public boolean execute(String sql) {
		this.doLog(sql, "");
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			this.stmtList.add(stmt);
			return stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			closeAndRemove(stmt);
			throw new RuntimeException(e);
		}
	}

	public int executeUpdate(String sql) {
		this.doLog(sql, "");
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			this.stmtList.add(stmt);
			return stmt.executeUpdate(sql);
		} catch (Exception e) {
			closeAndRemove(stmt);
			throw new RuntimeException(e);
		}
	}

	public int getCount(String sql, Object[] parms) {
		return getInt(this.preExecuteQuery(sql, parms));
	}

	// 绑定变量执行区,Object[] parms
	public ResultSet preExecuteQuery(String sql, Object[] parms) {
		this.doLog(sql, parms);
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = createPreStmt(sql, parms);
			rs = preStmt.executeQuery();
			this.rsList.add(rs);
			return rs;
		} catch (Exception e) {
			closeAndRemove(preStmt, rs);
			throw new RuntimeException(e);
		}
	}

	// 参数格式转换
	public static String conventArrayParm2Str(String[] parm) {
		String result = "''";
		if (parm != null && parm.length > 0) {
			for (String str : parm)
				result += ",'" + str + "'";
		}
		return result;
	}

	// 批处理
	public int[] preExecuteBatch(String sql, List<Object[]> listParms) {
		this.doLog(sql, listParms);
		try {
			this.batchPreStmt = this.conn.prepareStatement(sql);
			this.stmtList.add(this.batchPreStmt);
			for (Object[] parms : listParms) {
				this.checkParms(sql, parms);
				this.parmsBinding(this.batchPreStmt, parms);
				this.batchPreStmt.addBatch();
			}
			return this.batchPreStmt.executeBatch();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean preExecute(String sql, Object[] parms) {
		this.doLog(sql, parms);
		PreparedStatement preStmt = null;
		try {
			preStmt = createPreStmt(sql, parms);
			return preStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public int preExecuteUpdate(String sql, Object[] parms) {
		this.doLog(sql, parms);
		PreparedStatement preStmt = null;
		try {
			preStmt = createPreStmt(sql, parms);
			return preStmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 绑定变量执行区,parm1'parm2'parm3
	public ResultSet preExecuteQuery(String sql, String parms) {
		Object[] objs = parms.split("'");
		return preExecuteQuery(sql, objs);
	}

	public int getCount(String sql, String parms) {
		return this.getInt(this.preExecuteQuery(sql, parms));
	}

	public boolean preExecute(String sql, String parms) {
		Object[] objs = parms.split("'");
		return preExecute(sql, objs);
	}

	public int preExecuteUpdate(String sql, String parms) {
		Object[] objs = parms.split("'");
		return preExecuteUpdate(sql, objs);
	}

	// 释放资源
	public void close() {
		if (debug)
			logger.debug("关闭:" + (++closeNum));
		this.gcclose();
	}
	
	
	public void rollBack(){
		try {
			logger.debug("事务回滚");
			if(conn!=null){
				if(!conn.getAutoCommit()){
					conn.rollback();
					logger.debug("事务回滚...OK");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commit(){
		try {
			if (!this.conn.getAutoCommit()){
				this.conn.commit();
				this.conn.setAutoCommit(true);
				logger.debug("提交事务...OK1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void gcclose() {
		try {
			if (this.conn != null && !this.conn.isClosed()) {
				if (!this.conn.getAutoCommit()){
					this.conn.commit();
					this.setAutoCommit(true);
					logger.debug("提交事务...OK");
				}
			}
			if (!(this.stmtList == null || this.stmtList.isEmpty()))
				for (Statement stmt : this.stmtList) {
					this.closeStmt(stmt);
				}
			if (!(this.rsList == null || this.rsList.isEmpty()))
				for (ResultSet rs : this.rsList) {
					this.closeRs(rs);
				}
			if(batchStmt!=null&&!batchStmt.isClosed()){
				batchStmt.close();
			}
			if(batchPreStmt!=null&&!batchPreStmt.isClosed()){
				batchPreStmt.close();
			}
			if (this.conn != null && !this.conn.isClosed()) {
				this.conn.close();
				this.conn = null;
			}
			this.stmtList = null;
			this.rsList = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String converString(Object[] parms) {
		if (parms != null) {
			StringBuffer sb = new StringBuffer();
			for (Object obj : parms) {
				sb.append(obj + ",");
			}
			return sb.toString();
		}
		return null;
	}

	private void doLog(String sql, String param) {
		if (debug) {
			logger.debug("SQL:" + sql);
			logger.debug("Parameters:" + param);
		}

	}

	private void doLog(String sql, Object[] param) {
		if (debug) {
			logger.debug("SQL:" + sql);
			logger.debug("Parameters:" + converString(param));
		}
	}

	private void doLog(String sql, List<Object[]> listParam) {
		if (debug) {
			logger.debug("SQL:" + sql);
			if (listParam != null) {
				for (Object[] obj : listParam)
					logger.debug("Parameters:" + converString(obj));
			}
		}
	}

	// 检查参数
	private void checkParms(String sql, Object[] parms) {
		sql += " ";
		if (sql == null || parms == null)
			throw new RuntimeException("参数错误:" + sql + "," + parms);
		if (sql.split("\\?").length != parms.length + 1)
			throw new RuntimeException("参数个数不匹配(" + sql.split("\\?").length
					+ "):" + sql + ",参数:" + parms.length);
	}

	public static String[] packageTitle(ResultSet rs) {
		try {
			ResultSetMetaData rsm = rs.getMetaData();
			int size = rsm.getColumnCount();
			String[] title = new String[size];
			for (int i = 0; i < size; i++) {
				title[i] = rsm.getColumnLabel(i + 1);
				
			}
			if (debug){
				logger.debug((Arrays.asList(title)).toString().toUpperCase());
			}
			return title;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	static HashSet<String> s=new HashSet<String>();
	// 系统默认获取数据源
	private synchronized void setConn() {
		try {
//			if(source.equals("sys")){
//				System.setProperty("sqlite.purejava", "true");
//				Class.forName("org.sqlite.JDBC");
//				this.conn=DriverManager.getConnection(UtilTool.getProperty("sys_url"));
//			}else{
//				this.conn =DBCPPool.getConnection(source);
//			}
//			System.setProperty("sqlite.purejava", "true");
			this.conn =DBCPPool.getConnection(source);
			logger.debug("hashcode:"+this.conn.hashCode());
			s.add(""+this.conn.hashCode());
			if (debug)
				logger.debug("开启:" + (++openNum));
			logger.debug("s.size:"+s.size()+",values:"+s);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private PreparedStatement createPreStmt(String sql, Object[] parms) {
		checkParms(sql, parms);
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = this.conn.prepareStatement(sql);
			this.stmtList.add(preStmt);
			this.parmsBinding(preStmt, parms);
			return preStmt;
		} catch (Exception e) {
			closeAndRemove(preStmt, rs);
			throw new RuntimeException(e);
		}
	}

	// 绑定参数
	private PreparedStatement parmsBinding(PreparedStatement preStmt,
			Object[] parms) {
		try {
			for (int i = 1; i <= parms.length; i++) {
				if (parms[i - 1] instanceof java.util.Date) {
					parms[i - 1] = new Timestamp(
							((java.util.Date) parms[i - 1]).getTime());
					preStmt.setTimestamp(i, (Timestamp) parms[i - 1]);
				} else {
					preStmt.setObject(i, parms[i - 1]);
				}
			}
			return preStmt;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void closeAndRemove(Statement stmt, ResultSet rs) {
		closeAndRemove(rs);
		closeAndRemove(stmt);
	}

	private void closeAndRemove(Statement stmt) {
		closeStmt(stmt);
		this.stmtList.remove(stmt);
	}

	private void closeAndRemove(ResultSet rs) {
		closeRs(rs);
		this.rsList.remove(rs);
	}

	// 关闭statments
	private void closeStmt(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (Exception e) {
		}
	}

	// 关闭resultset
	private void closeRs(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (Exception e) {
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		logger.debug("destroy the dbaccess bean by the gc action at "
				+ new Date());
		this.gcclose();
	}

	public static void main(String[] args) {
		DBAccess dba=new DBAccess(DB.SYS);
		try {
			List<Map<String,Object>> list=DBAccess.pkRs2LM(dba.executeQuery("select * from devhead"));
			System.out.println(list.size());
			Map<String,Object> map1=list.get(0);
			System.out.println(map1.get("AGENTBM"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dba.close();
		}
	}
}
