package com.zghw.spring.demo.core.util;

import java.util.*;

import org.springframework.util.CompositeIterator;

/**
 * 组合多个迭代器(也就是实现了迭代器的都可以)为一个迭代器
 * @author zghw
 *
 */
public class CompositeIteratorTest {
	static void f(Object obj){
		System.out.println(obj);
	}
	public static void main(String[] args) {
		List<String> list =new ArrayList<String>();
		list.add("123");
		list.add("234");
		Set<String> set = new HashSet<String>();
		set.add("aa");
		set.add("bb");
		CompositeIterator<String> ct =new CompositeIterator<String>();
		ct.add(list.iterator());
		ct.add(set.iterator());
		while(ct.hasNext()){
			f(ct.next());
		}
	}

}
