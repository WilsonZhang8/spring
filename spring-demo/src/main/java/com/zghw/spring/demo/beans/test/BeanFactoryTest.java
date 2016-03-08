package com.zghw.spring.demo.beans.test;

public class BeanFactoryTest {

	public static void main(String[] args) {
		/**
		 * BeanFactory 访问spring bean的容器的根接口
		 * 这个接口的实现是通过一个数字代表一个bean对象，唯一一个String名称
		 * 根据bean上的定义，工厂将返回一个包含对象的实例（原型设计模式）
		 * 或一个共享实例（在工厂允许实例是一个单例时，更好的选择时单例模式）
		 * 返回那个实例类型依赖于bean工厂的配置 API也一样，
		 * Spring 2.0以来,更多的范围可以根据具体的应用程序上下文(例如“请求”和“会话”范围在web环境中)。
		 * 关键在于BeanFactory是应用程序组件注册中心和应用程序组件配置中心(单个对象不再需要读取属性文件
		 * 注意,最好还是依靠依赖注入(“推”配置)来配置应用程序对象通过setter方法或构造函数,而不是使用任何形式的“拉动”配置像BeanFactory查找。)
		 * Spring的依赖注入的功能是使用这个BeanFactory接口及其实现的子接口
		 * 
		 * 
		 * #####################   AutowireCapableBeanFactory 接口 ############
		 * AutowireCapableBeanFactory接口继承了BeanFactory接口
		 * 实现了bean工厂自动装配,为这个存在的bean实例提供它们想要的功能。
		 * 其他框架集成代码可以利用这个接口连接和注入现有的bean实例,spring不控制器生命周期。读于外部的Webwork非常有用。
		 * ApplicationContext不会使用这个接口，ApplicationContext很少使用外围代码
		 * 
		 * 设置注入方式 
		 * 		1.AUTOWIRE_NO=0 不注入
		 * 		2.AUTOWIRE_BY_NAME=1 通过bean的属性名称注入
		 * 		3.AUTOWIRE_BY_TYPE=2  通过bean的属性类型注入
		 * 		4.AUTOWIRE_CONSTRUCTOR=3 通过构造器注入
		 * 
		 * 接口方法：
		 * 典型的方法来创建和填充外部bean实例
		 * 1.给定一个Class类型完全创建一个新的bean实例
		 * 执行完整的bean的initialization,包括所有适用的BeanPostProcessors。
		 * 注意:这是用于创建一个新的实例,注入带注解的字段和方法以及应用所有标准bean 
		 * initialiation回调方法。这并不意味着传统的名字或按类型属性的自动装配;使用createBean(类、整型、布尔型)的目的。
		 * 2.填充给定的bean实例通过应用after-instantiation回调函数和bean属性后处理(例如注解驱动的注入)
		 * 注:这是用于(重新)填充带注释的字段和方法,对新实例或反序列化实例。这并不意味着传统的名字或按类型属性的自动装配;
		 * 使用autowireBeanProperties目的。
		 * 3.给定原始配置bean:自动装配bean属性,应用bean属性值,应用工厂回调setBeanName和setBeanFactory等,
		 * 并应用所有bean后处理器(包括那些可能将给定的原始bean)。这是有效的超集initializeBean提供什么,充分应用指定的配置相应的bean定义。
		 * 注意:这个方法需要一个bean定义为给定的名字!
		 * 4.解析工厂中bean定义的执行依赖项，返回一个对象
		 * 专业细粒度的控制bean生命周期的方法
		 * 5.根据类型及注入方式创建一个新的bean实例，可以设置是否依赖检查
		 * 6.根据类型及注入方式注入一个新的bean实例
		 * 7.通过类型或属性名称注入一个存在的bean实例
		 * 8.将bean定义的属性值与给定的bean实例的名字。bean定义可以定义一个完全独立的bean,重用其属性值,或只是属性值应该用于现有的bean实例。
		 * 9.初始化一个基本类型bean实例
		 * 10.应用BeanPostProcessors给定的现有的bean实例,调用postProcessBeforeInitialization方法。返回的bean实例可能是原始的包装器。
		 * 11.摧毁给定的bean实例(通常来自createBean),应用org.springframework.beans.factory。以及注册DestructionAwareBeanPostProcessors DisposableBean合同。
		 * 12.解析工厂中bean定义的执行依赖项，返回一个对象
		 */
	}

}
