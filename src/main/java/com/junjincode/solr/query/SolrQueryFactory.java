package com.junjincode.solr.query;

import java.io.File;
import java.io.FileFilter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: SolrQueryFactory.java</p>
 * <p>Description: </p>
 * @author junjin4838
 * @date 2016年11月27日
 * @version 1.0
 */
public class SolrQueryFactory {
	
	//需要扫描的包路径
	private static String NEED_TO_SCANPACKAGE = "com.junjincode.solr.query.impl";
	
	//类加载器
	private ClassLoader classLoader = getClass().getClassLoader();
	
	//查询实现接口
	private List<Class<? extends BaseQuery>> queryList = null;
	
	//初始化工厂类
    private void init(){
    	
    	queryList = new ArrayList<Class<? extends BaseQuery>>();
    	
    	//获取包下面所有的class资源
    	File[] resources = getResourse();
    	
    	
    }
    
    /**
     * 获取扫描包下所有的class文件
     * @return
     */
    private File[] getResourse(){
    	
    	try {
			File file = new File(classLoader.getResource(NEED_TO_SCANPACKAGE.replace(".", "/")).toURI());
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
	
	

}
