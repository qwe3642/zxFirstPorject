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

import java.util.Date;
import java.util.Map;

/**
 * 抽象的参数上下文实例
 * @author  LUOXWPC
 * @version  [版本号, 2017-5-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ContextBase extends AbstractContextBase{

	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	/**
	 * <默认构造函数>
	 */
	public ContextBase(Map<String, Object> params){
		this();
		setParams(params);
	}
	/**
	 * <默认构造函数>
	 */
	public ContextBase() {
		super();
	}
	
	/**
	 * 设置自定义参数部分
	 * @param ctx
	 * @see [类、类#方法、类#成员]
	 */
	protected void setCustomParams(Map<String, Object> ctx){
		
	}
	
	/**
	 * 获取参数上下文实例
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static Context getContext(){
		Context ctx = new ContextBase();
		return ctx;
	}
	
	/**
	 * 获取参数上下文实例
	 * @param params
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static Context getContext(Map<String, Object> params){
		Context ctx = getContext();
		ctx.setParams(params);
		return ctx;
	}
	
	public void clear() {
		getParams().clear();
	}
	
	public static void main(String[] args) {
		Context ctx = ContextBase.getContext();
		ctx.addParam("date", "20160101");
		ctx.addParam("boolean", true);
		Integer idate = ctx.getParam("date", Integer.class);
		Date ddate  = ctx.getParam("date", Date.class);
		System.out.println(idate);
		System.out.println(ddate);
		boolean tboolean = ctx.getParam("boolean", Boolean.class);
		String sboolean = ctx.getParam("boolean", String.class);
		System.out.println(tboolean);
		System.out.println(sboolean);
	}

}
