package com.zghw.spring.demo.demo;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;
/**
 * 实现MethodReplacer接口
 * @author zghw
 *
 */
public class ReplaceMethodUse implements MethodReplacer {

	public Object reimplement(Object obj, Method method, Object[] args)
			throws Throwable {
		System.out.println("专业替换户");
		return null;
	}

}
