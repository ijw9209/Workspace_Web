package com.cal.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class Util {

	private String toDates;
	
	public String getToDates() {
		return toDates;
	}
	public void setToDates(String mdate) {
		// mdate(yyyymmddhhmm)를 -> yyyy-MM-dd hh:mm:00
		// -> yyyy년 mm월 dd일 hh시 mm분
		String m = mdate.substring(0,4)+"-"
				  +mdate.substring(4, 6)+"-"
				  +mdate.substring(6, 8)+" "
				  +mdate.substring(8,10)+":"
				  +mdate.substring(10)+":00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		Timestamp tm = Timestamp.valueOf(m);
		toDates = sdf.format(tm);
	
	}			
	
	
	public static String isTwo(String msg) {
		
		return (msg.length()>=2)?"0"+msg:msg;
	}
}
