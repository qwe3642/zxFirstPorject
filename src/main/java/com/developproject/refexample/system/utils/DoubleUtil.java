/*
 * 版    权:  Timesoft Copyright 2014-2016,All rights reserved
 * 文 件 名:  DoubleUtil.java
 * 描       述:  <描述>
 * 修改人:  LUOXWPC
 * 修改时间:  2017-5-25
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.developproject.refexample.system.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Double相关单元
 * @author  LUOXWPC
 * @version  [版本号, 2017-5-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DoubleUtil {

	/**
	 * 加法运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal add(Object a,Object b){
		BigDecimal[] arrays = formatValue(a,b);
		BigDecimal v1 = arrays[0];
		BigDecimal v2 = arrays[1];
		return add(v1,v2);
	}
	
	/**
	 * 加法运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double add(Double a,Double b){
		a = nullDouble(a);
		b = nullDouble(b);
		BigDecimal v = new BigDecimal(a);
		BigDecimal v1 = new BigDecimal(b);
		return add(v,v1).doubleValue();
	}
	
	/**
	 * 加法运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal add(BigDecimal a,BigDecimal b){
		BigDecimal v = a;
		v = v.add(b);
		return v;
	}
	
	/**
	 * 加减乘除值格式化
	 * @param a
	 * @param b
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static BigDecimal[] formatValue(Object a,Object b){
		BigDecimal v1 = new BigDecimal(0.00);
		BigDecimal v2 = new BigDecimal(0.00);
		if (a != null){
			v1 = SystemCommonF.convert(a, BigDecimal.class, "");
			if (v1 == null)	v1 = new BigDecimal(0.00);
		}
		if (b != null){
			v2 = SystemCommonF.convert(b, BigDecimal.class, "");
			if (v2 == null)	v2 = new BigDecimal(0.00);
		}
		return new BigDecimal[]{v1,v2};
	}
	
	/**
	 * 减法运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal subtract(Object a,Object b){
		BigDecimal[] arrays = formatValue(a,b);
		BigDecimal v1 = arrays[0];
		BigDecimal v2 = arrays[1];
		return subtract(v1,v2);
	}
	
	/**
	 * 减法运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double subtract(Double a,Double b){
		BigDecimal v = new BigDecimal(a);
		BigDecimal v1 = new BigDecimal(b);
		return subtract(v,v1).doubleValue();
	}
	/**
	 * 减法运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal a,BigDecimal b){
		BigDecimal v = a;
		v = v.subtract(b);
		return v;
	}
	
	/**
	 * 除运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal divide(Object a,Object b){
		BigDecimal[] arrays = formatValue(a,b);
		BigDecimal v1 = arrays[0];
		BigDecimal v2 = arrays[1];
		return divide(v1,v2,8);
	}
	
	/**
	 * 除运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal divide(Object a,Object b,int scale){
		BigDecimal[] arrays = formatValue(a,b);
		BigDecimal v1 = arrays[0];
		BigDecimal v2 = arrays[1];
		return divide(v1,v2,scale,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 除运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal divide(Object a,Object b,int scale,int roundingMode){
		BigDecimal[] arrays = formatValue(a,b);
		BigDecimal v1 = arrays[0];
		BigDecimal v2 = arrays[1];
		return divide(v1,v2,scale,roundingMode);
	}
	
	/**
	 * 除运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double divide(Double a,Double b){
		BigDecimal v = new BigDecimal(a);
		BigDecimal v1 = new BigDecimal(b);
		return divide(v, v1).doubleValue();
	}
	/**
	 * 除运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal divide(BigDecimal a,BigDecimal b){
		return a.divide(b);
	}
	/**
	 * 除运算四舍五入
	 * @param a
	 * @param b
	 * @param scale 保留小数位
	 * @return
	 */
	public static Double divide(Double a,Double b,int scale){
		BigDecimal v = new BigDecimal(a);
		BigDecimal v1 = new BigDecimal(b);
		return divide(v, v1,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 除运算四舍五入
	 * @param a
	 * @param b
	 * @param scale 保留小数位
	 * @param roundingMode 进位模式参考BigDecimal.ROUND_HALF_UP
	 * @return
	 */
	public static BigDecimal divide(BigDecimal a,BigDecimal b, int scale, int roundingMode){
		if (b.doubleValue() == 0){
			return new BigDecimal(0.00);
		}
		BigDecimal v = a.divide(b,scale,roundingMode);
		return v;
	}
	/**
	 * 乘运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal multiply(Object a,Object b){
		BigDecimal[] arrays = formatValue(a,b);
		BigDecimal v1 = arrays[0];
		BigDecimal v2 = arrays[1];
		return multiply(v1,v2);
	}	
	/**
	 * 乘运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double multiply(Double a,Double b){
		BigDecimal v = new BigDecimal(a);
		BigDecimal v1 = new BigDecimal(b);
		return multiply(v,v1).doubleValue();
	}
	/**
	 * 乘运算
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal a,BigDecimal b){
		BigDecimal v = a.multiply(b);
		return v;
	}

	/**
	 * 判断数据数组是否为空值
	 * @param array
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isEmpty(Double para ){
		return para == null;
	}
	
	/**
	 * 规范null数值格式
	 * 
	 * @param para
	 * @return Double
	 */
	public static Double nullDouble(Double para) {
		if (para == null)
			para = Double.valueOf(0);
		return para;
	}
	
	public static Double StringDouble2(String value) {
		if (value == null || value.equals("")){
			value = "0";
		}
		return Double.parseDouble(value);
	}
	
	/**
	 * Double To String
	 * 
	 * @param Dvalue
	 * @return String
	 */
	public static String DoubleToString(double Dvalue) {

		String Svalue;
		Dvalue = nullDouble(Dvalue);
		BigDecimal a = new BigDecimal(String.valueOf(Dvalue));
		Svalue = a.toPlainString();
		return Svalue;
	}
	
	/**
	 * String To Double
	 * 
	 * @param Svalue
	 * @return double
	 */
	public static double StringToDouble(String Svalue) {
		// Double Dvalue;
		// Dvalue = Double.valueOf(Svalue);
		Svalue = StringUtil.nullString(Svalue);
		Svalue = Svalue.trim();
		if (Svalue.equals(""))
			Svalue = "0";
		BigDecimal a = new BigDecimal(Svalue);
		return a.doubleValue();
	}
	
	/**
	 * Double类型值大小比较
	 * @param v1 值1
	 * @param v2 值2
	 * @param type 比较符：= 等于,< 小于,> 大于,>= 大于等于,<= 小于等于,!= 不等于
	 * @return boolean
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean compareDouble(double d1,double d2,String type){
		boolean result = true;
		int v = Double.compare(d1, d2);
		if (type.equals("=") || type.equals("==")){
			if (v != 0) result = false;
		}
		if (type.equals("<")){
			if (v != -1) result = false;
		}
		if (type.equals(">")){
			if (v != 1) result = false;
		}
		if (type.equals(">=")){
			if (v == -1) result = false;
		}
		if (type.equals("<=")){
			if (v == 1) result = false;
		}
		if (type.equals("!=") || type.equals("<>")){
			if (v == 0) result = false;
		}
		return result;
	}
	
	/**
	 * Double类型值大小比较
	 * @param v1 值1
	 * @param v2 值2
	 * @param type 比较符：= 等于,< 小于,> 大于,>= 大于等于,<= 小于等于,!= 不等于
	 * @return boolean
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean compareDouble1(double v1,double v2,String type){
		boolean result = true;
		if (type.equals("=") || type.equals("==")){
			double v = v1 - v2;
			if (v != 0) result = false;
		}
		if (type.equals("<")){
			double v = v1 - v2;
			if (v >= 0) result = false;
		}
		if (type.equals(">")){
			double v = v1 - v2;
			if (v <= 0) result = false;
		}
		if (type.equals(">=")){
			double v = v1 - v2;
			if (v < 0) result = false;
		}
		if (type.equals("<=")){
			double v = v1 - v2;
			if (v > 0) result = false;
		}
		if (type.equals("!=") || type.equals("<>")){
			double v = v1 - v2;
			if (v == 0) result = false;
		}
		return result;
	}
	
	/**
	 * 取小值
	 * @param a
	 * @param b
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static double min(double a,double b){
		if (compareDouble(a,b,"<")) return a;
		return b;
	}
	
	/**
	 * 取大值
	 * @param a
	 * @param b
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static double max(double a,double b){
		if (compareDouble(a,b,">")) return a;
		return b;
	}
	
	/**
	 * 四舍五入
	 * @param value 值
	 * @param offset 小数位数
	 * @param jdxfz 精度修复值
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static Double round(double value,int offset,double jdxfz){
		if (jdxfz <= 0){
			jdxfz = 0.000001;
		}
		BigDecimal valueDecimal = BigDecimal.valueOf(value);
		valueDecimal = valueDecimal.add(new BigDecimal(jdxfz));
		//计算四舍五入
		valueDecimal = valueDecimal.setScale(offset, RoundingMode.HALF_UP);
		//返回计算结果
		return valueDecimal.doubleValue();
	}
	
	public static Double round(Double value,int digit){
		String pattern = "####";
		if (digit < 0) return value;		
		if (digit > 0){
			pattern = pattern + ".";
			for (int i=0;i<digit;i++){
				pattern = pattern + '0';
			}
		}
		DecimalFormat df = new DecimalFormat(pattern); 
		String s = df.format(value);
		return Double.valueOf(s);
	}
	
	public static int Offset(double value){
		int size = 0;
		String value_s = DoubleToString(value);
		String value_offset = StringUtil.right(value_s, ".");
		while (!StringUtil.isEmpty(value_offset) && StringUtil.right(value_offset, 1).equals("0")) {
			value_offset = StringUtil.left(value_offset, value_offset.length()-1);			
		}
		size = value_offset.length();
		return size;
	}
	
	public static Double convertToDouble(Object obj){
		Double v = 0.00;
		BigDecimal v1 = SystemCommonF.convert(obj,BigDecimal.class,"");
		if (v1 != null){
			v = v1.doubleValue();
		}
		return v;
	}
	
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(0.0179);
		BigDecimal b = new BigDecimal(0.0);
		System.out.println(a.divide(a,4,BigDecimal.ROUND_HALF_UP));
		Double d = 99.8500;
		System.out.println(DoubleUtil.Offset(d));
		
		
	}
}
