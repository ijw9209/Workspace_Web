package com.mul.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.apache.ibatis.session.SqlSession;

import com.mul.dto.MDBoardDto;

public class MDBoardDao extends SqlMapConfig{

	private String namespace= "muldelmapper.";
	public List<MDBoardDto> selectList() {
		SqlSession session = null;
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		
		//무언가 예외가 발생할것같으면 그냥 try-catch 로 묶는게 좋음
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList(namespace+"selectList");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		return list;
		
	}
	public MDBoardDto selectOne(int seq) {
		SqlSession session = null;
		MDBoardDto dto = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace+"selectOne",seq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
		return dto;
		
	}
	public boolean insert(MDBoardDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.insert(namespace+"insert",dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
		return (res > 0)?true:false;
		
	}
	public boolean update(MDBoardDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.update(namespace +"update",dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
				
				
		return (res > 0)?true:false;
		
	}
	public boolean delete(int seq) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.delete(namespace+"delete",seq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
				
				
		
		
		return (res > 0)?true:false;
		
	}
	public boolean multidelete(String[] seq) {
		
		int count = 0;
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("seqs",seq);
		
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			count = session.delete(namespace+"muldel",map);
		
			if(count == seq.length) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
		return (count == seq.length)?true:false;
		
	}
	public List<MDBoardDto> search(String text) {
		SqlSession session = null;
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList(namespace+"selectLike" , text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
		
		
		return list;
	}
}
