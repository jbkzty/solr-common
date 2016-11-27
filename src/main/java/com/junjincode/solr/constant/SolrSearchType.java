package com.junjincode.solr.constant;

/**
 * <p>Description: 入参的搜索标识</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * @author junjin4838
 * @date 2016年11月27日
 * @version 1.0
 */
public enum SolrSearchType {

  /**
   * 查询
   */
  Q,

  /**
   * 过滤查询
   */
  FQ,
  
  /**
   * 区间查询
   */
  RANGE_FQ,

  /**
   * 分页参数
   */
  PAGE,

  /**
   * 排序
   */
  SORT,

  /**
   * 自定义的FQ (可以封装成OR来拼接参数)
   */
  DEFINED_FQ,

  /**
   * 自定义的FQ (可以封装成OR来拼接参数 + 业务处理)
   */
  DEFINED_FQ_SYS;

}
