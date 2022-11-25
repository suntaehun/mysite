package com.bitacademy.mysite.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitacademy.mysite.exception.ControllerExceptionHandler;

@Controller
public class MainController {
	private static final Log Logger = LogFactory.getLog(MainController.class);
	
	@RequestMapping({"", "/main"})
	public String index() {
		Logger.debug("MainController.index() called");
		return "main/index";
	}
}
