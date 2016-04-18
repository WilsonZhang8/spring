package com.zghw.springmvc.demo;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class MyApplicationContextInitializer1 implements
		ApplicationContextInitializer<XmlWebApplicationContext> {

	public void initialize(XmlWebApplicationContext applicationContext) {
		System.out.println("在刷新容器前可以配置容器 局部");
	}

}
