package com.bitacademy.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public boolean write(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into board values(null, ?, ?, ?, now(), ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getHit());
			pstmt.setInt(4, vo.getGroupNo());
			pstmt.setInt(5, vo.getOrderNo());
			pstmt.setInt(6, vo.getDepth());
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
		return sqlSession.selectList("board.findAll");
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
