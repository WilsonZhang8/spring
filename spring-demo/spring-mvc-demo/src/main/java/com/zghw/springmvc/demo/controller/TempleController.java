package com.zghw.springmvc.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.zghw.springmvc.demo.vo.Info;
import com.zghw.springmvc.demo.vo.User;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"user", "info"}, types = {User.class, Info.class})
public class TempleController {
	
	/**
	 * 方法注解@RequestMapping主要有四种属性值，使用它们来验证一个请求是否符合该处理器来进行处理。
	 * 只有以下四种方式都匹配成功了，才能进入方法处理 请求路径匹配 value属性定义了映射多个路径，有一个路径与请求路径相同，则验证成功 请求方法匹配
	 * method属性判断请求的属性是否在定义的请求集合中，如果是则验证成功 请求参数匹配 params = {"id=2", "name!=3",
	 * "valve", "!key"},表示 请求的参数名称不能存在key，必须存在value，参数id的值必须=2，参数name的值不能等于3
	 * 仅支持这四种，当然这是相对于AnnotationMethodHandlerAdapter 请求头匹配
	 * headers和params的验证规则基本相同，它用来验证请求头部的信息。
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"/path.do", "/path/*.do", "path.do", "/${path}"}, method = {RequestMethod.GET,
			RequestMethod.POST}, params = {"id=2", "name!=3", "valve", "!key"}, headers = {"User-Agent", "!password",
			"Connection=keep-alive"})
	public String handlerMethod(HttpServletRequest request) {

		System.out.println(getHeadersString(request));
		System.out.println("执行handlerMethod");
		return "/index.jsp";
	}

	/**
	 * request请求参数绑定到对象中，供@RequestMapping方法中的参数使用该对象
	 * 其中方法参数的名字和@ModelAttribute中的值相同，并且类型也要一样。
	 * 
	 * @param name
	 *            这里的参数值可以和@RequestMapping方法中参数使用同样的注解
	 * @param age
	 * @return
	 */
	@ModelAttribute("info")
	public Info info(String name, int age) {
		Info info = new Info();
		info.setName(name);
		info.setAge(age);
		return info;
	}

	/**
	 * 当参数对象是自定义的时候，必须绑定数据使用，@ModelAttribute
	 * 
	 * @param info
	 * @return
	 */
	@RequestMapping("/save/info")
	public String saveInfo(Info info) {
		System.out.println(info.getName() + " " + info.getAge());
		return "/index.jsp";
	}
	/**
	 * @PathVariable 这个参数可以动态的获取请求地址为一个请求参数
	 * @ModelAttribute 绑定请求参数到对象上 value代表绑定的参数对象名
	 * @@Value可以使用表达式来设置默认的值
	 * @RequestParam 参数 required标示请求的参数是否必须有该参数名，默认为必须存在的。可以设置默认值defaultValue，
	 *               使用了@RequestParam如果请求参数不存在并且也没设置默认值则会返回400错误.
	 * @RequestHeader 和 @RequestParam 基本相同格式,它是来确定请求header中的值必须存在
	 * @CookieValue也是一样,用于请求cookie的处理
	 * @param name
	 * @return
	 */
	@RequestMapping("/save/u/{id}")
	public String saveUser(@PathVariable("id") String id, @ModelAttribute("info") Info info,
			@Value("#{systemProperties.rjrenbaleJNDI}") String usrDir,
			@RequestParam(required = true, defaultValue = "zhangsan") String name, @RequestHeader Map headers,
			@CookieValue(required = false) Cookie[] cookies,Date now) {
		System.out.println("id=" + id);
		System.out.println(usrDir);
		System.out.println(name);
		System.out.println(now);
		System.out.println(new Date().toGMTString());
		// System.out.println(System.getProperties().keySet());
		System.out.println(headers.toString());
		System.out.println(usrDir);
		System.out.println(info.getAge() + "    |  " + info.getName());
		System.out.println(cookies != null ? cookies.length : 0);
		return "/index.jsp";
	}
	private static String getHeadersString(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		Enumeration<String> headersNames = request.getHeaderNames();
		while (headersNames.hasMoreElements()) {
			String name = headersNames.nextElement();
			sb.append("name = " + name + " ,value = " + request.getHeader(name));
			sb.append("\n");
		}
		return sb.toString();
	}
	@InitBinder
	public void initDate(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
