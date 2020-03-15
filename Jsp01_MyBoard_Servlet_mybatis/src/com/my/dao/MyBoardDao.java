package com.my.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.my.dto.MyBoardDto;

public class MyBoardDao extends SqlMapConfig{

	private String namespace= "com.my.db.mapper.";
	public List<MyBoardDto> selectList() {
		SqlSession session = null;
		List<MyBoardDto> list = new ArrayList<MyBoardDto>();
		
		session = getSqlSessionFactory().openSession();
		list = session.selectList(namespace+"selectList");
		
		return list;
	}

	public MyBoardDto selectone(int myno) {
		SqlSession session = null;
		MyBoardDto dto = new MyBoardDto();
		
		session = getSqlSessionFactory().openSession(true);
		dto = session.selectOne(namespace+"selectOne",myno);
		
		session.close();
		
		return dto;
	}

	public int insert(MyBoardDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
		session = getSqlSessionFactory().openSession(true);
		res = session.insert(namespace+"insert",dto);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return res;
	}

	public int update(MyBoardDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {								//openSession(bool) autocommit여부
		session = getSqlSessionFactory().openSession(true);
		res = session.update(namespace+"update", dto);
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return res;
	}

	public int delete(int myno) {
		SqlSession session = null;
		int res = 0;
		try {
		session = getSqlSessionFactory().openSession(true);
		res = session.delete(namespace+"delete",myno);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		return res;
	}
}
