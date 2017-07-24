package com.gluxen.jgx.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 字符串日期公用工具类
* @author lishiqiang
* @date 2017-3-15
* modify history
 */
public class StrDateUtil {
	/**
	 * 得到当前时间字符串yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String getCurrentTimeToString() {
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		return sdf.format(cl.getTime());
	}
	/**
	 * 得到当前时间字符串yyyyMMddHHmmss
	 * @return String
	 */
	public static String getCurrentTimeToString2() {
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyyMMddHHmmss");
		return sdf.format(cl.getTime());
	}
	/**
	 * 得到当前年月日字符串（yyyy-MM-dd）

	 * @return String
	 */
	public static String getCurrentDateString() {
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd");
		return sdf.format(cl.getTime());
	}
	/**
	 * 得到当前年月日字符串(yyyyMMdd)
	 * @return
	 */
	public static String getCurentDateStr(){
		return StrDateUtil.getCurrentDateString().replaceAll("-","");
	}
	/**
	 * 得到当前时间字符串(HHmmss)
	 * @return
	 */
	public static String getCurentTimeStr(){
		return StrDateUtil.getCurentTime().replaceAll(":","");
	}
	
	/**
	 * 得到当前年字符串yyyy
	 * @return String
	 */
	public static String getCurentYearString() {
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy");
		return sdf.format(cl.getTime());
	}
	/**
	 * 得到当前月字符串mm
	 * @return String
	 */
	public static String getCurentMonthString() {
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("MM");
		return sdf.format(cl.getTime());
	}
	/**
	 * 得到时间long
	 * @return long
	 */
	public static long getTimeLong() {
		Calendar cl = Calendar.getInstance();
		return cl.getTimeInMillis();
	}
	
	private static final DateFormat DF_DATE_1 = new SimpleDateFormat("yyyy年MM月dd日");	//yyyy年MM月dd日

	private static final DateFormat DF_DATE_2 = new SimpleDateFormat("yyyy-MM-dd");		//yyyy-MM-dd
	private static final DateFormat DF_DATE_3 = new SimpleDateFormat("yyyy/MM/dd");		//yyyy/MM/dd
	private static final DateFormat DF_DATE_4 = new SimpleDateFormat("yyyy年M月d日");		//yyyy年M月d日

	private static final DateFormat DF_FULL_1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"); //yyyy年MM月dd日 HH:mm:ss
	private static final DateFormat DF_FULL_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //yyyy-MM-dd HH:mm:ss
	private static final DateFormat DF_TIME_1 = new SimpleDateFormat("HH:mm:ss");		//HH:mm:ss
	private static final DateFormat DF_WEEK_1 = new SimpleDateFormat("EEEE");			//星期几

	
	/*
	 * 方法 formatDateTime,提供几种全格式的日期格式化 @param date @param formattype <li>formattype =
	 * 1 -- YYYY-MM-DD hh:mm:ss</li> <li>formattype = 2 -- YYYY年MM月DD日

	 * hh:mm:ss</li> @return
	 */
	public static String formatDateTimeToString(Date date, int formattype) {
		switch (formattype) {
		case 2:
			return DF_FULL_1.format(date);
		default:
			return DF_FULL_2.format(date);
		}
	}
	/**
	 * 方法 formatDate， 提供几种日期格式化

	 * @param date
	 * @param formattype
	 *            <li>formattype = 1 -- YYYY年MM月DD日</li>
	 *            <li>formattype = 2 -- YYYY-MM-DD</li>
	 *            <li>formattype = 3 -- YYYY/MM/DD</li>
	 * @return
	 */
	public static String formatDateToString(Date date, int formattype) {
		switch (formattype) {
		case 2:
			return DF_DATE_2.format(date);
		case 3:
			return DF_DATE_3.format(date);
		default:
			return DF_DATE_1.format(date);
		}
	}
	
