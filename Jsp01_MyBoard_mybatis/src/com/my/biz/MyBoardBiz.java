package com.my.biz;

import java.util.List;

import com.my.dao.MyBoardDao;
import com.my.dto.MyBoardDto;

public class MyBoardBiz {
	
	MyBoardDao dao = new MyBoardDao();
	public List<MyBoardDto> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	public MyBoardDto selectone(int seq) {
		// TODO Auto-generated method stub
		return dao.selectone(seq);
	}

	public int insert(MyBoardDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	public int update(MyBoardDto dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	public int delete(int seq) {
		// TODO Auto-generated method stub
		return dao.delete(seq);
	}
	
}
