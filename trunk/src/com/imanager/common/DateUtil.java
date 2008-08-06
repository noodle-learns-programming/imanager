package com.imanager.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class DateUtil {
	
	public static final long MILLISECONDS_A_DAY = 24 * 3600 * 1000;
	
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 当前时间加上days天
	 */
	public static Date addDays(Date date, int days) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, days);
			return cal.getTime();
		} else {
			return null;
		}
	}
	
	/**
	 * 当前时间加上days月
	 */
	public static Date addMonths(Date date, int months) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, months);
			return cal.getTime();
		} else {
			return null;
		}
	}
	
	/**
	 * 获取当前月的最大日期
	 * @return
	 */
	public static Date getMaxDate(){
		Calendar cal = Calendar.getInstance();	
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		return cal.getTime();
	}
	
	/**
	 * 获取当前月的最小日期
	 * @return
	 */
	public static Date getMinDate(){
		Calendar cal = Calendar.getInstance();	
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		return cal.getTime();
	}
	
	/**
	 * 当前时间加上years年
	 */
	public static Date addYears(Date date, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}
	
	/**
	 * 获得指定格式的日期时间字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String datetime(Date date, String format) {
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.format(date);
		} else {
			return "";
		}
	}
	
	/**
	 * 获得指定格式的日期时间字符串
	 * @param 日期字符串
	 * @param format
	 * @return
	 */
	public static String datetime(String date, String format) {
		if (date != null && date.length() > 0) {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.format(date);
		} else {
			return "";
		}
	}
	
	/**
	 * 获得指定格式的当前日期字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date(Date date, String format) {
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 获得指定格式的当前日期字符串
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static String date(String dateStr, String format) {
		if (dateStr != null && dateStr.length() > 0) {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return formatter.format(dateStr);
		} else {
			return "";
		}
	}
	
	/**
	 * 获得"yyyy-MM-dd"格式的当前日期字符串
	 * @param date
	 * @return
	 */
	public static String getNowDateStr() {
		return getNowDatetimeStr(DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 获得"yyyy-MM-dd HH:mm:ss"格式的当前日期时间字符串
	 * @param date
	 * @return
	 */
	public static String getNowDatetimeStr() {
		return getNowDatetimeStr(DEFAULT_DATETIME_FORMAT);
	}
	
	/**
	 * 获得当前日期时间字符串
	 * @param format 日期格式
	 * @return 日期时间字符串
	 */
	public static String getNowDatetimeStr(String format) {
		Calendar cal = Calendar.getInstance();
		return datetime(cal.getTime(), format);
	}
	
	/**
	 * 只取当前时间的日期部分，小时、分、秒等字段归零
	 */
	public static Date dateOnly(Date date) {
		return new Date(date.getTime() / MILLISECONDS_A_DAY);
	}
	
	
	/**
	 * 只取当前时间的日期部分，小时、分、秒等字段归零
	 */
	public static Date dateOnlyExt(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);	
		return cal.getTime();
	}
	
	/**
	 * 只取当前时间的日期的最后时刻，23：59：59
	 */
	public static Date dateLastTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);	
		return cal.getTime();
	}
	
	/**
	 * 把类似2007-2-2 7:1:8的时间串变为标准的2007-02-02 07:01:08
	 * 
	 * @param dateTimeStr
	 *            未校正日期值
	 * @return 日期对象
	 */
	public static String getStandDateTimeStr(String dateTimeStr) {
		if (dateTimeStr == null || "".equals(dateTimeStr)) {
			return "";
		}
		
		dateTimeStr = dateTimeStr.replaceAll("\\s+", "|");
		String[] a = dateTimeStr.split("\\|");
		List list = Arrays.asList(a);
		String datetime = "";
		int count = 1;
		for (int i = 0; i < list.size(); i++) {
			String temp = (String) list.get(i);
			StringTokenizer st;
			if (i == 0)
				st = new StringTokenizer(temp, "-");
			else
				st = new StringTokenizer(temp, ":");
			int k = st.countTokens();
			for (int j = 0; j < k; j++) {
				String sttemp = (String) st.nextElement();
				if (count == 1) {
					datetime = sttemp;
				} else {
					if ((sttemp.equals("0")) || (sttemp.equals("00")))
						sttemp = "0";
					else if ((Integer.valueOf(sttemp).intValue()) < 10)
						sttemp = sttemp.replaceAll("0", "");
					if (count < 4) {
						if ((Integer.valueOf(sttemp).intValue()) < 10)
							datetime = datetime + "-0" + sttemp;
						else
							datetime = datetime + "-" + sttemp;
					}
					if (count == 4) {
						if ((Integer.valueOf(sttemp).intValue()) < 10)
							datetime = datetime + " 0" + sttemp;
						else
							datetime = datetime + " " + sttemp;
					}
					if (count > 4) {
						if ((Integer.valueOf(sttemp).intValue()) < 10)
							datetime = datetime + ":0" + sttemp;
						else
							datetime = datetime + ":" + sttemp;
					}
				}
				count++;
			}
		}
		
		try{
			Date test = getDateFromStr(datetime); //测试能否格式化成时间
			return datetime;
		}catch(Exception e){
			return "";
		}
	}

	/**
	 * 把标准的2007-02-02 07:01:08格式转换成日期对象
	 * 
	 * @param datetime
	 *            日期,标准的2007-02-02 07:01:08格式
	 * @return 日期对象
	 */
	public static Date getDateFromStr(String datetime) {
		if (datetime == null || "".equals(datetime)) {
			return new Date();
		}

		String nyr = datetime.trim();

		if (nyr.indexOf(" ") > 0) {
			nyr = nyr.substring(0, nyr.indexOf(" "));
		} else {
			nyr = nyr.substring(0, nyr.length());
		}

		StringTokenizer st = new StringTokenizer(nyr, "-");
		Date date = new Date();
		String temp = "";
		int count = st.countTokens();
		for (int i = 0; i < count; i++) {
			temp = (String) st.nextElement();
			// if(!(temp.equals("0")))
			// temp.replaceAll("0", "");
			if (i == 0)
				date.setYear(Integer.parseInt(temp) - 1900);
			if (i == 1)
				date.setMonth(Integer.parseInt(temp) - 1);
			if (i == 2)
				date.setDate(Integer.parseInt(temp));
		}

		if (datetime.length() > 10) {
			String sfm = datetime.substring(11, 19);
			StringTokenizer st2 = new StringTokenizer(sfm, ":");
			count = st2.countTokens();
			for (int i = 0; i < count; i++) {
				temp = (String) st2.nextElement();
				// if(!(temp.equals("0")))
				// temp.replaceAll("0", "");
				if (i == 0)
					date.setHours(Integer.parseInt(temp));
				if (i == 1)
					date.setMinutes(Integer.parseInt(temp));
				if (i == 2)
					date.setSeconds(Integer.parseInt(temp));
			}
		}
		return date;
	}
	
	/**
	 * 返回两个日期相差天数
	 * @param startDate 起始日期对象
	 * @param endDate	截止日期对象
	 * @return
	 */
	public static int getQuot(Date startDate, Date endDate){ 
		long quot = 0; 
		quot = endDate.getTime() - startDate.getTime(); 
		quot = quot / MILLISECONDS_A_DAY; 
		return (int)quot; 
	} 
	
	/**
	 * 返回两个日期相差天数
	 * @param startDateStr	起始日期字符串
	 * @param endDateStr	截止期字符串
	 * @param format		时间格式
	 * @return
	 */
	public static int getQuot(String startDateStr, String endDateStr, String format){ 
		long quot = 0; 
		format = (format != null && format.length() > 0) ? format : DEFAULT_DATE_FORMAT;
		SimpleDateFormat ft = new SimpleDateFormat(format);
		try {
			Date date1 = ft.parse(endDateStr); 
			Date date2 = ft.parse(startDateStr); 
			quot = date1.getTime() - date2.getTime(); 
			quot = quot / MILLISECONDS_A_DAY; 
		} catch (ParseException e) { 
			e.printStackTrace(); 
		} 
		return (int)quot; 
	}
	
}