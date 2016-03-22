package com.zghw.spring.demo.demo.annontaion.app.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.zghw.spring.demo.demo.Computer;
import com.zghw.spring.demo.demo.annontaion.app.service.MyService;

public class Bar {
	@Value("1215")
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 属性的注入
	 *  @Autowired 用来表示属性将有sprnig自动注入的注入
	 *  @Qualifier 用来规定注入的规则。使用beanName来限定当前的注入
	 */
	@Autowired
	@Qualifier("myServiceBean2")
	private MyService facde;
	@Autowired
	private Bar b;
	
	private Computer computer;
	
	public Bar(){
		System.out.println("是否在会自动的构造");
	}
	
	public Computer getComputer() {
		return computer;
	}

	
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	public void test(){
		
		System.out.println(facde.getMessage()+"=  "+b.getName());
	}
	
}
