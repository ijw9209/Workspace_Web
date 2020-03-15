package com.bike.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.bike.dto.BikeDto;

import common.JDBCTemplate;

public class BikeDao extends JDBCTemplate{
	
	public int insert(List<BikeDto> list) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		int[] cnt = null;
		String sql = " INSERT INTO BIKE_TB VALUES(?,?,?,?,?,?,?,?) ";
		try {
			pstm = con.prepareStatement(sql);
			
			for(BikeDto dto : list){
			pstm.setString(1, dto.getRent_id());
			pstm.setString(2,dto.getAddr_gu());
			pstm.setInt(3, dto.getContent_id());
			pstm.setString(4,dto.getContent_nm());
			pstm.setString(5, dto.getNew_addr());
			pstm.setInt(6, dto.getCradel_count());
			pstm.setDouble(7, dto.getLongitude());
			pstm.setDouble(8, dto.getLatitude());
			
			pstm.addBatch();
			
			}
			cnt = pstm.executeBatch();
			// -2 : 성공 / -3 : 실패
			for(int i = 0; i <cnt.length; i++) {
				if(cnt[i] == -2) {
					res++;
				}
			}
			if(res == list.size()) {
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
