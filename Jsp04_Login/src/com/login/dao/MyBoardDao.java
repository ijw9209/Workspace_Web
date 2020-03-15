package com.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.login.dto.MyBoardDto;

import common.JDBCTemplate;

public class MyBoardDao extends JDBCTemplate{

	public MyBoardDto login(String myid, String mypw) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		MyBoardDto dto = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM MEMBERBOARD WHERE MYID = ? AND MYPW = ? AND MYENABLED = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			pstm.setString(2, mypw);
			pstm.setString(3, "Y");
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				dto = new MyBoardDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return dto;
	}
	public int idChk(String myid) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT COUNT(MYID) FROM MEMBERBOARD WHERE MYID = ? ";
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return res;
		
	}
	
	public int update(MyBoardDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MEMBERBOARD SET MYPW = ?,MYADDR = ?,MYPHONE = ?,MYEMAIL=? WHERE MYNO = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMypw());
			pstm.setString(2, dto.getMyaddr());
			pstm.setString(3, dto.getMyphone());
			pstm.setString(4, dto.getMyemail());
			pstm.setInt(5, dto.getMyno());
			
			res = pstm.executeUpdate();
			if(res > 0) {
				commit(con);
			}else {
				rollback(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
	public int delete(int myno) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MEMBERBOARD SET MYENABLED = ? WHERE MYNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1 ,"N");
			pstm.setInt(2 ,myno);
			
			res = pstm.executeUpdate();
			if(res > 0) {
				commit(con);
			}else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
	public int insert(MyBoardDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MEMBERBOARD VALUES(MEMBERBOARDSEQ.NEXTVAL,?,?,?,?,?,?,'Y','USER')";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1,dto.getMyid());
			pstm.setString(2,dto.getMypw());
			pstm.setString(3,dto.getMyname());
			pstm.setString(4,dto.getMyaddr());
			pstm.setString(5,dto.getMyphone());
			pstm.setString(6,dto.getMyemail());
			
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			}else {
				rollback(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
}
