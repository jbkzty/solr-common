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
 * Description: Filter Query 过滤查询
 * </p>
 * 
 * @author junjin4838
 * @date 2016年11月27日
 * @version 1.0
 */
@InterfaceQuery(type = SolrSearchType.FQ)
public class SolrBuildFilterQuery implements SolrBaseQuery {
  
  /**
   * 入参 -- solr配置 缓存
   */
  private static final ConcurrentHashMap<String, String> solrMapper = new ConcurrentHashMap<String, String>();

  /**
   * 封装过滤查询条件 （业务需求在查询不等于的情况下，是传递的 "-" + 请求参数名）
   * 
   * @param query sorlQuery封装对象
   * @param trackId 跟踪类型
   * @param type 业务类型
   * @param defaultFiled 查询默认域
   * @param value 查询关键字
   * 
   */
  public void buildQuery(SolrQuery query, String trackId,String type, String fieldName, String requestName,JSONObject fieldValue) throws Exception {

    if (StringUtils.isEmpty(fieldName)) {
      throw new RuntimeException("FQ查询：检查实体类是否配置字段");
    }

    String fq_str = fieldName.toString();
    Object keyWord = fieldValue.get(requestName);
    if (keyWord == null) {
      keyWord = fieldValue.get("-" + requestName);
      if (keyWord != null) {
        fq_str = "-" + fieldName;
      }
    }
    
    if(keyWord!=null){
      String str = fq_str + ":" + keyWord;
      query.addFilterQuery(str);
    }

    // 放置缓存
    solrMapper.put(requestName, fieldName);

  }

  /**
   * 获取 入参字段与solr配置字段的关联关系
   * 
   * @return
   */
  public static ConcurrentHashMap<String, String> getCacheParams() {
    return solrMapper;
  }
}
