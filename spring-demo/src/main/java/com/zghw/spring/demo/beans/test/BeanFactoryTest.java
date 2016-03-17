package com.zghw.spring.demo.beans.test;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.AutowireCandidateQualifier;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.MethodOverride;
import org.springframework.beans.factory.support.MethodOverrides;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.style.StylerUtils;

import com.zghw.spring.demo.core.core.annonation.Fruit;
import com.zghw.spring.demo.demo.Car;
import com.zghw.spring.demo.demo.CarFactory;
import com.zghw.spring.demo.demo.CarSub;
import com.zghw.spring.demo.demo.Computer;
import com.zghw.spring.demo.demo.Legacy;
import com.zghw.spring.demo.demo.User;
import com.zghw.spring.demo.demo.WebSite;

public class BeanFactoryTest {
	static void f(Object object){
		System.out.println(object);
	}
	
	
	
	public static void printDefaultListableBeanFactoryMethod(DefaultListableBeanFactory dlbf){
		
		String[] names=dlbf.getBeanDefinitionNames();
		for(String name:names){
			f("######################"+name+"################## ");
			printBeanDefinition(dlbf.getBeanDefinition(name));
		}
		dlbf.getBean(Legacy.class).temp("sdfsd");
		f("");
	}
	public static void printBeanDefinition(BeanDefinition bdd){
		if(bdd instanceof GenericBeanDefinition){
			GenericBeanDefinition bd=(GenericBeanDefinition)bdd;
			String className=bd.getBeanClassName();
			boolean isAbstract=bd.isAbstract();
			boolean isAutowireCandidate=bd.isAutowireCandidate();
			boolean isEnforceDestroyMethod=bd.isEnforceDestroyMethod();
			boolean isEnforceInitMethod=bd.isEnforceInitMethod();
			boolean isLazyInit=bd.isLazyInit();
			boolean isLenientConstructorResolution=bd.isLenientConstructorResolution();
			boolean isNonPublicAccessAllowed=bd.isNonPublicAccessAllowed();
			boolean isPrimary=bd.isPrimary();
			boolean isPrototype=bd.isPrototype();
			boolean isSingleton=bd.isSingleton();
			boolean isSynthetic=bd.isSynthetic();
			f(", isAutowireCandidate="+isAutowireCandidate
					+", isEnforceDestroyMethod="+isEnforceDestroyMethod+", isEnforceInitMethod="+isEnforceInitMethod
					+", isSynthetic="+isSynthetic+",\n isLenientConstructorResolution="+isLenientConstructorResolution
					+", isNonPublicAccessAllowed="+isNonPublicAccessAllowed+", isPrimary="+isPrimary
					+", isLazyInit="+isLazyInit);
			f("是否是抽象bean："+isAbstract);
			f("是否是单例bean："+isSingleton);
			f("是否是原型bean："+isPrototype);
			int autowireMode=bd.getAutowireMode();
			f("bean注入模式："+autowireMode);
			////还未解析为真实的类
			//Class<?> beanClass=bd.getBeanClass();
			//f("bean的类型"+beanClass.getName());
			
			int dependencyCheck=bd.getDependencyCheck();
			f("依赖检查模式："+dependencyCheck);
			
			String[] dependsOn=bd.getDependsOn();
			f("依赖对象名称："+StylerUtils.style(dependsOn));
			
			String description=bd.getDescription();
			f("bean的描述："+description);
			
			String  factoryBeanName= bd.getFactoryBeanName();
			f("bean对应的工厂方法beanName："+factoryBeanName);
			String factoryMethodName=bd.getFactoryMethodName();
			f("bean对应的工厂方法名："+factoryMethodName);
			String parentName=bd.getParentName();
			f("引用父bean的名称，不是继承："+parentName);
			int role=bd.getRole();
			f("bean的作用："+role);
			String scope=bd.getScope();
			f("bean的范围："+scope);
			String destroyMethodName=bd.getDestroyMethodName();
			f("bean的销毁方法名称："+destroyMethodName);
			String initMethodName=bd.getInitMethodName();
			f("bean的初始化方法名称："+initMethodName);
			Set<AutowireCandidateQualifier> qualifiers =bd.getQualifiers();
			f("限定父对象："+StylerUtils.style(qualifiers));
			MethodOverrides methodOverrides =bd.getMethodOverrides();
			Set<MethodOverride> setMethodOverrides=methodOverrides.getOverrides();
			
			for(MethodOverride mo:setMethodOverrides){
				System.out.print(mo.getMethodName()+" ,");
			}
			f("重写的方法");
			ConstructorArgumentValues cavs=bd.getConstructorArgumentValues();
			f("构造参数："+StylerUtils.style(cavs));
			MutablePropertyValues mpv=bd.getPropertyValues();
			f("属性："+StylerUtils.style(mpv));
			BeanDefinition beanOriginating=bd.getOriginatingBeanDefinition();
			f("原始bean："+beanOriginating);
			Resource resource=bd.getResource();
			f("资源对象："+resource);
			String resourceDescription=bd.getResourceDescription();
			f("资源对象描述："+resourceDescription);
			//bd.getSource();
			
			//f(bd.toString());
			//f(bd.getPropertyValues());
		}
	}
	public static void main(String[] args) {
		//创建一个工厂用于代码方式的注入
		//DefaultListableBeanFactory autoWireBeanFactory=new DefaultListableBeanFactory();
		//AutowireCapableBeanFactory 功能
		//printAutoWireCapableBeanFactoryMethod(autoWireBeanFactory);
		
		
		//创建资源加载器
		ResourceLoader resourceLoader=new DefaultResourceLoader();
		//创建父资源
		Resource resourceParent =resourceLoader.getResource("application-test-beanfactory-parent.xml");
		//创建一个父工厂
		DefaultListableBeanFactory beanFactoryParent=new DefaultListableBeanFactory();
		//创建一个父xml读取器
		XmlBeanDefinitionReader beanDefinitionReaderParent = new XmlBeanDefinitionReader(beanFactoryParent);
		//加载父spring xml文件资源
		beanDefinitionReaderParent.loadBeanDefinitions(resourceParent);
		//创建资源器
		Resource resource =resourceLoader.getResource("application-test-beanfactory.xml");
		//创建一个bean工厂，并设置父工厂
		DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory(beanFactoryParent);
		//创建一个xml读取器
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		//加载spring xml文件资源
		beanDefinitionReader.loadBeanDefinitions(resource);
		//BeanFactory 功能
		//printBeanFactoryMethod(beanFactory);
		//ListableBeanFactory功能
		//printListableBeanFactoryMethod(beanFactory);
		//HierarchicalBeanFactory功能
		//printHierarchicalBeanFactoryMethod(beanFactory);
		//SingletonBeanRegistry功能
		//printSingletonBeanRegistryMethod(beanFactory);
		//DefaultListableBeanFactory功能
		printDefaultListableBeanFactoryMethod(beanFactory);
	}
	
