package com.cal.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {
	
	private String toDates;
	
	public String getToDates() {
		return toDates;
	}
	public void setToDates(String mdate) {
		// mdate(yyyymmddhhmm)를 -> yyyy-MM-dd hh:mm:00
		// -> yyyy년 mm월 dd일 hh시 mm분
		String m = mdate.substring(0, 4)+"-"
				  +mdate.substring(4, 6)+"-"
				  +mdate.substring(6, 8)+" "
				  +mdate.substring(8,10)+":"
				  +mdate.substring(10)+":00";
		//SimpleDateFormat()날짜를 원하는 포맷으로 출력하기(년도 y 월 M 일 d 시 H 분 m)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		//Timestamp : Timestamp타입 변환(date 의 레퍼클래스)
		//valueOf JDBC 타임 스탬프 이스케이프 형식의 문자열을 Timestamp 값으로 변환합니다.
		Timestamp tm = Timestamp.valueOf(m);
		toDates = sdf.format(tm);
	}
	
	//만일 한자리 숫자면 0붙여서 2자리 숫자로 리턴하자
	public static String isTwo(String msg) {
//		int num = Integer.parseInt(msg);
//		String msms = "";
//		if(num / 10 == 0) {
//			msms = "0"+num;
//		}
		return (msg.length() < 2)? "0"+msg : msg;
	}
	
	
}
