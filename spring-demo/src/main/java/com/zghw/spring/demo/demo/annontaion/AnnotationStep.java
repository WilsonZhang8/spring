package com.zghw.spring.demo.demo.annontaion;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationStep {
	public static void main(String args[]){
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(MyAnnotation.class);
		
		
	}
}
