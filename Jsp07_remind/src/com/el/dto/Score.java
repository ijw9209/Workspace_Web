package com.el.dto;

public class Score {
	
	private String name;
	private int kor;
	private int eng;
	private int math;
	
	
	
	public Score() {
		super();
	}
	public Score(String name, int kor, int eng, int math) {
		super();
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getSum() {
		return kor + math + eng;
	}
	public double getAvg() {
		return Math.floor(getSum() / 3) ;
	}
	public String getGrade() {
		
		switch((int)getAvg() / 10) {
		case 10 :
		case 9 :
			return "A";
		case 8 :
			return "B";
		case 7 :
			return "C";
		case 6 :
			return "D";
		default:
			return "F";
		}
	}
}