package com.liyunet.common.constant;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 这个类提供各种日期格式的处理函数，我们这个项目标准的日期字符串写法：yyyy-MM-dd 或 yyyy-MM-dd HH:mm
 * 
 * time
 */
public class DateHelper
{

	private static Logger logger = LogManager.getLogger("operateLog");

	public static final String		defaultDateFormat	= "yyyy-MM-dd HH:mm:ss";

	public static final String		defaultDayFormat	= "yyyy-MM-dd";
	
	public static final String		defaultFormat		= "yyyyMMdd";

	// ************************************************************ //
	/**
	 * 通过DATE类型得到当前DATE星期
	 * 
	 * @param date 当前日期
	 * @return int (1~7 , 1 代表星期日, 7 代表星期六)
	 */
	public static int getWeekByDate(Date date)
	{
		int a = 0;
		GregorianCalendar cal = new GregorianCalendar();
		try
		{
			cal.setTime(date);
			a = cal.get(Calendar.DAY_OF_WEEK);
		} catch (Exception ex)
		{
			logger.error("通过Date得到week发生异常", ex);
		}
		return a;
	}

	/**
	 * 过虑掉日期中的时分秒等
	 * 
	 * @param today Date 时间
	 * @return Date 修正时间
	 */
	public static Date getDay(Date now)
	{
		DateFormat df = new SimpleDateFormat(defaultDayFormat);
		String day = df.format(now);
		Date newDay = null;
		try
		{
			newDay = df.parse(day);
		} catch (ParseException e)
		{
			logger.debug("date transfer error", e);
		}
		return newDay;
	}
	
	public static String getDay(Date now,String dayFormat)
	{
		DateFormat df = new SimpleDateFormat(dayFormat);
		return df.format(now);		
	}
	
	/**
	 * convert string formated 'yyyy-MM-dd' to java.sql.Date
	 * 
	 * @param today String 日期
	 * @return java.sql.Date 日期
	 */
	public static java.sql.Date getSqlDay(String today)
	{
		java.sql.Date sqld = java.sql.Date.valueOf(today);
		return sqld;
	}

	/**
	 * 返回当天 string formated 'yyyy-MM-dd' to java.sql.Date
	 * 
	 * @param
	 * @return java.sql.Date 日期
	 */
	public static java.sql.Date getSqlToday()
	{
		return getSqlDay(new Date());
	}

	/**
	 * convert java.util.Date with formated 'yyyy-MM-dd' to java.sql.Date
	 *
	 * @param today java.util.Date 日期
	 * @return java.sql.Date 日期
	 */
	public static java.sql.Date getSqlDay(Date today)
	{
		java.sql.Date sqld = java.sql.Date.valueOf(getFormatedDateStr(today,
			defaultDayFormat));
		return sqld;
	}

	/**
	 * format date to formated date string
	 *
	 * @param now Date 日期
	 * @param format String 日期格式
	 * @return String 日期时间
	 */
	public static String getFormatedDateStr(Date now, String format)
	{
		DateFormat df = new SimpleDateFormat(format);
		String str = (now == null ? null : df.format(now));
		return str;
	}

	/**
	 * @param now
	 * @return 返回格式为 'yyyy-MM-dd HH:mm' 的字符串
	 */
	public static String getFormatedDateStr(Date now)
	{
		DateFormat df = new SimpleDateFormat(defaultDateFormat);
		String str = (now == null ? null : df.format(now));
		return str;
	}

	/**
	 * @param now
	 * @return 返回格式为 'yyyy-MM-dd HH:mm:ss' 的字符串
	 */
	public static Date getFormatedDateByStr(String date)
	{
		DateFormat dateFormat = new SimpleDateFormat(defaultDateFormat);
		Date d = new Date();
		try{
			d = dateFormat.parse(date);
		} catch (ParseException e){
			e.printStackTrace();
		}
		return d;
	}


	/**
	 * @param now
	 * @return 返回格式为 'yyyy-MM-dd' 的字符串
	 */
	public static String getFormatedDayStr(Date now)
	{
		DateFormat df = new SimpleDateFormat(defaultDayFormat);
		String str = (now == null ? null : df.format(now));
		return str;
	}

