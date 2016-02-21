package com.zghw.spring.demo.core.util;

import java.util.UUID;

import org.springframework.util.*;

/**
 * UUID生成器
 * @author zghw
 *
 */
public class IdGeneratorTest {

	public static void main(String[] args) {
		//0开始生成器每次加1 线程安全来生成UUID
		IdGenerator ig =new SimpleIdGenerator();
		UUID uuid =ig.generateId(); 
		System.out.println(uuid);
		//java生成普通的UUID
		ig = new JdkIdGenerator();
		UUID uuid2 =ig.generateId();
		System.out.println(uuid2);
		//安全的UUID
		ig = new AlternativeJdkIdGenerator();
		UUID uuid3 =ig.generateId();
		System.out.println(uuid3);
	}

}
