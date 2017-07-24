package com.gluxen.jgx.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;


/**
 * request参数获取处理
* @author lishiqiang
* @date 2017-3-15
* modify history
 */
public class ParamUtil {

    /**
     * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
     */
    private ParamUtil() {
    }

    /**
     * 取得并返回request参数值
     *
     * @param request
     * @param paramName
     * @return
     */
    public static String getString(HttpServletRequest request, String paramName) {
        String temp = request.getParameter(paramName);
        String temp1 = (String) request.getAttribute(paramName);
        if (temp != null && !"".equals(temp)) {
            return formatHttpString(temp);
        } else if (temp1 != null && !"".equals(temp1)) {
            return temp1;
        } else {
            return null;
        }
    }

    /**
     * 取得并返回request参数值,如果返回空值则用defaultString代替
     *
     * @param request
     * @param paramName
     * @return
     */
    public static String getString(HttpServletRequest request, String paramName,
                                   String defaultString) {
        String temp = getString(request, paramName);
        return temp == null ? defaultString : temp;
    }

    /**
     * 取得并返回request参数值,已处理了中文
     *
     * @param request
     * @param paramName
     * @return
     */
    public static String getFormatString(HttpServletRequest request,
                                         String paramName) {
        String temp = StringUtil.toChinese(getString(request, paramName));
        return temp == null ? "" : temp;
    }

    /**
     * 取得并返回request参数值,已处理了中文，如果返回空值则用defaultString代替
     *
     * @param request
     * @param paramName
     * @return
     */
    public static String getFormatString(HttpServletRequest request,
                                         String paramName, String defaultString) {
        String temp = StringUtil.toChinese(getString(request, paramName));
        return temp == null ? defaultString : temp;
    }

    /**
     * 取得并返回request参数值,已转换为整型数
     *
     * @param request
     * @param paramName
     * @return
     */
    public static int getInt(HttpServletRequest request, String paramName) throws
            NumberFormatException {
        return Integer.parseInt(getString(request, paramName));
    }

    /**
     * 取得并返回request参数值,已转换为整型数，如果返回空值则用defaultInt代替
     *
     * @param request
     * @param paramName
     * @return
     */
    public static int getInt(HttpServletRequest request, String paramName,
                             int defaultInt) {
        try {
            String temp = getString(request, paramName);
            return temp == null ? defaultInt : Integer.parseInt(temp.trim());
        } catch (Exception e) {
            return defaultInt;
        }
    }

    /**
     * 取得并返回request参数值,已转换为浮点数
     *
     * @param request
     * @param paramName
     * @return
     */
    public static float getFloat(HttpServletRequest request, String paramName) throws NumberFormatException {
        return Float.parseFloat(getString(request, paramName));
    }

    /**
     * 取得并返回request参数值,已转换为浮点数，如果返回空值则用defaultInt代替
     *
     * @param request
     * @param paramName
     * @return
     */
    public static float getFloat(HttpServletRequest request, String paramName, int defaultInt) {
        try {
            String temp = getString(request, paramName);
            return temp == null ? defaultInt : Float.parseFloat(temp);
        } catch (NumberFormatException e) {
            //  e.printStackTrace();
            return defaultInt;
        }
    }

    /**
     * 取得并返回request参数值,已转换为DOUBLE
     *
     * @param request
     * @param paramName
     * @return
     */
    public static double getDouble(HttpServletRequest request, String paramName) throws NumberFormatException {
        return Double.parseDouble(getString(request, paramName));
    }

    /**
     * 取得并返回request参数值,已转换为浮点数，如果返回空值则用defaultInt代替
     *
     * @param request
     * @param paramName
     * @return
     */
    public static double getDouble(HttpServletRequest request, String paramName, double defaultDouble) {
        try {
            String temp = getString(request, paramName);
            return temp == null ? defaultDouble : Double.parseDouble(temp);
        } catch (NumberFormatException e) {
            //.printStackTrace();
            return defaultDouble;
        }
    }

    /**
     * 取得Boolean
     *
     * @param request
     * @param paramName
     * @return
     */
    public static boolean getBoolean(HttpServletRequest request, String paramName) {
        return "true".equals(ParamUtil.getString(request, paramName, "false").toLowerCase()) ? true : false;
    }

    /**
     * 取得并返回request参数值,已转换为DOUBLE
     *
     * @param request
     * @param paramName
     * @return
     */
    public static Date getDate(HttpServletRequest request, String paramName) {
        String temp = getString(request, paramName);
        if (temp == null || temp.trim().length() == 0)
            return null;
        else
            return DateUtil.getDate(temp);
    }

