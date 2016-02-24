package com.zghw.spring.demo.core.util;

import org.springframework.util.PatternMatchUtils;
/**
 * xx 模式匹配 * 、*xx 、*xx* 、xx* 、*xx*y 
 * @author zghw
 *
 */
public class PatternMatchUtilsTest {

	public static void main(String[] args) {
		/**
		 * 匹配多个模式
		 */
		String str="xx";
		String[] patterns =new String[]{"*","*xx","","*xx*","xx*","xx*y"};
		boolean bb=PatternMatchUtils.simpleMatch(patterns, str);
		System.out.println(bb);
		String pattern="*";
		/**
		 * xx 匹配 * 、*xx 、*xx* 、xx* 、*xx*y 
		 */
		//如果pattern为*,则所有匹配成功
		boolean b=PatternMatchUtils.simpleMatch(pattern, str);
		System.out.println(b);
		pattern="*xx";
		b=PatternMatchUtils.simpleMatch(pattern, str);
		//如果pattern为*xx,则匹配成功
		System.out.println(b);
		pattern="xx*";
		b=PatternMatchUtils.simpleMatch(pattern, str);
		//如果pattern为xx*,则匹配成功
		System.out.println(b);
		pattern="*xx*";
		//如果pattern为*xx*,则匹配成功
		b=PatternMatchUtils.simpleMatch(pattern, str);
		System.out.println(b);
		pattern="*xx*";
		//如果pattern为xx*y,则匹配成功
		b=PatternMatchUtils.simpleMatch(pattern, str);
		System.out.println(b);
		str="123";
		pattern="*";
		b=PatternMatchUtils.simpleMatch(pattern, str);
		System.out.println(b);
		pattern="*123";
		b=PatternMatchUtils.simpleMatch(pattern, str);
		System.out.println(b);
		pattern="*123*";
		b=PatternMatchUtils.simpleMatch(pattern, str);
		System.out.println(b);
		pattern="123*";
		b=PatternMatchUtils.simpleMatch(pattern, str);
		System.out.println(b);
		
	}

}
