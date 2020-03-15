package com.my.biz;

import java.util.List;

import com.my.dao.MyBoardDao;
import com.my.dto.MyBoardDto;

public class MyBoardBiz {

	MyBoardDao dao = new MyBoardDao();
	public List<MyBoardDto> selectList(){
		return dao.selectList();
	}
	public boolean multidelete(String[] myno) {
		
		return dao.multidelete(myno);
	}
	public int insert(MyBoardDto dto) {
		return dao.insert(dto);
	}
	public MyBoardDto selectone(int myno) {
		return dao.selectone(myno);
	}
	public int update(MyBoardDto dto) {
		return dao.update(dto);
	}
	public int delete(int myno) {
		return dao.delete(myno);
	}
}
