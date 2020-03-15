package com.answer.biz;

import com.answer.dao.LoginDao;
import com.answer.dto.LoginDto;

public class LoginBiz {
	
	
	LoginDao dao = new LoginDao();
	public LoginDto Login(String myid,String mypw) {
		
		return dao.Login(myid, mypw);
	}	
	public int idChk(String id) {
		return dao.idChk(id);
	}
}
