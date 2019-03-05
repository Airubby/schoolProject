package com.loncom.ismac.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Project: lonweb Version:1.0 Package: com.lon.util File: UtilTime.java
 * 
 * Functional Description:
 * 
 * Notes
 * 
 * Revision History create by leijun, 2014-1-6 上午10:13:56
 * 
 * lon, Inc. Copyright (C): 2013
 */
public class UtilTime {

	private static int x; // 日期属性：年
	private static int y; // 日期属性：月
	private static int z; // 日期属性：日
	private static Calendar localTime; // 当前日期
	public static SimpleDateFormat ymdhm_f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static SimpleDateFormat ymdh_f = new SimpleDateFormat("yyyy-MM-dd HH");
	public static SimpleDateFormat ymdhms_f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat hms_f = new SimpleDateFormat("HH:mm:ss");
	public static SimpleDateFormat mdhm_f = new SimpleDateFormat("MM-dd HH:mm");
	public static SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat ym_f = new SimpleDateFormat("yyyy-MM");
	public static SimpleDateFormat ym = new SimpleDateFormat("yyyyMM");
	public static SimpleDateFormat ymd_f = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat ymdhms = new SimpleDateFormat("yyyyMMddHHmmss");
	public static SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat hh = new SimpleDateFormat("HH");
	public static SimpleDateFormat hwd = new SimpleDateFormat("yyyy/MM/dd HH:mm");// 华为时间戳格式

	public static String getStyingYMD(String s) {
		String str = "";
		String[] arr = s.split("\\s")[0].split("-");
		for (int i = 0; i < arr.length; i++) {
			str += arr[i];
		}
		return str;
	}

	public static String getNowymdhm_f() {
		return ymdhm_f.format(new Date());
	}

	public UtilTime() {
		localTime = Calendar.getInstance();
	}

	// yyyy-MM-dd HH:mm:ss
	public static String getNow() {
		return ymdhms_f.format(new Date());
	}

	// yyyyMMddHHmmss
	public static String getNows() {
		return ymdhms.format(new Date());
	}

	// 获取yyyy-MM-dd 00:00:00
	/**
	 * 系统日期；格式为yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getNowYMD() {
		return ymd_f.format(new Date());
	}

	// 获取yyyy-MM-dd 00:00:00
	public static String getNowYMD(String data) throws ParseException {
		return ymd_f.format(ymd_f.parse(data)) + " 00:00:00";
	}

	public static String getDateString(String date, SimpleDateFormat simpleDateFormat) {
		return simpleDateFormat.format(date);
	}

	// 获取yyyy-MM-dd 00:00:00
	public static String getNowYMDH(String data) throws ParseException {
		return ymdh_f.format(ymdh_f.parse(data)) + ":00:00";
	}

	// yyyyMMddHHmmss
	/**
	 * 系统日期；格式为yyyyMMdd
	 * 
	 * @return
	 */
	public static String getNowymd() {
		return ymd.format(new Date());
	}

	public static String getEndDate() {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = ymdhms_f.format(ca.getTime());
		return last;
	}

	// 获取yyyy-MM-dd 00:00:00
	public static String getBeginOfDay() {
		return ymd_f.format(new Date()) + " 00:00:00";
	}

	// 获取yyyy-MM-dd 00:00:00
	public static long getTimeMax() {

		return getTimes(ymd_f.format(new Date()) + " 23:59:59").getTime();
	}

	// 获取yyyy-MM-dd 00:00:00
	public static long getTimeCurrent() {

		return getTimes(ymdhms_f.format(new Date())).getTime();
	}

	public static String getEndtime(Date date) {
		return ymd_f.format(date) + " 23:59:59";
	}

	// 华为时间戳格式转换
	public static String transDate4Hw(String date) throws ParseException {
		if (UtilTool.isNull(date))
			return "";
		return ymdhms_f.format(hwd.parse(date));
	}

	// 华为时间戳格式转换
	public static String transDate5Hw(String date) throws ParseException {
		if (UtilTool.isNull(date))
			return "";
		return ymdhms_f.format(ymdhms_f.parse(date));
	}

	// 华为时间戳格式转换
	public static String transDate(String date) throws ParseException {
		if (UtilTool.isNull(date))
			return "";
		return ymdhms_f.format(ymdhms.parse(date));
	}

