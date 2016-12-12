package com.junjincode.solr.service.impl;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.solr.client.solrj.SolrQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.junjincode.solr.annotation.InterfaceQuery;
import com.junjincode.solr.constant.SolrSearchType;
import com.junjincode.solr.service.SolrBaseQuery;
import com.junjincode.solr.util.FastJsonUtil;
import com.junjincode.solr.util.StringUtils;

/**
 * 用户自定义的查询  (多字段的OR拼接)
 * @author junjin4838
 *
 */
@InterfaceQuery(type = SolrSearchType.DEFINED_FQ_OR)
public class UserDefinedQueryByOr implements SolrBaseQuery {
  
  private static final Logger logger = LoggerFactory.getLogger(UserDefinedQueryByOr.class);

  /**
   * 封装过滤条件
   *    {eg：xf_desc:33 OR xf_tag:22 OR xf_kycTag:11}
   * 
   * @param query
   *            solrQuery封装对象
   * @params trackId 
   *            流程追踪ID         
   * @param type
   *            业务类型
   * @param solrName
   *            solr配置的字段
   * @param requestName
   *            solr请求的字段
   * @param paramsObject
   *            请求参数          
   * 
   */
  public void buildQuery(SolrQuery query, String trackId,String type, String fieldName, String requestName,JSONObject fieldValue) throws Exception {
     
    ConcurrentHashMap<String, String> cacheParams = SolrBuildFilterQuery.getCacheParams();
    
    Object filterQueryOr = fieldValue.get(requestName);
    
    if (!StringUtils.isEmpty(filterQueryOr)) {
      
      StringBuffer sb = new StringBuffer();
      
      Map<String, Object> unionParamsMap = FastJsonUtil.toHashMap(filterQueryOr.toString());
      
      unionParamsMap.size();
      
      int i = 0;
      
      for (Map.Entry<String, Object> entry : unionParamsMap.entrySet()) {
        i++;
        if (i < unionParamsMap.size()) {
          String solrParams = cacheParams.get(entry.getKey());
          Object value = entry.getValue();

          sb.append(solrParams + ":" + value);

          sb.append(" ");
          sb.append("OR");
          sb.append(" ");
        } else {
          String solrParams = cacheParams.get(entry.getKey());
          Object value = entry.getValue();

          sb.append(solrParams + ":" + value);
        }
      }
      
      logger.info("trackId 自定义封装FQ 拼接OR" + sb.toString());
      
      query.addFilterQuery(sb.toString());
      
    }
    
  }

}
