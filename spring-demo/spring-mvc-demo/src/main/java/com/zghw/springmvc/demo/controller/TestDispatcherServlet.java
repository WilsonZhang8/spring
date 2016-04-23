package com.zghw.springmvc.demo.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.context.ThemeSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;

import com.zghw.springmvc.demo.util.RequestUtils;

@Controller
public class TestDispatcherServlet {

	@RequestMapping("/tds")
	public String testService(HttpServletRequest request){
		WebApplicationContext wac = RequestUtils.getWebApplicationContext(request);
		if(wac!=null){
			//ID=wac的类名+“：”+contextPath容器路径+Servlet名称
			//org.springframework.web.context.WebApplicationContext:/spring-mvc-demo/springmvc
			System.out.println(wac.getId());
		}
		
		LocaleResolver localeResolver=RequestUtils.getLocalResolver(request);
		if(localeResolver!=null){
			Locale locale = localeResolver.resolveLocale(request);
			System.out.println(locale.getCountry()+" "+locale.getLanguage());
		}
		
		ThemeResolver themeResolver = RequestUtils.getThemeResolver(request);
		if(themeResolver!=null){
			System.out.println(themeResolver.resolveThemeName(request));
		}
		
		ThemeSource themeSource = RequestUtils.getThemeSource(request);
		if(themeSource != null){
			System.out.println(themeSource.getTheme("theme").getName());
		}
		return "/hw.jsp";
	}
}
