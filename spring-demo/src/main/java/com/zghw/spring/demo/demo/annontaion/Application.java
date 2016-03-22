package com.zghw.spring.demo.demo.annontaion;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zghw.spring.demo.demo.annontaion.app.AppConfig;
import com.zghw.spring.demo.demo.annontaion.app.ConfigA;
import com.zghw.spring.demo.demo.annontaion.app.MyProfile;
import com.zghw.spring.demo.demo.annontaion.app.component.Bar;

/**
 * @Configuration 是被元数据 @Component 注解过的，so 它们都是会被扫描器@ComponentScan扫描，因为扫描器会扫描到
 *                对应的包中，@ComponentScan("com.zghw.spring.demo.demo.annontaion")，
 *                对扫描到的类调用，然后刷新容器，运行它们的 @Bean methods,作为BeanDefintion注册到容器中.
 * @author zghw
 *
 */
public class Application {
	public static void main(String args[]) {
		/**
		 * 使用spring驱动配置类，创建一个AnnotationConfigApplicationContext，配置@Configurable下的类作为输入
		 * ,@ComponentScan 作为扫描标记器. 构建AnnotationConfigApplicationContext不仅支持 @Configuration
		 * 还支持所有 @Component 组件。
		 * 
		 * @Configuration 会首先注册自己的BeanDefinition，然后注册该注解类下所有的 @Bean
		 *                方法返回的类的BeanDefinition
		 * @Component 中只有使用了元注解 @autowired 或 @inject 才会注册BeanDefinition
		 *            个人认为：不建议使用@Component组件来定义，使用 @Configuration
		 *            让它在@Component包的上层，加载@Configuration下的类，
		 *            扫描它对应的当前包中的类和当前包包含的所有类。
		 */
		// ApplicationContext ctx = new
		// AnnotationConfigApplicationContext(AppConfig.class);
		// ctx=new AnnotationConfigApplicationContext(new
		// Class<?>[]{MyBean.class,MyServiceImpl.class});
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		//设置环境变量
		ctx.getEnvironment().setActiveProfiles("prod");
		ctx.register(AppConfig.class);
		ctx.refresh();

		/*f(ctx.getBean("mybean"));
		f(ctx.getBean("myControllerBean"));
		f(ctx.getBean("bussiness"));
		f(ctx.getBean("foo"));*/
		ConfigA config = (ConfigA)ctx.getBean("configA");
		MyProfile myProfile = (MyProfile)ctx.getBean(MyProfile.class);
		f(config.myUrl());
		f(myProfile.getMyLocationSQL());
		Bar bar=(Bar)ctx.getBean("myBar");
		bar.test();
	}

	static void f(Object o) {
		System.out.println(o);
	}
}
