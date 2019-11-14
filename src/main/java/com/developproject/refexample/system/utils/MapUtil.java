/*
 * 版    权:  Timesoft Copyright 2014-2016,All rights reserved
 * 文 件 名:  MapUtil.java
 * 描       述:  <描述>
 * 修改人:  LUOXWPC
 * 修改时间:  2017-5-16
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.developproject.refexample.system.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Map相关单元
 * @author  LUOXWPC
 * @version  [版本号, 2017-5-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MapUtil {

	/**
	 * 判断数据数组是否为空值
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isEmpty(Map map){
		return map == null || map.size() == 0;
	}
	
	/**
	 * 去除空值
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <K,V> Map<K,V> nullArrayList(Map<K,V> map){
		if (map == null) map = new HashMap<K,V>();
		return map;
	}
	
	/**
	 * 取得线程同步Map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <K,V> Map<K,V>  getSynchronizedMap(){
		return Collections.synchronizedMap(new HashMap<K, V>());
	}
	
	/**
	 * 取得线程同步Map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <K,V> Map<K,V>  getSynchronizedMap(Map<K, V> map){
		return Collections.synchronizedMap(map);
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = null;
		map = nullArrayList(null);
		map.put("A", "STring");
		System.out.println(map.size());
		System.out.println(map.get("A"));
	}
}
