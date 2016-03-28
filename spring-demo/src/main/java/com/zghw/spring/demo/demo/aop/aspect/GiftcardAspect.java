package com.zghw.spring.demo.demo.aop.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.zghw.spring.demo.demo.aop.pojo.Giftcard;
import com.zghw.spring.demo.demo.aop.service.GiftcardService;

/**
 * 定义切面
 * 
 * @author zghw
 *
 */
@Aspect
@Component
public class GiftcardAspect {
	// 用来缓存查询到的giftCard
	private final Map<String, Object> cardCache = new HashMap<String, Object>();

	@Pointcut("execution(public * com.zghw.spring.demo.demo.aop.service.impl.GiftcardServiceImpl.findByCardNo(String)) && args(cardNo)")
	public void findByCardNo(String cardNo) {
	}

	/**
	 * 前置通知
	 * 
	 * @param cardNo
	 */
	@Before(value = "findByCardNo(cardNo)")
	public void findByCardNoCacheBefore(String cardNo) {
		System.out.println("前置通知：开始尝试从缓存中取：" + cardNo);
		if (cardCache.containsKey(cardNo)) {
			System.out.println("存在缓存中" + cardNo);
		} else {
			System.out.println("不存在缓存中" + cardNo);
		}
	}

	/**
	 * 在findByCardNo加入缓存
	 */
	// @AfterReturning(pointcut="findByCardNo(cardNo)",returning="retVal")
	// 或者使用
	@AfterReturning(value = "execution(public * com.zghw.spring.demo.demo.aop.service.impl.GiftcardServiceImpl.findByCardNo(String)) && args(cardNo)", argNames = "cardNo,retVal", returning = "retVal")
	public Giftcard findByCardNoCache(String cardNo, Giftcard retVal) {
		if (!cardCache.containsKey(cardNo)) {
			System.out.println("后置通知：不存在就加入缓存中" + cardNo);
			cardCache.put(retVal.getCardNo(), retVal);
		}
		return retVal;
	}

	/**
	 * JoinPoint可以在任何通知方法中作为第一个参数使用，可以用来访问加入点的很多信息。
	 * 
	 * @param joinPoint
	 */
	@After(value = "execution(* com.zghw.spring.demo.demo.aop.*.*.findByCardNo(..))")
	public void findByCardNoCacheAfter(JoinPoint joinPoint) {
		System.out.println("最后通知，已经完成。");
		System.out.println("来看看这个对象JoinPoint有什么作用");
		Object target = joinPoint.getTarget();
		System.out.println("目标对象：" + target);
		System.out.println("目标对象类型：" + target.getClass());
		if (target instanceof GiftcardService) {
			GiftcardService gs = (GiftcardService) target;
			// 使用目标类型调用其他方法
			System.out.println(gs.findByCardNo("2222222"));
		}
		Object[] args = joinPoint.getArgs();
		System.out.println("参数：长度" + args.length);
		int i = 0;
		for (Object arg : args) {
			System.out.println("参数" + (++i) + " :类型" + arg.getClass());
		}
		Object proxy = joinPoint.getThis();
		System.out.println("这个得到代理对象 " + proxy);
		System.out.println("得到代理对象类型 " + proxy.getClass());
		System.out.println("从这里可以看出来它代理的是GiftcardService == "
				+ (proxy instanceof GiftcardService));
		System.out.println("方法签名：" + joinPoint.getSignature());
		System.out.println(joinPoint.getSourceLocation());
		System.out.println(joinPoint.getKind());
		System.out.println(joinPoint.getStaticPart().toShortString());
	}

	/**
	 * 前置通知和后置通知不再一起很难控制目标对象调用方法， 比如前置方法判断出了该对象在缓存中，能够取得值而不能返回值缓存中的对象。
	 * 后置方法虽然能够返回值，但是不能够在目标方法调用前返回值。 使用环绕通知就很好的解决问题。 环绕通知
	 * 
	 * @param giftcard
	 * @throws Throwable
	 */
	@Around(value = "findByCardNo(cardNo)")
	public Giftcard findByCardNoCacheAround(ProceedingJoinPoint pjp,
			String cardNo) throws Throwable {
		Giftcard card = null;
		System.out.println("Around:前置通知：开始尝试从缓存中取：" + cardNo);
		if (cardCache.containsKey(cardNo)) {
			System.out.println("Around:存在缓存中" + cardNo);
			// 如果缓存中存在就不需要从目标对象中取
			return (Giftcard) cardCache.get(cardNo);
		}
		card = (Giftcard) pjp.proceed();// 通过一个个通知方法拦截器链得到返回的对象。
		if (!cardCache.containsKey(card.getCardNo())) {
			System.out.println("Around:后置通知：不存在就加入缓存中" + cardNo);
			cardCache.put(card.getCardNo(), card);
		}
		return card;
	}

	/**
	 * 用来测试一个方法的执行时间
	 * 
	 * @param pjp
	 * @param giftcard
	 */
	@Around(value = "execution(* com.zghw.spring.demo.demo.aop.*.*.save(com.zghw.spring.demo.demo.aop.pojo.Giftcard)) and args(giftcard)", argNames = "giftcard")
	public void saveTime(ProceedingJoinPoint pjp, Giftcard giftcard) {
		StopWatch sw = new StopWatch();
		sw.start(giftcard.getDescription());
		try {
			pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		sw.stop();
		System.out.println(sw.prettyPrint());
	}
}
