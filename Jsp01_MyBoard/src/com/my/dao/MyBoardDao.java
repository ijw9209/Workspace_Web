package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.MyBoardDto;

public class MyBoardDao {

	public List<MyBoardDto> selectList() {
		// 1.Driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01.driver 연결");
		} catch (ClassNotFoundException e) {
			System.out.println("01.error");
			e.printStackTrace();
		}

		// 2.계정연결
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "kh";
		String password = "kh";
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("02.계정 연결");
		} catch (SQLException e) {
			System.out.println("02.error");
			e.printStackTrace();
		}

		// 3.쿼리 준비
		Statement stmt = null;
		ResultSet rs = null;
		List<MyBoardDto> list = new ArrayList<MyBoardDto>();
		// 왠만하면 전체다쓰렴
		String sql = "  SELECT MYNO , MYNAME , MYTITLE , MYCONTENT , MYDATE FROM MYBOARD ORDER BY MYNO DESC  ";

		try {
			stmt = con.createStatement();
			System.out.println("03.query 준비 : " + sql);

			// 4,query 실행및 리턴
			rs = stmt.executeQuery(sql);
			System.out.println("04.query 실행 및 리턴");
			
			while (rs.next()) {
				
				MyBoardDto dto = new MyBoardDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
				
				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("03, 04, error");
			e.printStackTrace();
		} finally {
			try {
				// 5.db종료
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("05.error");
				e.printStackTrace();
			}
		}
		
		System.out.println("list : " + list.size());
		
		return list;
	}

	public MyBoardDto selectOne(int myno) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01.driver연결");
		} catch (ClassNotFoundException e) {
			System.out.println("01.error");
			e.printStackTrace();
		}
			Connection con = null;
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "kh";
			String password = "kh";
			try {
				con = DriverManager.getConnection(url, user, password);
				System.out.println("02. 계정연결");
			} catch (SQLException e) {
				System.out.println("02.error");
				e.printStackTrace();
			}
			
			PreparedStatement psmt = null;
			ResultSet rs = null;
			MyBoardDto dto = new MyBoardDto();
			
			String sql = " SELECT MYNO , MYNAME , MYTITLE , MYCONTENT , MYDATE FROM MYBOARD WHERE MYNO = ? ";
			
			try {
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, myno);
				System.out.println("03. query 준비 : " + sql);
				
				rs = psmt.executeQuery();
				System.out.println("04.query 실행 및 리턴");
				//그냥 rs.next() 써도 되긴하는데 까먹을까봐 while함
				while(rs.next()) {
					dto.setMyno(rs.getInt(1));
					dto.setMyname(rs.getString(2));
					dto.setMytitle(rs.getString(3));
					dto.setMycontent(rs.getString(4));
					dto.setMydate(rs.getDate(5));
				}
				
			} catch (SQLException e) {
				System.out.println("03 ,04 error");
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					psmt.close();
					con.close();
					System.out.println("05 db종료");
				} catch (SQLException e) {
					System.out.println("05 error");
					e.printStackTrace();
				}
			}
			
		
		
		
		return dto;
	}

	public int Insert(MyBoardDto dto) {
		try {
			//1.드라이버 연결
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01.driver연결");
		} catch (ClassNotFoundException e) {
			System.out.println("01.error");
			e.printStackTrace();
		}
			//2.계정 연결
			Connection con = null;
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "kh";
			String password = "kh";
			//setautocommit의 디폴트값은 true
			try {
				con = DriverManager.getConnection(url, user, password);
				System.out.println("02.계정 연결");
			} catch (SQLException e) {
				System.out.println("02.error");
				e.printStackTrace();
			}
		
			//3.query 준비
			PreparedStatement psmt = null;
			int res = 0;

			String sql = " INSERT INTO MYBOARD VALUES(MYSEQ.NEXTVAL,?,?,?,SYSDATE) ";
			
			try {
				psmt = con.prepareStatement(sql);
				psmt.setString(1, dto.getMyname());
				psmt.setString(2, dto.getMytitle());
				psmt.setString(3, dto.getMycontent());
				System.out.println("03.query 준비 : " + sql);
				//4.query 실행 및 리턴
				res = psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					//5.db종료
					psmt.close();
					con.close();
					System.out.println("05.db종료");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		return res;
	}

	public int Update(MyBoardDto dto) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			Connection con = null;
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "kh";
			String password = "kh";
			try {
				con = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		PreparedStatement psmt = null;
		int res = 0;
		String sql = " UPDATE MYBOARD SET MYTITLE = ? , MYCONTENT = ? WHERE MYNO = ? ";
		
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getMytitle());
			psmt.setString(2, dto.getMycontent());
			psmt.setInt(3, dto.getMyno());
			
			res = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return res;
	}

	public int Delete(int myno) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01.driver연결");
		} catch (ClassNotFoundException e) {
			System.out.println("01. error");
			e.printStackTrace();
		}
			Connection con = null;
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="kh";
			String password = "kh";
			try {
				con = DriverManager.getConnection(url, user, password);
				System.out.println("02.계정연결");
			} catch (SQLException e) {
				System.out.println("02.error");
				e.printStackTrace();
			}
		
			PreparedStatement psmt = null;
			int res = 0;
			
			String sql = " DELETE FROM MYBOARD WHERE MYNO = ? ";
			
			try {
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, myno);
				
				res = psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					psmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		return res;
	}
}
