package com.zghw.spring.demo.core.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ClassUtils;
import org.springframework.util.MethodInvoker;

/**
 * 在运行时调用方法，需要设置目标类或目标对象、目标方法、及参数
 * @author zghw
 *
 */
public class MethodInvokerTest {
	static int count=0;
	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
		MethodInvoker mi= new MethodInvoker();
		List<String> s = new ArrayList<String>();
		mi.setTargetObject(s);
		mi.setTargetMethod("add");
		mi.setArguments(new Object[]{String.valueOf(++count)});
		mi.prepare();//必须在调用invoke方法之前调用一次
		mi.invoke();
		System.out.println(((List)mi.getTargetObject()).get(0));
		mi.invoke();
		System.out.println(((List)mi.getTargetObject()).get(0));
		Boolean issuccess=(Boolean)mi.invoke();
		System.out.println(issuccess);
		//静态方法调用
		mi.setTargetClass(ClassUtils.class);
		mi.setTargetMethod("convertClassNameToResourcePath");
		mi.setArguments(new Object[]{"com.zghw.spring.demo.coreutil.ClassUtilsTest "});
		mi.prepare();//必须在调用invoke方法之前调用一次
		String path=(String)mi.invoke();//有返回值的
		
		System.out.println(path);
	}

}
