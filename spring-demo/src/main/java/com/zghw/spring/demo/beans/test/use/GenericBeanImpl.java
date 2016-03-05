package com.zghw.spring.demo.beans.test.use;

public class GenericBeanImpl implements GenericBean<Bean1, Bean2> {

	public Bean1 getA() {
		return new Bean1();
	}

	public Bean2 getB() {
		return new Bean2();
	}

}
