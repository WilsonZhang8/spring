package com.zghw.spring.demo.core.core.annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Todo {
	@AliasFor("desc")//desc 要和value值一致
	public String value() default "";

	// 基本类型String
	@AliasFor("value")//desc 要和value值一致
	public String desc() default "";
}
