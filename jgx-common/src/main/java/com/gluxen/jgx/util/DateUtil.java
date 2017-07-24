package com.gluxen.jgx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 时间处理类
 *
 * @author lishiqiang
 * @date 2017-3-15
 * modify history
 */
public class DateUtil {

    /**
     * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
     */
    private DateUtil() {
    }

    /**
     * 格式化timestamp日期型
     *
     * @param timestamp 日期
     * @return 格式化后的日期，格式如：2005-12-04
     */
    public static String convertDate(long timestamp) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(timestamp));
    }

    public static Date convertDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (Exception er) {
            return null;
        }
    }

    /**
     * 格式化日期(当前日期)
     *
     * @return 格式如：2005-12-05 12:25:36
     */
    public static String getDateTime() {
        return getDateTime(new Date());
    }

    /**
     * 取得SQL类型的DATE util.date转成sql.date
     *
     * @param d
     * @return
     */
    public static java.sql.Date getSQLDate(Date d) {
        return new java.sql.Date(d.getTime());
    }

    /**
     * 把Object对像转换成Date类型 如果对像为空或格式不能解析，返回当前日期
     *
     * @param o
     * @return
     */
    public static Date getDate(Object o) {
        if (o == null) {
            return new Date();
        } else if (o instanceof Date) {
            return (Date) o;
        } else if (o instanceof String) {
            return getDate(String.valueOf(o));
        } else if (o instanceof java.sql.Timestamp) {
            return new Date(((java.sql.Timestamp) o).getTime());
        } else {
            return new Date();
        }
    }

    /**
     * 根据传入的日期转换成字符形式的日期
     *
     * @param date 日期型
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
     * @param date 日期型
     * @return 如：2005-12-25 08:25:36
     */
    public static String getCurrentDateTime() {
        return getDateTime(new Date());
    }

    /**
     * 日期格式化成日期时分，不取秒，
     *
     * @param date
     * @return 2005-12-25 12:25
     */
    public static String getDateHF(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String NDate = formatter.format(date);
        return NDate;
    }

    /**
     * 日期格式化成日期时分，不取秒，
     *
     * @param date
     * @return 2005-12-25 12:25
     */
    public static String getDateYYMMDD(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String NDate = formatter.format(date);
        return NDate;
    }

    /**
     * 日期格式化成日期时分，不取秒，
     *
     * @return 2005-12-25 12:25
     */
    public static String getDateYYMMDD() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String NDate = formatter.format(new Date());
        return NDate;
    }

    public static String getDateYYYYMMDD() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String NDate = formatter.format(new Date());
        return NDate;
    }

    /**
     * 日期格式化成时分，不取秒，
     *
     * @param date
     * @return 12:25
     */
    public static String getHF(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String NDate = formatter.format(date);
        return NDate;
    }

    /**
     * @param date
     * @return
     */
    public static String getTimeString(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String NDate = formatter.format(date);
        return NDate;
    }

    /**
     * 取得年月日
     *
     * @return 2008-12-25
     */
    public static String getDateString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String NDate = formatter.format(new Date());
        return NDate;
    }

    /**
     * 取得年月日
     *
     * @return 08-12-25
     */
    public static String getShortDateString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd");
        String NDate = formatter.format(new Date());
        return NDate;
    }

    /**
     * 取得年月日
     *
     * @param date
     * @return 20058-12-25
     */
    public static String getDateString(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String NDate = formatter.format(date);
        return NDate;
    }

    /**
     * 取得年月
     *
     * @param date
     * @return 20058-12-25
     */
    public static String getDateString1(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String NDate = formatter.format(date);
        return NDate;
    }

    /**
     * 取得中文格式的日期
     *
     * @return 2005后12月25日
     */
    public static String getCnDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String NDate = formatter.format(new Date());
        return NDate;
    }

    /**
     * 取得中文格式的日期
     *
     * @return 2005后12月25日
     */
    public static String getCnDateMD() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        String NDate = formatter.format(new Date());
        return NDate;
    }

    /**
     * 取得中文格式的日期
     *
     * @return 2005后12月25日
     */
    public static String getCnDateMD(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        String NDate = formatter.format(d);
        return NDate;
    }

    public static String getCnDateMDHF(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日HH:mm");
        String NDate = formatter.format(d);
        return NDate;
    }

    public static String getCnDateDHF(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd日HH:mm");
        String NDate = formatter.format(d);
        return NDate;
    }

    /**
     * 取得中文格式的日期
     *
     * @return 2005后12月25日
     */
    public static String getCnDateYM(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
        String NDate = formatter.format(d);
        return NDate;
    }

    /**
     * 取得中文格式的日期
     *
     * @param d
     * @return 2005后12月25日
     */
    public static String getCnDateTime(Date d) {
        if (d == null)
            d = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        String NDate = formatter.format(d);
        return NDate;
    }

    /**
     * 取得中文格式的日期
     *
     * @param d
     * @return 2005后12月25日
     */
    public static String getCnDate(Date d) {
        if (d == null)
            d = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String NDate = formatter.format(d);
        return NDate;
    }

    /**
     * 根据传入的格式取得日期字符串
     *
     * @param date
     * @param Str  格式化格式，如yyyy-MM-dd
     * @return
     */
    public static String getDateStrByProp(Date date, String Str) {
        if (date == null)
            return "";
        String NDate = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(Str);
            NDate = formatter.format(date);
        } catch (Exception e) {
            NDate = "";
        }
        return NDate;
    }

    /**
     * 取得当前日期的Long型 如1212452121222
     *
     * @return
     */
    public static long getTime() {

        return (new Date()).getTime();
    }

    /**
     * 根据日期取得年月日 不推荐使用
     *
     * @param DateString
     * @return
     */
    public static String getStrDate(String DateString) {
        if (DateString == null || DateString.length() < 10) {
            return DateString;
        }
        return DateString.substring(0, 10);
    }

    /**
     * 根据日期取得年月日 不推荐使用
     *
     * @return
     */
    public static String getStrDate() {
        return getDateTime().substring(0, 10);
    }

    /**
     * 比较日期大小
     *
     * @param last
     * @param now
     * @return
     */
    public static boolean compareTo(String last, String now) {
        try {
            // DateFormat formatter=DateFormat.getDateInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date temp1 = formatter.parse(last);
            Date temp2 = formatter.parse(now);
            if (temp1.after(temp2)) {
                return false;
            } else if (temp1.before(temp2)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 取得 addLong 毫秒以前（以后）的时间
     *
     * @param olddate
     * @param addLong
     * @return
     */
    public static Date getAddDate(Date olddate, long addLong) {
        long temp = olddate.getTime();
        temp += addLong;
        return new Date(temp);
    }

    /**
     * @param date1
     * @param date2
     * @return
     */
    public static int DateDiff(String date1, String date2) {
        long d1 = Math.abs(getDate(date2).getTime() - getDate(date1).getTime());
        int dNum = Math.round(d1 / 1000 / 60 / 60 / 24);
        return dNum;
    }

    public static int DateDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            return 0;
        long d1 = Math.abs(date2.getTime() - date1.getTime());
        int dNum = Math.round(d1 / 1000 / 60 / 60 / 24);
        return dNum;
    }

    /**
     * 把字符串转换成时间，格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr
     * @return
     */
    public static Date getDate(String dateStr) {
        Date temp1 = null;
        if (dateStr == null)
            return null;
        if (dateStr.equals(""))
            return null;
        SimpleDateFormat formatter = null;
        try {
            if (dateStr.indexOf(" ") != -1) {
                String[] aa = StringUtil.split(dateStr, ":");
                if (aa.length == 3)
                    formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                else if (aa.length == 2)
                    formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                else
                    formatter = new SimpleDateFormat("yyyy-MM-dd HH");
            } else {
                formatter = new SimpleDateFormat("yyyy-MM-dd");
            }
            temp1 = formatter.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp1;
    }

    /**
     * 把long型日期转换成天数
     *
     * @param date
     * @return
     */
    public static String getDateLength(long date) {
        String s = "";
        if (date > 1000 * 60 * 60 * 24) {
            s += date / (1000 * 60 * 60 * 24) + "天";
            date = date % (1000 * 60 * 60 * 24);
        }
        if (date > 1000 * 60 * 60) {
            s += date / (1000 * 60 * 60) + "时";
            date = date % (1000 * 60 * 60);
        }
        if (date > 1000 * 60) {
            s += date / (1000 * 60) + "分";
        }
        return s;
    }

    /**
     * 取得当前时间
     *
     * @return
     */
    public static Date getDate() {
        return new Date();
    }

    /**
     * 取得当前星期
     *
     * @return
     */
    public static String getWeekDay() {
        String[] weekDay = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar ca = Calendar.getInstance();
        return weekDay[ca.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 取得指定周开始日期 默认从该周周一
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getWeekBeginDate(int year, int week) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.WEEK_OF_YEAR, week);
        c.set(Calendar.DAY_OF_WEEK, 2);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 取得指定周开始日期 默认从该周周一
     *
     * @return
     */
    public static Date getWeekBeginDate() {
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, d.getYear() + 1900);
        c.set(Calendar.WEEK_OF_YEAR, DateUtil.getWeek(d));
        c.set(Calendar.DAY_OF_WEEK, 2);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 取得指定周开始日期 默认从该周周一
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getMonthBeginDate(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 取得指定周开始日期 默认从该周周一
     *
     * @return
     */
    public static Date getMonthBeginDate() {
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, d.getYear() + 1900);
        c.set(Calendar.MONTH, d.getMonth());
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 取得指定周开始日期 默认从该周周一
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getMonthEndDate(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, 0);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 取得指定周开始日期 默认从该周周一
     *
     * @param d
     * @return
     */
    public static int getWeek(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 判断指定日期是否为周末
     *
     * @param d
     * @return
     */
    public static boolean isWeekEnd(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            return true;
        }
        return false;
    }

    /**
     * 取得指定周结束日期 默认为该周周日
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getWeekEndDate(int year, int week) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.WEEK_OF_YEAR, week + 1);
        c.set(Calendar.DAY_OF_WEEK, 1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 取得指定周结束日期 默认为该周周日
     *
     * @return
     */
    public static Date getWeekEndDate() {
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, d.getYear() + 1900);
        c.set(Calendar.WEEK_OF_YEAR, DateUtil.getWeek(d) + 1);
        c.set(Calendar.DAY_OF_WEEK, 1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 取得星期大写
     *
     * @param i
     * @return
     */
    public static String getWeekStr(int i) {
        i = i % 7;
        String[] s = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        return s[i];
    }

    /**
     * 根据日期生成目录
     *
     * @param d
     * @return
     */
    public static String getDatePath(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        StringBuffer path = new StringBuffer();
        path.append("/");
        path.append(c.get(Calendar.YEAR));
        path.append("/");
        path.append(c.get(Calendar.MONTH) + 1);
        path.append("/");
        path.append(c.get(Calendar.DATE));
        return path.toString();
    }

    /**
     * 根据日期生成访问路经
     *
     * @param d
     * @return
     */
    public static String getDateViewPath(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        StringBuffer path = new StringBuffer();
        path.append("/");
        path.append(c.get(Calendar.YEAR));
        path.append("/");
        path.append(c.get(Calendar.MONTH) + 1);
        path.append("/");
        path.append(c.get(Calendar.DATE));
        return path.toString();
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static String getDateY() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String NDate = formatter.format(new Date());
        return NDate;
    }

    private static SimpleDateFormat yymmcn = new SimpleDateFormat("yyyy年MM月");

    public static String getDateYYMMCn(Date date) {
        return yymmcn.format(date);
    }

    /**
     * 获取当前月
     *
     * @return
     */
    public static String getDateM() {
        return getDateM(new Date());
    }

    public static String getDateM(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        String NDate = formatter.format(date);
        return NDate;
    }

    /**
     * 获取当前日
     *
     * @return
     */
    public static String getDateD() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        String NDate = formatter.format(new Date());
        return NDate;
    }

    /**
     * 年份下拉列表
     *
     * @param beforeNum 从当前年份向前推移（0 为当前年份）
     * @param afterNum  从当前年份向后推移
     * @param objName   select控件名称
     * @param objValue  select控件默认被选中值
     * @param styleStr  select控件样式
     * @param event     select控件触发事件
     * @return String
     */
    public static String getYearSelect(int beforeNum, int afterNum, String objName, String objValue, String styleStr, String event, String defaultValue) {
        StringBuffer htmlStr = new StringBuffer();
        try {
            String curYear = getDateY();
            if (curYear == null || "".equals(curYear)) {
                curYear = "2008";
            }
            int beginYear = Integer.parseInt(curYear) - beforeNum;
            int endYear = Integer.parseInt(curYear) + afterNum;
            if (beginYear > endYear) {
                htmlStr.append("开始年度应该小于结束年度");
            } else {
                htmlStr.append("<select name=\"" + objName + "\" ");
                if (event != null && !event.equals("")) {
                    htmlStr.append(event);
                }
                if (styleStr != null && !styleStr.equals("")) {
                    htmlStr.append(" style=\"" + styleStr + "\"");
                } else {
                    htmlStr.append(" style=width:100px;\"");
                }
                htmlStr.append(">");
                if (defaultValue != null && !defaultValue.equals("")) {
                    htmlStr.append("<option value=\"\">" + defaultValue + "</option>");
                }
                for (int i = beginYear; i <= endYear; i++) {
                    htmlStr.append("<option value=\"" + i + "\"");
                    if (objValue != null && !objValue.equals("") && objValue.equals(String.valueOf(i))) {
                        htmlStr.append(" selected");
                    }
                    htmlStr.append(">" + i + "年</option>");
                }
                htmlStr.append("</select>");
            }
        } catch (Exception e) {
            System.out.println("DateUtil getYearSelect is error!");
            e.printStackTrace();
        } finally {
            return htmlStr.toString();
        }

    }

    /**
     * 年份下拉列表
     *
     * @param objName  select控件名称
     * @param objValue select控件默认被选中值
     * @param styleStr select控件样式
     * @param event    select控件触发事件
     * @return String
     */
    public static String getMonthSelect(String objName, String objValue, String styleStr, String event, String defaultValue) {
        StringBuffer htmlStr = new StringBuffer();
        try {
            htmlStr.append("<select name=\"" + objName + "\" ");
            if (event != null && !event.equals("")) {
                htmlStr.append(event);
            }
            if (styleStr != null && !styleStr.equals("")) {
                htmlStr.append(" style=\"" + styleStr + "\"");
            } else {
                htmlStr.append(" style=width:100px;\"");
            }
            htmlStr.append(">");
            if (defaultValue != null && !defaultValue.equals("")) {
                htmlStr.append("<option value=\"\">" + defaultValue + "</option>");
            }
            for (int i = 1; i <= 12; i++) {
                if (i < 10) {
                    htmlStr.append("<option value=\"0" + i + "\"");
                    if (objValue != null && !objValue.equals("") && objValue.equals("0" + String.valueOf(i))) {
                        htmlStr.append(" selected");
                    }
                    htmlStr.append(">0" + i + "月</option>");
                } else {
                    htmlStr.append("<option value=\"" + i + "\"");
                    if (objValue != null && !objValue.equals("") && objValue.equals(String.valueOf(i))) {
                        htmlStr.append(" selected");
                    }
                    htmlStr.append(">" + i + "月</option>");
                }
            }
            htmlStr.append("</select>");
        } catch (Exception e) {
            System.out.println("DateUtil getMonthSelect is error!");
            e.printStackTrace();
        } finally {
            return htmlStr.toString();
        }
    }

    private static final SimpleDateFormat formatyymm = new SimpleDateFormat("yyyy-MM");

    // 将String转换成Date
    public static Date getDateYYMM(String str) {
        try {
            return formatyymm.parse(str);
        } catch (Exception er) {
            er.printStackTrace();
            return null;
        }
    }

    public static String getDateYYMM(Date date) {
        try {
            return formatyymm.format(date);
        } catch (Exception er) {
            er.printStackTrace();
            return null;
        }
    }

    /**
     * 获取所传日期上一月日期的字符串形式
     *
     * @return 返回入参上一个月的日期格式，如2011-01
     */
    public static String getPreviewMonth() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        StringBuffer returnValue = new StringBuffer();
        returnValue.append(String.valueOf(year) + "-");
        if (month < 10) {
            returnValue.append("0" + String.valueOf(month));
        } else {
            returnValue.append(String.valueOf(month));
        }
        return returnValue.toString();
    }

    public static List twoDays(String str1, String str2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List list = new ArrayList();
        try {
            Date b = df.parse(str1);
            Date e = df.parse(str2);
            Calendar c = Calendar.getInstance();
            c.setTime(b);
            while (e.compareTo(c.getTime()) >= 0) {
                String tempDateStr = "";
                tempDateStr = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()).toString();
                list.add(tempDateStr);
                c.add(c.DATE, 1);
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return list;
    }

    public static String[] getLastNMonths(int n) {
        String[] last12Months = new String[n];
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1); // 要先+1,才能把本月的算进去</span>
        for (int i = 0; i < n; i++) {
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); // 逐次往前推1个月
            last12Months[11 - i] = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1);
            if ((cal.get(Calendar.MONTH) + 1) < 10) {
                last12Months[11 - i] = cal.get(Calendar.YEAR) + "-0" + (cal.get(Calendar.MONTH) + 1);
            } else {
                last12Months[11 - i] = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1);
            }
        }
        return last12Months;
    }

    //获取季度
    public static String getThisSeasonTime(int month) {
        String quarter = "";
        if (month >= 1 && month <= 3) {
            quarter = "Spring";
        }
        if (month >= 4 && month <= 6) {
            quarter = "Summer";
        }
        if (month >= 7 && month <= 9) {
            quarter = "Autumn";
        }
        if (month >= 10 && month <= 12) {
            quarter = "Winter";
        }
        return quarter;
    }

    //获取几月到几月
    public static String getMonthToMonth(int month) {
        String monthToMonth = "";
        String[] months = {"(1月-3月)", "(4月-6月)", "(7月-9月)", "(10月-12月)"};
        int index = month / 4;
        monthToMonth = months[index];
        return monthToMonth;
    }

    //取得当前时间
    public static Date getDateTime(String dateTime) {
        Date strDate = java.sql.Date.valueOf(dateTime);
        return strDate;
    }

    public static int getMonth(String dateTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(getDateTime(dateTime));
        int month = c.get(c.MONTH) + 1;
        return month;
    }

    public static int getYear(String dateTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(getDateTime(dateTime));
        int year = c.get(c.YEAR);
        return year;
    }

    /**
     * 获得最近的季度
     *
     * @param count 需要获得季度的数量
     * @return
     */
    public static QuarterBean[] getRecentQuarters(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("count must be great than 0");
        }
        QuarterBean[] quarters = new QuarterBean[count];
        quarters[0] = getCurrentQuarter();
        for (int i = 1; i < quarters.length; i++) {
            quarters[i] = quarters[i - 1].getPastQuarter();
        }
        return quarters;
    }

    /**
     * 获得当前季度
     *
     * @return
     */
    public static QuarterBean getCurrentQuarter() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return new QuarterBean(year, getQuarter(month));
    }

    /**
     * 通过月份计算季度
     *
     * @param month
     * @return
     */
    public static int getQuarter(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("month is invalid.");
        }
        return (month - 1) / 3 + 1;
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
	/**
	 * <p>description:获取</p>
	 * @param 
	 * @return String
	 * @author Wen Yugang
	 * @date 2017-4-26下午9:36:44
	 */
    public static String getNextDateStr(){
    	Calendar calendar = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	calendar.add(Calendar.DAY_OF_YEAR, 1);
    	Date date = calendar.getTime();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String NDate = formatter.format(date);
    	return NDate;
    }

    /**
     * 通过出生日期,获得年龄
     * @Param birthday
     * @return age
     */
    public static int getAge(Date birthDay){

        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("出生日期不能在未来!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        return age;
    }


}
