package com.zghw.spring.demo.context.annotation;

import com.zghw.spring.demo.beans.factory.BeanFactoryAware;
import com.zghw.spring.demo.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import com.zghw.spring.demo.beans.factory.config.InstantiationAwareBeanPostProcessor;

public class CommonAnnotationBeanPostProcessor extends InitDestroyAnnotationBeanPostProcessor
implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

}
