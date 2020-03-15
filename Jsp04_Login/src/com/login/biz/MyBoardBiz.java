package com.login.biz;

import com.login.dao.MyBoardDao;
import com.login.dto.MyBoardDto;

public class MyBoardBiz {
	
	MyBoardDao dao = new MyBoardDao();
	public MyBoardDto login(String myid, String mypw) {
		
		return dao.login(myid, mypw);
	}
	public int update(MyBoardDto dto) {
		return dao.update(dto);
	}
	public int delete(int myno) {
		return dao.delete(myno);
	}
	public int idChk(String myid) {
		return dao.idChk(myid);
	}
	public int insert(MyBoardDto dto) {
		return dao.insert(dto);
	}
}
