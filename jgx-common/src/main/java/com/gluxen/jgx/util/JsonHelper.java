package com.gluxen.jgx.util;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 1:将JavaBean转换成Map、JSONObject
 * 2:将Map转换成Javabean
 * 3:将JSONObject转换成Map、Javabean
 * 
 * @author lishiqiang
 */

public class JsonHelper {
    
    /**
     * 将Javabean转换为Map
     * 
     * @param javaBean
     *            javaBean
     * @return Map对象
     */
    public static Map toMap(Object javaBean) {

        Map result = new HashMap();
        Method[] methods = javaBean.getClass().getDeclaredMethods();

        for (Method method : methods) {

            try {

                if (method.getName().startsWith("get")) {

                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);

                    Object value = method.invoke(javaBean, (Object[]) null);
                    result.put(field, null == value ? "" : value.toString());

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return result;

    }

    /**
     * 将Json对象转换成Map
     * 
     * @param jsonObject
     *            json对象
     * @return Map对象
     * @throws JSONException
     */
    public static Map toMap(String jsonString) throws JSONException {

        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        
        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;
        
        while (iterator.hasNext()) {

            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);

        }
        return result;

    }

    /**
     * 将JavaBean转换成JSONObject（通过Map中转）
     * 
     * @param bean
     *            javaBean
     * @return json对象
     */
    public static JSONObject toJSON(Object bean) {

        return JSONObject.fromObject(toMap(bean));

    }

    /**
     * 将Map转换成Javabean
     * 
     * @param javabean
     *            javaBean
     * @param data
     *            Map数据
     */
    public static Object toJavaBean(Object javabean, Map data) {

        Method[] methods = javabean.getClass().getDeclaredMethods();
        for (Method method : methods) {

            try {
                if (method.getName().startsWith("set")) {

                    String field = method.getName();
                    field = field.substring(field.indexOf("set") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    method.invoke(javabean, new Object[] {

                    data.get(field)

                    });

                }
            } catch (Exception e) {
            }

        }

        return javabean;

    }
    
	public static List<Map> jsonToList(String json){
		List<Map> list = new ArrayList<Map>();
		if(json==null){
			return list;
		}
		Pattern p = Pattern.compile("[\\w[^\\[\\],{}]]+");
		Matcher m = p.matcher(json.replaceAll("'|\"", ""));
		String[] _strs = null;
		int i = 1;
		Map map = new HashMap();
		while (m.find()) {
			
			_strs = m.group().split(":");
			if (_strs.length == 2) {
				map.put(_strs[0], _strs[1].trim());
			}
			i++;
			if(i%4==0){
				list.add(map);
			}
		}
		return list;
	}

    /**
     * JSONObject到JavaBean
     * 
     * @param bean
     *            javaBean
     * @return json对象
     * @throws ParseException
     *             json解析异常
     * @throws JSONException
     */
    public static void toJavaBean(Object javabean, String jsonString)
            throws ParseException, JSONException {

        JSONObject jsonObject =  JSONObject.fromObject(jsonString);
    
        Map map = toMap(jsonObject.toString());
        
        toJavaBean(javabean, map);

    }
    
    public static void main(String[] args) {
		System.err.println(jsonToList(null));
	}

}
