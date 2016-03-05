package com.zghw.spring.demo.beans.factory.config;

import java.util.Properties;

import com.zghw.spring.demo.beans.factory.FactoryBean;
import com.zghw.spring.demo.beans.factory.InitializingBean;

public class YamlPropertiesFactoryBean  extends YamlProcessor implements FactoryBean<Properties>, InitializingBean {

}