    /**
     * 取得并返回request参数值,已转换为浮点数，如果返回空值则用defaultInt代替
     *
     * @param request
     * @param paramName
     * @return
     */
    public static Date getDate(HttpServletRequest request, String paramName, Date date) {
        try {
            String temp = getString(request, paramName);
            return temp == null ? date : DateUtil.getDate(temp);
        } catch (NumberFormatException e) {
            return date;
        }
    }

    /**
     * 取得并返回rs指定位置的字段值
     *
     * @param rs
     * @param i
     * @return
     */
    public static String getString(ResultSet rs, int i) {
        return getString(rs, i, null);
    }

    /**
     * 取得并返回rs指定列名的字段值
     *
     * @param rs
     * @param columnName
     * @return
     */
    public static String getString(ResultSet rs, String columnName) {
        return getString(rs, columnName, null);
    }

    /**
     * 取得并返回rs指定列名的字段值
     *
     * @param rs
     * @param columnName
     * @return
     */
    public static String getString(ResultSet rs, String columnName, String defValue) {
        try {
            String s = rs.getString(columnName);
            return s == null ? defValue : s;
        } catch (Exception e) {
            return defValue;
        }
    }

    /**
     * 取得并返回rs指定列的字段值
     *
     * @param rs
     * @param i
     * @return
     */
    public static String getString(ResultSet rs, int i, String defValue) {
        try {
            String s = rs.getString(i);
            return s == null ? defValue : s;
        } catch (Exception e) {
            return defValue;
        }
    }

