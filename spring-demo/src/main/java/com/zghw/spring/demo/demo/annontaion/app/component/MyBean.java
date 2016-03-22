package com.zghw.spring.demo.demo.annontaion.app.component;

import org.springframework.stereotype.Component;

/**
 * 使用@Component被注解的类代表是一个“组件”,
 * 当使用自动扫描classpath下的类时，会自动的把被@Component注解过的类加入到候选类中。
 * 它是一个通用组件的概念。而 @Repository, @Service, and @Controller 是它的更具体的用例子。
 * @author zghw
 *
 */
@Component("mybean")
public class MyBean {
	
	/**
	 * 初始化方法
	 */
	public void init(){
		System.out.println("初始化MyBean");
	}
	/**
	 * 销毁方法
	 */
	public void cleanup(){
		System.out.println("销毁MyBean");
	}
}
