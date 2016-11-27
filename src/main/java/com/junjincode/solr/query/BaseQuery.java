package com.junjincode.solr.query;

import org.apache.solr.client.solrj.SolrQuery;


/**
 * <p>Title: BaseQuery.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * @author junjin4838
 * @date 2016年11月26日
 * @version 1.0
 */
public interface BaseQuery {
	
	public void buildQuery(SolrQuery query,Object fieldName,Object fieldValue) throws Exception;

}