	/**
	 * BeanFactory功能使用
	 * @param beanFactory
	 */
	public static void printBeanFactoryMethod(BeanFactory beanFactory){
		f("#######################BeanFactory方法测试#####################");
		//使用User.class查询bean实例时，必须定义唯一一个
		/*User user=bf.getBean(User.class);
		f(user);*/
		f("#####");
		User user=(User)beanFactory.getBean("user1");
		f("通过id或name或别名查询bean："+user);
		f("#####");
		Computer computer=beanFactory.getBean(Computer.class);
		f("通过Class类型查询bean："+computer);
		f("#####");
		String[] aliases= beanFactory.getAliases("wss");
		f("根据别名查询级相关联的名称：");
		for(String s:aliases){
			System.out.print(s+", ");
		}
		f("\n#####");
		Car car=beanFactory.getBean(Car.class);
		f(car);
		f("使用类型加构造方法参数或静态工厂参数修改bean实例，定义bean时，这个bean的scope=prototype才有效");
		car=beanFactory.getBean(Car.class,"dsauto",1000.44);
		f("使用类型+构造参数(应用第一个构造器)修改bean实例："+car);
		car=beanFactory.getBean(Car.class,"QQ","京23043","奇瑞");
		f("使用类型+构造参数(应用第二个构造器)修改bean实例："+car);
		//静态工厂方法实例对象
		CarFactory carFactory =(CarFactory)beanFactory.getBean(CarFactory.class);
		//动态改变静态方法实例对象
		carFactory =(CarFactory)beanFactory.getBean(CarFactory.class,"设置过的==");
		CarSub carSub=(CarSub)beanFactory.getBean(CarSub.class,"动态工厂方法创建的对象",22222);
		f("使用类型+工厂方法参数设置对象："+carSub);
		f("#####");
		carSub=beanFactory.getBean("carSub", CarSub.class);
		f("通过名称加类型直接返回相应对象 不同转换："+carSub);
		f("#####");
		beanFactory.getBean("carSub", "bean名称查找工厂方法并使用参数值修改",33333);
		f("根据方法名得到bean的确切类型："+beanFactory.getType("carSub"));
		f("#####");
		f("是否包含bean对象："+beanFactory.containsBean("carSub"));
		f("#####");
		f("bean是否是原型对象："+beanFactory.isPrototype("carSub"));
		f("#####");
		f("bean是否是单例对象："+beanFactory.isSingleton("webSite1"));
		f("#####");
		f("指定beanname的bean类型是否和指定的类型匹配"+beanFactory.isTypeMatch("wss", WebSite.class));
		f("#####");
		f("指定beanname的bean类型是否和指定的类型匹配"+beanFactory.isTypeMatch("wss", ResolvableType.forClass(CarSub.class)));
		f("#########################################################");
	}
	/**
	 * 分层等级的BeanFactory
	 * @param hbf
	 */
	public static void printHierarchicalBeanFactoryMethod(HierarchicalBeanFactory hbf){
		f("######################### HierarchicalBeanFactory接口方法  ######################");
		f("########");
		//这个类可以查询到父工厂，每个工厂只能有一个父工厂
		BeanFactory bf=hbf.getParentBeanFactory();
		f("得到父工厂");
		printBeanFactoryMethod(bf);
		f("########");
		boolean isContains=hbf.containsLocalBean("webSite4");
		f("判断给定的bean是否来自本工厂而不是父工厂："+isContains);
	}
	/**
	 * ListableBeanFactory的功能使用 在BeanFactory中多个bean进行查询
	 * @param lbf
	 */
	public static void printListableBeanFactoryMethod(ListableBeanFactory lbf){
		f("#########################  ListableBeanFactory接口方法 ###########################");
		int beanCount=lbf.getBeanDefinitionCount();
		f("包含bean的总数量："+beanCount);
		f("###########");
		String[] beanNames=lbf.getBeanDefinitionNames();
		f("所有bean的名称："+StylerUtils.style(beanNames));
		f("###########");
		String[] beanNamesByType=lbf.getBeanNamesForType(User.class);
		f("根据Class类型查询包含的bean名称："+StylerUtils.style(beanNamesByType));
		f("###########");
		String[] beanNamesByAnnotation=lbf.getBeanNamesForAnnotation(Fruit.class);
		f("查询所有类上使用过该注解的类对应的bean对象的名称集合:"+StylerUtils.style(beanNamesByAnnotation));
		f("###########");
		String[] beanNamesByResolvableType=lbf.getBeanNamesForType(ResolvableType.forClass(User.class));
		f("根据ResolvableType查询包含的bean名称："+StylerUtils.style(beanNamesByResolvableType));
		f("###########");
		//参数 1.类型 2.查询不是单例传入true 查询是单例传入false 3.不太清楚
		String[] beanNamesByTypeBoolean=lbf.getBeanNamesForType(CarSub.class, true, true);
		f("查询是不是singleton类型的bean名称："+StylerUtils.style(beanNamesByTypeBoolean));
		f("###########");
		Map<String,User> beanTypeMap=lbf.getBeansOfType(User.class);
		f("根据指定类型得到bean名称和对象组成的Map："+StylerUtils.style(beanTypeMap));
		f("###########");
		//参数 1.类型 2.查询不是单例传入true 查询是单例传入false 3.不太清楚
		Map<String,CarSub> beanTypeBooleanMap=lbf.getBeansOfType(CarSub.class, false, true);
		f("根据指定类型,是否过滤singleton和工厂方法得到bean名称和对象组成的Map："+StylerUtils.style(beanTypeBooleanMap));
		f("###########");
		Map<String,Object> beanTypeAnnontation=lbf.getBeansWithAnnotation(Fruit.class);
		f("查询所有类上使用过该注解的类对应的bean对象Map表:"+StylerUtils.style(beanTypeAnnontation));
		f("###########");
		boolean isContains=lbf.containsBeanDefinition("webSite1");
		f("查询是否包含指定的BeanDefinition"+isContains);
		f("###########");
		Fruit fruit=lbf.findAnnotationOnBean("webSite1", Fruit.class);
		f("查询指定类型上的指定注解类型对象："+fruit.price());
		f("######################################################################");
	}
	//AutowireCapableBeanFactory功能暂时不会
	public static void printAutoWireCapableBeanFactoryMethod(AutowireCapableBeanFactory acbf){
		//功能方法不太会用
		//这个对象时原型的不会存储在注册表中，所以无法查询到
		User user=acbf.createBean(User.class);
		//f(acbf.getBean(User.class));
		User userNew=new User();
		userNew.setAge(15);
		userNew.setName("zghw");
		//初始化bean后，在填充对象属性时，例如注解注入
		acbf.autowireBean(userNew);
		//配置bean
		user=(User) acbf.configureBean(userNew,"configUser");
		//解析依赖 不太懂
		//acbf.resolveDependency(descriptor, beanName);
		//acbf.resolveDependency(descriptor, beanName, autowiredBeanNames, typeConverter);
		//f(user);
		f(acbf.getBean(User.class));
		//beforeInstantiationResolved
		//f(user);
	}
	/**
	 * SingletonBeanRegistry单例对象的注册管理器
	 * @param sbr
	 */
	public static void printSingletonBeanRegistryMethod(SingletonBeanRegistry sbr){
		f("###################### SingletonBeanRegistry #######################");
		//注册一个单例对象到注册表中
		sbr.registerSingleton("abc", new WebSite());
		sbr.registerSingleton("abc1", new WebSite());
		sbr.registerSingleton("abc2", new WebSite());
		f("######");
		int count=sbr.getSingletonCount();
		f("singleton的注册数量，只表示在此注册表中的数量："+count);
		f("######");
		String[] singletonNames=sbr.getSingletonNames();
		f("返回在此注册表中的单例名称："+StylerUtils.style(singletonNames));
		f("######");
		boolean isContains=sbr.containsSingleton("webSite2");
		f("指定beannanme，是否已经存在单例bean："+isContains);
		f("######");
		WebSite webSite=(WebSite)sbr.getSingleton("abc");
		f("根据名称查询在注册表注册过的对象，返回null表示没有注册过："+webSite);
		f("######");
		Object obj=sbr.getSingletonMutex();
		f("得到注册时互斥对象"+obj);
	}
	
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
