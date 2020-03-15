package com.cal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cal.dto.CalDto;

import common.JDBCTemplate;

public class CalDao extends JDBCTemplate{

	public List<CalDto> getCalList(String id, String yyyyMMDD){
		Connection con =getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<CalDto>();
		String sql = "SELECT * FROM CALBOARD WHERE ID = ? AND SUBSTR(MDATE,1,8) = ? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMDD);
			
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
	public boolean multiDelete(String[] seq) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		int cnt[] = null;
		String sql = " DELETE FROM CALBOARD WHERE SEQ = ? ";
		try {
			pstm = con.prepareStatement(sql);
			for(int i = 0; i<seq.length; i++) {
				pstm.setString(1, seq[i]);
				
				pstm.addBatch();
			}
			cnt = pstm.executeBatch();
			for(int i = 0; i<cnt.length; i++) {
				if(cnt[i] == -2) {
				res++;
				}
			}
			if(res == seq.length) {
				commit(con);
			}else {
				rollback(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		return (res == seq.length)?true:false;
	}
}
