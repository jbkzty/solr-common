package com.junjincode.solr.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.junjincode.solr.constant.SolrSearchType;

/**
 * <p>Title: SolrQuery.java</p>
 * <p>Description: </p>
 * @author junjin4838
 * @date 2016年11月27日
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InterfaceQuery {
	
	SolrSearchType type();

}
