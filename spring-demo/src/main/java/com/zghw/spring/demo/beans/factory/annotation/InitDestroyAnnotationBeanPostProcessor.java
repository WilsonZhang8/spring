package com.zghw.spring.demo.beans.factory.annotation;

import java.io.Serializable;

import com.zghw.spring.demo.beans.factory.config.DestructionAwareBeanPostProcessor;
import com.zghw.spring.demo.beans.factory.support.MergedBeanDefinitionPostProcessor;
import com.zghw.spring.demo.core.PriorityOrdered;


public class InitDestroyAnnotationBeanPostProcessor implements DestructionAwareBeanPostProcessor, MergedBeanDefinitionPostProcessor, PriorityOrdered, Serializable {

	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
