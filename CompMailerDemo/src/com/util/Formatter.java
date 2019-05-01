package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Formatter {

	public static java.sql.Date getSqlDate(String strDate){
		java.sql.Date sqlDate=null;
		try {
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate=format.parse(strDate);
			sqlDate=new java.sql.Date(utilDate.getTime());
		}catch(Exception e) {
			System.out.println(e);;
		}
		
		return sqlDate;
	}
	
	public static java.sql.Date getCurrentSqlDte(){
		java.sql.Date sqlDate=null;
		try {
			java.util.Date utilDate=Calendar.getInstance().getTime();
			sqlDate=new java.sql.Date(utilDate.getTime());
		}catch (Exception e) {
			System.out.println(e);
		}
		return sqlDate;
	}
}
