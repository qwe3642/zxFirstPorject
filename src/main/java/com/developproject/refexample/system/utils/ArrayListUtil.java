/*
 * 版    权:  Timesoft Copyright 2014-2016,All rights reserved
 * 文 件 名:  ArrayListUtil.java
 * 描       述:  <描述>
 * 修改人:  LUOXWPC
 * 修改时间:  2017-5-16
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.developproject.refexample.system.utils;


import com.developproject.refexample.system.dto.BasDto;

import java.lang.reflect.Array;
import java.util.*;

/**
 * ArrayList相关单元
 * @author  LUOXWPC
 * @version  [版本号, 2017-5-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ArrayListUtil {

	/**
	 * 判断数据数组是否为空值
	 * @param array
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isEmpty(List<?> array){
		return array == null || array.size() == 0;
	}
	
	/**
	 * List转换到数组
	 * @param array
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T[] listToArray(List<T> list,T[] array){
		return list.toArray(array);
	}
	
	/**
	 * List转换到数组
	 * @param list
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T[] listToArray(List<T> list){
		if (!isEmpty(list)){
			T[] newArray = (T[])Array.newInstance(list.get(0).getClass(), list.size());
			for (int i = 0; i < list.size(); i++) {
				newArray[i] = list.get(i);
			}
			return newArray;
		}
		return null;
	}
	
	/**
	 * List组合,合并
	 * @param lists
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> List<T> copyOf(List<T>... lists){
		List<T> result = new ArrayList<T>();
		for (List<T> list : lists){
			result.addAll(list);
		}
		return result;
	}
	
	/**
	 * List深度复制
	 * @param lists
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> List<T> listCopy(List<T>... lists){
		List<T> result = new ArrayList<T>();
		for (List<T> list : lists){
			for (T t : list){
				//对象类型时进行深度复制
				if (t instanceof Object){
					try {
						T t1 = (T)t.getClass().newInstance();
						ClassBeanUtils.copyProperties(t, t1);
						result.add(t1);
					} catch (Exception e) {
						throw new BusinessException("00036","List复制数据发生错误!",e);
					}
				}else{
					result.add(t);
				}
			}
		}
		return result;
	}
	
	/**
	 * 去除空值
	 * @param array
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static List<?> nullArrayList(List<?> array){
		if (array == null) array = new ArrayList<Class<?>>();
		return array;
	}
	
	public static List<?> sort(List<?> list, final String field){
		return sort(list,field,null);
	}
	
	/**
	 * 对list中的元素进行排序.
	 * 
	 * @param list
	 *            排序集合
	 * @param field
	 *            排序字段
	 * @param sort
	 *            排序方式: SortList.DESC(降序) SortList.ASC(升序).
	 * @return
	 */
	public static List<?> sort(List<?> list, final String field,final String sort){
		return SortListUtil.sort(list, field,sort);
	}
	
	/**
	 * 对list中的元素按fields和sorts进行排序,
	 * fields[i]指定排序字段,sorts[i]指定排序方式.如果sorts[i]为空则默认按升序排列.
	 * 
	 * @param list
	 * @param fields
	 * @param sorts
	 * @return
	 */
	public static List<?> sort(List<?> list, String[] fields, String[] sorts){
		return SortListUtil.sort(list, fields, sorts);
	}
	
	/**
	 * 默认按正序排列
	 * 
	 * @param list
	 * @param method
	 * @return
	 */
	public static List<?> sortByMethod(List<?> list, final String method){
		return sortByMethod(list, method,null);
	}
	/**
	 *  默认按正序排列
	 * @param list
	 * @param method
	 * @param sort
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static List<?> sortByMethod(List<?> list, final String method,final String sort){
		return SortListUtil.sortByMethod(list, method,sort);
	}
	
	/**
	 *  默认按正序排列
	 * @param list
	 * @param methods
	 * @param sorts
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static List<?> sortByMethod(List<?> list, final String methods[],final String sorts[]){
		return SortListUtil.sortByMethod(list, methods,sorts);
	}
	
	/**
	 * 随机百分比
	 * @param count  产生几个随机百分比 
	 * @return
	 */
	public static List<Integer> randomPercent(Integer count){
		List<Integer> rtn = new  ArrayList<Integer>();
		Integer number = 0;
		Integer number_sum = 0;
		for(int i=0;i<count;i++){
			number = new Random().nextInt(100-number_sum);
			if (number==0){
				number = 1;
			}
			if (number==100-number_sum){
				number = number - 1;
			}
			number_sum = number_sum + number;
			rtn.add(number);
		}
		return rtn;
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		System.out.println(SystemCommonF.isEmpty(list));
		String[] array = SystemCommonF.setNullObj();
		System.out.println(SystemCommonF.isEmpty(array));
		Map<String,String> map = new HashMap<String,String>();
		System.out.println(SystemCommonF.isEmpty(map));
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(3);
		list2.add(4);
		List<Integer> list3 = copyOf(list1,list2);
		System.out.println(list3);
		List<BasDto> basDtos1 = new ArrayList<BasDto>();
		List<BasDto> basDtos2 = new ArrayList<BasDto>();
		BasDto d1 = new BasDto();
		d1.setCode("1");
		BasDto d2 = new BasDto();
		d2.setCode("2");
		basDtos1.add(d1);
		basDtos1.add(d2);
		BasDto d3 = new BasDto();
		d3.setCode("3");
		BasDto d4 = new BasDto();
		d4.setCode("4");
		basDtos2.add(d3);
		basDtos2.add(d4);
		List<BasDto> basDtos = listCopy(basDtos1,basDtos2);
		basDtos1.get(0).setCode("9");
		System.out.println("源对象与目标对象的物理地址是否一样："+(basDtos.get(0) == basDtos1.get(0)?"浅复制":"深复制"));  
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeDuplicateWithOrder(List list) {
		Set set = new TreeSet();
		set.addAll(list);
		list.clear();
		list.addAll(set);
		return list;
	}
}
