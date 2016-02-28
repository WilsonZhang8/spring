package com.zghw.spring.demo.core.core;

import java.util.ArrayList;

import org.springframework.core.Conventions;
import org.springframework.util.ReflectionUtils;

import com.zghw.spring.demo.core.core.source.ApproximableMap;

/**
 * 根据方法返回值 、 字段、返回相应的字符串类型名称
 * 用处：根据方法返回值 、 字段、返回相应的字符串类型名称，可以用以自动化给其起别名
 * @author zghw
 *
 */
public class ConventionsTest {
	static void f(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConventionsTest ct = new ConventionsTest();
		f(Conventions.getVariableName(new ApproximableMap()));
		ArrayList arr = new ArrayList();
		arr.add("1");
		f(Conventions.getVariableName(arr));
		// 通过方法得到方法返回值名称
		f(Conventions.getVariableNameForReturnType(ReflectionUtils.findMethod(
				XSDsdfPli_A.class, "aBB_aaSaaAdf")));
		f(Conventions.getVariableNameForReturnType(ReflectionUtils.findMethod(
				XSDsdfPli_A.class, "aBB_aaSaaAdfStr")));
		f(Conventions.getVariableNameForReturnType(ReflectionUtils.findMethod(
				XSDsdfPli_A.class, "aBB_aaSaaAdfStr"), ct));
		f(Conventions.attributeNameToPropertyName("aBB_aaSaaAdf"));
	}

	class XSDsdfPli_A {
		public void aBB_aaSaaAdf() {
		}

		public XSDsdfPli_A aBB_aaSaaAdfStr() {
			return new XSDsdfPli_A();
		}

	}
}
