package com.developproject.refexample.system.utils;


import org.apache.ibatis.reflection.ExceptionUtil;

import java.util.HashMap;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String msg;
	private Object object;
	private String type;
	private Context otherParams = ContextBase.getContext(new HashMap<String, Object>());
	
	public BusinessException() {
		setCode("00000");
	}

	public BusinessException(String message) {
		super(message);
		setCode("00000");
		setMsg(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
		setCode("00000");
	}

	public BusinessException(String msg, Throwable cause) {
		this("00000",msg, cause);
	}
	
	public BusinessException(String code, String msg, Throwable cause) {
		super(msg, cause);
		setCode(code);
		if (SystemCommonF.isEmpty(cause.getMessage())){
			setMsg(msg);
		}else{
			String message = SystemCommonF.nullString(cause.getMessage());
			setMsg(msg + "====" + message);
		}
	}
	/**
	 * <默认构造函数>
	 */
	public BusinessException(String code, String msg) {
		super(msg);
		setCode(code);
		setMsg(msg);
	}

	
	/**
	 * <默认构造函数>
	 * @param object 
	 */
	public BusinessException(String code, String msg, Object object) {
		super(msg);
		setCode(code);
		setMsg(msg);
		this.object = object;
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
		String msgString = "%s:%s";
		this.msg = String.format(msgString,code,msg);
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Context getOtherParams() {
		return otherParams;
	}

	public void setOtherParams(Context otherParams) {
		this.otherParams = otherParams;
	}

	public void addParam(String key,String value){
		otherParams.addParam(key, value);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
