package com.zghw.spring.demo.demo.annontaion.app.controller;

import org.springframework.stereotype.Controller;

import com.zghw.spring.demo.demo.annontaion.app.service.MyService;
/**
 * 使用@Controller被注解的类经常被代表是一个“控制器”(例如web控制器),
 * 当使用自动扫描classpath下的类时，会自动的把被@Controller注解过的类加入到候选类中。
 * 它是作为一个专业化的 @Component组件
 * @author zghw
 *
 */
@Controller
public class MyController {
	public String excute(MyService ms){
		return ms.getMessage();
	}
}
