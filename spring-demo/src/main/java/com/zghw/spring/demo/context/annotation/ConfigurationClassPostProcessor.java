package com.zghw.spring.demo.context.annotation;

import com.zghw.spring.demo.beans.factory.BeanClassLoaderAware;
import com.zghw.spring.demo.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import com.zghw.spring.demo.context.EnvironmentAware;
import com.zghw.spring.demo.context.ResourceLoaderAware;
import com.zghw.spring.demo.core.PriorityOrdered;


public class ConfigurationClassPostProcessor implements BeanDefinitionRegistryPostProcessor,
PriorityOrdered, ResourceLoaderAware, BeanClassLoaderAware, EnvironmentAware{

	public int getOrder() {
		return 0;
	}

}
