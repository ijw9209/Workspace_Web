package com.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.answer.dto.LoginDto;

import common.JDBCTemplate;

public class LoginDao extends JDBCTemplate{

	public LoginDto Login(String myid,String mypw) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM LOGINBOARD WHERE MYID = ? AND MYPW = ? AND MYENABLED = 'Y' ORDER BY MYNO DESC ";
		LoginDto dto = new LoginDto();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			pstm.setString(2, mypw);
			rs = pstm.executeQuery();
			while(rs.next()) {
			dto.setMyno(rs.getInt(1));
			dto.setMyid(rs.getString(2));
			dto.setMypw(rs.getString(3));
			dto.setMyname(rs.getString(4));
			dto.setMyphone(rs.getString(5));
			dto.setMyenabled(rs.getString(6));
			dto.setMyrole(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		
		
		
		return dto;
		
	}
	public int idChk(String id) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT COUNT(MYID) FROM LOGINBOARD WHERE MYID = ? ";
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		
		
		return res;
	}
}
