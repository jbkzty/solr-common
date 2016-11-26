package com.junjincode.annotationDemo.service.impl;

import com.junjincode.annotationDemo.annotation.TotalVaildRegion;
import com.junjincode.annotationDemo.service.CalPrice;

/**
 * <p>Title: GoldVip.java</p>
 * <p>Description:
 *     环境会员 : 大于3000生效 
 * </p>
 * @author junjin4838
 * @date 2016年11月26日
 * @version 1.0
 */
@TotalVaildRegion(min=3000)
public class GoldVip implements CalPrice {

	public Double calPrice(Double originalPrice) {
		return originalPrice * 0.5;
	}

}
