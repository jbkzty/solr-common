package com.junjincode.solr.util;
/**
 * <p>Description: </p>
 * @author junjin4838
 * @date 2016年11月27日
 * @version 1.0
 */
public class StringUtils {
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object str) {
	 return (str == null) || ("".equals(str));
	}

}
