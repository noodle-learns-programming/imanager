package com.imanager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Yang Qiang
 * @since 2008-05-01
 *
 */
public class DateUtil {
	
	public static final long MILLISECONDS_A_DAY = 24 * 3600 * 1000;
	
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	private static final String LONG_REG_EXP = 
		"([0-9]{2,4})-([0-9]{1,2})-([0-9]{1,2})\\s+([0-9]{2}):([0-9]{2}):([0-9]{2})";
	
	private static final String SHORT_REG_EXP = 
		"([0-9]{2,4})-([0-9]{1,2})-([0-9]{1,2})";
	
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
	 * 获得所选年月的最大时间 23:59:59
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMaxDateByYearMonth(int year, int month){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
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
	 * 获得所选年月的最小时间 00:00:00
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMinDateByYearMonth(int year, int month){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);	
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		return cal.getTime();
	}
	

	/**
	 * 获得包括前一年的连续十年
	 * @param date
	 * @param years
	 * @return
	 */
	public static List<String> getPreviousYearAndNestTen() {
		List<String> yearList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		int theYear = cal.get(Calendar.YEAR) - 1;
		for (int i = 0; i < 10; i ++) {
			yearList.add(String.valueOf(theYear + i));
		}
		return yearList;
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
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 只取传入时间的日期的开始时刻，00:00:00
	 */
	public static Date dateBeginTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);	
		return cal.getTime();
	}
	
	/**
	 * 只取传入时间的日期的最后时刻，23：59：59
	 */
	public static Date dateEndTime(Date date) {
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
		String datetime = "";
		
		if (dateTimeStr != null && 
				( "".equals( dateTimeStr.trim().replaceAll(LONG_REG_EXP, "") ) || 
				  "".equals( dateTimeStr.trim().replaceAll(SHORT_REG_EXP, "") ) ) ) {
			String[] a = dateTimeStr.split("\\s+");
			int count = 1;
			for (int i = 0; i < a.length; i++) {
				String temp = (String) a[i];
				StringTokenizer st;
				if (i == 0)
					st = new StringTokenizer(temp, "-");
				else
					st = new StringTokenizer(temp, ":");
				int k = st.countTokens();
				for (int j = 0; j < k; j++) {
					String sttemp = (String) st.nextElement();
					if (count == 1) {
						switch (sttemp.length()) {
							case 4 :
								datetime = sttemp;
								break;
							case 2 : 
								datetime = getNowDatetimeStr().substring(0, 2) + sttemp;
								break;
							default:
								datetime = sttemp;
						}
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
				@SuppressWarnings("unused")
				Date test = getDateFromStr(datetime); //测试能否格式化成时间
			}catch(Exception e){
				datetime = "";
			}
		}		
		
		return datetime;
	}

	/**
	 * 把标准的2007-02-02 07:01:08格式转换成日期对象
	 * 
	 * @param datetime
	 *            日期,标准的2007-02-02 07:01:08格式
	 * @return 日期对象
	 */
	public static Date getDateFromStr(String datetime) {
		if (datetime != null && 
				( "".equals( datetime.trim().replaceAll(LONG_REG_EXP, "") ) || 
				  "".equals( datetime.trim().replaceAll(SHORT_REG_EXP, "") ) ) ) {
			String nyr = datetime.trim();

			if (nyr.indexOf(" ") > 0) {
				nyr = nyr.substring(0, nyr.indexOf(" "));
			} else {
				nyr = nyr.substring(0, nyr.length());
			}

			StringTokenizer st = new StringTokenizer(nyr, "-");
			Calendar cal = Calendar.getInstance();
			cal.clear();
			int temp = 0;
			int count = st.countTokens();
			for (int i = 0; i < count; i++) {
				temp = Integer.parseInt( (String) st.nextElement() );
				// if(!(temp.equals("0")))
				// temp.replaceAll("0", "");
				if (i == 0)
					cal.set(Calendar.YEAR, temp);
				if (i == 1)
					cal.set(Calendar.MONTH, temp - 1);
				if (i == 2)
					cal.set(Calendar.DATE, temp);
			}

			if (datetime.length() > 10) {
				String sfm = datetime.substring(11, 19);
				StringTokenizer st2 = new StringTokenizer(sfm, ":");
				count = st2.countTokens();
				for (int i = 0; i < count; i++) {
					temp = Integer.parseInt( (String) st2.nextElement() );
					// if(!(temp.equals("0")))
					// temp.replaceAll("0", "");
					if (i == 0)
						cal.set(Calendar.HOUR_OF_DAY, temp);
					if (i == 1)
						cal.set(Calendar.MINUTE, temp);
					if (i == 2)
						cal.set(Calendar.SECOND, temp);
				}
			}
			
			return cal.getTime();
		} else {
			return new Date();
		}
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
	
	
	@SuppressWarnings("deprecation")
	public static int getMonthBetween(Date dt1,Date dt2){
		int months =(dt2.getYear()-dt1.getYear())*12+(dt2.getMonth()-dt1.getMonth());//相隔整月数 
		return months;
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
	
	public static int getDaysBetween(Date date1, Date date2){ 
		long quot = 0; 
		quot = date2.getTime() - date1.getTime(); 
		quot = quot / MILLISECONDS_A_DAY; 
		return (int)quot; 
	}
	
	
	public static Date getCurrentDate(){
		return new Date();
	}
	
	public static void main(String[] args) {
		
	}
	
}