package com.junjincode.annotationDemo.pojo;

import com.junjincode.annotationDemo.factory.CalPriceFactory;
import com.junjincode.annotationDemo.service.CalPrice;
import com.junjincode.annotationDemo.service.impl.Common;

/**
 * <p>
 * Title: Customer.java
 * </p>
 * <p>
 * Description: 客户类
 * </p>
 * 
 * @author junjin4838
 * @date 2016年11月26日
 * @version 1.0
 */
public class Customer {

	// 客户在本商店消费的总额度
	private Double totalAmout = 0D;

	// 客户单次消费金额
	private Double amount = 0D;

	// 每个客户都有一个计算价格的策略，初始是普通价格
	private CalPrice calPrice = new Common();

	/**
	 * 客户购买商品，就会增加它的总额
	 */
	public void buy(Double amount) {
		this.amount = amount;
		totalAmout += amount;
		
		//利用工程类生成策略模式
		calPrice = CalPriceFactory.getInstance().createCalPrice(this);
		
	}

	/**
	 * 计算客户最终想要付的钱
	 * 
	 * @return
	 */
	public Double calLastAmount() {
		return calPrice.calPrice(amount);
	}

	public Double getTotalAmount() {
		return totalAmout;
	}

	public Double getAmount() {
		return amount;
	}

}
