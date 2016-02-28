package com.zghw.spring.demo.core.core;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.style.StylerUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.StandardClassMetadata;
import org.springframework.core.type.StandardMethodMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ReflectionUtils;

import com.zghw.spring.demo.core.core.source.TypeClassImple;
/**
 * 用处：用于取的Class及注解的元数据
 * 
 * 这个core中的一个重点
 * 两种方式来注入元数据获得类、注解、方法元数据信息
 * 1.使用ASM方式
 * 2.使用标准的反射
 * 以下两个例子演示了
 * @author zghw
 *
 */
public class TestType {
	static void f(Object obj) {
		System.out.println(obj);
	}
	static void f1(Object obj) {
		System.out.println(obj);
	}
	public static void main(String[] args) throws IOException {
		//
		MetadataReaderFactory mrf =new SimpleMetadataReaderFactory();
		String path="com.zghw.spring.demo.core.core.source.TypeClassImple";
		MetadataReader mr=mrf.getMetadataReader(path);
		f("###################### ASM方式得到类信息###################################");
		ClassMetadata cmm=mr.getClassMetadata();
		getClassInfo(cmm);
		f("###################### ASM方式得到注解信息 不会出现继承的###################################");
		AnnotationMetadata am=mr.getAnnotationMetadata();
		getAnnotationInfo(am);
		f("###################### ASM方式得到方法信息###################################");
		String doAnnotation="com.zghw.spring.demo.core.core.annonation.Do";
		Set<MethodMetadata> setMethod = am.getAnnotatedMethods(doAnnotation);
		for(MethodMetadata medata:setMethod){
			System.out.println("-----被注解的方法："+medata.getMethodName()+"--------");
			getMethodInfo(medata);
		}
		
		// 使用标准的clas类型获取Metadata
		ClassMetadata cm = new StandardClassMetadata(TypeClassImple.class);
		f("######################反射得到类信息###################################");
		// 反射得到类信息
		getClassInfo(cm);
		// 使用标准的注解获取Metadata
		AnnotationMetadata annonm = new StandardAnnotationMetadata(
				TypeClassImple.class);
		// getClassInfo(annonm);
		f("########################反射得到注解信息#################################");
		// 反射得到注解信息
		getAnnotationInfo(annonm);
		// 使用标准的方法类型获取Metadata
		Method method2 = ReflectionUtils.findMethod(TypeClassImple.class,
				"methodTwo", int.class, String.class);
		MethodMetadata mm = new StandardMethodMetadata(method2);
		f("############################反射得到方法信息及注解信息#############################");
		//反射得到方法信息及注解信息
		getMethodInfo(mm);

	}
	//反射得到方法信息及注解信息
	private static void getMethodInfo(MethodMetadata mm) {
		String fruitAnnonationName = "com.zghw.spring.demo.core.core.annonation.Fruit";
		f("得到类对象：" + mm.getClass());
		f("得到类名：" + mm.getDeclaringClassName());
		f("得到方法名" + mm.getMethodName());
		f("是否是抽象方法" + mm.isAbstract());
		f("是否是final方法" + mm.isFinal());
		f("是否是静态方法：" + mm.isStatic());
		f("是否是重载方法：" + mm.isOverridable());
		f("该方法上是否存在该注解："
				+ mm.isAnnotated(fruitAnnonationName)
				+ "  do="
				+ mm.isAnnotated("com.zghw.spring.demo.core.core.annonation.Do"));
		f("得到返回类型名称：" + mm.getReturnTypeName());
		Map<String, List<Object>> mvm = mm
				.getAllAnnotationAttributes(fruitAnnonationName);
		f("得到该方法被注解的集合：" + mvm);
		mvm = mm.getAllAnnotationAttributes(
				"com.zghw.spring.demo.core.core.annonation.Do", true);
		f("得到该方法被注解的集合（保存map中的对象为字符串，加快访问）：" + mvm);
		Map<String, Object> map = mm
				.getAnnotationAttributes("com.zghw.spring.demo.core.core.annonation.Do");
		f("得到该方法被注解的集合：" + map);
		map = mm.getAnnotationAttributes(
				"com.zghw.spring.demo.core.core.annonation.Test", true);
		f("得到该方法被注解的集合（保存map中的对象为字符串，加快访问）：" + map);
	}

	// 反射得到注解信息
	private static void getAnnotationInfo(AnnotationMetadata annonm) {
		String fruitAnnonationName = "com.zghw.spring.demo.core.core.annonation.Fruit";
		Map<String, List<Object>> mvm = annonm
				.getAllAnnotationAttributes(fruitAnnonationName);
		f("得到fruit注解对应的所有注解属性Map：                    " + mvm);
		mvm = annonm.getAllAnnotationAttributes(fruitAnnonationName, true);
		f("得到fruit注解对应的所有注解属性Map(包含的对象为class字符串保存在map中)：" + mvm);

		Map<String, Object> map = annonm
				.getAnnotationAttributes(fruitAnnonationName);
		f("得到fruit注解对应的注解属性Map                      ：" + map);
		map = annonm.getAnnotationAttributes(fruitAnnonationName, true);
		f("得到fruit注解对应的注解属性Map(包含的对象为class字符串保存在map中)：" + map);
		Set<String> sets = annonm.getAnnotationTypes();
		f("得到注解对象类型集合：" + sets);
		f("得到注解上的元注解" + annonm.getMetaAnnotationTypes(fruitAnnonationName));
		f("判断是否方法是否存在注解：" + annonm.hasAnnotatedMethods(fruitAnnonationName));
		f("判断元素内是否存在注解：" + annonm.hasAnnotation(fruitAnnonationName));
		f("判断元素是否被注解：" + annonm.isAnnotated(fruitAnnonationName));
		Set<MethodMetadata> setmehtod = annonm
				.getAnnotatedMethods("org.springframework.beans.factory.annotation.Autowired");
		f("该类型方法注解类型集合：" + setmehtod);
	}

	// 基本的class信息
	private static void getClassInfo(ClassMetadata cm) {
		f("类型：" + StylerUtils.style(cm.getClass()));
		f("类名：" + cm.getClassName());
		f("该类如果不是内部类则返回false： " + cm.hasEnclosingClass());
		f("包含该类的外部类，如果当前的类是内部类。否则返回null：" + cm.getEnclosingClassName());
		f("实现的所有接口:" + StylerUtils.style(cm.getInterfaceNames()));
		f("内部类不包括继承的内部类：" + StylerUtils.style(cm.getMemberClassNames()));
		f("该类型有无超类：" + cm.hasSuperClass());
		f("超类：" + StylerUtils.style(cm.getSuperClassName()));
		f("是否是抽象类：" + cm.isAbstract());
		f("是否是注解：" + cm.isAnnotation());
		f("是否是接口：" + cm.isInterface());
		f("此类即不是接口也不是抽象类？" + cm.isConcrete());
		f("是否是final类：" + cm.isFinal());
		f("是否是静态类或者是一个最外部类：" + cm.isIndependent());
		AnnotationMetadata am = new StandardAnnotationMetadata(
				TypeClassImple.class);
		f("是否被指定的注解类型注解："
				+ am.hasAnnotation("org.springframework.stereotype.Service"));
		// 注解名需全名
		f("是否被指定的注解类型注解过方法："
				+ am.hasAnnotatedMethods("org.springframework.beans.factory.annotation.Autowired"));
		f("是否被指定的注解类型注解过方法："
				+ am.hasMetaAnnotation("org.springframework.stereotype.Service"));
	}

}
