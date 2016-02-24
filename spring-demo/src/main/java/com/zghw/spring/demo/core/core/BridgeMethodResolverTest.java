package com.zghw.spring.demo.core.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.MethodCallback;
import org.springframework.util.ReflectionUtils.MethodFilter;

import com.zghw.spring.demo.core.core.source.Lis;

/**
 * 泛型生成桥梁方法解析器
 * 
 * @author zghw
 *
 */
public class BridgeMethodResolverTest {

	public static void main(String[] args) {
		/*
		 * Method[] methods
		 * =ReflectionUtils.getAllDeclaredMethods(BridgeMethod.class);
		 * for(Method m :methods){
		 * //System.out.println(BridgeMethodResolver.findBridgedMethod
		 * (m).getName()); }
		 */
		final List<Method> methods1 = new ArrayList<Method>();
		ReflectionUtils.doWithMethods(Lis.class, new MethodCallback() {
			public void doWith(Method method) throws IllegalArgumentException,
					IllegalAccessException {
				methods1.add(method);
			}
		}, new MethodFilter() {
			public boolean matches(Method method) {
				//过滤掉Object方法
				return method.getDeclaringClass() != Object.class;
			}
		});
		/**
		 * 为什么会出现桥接方法
		 * 就是说一个子类在继承（或实现）一个父类（或接口）的泛型方法时，在子类中明确指定了泛型类型，
		 * 那么在编译时编译器会自动生成桥接方法（当然还有其他情况会生成桥接方法，这里只是列举了其中一种情况）
		 * 那么往list中可以添加任何类型的对象，但是在从集合中获取对象时，无法确定获取到的对象是什么具体的类型，
		 * 所以在1.5的时候引入了泛型，在声明集合的时候就指定集合中存放的是什么类型的对象：
		 */
		Method m1=null;
		Method m2=null;
		int i=0;
		for (Method m : methods1) {
			if(i==0){
				m1=m;
			}else if(i==1){
				m2=m;
			}
			i++;
			//两个方法 一个定义的方法一个是桥接方法
			System.out.println(m.getName() + " isBrideg:"+m.isBridge()+",");
		}
		
		System.out.println(BridgeMethodResolver.isVisibilityBridgeMethodPair(m1, m2));
		System.out.println(BridgeMethodResolver.isVisibilityBridgeMethodPair(m2, m1));
		
		for (Method m : methods1) {
			System.out.println("转化前："+m.getName() + " isBrideg:"+m.isBridge()+",");
			//把桥接方法转换为真实方法
			Method declar =BridgeMethodResolver.findBridgedMethod(m);
			System.out.println("转化后："+declar.getName() + " isBrideg:"+declar.isBridge()+",");
		}
		/**
		 * 判断桥接方法的主要原理：
		 * 循环该类中的所有方法，判断方法名相等，参数个数相等
		 */
		
	}

}
