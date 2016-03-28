package com.zghw.spring.demo.demo.aop;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.aop.aspectj.AspectJAfterReturningAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.SimpleAspectInstanceFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;

import com.zghw.spring.demo.demo.aop.aspect.ApectService;
import com.zghw.spring.demo.demo.aop.aspect.MyIncrete;
import com.zghw.spring.demo.demo.aop.aspect.MyInterface;
//@Configuration
//@ComponentScan
public class ProxyFactoryTest {

	/**
	 * 使用工厂bean代理一个对象，get("factoryBean")得到的是代理对象myIncrete
	 * @param myIncrete
	 * @return
	 */
	@Bean
	public ProxyFactoryBean factoryBean(MyInterface myIncrete){
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		//设置目标类型
		proxyFactoryBean.setTargetClass(MyIncrete.class);
		//设置目标接口
		proxyFactoryBean.setInterfaces(MyInterface.class);
		//设置目标对象
		proxyFactoryBean.setTarget(myIncrete);
		
		return proxyFactoryBean;
	}
	
	@Bean
	public Advice advice(AfterReturningAdviceInterceptor afterReturningAdviceInterceptor){
		return afterReturningAdviceInterceptor;
	}
	@Bean
	public AfterReturningAdviceInterceptor afterReturningAdviceInterceptor(AfterReturningAdvice afterReturningAdvice){
		return new AfterReturningAdviceInterceptor(afterReturningAdvice);
	}
	@Bean
	public AfterReturningAdvice afterReturningAdvice(){
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		Method aspectJBeforeAdviceMethod = ReflectionUtils.findMethod(ApectService.class, "doCheck");
		AspectInstanceFactory aif=null;
		AspectJAfterReturningAdvice aspectJAfterReturningAdvice =new AspectJAfterReturningAdvice(aspectJBeforeAdviceMethod,pointcut,aif);
		return aspectJAfterReturningAdvice;
	}
	/**
	 * 使用普通的方式代理bean，需要使用ProxyFactory.getProxy()才能得到代理对象
	 * @param worker
	 * @return
	 */
	@Bean 
	public ProxyFactory proxyFactory(Worker worker){
		ProxyFactory proxyFactory = new ProxyFactory(Worker.class);
		//强制设置代理该对象，不需要接口
		proxyFactory.setProxyTargetClass(true);
		proxyFactory.setTarget(worker);
		return proxyFactory;
	}
	
	/*@Bean
	public AdvisedSupport advisor(MyInterface myIncrete){
		AdvisedSupport advisor=new AdvisedSupport();
		advisor.setTarget(myIncrete);
		return advisor;
	}*/
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ProxyFactoryTest.class);
		ProxyFactory proxyFactory=(ProxyFactory)ctx.getBean("proxyFactory");
		System.out.println("ProxyFactory proxy "+proxyFactory);
		System.out.println("ProxyFactory proxy "+(proxyFactory.getProxy() instanceof Worker));
		System.out.println("ProxyFactory proxy "+(proxyFactory.getProxy() instanceof Serializable));
		Worker worker = (Worker)proxyFactory.getProxy();
		worker.doSomething();
		MyInterface myInterface=(MyInterface)ctx.getBean("myIncrete");
		System.out.println(myInterface);
		ProxyFactoryBean factoryBean=(ProxyFactoryBean)ctx.getBean("&factoryBean");
		System.out.println(factoryBean);
		//真实的实例被代理了
		System.out.println(ctx.getBean("factoryBean").getClass());
		//工厂bean得到的是代理对象
		System.out.println(ctx.getBean("factoryBean") instanceof MyInterface);
		MyInterface mi = (MyInterface)ctx.getBean("factoryBean");
		int result = mi.add(1, 2);
		System.out.println(result);
	}

}
