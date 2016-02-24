package com.zghw.spring.demo.core.util;

import java.util.Map;

import org.springframework.util.SystemPropertyUtils;

/**
 * 使用系统环境值，来代替占位符信息
 * @author zghw
 *
 */
public class SystemPropertyUtilsTest {

	public static void main(String[] args) {
		//使用系统环境变量值替换占位符来生成字符串
		String info=SystemPropertyUtils.resolvePlaceholders("运行时环境：${java.runtime.name}");
		info+=SystemPropertyUtils.resolvePlaceholders("classpath路径：${java.class.path}");
		System.out.println(info);
		//运行时环境变量所有值
		for(Map.Entry<Object,Object> map : System.getProperties().entrySet()){
			System.out.println(map.getKey()+"\t"+map.getValue());
		}
	}

}
