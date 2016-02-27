package com.zghw.spring.demo.core.core;

import org.springframework.core.style.StylerUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.StandardClassMetadata;
import org.springframework.stereotype.Service;

import com.zghw.spring.demo.core.core.source.TypeClass;
import com.zghw.spring.demo.core.core.source.TypeClassImple;

public class TestType {
	static void f(Object obj) {
		System.out.println(obj);
	}
	public static void main(String[] args) {
		//使用标准的clas类型获取Metadata
		ClassMetadata cm =new StandardClassMetadata(TypeClassImple.class);
		f("类型："+StylerUtils.style(cm.getClass()));
		f("类名："+cm.getClassName());
		f("该类如果不是内部类则返回false： "+cm.hasEnclosingClass());
		f("包含该类的外部类，如果当前的类是内部类。否则返回null："+cm.getEnclosingClassName());
		f("实现的所有接口:"+StylerUtils.style(cm.getInterfaceNames()));
		f("内部类不包括继承的内部类："+StylerUtils.style(cm.getMemberClassNames()));
		f("该类型有无超类："+cm.hasSuperClass());
		f("超类："+StylerUtils.style(cm.getSuperClassName()));
		f("是否是抽象类："+cm.isAbstract());
		f("是否是注解："+cm.isAnnotation());
		f("是否是接口："+cm.isInterface());
		f("此类即不是接口也不是抽象类？"+cm.isConcrete());
		f("是否是final类："+cm.isFinal());
		f("是否是静态类或者是一个最外部类："+cm.isIndependent());
		AnnotationMetadata am=new StandardAnnotationMetadata(TypeClassImple.class);
		f("是否被指定的注解类型注解："+am.hasAnnotation("org.springframework.stereotype.Service"));
		//注解名需全名
		f("是否被指定的注解类型注解过方法："+am.hasAnnotatedMethods("org.springframework.beans.factory.annotation.Autowired"));
		f("是否被指定的注解类型注解过方法："+am.hasMetaAnnotation("org.springframework.stereotype.Service"));
		//使用标准的方法类型获取Metadata
		//MethodMetadata mm = new 
		
	}

}