	/**
	 * @function:将字符串转换为日期
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date getDateByStr(String date, String format)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date d = null;
		try{
			d = dateFormat.parse(date);
		} catch (ParseException e){
			e.printStackTrace();
		}
		return d;
	}

	// ****************************************************** //
	/**
	 * format date to string
	 *
	 * @param today Date 日期
	 * @return String 日期
	 */
	public static String getCNDefaultDate()
	{
		String str = "";
		try
		{
			Calendar cal = Calendar.getInstance();
			str = cal.get(Calendar.YEAR)
						+ "年"
						+ (cal.get(Calendar.MONTH) + 1)
						+ "月"
						+ cal.get(Calendar.DATE)
						+ "日";
			str = str + getCNWeek((cal.get(Calendar.DAY_OF_WEEK) - 1));
		} catch (Exception ex)
		{
			logger.error("得到默认中文日期格式发生异常", ex);
		}
		return str;
	}

	/**
	 * 得到中文星期
	 *
	 * @param a 数字（1,2,3,4,5,6,7）
	 * @return
	 */
	public static String getCNWeek(int a)
	{
		String str_week = "";
		switch (a)
		{
			case 1 :
				str_week = "星期一";
				break;
			case 2 :
				str_week = "星期二";
				break;
			case 3 :
				str_week = "星期三";
				break;
			case 4 :
				str_week = "星期四";
				break;
			case 5 :
				str_week = "星期五";
				break;
			case 6 :
				str_week = "星期六";
				break;
			case 7 :
				str_week = "星期日";
				break;
		}
		return str_week;
	}

