package com.zghw.spring.demo.demo.aop;

import org.springframework.stereotype.Component;

@Component
public class Worker {
	
	public void doSomething(){
		System.out.println("处理业务逻辑doSomething！");
	}
	
	public void doSomethingArgs(String name,int age){
		System.out.println("处理业务逻辑doSomethingArgs！");
	}
	
}
