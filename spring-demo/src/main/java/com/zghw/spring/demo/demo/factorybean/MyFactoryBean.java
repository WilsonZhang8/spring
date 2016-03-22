package com.zghw.spring.demo.demo.factorybean;

import org.springframework.beans.factory.FactoryBean;

import com.zghw.spring.demo.demo.Computer;
/**
 * Factory Bean 就像简单工厂创建一个bean
 * 其中方法 getbject()返回的对象才是spring BeanFactory的getBean返回的对象
 * 而不是当前类，如果要取得当前类MyfactoryBean对应的bean，则需要&beanName返回的才是
 * 该bean。
 * 用途：主要用在组件对象的创建，比如一个jndi对象或者一个Property属性解析器，所以你要使用时，
 * 最好该对象是一个组件比较好。简单的对象不要使用该方式。
 * @author zghw
 *
 */
public class MyFactoryBean implements FactoryBean<Computer> {

	/**
	 * 通过getBean，工厂会返回该bean对象作为该类的对象
	 */
	public Computer getObject() throws Exception {
		Computer cm =new Computer();
		cm.setTest("testFactory bean");
		return cm;
	}
	/**
	 * 返回bean的类型
	 */
	public Class<?> getObjectType() {
		return Computer.class;
	}

	/**
	 * 返回工厂方法的bean对象是否是单例的。
	 */
	public boolean isSingleton() {
		return false;
	}

}
