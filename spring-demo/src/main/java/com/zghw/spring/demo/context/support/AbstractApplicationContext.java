package com.zghw.spring.demo.context.support;

import com.zghw.spring.demo.beans.factory.DisposableBean;
import com.zghw.spring.demo.context.ConfigurableApplicationContext;
import com.zghw.spring.demo.core.io.*;
public abstract class AbstractApplicationContext extends DefaultResourceLoader
implements ConfigurableApplicationContext, DisposableBean {

}
