package org.spring.springboot.Utile;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtile {
	
	public static Timestamp getWeekStartDate(){
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(new Date());
		
		int d = 0;
		//System.out.println(cal.get(Calendar.DAY_OF_WEEK));
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
        	
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        // 所在周开始日期

		Date date = cal.getTime();
		return new Timestamp(date.getTime());
	}
	
	public static Timestamp getMonthStartDate(){
		Calendar cc = Calendar.getInstance();    
		cc.add(Calendar.MONTH, 0);
		cc.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		return new Timestamp(cc.getTimeInMillis());  
	}
	
	//java获取当前月的天数
	public static int getDayOfMonth(){
	   Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
	   int day=aCalendar.getActualMaximum(Calendar.DATE);
	   return day;
	}
	
	public static Timestamp addOneDate(Timestamp tt){
	  Calendar cc = Calendar.getInstance();
	  cc.setTime(tt);
	  cc.add(Calendar.DAY_OF_MONTH, 1);
	  return new Timestamp(cc.getTimeInMillis());
	}
	
	public static Timestamp subOneDate(Timestamp tt){
		  Calendar cc = Calendar.getInstance();
		  cc.setTime(tt);
		  cc.add(Calendar.DAY_OF_MONTH, -1);
		  return new Timestamp(cc.getTimeInMillis());  
	}
	public static void main(String[] args) {
		System.out.println(getWeekStartDate());
	}
	
	public static java.sql.Date get(String strDate){
	        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
	        Date date = new Date();
	        try {
	            date=simpleDateFormat.parse(strDate);
	        } catch(ParseException px) {
	            px.printStackTrace();
	        }
	        java.sql.Date d = new java.sql.Date(date.getTime());  
	        return d;
	}
}
