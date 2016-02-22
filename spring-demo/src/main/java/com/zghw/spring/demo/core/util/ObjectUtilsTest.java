package com.zghw.spring.demo.core.util;

import java.util.ArrayList;

import org.springframework.util.ObjectUtils;

/**
 * 对象Object工具类示例
 * 主要对数组中基本类型对象的equal方法比较，得到基本类型的hashcode，重写了toString方法对于基本类型的使用
 * @author zghw
 *
 */
public class ObjectUtilsTest {
	static void f(Object obj){
		System.out.println(obj);
	}
	public static void main(String[] args) {
		//判断一个对象是不是数组
		f(ObjectUtils.isArray(new ArrayList()));
		f(ObjectUtils.isArray(new Integer[]{}));
		//数组对象是否为空
		f(ObjectUtils.isEmpty(new Integer[]{}));
		//对象是否为空
		f(ObjectUtils.isEmpty(new ArrayList()));
		//数组中是否包含某个元素
		f(ObjectUtils.containsElement(new Integer[]{1,2,3},1));
		//比较两个对象是否相等 主要时数组
		int[]  a =new int[]{1,2,3,4,5};
		int[]  b =new int[]{1,2,3,4,5};
		//a和b我们认为时相等的
		f(a.equals(b));
		//a和b我们认为时相等的,所以使用下面方法
		f(ObjectUtils.nullSafeEquals(a, b));
		f(a.toString());//基本类型输出问题
		f(ObjectUtils.nullSafeToString(a));
	}

}
