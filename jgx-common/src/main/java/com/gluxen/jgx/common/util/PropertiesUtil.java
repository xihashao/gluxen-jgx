/**
 * 
 */
package com.gluxen.jgx.common.util;



import com.gluxen.jgx.util.StringUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>description:</p>
 * name PropertiesUtil
 * @author Wen Yugang
 * @date 2017-3-28上午10:04:11
 */
public class PropertiesUtil {
	/**
	 * 如果Key不存在返回defaultValue反之返回Key的对应value
	 * 
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(Properties props, String key,
			String defaultValue) {
		String value = props.getProperty(key);
		if (StringUtil.isEmpty(value)) {
			return defaultValue;
		}
		return value;
	}

	/**
	 *采用Properties类取得属性文件对应值
	 * 
	 * @param propertiesFileName
	 *            全路径
	 *@param propertyName
	 *            属性名
	 *@return 根据属性名得到的属性值，如没有返回""
	 * @throws FileNotFoundException 
	 */
	public static String getValueByPropertyName(String propertiesFileName,
			String propertyName) throws FileNotFoundException {
		
		return getValueByPropertyName(new FileInputStream(propertiesFileName), propertyName);
	}
	
	/**
	 * 获得属性文件key对应的
	 * @param is 
	 * @param propertyName 
	 * @return
	 */
	public static String getValueByPropertyName(InputStream is, String propertyName) {
		return getValueByPropertyName(is, propertyName, true);
	}
	
	/**
	 * 获得属性文件key对应的
	 * @param is
	 * @param propertyName
	 * @param closeStream 读取之后是否关闭流
	 * @return
	 */
	public static String getValueByPropertyName(InputStream is, String propertyName, boolean closeStream) {
		String s = "";
		Properties p = new Properties();
		try {
			p.load(is);
			if(closeStream){
				is.close();
			}
			s = p.getProperty(propertyName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * 获得属性文件key对应的
	 * @param is
	 * @param propertyName
	 * @param closeStream 读取之后是否关闭流
	 * @param defaultValue 
	 * @return
	 */
	public static String getValueByPropertyName(InputStream is, String propertyName, boolean closeStream, String defaultValue) {
		String s = defaultValue;
		Properties p = new Properties();
		try {
			p.load(is);
			if(closeStream){
				is.close();
			}
			s = p.getProperty(propertyName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * 调用getValueByResource方法读取 取propertiesFileName文件中名为propertyName的value
	 * 
	 * @param propertiesFileName
	 * @param propertyName
	 * @return
	 */
	public static String getValueByResource(String propertiesFileName,
			String propertyName) {
		return getValueByResource(propertiesFileName, propertyName, "");
	}

	/**
	 * 通过ResourceUtil.getResourceAsStream读
	 * 取propertiesFileName文件中名为propertyName的value
	 * 
	 * @param propertiesFileName
	 * @param propertyName
	 * @param defaultValue
	 * @return
	 */
	public static String getValueByResource(String propertiesFileName,
			String propertyName, String defaultValue) {
		String s = defaultValue;
		Properties p = new Properties();
		try {
			InputStream in = ResourceUtil
					.getResourceAsStream(propertiesFileName);
			p.load(in);
			in.close();
			s = p.getProperty(propertyName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * 通过FileInputStream读 取propertiesFileName文件中名为propertyName的value
	 * 
	 * @param propertiesFileName
	 * @param propertyName
	 * @param defaultValue
	 * @return
	 */
	public static String getValueByPropertyName(String propertiesFileName,
			String propertyName, String defaultValue) {
		String s = defaultValue;
		Properties p = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(propertiesFileName);
			p.load(in);
			in.close();
			s = p.getProperty(propertyName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 *  Description: 获取属性文件Properties对象
	 *  @author zhang.wen 2014-7-17 下午6:01:35
	 */
	public static Properties getProperties(InputStream is) {
		Properties p = new Properties();
		try {
			p.load(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
