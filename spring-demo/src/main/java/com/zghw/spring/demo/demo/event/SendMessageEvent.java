package com.zghw.spring.demo.demo.event;

import org.springframework.context.ApplicationEvent;
/**
 * 定义一个发送短信的事件
 * 实现了ApplicationEvent
 * @author zghw
 *
 */
public class SendMessageEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	//消息对象
	private Message message;
	
	//source代表了发布该事件的发布源
	public SendMessageEvent(Object source,Message message) {
		super(source);
		this.message = message;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
}
