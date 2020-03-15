package com.mvc.page;

public class Paging {
	
	public int v_size = 5;		//보여줄 글 개수
	public int p_size = 5;			//페이지 갯수
	public int writing_count = 0;//글 갯수
	public int cur_page = 0;
	public Paging(int v_size, int p_size, int writing_count, int cur_page) {
		super();
		this.v_size = v_size;
		this.p_size = p_size;
		this.writing_count = writing_count;
		this.cur_page = cur_page;
	}
	
	public int getPage_count() {
		return ((writing_count-1) / v_size);
	}
	public int getPage_Start() {
		return((cur_page -1)/p_size)*p_size + 1;
	}
	public int getPage_End() {
		return Math.min(getPage_Start() + p_size -1, getPage_count());
	}
	public boolean isPre() {
		return getPage_Start() != 1;
	}
	public boolean isNext() {
		return getPage_End() <getPage_count();
	}
	public int getWriting_start() {
		return getWriting_End() - v_size + 1;
	}
	public int getWriting_End() {
		return cur_page * v_size;
	}
}
