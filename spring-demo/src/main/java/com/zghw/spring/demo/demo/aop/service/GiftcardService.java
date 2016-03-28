package com.zghw.spring.demo.demo.aop.service;

import com.zghw.spring.demo.demo.aop.pojo.Giftcard;

public interface GiftcardService {
	
	public void save(Giftcard giftcard);

	public Giftcard findByCardNo(String cardNo);

	public Giftcard update(Giftcard giftcard);
}
