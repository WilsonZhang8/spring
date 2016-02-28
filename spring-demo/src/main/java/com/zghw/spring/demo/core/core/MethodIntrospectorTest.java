package com.zghw.spring.demo.core.core;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.MethodIntrospector;
import org.springframework.core.MethodIntrospector.MetadataLookup;
import org.springframework.util.ReflectionUtils;

import com.zghw.spring.demo.core.core.source.ApproximableList;
import com.zghw.spring.demo.core.core.source.User;
/**
 * 取得类型中所有方法和注入数据
 * 过滤类型中的方法
 * 用处：类中方法注入数据、类中的方法过滤、类中的代理方法转化为接口方法
 * @author zghw
 *
 */
public class MethodIntrospectorTest {

	public static void main(String[] args) {
		// 为每一个定义的方法注入一个数据，形成一个map对象 key是方法，value是注入值
		Map<Method, Integer> m = MethodIntrospector.selectMethods(
				ApproximableList.class, new MetadataLookup<Integer>() {
					int i = 0;

					public Integer inspect(Method method) {
						return ++i;
					}
				});
		for (Map.Entry<Method, Integer> me : m.entrySet()) {
			System.out.println(me.getKey().getName() + " " + me.getValue());
		}

		/**
		 * 提供一个过滤器，过滤类型对应的方法
		 */
		Set<Method> sets = MethodIntrospector.selectMethods(
				ApproximableList.class, new ReflectionUtils.MethodFilter() {
					public boolean matches(Method method) {
						return (method.getDeclaringClass() != Object.class)
								&& (method.getDeclaringClass() != List.class)&&(method.getName()!="add");
					}

				});
		for(Method ms : sets){
			System.out.print(ms.getName() + ", " );
		}
		
		/**
		 * 如果是代理方法转换为目标接口方法 方法是否是代理，如果是代理方法则转换为目标接口方法
		 */
		MethodIntrospector.selectInvocableMethod(ReflectionUtils.findMethod(User.class, "a"),User.class);
	}

}
