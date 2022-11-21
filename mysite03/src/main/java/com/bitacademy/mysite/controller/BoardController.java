package com.bitacademy.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitacademy.mysite.service.BoardService;
import com.bitacademy.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<BoardVo> list = boardService.findAll();
		model.addAttribute("list", list);
		return "/board/list";
	}
	
	@RequestMapping("/writeform")
	public String writeform() {
		return "/board/writeform";
	}
}
