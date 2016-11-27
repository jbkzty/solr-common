package com.junjincode.solr.query;

import java.lang.reflect.Field;

import org.apache.solr.client.solrj.SolrQuery;

import com.alibaba.fastjson.JSONObject;
import com.junjincode.solr.annotation.SolrField;

/**
 * <p>Title: WrapSolrQueryByAnnotation.java</p>
 * <p>Description: 读取自定义POJO的配置注解</p>
 * @author junjin4838
 * @date 2016年11月27日
 * @version 1.0
 */
public class WrapSolrQueryByAnnotation {
	
    private static SolrQueryFactory solrQueryFactory = SolrQueryFactory.getInstance();
	
    /**
     * 封装Solr Query
     * @param solrQuery  solr查询的类
     * @param obj        自定义的POJO类
     * @param paramsObject  调用方传入的参数
     * @return
     * @throws Exception 
     */
	public static SolrQuery wrapSolrQuery(SolrQuery solrQuery,Object obj,JSONObject paramsObject) throws Exception{
		
		Field[] fields = obj.getClass().getDeclaredFields();
		
	    for (Field f : fields) {
	    	
	    	SolrField field = f.getAnnotation(SolrField.class);
	    	
	    	String fieldName = field.solrName();
	    	String requestName = field.requestName();
	    	Object fieldValue = paramsObject.get(requestName);
	    			
	    	
	    	BaseQuery solrQueryService =  solrQueryFactory.buildSolrQuery(field.type());
	    	solrQueryService.buildQuery(solrQuery, fieldName, fieldValue);
	    	
			
		}
		
		return null;
	}

}
