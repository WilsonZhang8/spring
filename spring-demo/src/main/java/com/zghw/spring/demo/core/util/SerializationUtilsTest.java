package com.zghw.spring.demo.core.util;

import org.springframework.util.SerializationUtils;

/**
 * 序列化一个对象
 * @author zghw
 *
 */
public class SerializationUtilsTest {

	public static void main(String[] args) {
		//序列化对象
		User u=new User();
		u.setAge(100);
		byte[]  b=SerializationUtils.serialize(u);
		
		//反序列化对象
		User user=(User)SerializationUtils.deserialize(b);
		System.out.println(user.getAge());
	}

}
