package com.zghw.spring.demo.demo.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 测试发送短信事件
 * @author zghw
 *
 */
@Configuration
@ComponentScan
public class TestEvent {
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(TestEvent.class);
		UserService us=(UserService)ctx.getBean("userService");
		us.registerUser("zghw", "13699120345");
	}

}
