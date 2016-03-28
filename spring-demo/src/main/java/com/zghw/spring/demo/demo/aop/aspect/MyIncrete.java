package com.zghw.spring.demo.demo.aop.aspect;

import org.springframework.stereotype.Component;

@Component
public class MyIncrete implements MyInterface {

	public int add(int a, int b) {
		return a+b;
	}

	public int div(int a, int b) {
		return a/b;
	}

}
