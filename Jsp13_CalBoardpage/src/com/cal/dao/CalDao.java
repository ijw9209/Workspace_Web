package com.cal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cal.dto.CalDto;

import common.JDBCTemplate;

public class CalDao extends JDBCTemplate{

	public List<CalDto> getCalList(String id, String yyyyMMDD,int start,int end){
		Connection con =getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<CalDto>();
		String sql = " SELECT R,SEQ,ID,TITLE,CONTENT,MDATE,REGDATE " + 
				" FROM (SELECT ROWNUM AS R,SEQ,ID,TITLE,CONTENT,MDATE,REGDATE " + 
				" FROM CALBOARD WHERE ID = ? AND SUBSTR(MDATE,1,8) = ?) " + 
				" WHERE R BETWEEN ? AND ? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMDD);
			pstm.setInt(3, start);
			pstm.setInt(4, end);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				CalDto dto = new CalDto();
				dto.setSeq(rs.getInt(2));
				dto.setId(rs.getString(3));
				dto.setTitle(rs.getString(4));
				dto.setContent(rs.getString(5));
				dto.setMdate(rs.getString(6));
				dto.setRegdate(rs.getDate(7));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return list;
	}
	
	public int insertCalBoard(CalDto dto) {
		
		//INSERT INTO CALBOARD
		//VALUES(CALBOARDSEQ.NEXTVAL,?,?,?,?,SYSDATE)
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "INSERT INTO CALBOARD VALUES(CALBOARDSEQ.NEXTVAL,?,?,?,?,SYSDATE) ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			pstm.setString(4, dto.getMdate());
			
			
			res = pstm.executeUpdate();
			if(res > 0) {
				commit(con);
			}else {
				rollback(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return res;
	}
	public List<CalDto> getCalviewList(String id , String yyyyMM){
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<CalDto>();
		String sql = "SELECT * " + 
				" FROM(SELECT (ROW_NUMBER() OVER(PARTITION BY SUBSTR(MDATE,1,8) ORDER BY MDATE)) RN, " + 
				"		SEQ, ID, TITLE, CONTENT , MDATE , REGDATE " + 
				"		FROM CALBOARD " + 
				"		WHERE ID = ? AND SUBSTR(MDATE,1,6) = ?) " + 
				"	WHERE RN BETWEEN 1 AND 3";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMM);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
			CalDto dto = new CalDto(
					rs.getInt(2),	
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getDate(7));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return list;
	}
	public int getCalCount(String id , String yyyyMMdd) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int count = 0;
		ResultSet rs = null;
		
		String sql  = " SELECT COUNT(*) FROM CALBOARD WHERE ID = ? AND SUBSTR(MDATE,1,8) = ? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return count;
	}
	public List<CalDto> pageList(String id , String yyyyMMdd){
		Connection con = getConnection();
		
		
		PreparedStatement pstm = null;
		List<CalDto> list = new ArrayList<CalDto>();
		String sql = " SELECT * FROM CALBOARD WHERE ID = ? AND SUBSTR(MDATE,1,8) = ? ";
		ResultSet rs = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			rs = pstm.executeQuery();
			while(rs.next()) {
				CalDto dto = new CalDto();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setMdate(rs.getString(5));
				dto.setRegdate(rs.getDate(6));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return list;
	}
}
