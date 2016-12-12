package com.junjincode.solr.service.impl;



import org.apache.solr.client.solrj.SolrQuery;

import com.alibaba.fastjson.JSONObject;
import com.junjincode.solr.annotation.InterfaceQuery;
import com.junjincode.solr.constant.SolrSearchType;
import com.junjincode.solr.service.SolrBaseQuery;
import com.junjincode.solr.util.StringUtils;

/**
 * <p>
 * Description: Page Query
 * </p>
 * 
 * @author junjin4838
 * @date 2016年11月27日
 * @version 1.0
 */
@InterfaceQuery(type = SolrSearchType.PAGE)
public class SolrBuildPageQuery implements SolrBaseQuery {
  
	/**
	 * 封装分页查询条件
	 * 
	 * @param query
	 *            solrQuery封装对象
	 * @param trackId
     *            跟踪ID
	 * @param type
	 *            业务类型
	 * @param solrName
	 *            solr配置的字段
	 * @param requestName
	 *            solr请求的字段
	 * @param paramsObject
	 *            请求参数          
	 * 
	 */
	public void buildQuery(SolrQuery query, String trackId,String type,String solrName, String requestName,JSONObject paramsObject) throws Exception {
		
		if(StringUtils.isEmpty(requestName)){
			throw new RuntimeException("分页查询参数不能为空");
		}
		
		String[] pageArry = requestName.split("-");
		
		if(pageArry.length != 2){
			throw new RuntimeException("分页查询参数格式不对，请按照iStart-iPagesize格式传参");
		}
		
		Object start = paramsObject.get(pageArry[0]);
        Object size = paramsObject.get(pageArry[1]);

		Integer pageStart, pageSize;

		if (start == null) {
			pageStart = 1;
		} else {
			pageStart = (Integer.parseInt(start.toString()) < 1) ? 1 : Integer.parseInt(start.toString());
		}
		
	    if (size == null) {
	      pageSize = 10;
	    } else {
	      pageSize = (Integer.parseInt(size.toString()) < 1) ? 10 : Integer.parseInt(size.toString());
	    }
	    
	    query.setStart(pageStart);
	    query.setRows(pageSize);
	    
	}


}
