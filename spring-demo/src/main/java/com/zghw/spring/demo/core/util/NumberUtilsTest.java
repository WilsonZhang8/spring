package com.zghw.spring.demo.core.util;

import org.springframework.util.NumberUtils;

/**
 * 把Number下的类型互相转换
 * 
 * @see java.lang.Byte
 * @see java.lang.Short
 * @see java.lang.Integer
 * @see java.lang.Long
 * @see java.math.BigInteger
 * @see java.lang.Float
 * @see java.lang.Double
 * @see java.math.BigDecimal
 * @author zghw
 *
 */
public class NumberUtilsTest {

	public static void main(String[] args) {
		Byte bb=12;
		Number n=bb;
		Integer i=NumberUtils.convertNumberToTargetClass(bb, Integer.class);
		Long l=NumberUtils.convertNumberToTargetClass(i, Long.class);
		System.out.println(i +" "+l);
	}

}
