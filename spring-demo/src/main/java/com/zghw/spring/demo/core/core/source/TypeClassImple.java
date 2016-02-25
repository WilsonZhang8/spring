package com.zghw.spring.demo.core.core.source;

import java.util.*;

import com.zghw.spring.demo.core.Ordered;

public class TypeClassImple extends TypeClass implements Ordered {
	private List<Set<String>> nestField;
	private HashMap<String,String> hm;
	private LinkedList<String> ld;

	public TypeClassImple(){}
	
	public TypeClassImple(TwoTuple<Integer,String> tt,User user,boolean b){}
	public TypeClassImple(int a,String b){}
	
	public void impleMain() {

	}

	public void methodTwo(int a,String b) {

	}

	public void methodThree(TwoTuple<Integer,String> tt,User user,boolean b) {
		
	}

	public User returnMethod() {
		return new User();
	}
}
