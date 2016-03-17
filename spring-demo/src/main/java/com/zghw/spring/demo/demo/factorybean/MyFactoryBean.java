package com.zghw.spring.demo.demo.factorybean;

import org.springframework.beans.factory.FactoryBean;

import com.zghw.spring.demo.demo.Computer;

public class MyFactoryBean implements FactoryBean<Computer> {

	public Computer getObject() throws Exception {
		Computer cm =new Computer();
		cm.setTest("testFactory bean");
		return cm;
	}

	public Class<?> getObjectType() {
		return Computer.class;
	}

	public boolean isSingleton() {
		return false;
	}

}
