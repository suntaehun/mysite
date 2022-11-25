package com.bitacademy.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.security.Auth;
import com.bitacademy.mysite.security.AuthUser;
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
	
	@RequestMapping(value="/write/{no}", method=RequestMethod.GET)
	public String writeform(Model model, @PathVariable("no") Long no) {
		model.addAttribute("no", no);
		return "/board/write";
	}
	
	@RequestMapping(value="/write/{no}", method=RequestMethod.POST)
	public String write(BoardVo vo, @PathVariable("no") Long no) {
		vo.setUserNo(no);
		boardService.addContents(vo);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.findContents(no);
		model.addAttribute("title", vo.getTitle());
		model.addAttribute("contents", vo.getContents());
		model.addAttribute("userNo", vo.getUserNo()); 
		return "/board/view";
	}
	
//	@Auth
//	@RequestMapping("/delete/{no}")
//	public String delete(@PathVariable("no") Long no, Long userNo) {
//		boardService.deleteContents(no, userNo);
//		return "redirect:/board/list";
//	}
}
