package com.zghw.spring.demo.core.util;

import java.io.Serializable;

public class User implements Serializable {
	public String pubstr;
	private String str;
	private int age;
	private final static String CON="cc";
	public final static String CON2="bb";
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public int getAge() {
		return age;
	}
	public int getAge(String aa) {
		return 11;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public static String getCon() {
		return CON;
	}
	
}
