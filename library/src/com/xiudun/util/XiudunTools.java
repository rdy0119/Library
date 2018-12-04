/**
 * 
 */
package com.xiudun.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Administrator
 *	工具类
 */
public class XiudunTools {

	/**
	 * 
	 * TODO 获取不重复的字符串key
	 * @return
	 */
	public static String getKey() {
		return UUID.randomUUID().toString().replaceAll("-","");
	}
	/**
	 * 获取服务器当前的日期
	 */
	public static String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	/**
	 * 
	 * TODO 获取当前服务器时间戳
	 * @return
	 */
	public static long getDateTime() {
		return System.currentTimeMillis();
	}
}






































