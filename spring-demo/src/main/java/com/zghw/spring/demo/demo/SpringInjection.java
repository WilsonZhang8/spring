package com.zghw.spring.demo.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringInjection {

	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("application-demo.xml");
		User user =(User)ctx.getBean("user");
		WebSite[] arrayWebSite=user.getArrayWebSite();
		System.out.print(user);
		/**
		 * 测试scope
		 */
		User user1=(User)ctx.getBean("user1");
		User user2=(User)ctx.getBean("user1");
		System.out.println("\n 原型对象比较："+(user1==user2));
		user1=user.getUserChild();
		user=(User)ctx.getBean("user");
		user2=user.getUserChild();
		System.out.println("\n 单例的对象比较："+(user1==user2));
	}

}
