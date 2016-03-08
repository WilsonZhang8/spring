package com.zghw.spring.demo.demo;

public class WebSite {
	private static int count=0;
	private final int id=++count;
	public WebSite(){
		
	}
	@Override
	public String toString() {
		return "WebSite count"+id;
	}
}
