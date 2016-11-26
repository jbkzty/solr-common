package com.junjincode.annotationDemo.service.impl;

import com.junjincode.annotationDemo.annotation.TotalVaildRegion;
import com.junjincode.annotationDemo.service.CalPrice;

/**
 * <p>Title: Vip.java</p>
 * <p>Description: 
 *    总价在1000 - 2000的生效
 * </p>
 * @author junjin4838
 * @date 2016年11月26日
 * @version 1.0
 */
@TotalVaildRegion(min=1000,max=2000)
public class Vip implements CalPrice {

	public Double calPrice(Double originalPrice) {
		return originalPrice * 0.8;
	}

}
