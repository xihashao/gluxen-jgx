package com.gluxen.jgx.util;

import java.util.UUID;

/**
 * 
* UUID生成
* @author lishiqiang
* @date 2017-3-15
* modify history
 */
public abstract class CommonUtils {

	/**
	 * 随机获取UUID字符串(无中划线)
	 * 
	 * @return UUID字符串
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13)
				+ uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
	}
}
