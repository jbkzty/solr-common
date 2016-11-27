package com.junjincode.solr.query;

import java.lang.reflect.Field;

import org.apache.solr.client.solrj.SolrQuery;

import com.junjincode.solr.annotation.SolrField;

/**
 * <p>Title: WrapSolrQueryByAnnotation.java</p>
 * <p>Description: 读取自定义POJO的配置注解</p>
 * @author junjin4838
 * @date 2016年11月27日
 * @version 1.0
 */
public class WrapSolrQueryByAnnotation {
	
	public static SolrQuery wrapSolrQuery(SolrQuery solrQuery,Object obj){
		
		Field[] fields = obj.getClass().getDeclaredFields();
		
	    for (Field f : fields) {
	    	
	    	SolrField field = f.getAnnotation(SolrField.class);
			
		}
		
		return null;
	}

}
