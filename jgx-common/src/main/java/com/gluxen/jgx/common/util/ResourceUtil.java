/**
 * 
 */
package com.gluxen.jgx.common.util;



import com.gluxen.jgx.util.StringUtil;

import java.io.InputStream;
import java.net.URL;

/**
 * <p>description:</p>
 * name ResourceUtil
 * @author Wen Yugang
 * @date 2017-3-28上午10:04:23
 */
public class ResourceUtil {
	//传输协议file:
		public static final String PROTOCOL_FILE = "file:";
		//传输协议classpath*:
		public static final String PROTOCOL_CLASSPATH = "classpath*:";
		
		/**
		 * 通过path,Class得到InputStream
		 * @param path
		 * @param class1
		 * @return
		 */
		
		@SuppressWarnings("unchecked")
		public static InputStream getResourceAsStream(String path, Class class1){
			InputStream inputstream = class1.getResourceAsStream(path);
			return inputstream;
		}
		
		/**
		 * 通过path得到InputStream
		 * @param path
		 * @return
		 */
		public static InputStream getResourceAsStream(String path)
		{
			String trip = path.startsWith("/") ? path.substring(1) : path;
			InputStream stream = null;
			
			if(!StringUtil.isEmpty(trip))
			{
				stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(trip);
				
				if(stream == null)
				{
					stream = ResourceUtil.class.getClassLoader().getResourceAsStream(trip);
				}
				
				if(stream == null)
				{
					stream = ResourceUtil.class.getResourceAsStream(trip);
				}
			}
			
			return stream;
		}
		
		/**
		 * 通过path得到URL
		 * @param path
		 * @return
		 */
		public static URL getResource(String path)
		{
			String trip = path.startsWith("/") ? path.substring(1) : path;
			URL url = null;
			
			if(!StringUtil.isEmpty(trip))
			{
				url = Thread.currentThread().getContextClassLoader().getResource(trip);
				
				if(url == null)
				{
					url = ResourceUtil.class.getClassLoader().getResource(trip);
				}
				
				if(url == null)
				{
					url = ResourceUtil.class.getResource(trip);
				}
			}
			
			return url;
		}
		public static void main(String[] args) {
			System.out.println(ResourceUtil.getResourceAsStream("D:\\MyEclipse\\MyEclipse 9\\Workspaces\\smartwave_core\\src\\com\\eflag\\smartwave\\util\\ResourceUtil.java", ResourceUtil.class));
		}
}
