/**
 * 
 */
package com.gluxen.jgx.common.util;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * <p>
 * description:
 * </p>
 * name CookieUtil
 * 
 * @author Wen Yugang
 * @date 2017-3-23下午5:39:25
 */
public class CookieUtil {
	/**
	 * <p>
	 * description:获取Cookie
	 * </p>
	 * 
	 * @param
	 * @return Cookie
	 * @author Wen Yugang
	 * @date 2017-3-23下午5:44:14
	 */
	public static String getCookie(HttpServletRequest request, String cookieName) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie != null && cookie.getName() != null) {
					if (cookie.getName().equals(cookieName)) {
						String val = cookie.getValue();
						return URLDecoder.decode(val);
					}
				}
			}
		}
		return null;
	}

	/**
	 * <p>
	 * description:获取用户ID
	 * </p>
	 * 
	 * @param
	 * @return String
	 * @author Wen Yugang
	 * @date 2017-4-5下午2:36:59
	 */
	public static String getUserId(HttpServletRequest request) {
		return getCookie(request, "user_id");
	}

	/**
	 * <p>
	 * description:获取用户名称
	 * </p>
	 * 
	 * @param
	 * @return String
	 * @author Wen Yugang
	 * @date 2017-4-5下午2:38:21
	 */
	public static String getUserName(HttpServletRequest request) {
		return getCookie(request, "user_name");
	}

	/**
	 * <p>
	 * description:获取站点ID
	 * </p>
	 * 
	 * @param
	 * @return String
	 * @author Wen Yugang
	 * @date 2017-4-24下午5:39:10
	 */
	public static String getSiteId(HttpServletRequest request) {
		return getCookie(request, "siteId");
	}

	/**
	 * <p>
	 * description:设置cookie
	 * </p>
	 * 
	 * @param
	 * @return String
	 * @author sty
	 * @date 2017-4-24下午5:39:10
	 */
	public static void setcookie(HttpServletResponse resp, String cookieName,
			String cookieValue) {
		Cookie c;
		try {
			c = new Cookie(cookieName, URLEncoder.encode(cookieValue, "UTF-8"));
			// 设置生命周期为1小时，秒为单位
			c.setMaxAge(7 * 24 * 60 * 60 * 1000);
			c.setPath("/");
			resp.addCookie(c);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * <p>
	 * description:获取客户端IP地址
	 * </p>
	 * 
	 * @param
	 * @return String
	 * @author Wen Yugang
	 * @date 2017-5-14上午11:51:07
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
public static void main(String[] args) {
	String str = "";
	try{
		//str = getAddress("ip=124.161.16.164","utf-8");
		//str = getAddress("ip=116.236.241.46","utf-8");
		str = getAddress("114.135.62.67");
	}catch (Exception e) {
		e.printStackTrace();
	}
	System.out.println(str);
}
	public static String getAddress(String ip)
			throws Exception {
		String path = "http://ip.taobao.com/service/getIpInfo.php";
		ip="ip="+ip;
		String returnStr = getRs(path, ip, "utf-8");
		JSONObject json = null;
		if (returnStr != null) {
			json = JSONObject.fromObject(returnStr);
			if ("0".equals(json.get("code").toString())) {
				StringBuffer buffer = new StringBuffer();
//				buffer.append(decodeUnicode(json.optJSONObject("data")
//						.getString("region")));// 省份
//				buffer.append(decodeUnicode(json.optJSONObject("data")
//						.getString("city")));// 市区
//				buffer.append(decodeUnicode(json.optJSONObject("data")
//						.getString("county")));// 地区
//				buffer.append(decodeUnicode(json.optJSONObject("data")
//						.getString("isp")));// ISP公司
				
				buffer.append(json.optJSONObject("data")
						.getString("region"));// 省份
				buffer.append(json.optJSONObject("data")
						.getString("city"));// 市区
				buffer.append(json.optJSONObject("data")
						.getString("county"));// 地区
//				buffer.append(json.optJSONObject("data")
//						.getString("isp"));// ISP公司
				return buffer.toString();
			} else {
				return "获取地址失败!";
			}
		}
		return null;
	}

	public static String getRs(String path, String params, String encoding) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(path);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(5000);// 设置连接超时时间，单位毫秒?
			connection.setReadTimeout(5000);// 设置读取数据超时时间，单位毫秒?
			connection.setDoInput(true);// 是否打开输出�? true|false
			connection.setDoOutput(true);// 是否打开输入流true|false
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.writeBytes(params);
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encoding));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();// 关闭连接
		}
		return null;
	}

	/**
	 * 字符转码
	 * 
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer buffer = new StringBuffer(len);
		for (int i = 0; i < len;) {
			aChar = theString.charAt(i++);
			if (aChar == '\\') {
				aChar = theString.charAt(i++);
				if (aChar == 'u') {
					int val = 0;
					for (int j = 0; j < 4; j++) {
						aChar = theString.charAt(i++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							val = (val << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							val = (val << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							val = (val << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed      encoding.");
						}
					}
					buffer.append((char) val);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					}
					if (aChar == 'r') {
						aChar = '\r';
					}
					if (aChar == 'n') {
						aChar = '\n';
					}
					if (aChar == 'f') {
						aChar = '\f';
					}
					buffer.append(aChar);
				}
			} else {
			}
		}
		return buffer.toString();
	}
}
