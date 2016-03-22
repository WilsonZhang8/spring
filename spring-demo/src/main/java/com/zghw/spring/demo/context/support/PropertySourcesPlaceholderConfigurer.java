package com.zghw.spring.demo.context.support;

import com.zghw.spring.demo.beans.factory.config.PlaceholderConfigurerSupport;
import com.zghw.spring.demo.context.EnvironmentAware;

public class PropertySourcesPlaceholderConfigurer  extends PlaceholderConfigurerSupport implements EnvironmentAware {

	public int getOrder() {
		return 0;
	}

}
