package com.zghw.spring.demo.beans.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.springframework.beans.BeanInfoFactory;
import org.springframework.beans.ExtendedBeanInfoFactory;

import com.zghw.spring.demo.beans.test.use.MyBean;

/**
 * BeanInfo在spring中的创建过程和管理
 * @author zghw
 *
 */
public class BeanInfoTest {
	static void f(Object obj){
		System.out.println(obj);
	}
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		//创建BeanInfo使用了简单工厂BeanInfoFactory，
		//由子类ExtendedBeanInfoFactory实现，该类并且继承了排序接口Ordered对BeanInfo设置序号
		//ExtendedBeanInfoFactory内部使用了ExtendedBeanInfo来创建BeanInfo
		//ExtendedBeanInfo使用了装饰器模式对从Introspector#getBeanInfo得到BeanInfo对象进行了装饰。
		//装饰原因：主要对java bean支持写方法即set方法的返回值为void不支持set方法的返回值为非void
		//由于需要创建模式等，会返回当前对象所以需要支持该方式。
		
		//注意ExtendedBeanInfo只支持此方式
		
		/*例如：
		 * public Bean setFoo(Foo foo) {
		 *         this.foo = foo;
		 *         return this;
		 * }
		 *ExtendedBeanInfo继承BeanInfo 通过构造器委托该对象实现大部分方法，并包装了PropertyDescriptor[]返回对象
		 * 经过包装后的PropertyDescriptor[]，很好的支持了每个返回值不为void的set方法取的的PropertyDescriptor对象
		 * 装饰过程：
		 * 1.拷贝BeanInfo对象的PropertyDescriptor到Set集合中，
		 * 2.循环MethodDescriptor[]判断方式是否符合 set(参数1)或，setXx(参数1,参数2)
		 * 1.方法名称字数大于3 
		 * 2.set开头 
		 * 3.方法访问权限为public 
		 * 4.非void或者是静态方法
		 * 5.参数为一个 或者如果是数组属性 可以两个参数，但第一个必须是int类型
		 * 
		 * 如果符合截取方法名setXx为xx和第一个参数类型去匹配Set集合中的PropertyDescriptor
		 * 是否存在 如果存在，就设置PropertyDescriptor的write方法为该方法。
		 * 否则就创建一个PropertyDescriptor放入Set集合中。
		 * 最后在重写public PropertyDescriptor[] getPropertyDescriptors()时，
		 * 把Set集合返回。
		 * 经过包装spring可以只支持set方法就取的PropertyDescriptor
		 * 说了这么多，BeanInfo到底怎么创建的？
		 * 是由Introspector.getBeanInfo(xx.class)方法创建
		 * 由于工厂只创建包含典型的set方法返回的非void类型，
		 * 所以如果是正常的就是用Introspector.getBeanInfo(xx.class)创建Beanfo
		 * 
		 * 在类中，如果是泛型属性会存在一个set类型的桥梁方法
		 * 需要对BeanInfo中PropertyDescriptor[]数组的元素做写方法和读方法做处理
		 * GenericTypeAwarePropertyDescriptor继承了PropertyDescriptor类，
		 * 它对写方法和读方法出现的桥梁方法做了处理，并兼容了set方法比如set方法是private的。
		 * GenericTypeAwarePropertyDescriptor作为一个PropertyDescriptor放入缓存map中。
		 * key是属性名字，value就是这个GenericTypeAwarePropertyDescriptor对象
		 * 可以使用map得到集合 数组或使用属性名查询PropertyDescriptor
		 * 通过Class对象获得查询包装后的适合spring的BeanInfo及PropertyDescriptor[]
		 * 对于这些结果需要用缓存存起来，因为访问底层是有消耗的。
		 * CachedIntrospectionResults就是用来缓存内省结构信息，它接受一个或多个类加载器
		 * 来加载class类型。
		 * 如果给定的Class类型在它接受的类加载器中（或者Class类型类加载器与CachedIntrospectionResults对应的类加载器属于同一类加载器或是同一父类加载器）
		 * ，则把Class对象作为key CachedIntrospectionResults作为值
		 * 缓存在一个线程安全强引用的map中(strongClassCache)。
		 * 否则缓存在另一个线程安全弱引用的map中(softClassCache)。
		 * 通过CachedIntrospectionResults的静态方法 static CachedIntrospectionResults forClass(Class<?> beanClass)
		 * 首先从强引用缓存中取，没有则然后从弱引用中取，如何还没有则使用，则new CachedIntrospectionResults对象创建BeanInfo信息。
		 * 放入缓存，返回CachedIntrospectionResults，就可以得到beanInfo信息 或类加载，或PropertyDescriptor[]等信息。
		 * 大概数据结构：
		 *  * -Class<?>{
 * 						CachedIntrospectionResults{
 * 							ClassLoader{
 * 								ClassLoader1,
 * 								ClassLoader2,
 * 							}
 * 							BeanInfo,
 * 							PropertyDescriptors[
 *								PropertyDescriptor,
 *								PropertyDescriptor
 *							],
 *							TypeDescriptor{
 *								PropertyDescriptor:TypeDescriptor,
 *								PropertyDescriptor:TypeDescriptor,
 *							}
 * 						}，
 * 					  }
		 */
		try {
			BeanInfoFactory bif =new ExtendedBeanInfoFactory();
			//如果该类型中的set方法不是静态的或者返回类型为void返回null
			BeanInfo bi=bif.getBeanInfo(MyBean.class);
			for (Method method : MyBean.class.getMethods()) {
				if (isCandidateWriteMethod(method)) {
					f("该工厂方法只支持set方法不返回void不等于 或者静态的set方法");
				}
			}
			MethodDescriptor[] mds =bi.getMethodDescriptors();
			PropertyDescriptor[] pds=bi.getPropertyDescriptors();
			for(PropertyDescriptor pd :pds){
				f("显示的属性名："+pd.getDisplayName());
				f("处理后的属性名：:"+pd.getName());
				f("描述："+pd.getShortDescription());
				f("属性类型："+pd.getPropertyType().getName());
				f("读取方法即get方法："+pd.getReadMethod().getName());
				f("写方法即set方法"+pd.getWriteMethod());
			}
			for(MethodDescriptor md :mds){
				f("得到定义的名字："+md.getDisplayName());
				f("方法名："+md.getName());
				f("方法："+md.getMethod());
			}
			
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name",MyBean.class);
			f("显示的属性名："+propertyDescriptor.getDisplayName());
			f("处理后的属性名：:"+propertyDescriptor.getName());
			f("描述："+propertyDescriptor.getShortDescription());
			f("属性类型："+propertyDescriptor.getPropertyType().getName());
			f("读取方法即get方法："+propertyDescriptor.getReadMethod().getName());
			f("写方法即set方法"+propertyDescriptor.getWriteMethod());
			propertyDescriptor.setValue("name", "123");
			f("取的属性值："+propertyDescriptor.getValue("name"));
			//设置读写方法为其他
			propertyDescriptor.setWriteMethod(MyBean.class.getMethod("setName1", String.class));
			propertyDescriptor.setReadMethod(MyBean.class.getMethod("getName1"));
			f("读取方法即get方法："+propertyDescriptor.getReadMethod().getName());
			f("写方法即set方法"+propertyDescriptor.getWriteMethod());
			
			MethodDescriptor methodDescriptor=new MethodDescriptor(MyBean.class.getMethod("setAge", int.class));
			f("得到定义的名字："+methodDescriptor.getDisplayName());
			f("方法名："+methodDescriptor.getName());
			f("方法："+methodDescriptor.getMethod());
			f(methodDescriptor.getParameterDescriptors());
			
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static boolean isCandidateWriteMethod(Method method) {
		String methodName = method.getName();
		Class<?>[] parameterTypes = method.getParameterTypes();
		int nParams = parameterTypes.length;
		return (methodName.length() > 3 && methodName.startsWith("set") && Modifier.isPublic(method.getModifiers()) &&
				(!void.class.isAssignableFrom(method.getReturnType()) || Modifier.isStatic(method.getModifiers())) &&
				(nParams == 1 || (nParams == 2 && int.class == parameterTypes[0])));
	}
}
