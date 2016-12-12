package com.junjincode.solr.service.impl;



import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.CommonParams;

import com.alibaba.fastjson.JSONObject;
import com.junjincode.solr.annotation.InterfaceQuery;
import com.junjincode.solr.constant.SolrSearchType;
import com.junjincode.solr.service.SolrBaseQuery;
import com.junjincode.solr.util.ConstPropertiesReader;
import com.junjincode.solr.util.ScoreByEdismax;
import com.junjincode.solr.util.SolrGroup;
import com.junjincode.solr.util.SolrHightLight;
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
@InterfaceQuery(type = SolrSearchType.Q)
public class SolrBuildQuery implements SolrBaseQuery {
  
  private static final String XF_TAG = "XF";

  /**
   * 封装查询条件
   * 
   * @param query sorlQuery封装对象
   * @param type 业务类型
   * @param defaultFiled 查询默认域 在solr配置文件中配置
   * @param fieldValue 查询关键字
   * 
   */
  public void buildQuery(SolrQuery query, String trackId,String type, String fieldName, String requestName,JSONObject fieldValue) throws Exception {

    Object value = fieldValue.get(requestName);
    Object searchType = fieldValue.get("searchType");

    if (StringUtils.isEmpty(value)) {
      query.setQuery("*:*");
    } else {
      
      //新房业务
      if (XF_TAG.equals(type)) {
        if ("suggest".equals(searchType)) {
          query.set("q", "xf_name :" + value.toString());
        } else {
          query.setQuery(value.toString());
        }
      //其他业务线  
      }else{
          query.setQuery(value.toString());
      }
    }
    
    
    // 设置字段权重
    ScoreByEdismax.scoreByDefault(query,type);
    
    //设置Group
    SolrGroup.setGroup(query, type);
    
    //设置高亮
    SolrHightLight.setHighLight(query, type);
    
  }


  /**
   * 设置默认域
   * 
   * @param query sorlQuery封装对象
   * @param defaultFiled 默认域名
   */
  public static void setDefaultField(SolrQuery query) {

    ConstPropertiesReader propertiesReader = ConstPropertiesReader.getInstance();

    String default_field = propertiesReader.get("").toString();

    query.set(CommonParams.DF, default_field);

  }


  





}
