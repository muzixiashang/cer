package com.liyunet.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 */
public class DateChanged {

    //把中文日期改成英文日期格式--> 针对英文新闻的日期Dec 26,2
    public static String enDate(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(date, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        date=  k[1].toUpperCase()+" "+k[2]+"," + k[5];
        return date;
    }
}
