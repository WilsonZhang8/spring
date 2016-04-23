package com.zghw.springmvc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.MappedInterceptor;
@Configuration
public class MappedInterceptorConfig {
	private String[] includePatterns = new String[]{
			
	};
	private String[] excludePatterns = new String[]{
			
	};
	private HandlerInterceptor interceptor = null;
	//@Bean
	public MappedInterceptor myInterceptor() {
		MappedInterceptor mappedInterceptor = new MappedInterceptor(includePatterns, excludePatterns, interceptor);
		
		//mappedInterceptor.setPathMatcher(AntPathMatcher);
		return mappedInterceptor;
	}
	
}
