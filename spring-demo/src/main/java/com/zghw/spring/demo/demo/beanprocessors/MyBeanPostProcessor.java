package com.zghw.spring.demo.demo.beanprocessors;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class MyBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor,MergedBeanDefinitionPostProcessor {
	//定义在spring实例化前是否要进行后置处理，如果为false继续实例化，不执行postProcessBeforeInstantiation方法
	//如果为true则代表不继续实例化bean直接返回postProcessBeforeInstantiation给定的对象不能为空。
	private boolean isProcessAfterInstantiation=true;
	
	/**
	 * 在bean创建生命周期中，实例化前进行的后置处理，
	 * 给定beanClass类型及beanName，你可以自己代理一个新的对象返回，
	 * 或者也可以返回null，只处理一些简单操作，这里面可以扩展的你想得到的就取做吧。
	 */
	public Object postProcessBeforeInstantiation(Class<?> beanClass,
			String beanName) throws BeansException {
		System.out.println("postProcessBeforeInstantiation");
		Method[] methods = beanClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals("createUser")) {
				System.out.println("sdfsd");
				//设置不需要spring为我们实例化对象了，我们自己已经创建了， 直接返回这个给定的对象
				isProcessAfterInstantiation=false;
				return (Object) "我是一个string 哈哈哈! 你没被实例化我就给你返回了，和和 我代理了这个对象";
			}
		}

		return null;
	}
	/**
	 * 给定合成过的RootBeanDefinition进行后置处理，可以用RootBeanDefinition验证一些定义过的信息，使用这个
	 * RootBeanDefinition处理一些需要的业务逻辑都行。
	 */
	public void postProcessMergedBeanDefinition(
			RootBeanDefinition beanDefinition, Class<?> beanType,
			String beanName) {
		System.out.println("postProcessMergedBeanDefinition scope="+beanDefinition.getScope());
	}
	/**
	 * 在对象实例化后你可以做一些操作，对实例化的对象的进行处理。
	 * 处理后你返回是否需要继续初始化工作，如果为false，则直接返回该对象，
	 * 如果为true继续下面的工作。
	 */
	public boolean postProcessAfterInstantiation(Object bean, String beanName)
			throws BeansException {
		System.out.println("postProcessAfterInstantiation");
		return isProcessAfterInstantiation;
	}

	public PropertyValues postProcessPropertyValues(PropertyValues pvs,
			PropertyDescriptor[] pds, Object bean, String beanName)
			throws BeansException {
		System.out.println("postProcessPropertyValues");
		return pvs;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("postProcessBeforeInitialization");
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("postProcessAfterInitialization");
		return bean;
	}
	public Class<?> predictBeanType(Class<?> beanClass, String beanName)
			throws BeansException {
		return null;
	}
	public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass,
			String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getEarlyBeanReference(Object bean, String beanName)
			throws BeansException {
		System.out.println("getEarlyBeanReference 提早曝光");
		return bean;
	}

}
