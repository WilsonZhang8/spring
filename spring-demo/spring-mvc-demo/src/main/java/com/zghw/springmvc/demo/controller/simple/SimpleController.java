package com.zghw.springmvc.demo.controller.simple;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
	
	@RequestMapping("/simple")
	@ResponseBody
	public String simple(String id,String name,HttpServletRequest request){
		System.out.println("querystring="+request.getRequestURI());
		System.out.println("id="+id+" name =" +name);
		return "simple";
	}
}
