package com.zghw.springmvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
	@RequestMapping("/*")
	public String defaults(){
		return "index.jsp";
	}
}
