package com.zghw.spring.demo.core.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
/**
 * 用处：Class 的 类加载 、查找类信息、类信息的处理等
 * Class类工具
 * @author zghw
 *
 */
public class ClassUtilsTest {

	public static void main(String[] args) throws ClassNotFoundException, LinkageError {
		//取得类加载器
		ClassLoader cl =ClassUtils.getDefaultClassLoader();
		System.out.println(cl);
		PathClassLoader pcl = new PathClassLoader("/tmp");
		//替换线程加载
		//ClassLoader cl2 =ClassUtils.overrideThreadContextClassLoader(pcl);
		System.out.println(cl);
		//由于Class<?>不支持基本类型要通过名称转化为引用类型
		Class<?> c=ClassUtils.resolvePrimitiveClassName("int");
		c =ClassUtils.forName("java.lang.String[]",cl );
		ClassUtils.isPresent("java.lang.String[]",cl );
		System.out.println(c);
		//定义的类是否可以转换为Class<?>对象
		System.out.println(ClassUtils.isPresent("java.lang.String[]",cl ));
		//通过对象取得Class<?>
		Class<?> ccc=ClassUtils.getUserClass(new ClassUtilsTest());
		System.out.println(ccc.getName());
		//得到简短名字，去掉包名 ClassUtilsTest
		System.out.println(ClassUtils.getShortName(ccc.getName()));
		System.out.println(ClassUtils.getShortName(ccc));
		//得到名字+.class ClassUtilsTest.class
		System.out.println(ClassUtils.getClassFileName(ccc));
		//得到包名 com.zghw.spring.demo.core.util
		System.out.println(ClassUtils.getPackageName(ccc));
		//根据全限定类名查找到包名
		System.out.println(ClassUtils.getPackageName("com.zghw.spring.demo.core.util.ClassUtilsTest"));
		Class<?> c2=ClassUtils.resolvePrimitiveClassName("int");
		//得到全限定名
		System.out.println(ClassUtils.getQualifiedName(c));
		System.out.println(ClassUtils.getQualifiedName(ccc));
		//得到方法的全限定名称
		System.out.println(ClassUtils.getQualifiedMethodName(ccc.getMethods()[0]));
		//判断给定的类名和类对应的名称是否相等
		System.out.println(ClassUtils.matchesTypeName(ccc,"ClassUtilsTest"));
		//判断类是否有对应参数构造器
		System.out.println(ClassUtils.hasConstructor(ccc, null));
		//根据类及构造参数得到构造对象
		Constructor con=ClassUtils.getConstructorIfAvailable(ccc, null);
		System.out.println(con.toString());
		//判断某个类中的方法及参数是否正确
		System.out.println(ClassUtils.hasMethod(ccc, "main",c));
		//得到方法
		Method m=ClassUtils.getMethodIfAvailable(ccc, "main",c);
		System.out.println(m.getName());
		//统计类中有多少个"main"方法 包括父类 接口
		System.out.println(ClassUtils.getMethodCountForName(ccc, "main"));
		//至少含有一个方法？
		System.out.println(ClassUtils.hasAtLeastOneMethodWithName(ccc, "main"));
		//是否是用户级别的
		System.out.println(ClassUtils.isUserLevelMethod(ccc.getMethods()[0]));
		//得到静态方法
		System.out.println(ClassUtils.getStaticMethod(ccc, "main",c).getName());
		//判断是否是基本引用类型
		System.out.println(ClassUtils.isPrimitiveWrapper(c2));
		System.out.println(ClassUtils.isPrimitiveOrWrapper(c2));
		//把/转换为.
		System.out.println(ClassUtils.convertResourcePathToClassName("com/zghw/spring/demo/core/util/ClassUtilsTest"));
		//把.转换为/
		System.out.println(ClassUtils.convertClassNameToResourcePath("com.zghw.spring.demo.coreutil.ClassUtilsTest"));
		//在类对应的包后面追加一个路径成为新的包
		System.out.println(ClassUtils.addResourcePathToPackagePath(ccc,"/ClassUtilsTest"));
		//把包路径转换为资源路径
		System.out.println(ClassUtils.classPackageAsResourcePath(ccc));
		//一组类对应的类名集合
		System.out.println(ClassUtils.classNamesToString(ccc,c2,c));
		//得到对应的所有接口
		Class[] css=ClassUtils.getAllInterfaces(new ArrayList());
		css=ClassUtils.getAllInterfacesForClass(ArrayList.class);
		 Set<Class<?>> csr=ClassUtils.getAllInterfacesForClassAsSet(ArrayList.class);
		for(Class s: css){
			System.out.println(s.getName());
		}
		for(Class s:csr){
			System.out.println(s.getName());
		}
		
		//使用java代理类合并多个接口到一个类中
		Class<?> c4=ClassUtils.createCompositeInterface(new Class<?>[]{Collection.class,Runnable.class},ClassUtilsTest.class.getClassLoader());
		for(Method kkk :c4.getMethods()){
			System.out.println(kkk.getName());
		}
		//取得两个类的共同祖先类
		Class<?> c5=ClassUtils.determineCommonAncestor(ArrayList.class,Set.class);
		Class<?> c6=ClassUtils.determineCommonAncestor(ArrayList.class,List.class);
		System.out.println(c5 +"  "+c6);
		
		//判断某个类在类加载器中是否可见的
		System.out.println(ClassUtils.isVisible(ArrayList.class,cl));
		System.out.println(ClassUtils.isVisible(ArrayList.class,pcl));
		Class<List> A = List.class;
		Class<LinkedList> B = LinkedList.class;
		Class<LinkedList> C = LinkedList.class;
		
		//判断A是否来自于比，包含了等于，超类 接口中
		System.out.println(A.isAssignableFrom(B));
		
		System.out.println(ClassUtils.isAssignable(A, B));
		Class<?> D=int.class;
		Class<?> E=Integer.class;
		//原生类型Java判断 我门要任务Integer.class 和int.class相同
		System.out.println(D.isAssignableFrom(E));
		System.out.println(E.isAssignableFrom(D));
		//为原生类型做判断
		System.out.println(ClassUtils.isAssignable(D, E));
		System.out.println(ClassUtils.isAssignable(E, D));
		System.out.println(ObjectUtils.nullSafeEquals(List.class,LinkedList.class));
		
	}

}
