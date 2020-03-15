package com.my.biz;

import com.my.dao.MemberMyDao;
import com.my.dto.MemberMyDto;

public class MemberMyBiz {
	
	MemberMyDao dao = new MemberMyDao();
	public MemberMyDto login(String id, String pw) {
		return dao.login(id, pw);
	}
}
