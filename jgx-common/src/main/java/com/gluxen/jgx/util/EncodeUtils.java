package com.gluxen.jgx.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
* 编码处理
* @author lishiqiang
* @date 2017-3-15
* modify history
 */
public final class EncodeUtils {
	/**
	 * 编码为base64
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeAsBase64(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		Base64 coder = new Base64();
		String base64Password = coder.encodeToString(str.getBytes());
		return base64Password;
	}
	
	/**
	 * base64解码
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeBase64(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		Base64 coder = new Base64();
		byte[] tempArr = coder.decode(str.getBytes());
		String result=new String(tempArr);
		return result;
	}

	/**
	 * 编码为md5
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeAsMD5(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		return DigestUtils.md5Hex(str);
	}
}
