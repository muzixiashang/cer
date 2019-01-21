package com.liyunet.common.util;

import com.liyunet.common.constant.DateHelper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author
 * @version 1.0
 * 字符串方法
 * 
 */
public class StringUtil
{
   /*
    * 获得时间。格式：yyyy-MM-dd HH:mm:ss
    */
   public static String getStandTime()
   {
      DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return dFormat.format(new Date());
   }

   /*
    * 获得时间。格式：yyyy-MM-dd HH:mm:ss
    */
   public static String getStandTime(Date date)
   {
      if(date==null){
         return "";
      }
      DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return dFormat.format(date);
   }
   
   /*
    * 获得服务器的路径分隔符
    */
   public static String getSeparator()
   {
      return File.separator;
   }

   /**
    * 
    * @function: 反斜杠"\"转换为斜杠"/"
    * @param backslash 包括反斜杠的字符串
    * @return
    * @author: 
    */
   public static String backslashToSlash(String backslash) {
   	if (backslash == null) {
   		return null;
   	}
   	return backslash.replace("\\", "/");
   }

   /*
    * 获得当前的ActionPath名称
    */
   public static String getActionPath(HttpServletRequest req)
   {
      String strPath = "";
      strPath = req.getRequestURI();
      int beginIndex = strPath.lastIndexOf("/");
      int endIndex = strPath.lastIndexOf(".");
      if (endIndex > beginIndex)
         // 包含.do
         strPath = strPath.substring(beginIndex + 1, endIndex);
      else
         // 不包含.do
         strPath = strPath.substring(beginIndex + 1, strPath.length());
      return strPath;
   }

   /**
    * 当字符串为null，或者没有值是 返回true,否则返回false
    * 
    * @function:判断一个字符串是否为空
    * @param value
    * @return true/false
    */
   public static boolean isEmpty(String value)
   {
      if (value == null || value.trim().length() < 1)
      {
         return true;
      }
      return false;
   }
   
   public static String arrayToString(String [] s, String seperator)
   {
      StringBuilder returnValue = new StringBuilder();
      
      if (s != null && seperator != null)
      {
         for (int i = 0; i < s.length; i++)
         {
            returnValue.append(s[i]);
            returnValue.append(seperator);
         }
      }
      return returnValue.toString();
   }
   
   
   public static String arrayToStr(String [] s, String seperator)
   {
      StringBuilder returnValue = new StringBuilder();
      
      if (s != null && seperator != null)
      {
         for (int i = 0; i < s.length; i++)
         {
        	 if(i!=0){
        		 returnValue.append(seperator);
        	 }
            returnValue.append(s[i]);
         }
      }
      return returnValue.toString();
   }
   public static String arrayToString(List<String> s, String seperator)
   {
      StringBuilder returnValue = new StringBuilder();
      
      if (s != null && seperator != null)
      {
         for (int i = 0; i < s.size(); i++)
         {
        	 if(i!=0){
        		 returnValue.append(seperator);
        	 }
            returnValue.append(s.get(i));
         }
      }
      return returnValue.toString();
   }
   
   /**
    * 对象数组 转String字符串  格式：1,2,3,4
    * @author 
    * @version 创建时间2016-4-16上午09:40:36
    *@param s
    *@param seperator
    *@return
    */
   public static String arrayObjToStr(Object [] s, String seperator)
   {
      StringBuilder returnValue = new StringBuilder();
      
      if (s != null && seperator != null)
      {
         for (int i = 0; i < s.length; i++)
         {
        	 if(i!=0){
        		 returnValue.append(seperator);
        	 }
            returnValue.append(s[i].toString());
         }
      }
      return returnValue.toString();
   }
   
   
   public  static String[] stringToArray(String str,String flag)
   {
	   return isEmpty(str)?null:str.split(flag);
   }
   
   public  static List stringToList(String str,String flag)
   {
	    if(isEmpty(str)) return null;
	    String[] strArr = str.split(",");   
	   List list = new ArrayList();   
		for(int i = 0;i < strArr.length; i++)   
		{   
		    list.add(strArr[i].trim());   
		}   
	   return list;
   }
   
   
   
