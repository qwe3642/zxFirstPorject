/*
 * 文 件 名:  SystemCommonF.java
 * 版    权:  timesoft Co., Ltd. Copyright 2010-2011,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Sky Ask
 * 修改时间:  2011-9-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.developproject.refexample.system.utils;

import org.apache.commons.collections.functors.ExceptionFactory;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @author Administrator
 * @version [版本号, 2011-9-27]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SystemCommonF {
	
	/**
	 * 随机百分比
	 * @param count  产生几个随机百分比 
	 * @return
	 */
	public List<Integer> randomPercent(Integer count){
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
    
	/**
	 * 从左边开始截取字符串
	 * @param string
	 * @param digint
	 * @return
	 */
	public String left(String string, Integer digint) {
		try {
			return string.substring(0, digint);
		} catch (Exception e) {
			System.out.println("left取值范围超限，返回原值");
			return string;
		}
	}

	/**
	 * 从右边开始截取字符串
	 * @param string
	 * @param digint
	 * @return
	 */
	public String right(String string, Integer digint) {
		try {
			return string.substring(string.length() - digint, string.length());
		} catch (Exception e) {
			System.out.println("right取值范围超限，返回原值");
			return string;
		}
	}

	/**
	 * MID截取字符串
	 * @param string
	 * @param begindigint
	 * @param digint
	 * @return
	 */
	public String mid(String string, Integer begindigint, Integer digint) {
		try {
			return string.substring(begindigint - 1, begindigint + digint - 1);
		} catch (Exception e) {
			System.out.println("mid取值范围超限，返回原值");
			return string;
		}
	}

	/**
	 * 返回MD5加密后的字符串
	 * @param value
	 * @return
	 */
	public String GetStringMD5(String value) {
		return MD5(value);
	}

	public String getCurrTime(String parm) {
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat(parm);
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		return retStrFormatNowDate;
	}
	
	public Date getCurrDate() {
		Date nowTime = new Date(System.currentTimeMillis());
		return nowTime;
	}

	public String getSequence(String value) {
		String seq = "";
		int random = new Random().nextInt(10000);
		Calendar c = Calendar.getInstance();
		seq = "" + c.get(Calendar.YEAR) + c.get(Calendar.MONTH)
				+ c.get(Calendar.DATE) + c.get(Calendar.HOUR_OF_DAY)
				+ c.get(Calendar.MINUTE) + c.get(Calendar.SECOND)
				+ c.get(Calendar.MILLISECOND) + random + value;
		return MD5(seq).toUpperCase();
	}

	/**
	 * 返回MD5加密后的字符串（纯数字）
	 * @param value
	 * @return
	 */
	public String GetStringCodeMD5(String value) {
		return CodeMD5(value);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List removeDuplicateWithOrder(List list) {
		Set set = new TreeSet();
		set.addAll(list);
		list.clear();
		list.addAll(set);
		return list;
	}

	public String getMonthDays(String yy, String mm) {
		int y = Integer.parseInt(yy);
		int m = Integer.parseInt(mm);
		if (m == 4 || m == 6 || m == 9 || m == 11) {
			return "30";
		}
		if (m == 2) {
			if (IfLeap(y)) {
				return "29";
			} else {
				return "28";
			}
		}
		return "31";
	}
	
	public int getMonthDays(String yyyymm) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
		Calendar calendar = new GregorianCalendar(); 
		Date date1 = sdf.parse(yyyymm); 
		calendar.setTime(date1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
	}
	
	/**
	 * 
	 * @param m 字符串日期 YYYY-MM
	 * @param addMonth 正数向后，负数向前
	 * @return YYYY-MM
	 * @throws ParseException
	 */
	public String nextMonth(String m,int addMonth) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");   
        Date date = sdf.parse(m);  
        Calendar calendar = new GregorianCalendar();   
        calendar.setTime(date);   
        calendar.add(calendar.MONTH,addMonth);//把月份往后增加一天.整数往后推,负数往前移动   
        date=calendar.getTime();//这个时间就是月份往后推一天的结果   
        String nextMonth = sdf.format(date); //增加一个月后的日期  
        return nextMonth;
	}
	
	/**
	 * 
	 * @param d 字符串日期 YYYY-MM-dd
	 * @param addDate 正数向后，负数向前
	 * @return YYYY-MM-dd
	 * @throws ParseException
	 */
	public String nextDate(String d,int addDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
        Date date = sdf.parse(d);  
        Calendar calendar = new GregorianCalendar();   
        calendar.setTime(date);   
        calendar.add(calendar.DATE,addDate);//把日期往后增加一天.整数往后推,负数往前移动   
        date=calendar.getTime();//这个时间就是日期往后推一天的结果   
        String nextDate = sdf.format(date); //增加日期后的日期  
        return nextDate;
	}

	/**
	 * 
	 * 是否是闰年
	 * 
	 * @param y
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private static boolean IfLeap(int y) {
		if (y % 400 == 0)
			return true;
		if (y % 100 == 0)
			return false;
		if (y % 4 == 0)
			return true;
		return false;
	}
	
	public String getSystemCurDate(String fmt){
		Date d = new Date();
		DateFormat df = new SimpleDateFormat(fmt);
		String s = df.format(d);
		return s;
	}
	
	public Double round(Double value,int digit){
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
	
	/**
	 * 规范null字符串格式
	 * 
	 * @param para
	 * @return String
	 */
	public static String nullString(String para) {
		if (para == null)
			para = "";
		return para;
	}
	
	public Double String2Double(String value) {
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
		Svalue = nullString(Svalue);
		Svalue = Svalue.trim();
		if (Svalue.equals(""))
			Svalue = "0";
		BigDecimal a = new BigDecimal(Svalue);
		return a.doubleValue();
	}
	
	private final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	private final static String CodeMD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'0', '1', '2', '3', '4', '5' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	/** 
	* 获取十六进制的颜色代码.例如 "#6E36B4" , For HTML , 
	* @return String 
	*/ 
	public static String getRandColorCode(){ 
		String r,g,b; 
		Random random = new Random(); 
		r = Integer.toHexString(random.nextInt(256)).toUpperCase(); 
		g = Integer.toHexString(random.nextInt(256)).toUpperCase(); 
		b = Integer.toHexString(random.nextInt(256)).toUpperCase(); 
		r = r.length()==1 ? "0" + r : r ; 
		g = g.length()==1 ? "0" + g : g ; 
		b = b.length()==1 ? "0" + b : b ; 
		return r+g+b; 
	}

	/**
	 * 判断对象否为空值
	 * @param obj
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isEmpty(Object obj){
		boolean result = false;
		if (obj == null){
			result = true;
		}else{
			if (obj instanceof String){
				result = StringUtil.isEmpty((String)obj, "null","undefined");
			}else if (obj instanceof List<?>){
				result = ArrayListUtil.isEmpty((List<?>)obj);
			}else if (obj.getClass().isArray()){
				result = ArrayUtil.isEmpty((Object[])obj);
			}else if (obj instanceof Map){
				result = MapUtil.isEmpty((Map)obj);
			}
		}
		return result;
	}

	public static boolean isEmpty(String str){
		return isEmpty(str,"null","undefined");
	}

	public static boolean isEmpty(String str,String... regex){
		return StringUtil.isEmpty(str, regex);
	}


	/**
	 * 转换对象数据实现方法
	 * @param obj
	 * @param type
	 * @param format
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T convert(Object obj, Class<?> type, String format){
		return convert(obj, type.getName(), format);
	}
	
	/**
	 * 转换核心实现方法
	 * @param obj
	 * @param type
	 * @param format
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T convert(Object obj, String type, String format){
		Object v = TypeCaseHelper.convert(obj, type, format);
		if (v == null){
			if ("String".equals(type) || "java.lang.String".equals(type)) v = "";
			if ("Boolean".equals(type) || "java.lang.Boolean".equals(type) || "boolean".equals(type) || "java.lang.boolean".equals(type)) v = false;
			if ("Double".equals(type) || "java.lang.Double".equals(type) || "double".equals(type) || "java.lang.double".equals(type)) v =0.00;
			if ("BigDecimal".equals(type) || "java.math.BigDecimal".equals(type)) v = new BigDecimal(0.00);
			if ("Float".equals(type) || "java.lang.Float".equals(type) || "float".equals(type) || "java.lang.float".equals(type)) v = Float.valueOf(0);
			if ("Long".equals(type) || "java.lang.Long".equals(type) || "long".equals(type) || "java.lang.long".equals(type)) v = Long.valueOf(0);
			if ("Integer".equals(type) || "java.lang.Integer".equals(type) || "int".equals(type) || "java.lang.int".equals(type)) v = Integer.valueOf(0);
			if ("Date".equals(type) || "java.sql.Date".equals(type) || "java.util.Date".equals(type)) v = new Date(); 
			if ("Timestamp".equals(type) || "java.sql.Timestamp".equals(type)) v = new Timestamp(new Date().getTime());  
		}
		return (T)v;
	}
	
	/**
	*  应用路径
	* @return String 
	 */
	public static String getApplicationPath() {
		return Constants.applicationPath;
	}
	


	/**
	 * 设置空对象
	 * @return
	 */
	public static <T> T setNullObj(){
		return null;
	}
}