	/**
	 * 格式自定义, 将日期转化为字符
	 *<br>y  年

	 *<br>M  年中的月份

	 *<br>w  年中的周数

	 *<br>W  月份中的周数
	 *<br>D  年中的天数

	 *<br>d  月份中的天数
	 *<br>F  月份中的星期
	 *<br>E  星期中的天数
	 *<br>a  Am/pm 标记
	 *<br>H  一天中的小时数
	 *<br>k  一天中的小时数
	 *<br>K  am/pm 中的小时数(0-11)
	 *<br>h  am/pm 中的小时数(12-24)
	 *<br>m  小时中的分钟数

	 *<br>s  分钟中的秒数
	 *<br>S  毫秒数

	 *<br>z  时区
	 *<br>Z  时区
	 * @param date
	 * @param formatStr
	 * @return 转化后的日期
	 */
	public static String formatDateToString(Date date, String formatStr) throws RuntimeException{
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr) ;
		return sdf.format(date) ;
	}
	
	/** 方法 getDayOfWeek ，返回今天是星期几 */
	public static String getCutentDayOfWeek() {
		return DF_WEEK_1.format(new Date());
	}
	/** Get now [HH:MM:SS] */
	public static String getCurentTime() {
		return DF_TIME_1.format(new Date());
	}
	/** Get now [YYYY-MM-DD] */
	public static String getToday() {
		return DF_DATE_2.format(new Date());
	}
	/**
	 * 得到当天的格式化日期串

	 * @param style
	 *            <li>1:yyyy年MM月dd日</li>
	 *            <li>2:yyyy-MM-dd</li>
	 *            <li>3:yyyy/MM/dd</li>
	 *            <li>4:yyyy年M月d日</li>
	 * @return
	 */
	public String getToday(int style) {
		switch (style) {
		case 1:
			return DF_DATE_1.format(new Date());
		case 2:
			return DF_DATE_2.format(new Date());
		case 3:
			return DF_DATE_3.format(new Date());
		case 4:
			return DF_FULL_1.format(new Date());
		default:
			return DF_DATE_4.format(new Date());
		}
	}
	
	
	//================================================================================================
		
	/**
	 * 把字符转化成日期
	 * @param dateString
	 * @return Date
	 */
	public static Date getDateByString(String dateString) {
		Date day = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			day = df.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return day;
	}	
	/**
	 * 比较两个日期的前后关系. 参数必须为Date类型. 精确到日
	 * @author hl
	 * @param date1
	 * @param date2
	 * @return Date1小于Date2, 则返回1; Date2大于Date1, 则返回-1; 两日期相等, 则返回0
	 */
	public static int compareTwoDates(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new RuntimeException("有一个或多个日期为空, 无法比较.") ;
		}		
		//比较两个Date对象的年份

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy") ;
		int year1 = Integer.parseInt(sdf.format(date1)) ;
		int year2 = Integer.parseInt(sdf.format(date2)) ;
		if (year1 < year2) {
			return 1 ;
		} else if (year1 > year2) {
			return -1 ;
		}		
		//比较两个Date对象的月份

		sdf = new SimpleDateFormat("MM") ;
		int month1 = Integer.parseInt(sdf.format(date1)) ;
		int month2 = Integer.parseInt(sdf.format(date2)) ;
		if (month1 < month2) {
			return 1 ;
		} else if (month1 > month2) {
			return -1 ;
		}		
		//比较两个Date对象的日期

		sdf = new SimpleDateFormat("dd") ;
		int day1 = Integer.parseInt(sdf.format(date1)) ;
		int day2 = Integer.parseInt(sdf.format(date2)) ;
		if (day1 < day2) {
			return 1 ;
		} else {
			return -1 ;
		}
	}
	
	/**
	 * 得到指定年月最后一天串（yyyyMMdd）
	 * @param ymonth
	 * @return
	 */
	public static String getMonthend(String ymonth){
		if(ymonth==null) return "";
		if(ymonth.length()!=6) return "";
		int year=Integer.parseInt(ymonth.substring(0,4));	//年
		int month=Integer.parseInt(ymonth.substring(4,6));	//月
		String date="";
		if(month==2){
			if(year%4==0 && year%100!=0 || year%400==0){  //润年2月
				date="29";
			}else{		//平年润月
				date="28";
			}
		}else if(month==4 || month==6 || month==9 || month==11){  //小月
			date="30";
		}else{		//大月
			date="31";
		}
		
		return ymonth+date;
	}
	
	public static void main(String[] args) {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Long date =  new Date(sdf.format(new Date())).getTime()/1000;
        System.out.print(new Date("2012/12/18 14:30:33").getTime()+"/////////");
		System.err.println(date+"----------------------");

        System.out.println(sdf.format(new Date(date*1000))+"......8122......7978..");
	}	
	
	
	
}