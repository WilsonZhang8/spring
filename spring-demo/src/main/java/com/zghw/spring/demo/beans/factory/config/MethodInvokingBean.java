package com.zghw.spring.demo.beans.factory.config;

import com.zghw.spring.demo.beans.ArgumentConvertingMethodInvoker;
import com.zghw.spring.demo.beans.factory.BeanClassLoaderAware;
import com.zghw.spring.demo.beans.factory.BeanFactoryAware;
import com.zghw.spring.demo.beans.factory.InitializingBean;

public class MethodInvokingBean extends ArgumentConvertingMethodInvoker
implements BeanClassLoaderAware, BeanFactoryAware, InitializingBean  {

}
