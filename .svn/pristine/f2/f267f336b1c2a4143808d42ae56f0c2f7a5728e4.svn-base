package com.liyunet.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.liyunet.common.util.DateUtile;

public class Testtime {
public static void main(String[] args) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date parse=null;
	try {
		parse = sdf.parse("2018-11-16 23:59:01");
		System.out.println("数据库的数据：2018-11-16 23:59:01");
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  long etime=parse.getTime()+60000;
  
	Date date=new Date(etime);
	String format = sdf.format(date);
	System.out.println("变换后数据库的数据："+format);
	
	
//		Date addDate = DateUtile.addDate(sdf.parse("2018-11-16 23:59:00"), (long) 0.1);
//		String format = sdf.format(addDate);
//		System.out.println(format);
//		 System.out.println((int)Math.floor(1.6));

	
}
}
