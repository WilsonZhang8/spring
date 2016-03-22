package com.zghw.spring.demo.demo.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyInit implements InitializingBean,DisposableBean,BeanPostProcessor {

	public void afterPropertiesSet() throws Exception {
		System.out.println("init:生命周期InitializingBean = afterPropertiesSet");
	}

	public void destroy() throws Exception {
		System.out.println("destroy:生命周期DisposableBean = destroy");
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("afterInit:生命周期 BeanPostProcessor= postProcessAfterInitialization");
		return bean;
	}

}
