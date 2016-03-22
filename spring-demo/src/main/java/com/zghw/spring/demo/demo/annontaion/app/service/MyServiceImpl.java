package com.zghw.spring.demo.demo.annontaion.app.service;

import org.springframework.stereotype.Service;
/**
 * 使用@@Service被注解的类经常被代表是一个“服务”(例如业务 服务  Business Service Facade),
 * 当使用自动扫描classpath下的类时，会自动的把被@Service注解过的类加入到候选类中。
 * 它是作为一个专业化的 @Component组件
 * @author zghw
 */
@Service
public class MyServiceImpl implements MyService {

	public String getMessage() {
		String message="message= I'm service Impl";
		return message;
	}

}
