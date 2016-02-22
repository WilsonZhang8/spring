package com.zghw.spring.demo.core.util;

import java.util.*;

import org.springframework.util.InstanceFilter;

/**
 * 判断一个元素是否存在在A集合中但不存在B集合中
 * @author zghw
 *
 */
		
public class InstanceFilterTest {

	public static void main(String[] args) {
		List<String>  listA =new ArrayList<String>();
		listA.add("aa");
		listA.add("bb");
		listA.add("cc");
		List<String>  listB =new ArrayList<String>();
		listB.add("cc");
		listB.add("xx");
		listB.add("yy");
		//如果两个集合为空则返回不存在列表中,可以设置
		InstanceFilter ift =  new InstanceFilter(null,null,false);
		System.out.println(ift.match("aa"));
		ift =  new InstanceFilter(listA,listB,false);
		//判断一个元素是否存在在A集合中但不存在B集合中
		System.out.println(ift.match("aa"));
		System.out.println(ift.match("cc"));
		System.out.println(ift);
	}

}
