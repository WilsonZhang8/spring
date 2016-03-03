package com.zghw.spring.demo.beans.test.use;

public class MyBean {
	private String name;
	private int age;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public MyBean setName(String name) {
		this.name = name;
		return this;
	}
	/*public String getName1() {
		return name;
	}
	public void setName1(String name) {
		this.name = name;
	}*/
}
