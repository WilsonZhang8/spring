package com.zghw.spring.demo.core.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;

import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.util.ReflectionUtils;

import com.zghw.spring.demo.core.core.source.TwoTuple;
import com.zghw.spring.demo.core.core.source.TypeClass;
import com.zghw.spring.demo.core.core.source.TypeClassImple;
import com.zghw.spring.demo.core.core.source.User;

/**
 * 封装了反射类型，能够访问超类，接口及泛型解析可以得到Class类型 ResolvableType对象可以从字段、方法参数、方法返回值、Class类型
 * 
 * @author zghw
 *
 */
public class ResolvableTypeTest {
	static void f(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		// TODO Auto-generated method stub
		// 使用工厂方法创建ResolvableType
		//使用对象返回类型
		List<String> instan=new ArrayList<String>();
		ResolvableType instanceObj=ResolvableType.forInstance(instan);
		// 1.通过Class创建ResolvableType 原始类型
		ResolvableType forClass = ResolvableType.forClass(TypeClass.class);
		// 原始类型
		ResolvableType forPrimitiveClass = ResolvableType
				.forRawClass(int.class);
		ResolvableType forRawClass = ResolvableType.forRawClass(Integer.class);
		// 实现类 返回实现类指定的接口类型或超类型 如果不是超类型或接口则返回自身
		ResolvableType forImplClass = ResolvableType.forClass(List.class,
				LinkedList.class);
		// 泛型类 例如TwoTuple<A, B>
		// 把TwoTuple类型及参数A对应Integer.class，参数B对应String.class作为方法参数
		// 实现：
		// 1.多个参数类型转化为ResolableType数组，把Integer.class 和 String.class
		// 转化为ResolableType后放入类型数组中
		// 2.把参数A，B放入数据数组中
		// 把TwoTuple.class和ResolableType参数类型数组封装为一个对象
		// 把参数A，B数据数组和ResolableType数组封装为一个对象
		// 然后把上面两个对象关联到ResolableType上
		// 就形成了一个泛型ResolvableType对象
		ResolvableType forGenericsClass = ResolvableType.forClassWithGenerics(
				TwoTuple.class, Integer.class, String.class);
		ResolvableType A = ResolvableType.forRawClass(Integer.class);
		ResolvableType B = ResolvableType.forClass(String.class);
		ResolvableType forGenericsRTClass = ResolvableType
				.forClassWithGenerics(TwoTuple.class, A, B);
		forGenericsRTClass.isInstance(forGenericsClass);

		Field field = ReflectionUtils.findField(TypeClassImple.class,
				"typeField");
		// 字段类型创建ResolvableType 直接得到字段类型
		ResolvableType forField = ResolvableType.forField(field);
		// 判断这个字段是有父类或接口定义的还是有子类或实现定义的，谁定义的就返回谁对应的ResolvableType
		ResolvableType forImplField = ResolvableType.forField(field,
				TypeClassImple.class);
		ResolvableType rt = ResolvableType.forClass(TypeClassImple.class);
		ResolvableType forImplRTField = ResolvableType.forField(field, rt);
		// List<Set<String>> nestField 取得嵌套泛型的类型
		Field nestField = ReflectionUtils.findField(TypeClassImple.class,
				"nestField");
		// 1代表等到List 2代表了得到Set类型 3代表得到String类型
		ResolvableType forNestField = ResolvableType.forField(field, 2);

		// 比如使用2查找到Set类型 定义了泛型类型的实现类
		ResolvableType forImplNestField = ResolvableType.forField(nestField, 2,
				HashSet.class);

		Method method1 = ReflectionUtils.findMethod(TypeClassImple.class,
				"returnMethod");
		Method method2 = ReflectionUtils.findMethod(TypeClassImple.class,
				"methodTwo", int.class, String.class);
		Method method3 = ReflectionUtils.findMethod(TypeClassImple.class,
				"methodThree", TwoTuple.class, User.class, boolean.class);
		//从0开始
		MethodParameter mp1 = new MethodParameter(method3, 0);
		MethodParameter mp2 = new MethodParameter(method2, 1);
		MethodParameter mp3 = new MethodParameter(method3, 2);
		//接受一个MethodParameter 返回对应的方法参数类型
		ResolvableType rtm1=ResolvableType.forMethodParameter(mp2);
		//接受一个方法及方法参数位置 返回对应的方法参数类型
		ResolvableType rtm2=ResolvableType.forMethodParameter(method3, 2);
		//接受一个MethodParameter及实现类型 返回对应的方法参数类型实现类型
		ResolvableType rtm3=ResolvableType.forMethodParameter(mp2, TypeClassImple.class);
		//接受一个方法及方法参数位置及实现类型 返回对应的方法参数类型实现类型
		ResolvableType rtm5=ResolvableType.forMethodParameter(method3, 2, TypeClassImple.class);
		//接受一个方法 返回对应的方法返回类型
		ResolvableType rtm6=ResolvableType.forMethodReturnType(method1);
		//接受一个方法及方法返回类型实现类型 返回对应的方法返回类型实现类型
		ResolvableType rtm7=ResolvableType.forMethodReturnType(method1, User.class);
		Constructor<?> constructor=TypeClassImple.class.getConstructor(TwoTuple.class, User.class, boolean.class);
		//使用构造函数参数
		ResolvableType rtc=ResolvableType.forConstructorParameter(constructor, 2);
		ResolvableType rtc1=ResolvableType.forConstructorParameter(constructor, 2, TypeClass.class);
		
		//使用ResolvableType
		ResolvableType useAS=ResolvableType.forClass(TypeClassImple.class);
		System.out.println("基类型："+useAS);
		//向上转型为父类型
		ResolvableType superT=useAS.as(TypeClass.class);
		System.out.println("转化为父类型后："+superT);
		//把集合转化为高级接口
		Field hm=ReflectionUtils.findField(TypeClassImple.class, "hm");
		Field ld=ReflectionUtils.findField(TypeClassImple.class, "ld");
		f("hashMap字段使用前："+hm.getType());
		f("LinkedList字段使用前："+ld.getType());
		f("hashMap字段向上转型为Map："+ResolvableType.forField(hm).asMap());
		f("LinkedList字段向上转型为Collection："+ResolvableType.forField(ld).asCollection());
		f("得到TypeClassImple类型"+useAS.getType());
		f("得到TypeClassImple父类型"+useAS.getSuperType());
		for(ResolvableType rrrr:useAS.getInterfaces()){
			f("得到TypeClassImple接口类型不包括父类实现的接口："+rrrr);
		}
		f("是否是泛型："+useAS.hasGenerics());
		Field sss=ReflectionUtils.findField(TypeClassImple.class, "nestField");
		ResolvableType generic=ResolvableType.forField(sss);
		f("是否是泛型："+generic.hasGenerics());
		//
		
		f("List<Set<String>>得到第一层"+generic.getGeneric(0));
		f("List<Set<String>>得到第二层"+generic.getGeneric(0,0));
		f("List<Set<String>>得到第二层"+generic.getGeneric(0).getGeneric(0));
		
		//使用自定义类型不推荐
		ResolvableType tttt=ResolvableType.forType(new ParameterizedType(){
			public Type[] getActualTypeArguments() {
				return null;
			}

			public Type getRawType() {
				return null;
			}

			public Type getOwnerType() {
				return null;
			}
			
		});
		ResolvableType tttt2=ResolvableType.forType(new ParameterizedType(){

			public Type[] getActualTypeArguments() {
				return null;
			}

			public Type getRawType() {
				return null;
			}

			public Type getOwnerType() {
				return null;
			}
			
		}, ResolvableType.forClass(TypeClassImple.class));
				
		
		
		/*
		 * System.out.println(forGenericsRTClass.getType().equals(
		 * forGenericsClass.getType()));
		 */

		TypeVariable<Class<TwoTuple>>[] t = TwoTuple.class.getTypeParameters();
		for (TypeVariable tt : t) {
			//System.out.println(tt);
		}
	}
}
