package com.zghw.spring.demo.core.core;

import org.springframework.core.AliasRegistry;
import org.springframework.core.SimpleAliasRegistry;

/**
 * 别名注册示例
 * 
 * @author zghw
 *
 */
public class AliasRegistryTest {

	public static void main(String[] args) {
		AliasRegistry ar = new SimpleAliasRegistry();
		// 注册一个别名
		ar.registerAlias("zhanghognwei", "zhangsan");
		ar.registerAlias("ligang", "lisi");
		ar.registerAlias("zhangsan", "zhangweixiang");
		ar.registerAlias("zghw", "zhangsan");
		// 判断是否是别名
		System.out.println(ar.isAlias("lisi"));
		// 查询别名
		String[] alias = ar.getAliases("zhangsan");
		for (String a : alias) {
			System.out.println(a);
		}
		//移除别名
		ar.removeAlias("lisi");
		System.out.println(ar.isAlias("lisi"));
	}

}
