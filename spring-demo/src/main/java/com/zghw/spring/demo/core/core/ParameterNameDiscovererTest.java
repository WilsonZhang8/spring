package com.zghw.spring.demo.core.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import com.zghw.spring.demo.core.util.User;
/**
 *  用处：得到方法或构造方法中的参数名称
 *  
 * 取得方法及构造方法中参数定义的名称
 * @author zghw
 *
 */
public class ParameterNameDiscovererTest {
	public ParameterNameDiscovererTest(String str, int inin, User usr) {

	}

	public static void main(String[] args) throws NoSuchMethodException,
			SecurityException {
		ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
		/**
		 * 根据方法对象获取方法的参数定义的名称
		 */
		Method method = ReflectionUtils.findMethod(
				MethodConstructorParamName.class, "method1", String.class,
				int.class, User.class);
		String[] strs = pnd.getParameterNames(method);
		for (String s : strs) {
			System.out.print(s + ",");
		}
		/**
		 * 获取构造方法的参数定义的名称 类 及构造方法必须是publish
		 */
		Constructor c = ClassUtils.getConstructorIfAvailable(
				ParameterNameDiscovererTest.class, String.class, int.class,
				User.class);

		String[] strc = pnd.getParameterNames(c);
		for (String s : strc) {
			System.out.print(s + ",");
		}
	}

	class MethodConstructorParamName {
		private void method1(String str, int inin, User usr) {

		}
	}
}
