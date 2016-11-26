package com.junjincode.annotation;

import com.junjincode.annotationDemo.pojo.Customer;

/**
 * <p>Title: AnnotationTest.java</p>
 * <p>Description: </p>
 * @author junjin4838
 * @date 2016年11月26日
 * @version 1.0
 */
public class AnnotationTest {
	
	public static void main(String[] args){
		
		Customer customer = new Customer();
		customer.buy(500D);
        System.out.println("客户需要付钱：" + customer.calLastAmount());
        System.out.println("----------------");
        
		customer.buy(3000D);
        System.out.println("客户需要付钱：" + customer.calLastAmount());
        System.out.println("----------------");
		
	}

}
