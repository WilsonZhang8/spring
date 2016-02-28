package com.zghw.spring.demo.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.FieldFilter;
import org.springframework.util.ReflectionUtils.MethodCallback;
import org.springframework.util.ReflectionUtils.MethodFilter;

/**
 * 用处：封装反射功能  简化使用
 * 反射工具类使用示例
 * 
 * @author zghw
 *
 */
public class ReflectionUtilsTest {
	static void f(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) {
		// 根据类型查询字段
		Field f = ReflectionUtils.findField(User.class, "str");
		f(f.getName());
		f = ReflectionUtils.findField(User.class, "str", String.class);
		f(f.getName());
		User user = new User();
		// 设置对象属性值
		// 无法给私有变量赋值
		// ReflectionUtils.setField(f, user, "2222");
		// f(user.getStr());
		f = ReflectionUtils.findField(User.class, "pubstr", String.class);
		ReflectionUtils.setField(f, user, "2222");
		f(user.getStr() +" "+user.pubstr);
		//得到对象字段值
		String s=(String)ReflectionUtils.getField(f, user);
		f(s);
		//根据方法名得到方法对象
		Method method=ReflectionUtils.findMethod(User.class,"getAge");
		//根据方法名得到方法对象重载方法 ，根据参数
		Method method2=ReflectionUtils.findMethod(User.class,"getAge",String.class);
		//调用方法
		int age =(Integer)ReflectionUtils.invokeMethod(method, user);
		int age1 =(Integer)ReflectionUtils.invokeMethod(method2, user,"2");
		f(age +" "+age1);
		//判断一个字段是否是public static final 类型即是否是常量
		f = ReflectionUtils.findField(User.class, "CON");
		Field f2 = ReflectionUtils.findField(User.class, "CON2", String.class);
		f(ReflectionUtils.isPublicStaticFinal(f));
		f(ReflectionUtils.isPublicStaticFinal(f2));
		//判断是否是equals hashCode toString Object里的方法
		Method mm = ReflectionUtils.findMethod(User.class, "equals",User.class);
		Method mm2 = ReflectionUtils.findMethod(User.class, "ee");
		Method mm3 = ReflectionUtils.findMethod(User.class, "hashCode");
		Method mm4 = ReflectionUtils.findMethod(User.class, "toString");
		f(ReflectionUtils.isEqualsMethod(mm));
		f(ReflectionUtils.isEqualsMethod(mm2));
		f(ReflectionUtils.isHashCodeMethod(mm3));
		f(ReflectionUtils.isHashCodeMethod(mm4));
		f(ReflectionUtils.isToStringMethod(mm3));
		f(ReflectionUtils.isToStringMethod(mm4));
		f(ReflectionUtils.isObjectMethod(mm));
		f(ReflectionUtils.isObjectMethod(mm2));
		f(ReflectionUtils.isObjectMethod(mm3));
		f(ReflectionUtils.isObjectMethod(mm4));
		
		//为每个方法执行回调一个方法
		ReflectionUtils.doWithLocalMethods(User.class,new MethodCallback(){
			public void doWith(Method method) throws IllegalArgumentException,
					IllegalAccessException {
				System.out.println("为每一个方法添加一个功能");
			}
		});
		//根据匹配成功的方法作为回调方法
		ReflectionUtils.doWithMethods(User.class,new MethodCallback(){
			public void doWith(Method method) throws IllegalArgumentException,
					IllegalAccessException {
				System.out.println("添加到方法L："+method.getName());
			}
		},new MethodFilter(){
			public boolean matches(Method method) {
				//System.out.println(method.getName());
				//匹配所有get方法
				return PatternMatchUtils.simpleMatch("*get*", method.getName());
			}
		});
		
		//得到所有定义的方法
		Method[] mehods=ReflectionUtils.getAllDeclaredMethods(User.class);
		ReflectionUtils.doWithFields(User.class,new FieldCallback(){
			/**
			 * 处理得到字段后每个字段进行处理
			 */
			public void doWith(Field field) throws IllegalArgumentException,
					IllegalAccessException {
				System.out.println("开始do"+field.getName());
			}
		} );
		ReflectionUtils.doWithFields(User.class,new FieldCallback(){
			/**
			 * 处理得到字段后每个字段进行处理
			 */
			public void doWith(Field field) throws IllegalArgumentException,
					IllegalAccessException {
				System.out.println("只处理过滤后的："+field.getName());
			}
		},new FieldFilter(){

			public boolean matches(Field field) {
				/**
				 * 过滤不处理字段public static final 
				 */
				return !ReflectionUtils.isPublicStaticFinal(field);
			}
			
		} );
		UserChild uc=new ReflectionUtilsTest().new UserChild();
		user.setAge(15);
		user.setStr("56");
		f(uc.getAge());
		f(uc.getStr());
		//把一个父类对象信息复制给子类,需要默认构造函数
		ReflectionUtils.shallowCopyFieldState(user, uc);
		f(uc.getAge());
		f(uc.getStr());
		
	}
	public class UserChild extends User{
		
	}
}
