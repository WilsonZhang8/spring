package com.zghw.spring.demo.demo.factory;

public class Bean1 {
	public Bean2 getBean2() {
		return bean2;
	}

	public void setBean2(Bean2 bean2) {
		this.bean2 = bean2;
	}

	private Bean2 bean2;
}
