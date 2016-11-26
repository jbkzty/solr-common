package com.junjincode.annotationDemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: TotalVaildRegion.java</p>
 * <p>Description: 
 *     @Retention(RetentionPolicy.RUNTIME)  -- 将注解保留到运行时
 *     @Target(ElementType.TYPE) -- 表示只给类添加该注解   
 * </p>
 * @author junjin4838
 * @date 2016年11月26日
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TotalVaildRegion {
	
	int max() default Integer.MAX_VALUE;
	
	int min() default Integer.MIN_VALUE;

}
