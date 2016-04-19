package com.zghw.springmvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestServletRequestHandledEvent {

	@RequestMapping("hello")
	public String helloworld(){
		return "/hw.jsp";
	}
}
