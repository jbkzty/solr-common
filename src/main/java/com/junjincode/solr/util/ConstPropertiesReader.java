package com.junjincode.solr.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性读取配置
 */
public class ConstPropertiesReader {
	private static volatile ConstPropertiesReader instance = null;
	private static Properties properties = new Properties();
	InputStream inStream;

	
	private ConstPropertiesReader(String path){
		inStream = getClass().getResourceAsStream(path);
		try {
			properties.load(inStream);
		} catch (IOException e) {
		}
	}
	
	public static synchronized ConstPropertiesReader getInstance(){
		if (instance==null) {
			synchronized (ConstPropertiesReader.class) {
				instance = new ConstPropertiesReader("/com/junjincode/solr/conf/const.properties");
			}
		}
		return instance;
		
	}
	
	public Object get(String key){
		Object valueObject = properties.get(key);
		if (valueObject!=null) {
			return valueObject;
		}
		return null;
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}
