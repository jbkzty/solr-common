package com.junjincode.solr.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * solrCore 对应的实例名称
 * @author junjin4838
 *
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.TYPE)
public @interface SolrCoreName {

	String solrName();
	
}
