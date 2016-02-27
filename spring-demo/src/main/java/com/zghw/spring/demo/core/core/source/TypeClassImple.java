package com.zghw.spring.demo.core.core.source;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.zghw.spring.demo.core.Ordered;

@Component
@Service
public class TypeClassImple extends TypeClass implements Ordered {
	
	private List<Set<String>> nestField;
	private HashMap<String,String> hm;
	private LinkedList<String> ld;

	public TypeClassImple(){}
	
	public TypeClassImple(TwoTuple<Integer,String> tt,User user,boolean b){}
	public TypeClassImple(int a,String b){}
	
	public void impleMain() {

	}
	@Autowired
	public void methodTwo(int a,String b) {

	}

	public void methodThree(TwoTuple<Integer,String> tt,User user,boolean b) {
		
	}

	public User returnMethod() {
		return new User();
	}
	
	public class InnerClass1 {
	      public InnerClass1() {
	         System.out.println("Inner Class1");
	      }
	   }

	   public class InnerClass2 {
	      public InnerClass2() {
	         System.out.println("Inner Class2");
	      }
	   }

	   private class InnerPrivateClass {
	      public InnerPrivateClass() {
	         System.out.println("Inner Private Class");
	      }
	   }
	   interface interfaceInner{}
}