	/**
	 * 通过一个yyyy－MM-dd类型的时间得到 日期对象
	 *
	 * @param d1
	 * @return
	 */
	public static Date getDateByStr(String d1)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(defaultDayFormat);
		Date d = new Date();
		if(StringUtils.isBlank(d1))
		{
			return null;
		}
		try
		{
			d = dateFormat.parse(d1);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * @param now
	 * @return 返回格式为 'yyyy-MM-dd' 的字符串
	 */
	public static String getSimpleFormatDate(Date now)
	{
		DateFormat df = new SimpleDateFormat(defaultDayFormat);
		String str = (now == null ? null : df.format(now));
		return str;
	}

	/**
	 * 返回当前时间，格式为“yyyy-mm-dd”
	 *
	 * @author
	 * @return String
	 */
	public static String getSimpleFormatedDateNow()
	{
		Date now = new Date();
		return getFormatedDayStr(now);
	}

	/**
	 * @function:格式为 yyyy-MM-dd HH:mm
	 * @return
	 */
	public static String getStandTime()
	{
		DateFormat dFormat = new SimpleDateFormat(defaultDateFormat);
		return dFormat.format(new Date());
	}

	/**
	 * @function:格式为 yyyy-MM-dd
	 * @return
	 */
	public static String getStandDay10Time()
	{
		DateFormat dFormat = new SimpleDateFormat(defaultDayFormat);
		return dFormat.format(new Date());
	}

	/**
	 * 时间对比,nowTime大于endTime为false;
	 * @author: wcl
	 * @version: 2011-10-20 上午11:24:21
	 * @param nowTime
	 * @param endTime
	 * @return
	 */
	public static boolean compareTime(String nowTime,String endTime){
		Date date1 = getDateByStr(nowTime, defaultDayFormat);
		Date date2 = getDateByStr(endTime, defaultDayFormat);
		if(date1.after(date2)){
			return false;
		}else{
		  	return true;
		}
	}
	 /*
	    * 返回时间第一天的日期 
	    */  
	   public static String getFirstDayOfMonth(Date date) {  
		   Calendar calendar = Calendar.getInstance();  
	       calendar.setTime(date);  
	       calendar.set(Calendar.DATE, calendar.getMinimum(Calendar.DATE));  
	       return DateHelper.getFormatedDayStr(calendar.getTime());
	   } 

	   /**
	    * 
	    * <dd> 方法作用:获取指定日期下一个周的开始日期(本周周日)
	    * <dd> 创建人：王长亮
	    * @param date
	    * @param num
	    * @return
	    */
	   public static String getNextMonday(Date date,int num)   
       {   
		   Calendar cal=Calendar.getInstance();
		   cal.setTime(date);
		   int weedDayNum=cal.get(cal.DAY_OF_WEEK)-1;
		   cal.add(cal.DATE, num-weedDayNum);
		   return DateHelper.getDay(cal.getTime(), "yyyy-MM-dd");
       }
	   
		/**
		 * 
		 * <dd> 方法作用：获取格式为HH:mm的时间之间相差的毫秒数
		 * <dd> 创建人：王长亮
		 * <dd> 创建时间：2015-8-12 上午11:40:04
		 * @param HhMm1
		 * @param HhMm2
		 * @return
		 */
		public static long compareHhMmTime(String HhMm1,String HhMm2){
			String nowTime="2015-01-01 "+HhMm1+":00";
			String endTime="2015-01-01 "+HhMm2+":00";
			Date date1 = getDateByStr(nowTime, defaultDateFormat);
			Date date2 = getDateByStr(endTime, defaultDateFormat);
			long total=date1.getTime()-date2.getTime();
			if(total<0){
				total=0-total;
			}
			return total;
		}

		public static Integer getDayOfWeekByDate(Date date){
			Calendar cal=Calendar.getInstance();
			if(date!=null){
				cal.setTime(date);
			}
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			int tmp = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (0 == tmp) {tmp = 7;} 
			return tmp;
		}
		
	  /**
	    * 
	    * 功能描述：计算两个日期的时间差
	    * 时间：2015-10-20
	    * @author ：wcl
	    * @param date1 传入的日期1
	    * @param date2 传入的日期2
	    * @return date2-date1 的时间差 单位为：天
	    */
		public static int compareDate(String date1,String date2,String format){
		   int result = 0;
		   try{
	           DateFormat sdf = new SimpleDateFormat(format);
	           Date newdate1 = sdf.parse(date1);
	           Date newdate2 = sdf.parse(date2);
	           long time1=newdate1.getTime();
		       long time2=newdate2.getTime();	
		       result=(int) ((time2-time1)/(1000L*60L*60L*24L));			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	       return result;
		}
 
		/**
		 * 通过包含日期字符串，得到日期字符串
		 */
		public static String getFormatStrDate(String str){
			DateFormat df = new SimpleDateFormat(defaultDayFormat);
			try {
				return df.format(df.parse(str));
			} catch (Exception e) {
				logger.error("含日期的字符串格式为日期异常：", e);
				return null;
			}
		}
		
		/**
		 * 获得【指定时间】后或前（时分秒）的时间
		 * @author wcl
		 * @version 创建时间：2012-3-26 下午03:04:18
		 * @param type  1表示小时，2表示分钟，3表示秒钟
		 * @param number 表示在【原来时间基础】上加减的量
		 * @param date 表示指定的时间
		 * @return  表示加减后的时间
		 */
		public static Date getBeginOrEndTime(Integer type,Integer number,Date date)
		{
			//当前时间
	       Calendar cal = Calendar.getInstance();
	        //日期的DATE减去days  就是往前推days 天 同理 +days 就是往后推days天
	       cal.setTime(date);
	       if(type==1){//时
	           cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + number);     	   
	       }else if(type==2){//分
	           cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + number);    	   
	       }else if(type==3){//秒
	           cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + number);     	   
	       }
	       return cal.getTime();
		}	
		
		/**
		 * 获得指定日期的前几天，后几天。
		 * @param days
		 * @return
		 */
		public static String getBeginOrEndDay(Integer days,String date)
		{
			//当前时间
	       Calendar cal = Calendar.getInstance();
	        //日期的DATE减去days  就是往前推days 天 同理 +days 就是往后推days天
	       cal.setTime(getDateByStr(date));
	       cal.set(Calendar.DATE, cal.get(Calendar.DATE) + days); 
	       SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd"); 
	       String str = sf.format(cal.getTime()); 
	       return str;
		}
		/**
		 * 获得指定日期的前几天，后几天。(yyyy-MM-dd)
		 * @param days
		 * @return
		 */
		public static Date getBeginOrEndDay(Integer days,Date date)
		{
			//当前时间
	       Calendar cal = Calendar.getInstance();
	        //日期的DATE减去days  就是往前推days 天 同理 +days 就是往后推days天
	       cal.setTime(date);
	       cal.set(Calendar.DATE, cal.get(Calendar.DATE) + days); 
	       return getDay(cal.getTime());
		}
	  /**
	   * 功能描述：计算两个日期的时间差
	   * @author wcl
	   * @version 创建时间2016-1-12下午05:14:19
	   *@param date1
	   *@param date2
	   *@return
	   */
		public static int compareDate(Date date1,Date date2){
		   int result = 0;
		   try{
	           Date newdate1 = getDay(date1);
	           Date newdate2 =getDay(date2);
	           long time1=newdate1.getTime();
		       long time2=newdate2.getTime();	
		       result=(int) ((time2-time1)/(1000L*60L*60L*24L));			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	       return result;
		}
			
		
		/**
		 * * 获得星期格式日期. 格式：2016-01-12 星期一
		 * @author wcl
		 * @version 创建时间2016-1-12下午03:22:39
		 * @param date
		 * @return 
		 */
		public static String getDayWeekStr(Date date){
			return  getFormatedDayStr(date)+" "+getCNWeek(getDayOfWeekByDate(date));
		}
		
		/**
		 * * 获得星期格式日期. 格式：2016-01-12 星期一
		 * @author wcl
		 * @version 创建时间2016-1-12下午03:22:39
		 * @param dateStr
		 * @return 
		 */
		public static String getDayWeekStr(String dateStr){
			Date date=getDateByStr(dateStr);
			return  dateStr+" "+getCNWeek(getDayOfWeekByDate(date));
		}
		
 
		
	
		 
		/**
		 * 获取某时间段 某星期几的时间集合
		 * @author wcl
		 * @version 创建时间2016-6-18下午03:19:19
		 *@param beginDate
		 *@param endDate
		 *@param weeks
		 *@return
		 */
		public static   List<Date> getValidDates(Date beginDate,Date endDate,String weeks){
			List<Date> list=new ArrayList<Date>();
			Integer dayNum= DateHelper.compareDate(beginDate, endDate);
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(beginDate);
			for(int i=0;i<=dayNum;i++){
				Integer week= DateHelper.getDayOfWeekByDate(cal.getTime());
				if(week==7){week=0;}
				if(DateHelper.compareDate(cal.getTime(), endDate)>=0){
					if(weeks.indexOf(week.toString())!=-1){
						list.add(cal.getTime());
					}
				}else{
					break;
				}
				 cal.add(Calendar.DATE, 1);
			}
			return list;
		}
		
		 
		
		
		/**
		 * 获得两个日期的间隔天数.  endDate-startDate
		 * @author: wcl
		 * @version: 2010-11-26 上午08:51:49
		 * @param startDate
		 * @param endDate
		 * @return
		 */
		public static Long getIntervalDays(Date startDate, Date endDate)
		{
			Calendar fromCalendar = Calendar.getInstance();       
	        fromCalendar.setTime(startDate);       
	        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);       
	        fromCalendar.set(Calendar.MINUTE, 0);       
	        fromCalendar.set(Calendar.SECOND, 0);       
	        fromCalendar.set(Calendar.MILLISECOND, 0);       
	       
	        Calendar toCalendar = Calendar.getInstance();       
	        toCalendar.setTime(endDate);       
	        toCalendar.set(Calendar.HOUR_OF_DAY, 0);       
	        toCalendar.set(Calendar.MINUTE, 0);       
	        toCalendar.set(Calendar.SECOND, 0);       
	        toCalendar.set(Calendar.MILLISECOND, 0);       
	       
	        return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);       
	    }
		
		
		/**
		 * Parse formated date string
		 * 
		 * @param now String 日期时分 'yyyy-MM-dd HH:mm' 或 'yyyy-MM-dd'
		 * @return Date 日期
		 */
		public static Date getDate(String now)
		{
			// 若传过来的字符串是长度为10的类型：yyyy-MM-dd，则先把它改为16位的类型
			if (now != null && now.length() == defaultDayFormat.length())
			{
				now = now + " 00:00:00";
			}
			DateFormat df = new SimpleDateFormat(defaultDateFormat);
			Date date = null;
			try
			{
				date = ((now == null || now.length() != defaultDateFormat.length())
							? null
							: df.parse(now));
			} catch (Exception e)
			{
				logger.debug("date transfer error", e);
			}
			return date;
		}
		
		/**
		 * 根据月份开始时间  获取月份结束时间
		 * @version 创建时间2014-7-11下午03:25:09
		 *@param startDate  :  月份头一天  格式：2014-02-01
		 *@return
		 */
		public  static  Date  getLastDate(String startDate){
			Date  date= DateHelper.getDate(startDate);
			Calendar calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    calendar.add(Calendar.MONTH, 1);    //加一个月
		    calendar.set(Calendar.DATE, 1);        //设置为该月第一天
		    calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
		    return getDay(calendar.getTime()); 	
		}
		
		public static String getDateStrByStr(String date, String format)
		{
			Date date1= DateHelper.getDateByStr(date, format);
			String res= DateHelper.getFormatedDateStr(date1,format);
			return res;
		}	
		
		/**
		 * 两个日期字符串进行比较,前者大返回1,   相等返回0,    前者小返回-1 ,异常返回-2
		 * @param dateStr1
		 * @param dateStr2
		 * @return
		 */
		public static int compareDateStr(String dateStr1,String dateStr2){
			if(StringUtils.isBlank(dateStr1) || StringUtils.isBlank(dateStr2)){
				return -2;
			}
			DateFormat df = new SimpleDateFormat(defaultDateFormat);
			Date date1=null;
			Date date2=null;
			try {
				date1 = df.parse(dateStr1);
				date2 = df.parse(dateStr2);
			} catch (Exception e) {
				return -2;
			}
			try {
				if(date1.getTime()>date2.getTime()){
					return 1;
				}
				if(date1.getTime()<date2.getTime()){
					return -1;
				}
				if(date1.getTime()==date2.getTime()){
					return 0;
				}
			} catch (Exception e) {
				return -2;
			}
			return -2;
		}
		
		public static String formatDateStr(String dateStr,String dateformat){
			if(StringUtils.isBlank(dateStr) || "null".equals(dateStr.trim()))return null;
			String result;
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
				Date date = dateFormat.parse(dateStr);
				result = DateHelper.getFormatedDateStr(date, dateformat);
				result=result.substring(5);
				result=result.substring(0, result.length()-3);
			} catch (Exception e) {
				e.printStackTrace();
				result="";
			}
			return result;
		}

		public static String compareNowTime(Date nowTime,String date,Integer type){
			if(StringUtils.isBlank(date) || date.equals("null"))return "";
			String nowTimeStr= DateHelper.getFormatedDateStr(nowTime, "yyyy-MM-dd HH:mm:ss");
			int num= DateHelper.compareDate1(date,nowTimeStr,type, "yyyy-MM-dd HH:mm:ss");
			if(num>=24*60){
			    return DateHelper.formatDateStr(date, "yyyy-MM-dd HH:mm:ss");
			}else if(num<24*60 && num>=60){
				int hours=(int)(num/60);
				return hours+"小时前";
			}else if(num<60 && num>0){
				return num+"分钟前";
			}else{
				return "刚刚";
			}
		}
		
		/**
		    * 
		    * 功能描述：计算两个日期的时间差
		    * 时间：2015-10-20
		    * @author ：wcl
		    * @param date1 传入的日期1
		    * @param date2 传入的日期2
		    * @param type: 1返回相差天数  2返回相差小时数   3返回相差分钟数   4返回相差秒数   5返回相差毫秒数
		    * @return date2-date1 
		    */
			public static Integer compareDate1(String date1,String date2,int type,String format){
			   int result = 0;
			   try{
		           DateFormat sdf = new SimpleDateFormat(format);
		           Date newdate1 = sdf.parse(date1);
		           Date newdate2 = sdf.parse(date2);
		           long time1=newdate1.getTime();
			       long time2=newdate2.getTime();
			       switch (type) {
						case 1:
							result=(int) ((time2-time1)/(1000L*60L*60L*24L));
							break;
						case 2:
							result=(int) ((time2-time1)/(1000L*60L*60L));
							break;
						case 3:
							result=(int) ((time2-time1)/(1000L*60L));
							break;
						case 4:
							result=(int) ((time2-time1)/1000L);
							break;
						case 5:
							result=(int) (time2-time1);
							break;
						default:
							break;
					}
			       return result;
			   }catch(Exception e){
				   e.printStackTrace();
				   return null;
			   }
			}
}