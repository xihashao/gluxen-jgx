package com.gluxen.jgx.util;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * SQL对像和语句转换
* @author lishiqiang
* @date 2017-3-15
* modify history
 */
public class SqlUtils {
	 /**
     * @param conn
     * @param o
     * @param tableName
     * @return
     */
    public static boolean saveObject(Connection conn, Object o, String tableName) throws SQLException, Exception {
        StringBuffer buf = new StringBuffer();
        try {
            List paraList = getParaList(o);
            buf.append("insert into ").append(tableName);
            buf.append(" (");
            Iterator iter = paraList.iterator();
            while (iter.hasNext()) {
                buf.append(((Object[]) iter.next())[0]);
                if (iter.hasNext()) buf.append(",");
            }
            buf.append(") values (");
            iter = paraList.iterator();
            while (iter.hasNext()) {
                buf.append("?");
                iter.next();
                if (iter.hasNext()) buf.append(",");
            }
            buf.append(")");
            System.out.println(buf.toString());
            java.sql.PreparedStatement pstmt = conn.prepareStatement(buf.toString());
            for (int k = 0; k < paraList.size(); k++) {
                Object[] obj = (Object[]) paraList.get(k);
                System.out.println((Type) obj[1]);
                Type.setType(pstmt, k + 1, (Type) obj[1], obj[2]);
            }    /**  **/
            pstmt.execute();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param conn
     * @param o
     * @param tableName
     * @return
     */
    public static boolean updateObject(Connection conn, Object o, String tableName, String key) throws SQLException, Exception {
        StringBuffer buf = new StringBuffer();
        try {
            List paraList = getParaList(o);
            buf.append("update ").append(tableName).append(" set ");
            Iterator iter = paraList.iterator();
            while (iter.hasNext()) {
                buf.append(((Object[]) iter.next())[0] + "=?");
                if (iter.hasNext()) buf.append(", ");
            }
            buf.append(" where " + key);
            java.sql.PreparedStatement pstmt = conn.prepareStatement(buf.toString());
            for (int k = 0; k < paraList.size(); k++) {
                Object[] obj = (Object[]) paraList.get(k);
                Type.setType(pstmt, k + 1, (Type) obj[1], obj[2]);
            }
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 根据对像的get方法判断，返回一个用户参数对像List
     * obj[0] 字段名
     * obj[1] 字段返回类型
     * obj[2] 字段返回值
     *
     * @param o
     * @return
     * @throws Exception
     */
    private static List getParaList(Object o) throws Exception {
        Class c = o.getClass();
        List paraList = new ArrayList();
        final Method[] m = c.getDeclaredMethods();
        if (m.length <= 0) return paraList;
        for (int i = 0; i < m.length; i++) {
            if (m[i].getName().indexOf("get") != -1) {
                String name = m[i].getName();
                String paramname = name.substring(3, name.length()).toLowerCase();
                Class[] param = m[i].getParameterTypes();
                if (param.length == 0) {
                    Object[] obj = new Object[]{paramname, Type.getType(m[i].getReturnType().getName()), m[i].invoke(o, null)};
                    paraList.add(obj);
                }
            }
        }
        return paraList;
    }
	/**
	 * 将数组转换为in ()
	 * 
	 * @param arr
	 * @return
	 */
	public static String getInString(String[] arr) {
		if (arr == null) {
			return "''";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append("'").append(arr[i]).append("',");
		}
		sb.append("'none'");
		return sb.toString();
	}

	/**
	 * 将字符串转换为in ()
	 * 
	 * @param arr
	 * @return
	 */
	public static String getInString(String str) {
		if (str == null) {
			return "''";
		}
		String arr[] = str.split(",");
		return getInString(arr);
	}
}
