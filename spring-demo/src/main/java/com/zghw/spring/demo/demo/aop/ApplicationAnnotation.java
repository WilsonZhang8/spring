package com.zghw.spring.demo.demo.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.zghw.spring.demo.demo.aop.pojo.Giftcard;
import com.zghw.spring.demo.demo.aop.service.GiftcardService;
@Configuration 
@ComponentScan
//激活AspectJ进行自动代理
@EnableAspectJAutoProxy
public class ApplicationAnnotation {

	public static void main(String[] args) {
		ApplicationContext ctx =new AnnotationConfigApplicationContext(ApplicationAnnotation.class);
		GiftcardService giftcardService = (GiftcardService) ctx
				.getBean("giftcardService");
		//模拟添加数据到数据库中
		Giftcard card = new Giftcard();
		card.setCardNo("11111111");
		card.setDescription("第一个对象");
		card.setName("zghw");
		card.setPrice(1322546.45);
		//模拟保存对象到数据库中
		giftcardService.save(card);
		Giftcard card2 = new Giftcard();
		card2.setCardNo("2222222");
		card2.setDescription("第二个对象");
		card2.setName("dfsdf");
		card2.setPrice(467732346.45);
		//模拟保存对象到数据库中
		giftcardService.save(card2);
		//模拟先从缓存中取去不到就去数据库中取值
		System.out.println("=======第一次==========");
		giftcardService.findByCardNo("11111111");
		System.out.println("=========第二次========");
		giftcardService.findByCardNo("11111111");
		System.out.println("=========第三次========");
		giftcardService.findByCardNo("11111111");
	}

}
