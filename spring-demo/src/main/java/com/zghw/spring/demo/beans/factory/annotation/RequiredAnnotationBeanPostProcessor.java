package com.zghw.spring.demo.beans.factory.annotation;

import com.zghw.spring.demo.beans.factory.BeanFactoryAware;
import com.zghw.spring.demo.beans.factory.config.*;
import com.zghw.spring.demo.beans.factory.support.*;
import com.zghw.spring.demo.core.PriorityOrdered;

public class RequiredAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter
implements MergedBeanDefinitionPostProcessor, PriorityOrdered, BeanFactoryAware  {

	public int getOrder() {
		return 0;
	}

}
