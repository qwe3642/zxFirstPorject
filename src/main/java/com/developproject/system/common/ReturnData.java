/*
 * 文 件 名:  JSONResponse.java
 * 版    权:  changjet Co., Ltd. Copyright 2010-2011,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  张卓卫
 * 修改时间:  2011-4-7
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.developproject.system.common;

/**
 * 前台数据返回对象 
 * @author  Administrator
 * @version  [版本号, 2011-4-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 服务器返回对象 所有服务器处理返回的统一对象
 */
public class ReturnData {
	// 返回代码
	private String code;
	// 返回信息
	private String msg;
	// 成功、失败标志
	private String successed = "false";
	// 错误信息
	private List<String> errors = new ArrayList<String>();
	// 返回数据
	private Object returnObject;

	public String isSuccessed() {
		return successed;
	}

	public void setSuccessed(String successed) {
		this.successed = successed;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
