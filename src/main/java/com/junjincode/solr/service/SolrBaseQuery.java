package com.junjincode.solr.service;


import org.apache.solr.client.solrj.SolrQuery;

import com.alibaba.fastjson.JSONObject;


/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * @author junjin4838
 * @date 2016年11月26日
 * @version 1.0
 */
public interface SolrBaseQuery {
	
	public void buildQuery(SolrQuery query,String trackId,String type,String fieldName,String requestName,JSONObject fieldValue) throws Exception;

}
