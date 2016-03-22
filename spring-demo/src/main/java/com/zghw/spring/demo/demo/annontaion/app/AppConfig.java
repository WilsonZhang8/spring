package com.zghw.spring.demo.demo.annontaion.app;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.zghw.spring.demo.demo.annontaion.app.component.Bar;
import com.zghw.spring.demo.demo.annontaion.app.component.Foo;
import com.zghw.spring.demo.demo.annontaion.app.component.MyBean;
import com.zghw.spring.demo.demo.annontaion.app.controller.MyController;
import com.zghw.spring.demo.demo.annontaion.app.repository.MyRepository;
import com.zghw.spring.demo.demo.annontaion.app.repository.MyRepositoryImpl;
import com.zghw.spring.demo.demo.annontaion.app.service.MyService;
import com.zghw.spring.demo.demo.annontaion.app.service.MyServiceImpl;

/**
 *@Configuration 是一个类级别的注解,对象对应的BeanDefition的一个源产地.
 *定义的beans是通过被 @Bean 注解过的方法返回的bean.
 *调用 @Bean 下的方法会自动解析依赖关系.
 * 
 * 全配置 @Configuration vs 简化配置 @Beans
 * 如果 @Bean 不是配置在 @Configuration 中，那么这个bean就是简化配置，
 * 比如：这个 @Bean 是配置在 @Component 中或者普通类下，就是简化配置。
 * @Bean 配置在 @Configuration 是全配置模式
 * 简化配置时，一个bean调用另一个bean是方法的调用，AOP的时候会出现代理该类。
 * 不太懂！说是简化配置时，这个bean时工厂方法产生的bean，是CGlib动态代理，
 * AOP拦截不到。
 * 反正spring 就是建议你写在 @Bean 在 @Configuration 注解的类下使用就是了。
 * 
 * 
 * @ComponentScan 组件扫描器，默认扫描 被 @Configuration 定义类对应的包下面的所有类
 * 
 * @Import(ConfigA.class)导入应一个 @Configuration 标记的类，用来合并bean
 * @author zghw
 *
 */
@Configuration
@ComponentScan("com.zghw.spring.demo.demo.annontaion")
@Import({ConfigA.class,FactoryBeanConfig.class})
public class AppConfig {
	/**
	 * @Bean 方法级别的注解 
	 * 应用在方法上得到方法返回的实例，并初始化一个新的BeanDefintion有IOC容器管理。
	 * 就像xml中的<bean>元素一样.
	 * 方法的名字就是id,方法返回对象的类型就是class
	 * myControllerBean  --> com.zghw.spring.demo.demo.annontaion.app.controller.MyController
	 * 依赖:方法参数就是依赖的对象 com.zghw.spring.demo.demo.annontaion.app.service.MyService
	 * 你可以 @Bean 注解一个在任何 @Component 组件中的方法上，
	 * 但通常的它一般用于注解在 @Configuration 中的方法上返回对象。
	 * 使用 @Configuration 注解的类，代表了这个类的主要目的就是配置Bean定义。就像
	 * 一个XML文档一样,另外它允许 @Bean 可以通过调用其他 @Bean 方法建立依赖关系。
	 * @return
	 */
	/**
	 * 初始化方法和销毁方法
	 * scope设置
	 * 描述符
	 * parent不用配置 直接引用方法就是了
	 * 抽象的就不定义扫描它就行了
	 * @return
	 */
	@Bean(name="myB",initMethod="init",destroyMethod="cleanup")
	@Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE,proxyMode=ScopedProxyMode.TARGET_CLASS)
	@Description("Provides a basic example of a bean")
	public MyBean myBean(){
		return new MyBean();
	}
	@Bean
	public MyController myControllerBean(MyService facde){
		facde.getMessage();
		return new MyController();
	}
	/**
	 * 别名
	 * @return
	 */
	@Bean(name={"facde","bussiness"})
	public MyService myServiceBean(){
		return new MyServiceImpl();
	}
	/**
	 * Primary如果接口实现有多个，优先注入该bean
	 * @return
	 */
	@Bean
	@Primary 
	public MyService myServiceBean2(){
		return new MyServiceImpl();
	}
	@Bean
	public MyRepository myRepositoryBean(){
		return new MyRepositoryImpl();
	}
	
	@Bean
	public Bar myBar(){
		Bar bar=new Bar();
		bar.setName("sdfsddfs");
		return bar;
	}
	
	/**
	 * 内部引用bean只在@Configuration下使用 ,不要在 @Component 所有组件使用
	 * @return
	 */
	@Bean
	public Foo foo(){
		return new Foo(bar());
	}
	@Bean
	public Bar bar(){
		return new Bar();
	}
	
}
