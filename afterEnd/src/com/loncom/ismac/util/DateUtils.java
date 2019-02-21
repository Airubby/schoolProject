package com.loncom.ismac.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.Serializable;

/**
 * <pre>
 *        mine
 *         
 * File: DateUtils.java
 *
 * mine, Inc. Copyright (C): 2008
 *
 * Description:
 * 日期工具类，使用日期类型时，必须使用该类
 * 
 * Notes:
 *
 * Revision History
 * Date      Name      Desc
 * 11/07/08  Chen Jianfeng   Initial
 *
 * </pre>
 */

@SuppressWarnings("rawtypes")
public final class DateUtils implements Serializable, Comparable {
	private final static int[] full_day_month = new int[] { 1, 3, 5, 7, 8, 10,12 };

	/**
	 * The year of the gregorianCutover, with 0 representing 1 BC, -1
	 * representing 2 BC, etc.
	 */
	private static transient int gregorianCutoverYear = 1582;

	private final static int[] normal_day_month = new int[] { 4, 6, 9, 11 };

	public final static int QUARTER_OF_FIRST = 1;

	public final static int QUARTER_OF_SECOND = 2;

	public final static int QUARTER_OF_THIRD = 3;
	
	public final static int QUARTER_OF_FOURTH = 4;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1574586998788149344L;

	
	private Calendar cal = null;

	private Date dt = null;

	private Timestamp ts = null;
	
	/**
	 * 取得某年月最大日。
	 * 
	 * @param y
	 * @param m
	 * @return
	 */
	public static int getMaxDay(int y, int m) {
		if (isLeapNormalMonth(y, m))
			return 29;
		else if (isNormalMonth(y, m))
			return 28;
		else if (isSmallMonth(m))
			return 30;
		else
			return 31;
	}

	/**
	 * 是否为大月
	 * 
	 * @param m
	 * @return
	 */
	public static boolean isBigMonth(int m) {
		for (int i = 0; i < full_day_month.length; i++) {
			if (m == full_day_month[i])
				return true;
		}
		return false;
	}

	/**
	 * 是否为闰平月
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static boolean isLeapNormalMonth(int y, int m) {
		return isleapyear(y) && m == 2;
	}

	/**
	 * 是否闰年。
	 * 
	 * @param y
	 * @return
	 */
	public static boolean isleapyear(int y) {
		return y >= gregorianCutoverYear ? 
				((y % 4 == 0) && ((y % 100 != 0) || (y % 400 == 0)))// Gregorian
				: (y % 4 == 0); // Julian
	}

	/**
	 * 是否为普通平月
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static boolean isNormalMonth(int y, int m) {
		return !isleapyear(y) && m == 2;
	}

	/**
	 * 是否为小月
	 * 
	 * @param m
	 * @return
	 */
	public static boolean isSmallMonth(int m) {
		for (int i = 0; i < normal_day_month.length; i++) {
			if (m == normal_day_month[i])
				return true;
		}
		return false;
	}
	
	public DateUtils() {
		cal = Calendar.getInstance();
		dt = cal.getTime();
		ts = new Timestamp(cal.getTimeInMillis());
	}

	public DateUtils(Calendar cal) {
		init(cal);
	}

	public DateUtils(Date dt) {
		init(dt);
	}

	/**
	 * 根据字符串和模式获得日期，日期和模式必须匹配
	 * @param dateString 2007-1-1
	 * @param partern    yyyy-MM-dd HH:MM:SS
	 */
	public DateUtils(String  dateString,String partern) {
		SimpleDateFormat sdf = new SimpleDateFormat(partern); 
		try {
			dt = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		init(dt);
	}
	
	public DateUtils(int year) {
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		init(cal);
	}

	public DateUtils(int year, int month) {
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		init(cal);
	}

	public DateUtils(int year, int month, int day) {
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, day);
		init(cal);
	}

