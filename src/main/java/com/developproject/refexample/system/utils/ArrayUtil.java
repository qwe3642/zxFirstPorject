/*
 * 版    权:  Timesoft Copyright 2014-2016,All rights reserved
 * 文 件 名:  ArrayUtil.java
 * 描       述:  <描述>
 * 修改人:  LUOXWPC
 * 修改时间:  2017-5-16
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.developproject.refexample.system.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组相关单元
 * @author  LUOXWPC
 * @version  [版本号, 2017-5-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ArrayUtil {

	/**
	 * 判断数据数组是否为空值
	 * @param array
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> boolean isEmpty(T[] array){
		return array == null || array.length == 0;
	}

	/**
	 * 数组转换到List
	 * @param array
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> List<T> arrayToList(T[] array){
		List<T> list = new ArrayList<T>();
		for (T t : array){
			list.add(t);
		}
		return list;
	}
	
	/**
	 * 拷贝一个新数组
	 * @param original
	 * @param newLength
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T[] copyOf(T[] original,int newLength){
		return Arrays.copyOf(original, newLength);
	}
	
	/**
	 * 按起止范围获取新数组
	 * @param original
	 * @param from
	 * @param to
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T[] copyOfRange(T[] original,int from,int to){
		return Arrays.copyOfRange(original, from, to);
	}
	
	/**
	 * 拷贝一个新数组
	 * @param arrays
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T[] copyOf(T[]... arrays){
		List<T> list = new ArrayList<T>();
		for (T[] array : arrays){
			for (T t : array){
				list.add(t);
			}
		}
		return ArrayListUtil.listToArray(list);
	}
	
	/**
	 * 数组转换到List
	 * @param array
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T[] removeByIndex(T[] array,int index){
		List<T> list = arrayToList(array);
		list.remove(index);
		return ArrayListUtil.listToArray(list);
	}
	
	/**
	 * 数组转换到List
	 * @param array
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T[] removeByValue(T[] array,T value){
		List<T> list = arrayToList(array);
		List<T> results = new ArrayList<T>();
		for (T t : list){
			if (!t.equals(value)){
				results.add(t);
			}else{
				Comparable midVal = (Comparable)t;
				int cmp = midVal.compareTo(value);
				if (cmp != 0){
					results.add(t);
				}
			}
		}
		T[] newArray = ArrayListUtil.listToArray(results);
		return newArray;
	}
	
	/**
	 * 数组数据深度复制
	 * @param arrays
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T[] arrayCopy(T[]... arrays){
		List<T> list = new ArrayList<T>();
		for (T[] array : arrays){
			for (T t : array){
				//对象类型时进行深度复制
				if (t instanceof Object){
					try {
						T t1 = (T)t.getClass().newInstance();
						ClassBeanUtils.copyProperties(t, t1);
						list.add(t);
					} catch (Exception e) {
						throw new BusinessException("00037","复制数据发生错误!",e);
					}
				}else{
					list.add(t);
				}
			}
		}
		return ArrayListUtil.listToArray(list);
	}
	
	public static void main(String[] args) {

	}
}
