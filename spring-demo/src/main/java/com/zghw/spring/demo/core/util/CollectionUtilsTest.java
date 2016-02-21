package com.zghw.spring.demo.core.util;

import java.util.*;

import org.springframework.util.CollectionUtils;

public class CollectionUtilsTest {
	static void f(Object obj){
		System.out.println(obj);
	}
	
	public static void main(String args[]){
		//判断集合是否为空
		List list =new ArrayList();
		f(CollectionUtils.isEmpty(list));
		list.add("2");
		f(CollectionUtils.isEmpty(list));
		f(CollectionUtils.isEmpty(new HashMap()));
		//添加一个数组到集合中
		list=CollectionUtils.arrayToList(new String[]{"123"});
		f(list);
		//需泛型集合
		//CollectionUtils.mergeArrayIntoCollection(new String[]{"456"}, list);
		List list1=new ArrayList<String>();
		CollectionUtils.mergeArrayIntoCollection(new String[]{"456"}, list1);
		f(list1);
		//把properties属性放入map中
		Properties properties=new Properties();
		properties.setProperty("zhangsan", "gouwazi");
		Map<String,String> map =new HashMap<String,String>();
		CollectionUtils.mergePropertiesIntoMap(properties, map);
		f(map);
		//迭代器中是否包含某对象
		f(CollectionUtils.contains(list1.iterator(),"156"));
		f(CollectionUtils.contains(list1.iterator(),"456"));
		f(CollectionUtils.containsInstance(list1,"456"));
		//两个集合中有相同元素？
		f(CollectionUtils.containsAny(list1,list));
		list1.add("123");
		f(CollectionUtils.containsAny(list1,list));
		List<String> list2=new ArrayList<String>(list);
		list2.add("789");
		list1.add("789");
		//查找两集合查找第一个匹配的元素
		f(CollectionUtils.findFirstMatch(list1, list2));
		//集合中只有唯一对象
		f(CollectionUtils.hasUniqueObject(list2));
		list2=new ArrayList();
		list2.add("8888");
		list2.add("8888");
		f(CollectionUtils.hasUniqueObject(list2));
		//判断集合中的元素通用元素 ，如果有不是通用则返回null
		f(CollectionUtils.findCommonElementType(list2).getName());
		List list3=new ArrayList();
		list3.add(1);
		list3.add("sdf");
		f(CollectionUtils.findCommonElementType(list3));
	}
	enum Test{
		ZGHW,WILSEON
	}
}	

