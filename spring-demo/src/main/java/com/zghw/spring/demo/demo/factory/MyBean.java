package com.zghw.spring.demo.demo.factory;

import java.util.*;

public class MyBean {
	private String name;
	private int age;
	public Integer[] integerArray;
	private Map<String, Object> map;
	private List<MyBean> list;
	private MyEnum myenum;
	private Bean1 bean1;
	private GenericBean<Bean1, Bean2> gb;

	public Integer[] getIntegerArray() {
		return integerArray;
	}

	public void setIntegerArray(Integer[] integerArray) {
		this.integerArray = integerArray;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<MyBean> getList() {
		return list;
	}

	public void setList(List<MyBean> list) {
		this.list = list;
	}

	public MyEnum getMyenum() {
		return myenum;
	}

	public void setMyenum(MyEnum myenum) {
		this.myenum = myenum;
	}

	public Bean1 getBean1() {
		return bean1;
	}

	public void setBean1(Bean1 bean1) {
		this.bean1 = bean1;
	}

	public GenericBean<Bean1, Bean2> getGb() {
		return gb;
	}

	public void setGb(GenericBean<Bean1, Bean2> gb) {
		this.gb = gb;
	}

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
}
