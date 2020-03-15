package com.login.biz;

import java.util.List;

import com.login.dao.MemberDao;
import com.login.dto.MemberDto;

public class MemberBiz {

	MemberDao dao = new MemberDao();
	public List<MemberDto> userlistAll(){
		return dao.userlistAll();
	}
	public List<MemberDto> enableylist(){
		return dao.enableylist();
	}

	public int changRole(String role,int myno) {
		return dao.changRole(role, myno);
	}
	public MemberDto login(String myid , String mypw) {
		
		return dao.login(myid, mypw);
	}
	public int update(MemberDto dto) {
		return dao.update(dto);
	}
	public int delete(int myno) {
		
		return dao.delete(myno);
	}
	public String idChk(String myid) {
		return dao.idChk(myid);
		
	}
	public int insert(MemberDto dto) {
		return dao.insert(dto);
	}
	public MemberDto selectOne(int myno) {
		return dao.selectOne(myno);
	}
	public int updateRole(String role,int myno) {
		return dao.updateRole(role,myno);
	}
}
