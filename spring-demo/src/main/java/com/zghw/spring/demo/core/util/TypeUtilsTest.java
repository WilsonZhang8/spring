package com.zghw.spring.demo.core.util;

import org.springframework.util.TypeUtils;

/**
 * java 5 泛型参数应用
 * @author zghw
 *
 */
public class TypeUtilsTest {
	static void f(Object obj){
		System.out.println(obj);
	}
	public static void main(String[] args) {
		//判断两个类型是否相同
		//1.判断两个类型是否equals
		//2.判断两个class是否是相等或继承Assignable
		//3.判断参数类型 如果是参数类型得到原始类型 进行2判断
		//4.判断数组参数类型 然后在判断复合类型中的原始参数类型中的类型是否相等
		//判断通配符相等
		//TypeUtils.isAssignable(lhsType, rhsType);
	}

}
