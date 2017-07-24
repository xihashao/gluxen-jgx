package com.gluxen.jgx.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

/**
 * 此类中封装一些常用的List操作方法.
* @author lishiqiang
* @date 2017-3-15
* modify history
 */
public class ListUtil {
    /**
     * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
     */
    private ListUtil() {
    }

    /**
     * 将数组转换为一个List，实际上是一个ArrayList。
     *
     * @param array 原数组
     * @return 原数组不为null的时候返回包含数组内容的ArrayList，否则返回null
     */
    public static List arrayToList(Object[] array) {
        if (array != null) {
            ArrayList list = new ArrayList(array.length);
            for (int i = 0; i < array.length; i++) {
                list.add(array[i]);
            }
            return list;
        } else {
            return null;
        }
    }

    /**
     * 将数组中的内容全部添加到列表中。
     *
     * @param array 数组
     * @param list  列表
     */
    public static void addArrayToList(Object[] array, List list) {
        if (array == null || list == null || array.length == 0) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
    }

    /**
     * 将数组中的内容全部添加到列表中。
     *
     * @param array 数组
     * @param list  列表
     * @param start 开始位置
     */
    public static void addArrayToList(Object[] array, List list, int start) {
        if (array == null || list == null || array.length == 0) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            list.add(start + i, array[i]);
        }
    }

    /**
     * 移动列表中的元素
     *
     * @param list  列表
     * @param start 移动的元素的开始索引
     * @param end   移动的元素的最后索引，不包括这个
     * @param to    移动到的位置
     */
    public static void moveElements(List list, int start, int end, int to) {
        List subList = new ArrayList(list.subList(start, end));
        list.removeAll(subList);
        list.addAll(to, subList);
    }

    /**
     * 给list中的所有String元素增加引号（""）.
     *
     * @param list 需要增加引号string的List.
     */
    public static List quoteStrList(List list) {
        List tmpList = list;
        list = new ArrayList();
        Iterator i = tmpList.iterator();
        while (i.hasNext()) {
            String str = (String) i.next();
            str = "\"" + str + "\"";
            list.add(str);
        }
        return list;
    }

    /**
     * 把rs转换成list
     *
     * @param rs
     * @return
     * @throws java.sql.SQLException
     */
    public static List resultSetToList(ResultSet rs) throws java.sql.SQLException {
        if (rs == null) {
            return Collections.EMPTY_LIST;
        }
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        List list = new ArrayList();
        Map rowData;
        while (rs.next()) {
            rowData = new HashMap(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                Object value = rs.getObject(i);
               // if (value != null && value instanceof String)
                //    value = StringUtil.toChinese(String.valueOf(value));
                rowData.put(md.getColumnName(i).toUpperCase(), value);
            }
            list.add(rowData);
        }
        return list;
    }
}
