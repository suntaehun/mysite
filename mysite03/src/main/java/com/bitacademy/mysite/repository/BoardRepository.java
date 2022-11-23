package com.bitacademy.mysite.repository;

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
		int count = sqlSession.insert("board.write", vo);
		return count == 1;
	}

	public List<BoardVo> findAll() {
		return sqlSession.selectList("board.findAll");
	}

}
