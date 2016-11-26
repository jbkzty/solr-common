package com.junjincode.annotationDemo.service.impl;

import com.junjincode.annotationDemo.annotation.TotalVaildRegion;
import com.junjincode.annotationDemo.service.CalPrice;

/**
 * <p>Title: Common.java</p>
 * <p>Description:
 *    普通的在 0 - 1000 生效 
 * </p>
 * @author junjin4838
 * @date 2016年11月26日
 * @version 1.0
 */


@TotalVaildRegion(max=1000)
public class Common implements CalPrice {

	public Double calPrice(Double originalPrice) {
		return originalPrice;
	}

}
