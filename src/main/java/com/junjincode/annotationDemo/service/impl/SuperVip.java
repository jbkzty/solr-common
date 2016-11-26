package com.junjincode.annotationDemo.service.impl;

import com.junjincode.annotationDemo.annotation.TotalVaildRegion;
import com.junjincode.annotationDemo.service.CalPrice;

/**
 * <p>Title: SuperVip.java</p>
 * <p>Description: 
 *     超级会员 在 2000 - 3000 生效
 * </p>
 * @author junjin4838
 * @date 2016年11月26日
 * @version 1.0
 */
@TotalVaildRegion(min=2000,max=3000)
public class SuperVip implements CalPrice {

	public Double calPrice(Double originalPrice) {
		return originalPrice * 0.7;
	}

}
