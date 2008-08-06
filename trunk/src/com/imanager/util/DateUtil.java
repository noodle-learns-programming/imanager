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
	 * ��ǰʱ�����days��
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
	 * ��ǰʱ�����days��
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
	 * ��ȡ��ǰ�µ��������
	 * @return
	 */
	public static Date getMaxDate(){
		Calendar cal = Calendar.getInstance();	
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		return cal.getTime();
	}
	
	/**
	 * �����ѡ���µ����ʱ�� 23:59:59
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
	 * ��ȡ��ǰ�µ���С����
	 * @return
	 */
	public static Date getMinDate(){
		Calendar cal = Calendar.getInstance();	
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		return cal.getTime();
	}
	
	/**
	 * �����ѡ���µ���Сʱ�� 00:00:00
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
	 * ��ð���ǰһ�������ʮ��
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
	 * ��ǰʱ�����years��
	 */
	public static Date addYears(Date date, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}
	
	/**
	 * ���ָ����ʽ������ʱ���ַ���
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
	 * ���ָ����ʽ������ʱ���ַ���
	 * @param �����ַ���
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
	 * ���ָ����ʽ�ĵ�ǰ�����ַ���
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
	 * ���ָ����ʽ�ĵ�ǰ�����ַ���
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
	 * ���"yyyy-MM-dd"��ʽ�ĵ�ǰ�����ַ���
	 * @param date
	 * @return
	 */
	public static String getNowDateStr() {
		return getNowDatetimeStr(DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * ���"yyyy-MM-dd HH:mm:ss"��ʽ�ĵ�ǰ����ʱ���ַ���
	 * @param date
	 * @return
	 */
	public static String getNowDatetimeStr() {
		return getNowDatetimeStr(DEFAULT_DATETIME_FORMAT);
	}
	
	/**
	 * ��õ�ǰ����ʱ���ַ���
	 * @param format ���ڸ�ʽ
	 * @return ����ʱ���ַ���
	 */
	public static String getNowDatetimeStr(String format) {
		Calendar cal = Calendar.getInstance();
		return datetime(cal.getTime(), format);
	}
	
	/**
	 * ֻȡ��ǰʱ������ڲ��֣�Сʱ���֡�����ֶι���
	 */
	public static Date dateOnly(Date date) {
		return new Date(date.getTime() / MILLISECONDS_A_DAY);
	}
	
	
	/**
	 * ֻȡ��ǰʱ������ڲ��֣�Сʱ���֡�����ֶι���
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
	 * ֻȡ����ʱ������ڵĿ�ʼʱ�̣�00:00:00
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
	 * ֻȡ����ʱ������ڵ����ʱ�̣�23��59��59
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
	 * ������2007-2-2 7:1:8��ʱ�䴮��Ϊ��׼��2007-02-02 07:01:08
	 * 
	 * @param dateTimeStr
	 *            δУ������ֵ
	 * @return ���ڶ���
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
				Date test = getDateFromStr(datetime); //�����ܷ��ʽ����ʱ��
			}catch(Exception e){
				datetime = "";
			}
		}		
		
		return datetime;
	}

	/**
	 * �ѱ�׼��2007-02-02 07:01:08��ʽת�������ڶ���
	 * 
	 * @param datetime
	 *            ����,��׼��2007-02-02 07:01:08��ʽ
	 * @return ���ڶ���
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
	 * �������������������
	 * @param startDate ��ʼ���ڶ���
	 * @param endDate	��ֹ���ڶ���
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
		int months =(dt2.getYear()-dt1.getYear())*12+(dt2.getMonth()-dt1.getMonth());//��������� 
		return months;
	}
	/**
	 * �������������������
	 * @param startDateStr	��ʼ�����ַ���
	 * @param endDateStr	��ֹ���ַ���
	 * @param format		ʱ���ʽ
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