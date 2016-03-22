package com.zghw.spring.demo.demo.annontaion.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zghw.spring.demo.demo.User;
import com.zghw.spring.demo.demo.annontaion.app.controller.MyController;
import com.zghw.spring.demo.demo.annontaion.app.service.MyService;

/** 
 * @ImportResource 导入资源xml资源 一般是bean定义
 *  @PropertySource导入属性properties资源 使用Environment得到变量
 * @author zghw
 *
 */
@Configuration
@ImportResource("classpath:application-test-annotation.xml")
@PropertySource("my-prod.properties")
public class ConfigA {
	@Autowired
	private Environment env;
	/**
	 * 在xml配置属性占位符，设置参数
	 */
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	@Value("${jdbc.driver}")
	private String driver;
	@Value("${jdbc.url}")
	private String url;
	
	public String myUrl(){
		return username+" "+password+" "+driver+" url";
	}
	
	/**
	 * @Profile 设置配置的环境
	 * @return
	 */
	@Bean
	@Profile("prod")
	public MyProfile prodProfile(){
		return new MyProfile(env.getProperty("alias"));
	}
	@Bean
	@Profile("test")
	public MyProfile testProfile(){
		return new MyProfile("test");
	}
	@Bean
	@Profile("default")
	public MyProfile defaultProfile(){
		return new MyProfile("default");
	}
	/**
	 * 依赖bean自动的注入
	 * 当 @Bean 配置在 @Configuration 下，
	 * 该 @Bean 的方法参数就是依赖的对象，方法参数名字和其他 @Bean 注解过的方法名称相同匹配，
	 * 则spring会自动的帮你维护依赖关系，创建或查找依赖的对象，你直接调用这个方法就可以了。
	 * @param myControllerBean
	 * @param bussiness
	 * @return
	 */
	@Bean
	public User myUser(MyController myControllerBean,MyService bussiness){
		System.out.println(myControllerBean.excute(bussiness));
		return new User();
	}
}
