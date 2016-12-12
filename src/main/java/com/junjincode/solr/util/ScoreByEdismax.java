package com.junjincode.solr.util;


import org.apache.solr.client.solrj.SolrQuery;

/**
 * 权重设置有两种方式 edismax ： 支持boost函数与score相乘 dismax : 只能使用bf作用效果是相加
 * 
 * 使用solr自带的edismax，通过bf配置来影响boost打分
 * 
 * @author JIBINGKUN791
 *
 */
public class ScoreByEdismax {

  private static ConstPropertiesReader propertiesReader = ConstPropertiesReader.getInstance();
  
  //默认的权重设置
  private static final String DEFAULT_WEIGHT = "_DEFAULT_WEIGHT";
  
  //默认的权重计算方式
  private static final String DEFAULT_FUNCTION = "_DEFAULT_FUNCTION";
  

  /**
   * 默认的评分规则
   * 
   * @param query
   */
  public static void scoreByDefault(SolrQuery query,String type) {

    String defaultWeight = propertiesReader.get(type + DEFAULT_WEIGHT).toString();
    String defaultFunction = propertiesReader.get(type + DEFAULT_FUNCTION).toString();

    query.set("defType", defaultFunction);
    query.set("qf", defaultWeight);

  }

  /**
   * 取权重的sum来做排序
   * 
   * @param query
   * 
   *        bf -- 用函数计算某个字段的权重，bf内字段必须是索引的 {@link http://wiki.apache.org/solr/FunctionQuery}
   * 
   *        qf -- 对默认查询增加权重比值，不使用函数进行计算
   * 
   *        pf -- 查询字段，这样在schema不用制定默认字段
   * 
   * 
   */
  @SuppressWarnings("unused")
  private static void scoreBySum(SolrQuery query) {

    query.set("defType", "edismax");

  }

}
