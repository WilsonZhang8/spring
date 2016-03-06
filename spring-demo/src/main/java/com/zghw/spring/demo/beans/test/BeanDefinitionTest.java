package com.zghw.spring.demo.beans.test;
/**
 * 
 * BeanDefinition相关接口定义分析，主要是顶bean的数据结构
 * 
 * @author zghw
 *
 */
public class BeanDefinitionTest {

	public static void main(String[] args) {
		/**############################## BeanDefinition 接口##########################
		 * BeanDefinition接口继承了AttributeAccessor接口和BeanMetadataElement接口
		 * 这就代表了则为这个元数据元素BeanDefinition配置一个源对象，从BeanDefinition对象
		 * 中增加或访问元数据。
		 * BeanDefinition 接口描述了一个bean的实例，有属性值、构造参数值和通过接口实现提供近一步的信息。
		 * 这只是一个最小的接口：它的主要目的是允许BeanFactoryPostProcessor和ProperPlaceholderConfigurer
		 * 注入和修改属性值及其他bean元数据。
		 * 
		 * 定义的一些常量：BeanDefinition的范围及角色扮演
		 * 1.SCOPE_SINGLETON="singleton" Bean的作用范围为单例 全局单个实例 一般作用在没有状态的beansingleton
		 * 2.SCOPE_PROTOTYPE="prototype" Bean的作用范围为原型 可以有多个Bean实例 一般作用在有状态的匾bean，比如seesion request
		 * 3.ROLE_APPLICATION=0 作用提示说明这个BeanDefinition代表了它是程序中重要的一部分。通常是用户定义的bean
		 * 4.ROLE_SUPPORT=1 作用提示说明这个BeanDefinition支持一些大型配置的一部分
		 * 5.ROLE_INFRASTRUCTURE = 2 作用提示说明这个BeanDefinition提供了对客户端使用隐藏功能，只在内部中使用。
		 * 
		 * 接口方法：
		 * 1.如果存在，返回父BeanDefinition的名称
		 * 2.如果存在，设置父BeanDefinition的名称
		 * 3.得到BeanDefinition的类名
		 * 4.重设置BeanDefinition的bean类名 
		 * 5.得到工厂bean的名称
		 * 6.设置工厂bean的名称
		 * 7.返回这个bean的当前范围scope的值
		 * 8.重设置这个bean的当前范围scope的值，可以使用上面的常量SCOPE_SINGLETON或SCOPE_PROTOTYPE
		 * 9.是否是懒加载，比如在启动是不初始化对象，在使用时才加载。这个只适合单例bean
		 * 10.设置懒加载
		 * 11.返回这个bean依赖的所有bean名称数组
		 * 12.在将要初始化时，设置依赖bean的名称，bean factory将保证这些bean最先初始化
		 * 13.返回这个bean对象是否是自动装配到其他bean对象的候选bean
		 * 14.设置这个bean对象是否是自动装配到其他bean对象的候选bean
		 * 15.返回这个bean对象是否是自动装配到其他bean对象的主要候选bean，如果为true，代表这个是其他候选bean中的决胜者，优先使用。
		 * 16.设置这个bean对象是否是自动装配到其他bean对象的主要候选bean
		 * 17.返回这个bean对象的构造参数对象ConstructorArgumentValues，这个返回的实例可以在bean工厂的 post-processing期间进行修改。
		 * 18.返回新实例bean对应的属性值集合对象MutablePropertyValues，这个返回的实例可以在bean工厂的 post-processing期间进行修改。
		 * 19.返回是否是一个单例，所有的调用者返回单一的共享实例。
		 * 20.返回是否是一个原型，所有的调用者返回独立的实例
		 * 21.返回是否是一个abstract bean, 意味着这个bean不能被实例化
		 * 22.返回该BeanDefinition的作用提示，提供了框架和工具在使用BeanDefinition时的作用。
		 * 23.返回可读该bean定义的描述
		 * 24.返回该BeanDefinition资源来源信息描述，为了显示上下文的错误
		 * 25.如果没有返回原始BeanDefinition,或null。允许检索bean定义,装饰。注意,这个方法返回的直接发起者。
		 * 	遍历发起人链找到原始BeanDefinition由用户定义。
		 * ############################## AnnotatedBeanDefinition 接口##########################
		 * AnnotatedBeanDefinition接口继承了BeanDefinition接口
		 * 它不需要加载类，使用了ASM或反射等方式加载类然后包装到AnnotationMetadata对象中来表示。
		 * 接口方法：
		 * 1.得到AnnotationMetadata对象，使用这个bean定义的class
		 * 2.从BeanDefinition的工厂方法中获得MethodMetadata对象
		 * ############################### AbstractBeanDefinition抽象类 ####################
		 * AbstractBeanDefinition抽象类实现BeanDefinition接口和Cloneable接口，并且继承了BeanMetadataAttributeAccessor
		 * BeanMetadataAttributeAccessor类实现了BeanDefinition接口继承的AttributeAccessor接口和BeanMetadataElement接口
		 * AbstractBeanDefinition抽象类及其子类就对AttributeAccessor接口和BeanMetadataElement接口做太多的实现，
		 * 这是spring经常做的事情，接口A继承另一个接口B,实现接口A的子类只要继承B接口的实现类就拥有了B的功能。分工明确，就像一个部门只做该
		 * 部门要做的事情。
		 * AbstractBeanDefinition抽象类，是一个成熟的BeanDefinition实现，它为子类GenericBeanDefinition、RootBeanDefinition、
		 * 和ChildBeanDefinition抽取了相同的属性。
		 * 实现的新方法：
		 * 1.给定一个子类BeanDefinition覆盖父类BeanDefinition copy字段属性
		 * 2.使用BeanDefinitionDefaults对象创建一个简单的默认BeanDefinition
		 * 3.是否存在beanclass
		 * 4.设置beanclass
		 * 5.得到beanclass
		 * 6.使用类加载器重新加载类
		 * 7.设置注入方式 
		 * 		1.AUTOWIRE_NO=0 不注入
		 * 		2.AUTOWIRE_BY_NAME=1 通过bean的属性名称注入
		 * 		3.AUTOWIRE_BY_TYPE=2  通过bean的属性类型注入
		 * 		4.AUTOWIRE_CONSTRUCTOR=3 通过构造器注入
		 * 8.得到注入的方式
		 * 9.设置依赖检查模式
		 * 		1.DEPENDENCY_CHECK_NONE = 0 不需要依赖检查
		 * 		2.DEPENDENCY_CHECK_OBJECTS = 1 依赖检查对象的引用
		 * 		3.DEPENDENCY_CHECK_SIMPLE = 2  依赖检查简单的属性
		 * 		4.DEPENDENCY_CHECK_ALL = 3	依赖检查所有属性(对象的引用以及简单的属性)
		 * 10.得到依赖检查模式
		 * 11.注册一个限定符用于自动装配候选人
		 * 12.给定限定名返回是否存在包含的限定器
		 * 13.返回限定符映射到所提供的类型名称。
		 * 14.得到全限定符集合
		 * 15.从其他AbstractBeanDefinition中拷贝全限定符
		 * 16.指定是否允许访问非公有的构造函数和方法,指向外部的元数据。默认是true的;设置为false只能访问public方法。
				这只适用于构造函数,工厂方法,init /destroy方法。Bean属性访问器必须在任何情况下,不受该设置的影响。
				注意,注解驱动的配置仍然访问非公有制成员被注释。这个设置只适用于这个bean定义中外部的元数据。
		 * 17.返回是否允许非共有访问
		 * 18.设置使用构造函数在宽松的条件下还是严格的默认时宽松的，它使用了默认构造器 false为严格的需要指定构造器参数
		 * 19.返回宽松的构造器还是严格的构造器
		 * 20.设置构造器参数对象
		 * 21.返回是否包含构造器值
		 * 22.设置属性值集合
		 * 23.设置重写的方法对象
		 * 24.返回重写方法对象
		 * 25.设置初始化方法名称
		 * 26.返回初始化方法名称
		 * 27.是否设置执行初始化方法
		 * 28.返回是否执行初始化方法
		 * 29.设置销毁方法名称
		 * 30.返回销毁方法名称
		 * 31.是否设置执行销毁方法
		 * 32.返回是否设置执行销毁方法
		 * 33.设置是否是动态很合成的，比如代理创建的
		 * 34.返回是否是合成的
		 * 35.设置bean定义的来源resource
		 * 36.返回bean定义的来源resource
		 * 37.设置一个包装过的beanDefinition来代替
		 * 38.验证bean
		 * 39.对象clone 有子类实现抽象方法cloneBeanDefinition
		 * 40.对equals、hashcode、toString的重写
		 * 
		 * 以上表明了这就是一个BeanDefinition对应的数据结构，等待工厂灌入数据产生生命阿
		 * ##################  GenericBeanDefinition 类##################
		 * GenericBeanDefinition类继承AbstractBeanDefinition抽象类
		 * 他的目的是提供一站式标准的bean定义，像任何bean定义,它允许指定一个类+可选地构造函数参数值和属性值。
		 * 此外,源于父bean可以灵活配置bean定义“parentName”属性。
		 * 
		 * ChildBeanDefinition 和RootBeanDefinition暂时不用以后在看。
		 * 
		 * AnnotatedGenericBeanDefinition 和 ScannedGenericBeanDefinition 都继承了GenericBeanDefinition
		 * 并实现了AnnotatedBeanDefinition接口，它们不仅有基本类的bean定义并且拥有注解元数据。
		 * AnnotatedGenericBeanDefinition一般不常用，它是基于反射的方式获取annotation的，然后返回元数据
		 * ScannedGenericBeanDefinition通过ASM方式访问的所以一般使用它。
		 * 
		 */
		
	}

}
