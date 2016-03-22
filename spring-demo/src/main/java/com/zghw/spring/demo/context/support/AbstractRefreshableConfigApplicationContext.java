package com.zghw.spring.demo.context.support;

import com.zghw.spring.demo.beans.factory.BeanNameAware;
import com.zghw.spring.demo.beans.factory.InitializingBean;

public abstract class AbstractRefreshableConfigApplicationContext extends AbstractRefreshableApplicationContext
implements BeanNameAware, InitializingBean {

}
