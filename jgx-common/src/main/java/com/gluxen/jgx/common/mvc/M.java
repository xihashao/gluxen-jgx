package com.gluxen.jgx.common.mvc;//package com.wisesoft.wisdom.trip.plat.common.mvc;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.ui.Model;
//
//
///**
// * json对象
// * @author Administrator
// *
// */
//public class M {
//	/**
//	 * 消息关键字
//	 */
//	private static final String MSG_KEY = "springMvcMessage";
//	/**
//	 * 跳转地址
//	 */
//	private String url;
//	/**
//	 * 消息
//	 */
//	private String message;
//	/**
//	 * 结果类型
//	 */
//	private String status;
//	/**
//	 * jsFunction
//	 */
//	private String jsFunction;
//	private String yzm;
//	/**
//	 * 数据
//	 */
//	private Object data;
//
//	public String getYzm() {
//		return yzm;
//	}
//
//	public void setYzm(String yzm) {
//		this.yzm = yzm;
//	}
//
//	/**
//	 * 返回消息
//	 * 
//	 * @return
//	 */
//	public M msg(String msg) {
//		message = msg;
//		return this;
//	}
//
//	/**
//	 * 本地跳转
//	 * 
//	 * @param url
//	 * @return
//	 */
//	public M redirect(String url) {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//				.getRequestAttributes()).getRequest();
//		String ctxPath = request.getContextPath();
//		this.url = ctxPath + url;
//		return this;
//	}
//
//	/**
//	 * 远程跳转
//	 * 
//	 * @param url
//	 * @return
//	 */
//	public M remote(String url) {
//		this.url = url;
//		return this;
//	}
//
//	/**
//	 * 返回前调用JS代码
//	 * 
//	 * @param js
//	 * @return
//	 */
//	public M js(String js) {
//		this.jsFunction = js;
//		return this;
//	}
//
//	/**
//	 * 创建错误
//	 * 
//	 * @param model
//	 * @return
//	 */
//	public static M error(Model model) {
//		M message = new M();
//		model.addAttribute(MSG_KEY, message);
//		message.status = "error";
//		message.message = "操作失败!";
//		return message;
//	}
//
//	/**
//	 * 创建成功
//	 * 
//	 * @param model
//	 * @return
//	 */
//	public static M success(Model model) {
//		M message = new M();
//		model.addAttribute(MSG_KEY, message);
//		message.status = "success";
//		message.message = "您的操作已成功!";
//		return message;
//	}
//	/**
//	 * 创建成功
//	 * 
//	 * @param model
//	 * @return
//	 */
//	public static M success2(Model model,String yzm) {
//		M message = new M();
//		model.addAttribute(MSG_KEY, message);
//		message.status = "success";
//		message.message = "您的操作已成功!";
//		message.yzm = yzm;
//		return message;
//	}
//
//	/**
//	 * 设置响应状态
//	 * 
//	 * @param model
//	 * @param status
//	 * @return
//	 */
//	public static M status(Model model, String status) {
//		M message = new M();
//		model.addAttribute(MSG_KEY, message);
//		message.status = status;
//		return message;
//	}
//
//	/**
//	 * 创建成功
//	 * 
//	 * @param model
//	 * @return
//	 */
//	public static M none(Model model) {
//		M message = new M();
//		model.addAttribute(MSG_KEY, message);
//		return message;
//	}
//
//	/**
//	 * 返回页面地址
//	 * 
//	 * @return
//	 */
//	public String go() {
//		if (StringUtils.equals("success", status)) {
//			return "common/success";
//		} else {
//			return "common/error";
//		}
//	}
//
//	/**
//	 * forward到某个页面
//	 * 
//	 * @param url
//	 * @return
//	 */
//	public String forward(String url) {
//		return "forward:" + url;
//	}
//
//	/**
//	 * forward到某个页面
//	 * 
//	 * @param url
//	 * @return
//	 */
//	public static String forwardTo(String url) {
//		return "forward:" + url;
//	}
//
//	/**
//	 * render某个页面
//	 * 
//	 * @param url
//	 * @return
//	 */
//	public static String goTo(String url) {
//		return url;
//	}
//
//	/**
//	 * redirect到某个页面
//	 * 
//	 * @param url
//	 * @return
//	 */
//	public static String redirectTo(String url) {
//		return "redirect:" + url;
//	}
//
//	/**
//	 * 返回地址
//	 * 
//	 * @param url
//	 * @return
//	 */
//	public String go(String url) {
//		return url;
//	}
//
//	/**
//	 * 返回json格式消息
//	 * 
//	 * @return
//	 */
//	public M ajax() {
//		return this;
//	}
//
//	/**
//	 * 设置对象
//	 * 
//	 * @param object
//	 * @return
//	 */
//	public M data(Object object) {
//		this.data = object;
//		return this;
//	}
//
//	/**
//	 * 将data设置为map
//	 * 
//	 * @return
//	 */
//	public M data() {
//		this.data = new HashMap<String, Object>();
//		return this;
//	}
//
//	/**
//	 * 设置map的值
//	 * 
//	 * @param key
//	 * @param value
//	 * @return
//	 */
//	public M set(String key, Object value) {
//		if (data == null || (data instanceof Map) == false) {
//			throw new IllegalArgumentException("调用此方法前请先调用M.map()方法!");
//		}
//		Map<String, Object> map = (Map<String, Object>) data;
//		map.put(key, value);
//		return this;
//	}
//
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getJsFunction() {
//		return jsFunction;
//	}
//
//	public void setJsFunction(String jsFunction) {
//		this.jsFunction = jsFunction;
//	}
//
//	/**
//	 * 输出Json
//	 * 
//	 * @param object
//	 */
//	public static void ajax(HttpServletResponse response, Object object) {
//		try {
//			response.setContentType("text/html; charset=UTF-8");
//			String jsonString = JsonUtils.objectToString(object);
//			PrintWriter writer = response.getWriter();
//			writer.println(jsonString);
//			writer.flush();
//		} catch (IOException er) {
//			Logger.error(er);
//		}
//	}
//	
//	/**
//	 * 输出text
//	 * 
//	 * @param text
//	 */
//	public static void text(HttpServletResponse response, String text) {
//		try {
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter writer = response.getWriter();
//			writer.println(text);
//			writer.flush();
//		} catch (IOException er) {
//			Logger.error(er);
//		}
//	}
//
//	public void ajaxM(HttpServletResponse response) {
//		ajax(response, this);
//	}
//
//	public Object getData() {
//		return data;
//	}
//
//	public void setData(Object data) {
//		this.data = data;
//	}
//
//	/**
//	 * 跳转到外网
//	 * 
//	 * @param url
//	 */
//	public static String forceRedirect(HttpServletResponse response,String url) {
//		try {
//			response.sendRedirect(url);
//		} catch (Exception er) {
//			Logger.debug(er.getMessage());
//		}
//		return null;
//	}
//
//}
