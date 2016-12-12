package com.junjincode.solr.service.impl;



import java.util.concurrent.ConcurrentHashMap;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;

import com.alibaba.fastjson.JSONObject;
import com.junjincode.solr.annotation.InterfaceQuery;
import com.junjincode.solr.constant.SolrSearchType;
import com.junjincode.solr.service.SolrBaseQuery;
import com.junjincode.solr.util.ConstPropertiesReader;
import com.junjincode.solr.util.StringUtils;

/**
 * <p>
 * Description: 排序查询
 * </p>
 * 
 * @author junjin4838
 * @date 2016年11月27日
 * @version 1.0
 */
@InterfaceQuery(type = SolrSearchType.SORT)
public class SolrBuildSortQuery implements SolrBaseQuery {
  
  private static ConstPropertiesReader propertiesReader = ConstPropertiesReader.getInstance();

  private static final String DEFAULT_SORT = "_DEFAULT_SORT";
  
  /**
   * 封装查询条件
   * 
   * 调用者的传排序参数格式: iScore desc
   * 
   * @param query sorlQuery封装对象
   * @param trackId 跟踪ID
   * @param fieldName solr域名称
   * @param fieldValue solr域值
   * 
   */
  public void buildQuery(SolrQuery query, String trackId,String type, String fieldName, String requestName,
      JSONObject fieldValue) throws Exception {

    ConcurrentHashMap<String, String> cacheParams = SolrBuildFilterQuery.getCacheParams();
    
    String defaultSort = propertiesReader.get("XF" + DEFAULT_SORT).toString();
    
    Object sortObj = fieldValue.get(requestName);

    if (StringUtils.isEmpty(sortObj)) {
      query.set("sort", defaultSort);
    } else {
      String[] sortArry = sortObj.toString().split(",");
      for (String string : sortArry) {
        String[] strArry = string.split(" ");
        if ((ORDER.desc.toString()).equals(strArry[1])) {
          query.addSort(cacheParams.get(strArry[0]), ORDER.desc);
        } else {
          query.addSort(cacheParams.get(strArry[0]), ORDER.asc);
        }
      }
    }
    
  }

}
