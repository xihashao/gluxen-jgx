package com.gluxen.jgx.util;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;


/**
 * 类型对照
* @author lishiqiang
* @date 2017-3-15
* modify history
 */
public class Type {
    /**
     * �ַ�
     */
    public static final Type STRING = new Type("java.lang.String");
    /**
     * ����
     */
    public static final Type INTEGER = new Type("int");
    /**
     * long
     */
    public static final Type LONG = new Type("long");
    /**
     * double
     */
    public static final Type DOUBLE = new Type("double");
    /**
     * float
     */
    public static final Type FLOAT = new Type("float");
    /**
     * ����
     */
    public static final Type DATE = new Type("java.util.Date");
    /**
     * ʱ��
     */
    public static final Type TIME = new Type("java.sql.time");
    /**
     * ʱ��
     */
    public static final Type TIMESTAMP = new Type("Timestamp");

    /**
     * short
     */
    public static final Type SHORT = new Type("short");

    /**
     * byte
     */
    public static final Type BYTE = new Type("byte");
    /**
     * character
     */
    public static final Type CHARACTER = new Type("character");
    /**
     * ����
     */
    public static final Type CALENDAR = new Type("calendar");

    /**
     * ����
     */
    private String typeName;

    public Type(String typeName) {
        this.typeName = typeName;
    }

    public String toString() {
        return this.typeName;
    }

    /**
     * �������
     *
     * @param index
     * @param t
     * @param value
     * @throws SQLException
     */
    public static void setType(java.sql.PreparedStatement ps, int index, Type t, Object value) throws SQLException {
        if (Type.STRING.equals(t)) {
            ps.setString(index, String.valueOf(value));
        } else if (Type.INTEGER.equals(t)) {
            int i;
            if (value instanceof Integer) {
                i = ((Integer) value).intValue();
            } else {
                i = Integer.parseInt(String.valueOf(value));
            }
            ps.setInt(index, i);
        } else if (Type.LONG.equals(t)) {
            long lo;
            if (value instanceof Long) {
                lo = ((Long) value).longValue();
            } else {
                lo = Long.parseLong(String.valueOf(value));
            }
            ps.setLong(index, lo);
        } else if (Type.FLOAT.equals(t)) {
            float fl;
            if (value instanceof Float) {
                fl = ((Float) value).floatValue();
            } else {
                fl = Float.parseFloat(String.valueOf(value));
            }
            ps.setFloat(index, fl);
        } else if (Type.DOUBLE.equals(t)) {
            double db;
            if (value instanceof Double) {
                db = ((Double) value).doubleValue();
            } else {
                db = Double.parseDouble(String.valueOf(value));
            }
            ps.setDouble(index, db);
        } else if (Type.DATE.equals(t)) {
            Date sqlDate;
            if (value instanceof Date) {
                sqlDate = (Date) value;
            } else {
                sqlDate = new Date(((java.util.Date) value).getTime());
            }
            ps.setDate(index, sqlDate);
        } else if (Type.TIMESTAMP.equals(t)) {
            Timestamp ts;
            if (value instanceof Timestamp) {
                ts = (Timestamp) value;
            } else {
                ts = new Timestamp(((java.util.Date) value).getTime());
            }
            ps.setTimestamp(index, ts);
        } else if (Type.TIME.equals(t)) {
            Time ts;
            if (value instanceof Time) {
                ts = (Time) value;
            } else {
                ts = new Time(((java.util.Date) value).getTime());
            }
            ps.setTime(index, ts);
        } else if (Type.SHORT.equals(t)) {
            short sh;
            if (value instanceof Short) {
                sh = ((Short) value).shortValue();
            } else {
                sh = Short.parseShort(String.valueOf(value));
            }
            ps.setShort(index, sh);
        } else if (Type.BYTE.equals(t)) {
            ps.setByte(index, ((Byte) value).byteValue());
        } else if (Type.CHARACTER.equals(t)) {
            Timestamp ts = new Timestamp(((Calendar) value).getTime().getTime());
            ps.setTimestamp(index, ts);
        } else if (Type.CHARACTER.equals(t)) {
            Timestamp ts = new Timestamp(((Calendar) value).getTime().getTime());
            ps.setTimestamp(index, ts);
        } else if (Type.CALENDAR.equals(t)) {
            ps.setString(index, value.toString());
        } else {
            ps.setObject(index, value);
        }
    }

    /**
     * @param s
     * @return
     */
    public static Type getType(String s) {
        if (s.equals("java.lang.String")) {
            return Type.STRING;
        } else if (s.equals("int")) {
            return Type.INTEGER;
        } else if (s.equals("long")) {
            return Type.LONG;
        } else if (s.equals("double")) {
            return Type.DOUBLE;
        } else if (s.equals("float")) {
            return Type.FLOAT;
        } else if (s.equals("java.util.Date")) {
            return Type.TIMESTAMP;
        } else {
            return Type.STRING;
        }
    }
}
