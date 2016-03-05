package com.zghw.spring.demo.beans.factory.config;

import java.util.Map;

import com.zghw.spring.demo.beans.factory.FactoryBean;
import com.zghw.spring.demo.beans.factory.InitializingBean;

public class YamlMapFactoryBean  extends YamlProcessor implements FactoryBean<Map<String, Object>>, InitializingBean {

}
