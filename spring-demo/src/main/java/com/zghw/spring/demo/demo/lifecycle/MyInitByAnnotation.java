package com.zghw.spring.demo.demo.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class MyInitByAnnotation {

	@PostConstruct
	public void initByAnnontation(){
		System.out.println("init by anntation @PostConstruct");
	}
	@PreDestroy
	public void cleanByAnnontation(){
		System.out.println("destory by anntation @PreDestroy");
	}
}
