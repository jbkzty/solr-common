package com.junjincode.solr.util;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.GroupParams;

/**
 * Group查询
 * @author junjin4838
 *
 */
public class SolrGroup {
  
  private static ConstPropertiesReader propertiesReader = ConstPropertiesReader.getInstance();
  
  //默认的分组域
  private static final String  DAFAULT_GROUP = "_DEFAULT_GROUPID";
  
  //是否分组查询
  private static final String  DAFAULT_ON = "_DEFAULT_ON";
  
  
  /**
   * 设置分组查询
   * 
   * @param query {@link http://eksliang.iteye.com/blog/2169458}
   * 
   *        group -- （true/false） 是否开启分组查询 group.field --
   *        group字段，通过在请求中加入group.field参数加以声明，如果需要对多个字段进行group by,那么该参数可以声明多次 group.query --
   *        可以对任意条件进行分组统计查询 group.limit -- 返回的数据的条目，默认为1 group.offset --
   *        偏移量，跟上面的group.limit一起可以达到分页的效果 group.sort -- 排序 group.main --
   * 
   */
  public static void setGroup(SolrQuery query,String type)  throws Exception {
    
    String defaultGroupId = propertiesReader.get(type + DAFAULT_GROUP).toString();
    
    String on = propertiesReader.get(type + DAFAULT_ON).toString();
    
    query.setParam(GroupParams.GROUP, on);
    query.setParam(GroupParams.GROUP_FIELD, defaultGroupId);
    query.setParam(GroupParams.GROUP_TOTAL_COUNT, on);
    query.setParam(GroupParams.GROUP_LIMIT, "1");
    //query.setParam(GroupParams.GROUP_MAIN, "true");
    
  }
  
  

}
