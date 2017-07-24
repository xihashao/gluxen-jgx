package com.gluxen.jgx.common.utils;

/**
 * Created by Administrator on 2016/9/5.
 */


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.sql.Timestamp;
import java.util.*;

/**
 * JSON转换工具类
 *
 * @author penghuaiyi
 * @date 2014-04-04
 */
public class JsonUtil {

    /**
     * 对象转换成JSON字符串
     *
     * @param obj
     *            需要转换的对象
     * @return 对象的string字符
     */
    public static String toJson(Object obj) {
        JSONObject jSONObject = JSONObject.fromObject(obj);
        return jSONObject.toString();
    }

    /**
     * JSON字符串转换成对象
     *
     * @param jsonString
     *            需要转换的字符串
     * @param type
     *            需要转换的对象类型
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String jsonString, Class<T> type) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        return (T) JSONObject.toBean(jsonObject, type);
    }

    /**
     * 将JSONArray对象转换成list集合
     *
     * @param jsonArr
     * @return
     */
    public static List<Object> jsonToList(JSONArray jsonArr) {
        List<Object> list = new ArrayList<Object>();
        for (Object obj : jsonArr) {
            if (obj instanceof JSONArray) {
                list.add(jsonToList((JSONArray) obj));
            } else if (obj instanceof JSONObject) {
                list.add(jsonToMap((JSONObject) obj));
            } else {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 将json字符串转换成map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        return jsonToMap(obj);
    }

    /**
     * 将JSONObject转换成map对象
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> jsonToMap(JSONObject obj) {
        Set<?> set = obj.keySet();
        Map<String, Object> map = new HashMap<String, Object>(set.size());
        for (Object key : obj.keySet()) {
            Object value = obj.get(key);
            if (value instanceof JSONArray) {
                map.put(key.toString(), jsonToList((JSONArray) value));
            } else if (value instanceof JSONObject) {
                map.put(key.toString(), jsonToMap((JSONObject) value));
            } else {
                map.put(key.toString(), obj.get(key));
            }

        }
        return map;
    }
    
    /**
     * 转json
     *
     * @param state
     * @param msg
     * @param lists
     * @return
     */
    public static String turnToJson(Boolean state, String msg, Object o) {
        JSONObject obj = new JSONObject();
        obj.put("state", state);
        obj.put("msg", msg);
        if (o instanceof List) {
            o = toJsonArray((List) o);
        } else if (o instanceof Map) {
            o = toJsonObject((Map) o);
        } else if (o != null) {
            o = o.toString();
        } else {
            o = "";
        }
        obj.put("result", o);
        String result = obj.toString();
        return result;
    }
    
    public static JSONObject toJsonObject(Map map) {
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Timestamp.class, new TimestampProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONObject resJson = JSONObject.fromObject(map, config);
        return resJson;
    }

    public static JSONArray toJsonArray(List list) {
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Timestamp.class, new TimestampProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONArray resJson = JSONArray.fromObject(list, config);
        return resJson;
    }
}

