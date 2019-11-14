/*
 * 版    权:  Timesoft Copyright 2014-2016,All rights reserved
 * 文 件 名:  AbstractContextBase.java
 * 描       述:  <描述>
 * 修改人:  LUOXWPC
 * 修改时间:  2017-5-22
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.developproject.refexample.system.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 抽象的参数上下文实例
 * @author  LUOXWPC
 * @version  [版本号, 2017-5-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractContextBase implements Context{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	/**
	 * 任务参数
	 */
	private Map<String, Object> params;
	
	/**
	 * <默认构造函数>
	 */
	public AbstractContextBase(Map<String, Object> params){
		this();
		this.params = params;
	}
	/**
	 * <默认构造函数>
	 */
	public AbstractContextBase() {
		params = new HashMap<String, Object>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> getParams() {
		return params;
	}
	/**
	 * {@inheritDoc}
	 */
	public void setParams(Map<String, Object> p) {
		this.params = p;
	}
	/**
	 * {@inheritDoc}
	 */
	public void addParam(String k,Object v){
		params.put(k, v);
	}
	/**
	 * {@inheritDoc}
	 */
	public void delParam(String k){
		if (params.containsKey(k)) params.remove(k);
	}
	/**
	 * {@inheritDoc}
	 */
	public <P> P getParam(String k){
		if (params.containsKey(k)) {
			return (P)params.get(k);
		}else{
			return null;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public <P> P getParam(String k,Class<?> type){
		return getParam(k, type,"yyyymmdd");
	}
	
	/**
	 * {@inheritDoc}
	 */
	public <P> P getParam(String k,Class<?> type,String format){
		if (params.containsKey(k)) {
			return (P)TypeCaseHelper.convert(params.get(k), type, format);
		}else{
			return null;
		}
	}
	
	/**
	 * 设置自定义参数部分
	 * @param ctx
	 * @see [类、类#方法、类#成员]
	 */
	protected abstract void setCustomParams(Map<String, Object> ctx); 
	
	/**
	 * 上下文转换到Map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String, Object> ctxToMap(){
		Map<String, Object> result = new HashMap<String, Object>();
		result.putAll(params);
		setCustomParams(result);
		return result;
	}
	
	/**
	 * 检查是否有对应键值参数
	 * @param k
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public boolean containsKey(String k){
		return params.containsKey(k);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void addParams(Map<String, Object> p){
		Set<String> keys = p.keySet();
		for (String k : keys){
			addParam(k,p.get(k));
		}
	}
}
