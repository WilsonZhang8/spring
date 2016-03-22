package com.zghw.spring.demo.context;

import com.zghw.spring.demo.beans.factory.HierarchicalBeanFactory;
import com.zghw.spring.demo.beans.factory.ListableBeanFactory;
import com.zghw.spring.demo.core.env.EnvironmentCapable;


public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, HierarchicalBeanFactory,
MessageSource, ApplicationEventPublisher, ResourcePatternResolver{

}
