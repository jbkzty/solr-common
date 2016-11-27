package com.junjincode.solr.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.junjincode.solr.constant.SolrSearchType;

/**
 * <p>Description: 使用注解类，方便以后将传参映射成solr的搜索字段</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * @author junjin4838
 * @date 2016年11月27日
 * @version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SolrField {
	
	/**
	 * 参数对应的搜索类型
	 */
	SolrSearchType type();
	
	/**
	 * 是否需要自己处理拼接，比如价格：min + "TO" + max
	 * @return
	 */
	int autoWrite() default 1;
	
	/**
	 * 传入的参数
	 */
	String requestName();
	
	/**
	 * solr配置的字段
	 */
	String solrName() default "";
	

}
