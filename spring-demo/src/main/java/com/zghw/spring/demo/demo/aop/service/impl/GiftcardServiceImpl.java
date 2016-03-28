package com.zghw.spring.demo.demo.aop.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.zghw.spring.demo.demo.aop.pojo.Giftcard;
import com.zghw.spring.demo.demo.aop.service.GiftcardService;
@Component("giftcardService")
public class GiftcardServiceImpl implements GiftcardService {
	private final Map<String, Giftcard> giftcards = new HashMap<String, Giftcard>();

	public void save(Giftcard giftcard) {
		System.out.println("开始保存对象。。。。");
		giftcards.put(giftcard.getCardNo(), giftcard);
		try {
			//模拟一下时间
			Thread.sleep((int)(Math.random()*100));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		System.out.println("保存完成");
	}

	public Giftcard findByCardNo(String cardNo) {
		Giftcard giftcard = giftcards.get(cardNo);
		System.out.println("执行目标方法：findByCardNo");
		return giftcard;
	}

	public Giftcard update(Giftcard giftcard) {
		Giftcard card = giftcards.put(giftcard.getCardNo(), giftcard);
		return card;
	}

}
