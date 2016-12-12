package com.junjincode.solr;

import org.apache.solr.client.solrj.SolrQuery;

import com.alibaba.fastjson.JSONObject;
import com.junjincode.solr.pojo.XFilterModel;
import com.junjincode.solr.query.WrapSolrQueryByAnnotation;

/**
 * <p>Description: </p>
 * @author junjin4838
 * @date 2016年11月28日
 * @version 1.0
 */
public class SolrTest {
	
	public static void main(String[] args) {
		
		/**
		 * 封装Query
		 */
		SolrQuery solrQuery = new SolrQuery();
		
		/**
		 * 配置模板
		 */
		XFilterModel model = new XFilterModel();
		
		/**
		 * 封装参数
		 */
		JSONObject paramsObject = new JSONObject();
		
		JSONObject appUnionParams = new JSONObject();
		appUnionParams.put("sDesc", "万科");
		appUnionParams.put("sKycTag", "东方明珠");
		appUnionParams.put("sTag", "超便宜");
		
		paramsObject.put("sKw", 1);
		paramsObject.put("sClientType", "list");
		paramsObject.put("sRegionIDs", "1");
		paramsObject.put("sPrice", "10-20");
		paramsObject.put("sAppUnionParams", appUnionParams);
		
		/**
		 * 跟踪ID
		 */
		String trackId = "000394358080283028";
		
		/**
		 * 请求类型
		 */
		String type = "XF";
		
		try {
			WrapSolrQueryByAnnotation.wrapSolrQuery(solrQuery, trackId, type, model, paramsObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(solrQuery);
	}

}
