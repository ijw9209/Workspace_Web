package com.answer.dto;

public class LoginDto {

	private int myno;
	private String myid;
	private String mypw;
	private String myname;
	private String myphone;
	private String myenabled;
	private String myrole;
	public LoginDto() {
		super();
	}
	public LoginDto(int myno, String myid, String mypw, String myname, String myphone, String myenabled,
			String myrole) {
		super();
		this.myno = myno;
		this.myid = myid;
		this.mypw = mypw;
		this.myname = myname;
		this.myphone = myphone;
		this.myenabled = myenabled;
		this.myrole = myrole;
	}
	public int getMyno() {
		return myno;
	}
	public void setMyno(int myno) {
		this.myno = myno;
	}
	public String getMyid() {
		return myid;
	}
	public void setMyid(String myid) {
		this.myid = myid;
	}
	public String getMypw() {
		return mypw;
	}
	public void setMypw(String mypw) {
		this.mypw = mypw;
	}
	public String getMyname() {
		return myname;
	}
	public void setMyname(String myname) {
		this.myname = myname;
	}
	public String getMyphone() {
		return myphone;
	}
	public void setMyphone(String myphone) {
		this.myphone = myphone;
	}
	public String getMyenabled() {
		return myenabled;
	}
	public void setMyenabled(String myenabled) {
		this.myenabled = myenabled;
	}
	public String getMyrole() {
		return myrole;
	}
	public void setMyrole(String myrole) {
		this.myrole = myrole;
	}
	
	
}
