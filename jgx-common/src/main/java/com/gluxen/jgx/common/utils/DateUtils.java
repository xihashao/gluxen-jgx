package com.gluxen.jgx.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by cby on 2016/8/30.
 */
public class DateUtils {

	private static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
	private static final String FLIGHT_TIME_FORMAT = "ddMMMyyHHmm";
	private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();
	private static final String YMD = "yyyy-MM-dd";
	private static final String DMYEn = "ddMMMyy";
	private static ThreadLocal<DateFormat> threadLocal2 = new ThreadLocal<DateFormat>();

	private static final String YM = "yyyy-MM";
	private static ThreadLocal<DateFormat> threadLocal3 = new ThreadLocal<DateFormat>();
	private static ThreadLocal<DateFormat> threadLocal4 = new ThreadLocal<DateFormat>();
	private static DateFormat getDateFormat(String dateFormat,
											ThreadLocal<DateFormat> threadLocalP,Locale locale) {
		DateFormat df = threadLocalP.get();
		if (df == null) {
			if(locale!=null){
				df = new SimpleDateFormat(dateFormat,locale);
			}else{
				df = new SimpleDateFormat(dateFormat);
			}

			threadLocalP.set(df);
		}
		return df;
	}
	public static String formatTime(Date date) throws ParseException {
		return getDateFormat(YMDHMS,threadLocal,null).format(date);
	}

	public static Date parseTime(String strDate) throws ParseException {
		return getDateFormat(YMDHMS,threadLocal,null).parse(strDate);
	}
	public static String formatDate(Date date) throws ParseException {
		return getDateFormat(YMD,threadLocal2,null).format(date);
	}

	public static Date parseDate(String strDate) throws ParseException {
		return getDateFormat(YMD,threadLocal2,null).parse(strDate);
	}
	public static Date parseddMMMyy(String strDate) throws ParseException {
		return getDateFormat(DMYEn,threadLocal4,Locale.ENGLISH).parse(strDate);
	}

	public static String formatYM(Date date) throws ParseException {
		return getDateFormat(YM,threadLocal3,null).format(date);
	}

	public static Date parseYM(String strDate) throws ParseException {
		return getDateFormat(YM,threadLocal3,null).parse(strDate);
	}


	public static Date getFlightTime(String strDate){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(
					"ddMMMyyHHmm", Locale.ENGLISH);
			return sdf.parse(strDate);
		} catch (ParseException e) {
			return null;
		}catch (Exception e2){
		    return null;
        }
	}
	
	public static String getYMDHMS() {
		return YMDHMS;
	}

	public static String getYMD() {
		return YMD;
	}

	public static String getYM() {
		return YM;
	}

	public static String getDMYEn() {
		return DMYEn;
	}

	/**
	 *
	 * 截断日期 ,例如截断到分
	 * originDate = 2016.09.03 14:52:11
	 * field = Calendar.MINUTE
	 * 返回  2016.09.03 14:00:00
	 *
	 * @param originDate 原始日期
	 * @param field @see Calendar field
	 * @return
	 * @author lijunfeng
	 * @date 2016年9月3日
	 * @version V1.0 modify history
	 */
	public static Date truncate(Date originDate, int field) {
		Calendar c = Calendar.getInstance();
		c.setTime(originDate);
		switch (field) {
			case Calendar.MONTH:
				c.set(Calendar.MONTH, 0);
			case Calendar.DAY_OF_MONTH:
				c.set(Calendar.DAY_OF_MONTH, 0);
			case Calendar.HOUR_OF_DAY:
				c.set(Calendar.HOUR_OF_DAY, 0);
			case Calendar.MINUTE:
				c.set(Calendar.MINUTE, 0);
			case Calendar.SECOND:
				c.set(Calendar.SECOND, 0);
			case Calendar.MILLISECOND:
				c.set(Calendar.MILLISECOND, 0);
			default:
				break;
		}

		return c.getTime();
	}
}
