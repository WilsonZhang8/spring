package com.zghw.spring.demo.core.util;

import org.springframework.util.ConcurrencyThrottleSupport;
/**
 * 用处：控制并发任务数量
 * 
 * 在工作前 如果线程并发数大于线程设置的最大数，则线程等待其他线程释放
 * 否则就线程任务，
 * 做完任务通知线程数减少了 可以重新判断其他线程是否等待
 * @author zghw
 *
 */
public class ConcurrencyThrottleSupportBase extends ConcurrencyThrottleSupport {
	public void doThing(){
		this.beforeAccess();
		System.out.println("");
		this.afterAccess();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		ConcurrencyThrottleSupportBase bts =new ConcurrencyThrottleSupportBase();
		//设置线程最大数 默认为-1 无限限制
		bts.setConcurrencyLimit(2);
		bts.doThing();
		//是否有在工作的线程活动
		bts.isThrottleActive();
		
	}

}
