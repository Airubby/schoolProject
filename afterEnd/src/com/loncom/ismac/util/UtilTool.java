package com.loncom.ismac.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.http.NameValuePair;

import com.loncom.ismac.application.AppContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings({ "rawtypes", "unused" })
public class UtilTool {

	public static String xmlBegin = "";
	public static String xmlEnd = "";
	public static DecimalFormat decimalFormat2 = new DecimalFormat("#0.00");
	public static DecimalFormat decimalFormat1 = new DecimalFormat("#0.0");
	public static DecimalFormat decimalFormat3 = new DecimalFormat("#0.000");

	/**
	 * 取配置文件数据
	 * 
	 * @param pty
	 * @param defaultValue
	 * @return
	 */
	public static String getProperty(String pty, String defaultValue) {
		return AppContext.getPropSet(pty, defaultValue);

	}

	public static String cutFloat2(String value) {
		if (isNull(value))
			return "";
		return decimalFormat2.format(Float.parseFloat(value));
	}

	public static String cutFloat3(String value) {
		if (isNull(value))
			return "";
		return decimalFormat3.format(Float.parseFloat(value));
	}

	public static String cutFloat1(String value) {
		if (isNull(value))
			return "";
		return decimalFormat1.format(Float.parseFloat(value));
	}

	public static String cutInt(String value) {
		if (isNull(value))
			return "";
		return "" + Math.round(Float.parseFloat(value));
	}

	public static String getInt(String value) {
		if (isNull(value))
			return "";
		double d;
		try {
			d = Double.valueOf(value).doubleValue();
		} catch (Exception e) {
			return "";
		}
		return (int) d + "";
	}

	public static float parseFloat(String fl) {
		if (UtilTool.isNull(fl))
			return 0;
		return Float.parseFloat(fl);
	}

	public static String getIntStr(int i) {
		if (i < 10)
			return "0" + i;
		return "" + i;
	}

	/**
	 * 取配置文件数据
	 * 
	 * @param pty
	 * @return
	 */
	public static String getProperty(String pty) {
		return AppContext.getPropSet(pty);

	}

	public synchronized static String getTimeSN() {
		return "" + new Date().getTime();
	}

	public static String empty2SqlNumberNull(String d) {
		if (isNull(d))
			return "null";
		return "'" + d + "'";
	}

