package com.zghw.spring.demo.demo;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.zghw.spring.demo.demo.beanprocessors.MyBeanPostProcessor;

public class BeanPostProcessorTest {
	public static void  f(Object obj){
		System.out.println(obj);
	}
	public static void main(String[] args) {
		// 创建资源加载器
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		// 创建资源器
		Resource resource = resourceLoader
				.getResource("application-test-beanfactory.xml");
		// 创建一个bean工厂
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		//循环依赖设置
		beanFactory.setAllowCircularReferences(true);
		//创建一个demo后置处理器
		MyBeanPostProcessor myPostProcessor =new MyBeanPostProcessor();
		//设置bean表达解析器
		beanFactory.setBeanExpressionResolver(new StandardBeanExpressionResolver());
		//把处理器给工厂使用
		beanFactory.addBeanPostProcessor(myPostProcessor);
		// 创建一个xml读取器
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(
				beanFactory);
		// 加载spring xml文件资源
		beanDefinitionReader.loadBeanDefinitions(resource);
		/**
		 * Aware实现
		 */
		f(beanFactory.getBean("myAware"));
		/**
		 * 使用&+beanName访问的是FactoryBean实例返回的当前bean，否则使用beanName就是工厂方法getObject()返回的对象。
		 */
		f(beanFactory.getBean("&myFactoryBean"));
	}
}
