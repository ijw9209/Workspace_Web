package com.answer.dto;

import java.sql.Date;

public class AnswerBoardDto {
	private int rownum;
	private int boardno;
	private int groupno;
	private int groupsq;
	private int titletab;
	private String delflag;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	public AnswerBoardDto() {
		super();
	}

	public AnswerBoardDto(int rownum, int boardno, int groupno, int groupsq, int titletab, String delflag, String title,
			String content, String writer, Date regdate) {
		super();
		this.rownum = rownum;
		this.boardno = boardno;
		this.groupno = groupno;
		this.groupsq = groupsq;
		this.titletab = titletab;
		this.delflag = delflag;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public int getGroupno() {
		return groupno;
	}
	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}
	public int getGroupsq() {
		return groupsq;
	}
	public void setGroupsq(int groupsq) {
		this.groupsq = groupsq;
	}
	public int getTitletab() {
		return titletab;
	}
	public void setTitletab(int titletab) {
		this.titletab = titletab;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
