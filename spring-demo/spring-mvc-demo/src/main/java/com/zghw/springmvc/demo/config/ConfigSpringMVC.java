package com.zghw.springmvc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * 使用编程注解的方式来配置spring mvc
 * 
 * @author zghw
 *
 */
@Configuration
public class ConfigSpringMVC {

	/**
	 * 配置HandlerMapping参数
	 * 这里是BeanNameUrlHandlerMapping的配置
	 * @return
	 */
	//@Bean
	public HandlerMapping beanNameUrlHandlerMapping() {
		System.out.println("初始化BeanNameUrlHandlerMapping");
		BeanNameUrlHandlerMapping handlerMapping = new BeanNameUrlHandlerMapping();
		// 设置当前handlerMapping排列顺序
		handlerMapping.setOrder(Integer.MAX_VALUE);
		// 设置默认处理器
		// handlerMapping.setDefaultHandler(defaultHandler);
		// 设置是否允许路径全匹配
		handlerMapping.setAlwaysUseFullPath(false);
		// 是否使用默认的url编码ISO-8859-1
		handlerMapping.setUrlDecode(false);
		// 设置过滤器对象
		// handlerMapping.setInterceptors(interceptors);
		// 设置容器
		// handlerMapping.setApplicationContext(context);
		// 设置url匹配器
		// handlerMapping.setPathMatcher(pathMatcher);
		// 是否懒加载Handlers
		handlerMapping.setLazyInitHandlers(false);
		// 设置“/”根部的处理器
		// handlerMapping.setRootHandler(rootHandler);
		// 是否移除；后面的字符串
		handlerMapping.setRemoveSemicolonContent(false);
		// 设置URL帮助
		// handlerMapping.setUrlPathHelper(urlPathHelper);
		return handlerMapping;
	}
}
