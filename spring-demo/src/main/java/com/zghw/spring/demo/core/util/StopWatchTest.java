package com.zghw.spring.demo.core.util;

import org.springframework.util.StopWatch;

/**
 * 妙表用来计算任务时间
 * @author zghw
 *
 */
public class StopWatchTest {

	public static void main(String[] args) throws InterruptedException {
		StopWatch sw =new StopWatch();
		sw.start();
		System.out.println("开始运行");
		Thread.sleep(2020);
		sw.stop();
		System.out.println(sw.prettyPrint());
	}

}
