package com.zghw.spring.demo.beans.factory.config;

import com.zghw.spring.demo.beans.factory.*;

public abstract class AbstractFactoryBean<T> implements FactoryBean<T>, BeanClassLoaderAware, BeanFactoryAware, InitializingBean, DisposableBean {

}
