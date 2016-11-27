package com.junjincode.solr.query.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.CommonParams;

import com.junjincode.solr.query.BaseQuery;
import com.junjincode.solr.util.StringUtils;

/**
 * <p>
 * Description: solr 主查询
 * </p>
 * 
 * @author junjin4838
 * @date 2016年11月27日
 * @version 1.0
 */
public class SolrBuildQuery implements BaseQuery {
	
	/**
	 * 封装查询条件
	 * @param query sorlQuery封装对象
	 * @param defaultFiled 查询默认域
	 * @param value 查询关键字
	 * 
	 */
	public void buildQuery(SolrQuery query, Object fieldName,Object fieldValue, Boolean mark) throws Exception {
		
		if(StringUtils.isEmpty(fieldName)){
			throw new RuntimeException("字段");
		}
		
		
		
	}

	

	/**
	 * 设置默认域
	 * 
	 * @param query
	 *            sorlQuery封装对象
	 * @param defaultFiled
	 *            默认域名
	 */
	public static void setDefaultField(SolrQuery query, String defaultFiled) {
		query.set(CommonParams.DF, defaultFiled);
	}


}
