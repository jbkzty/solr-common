package com.junjincode.solr.util;


import org.apache.solr.client.solrj.SolrQuery;

/**
 * 高亮
 * 
 * @author junjin4838
 *
 */
public class SolrHightLight {
  
  //高亮字段
  private static final String DEFAULT_HL = "_DEFAULT_HL";
  
  //是否高亮
  private static final String DEFAULT_ON = "_DEFAULT_ON";

  /**
   * 设置高亮
   * 
   * 高亮的信息属性可以在solr的配置文件中配置 query.setHighlightSimplePre("<font style=\"color:red\">")
   * query.setHighlightSimplePost("</font>")
   * 
   * @param query sorlQuery封装对象
   * @param key 对应的索引字段
   */
  public static void setHighLight(SolrQuery query, String type) {

    ConstPropertiesReader propertiesReader = ConstPropertiesReader.getInstance();

    String hightLight = propertiesReader.get(type + DEFAULT_HL).toString();
    
    Boolean on = Boolean.parseBoolean(propertiesReader.get(type + DEFAULT_ON).toString());

    query.setHighlight(on);
    query.addHighlightField(hightLight);

  }

}
