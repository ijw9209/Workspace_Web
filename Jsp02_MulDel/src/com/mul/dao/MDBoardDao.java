package com.mul.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static common.JDBCTemplete.*;
import com.mul.dto.MDBoardDto;



public class MDBoardDao {

	public List<MDBoardDto> selectList() {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		
		String sql = " SELECT SEQ,WRITER,TITLE,CONTENT,REGDATE FROM MDBOARD ORDER BY SEQ DESC ";
		
		try {
			stmt = con.createStatement();
			System.out.println("03.query 준비 :" +sql);
			rs = stmt.executeQuery(sql);
			System.out.println("04 query 실행 및 리턴");
			
			while(rs.next()) {
				MDBoardDto dto = new MDBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("03.04.에러");
			e.printStackTrace();
		} finally {
			//close(rs,stmt,con) 이렇게 만들어도됨
			close(rs);
			close(stmt);
			close(con);
			System.out.println("05.db종료");
		}
		return list;
	}
	public MDBoardDto selectOne(int seq) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MDBoardDto dto = new MDBoardDto();
		String sql = " SELECT SEQ,WRITER,TITLE,CONTENT,REGDATE FROM MDBOARD WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				
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
	public boolean insert(MDBoardDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MDBOARD VALUES(MDBOARDSEQ.NEXTVAL,?,?,?,SYSDATE) ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			
			res= pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		
		
		return (res > 0)?true:false;
	}
	public boolean update(MDBoardDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE MDBOARD SET TITLE = ? , CONTENT = ? WHERE SEQ = ? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			
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
		
		return (res > 0)?true:false;
	}
	public boolean delete(int seq) {
		Connection con = getConnection();
		
		PreparedStatement pstm= null;
		int res = 0;
		
		String sql = " DELETE FROM MDBOARD WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			
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
		
		
		
		
		return (res >0)?true:false;
	}
	public boolean multidelete(String[] seq) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MDBOARD WHERE SEQ = ? ";
		int[] cnt = null;

		try {
			pstm = con.prepareStatement(sql);
			
			for(int i = 0; i< seq.length; i++ ) {
				pstm.setString(1, seq[i]);
				//addBatch() : 메모리에 적재후 , executeBatch() 가 호출될때 한번에 모두 실행
				pstm.addBatch();
				System.out.println("03.query준비 : " + sql + seq[i]);
			}
			// dao <==> db 사이에 먼저 addbatch() 라고 반복해서 메모리에 쌓아둠 executeBatch() 하면 그때 한번에 실행 훨씬 빠르다
			cnt = pstm.executeBatch();
			
			System.out.println("04.query 실행 및 리턴");
			//executeBatch(); -2 , -3 배열형식으로 cnt 배열에담김
			//-2 : 성공 , -3 : 실패
			for(int i =0; i<cnt.length; i++) {
				if(cnt[i] == -2) {
					res++; 
				}
			}
			//성공한 애들이랑 넘어온애들이랑 같으면 commit
			if(res == seq.length) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("05. db종료");
		}
		
		
		return (res == seq.length)?true:false;
	}
}
