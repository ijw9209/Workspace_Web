package com.my.biz;

import java.util.List;

import com.my.dao.MyBoardDao;
import com.my.dto.MyBoardDto;

public class MyBoardBiz {

	MyBoardDao dao = new MyBoardDao();
	public List<MyBoardDto> selectlist(){
		return dao.selectlist();
	}
	public MyBoardDto selectone(int myno) {
		return dao.selectone(myno);
	}
	public int update(MyBoardDto dto) {
		return dao.update(dto);
	}
	public int insert(MyBoardDto dto) {
		return dao.insert(dto);
	}
	public int delete(int myno) {
		return dao.delete(myno);
	}
}
