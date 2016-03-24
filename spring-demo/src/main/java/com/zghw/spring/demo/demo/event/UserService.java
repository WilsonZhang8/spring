package com.zghw.spring.demo.demo.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * 实现ApplicationEventPublisherAware让容器ApplicationContext作为事件发布中心，
 * 因为ApplicationContext实现了ApplicationEventPublisher
 * @author zghw
 *
 */
@Service
public class UserService implements ApplicationEventPublisherAware{
	private ApplicationEventPublisher publisher;
	
	
	public void registerUser(String name,String phone) throws InterruptedException{
		System.out.println("注册用户中");
		Thread.sleep(300);
		System.out.println("注册完成！");
		
		Message message=new Message();
		message.setMessage("你好，"+name+" 你中了1000W");
		message.setPhone(phone);
		SendMessageEvent event=new SendMessageEvent(this,message);
		//发布中心发布事件
		publisher.publishEvent(event);
	}


	/**
	 * 实现ApplicationEventPublisherAware的方法，spring在使用时UserServicebean对象时会自动帮我们注入
	 * ApplicationEventPublisher的实现
	 */
	public void setApplicationEventPublisher(
			ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}
}
