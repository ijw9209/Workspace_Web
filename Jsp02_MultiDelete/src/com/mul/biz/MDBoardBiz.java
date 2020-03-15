package com.mul.biz;

import java.util.List;

import com.mul.dao.MDBoardDao;
import com.mul.dto.MDBoardDto;

public class MDBoardBiz {

	MDBoardDao dao;
	
	public MDBoardBiz() {
		dao = new MDBoardDao();
	}
	public List<MDBoardDto> selectList(){
		return dao.selectList();
	}
	public MDBoardDto selectone(int seq) {
		return null;
	}
	public boolean insert(MDBoardDto dto) {
		return false;
	}
	public boolean update(MDBoardDto dto) {
		return false;
	}
	public boolean delete(int seq) {
		return false;
	}
	public boolean multidelete(String[] seq) {
		
		return dao.multidelete(seq);
	}


}
