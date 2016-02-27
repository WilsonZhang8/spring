package com.zghw.spring.demo.core.core.annonation;

import java.awt.List;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Autowired;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Test {
	// 数组类型
	public String[] names() default {};

	// 枚举类型
	public RetentionPolicy policy() default RetentionPolicy.SOURCE;

	// Class类型
	public Class<?> list() default List.class;

	// 注解类型
	public Autowired aw() default @Autowired;

}