   /**
    * @function: 判断str是否包含在List<string>中
    */
   public static boolean contains(List<String> lis,String str){
      for(String item:lis){
         if(str.equals(item)){
           return true;
         }
      }
      return false;
   }
   
   /**
    * @function:  把特殊字符替换成全角 
    */  
   public static  String  escapeChar(String   input){   
      if(input == null || input.length()==0)   
       return   input; 
      
      String regEx=">|<|&|\"|'"; //表示a或f 
      Pattern p=Pattern.compile(regEx); 
      Matcher m=p.matcher(input); 
      if(!m.find()){
         return input;
      } 
      
      
        StringBuffer buf = new StringBuffer(input.length()+6);   
        char ch=' '; 
        
        for(int i=0;i<input.length();i++){
            ch = input.charAt(i);   
            switch(ch){
            case '>':   
               buf.append("＞");   
               break;
            case '<':
               buf.append("＜");   
               break;
            case '&':
               buf.append("＆");   
               break;
            case '"':
               buf.append("＂");   
               break;
            case '\'':
               buf.append("＇");   
               break;
            default :
               buf.append(ch);
            }
           
        }   
        return   buf.toString();   
    }   
    
   public static String MyHtmlEncode(String strContent)
   {
      strContent=strContent.replace("&","＆");
      strContent=strContent.replace("'","＇");
      strContent=strContent.replace("<","＜");
      strContent=strContent.replace(">","＞");
      strContent=strContent.replace("chr(60)","＜");
      strContent=strContent.replace("chr(37)","＞");
      strContent=strContent.replace("\"","＂");
      strContent=strContent.replace(";","；");
      strContent=strContent.replace("\r\n","<br>");
      strContent=strContent.replace(" ","　");
      return strContent;
   }
   
   /**
    * @function: 过滤掉换行符号,用于textarea
    * @return
    * @author: 
    */
   public static String filterEnter(String src){
	 return javaToJson(src);
   } 
   
