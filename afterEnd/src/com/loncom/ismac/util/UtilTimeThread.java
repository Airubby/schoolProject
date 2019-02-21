package com.loncom.ismac.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * 线程安全的日期格式化
 * @author wangc
 *
 */
public class UtilTimeThread {

	private static ThreadLocal<Map<String,SimpleDateFormat>> dateFormateThread=new ThreadLocal<Map<String,SimpleDateFormat>>(){
		protected Map<String,SimpleDateFormat> initialValue(){
			System.out.println("线程名："+Thread.currentThread().getName()+"进行初始化");
			return new HashMap<String,SimpleDateFormat>();
		}
	};
	
	private static SimpleDateFormat getDateFormate(String pattern){
		Map<String,SimpleDateFormat> map=dateFormateThread.get();
		SimpleDateFormat sdf=map.get(pattern);
		if(sdf==null){
			sdf=new SimpleDateFormat(pattern);
			map.put(pattern, sdf);
		}
		return sdf;
	}
	
	public static String format(Date date,String pattern){
		return getDateFormate(pattern).format(date);
	}
	
	public static Date parse(String date,String pattern) throws ParseException{
		return getDateFormate(pattern).parse(date);
	}
}
