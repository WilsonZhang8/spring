package com.zghw.spring.demo.demo;

import java.lang.annotation.RetentionPolicy;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zghw.spring.demo.core.core.annonation.Fruit;

@Fruit(value="苹果",price=123,names={"green","red"},list=List.class,aw=@Autowired(required=false),policy=RetentionPolicy.CLASS)
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
