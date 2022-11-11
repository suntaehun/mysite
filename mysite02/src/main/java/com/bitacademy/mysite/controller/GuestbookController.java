package com.bitacademy.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite.dao.GuestbookDao;
import com.bitacademy.mysite.vo.GuestbookVo;

public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("a");
		
		if("list".equals(action)) {
			List<GuestbookVo> list = new GuestbookDao().findAll();
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp").forward(request, response);
		} else if("insert".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("contents");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);
			
			new GuestbookDao().insert(vo);
			response.sendRedirect(request.getContextPath() + "/guestbook?a=list");
		} else if("deleteform".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp").forward(request, response);			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
