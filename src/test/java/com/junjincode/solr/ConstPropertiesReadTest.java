package com.junjincode.solr;

import com.junjincode.solr.util.ConstPropertiesReader;

/**
 * <p>Description: </p>
 * @author junjin4838
 * @date 2016年11月29日
 * @version 1.0
 */
public class ConstPropertiesReadTest {
	
	public static void main(String[] args) {
		
		ConstPropertiesReader propertiesReader = ConstPropertiesReader.getInstance();
		String hightLight = propertiesReader.get("highlightField").toString();
		  
		System.out.println(hightLight);
	}

}