	/**
	 * double截断，保留x位
	 * 
	 * @param data
	 * @param x
	 * @return
	 */
	public static Double cuatDouble(Double data, int x) {
		try {
			// Logs.log("浮点数截取:"+data);
			if (data != null && (data.isInfinite() || data.isNaN()))
				return null;
			BigDecimal bg = new BigDecimal(data);
			double f1 = bg.setScale(x, BigDecimal.ROUND_HALF_UP).doubleValue();
			return f1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将object转化成string,避免null.toString异常
	 * 
	 * @param obj
	 * @return
	 */
	public static String obj2Str(Object obj) {
		if (obj == null || "null".equalsIgnoreCase(obj.toString())) {
			return "";
		} else {
			return obj.toString();
		}
	}

	public static int obj2Int(Object obj) {
		if (obj == null) {
			return 0;
		} else {
			return Integer.parseInt(obj.toString());
		}
	}

	/**
	 * 
	 * @param menuid
	 *            当前id
	 * @param pid
	 *            参数传递id
	 * @return
	 */
	public static String setMenuSeletc(String menuid, String pid) {
		if (UtilTool.isNull(pid) && "1".equals(menuid))
			return "style=\"background-color: #202833;\"";
		else if (UtilTool.isNotNull(pid) && menuid.equals(pid))
			return "style=\"background-color: #202833;\"";
		else
			return "";
	}

	/**
	 * 下拉菜单默认值
	 * 
	 * @param target
	 * @param value
	 * @return
	 */
	public static String setSelectDefault(Object target, String value) {
		if (UtilTool.isNotNull(target)) {
			if (value.equals(target.toString())) {
				return "selected";
			}
		}
		return "";
	}

	/**
	 * 如果不为空，则选中
	 * 
	 * @param target
	 * @return
	 */
	public static String setIfNotNullSelect(Object target) {
		if (UtilTool.isNotNull(target)) {
			return "selected";
		}
		return "";
	}

	/**
	 * 如果不为空，则选中
	 * 
	 * @param target
	 * @return
	 */
	public static String setIfNotNullCheck(Object target) {
		if (UtilTool.isNotNull(target)) {
			return "checked";
		}
		return "";
	}

	public static String setCheckedDefault(Object target, String value) {
		if (UtilTool.isNotNull(target)) {
			if (value.equals(target.toString())) {
				return "checked";
			}
		}
		return "";
	}

	public static String printChecktrue() {
		return "checked=\"true\"";
	}

	public static String obj2star(String x) {
		if (UtilTool.isNull(x))
			return "*";
		return x;
	}

	/**
	 * 转换成数字字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String obj2num(Object obj) {
		if (UtilTool.isNull(obj)) {
			return "0.0";
		} else {
			return decimalFormat1.format(Float.parseFloat(obj + ""));
		}
	}

	public static String obj2numobj(Object obj) {
		if (UtilTool.isNull(obj)) {
			return "null";
		} else {
			return obj.toString();
		}
	}

	/**
	 * 对象是否为空或空字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		if (obj == null || "".equals(obj)|| "\"\"".equals(obj) || "null".equalsIgnoreCase(obj.toString())
				|| "\"null\"".equalsIgnoreCase(obj.toString())) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// DecimalFormat df1 = new DecimalFormat("#0.0");
		System.out.println(cutStringTail("01"));
	}

	/**
	 * 对象是否为空或空字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	//
	public static String cutDateString(String date) {
		if (UtilTool.isNotNull(date) && date.length() > 19) {
			return date.substring(0, 19);
		}
		return date;
	}
	
	public static String cutStringTail(String str) {
		if(!UtilTool.isNotNull(str)){
			return str;
		}else{
			return str.substring(0,str.length()-1);
		}
	}

	/**
	 * 数据库操作用，将集合转换成（'','','',''）形式
	 * 
	 * @param obj
	 * @return
	 */

	public static String convertCollectionParm(Collection obj) {
		if (obj == null) {
			return "('')";
		}
		Collection c = (Collection) obj;
		StringBuilder sb = new StringBuilder("(''");
		for (Object item : c) {
			sb.append(",'" + obj2Str(item) + "'");
		}
		sb.append(")");
		return sb.toString();

	}

	// 将json参数转换成map便于统一处理
	@SuppressWarnings("unchecked")
	public static Map<String, String> parse2Map(String jsonStr) {
		HashMap<String, String> map = new HashMap<String, String>();
		if (jsonStr.startsWith("[")) {
			// 数组，form序列化提交形式
			JSONArray j = JSONArray.fromObject(jsonStr);
			for (int i = 0; i < j.size(); i++) {
				JSONObject x = j.getJSONObject(i);
				map.put(UtilTool.obj2Str(x.get("name")), UtilTool.obj2Str(x.get("value")));
			}
		} else if (jsonStr.startsWith("{")) {
			// json对象
			JSONObject jsonMap = JSONObject.fromObject(jsonStr);
			Iterator<String> it = jsonMap.keys();
			while (it.hasNext()) {
				String key = (String) it.next();
				map.put(key, jsonMap.getString(key));
			}
		}

		return map;
	}

	// 截取时间，去掉超长部分
	public static String fmtTime(String obj) {
		if (UtilTool.isNotNull(obj)) {
			int length = obj.length();
			if (length > 19)
				return obj.substring(0, 19);
			return obj;
		}
		return "";
	}

	/**
	 * 十进制转换十六进制,补齐两位
	 * 
	 * @param n
	 * @return
	 */
	public static String ten2Hex(String n) {
		if (UtilTool.isNull(n))
			return "";
		return bo(Integer.toHexString(Integer.parseInt(n))).toUpperCase();
	}

	public static String subString(Object tar, int length) {
		if (UtilTool.isNull(tar))
			return "";
		int l = tar.toString().length();
		if (l > length)
			l = length;
		return tar.toString().substring(0, l);
	}

	/**
	 * 十六进制转换十进制
	 * 
	 * @param n
	 * @return
	 */
	public static String hex2Ten(String n) {
		if (UtilTool.isNull(n))
			return "";
		return "" + Integer.parseInt(n, 16);
	}

	public static int parseInt(String obj) {
		if (UtilTool.isNull(obj))
			return 0;
		return Integer.parseInt(obj.toString());
	}

	private static String bo(String hex) {
		if (hex.length() == 1)
			return "0" + hex;
		return hex;
	}

	/**
	 * 将双引号，单引号转意，便于javascript输出
	 * 
	 * @param str
	 * @return
	 */
	public static String singleSwitch(String str) {
		if (UtilTool.isNull(str))
			return "";
		str = str.replaceAll("'", "\\\\'");
		str = str.replaceAll("\"", "\\\\'");
		return str;
	}

	public static Map<String, String> getBase(Map<String, String[]> map) {

		Map<String, String> maps = new HashMap<String, String>();

		List<NameValuePair> paramMap = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			if ("jsoncallback".equals(entry.getKey())) {
				continue;
			}
			maps.put(entry.getKey(), entry.getValue()[0]);
			// paramMap.add(new
			// BasicNameValuePair(entry.getKey(),entry.getValue()[0]));
		}
		return maps;
	}

	
	/**
	 * 一位数字转为两位字符串
	 * @param i
	 * @return
	 */
	public static String totwo(int i) {
		if (i < 10) {
			return "0" + i;
		} else {
			return String.valueOf(i);
		}

	}

	
	/**
	 * 获取该年月的总日期
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getallday(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		int lastd = cal.getActualMaximum(Calendar.DATE);
		return lastd;
	}

	public static Object express(String express,Map<String,Object> param_map) throws ScriptException{
//		String str = "(a >= 0 && a <= 5)";
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
//		engine.put("a", 4);
		if(param_map!=null && param_map.size()>0){
			Set<String> keySet=param_map.keySet();
			for (String key : keySet) {
				engine.put(key, param_map.get(key));
			}
		}
		Object result = engine.eval(express);
//		System.out.println("结果类型:" + result.getClass().getName() + ",计算结果:" + result);
		return result;
	}
}
