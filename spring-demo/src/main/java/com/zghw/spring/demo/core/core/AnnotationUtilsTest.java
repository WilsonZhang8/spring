package com.zghw.spring.demo.core.core;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.style.StylerUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;

import com.zghw.spring.demo.core.core.annonation.Fruit;
import com.zghw.spring.demo.core.core.source.TypeClassImple;

/**
 * 
 * 用处：使用反射查找注解信息
 * 
 * find*（）方法用于得到注解对象 多层次 包含了接口 继承的注解 简单的注解对象 get*()方法但层次
 * get方法取的多数为复合annotation对象
 * 
 * @author zghw
 *
 */
public class AnnotationUtilsTest {
	static void f(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		// ##############JDK基本使用####################

		// 得到注解集合
		Annotation[] annotationss = TypeClassImple.class.getAnnotations();
		// 得到注解类型
		Service s = TypeClassImple.class.getAnnotation(Service.class);
		// Class Method Field及构造方法都实现了AnnotatedElement接口可以转换
		AnnotatedElement ae = TypeClassImple.class;
		// 得到所有注解包括了继承来的
		annotationss = ae.getAnnotations();
		for (Annotation annotation : annotationss) {
			// f("注解对象："+annotation);
			// f("注解类型"
			// + annotation.annotationType()
			// .getAnnotation(Retention.class));
			for (Method method : annotation.annotationType()
					.getDeclaredMethods()) {
				// Object attributeValue = method.invoke(annotation);
				// Object defaultValue = method.getDefaultValue();
				// System.out.println(method.getName()+" ="+attributeValue+", default="+defaultValue);
				// f("得到注解属性"+annotation.getClass().getName()+"==="+ReflectionUtils.findMethod(annotation.annotationType(),
				// method.getName()));
			}

		}

		// f("指定注解是否直接存在该类上："+ae.isAnnotationPresent(Service.class));
		// 得到上面的所有注解，这个不包括@Inherited继承来的注解
		annotationss = ae.getDeclaredAnnotations();
		for (Annotation annotation : annotationss) {
			// f("注解对象："+annotation);
			// f("注解类型"+annotation.annotationType());
		}

		// ######################find()#####################

		// find*()方法流程
		// 1.使用AnnotatedElement创建一个key
		// 2.使用key从缓存中取出来Fruit
		// 3.不为空返回
		// 4.得到定义在AnnotatedElement上的annotation数组
		// 5.循环annotation数组与Fruit类型匹配成功则返回Fruit注解对象
		// 6.注解类上的注解匹配：如果匹配不成功 则取annotation数组中的每一个注解类上的每一个注解进行匹配，不包含JDK的元注解
		// 7，接口匹配：如果还不匹配就从接口中匹配
		// 8.最后在基类中匹配
		Fruit fruit = AnnotationUtils.findAnnotation(TypeClassImple.class,
				Fruit.class);
		f("这种方式查找可以查找到父类定义的：" + fruit);
		// 由于AnnotatedElement是接口 所以不会查找父类及接口
		AnnotatedElement elem = TypeClassImple.class;
		fruit = AnnotationUtils.findAnnotation(elem, Fruit.class);
		f("只能查找定义的注解不包含父类的及接口：" + fruit);// 当前类判断然后父类判断
		Class<?> declar = AnnotationUtils.findAnnotationDeclaringClass(
				Fruit.class, TypeClassImple.class);
		f("直接定义注解的类：" + declar);
		List<Class<? extends Annotation>> list = new ArrayList<Class<? extends Annotation>>();
		list.add(Service.class);
		list.add(Fruit.class);
		// 因为Service第一添加，在查找时会先查找到它在TypeClassImple中定义直接返回，不再查找Fruit注解
		declar = AnnotationUtils.findAnnotationDeclaringClassForTypes(list,
				TypeClassImple.class);
		f("查找集合中第一个匹配到直接定义类：" + declar);
		Method method2 = ReflectionUtils.findMethod(TypeClassImple.class,
				"methodTwo", int.class, String.class);
		Autowired aw = AnnotationUtils.findAnnotation(method2, Autowired.class);
		// Autowired具有继承 继承了父类TypeClass的Fruit上的Autowired
		f("查找到方法上的注解" + aw);
		f("是否是直接定义在该TypeClassImple类型上："
				+ AnnotationUtils.isAnnotationDeclaredLocally(Fruit.class,
						TypeClassImple.class));
		f("是否使用了@Inherited注解："
				+ AnnotationUtils.isAnnotationInherited(Fruit.class,
						TypeClassImple.class));
		f("是否使用了注解是来自java.lang.annonation 即是否是元注解："
				+ AnnotationUtils.isInJavaLangAnnotationPackage(Fruit.class
						.getAnnotations()[0]));
		f("是否使用了注解是来自java.lang.annotation 即是否是元注解："
				+ AnnotationUtils
						.isInJavaLangAnnotationPackage(TypeClassImple.class
								.getAnnotations()[0]));
		f("是否使用了注解是来自java.lang.annotation 即是否是元注解:"
				+ AnnotationUtils
						.isInJavaLangAnnotationPackage("java.lang.annotation.Inherited"));
		f("注解是否来自于元数据："
				+ AnnotationUtils.isAnnotationMetaPresent(Fruit.class,
						Autowired.class));
		f("注解是否来自于元数据："
				+ AnnotationUtils.isAnnotationMetaPresent(Fruit.class,
						Retention.class));

		// ######################get()#####################
		// ######################得到合成的注解对象#####################

		Class<Fruit> annotationType = Fruit.class;
		AnnotatedElement annotatedElement = TypeClassImple.class;
		fruit = AnnotationUtils.getAnnotation(annotatedElement, annotationType);
		fruit = AnnotationUtils.getAnnotation(fruit, annotationType);

		// annotatedElement 转化为 annonation 后 复合注解
		AnnotationUtils.getAnnotation(annotatedElement, annotationType);
		Annotation[] annotations = AnnotationUtils
				.getAnnotations(annotatedElement);
		// 方法转换为AnnotatedElement 转化为 annonation 后 复合注解
		AnnotationUtils.getAnnotation(method2, annotationType);
		annotations = AnnotationUtils.getAnnotations(method2);
		// 1.注解对象如果是注解类型的实例则返回注解对象 否则进入2
		fruit = AnnotationUtils.synthesizeAnnotation(annotationType);
		// 2.查找注解对象对应类型上的元注解,把元注解和被注解类型放入进行组合
		fruit = AnnotationUtils.synthesizeAnnotation(fruit.getClass()
				.getAnnotation(annotationType), Fruit.class);
		// 组合注解 数组
		Annotation[] syncs = AnnotationUtils.synthesizeAnnotationArray(
				TypeClassImple.class.getAnnotations(), TypeClassImple.class);
		// 合成注解synthesizeAnnotation 处理
		// 判断该注解对象是否是代理注解对象
		// 如果是该代理注解对象直接返回
		// 判断是否是组合注解对象：
		// 1.有别名注解在属性上
		// 2.属性的返回值为注解
		// 如果不是组合注解对象，则代理该注解对象
		// 代理一个annotation对象 该对象实现了对应的annotaion接口
		// 给代理注入一个提取器 该提取器保存了元素AnnotatedElement（如方法 类）和Annotation及annotation的类型
		// 返回这个annotation代理对象

		// 使用代理对象属性返回值经过的流程
		// 调用invoke方法会使用属性提取器提取该方法的别名对象
		// 然后使用别名和真名的返回值进行对比，如果不相等，并且真名和别名的值都不为空 则抛异常，如果真名的值为空，则把别名的值赋给它。
		// 如果该属性返回的是一个annontation 则重复开始的程序得到属性值

		f(fruit);

		annotationType = Fruit.class;
		annotatedElement = TypeClassImple.class;
		fruit = AnnotationUtils.getAnnotation(annotatedElement, annotationType);
		// 循环所有方法
		// 如果方法返回值为空 判断 取别名的值
		// 如果别名为空 取默认值
		// 如果默认值为空则抛异常
		// 取的方法返回类型与默认值或别名值是同一种类型，则返回
		// 否则 如果即是注解类型又是Map类型则是代理生成的转化后放入map中
		// #####此方法是把取的注解值Map 转化为代理
		AnnotationUtils.synthesizeAnnotation(
				AnnotationUtils.getAnnotationAttributes(fruit), annotationType,
				annotatedElement);

		// ############################得到annotation的属性值#########
		Map<String, Object> map = AnnotationUtils
				.getAnnotationAttributes(fruit);
		f("属性值:" + map);
		map = AnnotationUtils.getAnnotationAttributes(fruit, true);
		f("对象属性值为类字符串 速度快:" + map);
		AnnotationAttributes at = AnnotationUtils.getAnnotationAttributes(
				annotatedElement, fruit);
		f("元素上的注解属性值:" + map);
		at = AnnotationUtils.getAnnotationAttributes(fruit, true, false);
		at = AnnotationUtils.getAnnotationAttributes(annotatedElement, fruit,
				true, false);
		/**
		 * 取的注解属性值流程： 1.循环注解中的所有方法 2.使用方法的调用取的返回值及默认值 3.对返回值进行适配
		 * 4.classValuesAsString=true返回类名 5.返回值为注解 nestedAnnotationsAsMap=true
		 * 则返回AnnotationAttributes类型 否则返回混合类型synthesizeAnnotation()
		 */
		// 取的注解值
		f("取的value注解值：" + AnnotationUtils.getValue(fruit));
		f("取的list注解值：" + AnnotationUtils.getValue(fruit, "list"));
		// annotationType.getDeclaredMethod(attributeName).getDefaultValue();
		// 通过这种方式取的默认值
		// 取的某个属性的默认值
		f("默认值value=" + AnnotationUtils.getDefaultValue(fruit));
		f("默认值value=" + AnnotationUtils.getDefaultValue(fruit.annotationType()));
		f("默认值list=" + AnnotationUtils.getDefaultValue(fruit, "list"));
		f("默认值list="
				+ AnnotationUtils.getDefaultValue(fruit.annotationType(),
						"list"));

		// Java 8版本以上才有
		// AnnotationUtils.getDeclaredRepeatableAnnotations(annotatedElement,
		// annotationType);
		// AnnotationUtils.getDeclaredRepeatableAnnotations(annotatedElement,
		// annotationType, containerAnnotationType);

		// #######################AnnotationAttributes的使用############3
		// 它继承了LinkedHashMap 它用来存储注解的属性值
		f(at.get("desc"));// 直接取值也可以
		f("得到枚举值：" + at.getEnum("policy"));
		f("得到数组：" + StylerUtils.style(at.getStringArray("names")));
		f("得到boolean值：" + at.getBoolean("boo"));
		f("默认值：" + at.getAliasedString("desc", annotationType, fruit));

		// #######################AnnotationAttributes的使用############
		// 1.得到该元素上定义的所有注解
		// 2.调用AnnotationUtils.getAnnotationAttributes方法
		// 使用了访问器模式、策略模式
		AnnotatedElement element = TypeClassImple.class;
		String annotationName = "com.zghw.spring.demo.core.core.annonation.Fruit";
		MultiValueMap<String, Object> multmap = AnnotatedElementUtils
				.getAllAnnotationAttributes(element, annotationName);
		f(multmap);
		multmap = AnnotatedElementUtils.getAllAnnotationAttributes(element,
				annotationName, true, true);
		f(multmap);

		// AnnotatedElementUtils.getAnnotationAttributes(element,
		// annotationName);
		// 如果有别名就使用别名替换 如果没有赋值就用默认值替换
		AnnotationAttributes attmap = AnnotatedElementUtils
				.getAnnotationAttributes(element, annotationName, true, false);
		f(attmap);
		// 合并属性使用动态代理返回注解代理对象 synthesizeAnnotation()组合注解
		Fruit fr = AnnotatedElementUtils.getMergedAnnotation(element,
				annotationType);
		// 使用注解类型 返回注解属性
		attmap = AnnotatedElementUtils.getMergedAnnotationAttributes(element,
				annotationType);
		// 使用注解类名 返回注解属性
		attmap = AnnotatedElementUtils.getMergedAnnotationAttributes(element,
				annotationName);

		// 得到元注解
		Set<String> sets = AnnotatedElementUtils.getMetaAnnotationTypes(
				element, Retention.class);
		f(sets);
		sets = AnnotatedElementUtils.getMetaAnnotationTypes(element,
				"java.lang.annotation.Retention");
		f(sets);

		/**
		 * 查找到属性后封装后就成为了一个注解对象 查找注解对象
		 */
		fr = AnnotatedElementUtils
				.findMergedAnnotation(element, annotationType);
		// AnnotatedElementUtils.findMergedAnnotation(element, annotationName);
		// 增加了访问classValuesAsString=true 代表map中包含的是对象的类名
		// nestedAnnotationsAsMap=true 包装为代理map对象
		attmap = AnnotatedElementUtils.findMergedAnnotationAttributes(element,
				annotationType, true, true);
		attmap = AnnotatedElementUtils.findMergedAnnotationAttributes(element,
				annotationName, true, true);

		// 是否包含元素
		f(AnnotatedElementUtils.hasMetaAnnotationTypes(Fruit.class,
				Retention.class));
		f(AnnotatedElementUtils.hasMetaAnnotationTypes(element, annotationName));

		// 是否被注解了
		f(AnnotatedElementUtils.isAnnotated(element, annotationType));
		f(AnnotatedElementUtils.isAnnotated(element, annotationName));
	}

}
