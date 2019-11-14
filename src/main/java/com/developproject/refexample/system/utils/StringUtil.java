package com.developproject.refexample.system.utils;

import org.apache.commons.lang.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import sun.io.ByteToCharConverter;
//import sun.io.CharToByteConverter;

/**
 * <p>公共方法类</p>
 * <p>提供字符串处理的实用方法集</p>
 *
 */

public class StringUtil
{
	private static String str;

	public StringUtil()
	{
	}
	public static final String escapeForIntro(String string)
	{
		//			 String str = escapeHTMLTags(string);
		String str = string;
		str = replace(str, "\r\n", "<br>");
		str = replace(str, "\n", "<br>");
		str = replace(str, "'", "\\'");
		return replace(str, "\r", "");

	}
	/**
	 * 得到非空的字符串，若字符串对象为null，则返回""。
	 * @param objValue Object待转换的原字符串对象
	 * @return String 转换后的字符串
	 * */
	public static String getNotNullStr(Object objValue)
	{
		return (objValue == null ? "" : objValue.toString());
	}
	/**
	 * 得到非空的字符串，若字符串为null，则返回""。
	 * @param strValue String待转换的原字符串
	 * @return String 转换后的字符串
	 * */
	public static String getNotNullStr(String strValue)
	{
		return (strValue == null ? "" : strValue.trim());
	}
	/**
	 * 将中文转化成AscII码以便存入数据库
	 * @param s  中文字符串
	 * @return 16进制字符串
	 */
//	public static String ChineseStringToAscii(String s)
//	{
//		try
//		{
//			CharToByteConverter toByte = CharToByteConverter.getConverter("GBK");
//			byte[] orig = toByte.convertAll(s.toCharArray());
//			char[] dest = new char[orig.length];
//			for (int i = 0; i < orig.length; i++)
//				dest[i] = (char) (orig[i] & 0xFF);
//			return new String(dest);
//		}
//		catch (Exception e)
//		{
//			System.out.println(e);
//			return s;
//		}
//	}
	/**
	 * 将UTF-8转化成AscII码以便存入数据库
	 * @param s 中文字符串
	 * @return 16进制字符串
	 */
//	public static String ChineseStringToUTF(String s)
//	{
//		try
//		{
//			CharToByteConverter toByte = CharToByteConverter.getConverter("UTF-8");
//			byte[] orig = toByte.convertAll(s.toCharArray());
//			char[] dest = new char[orig.length];
//			for (int i = 0; i < orig.length; i++)
//				dest[i] = (char) (orig[i] & 0xFF);
//			return new String(dest);
//		}
//		catch (Exception e)
//		{
//			System.out.println(e);
//			return s;
//		}
//	}

	/**
	 * 将AscII字符转换成汉字
	 * @param s -  ASCII字符串
	 * @return 汉字
	 */
//	public static String AsciiToChineseString(String s)
//	{
//		char[] orig = s.toCharArray();
//		byte[] dest = new byte[orig.length];
//		for (int i = 0; i < orig.length; i++)
//			dest[i] = (byte) (orig[i] & 0xFF);
//		try
//		{
//			ByteToCharConverter toChar = ByteToCharConverter.getConverter("GBK");
//			return new String(toChar.convertAll(dest));
//		}
//		catch (Exception e)
//		{
//			System.out.println(e);
//			return s;
//		}
//	}

//	/**
//	 * 使用正则表达式进行字符串内容替换
//	 * @param regularExpression 正则表达式
//	 * @param sub 替换的字符串
//	 * @param input 要替换的字符串
//	 * @return String 替换后的字符串
//	 */
//	public static synchronized String regexReplace(String regularExpression, String sub, String input)
//	{
//		Pattern pattern = PatternFactory.getPattern(regularExpression);
//		Matcher matcher = pattern.matcher(input);
//		StringBuffer sb = new StringBuffer();
//		while (matcher.find())
//		{
//			matcher.appendReplacement(sb, sub);
//		}
//		matcher.appendTail(sb);
//		return sb.toString();
//	}

	/**
	 * 判断一个字符串中是否包含符合正则表达式定义的匹配条件的子串
	 * @param regularExpression - 正则表达式
	 * @param input - 待检查字符串
	 * @return - 若输入字符串中包含符合正则表达式定义的匹配条件的子串，则返回true，否则返回false
	 */
//	//正则表达式匹配判断
//	public static synchronized boolean exist(String regularExpression, String input)
//	{
//		Pattern pattern = PatternFactory.getPattern(regularExpression);
//		Matcher matcher = pattern.matcher(input);
//		return matcher.find();
//	}

