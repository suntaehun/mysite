package com.bitacademy.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitacademy.mysite.dao.BoardDao;
import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.UserVo;

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("a");
		
		if("writeform".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/views/board/writeform.jsp").forward(request, response);			
		} else if("write".equals(action)) {
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			if(authUser == null) {
				response.sendRedirect(request.getContextPath() + "/user?a=loginform");
				return;
			}
			
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			Long hit = 0L;
			Long groupNo = 1L;
			Long orderNo = 1L;
			Long depth = 0L;
			
			BoardVo vo = new BoardVo();
			vo.setTilte(title);
			vo.setContents(contents);
			vo.setHit(hit);
			vo.setGroupNo(groupNo);
			vo.setOrderNo(orderNo);
			vo.setDepth(depth);
			vo.setUserNo(authUser.getNo());
			
			new BoardDao().write(vo);
			
			response.sendRedirect(request.getContextPath() + "/board?a=list");
			
		} else if("view".equals(action)){
			request.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(request, response);			
		} else {
			
			request.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}