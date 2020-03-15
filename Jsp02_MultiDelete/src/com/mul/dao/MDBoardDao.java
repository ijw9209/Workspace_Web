package com.mul.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mul.dto.MDBoardDto;

import common.JDBCTemplete;

public class MDBoardDao extends JDBCTemplete{

	public List<MDBoardDto> selectList(){
		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		
		String sql = " SELECT SEQ,WRITER,TITLE,CONTENT,REGDATE FROM MDBOARD ORDER BY SEQ DESC ";
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				MDBoardDto dto = new MDBoardDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,stmt ,con);
		}
		
		return list;
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
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MDBOARD WHERE SEQ = ? ";
		int cnt[] = null;
		
		try {
			pstm = con.prepareStatement(sql);
			
			for(int i = 0; i<seq.length; i++) {
				pstm.setString(1, seq[i]);
				
				pstm.addBatch();
			}
			cnt = pstm.executeBatch();
			for(int i = 0; i< cnt.length; i++) {
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
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		
		return (res == seq.length)?true:false;
		
	}
}
