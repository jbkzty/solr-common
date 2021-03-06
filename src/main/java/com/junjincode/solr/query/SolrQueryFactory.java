package com.junjincode.solr.query;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.junjincode.solr.annotation.InterfaceQuery;
import com.junjincode.solr.constant.SolrSearchType;
import com.junjincode.solr.service.SolrBaseQuery;

/**
 * <p>
 * Description: 通过传入的参数的不同来获取不同的策略接口
 * </p>
 * 
 * @author junjin4838
 * @date 2016年11月27日
 * @version 1.0
 */
public class SolrQueryFactory {

	/**
	 * 放置缓存信息
	 */
	private static ConcurrentHashMap<SolrSearchType, SolrBaseQuery> serviceImplMap = new ConcurrentHashMap<SolrSearchType, SolrBaseQuery>();

	// 需要扫描的包路径
	private static String NEED_TO_SCANPACKAGE = "com.junjincode.solr.service.impl";

	// 类加载器
	private ClassLoader classLoader = getClass().getClassLoader();

	// 查询实现接口
	private List<Class<? extends SolrBaseQuery>> queryList = null;

	// 使用private 来防止外界new这个实例
	private SolrQueryFactory() {
		init();
		initService();
	}

	public static SolrQueryFactory getInstance() {
		return SolrQueryFactoryInstance.instance;
	}

	public static class SolrQueryFactoryInstance {
		private static SolrQueryFactory instance = new SolrQueryFactory();
	}

	/**
	 * 传入的搜索类型来选择具体的策略类 （增加缓存）
	 * 
	 * @param solrSearchType
	 */
	public SolrBaseQuery buildSolrQuery(SolrSearchType solrSearchType) {

		return serviceImplMap.get(solrSearchType);

	}

	/**
	 * 处理注解类
	 * 
	 * @param clazz
	 *            传入的策略类
	 * @return 返回相应的注解
	 */
	private InterfaceQuery handleAnnotation(Class<? extends SolrBaseQuery> clazz) {

		Annotation[] annotations = clazz.getDeclaredAnnotations();
		if (annotations == null || annotations.length == 0) {
			return null;
		}

		for (int i = 0; i < annotations.length; i++) {
			if (annotations[i] instanceof InterfaceQuery) {
				return (InterfaceQuery) annotations[i];
			}
		}

		return null;

	}

	/**
	 * 初始化接口实现类的对应关系
	 */
	public void initService() {

		for (Class<? extends SolrBaseQuery> clazz : queryList) {

			InterfaceQuery clazzOfAnnotation = handleAnnotation(clazz);

			SolrBaseQuery solrQueryClazz = null;
			try {
				solrQueryClazz = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

			serviceImplMap.put(clazzOfAnnotation.type(), solrQueryClazz);

		}

	}

	/**
	 * 初始化工程类
	 */
	@SuppressWarnings("unchecked")
	private void init() {

		queryList = new ArrayList<Class<? extends SolrBaseQuery>>();

		// 获取包下面所有的class资源
		File[] resources = getResourse();
		Class<SolrBaseQuery> baseQueryClazz = null;

		try {
			// 使用相同的类接口加载策略接口
			baseQueryClazz = (Class<SolrBaseQuery>) classLoader.loadClass(SolrBaseQuery.class.getName());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("未找到策略接口");
		}

		for (int i = 0; i < resources.length; i++) {
			try {
				Class<?> clazz = classLoader.loadClass(NEED_TO_SCANPACKAGE + "." + resources[i].getName().replace(".class", ""));
				System.out.println("获取文件的名称： " + resources[i].getName());
				if (SolrBaseQuery.class.isAssignableFrom(clazz)
						&& clazz != baseQueryClazz) {
					queryList.add((Class<? extends SolrBaseQuery>) clazz);
				}

			} catch (ClassNotFoundException e) {
				throw new RuntimeException("添加策略接口失败");
			}
		}

	}

	/**
	 * 获取扫描包下所有的class文件
	 * 
	 * @return
	 */
	private File[] getResourse() {

		try {
			File file = new File(classLoader.getResource(NEED_TO_SCANPACKAGE.replace(".", "/")).toURI());
			return file.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					if (pathname.getName().endsWith(".class")) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception e) {
			throw new RuntimeException("未找到策略资源");
		}
	}

}
