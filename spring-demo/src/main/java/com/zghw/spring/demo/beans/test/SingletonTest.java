package com.zghw.spring.demo.beans.test;

public class SingletonTest {

	public static void main(String[] args) {
		/**
		 * SingletonBeanRegistry接口
		 * 定义了注册一个共享单个的bean实例。实现beanfactory然后实现它，BeanFactory
		 * 实例可以管理这些实例。
		 * 接口方法：
		 * 1.名字+对象注册一个单例
		 * 2.通过名称得到一个单例
		 * 3.是否包含一个给定名称的单例
		 * 4.得到单例名称的数组
		 * 5.单例的数量
		 * 6.返回单例的互斥对象
		 * 
		 * DefaultSingletonBeanRegistry是SingletonBeanRegistry的默认实现，并且继承了SimpleAliasRegistry
		 * 它从ObjectFactory中取的对象，放入自己定义的Map集合中，并且使用同步方式来访问该对象
		 * 它使用了多Map缓存来处理单例的管理
		 * 
		 */
	}

}
