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
	 * ��ȡ��ǰ�µ���С����
	 * @return
	 */
	public static Date getMinDate(){
		Calendar cal = Calendar.getInstance();	
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		return cal.getTime();
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
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);	
		return cal.getTime();
	}
	
	/**
	 * ֻȡ��ǰʱ������ڵ����ʱ�̣�23��59��59
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
	 * ������2007-2-2 7:1:8��ʱ�䴮��Ϊ��׼��2007-02-02 07:01:08
	 * 
	 * @param dateTimeStr
	 *            δУ������ֵ
	 * @return ���ڶ���
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
			Date test = getDateFromStr(datetime); //�����ܷ��ʽ����ʱ��
			return datetime;
		}catch(Exception e){
			return "";
		}
	}

	/**
	 * �ѱ�׼��2007-02-02 07:01:08��ʽת�������ڶ���
	 * 
	 * @param datetime
	 *            ����,��׼��2007-02-02 07:01:08��ʽ
	 * @return ���ڶ���
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
	
}