   /**
    * @function: 得到当前系统时间序列
    * @return 返回"yyMMddHHmmssSS"格式字符串
    */
   public static String getTimeString()
   {
       DateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
       return dFormat.format(new Date());        
   }
   /**
    * @function: 随机数
    * @param length
    * @return
    */
   public static String getRandomChar(int length) {
	    char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	    Random random = new Random();
	    StringBuffer buffer = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	      buffer.append(chr[random.nextInt(62)]);
	    }
	    return buffer.toString();
	  }
   
   public static String getRandomUppercase(int length) {
	    char[] chr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	    Random random = new Random();
	    StringBuffer buffer = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	      buffer.append(chr[random.nextInt(26)]);
	    }
	    return buffer.toString();
	  }
   
   /**
    * @function: 随机数字
    * @param length
    * @return
    */
   public static String getRandomCharNum(int length) {
	    char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

	    Random random = new Random();
	    StringBuffer buffer = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	      buffer.append(chr[random.nextInt(10)]);
	    }
	    return buffer.toString();
	  }
   
   /**
    * @function: 生成10位随机数
    * @return
    */
   public static String getRandomChar() {
	    return getRandomChar(10);
   }
   
   /**
    * @function: 随机数(数字+小写字母)
    * @param length
    * @return
    */
   public static String getRandomCharNumAndLowerLetters(int length) {
	    char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	    Random random = new Random();
	    StringBuffer buffer = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	      buffer.append(chr[random.nextInt(36)]);
	    }
	    return buffer.toString();
	}
   
   /**
    * 获得当前时间字符串.格式:yyMMddHHmmss.
    * @author: guanshengjun@163.com
    * @version: 2011-8-5 下午02:22:23
    * @return
    */
   public static String getDateTime(){
	   DateFormat dFormat = new SimpleDateFormat("yyMMddHHmmss");
       return dFormat.format(new Date());
   }
   
   /**
    * 票纸批次号.
    * @author: guanshengjun@163.com
    * @version: 2011-8-5 下午02:18:23
    * @return
    */
   public static String getSeqNumber(){
	   int num = 1;
	   double random = Math.random();
	   if(random < 0.1) {
		   random = random + 0.1;
	   }
	   num = (int)(random * 10000);
	   return getDateTime()+num;
	}
   
   /**
    * 生成四位随机数.
    * @author: guanshengjun@163.com
    * @version: 2012-2-2 下午07:47:10
    * @return
    */
   public static String getRandom4(){
	   int num = 1;
	   double random = Math.random();
	   if(random < 0.1) {
		   random = random + 0.1;
	   }
	   num = (int)(random * 10000);
	   return String.valueOf(num);
	}
   
 
   
   
  
   /**
    * 流水号(时间+6位随机).
    * author:孙晓达 specter825@gmail.com
    * 2011-9-6  下午05:22:24
    */
   public static String getSerialNumber() {
	   String number = getDateTime();
	   return number + getRandomCharNum(6);
   }
   
   /**
    * 发送短信时的信息id(唯一).
    * @author: guanshengjun@163.com
    * @version: 2011-9-14 下午04:45:20
    * @return
    */
   public static Long getSmsSerialNumber(){
	   return Long.valueOf(getSerialNumber());
   }
   
   /**
	 * 是否是合法手机号.
	 * @author: guanshengjun@163.com
	 * @version: 2011-9-28 上午09:24:11
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile){
		String regExp = "^[1][3-8]\\d{9}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(mobile);
		return m.find();
	}
	
	/**
	 * 组装json字串串转义特殊字符.
	 * @author: guanshengjun@163.com
	 * @version: 2011-11-12 下午02:57:25
	 * @param msg
	 * @return
	 */
	public static String javaToJson(String msg){
		if(StringUtils.isBlank(msg))
			return "";
		if(msg.indexOf("'") != -1){//单引号转义.
		    msg = msg.replaceAll("'", "\\'");
		}
		if(msg.indexOf("\"")!=-1){//双引号转义.
		    msg = msg.replaceAll("\"", "\\\"");
		}
		if(msg.indexOf("\r\n") != -1){//回车换行转换.
		    msg = msg.replaceAll("\r\n", "");
		}
		if(msg.indexOf("\n")!=-1){//换行转换.
		    msg = msg.replaceAll("\n", "");
		}
		return msg;
	}
	/**
	 * 2011-11-19  下午04:15:22
	 * @param source　原字符窜　abcdefg
	 * @param offer　开始 2
	 * @param len　长度 2
	 * @param cover　覆盖字字符 *
	 * @return ab**efg
	 */
	public static String replace(String source, Integer offer, Integer len, String cover) {
		if(source.length() < offer) return source;
		String c = "";
		for(int i=0; i<len; i++) {c += cover;}
		String s = source.substring(0, offer);
		if(source.length() < (offer + len)) {
			return (s + c).substring(0, source.length());
		}
		String e = source.substring(offer + len, source.length());
		return s + c + e;
	}
	
	/**
	 * 字符串替换.(前后预留len长度,其它位置以cover替代)
	 * @version: 2012-8-1 下午01:02:44
	 * @param source 原字符串.
	 * @param len 前后不变长度.
	 * @param cover 替代字符.
	 * @return
	 */
	public static String replace(String source, Integer len, String cover) {
		int s_len = len*2;
		if(source.length() < s_len) return source;
		String c = "";
		for(int i=s_len; i<source.length(); i++) {c += cover;}
		String s = source.substring(0, len);
		if(source.length() < s_len) {
			return (s + c).substring(0, source.length());
		}
		String e = source.substring(source.length()-len);
		return s + c + e;
	}
