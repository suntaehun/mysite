package com.bitacademy.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitacademy.mysite.vo.BoardVo;

public class BoardDao {

	public boolean write(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into board values(null, ?, ?, ?, now(), ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTilte());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getHit());
			pstmt.setLong(4, vo.getGroupNo());
			pstmt.setLong(5, vo.getOrderNo());
			pstmt.setLong(6, vo.getDepth());
			pstmt.setLong(7, vo.getUserNo());

			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			
			String sql ="select a.no, a.title, a.hit, date_format(a.reg_date, '%Y/%m/%d %H:%i:%s'), a.depth, a.user_no, b.name"
					+ "	from board a, user b"
					+ "   where a.user_no = b.no"
					+ "  order by group_no asc, order_no asc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long hit = rs.getLong(3);
				String regDate = rs.getString(4);
				Long depth= rs.getLong(5);
				Long userNo = rs.getLong(6);
				String name = rs.getString(7);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTilte(title);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setName(name);
				
				result.add(vo);
			}
	} catch (SQLException e) {
		System.out.println("Error:" + e);
	} finally {
		try {
			if(rs != null) {
				rs.close();
			}
			
			if(pstmt != null) {
				pstmt.close();
			}
			
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}		
	
	return result;
}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}


}
