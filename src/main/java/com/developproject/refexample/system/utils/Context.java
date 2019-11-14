/*
 * 版    权:  Timesoft Copyright 2014-2016,All rights reserved
 * 文 件 名:  Context.java
 * 描       述:  <描述>
 * 修改人:  LXWHP
 * 修改时间:  2017-5-22
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.developproject.refexample.system.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * 参数上下文
 * @author  LXWHP
 * @version  [版本号, 2013-1-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface Context extends Serializable {

	/**
	 * 获取参数列表
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String, Object> getParams();
	/**
	 * 设置参数列表
	 * @param p
	 * @see [类、类#方法、类#成员]
	 */
	public void setParams(Map<String, Object> p);
	/**
	 * 添加参数
	 * @param k
	 * @param v
	 * @see [类、类#方法、类#成员]
	 */
	public void addParam(String k, Object v);
	/**
	 * 删除参数
	 * @param k
	 * @see [类、类#方法、类#成员]
	 */
	public void delParam(String k);
	/**
	 * 获取参数
	 * @param k
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public <P> P getParam(String k);
	
	/**
	 * 获取参数
	 * @param k
	 * @param type
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public <P> P getParam(String k, Class<?> type);
	
	/**
	 * 获取参数
	 * @param k
	 * @param type
	 * @param format
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public <P> P getParam(String k, Class<?> type, String format);
	
	/**
	 * 检查是否有对应键值参数
	 * @param k
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public boolean containsKey(String k);
	
	
	/**
	 * 上下文转换到Map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String, Object> ctxToMap();
	
	/**
	 * 添加参数
	 * @param p
	 * @see [类、类#方法、类#成员]
	 */
	public void addParams(Map<String, Object> p);
	
}