	public static long getHmsLong(String hhmmss) {

		return getTimes(ymd_f.format(new Date()) + " " + hhmmss).getTime();
	}

	// 时间格式转，long--->hwd
	public static String transHttpDate(String longTime) {
		return hwd.format(new Date(longTime));
	}

	public static long transDate4HwTimes(String date) throws ParseException {
		if (UtilTool.isNull(date))
			return 0;
		;
		return Long.parseLong(date);
		// return hwd.parse(date).getTime()/1000;
	}

	public static String getYMD(Date date) {
		return ymd.format(date);
	}

	public static String getYMD() {
		return ymd.format(new Date());
	}

	// 获取当前时间，以给定形式返回
	public static String getCurrentXX(SimpleDateFormat sdf) {
		return sdf.format(new Date());
	}

	public static String getLX(String mintime, String f) {
		long mtime = Long.parseLong(mintime) + (Long.parseLong(f) * 1000);
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(mtime);
		return hms_f.format(ca.getTime());
	}

	// 当前时间+分钟
	public static Date getModifyData(Date date, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, min);
		return calendar.getTime();
	}

	// 当前时间+分钟
	public static String getModifyString(Date date, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, min);
		return ymdhms_f.format(calendar.getTime());
	}

	public static Date getModifyDataDay(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}

	public static Date getModifyDataYear(Date date, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}

	public static Long compareDate(String date1, String date2) {
		return (Long.parseLong(date1) - Long.parseLong(date2));
	}

	public static String formatDate(String date) {
		String r_date = "";
		try {
			r_date = ymdhms_f.format(ymdhms.parse(date));
		} catch (ParseException e) {

		}

		return r_date;
	}

	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	public static Date getLastSecondOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 时间format
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String fromDateToString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			String temp = sdf.format(date);
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getModifyData(int month, int week, int day, int hour, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.WEEK_OF_MONTH, week);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		calendar.add(Calendar.MINUTE, min);
		return ymd_f.format(calendar.getTime());
	}

	public static String getModifyData(int hour) {
		String hors = hour < 10 ? "0" + hour : hour + "";
		return ymd_f.format(new Date()) + " " + hors + ":59:59";
	}

	// public static Date getTimes(String data) {
	// DateUtils a = new DateUtils(data, "yyyyMMddHHmmss");
	// return a.getCalendar().getTime();
	// }

	public static Date getTimes(String data) {
		DateUtils a = new DateUtils(data, "yyyy-MM-dd HH:mm");
		return a.getCalendar().getTime();
	}

	public static void main(String[] args) throws InterruptedException, ParseException {
		// System.out.println(UtilTime.ymdhms_f.format(UtilTime.getModifyDataDay(new
		// Date(), -90)));
		// System.out.println(UtilTime.ymdhms_f.format(UtilTime.getModifyDataDay(new
		// Date(), -91)));
		/*
		 * long un=UtilTime.getTimes(UtilTime.getBeginOfDay()).getTime();
		 * Calendar ca = Calendar.getInstance(); ca.setTimeInMillis(un);
		 * System.out.println(ca);
		 */
		/*
		 * System.out.println(UtilTime.getTimeInterval(new Date()));
		 * System.out.println(UtilTime.getLastTimeInterval());
		 * System.out.println(UtilTime.getThedaybefore());
		 * System.out.println(UtilTime.getTheMonthend());
		 * System.out.println(UtilTime.getMonthend());
		 */
		System.out.println(getThedaybefore());
		System.out.println(getTimeInterval(new Date()));
		System.out.println(getLastTimeInterval());
		System.out.println(getMonthend());
		System.out.println(getTheMonthend());
		System.out.println(thisYearEnd());

		/*
		 * System.out.println(UtilTime.getTimes("2017-1-18 16:00:10").getTime())
		 * ;
		 * 
		 * Date a=UtilTime.getModifyDataDay(new Date(), -91);
		 * System.out.println(getDateToString(a));
		 * 
		 * String sql="select * from eventlog a where a.MgrObjId='%s' " +
		 * "and UNIX_TIMESTAMP(a.AlarmTime) >UNIX_TIMESTAMP('%s')  and UNIX_TIMESTAMP(a.AlarmTime)<UNIX_TIMESTAMP('%s')"
		 * ; sql=String.format(sql,"002",UtilTime.getDateToString(a),UtilTime.
		 * getDateToString(new Date())); System.out.println(sql);
		 */
		// long l=new Date().getTime();
		// System.out.println(l);
		// Thread.sleep(100);
		// Date d1=new Date(0);
		// System.out.println(ymdhms_f.format(d1));
		// Calendar calendar = Calendar.getInstance();
		// String x=getModifyData(-3,0,0,0,0);
		// System.out.println(x);
		// System.out.println(compareDate(getNows(),"20140723114612"));
		// System.out.println(ymdhms_f.format(getLastSecondOfMonth(new
		// Date())));
	}

	public static String getDateToString(Date date) {

		return ymdhm_f.format(date);
	}

	public static void main1(String[] args) {
		// Date d1=new Date();
		// System.out.println(ymdhms_f.format(d1));
		// long time=d1.getTime();
		// System.out.println(getLX(""+time,"10"));
		// System.out.println(UtilTime.getTimes("2017-1-18
		// 16:00:00").getTime());
		// System.out.println(UtilTime.getModifyDataDay(new Date(), 1));
		// System.out.println(UtilTime.getModifyDataDay(new Date(), 2));

	}

	public static void test(FPS x) {
		System.out.println("x:" + x.isB());
		x.setB(true);
	}

	public static Boolean belongCalendar(Date now, Date beginTime, Date endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 功能：得到当前年份年底 格式为：xxxx-yy-zz (eg: 2007-12-31)<br>
	 * 
	 * @return String
	 * @author pure
	 */
	public static String thisYearEnd() {
		localTime = Calendar.getInstance();
		x = localTime.get(Calendar.YEAR);
		return x + "-12" + "-31 23:59:59";
	}

	/**
	 * 功能：得到当前年份年初 格式为：xxxx-yy-zz (eg: 2007-01-01)<br>
	 * 
	 * @return String
	 * @author pure
	 */
	public static String thisYear() {
		localTime = Calendar.getInstance();
		x = localTime.get(Calendar.YEAR);
		return x + "-01" + "-01 00:00:00";
	}

	public static String getThedaybefore() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return ymd_f.format(date) + " 00:00:00" + "," + ymd_f.format(date) + " 23:59:59";

	}

	/**
	 * 根据当前日期获得所在周的日期区间（周一和周日日期）
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getTimeInterval(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		String imptimeBegin = ymd_f.format(cal.getTime()) + " 00:00:00";
		// System.out.println("所在周星期一的日期：" + imptimeBegin);
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = ymd_f.format(cal.getTime()) + " 23:59:59";
		// System.out.println("所在周星期日的日期：" + imptimeEnd);
		return imptimeBegin + "," + imptimeEnd;
	}

	/**
	 * 获取当前月的起始和结束
	 * 
	 * @return
	 */
	public static String getMonthend() {
		// 获取当前月第一天：
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = ymd_f.format(c.getTime());
		// 获取当前月最后一天
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = ymd_f.format(ca.getTime());
		return first + " 00:00:00," + last + " 23:59:59";
	}

	/**
	 * 获取前月的起始和结束
	 * 
	 * @return
	 */
	public static String getTheMonthend() {
		// 获取前月的第一天
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.add(Calendar.MONTH, -1);
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String firstDay = ymd_f.format(cal_1.getTime());
		// 获取前月的最后一天
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
		String lastDay = ymd_f.format(cale.getTime());
		return firstDay + " 00:00:00," + lastDay + " 23:59:59";

	}

	/**
	 * 根据当前日期获得上周的日期区间（上周周一和周日日期）
	 * 
	 * @return
	 * 
	 */
	public static String getLastTimeInterval() {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
		int offset1 = 1 - dayOfWeek;
		int offset2 = 7 - dayOfWeek;
		calendar1.add(Calendar.DATE, offset1 - 7);
		calendar2.add(Calendar.DATE, offset2 - 7);
		// System.out.println(sdf.format(calendar1.getTime()));// last Monday
		String lastBeginDate = ymd_f.format(calendar1.getTime()) + " 00:00:00";
		// System.out.println(sdf.format(calendar2.getTime()));// last Sunday
		String lastEndDate = ymd_f.format(calendar2.getTime()) + " 23:59:59";
		return lastBeginDate + "," + lastEndDate;
	}

	/**
	 * 获取{begintime}时间前{month}天的时间
	 * 
	 * @param begintime
	 *            可以不用传入 默认为当天
	 * @param month
	 *            天
	 * @return
	 * @throws ParseException
	 */
	public static String getBetweenDate(String begintime, int month) throws ParseException {
		Date date = new Date();
		if (BaseUtil.isNotNull(begintime)) {
			date = ymdhms_f.parse(begintime);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		String lastBeginDate = ymd_f.format(date) + " 00:00:00";
		// System.out.println(sdf.format(calendar2.getTime()));// last Sunday
		String lastEndDate = ymd_f.format(date) + " 23:59:59";
		return lastBeginDate + "," + lastEndDate;
	}

	/**
	 * 根据开始时间和结束时间返回时间段内的时间集合
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return List
	 * @throws ParseException
	 */
	public static List getDatesBetweenTwoDate(String beginTime, String endTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = sdf.parse(beginTime);
		Date endDate = sdf.parse(endTime);
		List lDate = new ArrayList();
		lDate.add(beginTime);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				// lDate.add(cal.getTime());
				lDate.add(sdf.format(cal.getTime()));
			} else {
				break;
			}
		}
		lDate.add(endTime);
		return lDate;
	}

	public static String getNowBeforeMin(Date date, int min) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, min);
		return sdf.format(c.getTime());
	}

	public static String getYMDHM(String data) throws ParseException {
		return ymdhm_f.format(ymdhm_f.parse(data)) + ":00";
	}

	/**
	 * 根据开始时间和结束时间返回时间段内的时间集合
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return List
	 * @throws ParseException
	 */
	public static List getDatesBetweenTwoDate(String beginTime, String endTime, String type) throws ParseException {
		SimpleDateFormat sdf = null;
		List lDate = new ArrayList();

		if ("1".equals(type)) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH");
			beginTime = UtilTime.getNowYMDH(beginTime);
			endTime = UtilTime.getNowYMDH(endTime);
		} else if ("5".equals(type)) {

			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			beginTime = UtilTime.getNowYMDH(beginTime);
			endTime = UtilTime.getYMDHM(endTime);
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			beginTime = UtilTime.getNowYMD(beginTime);
			endTime = UtilTime.getNowYMD(endTime);
		}
		Date beginDate = sdf.parse(beginTime + "");
		Date endDate = sdf.parse(endTime);
		lDate.add(beginTime);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			if ("1".equals(type)) {
				cal.add(Calendar.HOUR_OF_DAY, 1);

			} else if ("5".equals(type)) {
				cal.add(Calendar.MINUTE, 10);

			} else {
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}

			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				// lDate.add(cal.getTime());
				if ("1".equals(type)) {
					lDate.add(sdf.format(cal.getTime()) + ":00:00");
				} else if ("5".equals(type)) {
					lDate.add(sdf.format(cal.getTime()) + ":00");

				} else {
					lDate.add(sdf.format(cal.getTime()) + " 00:00:00");
				}
			} else {
				break;
			}
		}
		lDate.add(endTime);
		return lDate;
	}

	/**
	 * 将字符串格式yyyyMMdd的字符串转为日期格式"yyyy-MM-dd"的字符串
	 *
	 * @param date
	 *            日期字符串
	 * @return 返回格式化的日期
	 * @throws ParseException
	 *             分析时意外地出现了错误异常
	 */
	public static String strToDateFormat(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		// setLenient(Boolean):是否严格解析日期
		formatter.setLenient(false);
		Date newDate = formatter.parse(date);
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(newDate);
	}

	/**
	 * 日期字符串格式为yyyy-MM-dd
	 * 
	 * @param sourDay
	 * @param currentDay
	 * @return
	 * @throws Exception
	 */
	public static int intervalDays(String sourDay, String currentDay) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = format.parse(currentDay);
		Date date2 = format.parse(sourDay);
		return (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
	}
	  /**
	    * 当前时间-保存天数=最早天数
	    * @param currentDate
	    * @param day
	    * @return
	    */
	   public static String expiredDate(Date currentDate,int day) {
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		   long expiredTime = currentDate.getTime()-day*24*3600*1000;
		   Date date = new Date();
		   date.setTime(expiredTime);
		   return sdf.format(date);
	   }
}

class FPS {
	private boolean b;

	public void setB(boolean b) {
		this.b = b;
	}

	public boolean isB() {
		return b;
	}

}
