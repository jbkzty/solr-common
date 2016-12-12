package com.junjincode.solr.constant;


/**
 * 入参的搜索标识
 * 
 * @author junjin4838
 *
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
   * 分页参数
   */
  PAGE,
  
  /**
   * 价格参数
   */
  PRICE,

  /**
   * 排序
   */
  SORT,

  /**
   * 自定义的FQ (可以封装成OR来拼接参数)
   */
  DEFINED_FQ_OR,
  
  /**
   * 自定义的FQ (优惠时间字段)
   */
  DEFINED_FQ_DISCOUNT,

  /**
   * 自定义的FQ (优惠时间字段 + 是否金融支持)
   */
  DEFINED_FQ_DISCOUNT_OR;

}
