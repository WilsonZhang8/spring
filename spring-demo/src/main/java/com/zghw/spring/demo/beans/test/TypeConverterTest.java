package com.zghw.spring.demo.beans.test;

import java.util.*;

import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.TypeConverter;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import com.zghw.spring.demo.beans.test.use.MyBean;

/**
 * 类型转换
 * @author zghw
 *
 */
public class TypeConverterTest {
	static void f(Object obj){
		System.out.println(obj);
	}
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, NoSuchMethodException {
		/**
		 * spring封装了自动的转换的功能，结合了TypeCoverter
		 * TypeConverter接口定义了类型转换的方法，通常的（但不需要的）子类也要实现PropertyEditorRegistry
		 * 转换通常使用PropertyEditor的setAsText或者使用spring的ConversionService转换服务
		 * 通常把一个对象转化为给定的类型
		 * TypeConverterSupport实现了TypeConverter和PropertyEditorRegistry它没有做实质性的实现，
		 * 直接把实现委托给TypeConverterDelegate类来处理
		 * TypeConverterDelegate它认认真真的完成委托人的转换类型任务。它需要有属性注册服务PropertyEditorRegistrySupport的支持
		 * BeanWrapperImpl和SimpleTypeConverter子类把自己作为PropertyEditorRegistrySupport委托给TypeConverterDelegate处理
		 * 因为不同的实现有不同编辑属性注册表
		 * 
		 * convertIfNecessary(String propertyName, Object oldValue, Object newValue, Class<T> requiredType, 
		 * TypeDescriptor typeDescriptor) 
		 * propertyName 注册表中的集合属性地址
		 * oldValue
		 * newValue    要转换的对象
		 * requiredType  转换后需要的类型
		 * typeDescriptor 转换后的类型描述，比如有annotation的方法参数或字段包含的信息
		 *===转换器转换
		 * 通过propertyName requiredType来查找一个属性编辑器
		 * 取的注册编辑器服务设置的ConversionService转换对象
		 * 如果属性编辑器为空并且ConversionService不为空并且newValue不为空并且typeDescriptor不为空
		 * 则使用typeDescriptor取的newValue的TypeDescriptor，
		 * 使用ConversionService.convert判断要转换对象的TypeDescriptor和目标typeDescriptor能否转换
		 * 如果能转换直接使用ConversionService转换返回转换类型
		 * 
		 * ==编辑器转换及集合转换
		 * 否则使用编辑器转换
		 *    newValue requiredType typeDescriptor         
		 * 1.String --    集合    - > 枚举             String[]  
		 * 如果编辑器不为空或者newValue不是来自于requiredType
		 * 		如果requiredType是一个集合类型，newValue为一个String类型，并且typeDescriptor是枚举类型
		 * 		则使用，分割String类型后作为String数组设置newValue为String数组,使用编辑器进行编辑
		 *      把newValue进行编辑器先转换一次
		 * 
		 * 如果requiredType不为空，并且编辑后convertedValue不为空，
		 * 1.convertedValue是Object类型直接返回
		 * 2.requiredType是数组
		 * 3.convertedValue是集合
		 * 4.convertedValue是Map
		 * 5.convertedValue是数组类型，就包含一个元素，就返回该元素
		 * 6.requiredType如果是String类型，包装convertedValue作为对象返回
		 * 7.如果convertedValue是String 但requiredType不是String的实例
		 * 试着使用requiredType的构造函数(String convertedValue)创建对象实例
		 * 或者requiredType是Number类型的就转换
		 * 或者转换为枚举
		 * 
		 *数组的转换:
		 *	如果转换对象类型为Collection
		 *	根据转换对象集合,创建一个需要类型数组
		 *	循环转换对象集合,使用上面的convertIfNecessary方法来转换,其中propertyName=propertyName[i]
		 *	这个可以用来查询编辑器注册表,需要类型数组添加每一个转换后的返回元素
		 * 	返回这个类型数组
		 *  如果是转换类型是数组, 并且需要类型也是该数组,并且编辑器中不包含数组中元数的转换器,则直接返回该转换数组
		 *  否则循环转换
		 *  
		 *  如果 转换对象就一个对象 直接创建一个需要类型的数组把这个转换对象添加进入返回
		 *  
		 *集合和数组相似
		 *
		 * SimpleTypeConverter设置了默认编辑器注册表，并且把转换类型任务委托给TypeConverterDelegate处理
		 * 
		 */
		TypeConverter tc =new SimpleTypeConverter();
		List list =new ArrayList();
		list.add("11111");
		list.add("2222");
		list.add("3333");
		list.add("4444");
		Integer[] listArray=tc.convertIfNecessary(list, Integer[].class);
		for(Integer inte :listArray){
			f(inte);
		}
		listArray=tc.convertIfNecessary(list, Integer[].class,MyBean.class.getField("integerArray"));
		for(Integer inte :listArray){
			f(inte);
		}
		
		ResolvableType rt=ResolvableType.forClass(MyBean.class);
		MethodParameter mp =new MethodParameter(MyBean.class.getMethod("setList", List.class),0);
		
		List<MyBean> listBean = new ArrayList<MyBean>();
		listBean.add(new MyBean());
		listBean.add(new MyBean());
		listBean.add(new MyBean());
		ConversionService cs =new DefaultConversionService();
		//没有注册属性编辑器和转换服务是无法对复杂类型转换
		/*MyBean[] myBeanArray=cs.convert(list,  MyBean[].class);
		//MyBean[] myBeanArray=tc.convertIfNecessary(list, MyBean[].class,mp);
		for(MyBean mmb :myBeanArray){
			f(mmb.getAge());
		}*/
	}
	
}
