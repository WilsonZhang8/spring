package com.zghw.spring.demo.beans.test;

public class BeanDefinitionRegistryTest {

	public static void main(String[] args) {
		/**
		 * BeanDefinitionRegistry接口继承了AliasRegistry接口
		 * 这个接口注册一个bean的定义
		 * 比如RootBeanDefinition的实例 和ChildBeanDefinition实例
		 * spring工厂内部一个唯一包装了bean 定义的接口。
		 * 访问只需要访问bean工厂中的配置工厂就行比如DefaultListableBeanFactory
		 * 接口方法：
		 * 1.注册一个beanDefinition
		 * 2.删除一个beanDefinition
		 * 3.返回一个beanDefinition
		 * 4.返回是否存在beanDefinition
		 * 5.返回beanDefinition的名字数组
		 * 6.得到beanDefinition的数量
		 * 7.给定的beanname是否已经注册
		 * SimpleBeanDefinitionRegistry类实现了BeanDefinitionRegistry
		 * 使用了线程安全的map来管理beanDefinition注册表
		 * 
		 * DefaultListableBeanFactory内部也是用线程安全的map来管理beanDefinition注册表的
		 */
	}

}
