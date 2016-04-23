package com.zghw.springmvc.demo.controller.simple;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {
	
	@RequestMapping("/auth/index")
	@ResponseBody
	public String simple(String id,String name,HttpServletRequest request){
		System.out.println("/auth/index");
		return "index";
	}
	@RequestMapping("/auth/any")
	@ResponseBody
	public String any(String id,String name,HttpServletRequest request){
		System.out.println("/auth/any");
		return "any";
	}
	@RequestMapping("/auth/some")
	public String some(String id,String name,HttpServletRequest request){
		System.out.println("/auth/some");
		return "/index.jsp";
	}
	@RequestMapping("/auth/some/*")
	public String someAny(String id,String name,HttpServletRequest request){
		System.out.println("/auth/some/*");
		return "/index.jsp";
	}
	@RequestMapping("/admin/config")
	@ResponseBody
	public String config(String id,String name,HttpServletRequest request){
		System.out.println("/admin/config");
		return "config";
	}
	
	@RequestMapping("/admin/config/*")
	@ResponseBody
	public String configAny(String id,String name,HttpServletRequest request){
		System.out.println("/admin/config/*");
		return "config";
	}
}
