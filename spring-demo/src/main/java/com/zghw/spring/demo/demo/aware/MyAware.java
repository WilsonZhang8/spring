package com.zghw.spring.demo.demo.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;

public class MyAware implements BeanNameAware,BeanClassLoaderAware,BeanFactoryAware{

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("调用BeanFactoryAware： ");
	}

	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("调用BeanClassLoaderAware:" + classLoader.getResource(""));
	}

	public void setBeanName(String name) {
		System.out.println("调用BeanClassLoaderAware:" +name);
		
	}

}
