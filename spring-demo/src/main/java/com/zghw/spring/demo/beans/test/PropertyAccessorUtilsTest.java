package com.zghw.spring.demo.beans.test;

import java.beans.*;

import com.zghw.spring.demo.beans.test.use.MyBean;

public class PropertyAccessorUtilsTest {
	static void f(Object obj){
		System.out.println(obj);
	}
	public static void main(String[] args) throws IntrospectionException {
		BeanInfo info=Introspector.getBeanInfo(MyBean.class);
		PropertyDescriptor[] pds=info.getPropertyDescriptors();
		for(PropertyDescriptor pd:pds){
			f("name "+pd.getDisplayName());
			f("name "+pd.getName());
		}
	}

}