	/**
	 * 用"0"补足一个字符串到指定长度
	 * @param str -  源字符串
	 * @param size - 补足后应达到的长度
	 * @return - 补零后的结果
	 */
	public static String fillZero(String str, int size)
	{
		return fillZero(str, size, '0');
	}
	
	/**
	 * 用"0"补足一个字符串到指定长度
	 * @param str -  源字符串
	 * @param size - 补足后应达到的长度
	 * @param tg - 替换字符
	 * @return - 补零后的结果
	 */
	public static String fillZero(String str, int size,char tg)
	{
		String result;
		if (str.length() < size){
			char[] s = new char[size - str.length()];
			for (int i = 0; i < (size - str.length()); i++)
			{
				s[i] = tg;
			}
			result = new String(s) + str;
		}else{
			result = str;
		}
		return result;
	}

	/**
	 * 根据字符串（文件类型或者多行输入类型）获取字符串数组
	 * @param str1 String 输入字符串
	 * @return String[] 返回结果
	 */
	public static String[] getStrArryByString(String str1)
	{
		if (str1.indexOf("\t") > 0)
		{
			for (int i = 0; i < str1.length(); i++)
			{
				if (str1.substring(i, i + 1).equals("\t"))
				{
					str1 = str1.substring(0, i) + " " + str1.substring(i + 1, str1.length());
				}
			}
		}
		StringTokenizer stringTokenizer = new StringTokenizer(str1, "\r\n");
		String[] strId = new String[stringTokenizer.countTokens()];
		int i = 0;
		while (stringTokenizer.hasMoreTokens())
		{
			strId[i] = stringTokenizer.nextToken();
			i++;
		}
		return strId;
	}
	/**
	 * 判断一个字符串是否为 NUll 或为空
	 * @param inStr inStr
	 * @return boolean
	 */
	public static boolean isValid(String inStr)
	{
		if (inStr == null)
		{
			return false;
		}
		else if (inStr.equals(""))
		{
			return false;
		}
		else if (inStr.equals("null"))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * 判断一个字符串是否为 NUll 或为空
	 * @return boolean
	 */	
	public static boolean checkNotNull(String str){
		boolean flag = false;
		
		if(str != null && str.trim().length() != 0)	
			flag = true;
		return flag;
	}
	/**
	 * 获得指定长度的空格
	 * @param spaceNum spaceNum
	 * @return String
	 */
	public static String getStrSpace(int spaceNum)
	{
		return getStrWithSameElement(spaceNum, " ");
	}
	/**
	 * 获得指定长度的字符串
	 * @param num int
	 * @param element String
	 * @return String
	 */
	public static String getStrWithSameElement(int num, String element)
	{
		if (num <= 0)
		{
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++)
		{
			sb.append(element);
		}
		return sb.toString();
	}
	/**
	 * 从右或左加空格
	 * @param strIn String
	 * @param totalLength int
	 * @param isRightAlign boolean
	 * @return String
	 */
	public static String getFillString(String strIn, int totalLength, boolean isRightAlign)
	{
		int spaceLength = 0;
		String spaces = null;
		String strOut = null;

		if (strIn == null)
		{
			strIn = "";
		}

		spaceLength = totalLength - strIn.length();

		if (spaceLength < 0)
		{
			spaceLength = 0;
		}
		spaces = StringUtil.getStrSpace(spaceLength);

		if (isRightAlign)
		{
			strOut = spaces + strIn;
		}
		else
		{
			strOut = strIn + spaces;

		}
		return strOut;
	}
	/**
	 * 以String类型返回错误抛出的堆栈信息
	 * @param t Throwable
	 * @return String
	 */
	public static String getStackTrace(Throwable t)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		t.printStackTrace(pw);
		return sw.toString();
	}
	/**
	 * 转换字符串第一个字符为大写
	 * @param str String
	 * @return String
	 */
	public static String getStrByUpperFirstChar(String str)
	{
		try
		{
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		catch (Exception e)
		{
			return "";
		}

	}
	/**
	 * 转换字符串第一个字符为小写
	 * @param str String
	 * @return String
	 */
	public static String getStrByLowerFirstChar(String str)
	{
		try
		{
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
		catch (Exception e)
		{
			return "";
		}

	}
	/**
	 * 通过字符串转换成相应的整型，并返回。
	 * @param strValue String 待转换的字符串
	 * @return int 转换完成的整型
	 * */
	public static int getStrToInt(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		int iValue = 0;
		try
		{
			iValue = new Integer(strValue.trim()).intValue();
		}
		catch (Exception ex)
		{
			iValue = 0;
		}
		return iValue;
	}

	/**
	 * 通过字符串转换成相应的DOUBLE，并返回。
	 * @param strValue String 待转换的字符串
	 * @return double 转换完成的DOUBLE
	 * */
	public static double getStrToDouble(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		double dValue = 0;
		try
		{
			dValue = Double.parseDouble(strValue.trim());
		}
		catch (Exception ex)
		{
			dValue = 0;
		}
		return dValue;
	}

	/**
	 * 通过字符串转换成相应的短整型，并返回。
	 * @param strValue String 待转换的字符串
	 * @return short 转换完成的短整型
	 * */
	public static short getStrToShort(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		short iValue = 0;
		try
		{
			iValue = new Short(strValue.trim()).shortValue();
		}
		catch (Exception ex)
		{
			iValue = 0;
		}
		return iValue;
	}

	/**
	 * 通过字符串转换成相应的长整型，并返回。
	 * @param strValue String 待转换的字符串
	 * @return long 转换完成的长整型
	 * */
	public static long getStrToLong(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		long lValue = 0;
		try
		{
			lValue = new Long(strValue.trim()).longValue();
		}
		catch (Exception ex)
		{
			lValue = 0;
		}
		return lValue;
	}

	public static String toLengthForEn(String str, int length)
	{
		if (null != str)
		{
			if (str.length() <= length)
			{
				return str;
			}
			else
			{
				str = str.substring(0, length - 2);
				str = str + "..";
				return str;
			}
		}
		else
		{
			return "";
		}
	}

	/**
	  * 降字符串转换成给定长度的字符串，如超出的话截断，并在最后以两个点结尾
	  * @param str String
	  * @param length int
	  * @return String
	  */
	public static String toLengthForIntroduce(String str, int length)
	{
		str = delTag(str);

		byte[] strByte = str.getBytes();
		int byteLength = strByte.length;
		char[] charArray;
		StringBuffer buff = new StringBuffer();
		if (byteLength > (length * 2))
		{
			charArray = str.toCharArray();
			int resultLength = 0;
			for (int i = 0; i < charArray.length; i++)
			{
				resultLength += String.valueOf(charArray[i]).getBytes().length;
				if (resultLength > (length * 2))
				{
					break;
				}
				buff.append(charArray[i]);

			}
			buff.append("..");
			str = buff.toString();
		}

		//		str = replace(str, "'", "\\'");
		str = replace(str, "\"", "\\\"");
		str = replace(str, "，", ",");
		return str;

	}

	public static String delTag(String str)
	{
		str = str + "<>";
		StringBuffer buff = new StringBuffer();
		int start = 0;
		int end = 0;

		while (str.length() > 0)
		{
			start = str.indexOf("<");
			end = str.indexOf(">");
			if (start > 0)
			{
				buff.append(str.substring(0, start));
			}
			if (end > 0 && end <= str.length())
			{
				str = str.substring(end + 1, str.length());
			}
			else
			{
				str = "";
			}

		}
		String result = buff.toString();

		while (result.startsWith(" "))
		{

			result = result.substring(result.indexOf(" ") + 1, result.length());

		}
		return result;

	}

	public static final String replace(String line, String oldString, String newString)
	{
		if (line == null)
		{
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0)
		{
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0)
			{
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;

	}
	//	Replace
	public static String Replace(String source, String oldString, String newString)
	{
		if (source == null)
		{
			return null;
		}
		StringBuffer output = new StringBuffer();
		int lengOfsource = source.length();
		int lengOfold = oldString.length();
		int posStart = 0;
		int pos;
		while ((pos = source.indexOf(oldString, posStart)) >= 0)
		{
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengOfold;
		}
		if (posStart < lengOfsource)
		{
			output.append(source.substring(posStart));
		}
		return output.toString();
	}

	//此函数前台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
	public static String toHtml(String s)
	{
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
		s = Replace(s, "\n", "<br>");
		//s = Replace(s, " ", "&nbsp;");
		s = Replace(s, "'", "&#39;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "%", "％");
		//	s = Replace(s, "&", "&amp;");
		return s;
	}
	//	逆
	public static String unHtml(String s)
	{

		//s = Replace(s, "&lt;", "<");
		//s = Replace(s, "&gt;", ">");
		//		s = Replace(s, "    ", "\t");
		//		s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		//		s = Replace(s, "&nbsp;", " ");
		//		s = Replace(s, "&amp;", "&");
		//		s = Replace(s, "&#39;", "'");
		//		s = Replace(s, "&#92;", "\\");
		//		s = Replace(s, "％", "%");
		return s;
	}

	//	此函数后台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
	public static String toHtmlBack(String s)
	{
		//显示
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
		//		s = Replace(s, "\n", "<br>");
//		s = Replace(s, " ", "&nbsp;");
		//		s = Replace(s, "'", "&#39;");
		//		s = Replace(s, "%", "%");

		return s;
	}
	//	逆
	public static String unHtmlBack(String s)
	{
		s = Replace(s, "&lt;", "<");
		s = Replace(s, "&gt;", ">");
		s = Replace(s, "    ", "\t");
		s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		s = Replace(s, "&nbsp;", " ");
		s = Replace(s, "&amp;", "&");
		s = Replace(s, "&#39;", "'");
		s = Replace(s, "&#92;", "\\");
		s = Replace(s, "％", "%");
		return s;
	}
	/*
	public static String toHtml(String s)
	{
		//显示
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
	//		s = Replace(s, "\n", "<br>");
		s = Replace(s, " ", "&nbsp;");
	//		s = Replace(s, "'", "&#39;");
	//		s = Replace(s, "%", "%");

		return s;
	}

	public static String unHtml(String s)
	{
		s = Replace(s, "&lt;", "<");
		s = Replace(s, "&gt;", ">");
		s = Replace(s, "    ", "\t");
		s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		s = Replace(s, "&nbsp;", " ");
		s = Replace(s, "&amp;", "&");
		s = Replace(s, "&#39;", "'");
		s = Replace(s, "&#92;", "\\");
		s = Replace(s, "％", "%");
		return s;
	}
	*/
	//判断是否含有中文，如果含有中文返回ture
	public static boolean containsChinese(String str) throws UnsupportedEncodingException
	{

		if (str.length() < (str.getBytes()).length)
			return true;

		return false;

		//	  for (int i = 0; i < username.length(); i++) {
		//		String unit=Character.toString(username.charAt(i));
		//		byte[] unitByte=unit.getBytes("GBK");
		////  		((unitByte[0]+256)*256 + (unitByte[1]+256)) <= 0xFEFE)
		//	   if (unitByte.length == 2)
		//		{
		//		  return true;
		//		}
		//	  }
		//	  return false;

	}

	public static String[] split(String str1, String str2)
	{
		return StringUtils.split(str1, str2);
	}

	/**
	 *
	 * <br>
	 * <b>功能：</b>将字符串转成list<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Oct 28, 2011 <br>
	 * @param exp 分割符 如","
	 * @param value
	 * @return
	 */
	public static List<String> StringToList(String value,String exp)
	{	List<String> resultList = new ArrayList<String>();
		String[] vals = split(value , exp);
		for(String val  : vals){
			resultList.add(val);
		}
		return resultList;
	}

	/**
	 *
	 * <br>
	 * <b>功能：</b>数字转换成字符串<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Jul 30, 2011 <br>
	 * @param arrs
	 * @return
	 */
	public static String arrayToString(String[] arrs)
	{
		StringBuffer result= new StringBuffer("");
		if(arrs != null && arrs.length >0){
			for(int i=0;i<arrs.length;i++){

				if(!result.toString().equals("")){
					result.append(",");
				}
				if(arrs[i] != null &&  !"".equals(arrs[i].trim())){
					result.append(arrs[i]);
				}
			}
		}
		return result.toString();
	}



	/**
	 *
	 * <br>
	 * <b>功能：</b>数字转换成字符串<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Jul 30, 2011 <br>
	 * @param arrs
	 * @return
	 */
	public static String arrayToString(Object[] arrs)
	{
		StringBuffer result= new StringBuffer("");
		if(arrs != null && arrs.length >0){
			for(int i=0;i<arrs.length;i++){

				if(!result.toString().equals("")){
					result.append(",");
				}
				if(arrs[i] != null &&  !"".equals(arrs[i].toString().trim())){
					result.append(arrs[i]);
				}
			}
		}
		return result.toString();
	}

	public static String left(String str, int length)
	{
		return StringUtils.left(str, length);
	}

	/**
	 *
	 * <br>
	 * <b>功能：</b>替换回车<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Oct 26, 2011 <br>
	 * @param str
	 * @return
	 */
	public static String replaceHuiche(String str){
	   String after = str.replaceAll("\r\n", "");
	   return after;
	}


	/**
	 * 根据输入的长度截取字符串，单个单词超过输入长度的强制加<BR>换行
	 * @param str 输入的字符串
	 * @param len 输入的长度
	 * @return 处理过后的字符串
	 */
	public static String truncateStr(String str, int len)
	{
		if (str != null && !("").equalsIgnoreCase(str))
		{

			String strs[] = str.split(" ");
			StringBuffer buff = new StringBuffer();
			if (strs.length > 0)
			{
				for (int i = 0; i < strs.length; i++)
				{
					StringBuffer temp = new StringBuffer();
					while (strs[i].length() > len)
					{
						temp.append(strs[i].substring(0, len) + "<BR>");
						strs[i] = strs[i].substring(len);
					}
					temp.append(strs[i]);
					buff.append(temp.toString() + " ");
				}

			}
			return buff.toString();
		}
		else
		{
			return "";
		}
	}

	public static String truncateStr2(String str, int len)
	{
		if (str != null && !("").equalsIgnoreCase(str) && len!=0)
		{
			String strs[] = str.split(" ");

			StringBuffer buff = new StringBuffer();
			for (int i = 0; i < strs.length; i++)
			{
				StringBuffer temp = new StringBuffer();
				String tempstr = "";
				while (strs[i].length() > len)
				{
					tempstr = strs[i].substring(0, len);
					tempstr = tempstr.replaceAll(" ","&nbsp; ");
					tempstr = tempstr.replaceAll("<","&lt; ");
					tempstr = tempstr.replaceAll("\n","<br> ").replaceAll("\"","&quot; ").replaceAll("'","&#39; ");
					tempstr = tempstr + "<br>";
					temp.append(tempstr);

					strs[i] = strs[i].substring(len);
				}
				tempstr = strs[i];
				tempstr = tempstr.replaceAll(" ","&nbsp; ");
				tempstr = tempstr.replaceAll("<","&lt; ");
				tempstr = tempstr.replaceAll("\n","<br> ").replaceAll("\"","&quot; ").replaceAll("'","&#39; ");

				temp.append(tempstr);
				buff.append(temp.toString() + " ");
			}

			if(buff.length() > 0)
				return buff.toString().substring(0,buff.length()-1);
			else
				return str;
		}
		else
		{
			return "";
		}
	}

	/**
	 * 编码转换，从unicode转换为GBK
	 * @return str编码转换后的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String unicodeToGB(String l_S_Source) throws UnsupportedEncodingException
	{
		String l_S_Desc = "";
		if (l_S_Source != null && !l_S_Source.trim().equals(""))
		{
			byte l_b_Proc[] = l_S_Source.getBytes("GBK");
			l_S_Desc = new String(l_b_Proc, "ISO8859_1");
		}
		return l_S_Desc;
	}
	/**
	 * 编码转换，从GBK转换为unicode
	 * @param l_S_Source
	 * @return str 编码转换后的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String GBToUnicode(String l_S_Source) throws UnsupportedEncodingException
	{
		String l_S_Desc = "";
		if (l_S_Source != null && !l_S_Source.trim().equals(""))
		{
			byte l_b_Proc[] = l_S_Source.getBytes("ISO8859_1");
			l_S_Desc = new String(l_b_Proc, "GBK");
		}
		return l_S_Desc;
	}

	/**
	 * Escapes a <code>String</code> according the JavaScript string literal
	 * escaping rules. The resulting string will not be quoted.
	 *
	 * <p>It escapes both <tt>'</tt> and <tt>"</tt>.
	 * In additional it escapes <tt>></tt> as <tt>\></tt> (to avoid
	 * <tt>&lt;/script></tt>). Furthermore, all characters under UCS code point
	 * 0x20, that has no dedicated escape sequence in JavaScript language, will
	 * be replaced with hexadecimal escape (<tt>\x<i>XX</i></tt>).
	 */
	public static String javaScriptStringEnc(String s) {
		int ln = s.length();
		for (int i = 0; i < ln; i++) {
			char c = s.charAt(i);
			if (c == '"' || c == '\'' || c == '\\' || c == '>' || c < 0x20) {
				StringBuffer b = new StringBuffer(ln + 4);
				b.append(s.substring(0, i));
				while (true) {
					if (c == '"') {
						b.append("\\\"");
					} else if (c == '\'') {
						b.append("\\'");
					} else if (c == '\\') {
						b.append("\\\\");
					} else if (c == '>') {
						b.append("\\>");
					} else if (c < 0x20) {
						if (c == '\n') {
							b.append("\\n");
						} else if (c == '\r') {
							b.append("\\r");
						} else if (c == '\f') {
							b.append("\\f");
						} else if (c == '\b') {
							b.append("\\b");
						} else if (c == '\t') {
							b.append("\\t");
						} else {
							b.append("\\x");
							int x = c / 0x10;
							b.append((char)
									(x < 0xA ? x + '0' : x - 0xA + 'A'));
							x = c & 0xF;
							b.append((char)
									(x < 0xA ? x + '0' : x - 0xA + 'A'));
						}
					} else {
						b.append(c);
					}
					i++;
					if (i >= ln) {
						return b.toString();
					}
					c = s.charAt(i);
				}
			} // if has to be escaped
		} // for each characters
		return s;
	}


	private static StringUtil instance = null;

	public static synchronized StringUtil getInstance()
	{
		if (instance == null)
		{
			instance = new StringUtil();
		}
		return instance;
	}
	/**
	 * 将多个连续空格替换为一个,"a  b"-->"a b"
	 * @param src
	 * @return
	 * @author WilliamLau
	 * @desc
	 */
	public static String trimContinuousSpace(String src){
		return src.replaceAll("\\s+", " ");
	}
	public static String replace(String src, String target, String rWith, int maxCount)
	{
		return StringUtils.replace(src, target, rWith, maxCount);
	}

	public static boolean equals(String str1, String str2)
	{
		return StringUtils.equals(str1, str2);
	}

	public static boolean isAlphanumeric(String str)
	{
		return StringUtils.isAlphanumeric(str);
	}

	public static boolean isNumeric(String str)
	{
		return StringUtils.isNumeric(str);
	}

	public static String[] stripAll(String[] strs)
	{
		return StringUtils.stripAll(strs);
	}
//	public static void main(String[] args)
//	{
//		System.out.println(wcsUnescape("#lt;strong#gt;#lt;span style=#quot;color:#e53333;#quot;#gt;1111111111#lt;/span#gt;#lt;/strong#gt;"));
//		//		String testStr = "<input > &    \\r\\n    \\n", newStr;
//		//		newStr = toHtml(testStr);
//		//		System.out.println(testStr);
//		//		System.out.println(newStr);
//		//		System.out.println(unHtml(newStr));
//		//		String aaa = "中文中文中文bcdefghijk";
//
//		//        		String bbb = toLengthForIntroduce(aaa,5);
//		//		System.out.println(bbb);
//		//		Object aa = null;
//		//		String bb = "  aaaa  ";
//
//		try
//		{
//			//			System.out.println(getNotNullStr(aa));
//			//			System.out.println(getNotNullStr(bb));
//			//			System.out.println(containsChinese(aaa));
//			//			System.out.println(containsChinese(aaa));
//			//			System.out.println(containsChinese(bb));
//			//String abc = null;
//			//System.out.println(toLengthForEn(abc, 3));
//			/*String num = "05";
//			if(num.indexOf(".")==-1){
//				num = num + ".00";
//			}*/
////			String str="<p >ksdkks </br> </p>    jkskskeeee <div>  lllll </div>";
////			System.out.println(delTag(str));
//			//System.out.println(toFloatNumber("5.2"));
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//
//	}
//
	public static String toFloatNumber(String num) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		return nf.format(Double.parseDouble(num));
	}
	
	
	public static String toFloatNumber(Double num, int accuracy){
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(accuracy);
		nf.setMinimumFractionDigits(accuracy);
		return nf.format(num);
	}
	
	
	public static String wcsUnescape(String str) {
		str = str.replace("#lt;", "<");
		str = str.replace("#gt;", ">");
		str = str.replace("#quot;", "\"");
		str = str.replace("#amp;amp;", "&");
		str = str.replace("#amp;", "&");
		str = str.replace("#039;", "'");
		return str;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>返回string型的字节数<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Sep 2, 2011 <br>
	 * @param str
	 * @return
	 */
	public static int getByteLength(String str){
		if(str  == null){
			return 0;
		}
		return str.getBytes().length;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>详细的功能描述<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Sep 2, 2011 <br>
	 * @param str 字符
	 * @param limitLen 长度
	 * @return
	 */
	public static String getByteStr(String str,int limitLen){
		StringBuffer sb  = new StringBuffer();
		char[] chars =getNotNullStr(str).toCharArray();
		int len = 0;
		for(char c :  chars){
			len += getByteLength(String.valueOf(c));
			if(len<= limitLen){
				sb.append(c);
			}
		}
		return sb.toString();
		
	}
	
	 /**
     *@param  content 内容
     *@param  length 指定长度。 超出这个长度就截取字符串。
     *@param  padding 超出长度后，尾加上字符，如"..."，可以为空
     *@return 返回结果 如果内容没有超出指定的长度。则返回原字符串，超出长度后则截取到指定的长度的内容
     */
    public static String subStr(String content,Integer length,String padding) throws UnsupportedEncodingException{
    	String str = "";
    	int paddSize =  StringUtils.isBlank(padding)? 0 : padding.length();
    	//如果内容为空，或者小于指定的长度。则返回原内容
    	if(StringUtils.isBlank(content) ||  content.length() <= length){
    		return content;
    	}
    	str = content.substring(0, length-paddSize);
    	if(StringUtils.isNotBlank(padding)){
    		str += padding;
    	}
    	return str;
    }
	
	public static void main(String[] args) {
		System.out.println(getSsndSnXy("2018", 2));
		System.out.println(getSsndSnXy("2018", -2));
		System.out.println(equals("Company Code 705", "Company Code 705"));
		System.out.println(equals("Company Code 705".toUpperCase(), "Company Code 705"));
		System.out.println("Company Code 705".toUpperCase().equals("Company Code 705"));
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		List<String> list1 = new ArrayList<String>();
		list1.add("2");
		list1.add("2");
		list1.add("2");
		list1.add("2");
		list1.add("2");
		list.addAll(list1);
		System.out.println(list);
		List<String> keys = clearStringListDuplicate(list);
		System.out.println(keys);
		
		String regex="3. 702 视同销售-总店 201901.xlsx";
		System.out.println(regex.substring(regex.lastIndexOf('.')+1));
	}
	
	/**
	 * 连接字符串
	 * @param regex
	 * @param arrs 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String jion(String regex,String... arrs){
		StringBuffer result= new StringBuffer("");
		if(arrs != null && arrs.length >0){
			for(int i=0;i<arrs.length;i++){
				if(!result.toString().equals("")){
					result.append(regex);
				}
				if(arrs[i] != null &&  !"".equals(arrs[i].trim())){
					result.append(arrs[i]);
				}
			}
		} 
		return result.toString();
	}
	
	private static String htmlEncode(char c) {
	    switch(c) {
	       case '&':
	           return"&amp;";
	       case '<':
	           return"&lt;";
	       case '>':
	           return"&gt;";
	       case '"':
	           return"&quot;";
	       case ' ':
	           return"&nbsp;";
	       default:
	           return c +"";
	    }
	}
	 
	/** 对传入的字符串str进行Html encode转换 */
	public static String htmlEncode(String str) {
	    if(str ==null || str.trim().equals(""))   return str;
	    StringBuilder encodeStrBuilder = new StringBuilder();
	    for (int i = 0, len = str.length(); i < len; i++) {
	       encodeStrBuilder.append(htmlEncode(str.charAt(i)));
	    }
	    return encodeStrBuilder.toString();
	}

	/**
	 * 字符是否为空值
	 * @param str
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isEmpty(String str)
	{
		if (str == null || "null".equals(str) || "NULL".equals(str))
			return true;
		return "".equals(str.trim());
	}

	/**
	 * 字符是否为空值
	 * @param str
	 * @param regex
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isEmpty(String str,String... regex)
	{
		boolean result = false;
		if (str == null){
			result = true;
		}else{
			if ("".equals(str.trim())){
				result = true;
			}else{
				for (int i = 0; i < regex.length; i++) {
					if (str.toLowerCase().equals(regex[i].toLowerCase())){
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}

	/**
	 * 判断字符是否在指定格式串中存在
	 * @param value
	 * @param format
	 * @return
	 * @see [类、类#方法、类#成员]
	*/
	public static boolean contains(String value,String format){
		if (isEmpty(value)) return false;
		if (isEmpty(format)) return false;
		return format.contains(value);
	}
	
	/**
	 * 空值时返回值默认值信息
	 * @param str 原值
	 * @param defultStr 空值时的默认返回值
	 * @param regx
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String isNull(String str,String defultStr,String... regx){
		String result = str;
		if (isEmpty(str,regx)){
			result = defultStr;
		}
		return result;
	}
	
	/**
	 * 比较字符
	 * @param str1
	 * @param str2
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean equals(String str1, String... str2)
	{
		boolean result = false;
		if (!SystemCommonF.isEmpty(str2)){
			for (String s : str2){
				if (s.equals(str1)){
					result =true;
					break;		
				}
			}
		}
		return result;
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
	
	/**
	 * 从左边开始截取字符串
	 * @param string
	 * @param digint
	 * @return
	 */
	public static String left(String string, Integer digint) {
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
	public static String right(String string, Integer digint) {
		try {
			return string.substring(string.length() - digint, string.length());
		} catch (Exception e) {
			System.out.println("right取值范围超限，返回原值");
			return string;
		}
	}
	
	/**
	 * 从左边开始截取字符串
	 * @param string
	 * @param regex
	 * @return
	 */
	public static String left(String string, String regex) {
		String result = "";
		int index = string.indexOf(regex);
		if (index < 0) return result;
		String[] values = split(string, regex);
		result = values[0];
		return result;
	}
	
	/**
	 * 从右边开始截取字符串
	 * @param string
	 * @param regex
	 * @return
	 */
	public static String right(String string, String regex) {
		String result = "";
		int index = string.indexOf(regex);
		if (index < 0) return result;
		String[] values = split(string, regex);
		result = values[1];
		return result;
	}
	
	/**
	 * MID截取字符串
	 * @param string
	 * @param begindigint
	 * @param digint
	 * @return
	 */
	public static String mid(String string, Integer begindigint, Integer digint) {
		try {
			return string.substring(begindigint - 1, begindigint + digint - 1);
		} catch (Exception e) {
			System.out.println("mid取值范围超限，返回原值");
			return string;
		}
	}
	
	/**
	 * 获取表达式字串内容列表
	 * @param str
	 * @param pattern
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String[] patternStrings(String str,String pattern){
		return patternStrings(str,pattern,true);
	}
	
	/**
	 * 获取表达式字串内容列表
	 * @param str
	 * @param pattern
	 * @param isSingle
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String[] patternStrings(String str,String pattern,boolean isSingle){
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		List<String> keys = new ArrayList<String>();
        while (m.find()) { 
        	String key = m.group();
        	if (isSingle){
	        	if (!keys.contains(key)) {
	        		keys.add(key);
	        	}
        	}else{
        		keys.add(key);
        	}
        }  	
		return ArrayListUtil.listToArray(keys);
	}
	
	/**
	 * 获取表达式字串内容
	 * @param str
	 * @param pattern
	 * @param isSingle
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String patternString(String str,String pattern){
		return patternString(str,pattern,"");
	}
	
	/**
	 * 获取表达式字串内容
	 * @param str
	 * @param pattern
	 * @param isSingle
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String patternString(String str,String pattern,String regx){
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
        String str_s = m.replaceAll(regx);
		return str_s;
	}

	/**
	 * 获取表达式字串内容(参数顺序替换表达式字串内容)
	 * @param str
	 * @param pattern
	 * @param params
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String patternStringByParams(String str,String pattern,Object[] params){
		String format = patternString(str,pattern,"%s");
		return String.format(format, params);
	}
	
    /** 
     * 匹配是否符合正则表达式pattern 匹配返回true 
     * @param str 匹配的字符串 
     * @param pattern 匹配模式 
     * @return boolean 
     */
    public static boolean Regular(String str,String pattern){  
        if(null == str || str.trim().length()<=0)  
            return false;           
        Pattern p = Pattern.compile(pattern);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
    
	/**
	 * 截取字符串
	 * @param str 字符串值
	 * @param start 截取起始位置
	 * @param end 截取终止位置
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String subStr(String str,int start,int end){
		str =getNotNullStr(str);
		return str.substring(start, end);
	}
	
	/**
	 * 截取字符串
	 * @param str 字符串值
	 * @param start 截取起始位置
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String subStr(String str,int start){
		str =getNotNullStr(str);
		return str.substring(start);
	}
	
	/**
	 * 截取字符串从指定位置截取
	 * @param str 字符串值
	 * @param start 截取起始位置
	 * @param limitLen 截取长度
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String subStr_a(String str,int start,int limitLen){
		str =getNotNullStr(str);
		String result = subStr(str, start); 
		return subStr(result, 0,limitLen);
	}
	
	/**
	 * 截取字符串从末位开始截取
	 * @param str 字符串值
	 * @param length 截取长度
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String subStr_b(String str,int limitLen){
		str = getNotNullStr(str);
		int length = str.length();
		int start = length - limitLen;
		return subStr(str, start, length);
	}
	
	/**
	 * String列表去重复项目
	* @param  list
	* @return List<String>  返回类型 
	 */
	public static List<String> clearStringListDuplicate(List<String> list){
		List<String> result = new ArrayList<String>();
		Map<String, String> objMap = new HashMap<String, String>();
		for (String s : list) {
			objMap.put(s, s);
		}
		Set<String> keys = objMap.keySet();
		for (String k : keys) {
			result.add(k);
		}
		return result;
	}
	
	/**
	 * 获取文件名中扩展名
	 * @param filename 字符串值
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getFileNameRegex(String filename){
		return filename.substring(filename.lastIndexOf('.')+1); 
	}
	
	/**
	 * 获取所属年度上年下年
	* @param ssnd
	* @param  i 年度步长变量(上2年 -2,下2年 2)
	* @param 设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getSsndSnXy(String ssnd,Integer i) {
		Integer ssnd_i =  Integer.valueOf(ssnd);
		ssnd_i = ssnd_i + i;
		return String.valueOf(ssnd_i);
	}
	
	public static String Replace(String source, String start_str, String end_str,String new_str){
		String[] values = contents(source, start_str, end_str);
		if (!ArrayUtil.isEmpty(values)){
			for (String s : values){
				source = Replace(source, s, new_str);
			}
		}
		return source;
	}
	
    public static String[] contents(String str,String start_str,String end_str){
    	List<String> values = new ArrayList<String>();
    	int index_s = 0;
    	int index_e = 0;
    	if (!StringUtil.isEmpty(str)){
    		while (contains(start_str,str)) {
				index_s = str.indexOf(start_str);
				index_e = str.indexOf(end_str) + 1;
				String v =  subStr(str, index_s, index_e);
				values.add(v);
				str = replace(str, v, "?", 1);
			}
    	}
    	return ArrayListUtil.listToArray(values, new String[]{});
    }	
}
