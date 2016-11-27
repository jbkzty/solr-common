package com.junjincode.solr.server;

/**
 * <p>Description: solr的配置文件</p>
 * @author junjin4838
 *
 */
public class SolrServerConfiguration {

	/**
	 * 连接等待时长
	 */
	public static final int CONNECTION_TIMEOUT = 10000;

	/**
	 * 等待相应时长
	 */
	public static final int SOCKET_TIMEOUT = 10000;

	/**
	 * 每个服务器最多并发连接数
	 */
	public static final int MAX_CONNS_PER_HOST = 100;

	/**
	 * 从此客户端发出的最多并发连接数
	 */
	public static final int MAX_TATAL_CONNS = 128;
	
	/**
	 * 服务器连接地址列表
	 */
	private String serverUrls = "";

	public String getServerUrls() {
		return serverUrls;
	}

	public void setServerUrls(String serverUrls) {
		this.serverUrls = serverUrls;
	}

	

}
