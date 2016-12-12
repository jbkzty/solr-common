package com.junjincode.solr.service.impl;



import java.util.concurrent.ConcurrentHashMap;

import org.apache.solr.client.solrj.SolrQuery;

import com.alibaba.fastjson.JSONObject;
import com.junjincode.solr.annotation.InterfaceQuery;
import com.junjincode.solr.constant.SolrSearchType;
import com.junjincode.solr.service.SolrBaseQuery;
import com.junjincode.solr.util.StringUtils;

/**
 * <p>
 *    Description: 价格范围的搜索
 * </p>
 * @author junjin4838
 * @date 2016年11月30日
 * @version 1.0
 */
@InterfaceQuery(type = SolrSearchType.PRICE)
public class SolrBuildPriceQuery implements SolrBaseQuery {
  
  /**
   * 封装价格查询条件
   * 
   * @param query
   *            solrQuery封装对象
   * @param trackId
   *            业务类型           
   * @param type
   *            流程追踪ID       
   * @param solrName
   *            solr配置的字段
   * @param requestName
   *            solr请求的字段
   * @param fieldValue
   *            请求参数          
   * 
   */
	public void buildQuery(SolrQuery query, String trackId,String type,String fieldName,String requestName, JSONObject fieldValue) throws Exception {
		
		
	   String analyzeStr = analyzePrice(requestName,fieldValue);
	   
	   if("".equals(analyzeStr)){
	    String analyzeStrFalse =  analyzePrice("-" + requestName,fieldValue);
	    if(!"".equals(analyzeStrFalse)){
	      query.addFilterQuery("-" + fieldName + ":" + analyzeStrFalse.toString());
	    }
	   }else{
        query.addFilterQuery(fieldName + ":" + analyzeStr.toString());
	   }
	   
	   ConcurrentHashMap<String, String> cacheParams = SolrBuildFilterQuery.getCacheParams();
	   cacheParams.put(requestName, fieldName);

	}
	
	
	/**
	 * 拼装价格参数
	 * @param price
	 * @return
	 */
	private String analyzePrice(String requestName, JSONObject fieldValue){
	  
	  StringBuffer sb = new StringBuffer();
	  
	  Object price = fieldValue.get(requestName);
	  
	  if (!StringUtils.isEmpty(price)) {
        
        String[] priceArry = price.toString().split("-");
        
        if (priceArry.length != 2) {
            throw new RuntimeException("FQ查询：检查价格参数是否按照 最低价-最高价 形式");
        }
        
        sb.append("[");
        sb.append(priceArry[0]);
        sb.append(" TO ");
        sb.append(priceArry[1]);
        sb.append("]");
        
    }

	  return sb.toString();
	}

}
