package com.junjincode.solr.query;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;

import com.alibaba.fastjson.JSONObject;
import com.junjincode.solr.annotation.SolrField;
import com.junjincode.solr.constant.SolrSearchType;
import com.junjincode.solr.pojo.XFilterModel;
import com.junjincode.solr.service.SolrBaseQuery;
import com.junjincode.solr.util.FastJsonUtil;

/**
 * <p>
 * Description: 读取自定义POJO的配置注解
 * </p>
 * 
 * @author junjin4838
 * @date 2016年12月05日
 * @version 1.0
 *
 */
public class WrapSolrQueryByAnnotation {

  private static SolrQueryFactory solrQueryFactory = SolrQueryFactory.getInstance();

  private static HashMap<String, String> requestTosolrFieldMap = new HashMap<String, String>();

  private static HashMap<String, SolrSearchType> requestToTypeMap =
      new HashMap<String, SolrSearchType>();

  static {
    init();
  }



 /**
   * 封装Solr Query
   * 
   * @param solrQuery solr查询的类
   * @param obj 自定义的POJO类
   * @param trackId 接口跟踪ID
   * @param paramsObject 调用方传入的参数
   * @return
   * @throws Exception
   **/
  public static SolrQuery wrapSolrQuery(SolrQuery solrQuery, String trackId, String type,
      Object obj, JSONObject paramsObject) throws Exception {

    Map<String, Object> requestObj = FastJsonUtil.toHashMap(paramsObject.toString());
    
    //判断请求参数中是否存在sKw，如果不存在，则手动添加（为了加载SolrBuildQuery中权重，group，高亮等设置）
    if(!requestObj.containsKey("sKw")){
      requestObj.put("sKw", "");
    }
    
    /**
     * 减少遍历的次数
     */
    for (Map.Entry<String, Object> entry : requestObj.entrySet()) {

      /**
       * 请求参数
       */
      String paramName = entry.getKey();
      
      /**
       * 获取缓存中queryType字段
       */
      SolrSearchType queryType = requestToTypeMap.get(paramName);
      
      /**
       * 获取缓存中的solrField字段
       */
      String fieldName = requestTosolrFieldMap.get(paramName);
      
      /**
       * 通过配置的查询策略动态获取相应的策略模式
       */
      SolrBaseQuery solrQueryService = (SolrBaseQuery) solrQueryFactory.buildSolrQuery(queryType);

      /**
       * 具体执行
       */
      solrQueryService.buildQuery(solrQuery, trackId, type, fieldName, paramName, paramsObject);
      
    }

    return solrQuery;

  }


  /**
   * 初始化实体类的对应关系
   */
  private static void init() {
    
    XFilterModel obj = new XFilterModel();

    Field[] fields = obj.getClass().getDeclaredFields();

    for (Field f : fields) {
      SolrField field = f.getAnnotation(SolrField.class);
      String fieldName = field.solrName();
      String requestName = field.requestName();
      SolrSearchType type = field.type();
      requestTosolrFieldMap.put(requestName, fieldName);
      requestToTypeMap.put(requestName, type);
    }

  }
  
  /**
   * 返回映 请求参数 -- solr实体类 映射关系
   * @return
   */
  public static HashMap<String, String> getRequestTosolrFieldMap(){
     return requestTosolrFieldMap;
  }
}
