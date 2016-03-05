package com.zghw.spring.demo.beans.factory.annotation;

import com.zghw.spring.demo.core.Ordered;

import com.zghw.spring.demo.beans.factory.BeanClassLoaderAware;
import com.zghw.spring.demo.beans.factory.BeanFactoryPostProcessor;

public class CustomAutowireConfigurer  implements BeanFactoryPostProcessor, BeanClassLoaderAware, Ordered{

}
