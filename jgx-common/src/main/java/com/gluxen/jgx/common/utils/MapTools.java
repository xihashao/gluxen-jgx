package com.gluxen.jgx.common.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class MapTools {
	/**
	 * jsonArray转List<map>
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(jsonToMap(json2.toString()));
		}
		return list;
	}
	/**
	 * jsonObject转map
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = JSONArray.fromObject(v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(jsonToMap(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}
	/**
	 * http获取jsonArray 转  list<map>
	 * @param url
	 * @return
	 */
	public static List<Map<String, Object>> getListByUrl(String url) {
		try {
			// 通过HTTP获取JSON数据
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return parseJSON2List(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * http获取jsonObject 转  map
	 * @param url
	 * @return
	 */
	public static Map<String, Object> getMapByUrl(String url) {
		try {
			// 通过HTTP获取JSON数据
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return jsonToMap(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// test
	public static void main(String[] args) {
		String url = "{'code':200,'message':'查询成功','data':[{'airl':'','code':'','countryCode':'','createBy':'','createTime':null,'defaultOperating':'','iataCode':'','icaoCode':'','id':0,'isInternational':'','name':'','nameFull':'','operatorFull':'','operatorGroupName':'','operatorNamecn':'','pulishState':0,'pulishTime':null,'recs':0,'styp':'','subcompanyRecord':'','type':'','updateBy':'','updateTime':null},{'airl':'','code':'','countryCode':'','createBy':'','createTime':null,'defaultOperating':'','iataCode':'','icaoCode':'','id':0,'isInternational':'','name':'','nameFull':'','operatorFull':'','operatorGroupName':'','operatorNamecn':'','pulishState':0,'pulishTime':null,'recs':0,'styp':'','subcompanyRecord':'','type':'','updateBy':'','updateTime':null}]}";
		Map<String, Object> list = jsonToMap(url);
		System.out.println(list.get("data"));
	}
}