    /**
     * 取得并返回rs指定位置的字段值，返回整数
     *
     * @param rs
     * @param i
     * @return
     */
    public static int getInt(ResultSet rs, int i) {
        try {
            return rs.getInt(i);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 取得并返回rs指定位置的字段值，返回整数
     *
     * @param rs
     * @param columnName
     * @return
     */
    public static int getInt(ResultSet rs, String columnName) {
        try {
            return rs.getInt(columnName);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 取得并返回rs参数值,返回浮点数
     *
     * @param rs
     * @param i
     * @return
     */
    public static float getFloat(ResultSet rs, int i) {
        try {
            return rs.getFloat(i);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 取得并返回rs指定位置的字段值，返回浮点数
     *
     * @param rs
     * @param columnName
     * @return
     */
    public static float getFloat(ResultSet rs, String columnName) {
        try {
            return rs.getFloat(columnName);
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * 取得并返回rs指定位置的字段值，将日期转换为字符串
     *
     * @param rs
     * @param i
     * @return
     */
    public static String getDateString(ResultSet rs, int i) {
        return DateUtil.getDateString(getDate(rs, i));
    }

    /**
     * 取得并返回rs指定位置的字段值，将日期转换为字符串
     *
     * @param rs
     * @param i
     * @return
     */
    public static String getDateString(ResultSet rs, String i) {
        try {
            Date d = new Date(rs.getTimestamp(i).getTime());
            return DateUtil.getDateTime(d);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 转换成java.util.date
     *
     * @param rs
     * @param i
     * @return
     */
    public static Date getDate(ResultSet rs, int i) {
        try {
            return new Date(rs.getTimestamp(i).getTime());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 取得并返回rs指定位置的字段值，将时间转换为字符串
     *
     * @param rs
     * @param i
     * @return
     */
    public static String getTimeString(ResultSet rs, int i) {
        try {
            Date d = new Date(rs.getTimestamp(i).getTime());
            return DateUtil.getDateTime(d);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 转换成java.util.date
     *
     * @param rs
     * @param i
     * @return
     */
    public static Date getTime(ResultSet rs, int i) {
        try {
            return new Date(rs.getTimestamp(i).getTime());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 取得并返回rs指定位置的字段值，将日期时间转换为字符串
     *
     * @param rs
     * @param i
     * @return
     */
    public static String getDateTime(ResultSet rs, int i) {
        try {
            Date d = new Date(rs.getTimestamp(i).getTime());
            return DateUtil.getDateTime(d);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 取得格式化后的值，形式如:2002-9-9 12:55:21
     *
     * @param rs
     * @param columnName
     * @return
     */
    public static String getDateTime(ResultSet rs, String columnName) {
        try {
            Date d = new Date(rs.getTimestamp(columnName).getTime());
            return DateUtil.getDateTime(d);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 格式化Session,根据Session名称返回格式化后的值
     *
     * @param session
     * @param sessionName
     * @return
     */
    public static String getString(HttpSession session, String sessionName) {
        String sessionTemp = (String) session.getAttribute(sessionName);
        if (sessionTemp == null) sessionTemp = "";
        return sessionTemp;
    }

    /**
     * 从map中取得String
     *
     * @param m
     * @param name
     * @param defaultValue
     * @return
     */
    public static String getString(Map m, String name, String defaultValue) {
        String temp = String.valueOf(m.get(name));
        if (temp == null) temp = defaultValue;
        return temp;
    }

    /**
     * 从map中取得String
     *
     * @param m
     * @param name
     * @return
     */
    public static String getString(Map m, String name) {
        return getString(m, name, null);
    }

    /**
     * 从Map中取得int
     *
     * @param m
     * @param name
     * @param defaultInt
     * @return
     */
    public static int getInt(Map m, String name, int defaultInt) {
        try {
            String temp = getString(m, name);
            return temp == null ? defaultInt : Integer.parseInt(temp.trim());
        } catch (Exception e) {
            return defaultInt;
        }
    }

    /**
     * 取得日期
     *
     * @param m
     * @param name
     * @param defaultDate
     * @return
     */
    public static Date getDate(Map m, String name, Date defaultDate) {
        return null;
    }

    /**
     * 从Map中取得int
     *
     * @param m
     * @param name
     * @return
     */
    public static int getInt(Map m, String name) {
        return getInt(m, name, 0);
    }

    /**
     * 从request中取得对像
     *
     * @param c
     * @param request
     * @return
     */
    public static Object getObject(Class c, HttpServletRequest request) {
        return getObject(c, request, true);
    }

    /**
     * 从request中填充内容至对像
     * 要求：
     * 1.网页上的对像名必须和对像中字段名一致才能填充
     * 2.对像名要么全大写，要么全小写。
     * 3 出错返回空对像
     * 4 名字不一致将不能填充
     *
     * @param c
     * @param request
     * @return
     */
    public static Object getObject(Class c, HttpServletRequest request, boolean uppercase) {
        Object instance = null;
        try {
            final Method[] m = c.getDeclaredMethods();
            if (m.length <= 0) return null;
            instance = c.newInstance();
            for (int i = 0; i < m.length; i++) {
                if (m[i].getName().indexOf("set") != -1) {
                    String name = m[i].getName();
                    String paramname = name.substring(3, name.length()).toLowerCase();
                    if (uppercase) paramname = paramname.toUpperCase();
                    Class[] param = m[i].getParameterTypes();
                    Object[] paramValue = new Object[param.length];
                    if (param.length > 0) {
                        for (int tt = 0; tt < param.length; tt++) {
                            String s = param[tt].getName();
                            if (s.equals("java.lang.String")) {
                                paramValue[tt] = getString(request, paramname, "");
                            } else if (s.equals("int")) {
                                paramValue[tt] = new Integer(getInt(request, paramname, 0));
                            } else if (s.equals("long")) {
                                paramValue[tt] = new Long(getInt(request, paramname, 0));
                            } else if (s.equals("double")) {
                                paramValue[tt] = new Double(getDouble(request, paramname, 0));
                            } else if (s.equals("float")) {
                                paramValue[tt] = new Float(getFloat(request, paramname, 0));
                            } else if (s.equals("java.util.Date")) {
                                paramValue[tt] = ParamUtil.getDate(request, paramname, null);
                            }
                        }
                        m[i].invoke(instance, paramValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }


    /**
     * 格式化输入内容，
     * 防止sql注入
     *
     * @param str
     * @return
     */
    private static String formatHttpString(String str) {
        if (str == null) return "";
        // str = str.replaceAll(" ", "");   //去空格
        //str = str.replaceAll(" and ", "");//去掉and
        //str = str.replaceAll(" or ", ""); //去掉or
        //str = str.replaceAll("%20", ""); //去空格
        // str = str.replaceAll("", "");
        // str = str.replaceAll("", "");
        return str;
    }

    /**
     * @param request
     * @param param
     * @return
     */
    public static String getFilterString(HttpServletRequest request, String param) {
        return getFilterString(request, param, null);
    }

    /**
     * @param request
     * @param param
     * @return
     */
    public static String getFilterString(HttpServletRequest request, String param, String defvalue) {
        String str = getString(request, param, defvalue);
        if (str == null) return null;
        str = str.replaceAll(" and ", "");//去掉and
        str = str.replaceAll(" or ", ""); //去掉or
        str = str.replaceAll("%20", ""); //去空格
        return str;
    }
}
