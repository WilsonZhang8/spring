package com.zghw.spring.demo.context.event;

import com.zghw.spring.demo.beans.factory.BeanClassLoaderAware;
import com.zghw.spring.demo.beans.factory.BeanFactoryAware;
import com.zghw.spring.demo.context.config.ApplicationEventMulticaster;


public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanClassLoaderAware, BeanFactoryAware  {

}