	public DateUtils(int year, int month, int day, int hour) {
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		init(cal);
	}

	public DateUtils(int year, int month, int day, int hour, int minute) {
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		init(cal);
	}

	public DateUtils(int year, int month, int day, int hour, int minute,
			int second) {
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		init(cal);
	}

	public DateUtils(int year, int month, int day, int hour, int minute,
			int second, int millisecond) {
		cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, millisecond);
		init(cal);
	}

	public DateUtils(DateUtils tdt) {
		cal = (Calendar) tdt.cal.clone();
		init(cal);
	}

	public DateUtils(Timestamp ts) {
		init(ts);
	}

	/**
	 * 增加日期。
	 * 
	 * @param add
	 */
	public void addDay(int add) {
		cal.add(Calendar.DATE, add);
		init(cal);
	}

	/**
	 * 增加小时。
	 * 
	 * @param add
	 */
	public void addHour(int add) {
		cal.add(Calendar.HOUR_OF_DAY, add);
		init(cal);
	}

	/**
	 * 增加毫秒。
	 * 
	 * @param add
	 */
	public void addMillisecond(int add) {
		cal.add(Calendar.MILLISECOND, add);
		init(cal);
	}

	/**
	 * 增加分钟。
	 * 
	 * @param add
	 */
	public void addMinute(int add) {
		cal.add(Calendar.MINUTE, add);
		init(cal);
	}

	/**
	 * 增加月。
	 * 
	 * @param add
	 */
	public void addMonth(int add) {
		cal.add(Calendar.MONTH, add);
		init(cal);
	}

	/**
	 * 增加秒。
	 * 
	 * @param add
	 */
	public void addSecond(int add) {
		cal.add(Calendar.SECOND, add);
		init(cal);
	}

	/**
	 * 增加年份。
	 * 
	 * @param add
	 */
	public void addYear(int add) {
		cal.add(Calendar.YEAR, add);
		init(cal);
	}

	
	/**
	 * DateString转化的时间与当前比较
	 * @param DateString
	 * @param partern
	 * @return
	 */
	public int compareToString(String DateString,String partern) {
		return compareTo((new DateUtils(DateString,partern)));
		
		
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable
	 * #compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o == null) {
			return 1;
		}
		if (o instanceof DateUtils) {
			return compareTo((DateUtils) o);
		}
		return -1;
	}

	public int compareTo(DateUtils tdt) {
		if (tdt == null) {
			return 1;
		}
		return dt.compareTo(tdt.dt);
	}

	/**
	 * 取得Calendar数据。
	 * 
	 * @return
	 */
	public Calendar getCalendar() {
		return cal;
	}

	/**
	 * 取得Date数据。
	 * 
	 * @return
	 */
	public Date getDate() {
		return dt;
	}

	/**
	 * 取得日期。
	 * 
	 * @return Returns the day.
	 */
	public int getDay() {
		return cal.get(Calendar.DATE);
	}

	/**
	 * 取得两个日期相隔天数。 <br>
	 * 如果比参数日大，返回正数，否则如果小，返回负数，否则返回零。
	 * 
	 * @param tdt
	 * @return
	 */
	public int getDayDistance(DateUtils tdt) {
		tdt = new DateUtils(tdt);
		DateUtils clone = new DateUtils(this);
		clone.setHour(0);
		clone.setMinute(0);
		clone.setSecond(0);
		clone.setMillisecond(0);
		tdt.setHour(0);
		tdt.setMinute(0);
		tdt.setSecond(0);
		tdt.setMillisecond(0);

		int day = 0;
		if (clone.compareTo(tdt) > 0) {
			while (clone.compareTo(tdt) > 0) {
				day++;
				clone.addDay(-1);
			}
		} else {
			while (clone.compareTo(tdt) < 0) {
				day--;
				clone.addDay(1);
			}
		}
		return day;
	}

	/**
	 * 取得小时。
	 * 
	 * @return Returns the hour.
	 */
	public int getHour() {
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 取得本月最大天数。
	 * 
	 * @return
	 */
	public int getMaxDay() {
		return getMaxDay(getYear(), getMonth());
	}

	/**
	 * 取得毫秒。
	 * 
	 * @return Returns the millisecond.
	 */
	public int getMillisecond() {
		return cal.get(Calendar.MILLISECOND);
	}

	/**
	 * 取得分钟。
	 * 
	 * @return Returns the minute.
	 */
	public int getMinute() {
		return cal.get(Calendar.MINUTE);
	}

	/**
	 * 取得月份。
	 * 
	 * @return Returns the month.
	 */
	public int getMonth() {
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 取得季节。
	 * 
	 * @return
	 * @throws Exception 
	 */
	public int getQuarter() throws Exception {
		int month = getMonth();
		switch (month) {
		case 1:
		case 2:
		case 3:
			return DateUtils.QUARTER_OF_FIRST;
		case 4:
		case 5:
		case 6:
			return DateUtils.QUARTER_OF_SECOND;
		case 7:
		case 8:
		case 9:
			return DateUtils.QUARTER_OF_THIRD;
		case 10:
		case 11:
		case 12:
			return DateUtils.QUARTER_OF_FOURTH;
		}
	throw new Exception("month is error");
	}

	/**
	 * 取得秒。
	 * 
	 * @return Returns the second.
	 */
	public int getSecond() {
		return cal.get(Calendar.SECOND);
	}

	/**
	 * 获得Timestamp类型数据。
	 * 
	 * @return
	 */
	public Timestamp getTimestamp() {
		return ts;
	}

	/**
	 * 取得年。
	 * 
	 * @return Returns the year.
	 */
	public int getYear() {
		return cal.get(Calendar.YEAR);
	}

	/**
	 * @param c
	 */
	private void init(Calendar c) {
		cal = c;
		dt = cal.getTime();
		ts = new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * @param d
	 */
	private void init(Date d) {
		dt = d;
		ts = new Timestamp(d.getTime());
		cal = Calendar.getInstance();
		cal.setTimeInMillis(ts.getTime());
	}

	/**
	 * @param t
	 */
	private void init(Timestamp t) {
		ts = t;
		cal = Calendar.getInstance();
		cal.setTimeInMillis(ts.getTime());
		dt = cal.getTime();
	}

	/**
	 * 设置日期。
	 * 
	 * @param day
	 *            The day to set.
	 */
	public void setDay(int day) {
		cal.set(Calendar.DATE, day);
		init(cal);
	}

	/**
	 * 设置小时。
	 * 
	 * @param hour
	 *            The hour to set.
	 */
	public void setHour(int hour) {
		cal.set(Calendar.HOUR_OF_DAY, hour);
		init(cal);
	}

	/**
	 * 设置毫秒。
	 * 
	 * @param millisecond
	 *            The millisecond to set.
	 */
	public void setMillisecond(int millisecond) {
		cal.set(Calendar.MILLISECOND, millisecond);
		init(cal);
	}

	/**
	 * 设置分钟。
	 * 
	 * @param minute
	 * The minute to set.
	 */
	public void setMinute(int minute) {
		cal.set(Calendar.MINUTE, minute);
		init(cal);
	}

	/**
	 * 设置月份。
	 * 
	 * @param month
	 *  month to set.
	 */
	public void setMonth(int month) {
		cal.set(Calendar.MONTH, month - 1);
		init(cal);
	}

	/**
	 * 设置秒。
	 * 
	 * @param second
	 *  The second to set.
	 */
	public void setSecond(int second) {
		cal.set(Calendar.SECOND, second);
		init(cal);
	}

	/**
	 * 设置年。
	 * 
	 * @param year
	 *  The year to set.
	 */
	public void setYear(int year) {
		cal.set(Calendar.YEAR, year);
		init(cal);
	}

	/**
	 * 返回日期：年月日串。
	 * 
	 * @return
	 */
	public String toDateString() {
		return toString("yyyy-MM-dd");
	}
	
	/**
	 * 返回日期：年月日日分秒串。
	 * 
	 * @return
	 */
	public String toDateHMSString() {
		return toString("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 返回日期：年月日日分秒微妙串
	 * 
	 * @return
	 */
	public String toDateTimeString() {
		return toString("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 返回短时间类型。
	 * 
	 * @return
	 */
	public String toShortTimeString() {
		return toString("HH:mm");
	}

	/**
	 * 返回时间串。
	 * 
	 * @return
	 */
	public String toTimeString() {
		return toString("HH:mm:ss.SSS");
	}
	
	/**
	 * 
	 * @todo 返回年　2011年(01月-01日) -> 2011 <br/>截取前四位
	 * @user yangyb
	 * @date Oct 9, 2011
	 * @time 2:42:53 PM
	 * @param year
	 * @return
	 */
	public static String toYear(String year){
		if(year!=null&&year.length()>4){
			return year.substring(0,4);
		}else{
			return null;
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object
	 * #toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("DateUtils");
		sb.append("[");
		sb.append(getTimestamp());
		sb.append("]");
		sb.append("[");
		sb.append("Year:");
		sb.append(getYear());
		sb.append(",");
		sb.append("Month:");
		sb.append(getMonth());
		sb.append(",");
		sb.append("Day:");
		sb.append(getDay());
		sb.append(",");
		sb.append("Hour:");
		sb.append(getHour());
		sb.append(",");
		sb.append("Minute:");
		sb.append(getMinute());
		sb.append(",");
		sb.append("Second:");
		sb.append(getSecond());
		sb.append(",");
		sb.append("MilliSecond:");
		sb.append(getMillisecond());
		sb.append("]");
		return sb.toString();
	}

	/**
	 * <pre>
	 * 时间格式语法： 
	 *                          
	 * 使用一个 time pattern 字符串指定时间格式。 在这种方式下，
	 * 所有的 ASCII 字母被保留为模式字母，定义如下： 
	 *                          
	 * 符号     含义                    表示                示例
	 * ------   -------                 ------------        -------
	 * G        年代标志符              (Text)              AD
	 * y        年                     (Number)            1996
	 * M        月                      (Text &amp; Number)     July &amp; 07
	 * d        日                      (Number)            10
	 * h        时 在上午或下午 (1?12)  (Number)            12
	 * H        时 在一天中 (0?23)      (Number)            0
	 * m        分                      (Number)            30
	 * s        秒                      (Number)            55
	 * S        毫秒                    (Number)            978
	 * E        星期                    (Text)              Tuesday
	 * D        一年中的第几天          (Number)            189
	 * F        一月中第几个星期几      (Number)            2  (2nd Wed in July)
	 * w        一年中第几个星期        (Number)            27
	 * W        一月中第几个星期        (Number)            2
	 * a        上午 / 下午 标记符      (Text)              PM
	 * k        时 在一天中 (1?24)      (Number)            24
	 * K        时 在上午或下午 (0?11)  (Number)            0
	 * z        时区                    (Text)      Pacific Standard Time
	 * '        文本转义符              (Delimiter)
	 * ''       单引号                  (Literal)           '
	 * </pre>
	 * 
	 * @param parttern
	 * @return
	 */
	/**
	 * @param parttern
	 * @return
	 */
	public String toString(String parttern) {
		SimpleDateFormat sdf = new SimpleDateFormat(parttern);
		return sdf.format(dt);
	}
	
	/**
	 * @param  DateBean date
	 * @param parttern
	 * @return
	 */
	public static String toString(Date date,String parttern) {
		if(date==null){
			return "";
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat(parttern);
			return sdf.format(date);	
		}
	}
}
