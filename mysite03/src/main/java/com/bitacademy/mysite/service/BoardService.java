package com.bitacademy.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.BoardRepository;
import com.bitacademy.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	public void addContents(BoardVo vo) {
		boardRepository.write(vo);
	}
	
	public BoardVo findContents(Long no) {
		return boardRepository.view(no);
	}

//	public BoardVo findContents(Long no, Long userNo) {
//		return null;
//	}
	
//	public Map<String, Object> findContentsList(int currentPage) {
//		// view의 페이징을 처리하기 위한 데이터의 값 계산
//		int beginPage = 0;
//		int endPage = 0;
//				
//		// 리스트 가져오기
//		return null;
//	}
	
	public List<BoardVo> findAll(){
		return boardRepository.findAll();
	}
	
//	public void updateContents(BoardVo vo) {
//		
//	}

	public void deleteContents(Long no, Long userNo) {
		boardRepository.delete(no);
	}

}
