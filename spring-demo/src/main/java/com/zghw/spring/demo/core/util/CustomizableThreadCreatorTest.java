package com.zghw.spring.demo.core.util;

import org.springframework.util.CustomizableThreadCreator;

/**
 * 用处：可配置线程信息
 * 配置一个线程，最要可以设置一些线程的基本参数
 * @author zghw
 *
 */
public class CustomizableThreadCreatorTest {

	public static void main(String[] args) {
		CustomizableThreadCreator ctc =new CustomizableThreadCreator("线程前最");
		ctc.createThread(new Runnable(){
			public void run() {
				System.out.println(Thread.currentThread().getName()+" 老阿披");
			}}).start();
	}

}
