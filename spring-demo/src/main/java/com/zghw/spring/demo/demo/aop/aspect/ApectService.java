package com.zghw.spring.demo.demo.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.stereotype.Component;

/**
 * 定义切面
 * @author zghw
 *
 */
@Aspect
@Component
//激活AspectJ进行自动代理
@EnableAspectJAutoProxy
public class ApectService {
	@Pointcut("execution(* com.zghw.spring.demo.demo.aop.Worker.*(..))")
	public void myPointCut(){}
	
	@Before("myPointCut()")
	public void  doCheck(JoinPoint joinPoint){
		System.out.println("前置通知");
		joinPoint.getArgs();
	}
}
