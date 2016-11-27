package com.junjincode.solr.pojo;

import com.junjincode.solr.annotation.SolrCoreName;
import com.junjincode.solr.annotation.SolrField;
import com.junjincode.solr.constant.SolrSearchType;

/**
 * <p>Title: BaseQuery.java</p>
 * <p>Description: 入参 </p>
 * @author junjin4838
 * @date 2016年11月27日
 * @version 1.0
 */
@SolrCoreName(solrName = "xf_collection")
public class RequestParams {

  // 搜索关键字
  @SolrField(type = SolrSearchType.Q, requestName = "sKw", solrName = "multiple_xf_search")
  private String keyWord;

  // 使用端类型
  @SolrField(type = SolrSearchType.FQ, requestName = "sClientType", solrName = "xf_show_tags")
  private int clientType;

  // 城市 - 区域 -板块
  @SolrField(type = SolrSearchType.FQ, requestName = "iRegionIDs", solrName = "xf_region_relation")
  private int regionIDs;

  // 价格区间
  @SolrField(type = SolrSearchType.FQ, requestName = "sPrice", solrName = "xf_price", autoWrite = 0)
  private int price;

  // 价格单位，元/平米,万元/套(判断均价/总价）
  @SolrField(type = SolrSearchType.FQ, requestName = "sPriceUnit", solrName = "xf_price_unit")
  private String priceUnit;

  // 户型
  @SolrField(type = SolrSearchType.FQ, requestName = "iRoomCnt", solrName = "xf_layout_SWs")
  private int roomCnt;

  // 楼盘特色
  @SolrField(type = SolrSearchType.FQ, requestName = "sTag", solrName = "xf_tag")
  private String sTag;

  // 物业类型
  @SolrField(type = SolrSearchType.FQ, requestName = "iPropTypeID", solrName = "xf_protype_id")
  private int propTypeID;

  // 地铁线路
  @SolrField(type = SolrSearchType.FQ, requestName = "lineIDs", solrName = "xf_line_ids")
  private int lineIDs;

  // 地铁站
  @SolrField(type = SolrSearchType.FQ, requestName = "iSubwayStationIDs", solrName = "xf_subwaystation_ids")
  private int subwayStationIDs;

  // 环线位置
  @SolrField(type = SolrSearchType.FQ, requestName = "iLoopID", solrName = "xf_loop_id")
  private int loopID;

  // 开盘时间
  @SolrField(type = SolrSearchType.FQ, requestName = "iOpenTime", solrName = "xf_open_time")
  private int openTime;

  // 合作类型
  @SolrField(type = SolrSearchType.FQ, requestName = "iCooTypeID", solrName = "xf_coo_type_id")
  private int cooTypeID;

  // 楼盘状态
  @SolrField(type = SolrSearchType.FQ, requestName = "iStatus", solrName = "xf_status")
  private int status;

  // 分页参数
  @SolrField(type = SolrSearchType.PAGE, requestName = "iStart-iPagesize")
  private int page;

  // 排序规则
  @SolrField(type = SolrSearchType.SORT, requestName = "sSort")
  private int sort;

  // 金融支持
  @SolrField(type = SolrSearchType.FQ, requestName = "iFinanceSupports", solrName = "xf_finance_supports")
  private int financeSupports;

  // 是否优惠
  @SolrField(type = SolrSearchType.FQ, requestName = "iIsYouhui", solrName = "xf_youhui_begintime,xf_youhui_endtime", autoWrite = 2)
  private int discount;

  // 特殊字段，需要解析JSON
  @SolrField(type = SolrSearchType.DEFINED_FQ_SYS, requestName = "sWebUnionParams", solrName = "")
  private int webUnionParams;

  // 特殊字段，需要解析JSON
  @SolrField(type = SolrSearchType.DEFINED_FQ, requestName = "sAppUnionParams", solrName = "")
  private int appUnionParams;

}
