package com.junjincode.annotationDemo.factory;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.junjincode.annotationDemo.annotation.TotalVaildRegion;
import com.junjincode.annotationDemo.pojo.Customer;
import com.junjincode.annotationDemo.service.CalPrice;

/**
 * <p>
 * Title: CalPriceFactory.java
 * </p>
 * <p>
 * Description: 工厂模式
 * </p>
 * 
 * @author junjin4838
 * @date 2016年11月26日
 * @version 1.0
 */
public class CalPriceFactory {

	// 需要扫描的策略包
	private static final String NEED_TO_SCAN = "com.junjincode.annotationDemo.service.impl";

	// 加载策略时的类加载器
	private ClassLoader classLoader = getClass().getClassLoader();

	// 策略列表
	private List<Class<? extends CalPrice>> calPriceList;

	// 根据用户的总金额产生相应的策略
	public CalPrice createCalPrice(Customer customer) {
		// 在策略列表中查找策略
		for (Class<? extends CalPrice> clazz : calPriceList) {
			TotalVaildRegion vaildRegion = handleAnnotation(clazz);
			//判断金额是否在注解的区间
			if(customer.getTotalAmount() < vaildRegion.max() && customer.getTotalAmount() > vaildRegion.min()){
				try {
					//返回当前策略的实例
					return clazz.newInstance();
				} catch (Exception e) {
					throw new RuntimeException("策略获得失败");
				} 
			}
		}
		throw new RuntimeException("策略获得失败");
	}

	/**
	 * 处理注解类
	 * 
	 * @param clazz
	 *            传入的策略类
	 * @return 返回相应的注解
	 */
	private TotalVaildRegion handleAnnotation(Class<? extends CalPrice> clazz) {
		Annotation[] annotations = clazz.getDeclaredAnnotations();
		if (annotations == null || annotations.length == 0) {
			return null;
		}

		for (int i = 0; i < annotations.length; i++) {
			if (annotations[i] instanceof TotalVaildRegion) {
				return (TotalVaildRegion) annotations[i];
			}
		}

		return null;
	}

	/**
	 * 在工程初始化的时候初始化策略列表
	 */
	private void init() {

		calPriceList = new ArrayList<Class<? extends CalPrice>>();
		//获取包下面所有的class资源
		File[] resources = getResource();
		Class<CalPrice> calPriceClazz = null;
		
		try {
			//使用相同的类加载器加载策略接口
			calPriceClazz = (Class<CalPrice>) classLoader.loadClass(CalPrice.class.getName());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("未找到策略接口");
		}
		
		for(int i=0;i<resources.length;i++){
			System.out.println("获得到的文件名：" + resources[i].getName());
			try {
				//加载包下的类
				Class<?> clazz = classLoader.loadClass(NEED_TO_SCAN + "."+resources[i].getName().replace(".class", ""));
				if(CalPrice.class.isAssignableFrom(clazz) && clazz != calPriceClazz){
					calPriceList.add((Class<? extends CalPrice>) clazz);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		

	}

	/**
	 * 获取扫描包下面所有的class文件
	 * @return
	 */
	private File[] getResource() {
		try {
			File file = new File(classLoader.getResource(NEED_TO_SCAN.replace(".", "/")).toURI());
			return file.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					if(pathname.getName().endsWith(".class")){
						return true;
					}
					return false;
				}
			});
		} catch (URISyntaxException e) {
			throw new RuntimeException("未找到策略资源");
		}
	}
	
	private CalPriceFactory(){
		init();
	}
	
	/**
	 * 工厂类的单例模式
	 * @return
	 */
	public static CalPriceFactory getInstance(){
		return CalPriceFactoryInstance.instance;
	}
	
	private static class CalPriceFactoryInstance{
		private static CalPriceFactory instance = new CalPriceFactory();
	}

}
