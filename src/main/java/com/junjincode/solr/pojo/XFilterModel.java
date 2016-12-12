package com.junjincode.solr.pojo;


import com.junjincode.solr.annotation.SolrField;
import com.junjincode.solr.constant.SolrSearchType;

/**
 * 封装过滤条件
 * 
 * 规则 ： 因为要动态的放置 入参字段与索引字段的对应关系 配置参数的规则为：Q - PAGE - FQ - 自定义拼接FQ - SORT
 * 
 * @author junjin4838
 *
 */
public class XFilterModel {

  // 搜索关键字
  @SolrField(type = SolrSearchType.Q, requestName = "sKw", solrName = "default_search_field")
  private String keyWord;

  // 分页参数
  @SolrField(type = SolrSearchType.PAGE, requestName = "iStart-iPagesize")
  private int page;

  // 使用端类型
  @SolrField(type = SolrSearchType.FQ, requestName = "sClientType", solrName = "xf_show_tags")
  private int clientType;

  // 城市 - 区域 -板块
  @SolrField(type = SolrSearchType.FQ, requestName = "sRegionIDs", solrName = "xf_region_relation")
  private int regionIDs;

  // 价格区间
  @SolrField(type = SolrSearchType.PRICE, requestName = "sPrice", solrName = "xf_price")
  private int price;

  // 价格单位，元/平米,万元/套(判断均价/总价）
  @SolrField(type = SolrSearchType.FQ, requestName = "sPriceUnit", solrName = "xf_price_unit")
  private String priceUnit;

  // 楼盘房间数组合
  @SolrField(type = SolrSearchType.FQ, requestName = "sRoomCnt", solrName = "xf_room_cnts")
  private int roomCnt;

  // 户型
  @SolrField(type = SolrSearchType.FQ, requestName = "sLayoutSWs", solrName = "xf_layout_SWs")
  private int layoutSWs;

  // 楼盘特色
  @SolrField(type = SolrSearchType.FQ, requestName = "sTag", solrName = "xf_tag")
  private String sTag;

  // 物业类型
  @SolrField(type = SolrSearchType.FQ, requestName = "sPropTypeID", solrName = "xf_protype_id")
  private String propTypeID;

  // 地铁线路
  @SolrField(type = SolrSearchType.FQ, requestName = "iLineIDs", solrName = "xf_line_ids")
  private int lineIDs;

  // 地铁站
  @SolrField(type = SolrSearchType.FQ, requestName = "sSubwayStationIDs", solrName = "xf_subwaystation_ids")
  private int subwayStationIDs;

  // 环线位置
  @SolrField(type = SolrSearchType.FQ, requestName = "iLoopID", solrName = "xf_loop_id")
  private int loopID;

  // 开盘时间
  @SolrField(type = SolrSearchType.FQ, requestName = "iOpenTime", solrName = "xf_open_time")
  private int openTime;

  // 楼盘状态
  @SolrField(type = SolrSearchType.FQ, requestName = "iStatus", solrName = "xf_status")
  private int status;

  // 开发商
  @SolrField(type = SolrSearchType.FQ, requestName = "iDeveloper_i", solrName = "xf_dev_id")
  private int developerId;

  // 新房更新时间 楼盘评级 (1 - A1； 2 - A2； 3 - B；4 - C； 5 - D)  xf_grade_name
  @SolrField(type = SolrSearchType.FQ, requestName = "iGrade_i", solrName = "xf_grade")
  private int grade;

  // 新房更新时间 楼盘评级名称 (A1  A2  B  C  D)  
  @SolrField(type = SolrSearchType.FQ, requestName = "sGrade_s", solrName = "xf_grade_name")
  private int gradeName;
  
  // 销售状态
  @SolrField(type = SolrSearchType.FQ, requestName = "sSaleStatusID", solrName = "xf_sale_status")
  private String sSaleStatusID;
  
  // 是否在售
  @SolrField(type = SolrSearchType.FQ, requestName = "iSaleOut_i", solrName = "xf_saleOut_i")
  private String iSaleOut_i;
  
  // 楼盘总价
  @SolrField(type = SolrSearchType.FQ, requestName = "iTotalPrice_i", solrName = "xf_total_price")
  private String iTotalPrice_i;
  
  // 楼盘面积
  @SolrField(type = SolrSearchType.FQ, requestName = "iSpace", solrName = "xf_spaces")
  private String iSpace;

  // 特殊字段，需要解析JSON { "sDesc":"...", "sKycTag":"...", "sTag":"..." }
  @SolrField(type = SolrSearchType.DEFINED_FQ_OR, requestName = "sAppUnionParams", solrName = "")
  private int appUnionParams;
  
  // 排序规则
  @SolrField(type = SolrSearchType.SORT, requestName = "sSort")
  private int sort;



}
