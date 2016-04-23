package com.zghw.springmvc.demo;

import org.springframework.web.context.WebApplicationContext;

public class Test {

	public static void main(String[] args) {
		System.out.println(foo());
		//System.out.println("WebApplicationContext的名字="+WebApplicationContext.class.getName());
	}
	public static String foo(){
		int i=0;
		for(;i<100;i++){
			if(i==30){
				System.out.println("执行到这里面了吗");
				return String.valueOf(i);
			}
		}
		System.out.println(i);
		return String.valueOf(i);
	}

}
