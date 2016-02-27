package com.zghw.spring.demo.core.core.annonation;

import java.awt.List;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Fruit {
	@AliasFor("desc")//desc 要和value值一致
	public String value() default "";

	// 基本类型String
	@AliasFor("value")//desc 要和value值一致
	public String desc() default "";

	// 基本类型
	public int price() default 0;
	
	// 基本类型boolean
	public boolean boo() default false;

	// 基本类型double
	public double dou() default 2323.22;

	// 数组类型
	public String[] names() default {};

	// 枚举类型
	public RetentionPolicy policy() default RetentionPolicy.SOURCE;

	// Class类型
	public Class<?> list() default List.class;

	// 注解类型
	public Autowired aw() default @Autowired;

}
