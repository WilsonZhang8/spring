package com.zghw.spring.demo.core.core;

import org.springframework.core.Constants;

import com.zghw.spring.demo.core.core.source.ConstantsInfo;
import com.zghw.spring.demo.core.core.source.Phone;

/**
 * 常量类型工具处理常量。
 * 用处：加载类中的public static final常量进行分析其信息 查找 转换等
 * @author zghw
 *
 */
public class ConstantsTest {
	static void f(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) {
		Constants cs = new Constants(ConstantsInfo.class);
		f("包含常量的类：" + cs.getClass());
		f("包含常量的类名：" + cs.getClassName());
		f("常量个数：" + cs.getSize());
		// 循环所有常量名称
		for (String str : cs.getNames(null)) {
			System.out.println(str);
		}
		System.out.println("常量以P开头的：");
		for (String str : cs.getNames("P")) {
			System.out.println(str);
		}
		System.out.println("常量以s结尾的：");
		for (String str : cs.getNamesForSuffix("s")) {
			System.out.println(str);
		}
		// 通过常量名得到常量值 转换常量值
		Object s = cs.getValues("P_NAME");
		String name = cs.asString("P_NAME");
		Phone p = (Phone) cs.asObject("PHONE_INFO");
		Integer i = (Integer) cs.asNumber("VARIVAL_AGE");
		System.out.println("s=" + s + " name=" + name);
		System.out.println("p=" + p + " i=" + i);
		// myName 会转化为MY_NAME 进行前缀匹配
		System.out.println("通过属性命名可以查询到常量对应的信息");
		for (String str : cs.getNamesForProperty("myName")) {
			System.out.println(str);
		}
		System.out.println("通过属性命名可以查询到常量对应的信息");
		for (Object str : cs.getValuesForProperty("myName")) {
			System.out.println(str);
		}
	}

}
