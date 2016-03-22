package com.zghw.spring.demo.demo.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {
	
	public static void main(String args[]){
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
		MyInit mi=(MyInit)ctx.getBean("myInit");
		System.out.println(mi);
		MyInitByAnnotation mi2=(MyInitByAnnotation)ctx.getBean("myInitByAnnotation");
		System.out.println(mi2);
		
	}
}