/**
 * 判断某字符串或字符  是否存在数组中
 * @version: 创建时间：2012-5-7 上午11:29:11
 * @param strs
 * @param s
 * @return
 */
	 public static boolean isHave(String[] strs,String s){
		  /*此方法有两个参数，第一个是要查找的字符串数组，第二个是要查找的字符或字符串
		   * */
		  for(int i=0;i<strs.length;i++){
		   if(strs[i].indexOf(s)!=-1){//循环查找字符串数组中的每个字符串中是否包含所有查找的内容
		    return true;//查找到了就返回真，不在继续查询
		   }
		  }
		  return false;//没找到返回false
		 }

	   public static String taoBaoEncryptMD5(String data,String encode) throws IOException {
		   String result = "";
		      try {
		         MessageDigest md = MessageDigest.getInstance("MD5");
		         byte[] b  = md.digest(data.getBytes(encode));
				 for (int i=0; i < b.length; i++) {
				    result += Integer.toString((b[i]&0xff) + 0x100, 16).substring(1);
				 }
		      } catch (GeneralSecurityException gse) {
		         throw new IOException(gse.getMessage());
		      }
		   return result;
		}
	   
	   public static String toMD5(byte[] source) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");			
			md.update(source);
			StringBuffer buf = new StringBuffer();
			for (byte b : md.digest())
				buf.append(String.format("%02x", b & 0xff));
			return buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	   

	   /**
	    * 字符串分组方法
	    * @version 创建时间：2012-12-3 上午10:49:08
	    * @param str	原字符串		例如：“sdfsdf1122458ccc1522”
	    * @param reg	正则表达式	\\d+
	    * @return	 List集合【1122458,1522】
	    */
	   public static List<String> strGroupByReg(String str,String reg){
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(str);
			List<String> args=new ArrayList<String>();
			int i=0;
			while(matcher.find()) { 
				args.add(i,matcher.group());  
				i++;
			}
			return args;
	   }
	   
	   /**
	    * 获得in sql的占位符字符串
	    * @version 创建时间：2013-1-21 下午05:16:37
	    * @param paramSize  占位符的个数
	    * @return	（?,?,?,?,?）
	    */
	   public static String getInPlaceholderSql(Integer paramSize){
		   if(paramSize==null||paramSize<1)return "";
		   String insql="(?";
		   for (int i = 1; i < paramSize; i++) {
			   insql+=",?";
		   }
		   insql+=")";
		   return insql;
	   }
	   
	   /**
	    * 字符串前追加 0 
	    * @version 创建时间：2013-12-23 下午04:56:49
	    * @param num	追加0后，字符串的总长度
	    * @param str 原字符串.
	    * @return
	    */
	   public static String appendEnd0ToStr(Integer num,String str){
			 try {
				   for(int i=str.length();i<num;i++){
					   str="0"+str;
				   }			
			} catch (Exception e) {	}
		   return str;
	   }
	   
	   /**
	    * 获得当前时间截字符串.格式：yyyyMMddHHmmss.
	    * @author: guanshengjun@163.com
	    * @version: 2012-7-8 下午01:34:18
	    * @return
	    */
	   public static String getStringDateTime(){
		   DateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	       return dFormat.format(new Date());
	   }	
	   
	   /**
	    * 
	    * @createTime：2015-6-25 下午01:44:28
	    * @param fileType  例值：.rar  .jpg
	    * @return  例值 091213_3581.jpg (时分秒_4位随机数.jpg)
	    */
	   public static String getDateStrFileName(String fileType){
		   DateFormat dFormat = new SimpleDateFormat("HHmmss");
	       return dFormat.format(new Date())+"_"+StringUtil.getRandomCharNum(4)+fileType;		   
	   }
	   
		public static String getCNWeek(int a)
		{
			String str_week = "";
			switch (a)
			{
				case 1 :
					str_week = " 星期一";
					break;
				case 2 :
					str_week = " 星期二";
					break;
				case 3 :
					str_week = " 星期三";
					break;
				case 4 :
					str_week = " 星期四";
					break;
				case 5 :
					str_week = " 星期五";
					break;
				case 6 :
					str_week = " 星期六";
					break;
				case 7 :
					str_week = " 星期日";
					break;
			}
			return str_week;
		}
		
		public final static String[] weekName={"日","一","二","三","四","五","六"};		
	   
		public static String getCNDefaultDate()
		{
			String str = "时间:";
			try
			{
				Calendar cal = Calendar.getInstance();
				str += cal.get(Calendar.YEAR)
							+ "年"
							+ (cal.get(Calendar.MONTH) + 1)
							+ "月"
							+ cal.get(Calendar.DATE)
							+ "日";
				str = str + getCNWeek((cal.get(Calendar.DAY_OF_WEEK) - 1));
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
			return str;
		}
		
		/**
		 * 
		 * 功能描述：去除字符串首部为"0"字符
		 * 时间：2015-7-06
		 * @author ：wangchangliang
		 * @param str 传入需要转换的字符串
		 * @return 转换后的字符串
		 */
		public static String removeZero(String str){   
		   	char  ch;  
		   	String result = "";
		   	if(str != null && str.trim().length()>0 && !str.trim().equalsIgnoreCase("null")){				
		   		try{			
					for(int i=0;i<str.length();i++){
						ch = str.charAt(i);
						if(ch != '0'){						
							result = str.substring(i);
							break;
						}
					}
				}catch(Exception e){
					result = "";
				}	
			}else{
				result = "";
			}
		   	return result;
				
		}
		
		/**
		 * 
		 * 功能描述：金额字符串转换：单位分转成单元
		 * 时间：2015-7-06
		 * @author ：wangchangliang
		 * @param str 传入需要转换的金额字符串
		 * @return 转换后的金额字符串
		 */	
		public static String fenToYuan(Object o) {
			if(o == null)
				return "0.00";
			String s = o.toString();
			int len = -1;	
			StringBuilder sb = new StringBuilder();
			if (s != null && s.trim().length()>0 && !s.equalsIgnoreCase("null")){
				s = removeZero(s);
				if (s != null && s.trim().length()>0 && !s.equalsIgnoreCase("null")){
					len = s.length();
					int tmp = s.indexOf("-");
					if(tmp>=0){
						if(len==2){
							sb.append("-0.0").append(s.substring(1));
						}else if(len==3){
							sb.append("-0.").append(s.substring(1));
						}else{
							sb.append(s.substring(0, len-2)).append(".").append(s.substring(len-2));				
						}						
					}else{
						if(len==1){
							sb.append("0.0").append(s);
						}else if(len==2){
							sb.append("0.").append(s);
						}else{
							sb.append(s.substring(0, len-2)).append(".").append(s.substring(len-2));				
						}					
					}
				}else{
					sb.append("0.00");
				}
			}else{
				sb.append("0.00");
			}
			return sb.toString();		
		}
		/**
		 * 
		 * 功能描述：金额字符串转换：单位元转成单分
		 * 时间：2015-7-06
		 * @author ：wangchangliang
		 * @param str 传入需要转换的金额字符串
		 * @return 转换后的金额字符串
		 */		
		public static String yuanToFen(Object o) {
			if(o == null)
				return "0";
			String s = o.toString();
			int posIndex = -1;
			String str = "";
			StringBuilder sb = new StringBuilder();
			if (s != null && s.trim().length()>0 && !s.equalsIgnoreCase("null")){
				posIndex = s.indexOf(".");
				if(posIndex>0){
					int len = s.length();
				    if(len == posIndex+1){
						str = s.substring(0,posIndex);
						if(str == "0"){
					    	str = "";
					    }
					    sb.append(str).append("00");
					}else if(len == posIndex+2){
					    str = s.substring(0,posIndex);
					    if(str == "0"){
					    	str = "";
					    }
					    sb.append(str).append(s.substring(posIndex+1,posIndex+2)).append("0");
					}else if(len == posIndex+3){
						str = s.substring(0,posIndex);
						if(str == "0"){
					    	str = "";
					    }
						sb.append(str).append(s.substring(posIndex+1,posIndex+3));
					}else{
						str = s.substring(0,posIndex);
						if(str == "0"){
					    	str = "";
					    }
						sb.append(str).append(s.substring(posIndex+1,posIndex+3));
					}
				}else{
					sb.append(s).append("00");
				}
			}else{
				sb.append("0");
			}
			str = removeZero(sb.toString());
			if(str != null && str.trim().length()>0 && !str.trim().equalsIgnoreCase("null")){
				return str;
			}else{
				return "0";
			}
		}
		
		//内存容量单位换算，B转MB
		public static Float bytes2kb(long bytes) {  
			     BigDecimal filesize = new BigDecimal(bytes);  
			     BigDecimal megabyte = new BigDecimal(1024*1000);  
			     Float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
			     return returnValue;  
		}
		/**
		 * sql查询，获得列特定别名.
		 * @createTime：2015-7-21 上午10:14:07
		 * @param size
		 * @return
		 */
		public static String[] getAliasArrary(Integer size){
			String[] columnAlias=new String[size];
			for (int i = 0; i <size; i++) {
				columnAlias[i]="colAlias"+i;
			}
			return columnAlias;
		}
		
		/**
		 * 生成30位流水号（10机构号+14时间+6位随机数字）
		 * @version 创建时间：2012-7-6 下午02:18:22
		 * @return
		 */
		public static String getRunningNum(String organization){
			String runNum=organization;
			runNum+=DateHelper.getFormatedDateStr(new Date(), "yyyyMMddHHmmss");
			runNum+=StringUtil.getRandomCharNum(6);
			return runNum;
		} 
		
		public static List getDateList(Date startDate,Integer dateNum){
			List<String> list=new ArrayList<String>();
			Calendar cal=Calendar.getInstance();
			cal.setTime(startDate);
			for (int i = 0; i < dateNum; i++) {
				cal.set(Calendar.DATE, cal.get(Calendar.DATE)+(i>0?1:0));
				list.add(DateHelper.getFormatedDayStr(cal.getTime()));
			}
			return list;
		}
		
		//字符串数组去重
		public static String[] stringArrayUnique(String[] ary) {   
		   List<String> list = new ArrayList<String>();   
		   for(int i = 0; ary!=null && i < ary.length; i++) {   
		       if(!list.contains(ary[i])) {   
		          list.add(ary[i]);   
		       }   
		    }   
		   return (String[])list.toArray(new String[list.size()]);   
		} 
		
		//字符串数组去重
		public static Integer[] stringArrayToInteger(String[] ary) {   
		   List<Integer> list = new ArrayList<Integer>();   
		   for(int i = 0; ary!=null && i < ary.length; i++) {   
		       if(!list.contains(ary[i])) {   
		    	   try {
		    		   list.add(Integer.parseInt(ary[i]));  
					} catch (Exception e) {
						e.getStackTrace();
					}		          
		       }   
		    }   
		   return (Integer[])list.toArray(new Integer[list.size()]);   
		} 
		
		/**
		 * Exception输出的堆栈信息转成字符串
		 * @param e
		 * @return
		 */
		public static String exceptionToStr(Exception e){
			try {
				StringWriter sw = new StringWriter();    
				PrintWriter pw = new PrintWriter(sw);    
				e.printStackTrace(pw);    
				String msg=sw.toString(); 
				return msg;
			} catch (Exception e1) {
				e1.printStackTrace();
				return "exceptionToStr error";
			}
		}
		
		/*
		 * 计算两个数的百分比
		 */
		 public   static  String percent( double  p1,  double  p2)   
		  {
		   String str;
		   double  p3  =  p1  /  p2;
		   NumberFormat nf  =  NumberFormat.getPercentInstance();
		   nf.setMinimumFractionDigits(0);
		   str  =  nf.format(p3);
		   return  str;
		  }  
		 
		 /**
		  * 获取指定范围内的随机数
		  * @param min
		  * @param max
		  * @return
		  */
		 public static int getRamdomStr(int min,int max){
		     Random random = new Random();
		     int s = random.nextInt(max)%(max-min+1) + min;
		     return s;
		 }
}
