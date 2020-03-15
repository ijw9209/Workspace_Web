package com.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.login.dto.MemberDto;

import common.JDBCTemplate;

public class MemberDao extends JDBCTemplate{

	/*
	 * 관리자기능
	 * 1.회원 전체 정보(탈퇴한 회원 포함)
	 * 2.가입된 회원의 전체 정보(myenabled='y')
	 * 3.회원 등급 조정
	 */
	public List<MemberDto> userlistAll(){
		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		List<MemberDto> list = new ArrayList<MemberDto>();
		String sql = " SELECT MYNO,MYID,MYNAME,MYADDR,MYPHONE,MYEMAIL,MYENABLED,MYROLE FROM MEMBERBOARD ORDER BY MYNO DESC ";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMyname(rs.getString(3));
				dto.setMyaddr(rs.getString(4));
				dto.setMyphone(rs.getString(5));
				dto.setMyemail(rs.getString(6));
				dto.setMyenabled(rs.getString(7));
				dto.setMyrole(rs.getString(8));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		
		
		
		return list;
	}
	public List<MemberDto> enableylist(){
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<MemberDto> list = new ArrayList<MemberDto>();
		String sql = " SELECT MYNO,MYID,MYNAME,MYADDR,MYPHONE,MYEMAIL,MYENABLED,MYROLE FROM MEMBERBOARD WHERE MYENABLED='Y' ORDER BY MYNO DESC ";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMyname(rs.getString(3));
				dto.setMyaddr(rs.getString(4));
				dto.setMyphone(rs.getString(5));
				dto.setMyemail(rs.getString(6));
				dto.setMyenabled(rs.getString(7));
				dto.setMyrole(rs.getString(8));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
			
		}
		return list;
	}
	public int changRole(String role,int myno) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		
		if(role.equals("USER")) {
		String sql = "UPDATE MEMBERBOARD SET MYROLE = 'ADMIN' WHERE MYNO = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			
		}
		
		}else{
		String sql = " UPDATE MEMBERBOARD SET MYROLE = 'USER' WHERE MYNO = ?  ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			
		}
		
		}
		
		return res;
	}
	
	
	/*
	 * 
	 * 유저기능
	 * 1. 로그인
	 * 2. 회원가입 -> 아이디 중복체크기능
	 * 3. 정보 조회
	 * 4. 정보 수정(주소,전화번호,이메일,비밀번호)
	 * 5. 회원 탈퇴(UPDATE MYENABLED = 'N')
	 */
	public MemberDto login(String myid , String mypw) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MemberDto res = new MemberDto();
		
		String sql = " SELECT * FROM MEMBERBOARD WHERE MYID = ? AND MYPW = ? AND MYENABLED = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			pstm.setString(2, mypw);
			pstm.setString(3, "Y");
			System.out.println("03.query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				res.setMyno(rs.getInt(1));
				res.setMyid(rs.getString(2));
				res.setMypw(rs.getString(3));
				res.setMyname(rs.getString(4));
				res.setMyaddr(rs.getString(5));
				res.setMyphone(rs.getString(6));
				res.setMyemail(rs.getString(7));
				res.setMyenabled(rs.getString(8));
				res.setMyrole(rs.getString(9));
			}
			
		} catch (SQLException e) {
			System.out.println("03 04 error");
			e.printStackTrace();
		} finally {
			System.out.println("05 db 종료");
			close(rs);
			close(pstm);
			close(con);
		}
		
		
		
		return res;
	}
	public String idChk(String myid) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String res = null;
		String sql = " SELECT MYID FROM MEMBERBOARD WHERE MYID = ? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				res = rs.getString(1);
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
	public int insert(MemberDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MEMBERBOARD VALUES(MEMBERBOARDSEQ.NEXTVAL,?,?,?,?,?,?,'Y','USER') " ;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyid());
			pstm.setString(2, dto.getMypw());
			pstm.setString(3, dto.getMyname());
			pstm.setString(4, dto.getMyaddr());
			pstm.setString(5, dto.getMyphone());
			pstm.setString(6, dto.getMyemail());
			res = pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			}else {
				rollback(con);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		return res;
		
	}
	public int update(MemberDto dto) {
		Connection con = getConnection();
		//(주소,전화번호,이메일,비밀번호
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MEMBERBOARD SET MYPW = ?, MYADDR = ?,MYPHONE= ? ,MYEMAIL = ? WHERE MYNO = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMypw());
			pstm.setString(2, dto.getMyaddr());
			pstm.setString(3, dto.getMyphone());
			pstm.setString(4,dto.getMyemail());
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
		String sql = " UPDATE MEMBERBOARD SET MYENABLED=? WHERE MYNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, "N");
			pstm.setInt(2, myno);
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
		
		
		
		
		
		return myno;
		
	}
	public MemberDto selectOne(int myno) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MemberDto dto = new MemberDto();
		String sql = " SELECT * FROM MEMBERBAORD WHERE MYNO = ? ";
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
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
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		
		return dto;
	}
	public int updateRole(String role,int myno) {
		
		String sql = "UPDATE MEMBERBOARD SET MYROLE = ? WHERE MYNO = ? ";
		
		return 0;
	}
	
}
