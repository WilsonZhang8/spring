package com.zghw.spring.demo.beans.test;

public class BeanDefinitionReaderTest {

	public static void main(String[] args) {
		/**
		 * BeanDefinitionReader
		 * 把一个Resource资源或者一个String定位符加载成一个BeanDefinition对象
		 * 实现该接口可以设置不同的加载实现，及对BeanDefinition的format
		 * 当然你也不一定非要实现这个接口，可以通过其他方式加载bean
		 * 
		 * 接口方法：
		 * 1.加在一个资源Resource
		 * 2.加载多个资源Resource
		 * 3.加载一个String location定位符
		 * 4.加载多个String location定位符
		 * 5.返回BeanDefinition注册表
		 * 6.返回资源加载器
		 * 7.返回类加载器
		 * 8.返回beanname生成器
		 * 
		 * BeanNameGenerator 接口
		 * BeanDefinition的name生成器
		 * 接口方法：
		 * 1.放入BeanDefinition 及BeanDefinitionResitory注册表
		 * 生成一个name
		 * DefaultBeanNameGenerator实现了BeanNameGenerator接口
		 * 1.得到beanclassname 然后 如果为空使用父类的classname+$child 如果父类classname为空就用工厂中bean的名称+$created
		 * 判断得到的最后name在注册表中的数量
		 * 最后得到BeanName#counter
		 * 
		 * AbstractBeanDefinitionReader抽象类实现BeanDefinitionReader大部分工作，并且实现了EnvironmentCapable运行环境属性
		 * 提供了共同的属性,比如bean工厂工作和使用的类加载器加载bean类。
		 * 他通过构造方法参数BeanDefinitionRegistry来创建该对象，使读到的BeanDefinition注入到注册器中
		 * 
		 * PropertiesBeanDefinitionReader继承了AbstractBeanDefinitionReader
		 * 这个读取器可以读取属性格式的BeanDefinition
		 * 也可以读取map 然后把属性值或map循环取值匹配到BeanDefinition中，然后放入注册表中。
		 * 在spring中有很多final map 它们在spring容器中就像一个个数据结构一样或者说是数据库把
		 * 
		 * 	
		 */
	}

}
