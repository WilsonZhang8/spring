package com.zghw.spring.demo.demo.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.style.StylerUtils;
/**
 * 属性访问器
 * @author zghw
 *
 */
public class BeanWapperTest {
	static void f(Object obj){
		System.out.println(obj);
	}
	public static void main(String[] args) {
		BeanNest bn =new BeanNest();
		List<MyBean> list =new ArrayList<MyBean>();
		MyBean mb =new MyBean();
		Bean1 bean1 =new Bean1();
		Bean2 bean2 =new Bean2();
		bean2.setOrder(new Integer[]{1,2,3,4,5,6,7,8});
		bean1.setBean2(bean2);
		mb.setBean1(bean1);
		
		list.add(mb);
		bn.setMb(list);
		DirectFieldAccessor dfa =new DirectFieldAccessor(bn);
		dfa.setPropertyValue("mb[0].bean1.bean2.order[2]", 100);
		//首先根据 mb[0].bean1.bean2.order[2] 属性名，取mb字段通过反射取的值，
		//然后取的集合中的第0个对象这里就叫root对象，
		//使用参数root对象、下一个属性路径bean1.bean2.order[2]和父访问器dfa 来构造一个新的访问器
		//递归以上方式
		Integer[] order = (Integer[])dfa.getPropertyValue("mb[0].bean1.bean2.order");
		f(StylerUtils.style(order));
		//使用PropertyAccessorFactory的工厂方法访问
		ConfigurablePropertyAccessor dfa2=PropertyAccessorFactory.forDirectFieldAccess(bean1);
		Integer[] order2 = (Integer[])dfa2.getPropertyValue("bean2.order");
		f(StylerUtils.style(order2));
		BeanWrapperImpl bwi =new BeanWrapperImpl(bn);
		bwi.setPropertyValue("mb[0].bean1.bean2.order[3]", 300);
		Integer[] order4 = (Integer[])bwi.getPropertyValue("mb[0].bean1.bean2.order");
		f(StylerUtils.style(order4));
		//使用
		ConfigurablePropertyAccessor dfa3=PropertyAccessorFactory.forBeanPropertyAccess(bean1);
		Integer[] order3 = (Integer[])dfa3.getPropertyValue("bean2.order");
		f(StylerUtils.style(order3));
		
		/**
		 * PropertyAccessor接口定义了class类中字段的访问和设置功能
		 * 判断该字段是否支持读 写
		 * 通过字段名取得对应的类型
		 * 通过字段名得到对应的对象
		 * 通过字段名得到类型描述TypeDescipter
		 * 通过name，vaule或Map或PropertyValue或PropertyValues来设置字段值
		 * 在设置PropertyValues集合属性时，可以设置是否忽略不需要的元素或无法验证的元素
		 * 
		 * ConfigurablePropertyAccessor接口继承了PropertyAccessor接口 PropertyEditorRegistry接口 和TypeConverter接口
		 * 它就包含属性访问器、编辑器管理、及类型转换功能
		 * 它自己设置配置转换器服务ConversionService
		 * 设置了是否需要提取编辑器设置的旧值
		 * AbstractPropertyAccessor抽象类实现了TypeConverterSupport和ConfigurablePropertyAccessor
		 * 定义了访问器的set get 主要结构，主要的实现有子类完成，这是典型的模板方法。
		 * 
		 * 定义bean对象，
		 * 定义bean对象的.之前的根路径对象，如果对象是集合，定义每个元素在集合中的路径
		 * 定义了缓存当前访问器的map key nestedPath value是该类下的下一个访问器
		 * 构造器 
		 * 1.是否使用默认的编辑器
		 * 2.默认构造函数为使用默认的编辑器
		 * 3.给定对象或类型
		 * 构造器根据设定的值构造该对象
		 * 可以访问该对象实例
		 * 可以访问该对象类型
		 * 可以访问该对象在元素集合中的位置属性
		 * 可以访问该对象访问器的根对象 .最前面的对像及根对象类型
		 * 
		 * 
		 * 设置值 String propertyName, Object value
		 * 1：通过属性名转换为PropertyTokenHolder对象，这个对象包含了 
		 * actualName真实的对象名如果是集合就是[]前面的对象名，否则是propertyName
		 * 如果是集合canonicalName=actualName[key1][key2]否则是propertyName
		 * 包含了keys的集合 key1 key2
		 * 然后使用这actualName得到PropertyHandler 这个功能有子类实现
		 * PropertyHandler是什么呢？
		 * PropertyHandler是定义在AbstractNestablePropertyAccessor内部的一个抽象类
		 * 它用于持有一个对象属性的各种资源，它的大部分实现是有子类完成的，有见模板方法
		 * 属性资源是通过反射还是Javabean方式获取有子类来实现。
		 * 有了对象实例和类型就可以通过反射的取的属性相关的信息 子类来实现 填充对象
		 * 继续回到上面得到PropertyHandler以后就可以取的该属性的值，
		 * 如果子类没有实现 则创建一个新的对象返回
		 * 然后创建一个AbstractNestablePropertyAccessor或则从缓存中取的
		 * 递归查找每个访问器。
		 * 查找到放入PropertyValue，包含了PropertyTokenHolder，如果是数组就可以定位该地方设置值了
		 * 
		 * 查找
		 * mb[0].bean1.bean2.order[2]
		 * //首先根据 mb[0].bean1.bean2.order[2] 属性名，取mb字段通过反射取的值，
		//然后取的集合中的第0个对象这里就叫root对象，
		//使用参数root对象、下一个属性路径bean1.bean2.order[2]和父访问器dfa 来构造一个新的访问器
		//递归以上方式，最后取的值。
		 * 
		 * BeanWrapperImpl和DirectFieldAccessor都实现了AbstractNestablePropertyAccessor
		 * DirectFieldAccessor使用了反射的方式设置和取值的
		 * BeanWrapperImpl使用的时Javabean技术访问BeanInfo的PropertyDescripter
		 * 
		 * 
		 */
		
		
	}

}
