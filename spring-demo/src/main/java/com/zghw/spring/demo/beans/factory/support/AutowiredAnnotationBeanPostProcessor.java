package com.zghw.spring.demo.beans.factory.support;

import com.zghw.spring.demo.beans.factory.BeanFactoryAware;
import com.zghw.spring.demo.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import com.zghw.spring.demo.core.PriorityOrdered;

public abstract class AutowiredAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter
implements MergedBeanDefinitionPostProcessor, PriorityOrdered, BeanFactoryAware  {

}
