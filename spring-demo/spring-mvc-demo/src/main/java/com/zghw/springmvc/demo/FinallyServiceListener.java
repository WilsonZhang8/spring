package com.zghw.springmvc.demo;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * 在DispatcherServelt的service(不管是否出现异常)结束后，会触发ServletRequestHandledEvent时间，通知该监听器
 * 可以得到一些请求结果
 * 比如：客户端IP，请求的URL，请求方式，sessionId，servlet名字，运行时间，异常原因
 * @author zghw
 *
 */
@Component
public class FinallyServiceListener implements ApplicationListener<ServletRequestHandledEvent> {

	public void onApplicationEvent(ServletRequestHandledEvent event) {
		
		System.out.println(event.toString());
	}

}
