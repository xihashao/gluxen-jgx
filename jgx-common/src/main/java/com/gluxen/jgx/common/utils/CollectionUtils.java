package com.gluxen.jgx.common.utils;

import java.util.Collection;

/**
 * <p>description：集合处理工具类</p>
 * @name：CollectionUtils
 * @author：Wen Yugang
 * @date：2017年3月15日下午1:57:36
 */
public class CollectionUtils {
	/**
	 * <p>description：判断集合不为null 或 size = 0</p>
	 * @author：Wen Yugang
	 * @date：2017年3月15日下午1:56:32
	 */
	public static boolean isNotEmpty(Collection c){
		if(c==null || c.size()==0){
			return false;
		}
		return true;
	}
	/**
	 * <p>description：判断集合是null 或 size = 0</p>
	 * @author：Wen Yugang
	 * @date：2017年3月15日下午1:57:02
	 */
	public static boolean isEmpty(Collection c){
		if(c!=null&&c.size()>0){
			return false;
		}
		return true;
	}
}
