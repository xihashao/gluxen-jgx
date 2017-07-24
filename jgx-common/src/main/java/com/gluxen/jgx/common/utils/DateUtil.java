package com.gluxen.jgx.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * <p>description：获取当前日期</p>
	 * @author：Administrator
	 * @date：2017年3月14日下午9:26:26
	 */
	public static Date getCurrentDate(){
		return new Date();
	}
	/**
	 * <p>description：获取当前日期字符串</p>
	 * @author：Administrator
	 * @date：2017年3月14日下午9:27:20
	 */
	public static String getCnString(){
		Date date = new Date();
		return date.toLocaleString();
	}
	/**
	 * <p>description：计算日期相差天数</p>
	 *
	 * @Author Tarriance
	 * @Date 2017/3/25,11:02
	 */
	public static int getDateMis(Date date1, Date date2) {

		if (null == date1 || null == date2) {

			return -1;

		}

		long intervalMilli = date2.getTime() - date1.getTime();

		return (int) (intervalMilli / (24 * 60 * 60 * 1000));

	}


	public static void main(String[] args) {
		Date date = new Date();
		String str = date.toGMTString();
		str = date.toLocaleString();
		System.out.println(str);
	}

	/**
	 * 字符串转换成日期
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return date;
	}

	/**
	 * 根据传入的日期转换成字符形式的日期
	 * 
	 * @param date
	 *            日期型
	 * @return 如：2005-12-25 08:25:36
	 */
	public static String getDateTime(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String NDate = formatter.format(date);
		return NDate;
	}

	/**
	 * 根据传入的日期转换成字符形式的日期
	 *
	 * @param date
	 *            日期型
	 * @return 如：2005-12-25 08:25:36
	 */
	public static String getDateTimeN(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String NDate = formatter.format(date);
		return NDate;
	}
}
