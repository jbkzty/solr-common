package com.junjincode.solr.server;


import java.util.concurrent.ConcurrentHashMap;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SolrServer 工厂类
 * 
 * @author junjin4838
 *
 */
public class SolrServerFactory {

  private static final Logger logger = LoggerFactory.getLogger(SolrServerFactory.class);

  /**
   * 放置缓存信息
   */
  private static ConcurrentHashMap<String, HttpSolrClient> solrSearchMap =
      new ConcurrentHashMap<String, HttpSolrClient>();

  // 配置信息
  private SolrServerConfiguration solrConf;

  // volatile （禁止指令重排优化）
  private static volatile HttpSolrClient solrServer = null;

  private static String SOLR_ADMIN_URL;


  public SolrServerConfiguration getSolrConf() {
    return solrConf;
  }

  public void setSolrConf(SolrServerConfiguration solrConf) {
    this.solrConf = solrConf;
  }

  // 使用private方法，防止外界利用new创建实例
  private SolrServerFactory() {

  }

  /**
   * 工厂模式获取SolrServer
   * 
   * @return
   */
  public HttpSolrClient getSearchServer(String searchName) {

    if (solrServer == null) {
      synchronized (SolrServerFactory.class) {
        if (!solrSearchMap.containsKey(searchName)) {

          SOLR_ADMIN_URL = solrConf.getServerUrls() + searchName;
          logger.info("请求的solr路径 ： " + SOLR_ADMIN_URL);

          // use {@link Builder} instead.
          // solrServer = new HttpSolrClient.Builder(SOLR_ADMIN_URL).build();
          solrServer = new HttpSolrClient(SOLR_ADMIN_URL);
          solrServer.setSoTimeout(SolrServerConfiguration.SOCKET_TIMEOUT);
          solrServer.setConnectionTimeout(SolrServerConfiguration.CONNECTION_TIMEOUT);
          solrServer.setMaxTotalConnections(SolrServerConfiguration.MAX_TATAL_CONNS);
          solrServer.setDefaultMaxConnectionsPerHost(SolrServerConfiguration.MAX_CONNS_PER_HOST);
          // 放置缓存
          solrSearchMap.put(searchName, solrServer);
        }
      }
    }
    return solrSearchMap.get(searchName);
  }

}
