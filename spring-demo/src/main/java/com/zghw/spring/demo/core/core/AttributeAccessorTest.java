package com.zghw.spring.demo.core.core;

import org.springframework.beans.BeanMetadataAttribute;
import org.springframework.beans.BeanMetadataAttributeAccessor;
import org.springframework.core.AttributeAccessor;
/**
 * 管理属性访问的类
 * @author zghw
 *
 */
public class AttributeAccessorTest {

	public static void main(String[] args) {
		BeanMetadataAttribute ba = new BeanMetadataAttribute("list",new Integer[]{1,2,3});
		BeanMetadataAttributeAccessor bac=new BeanMetadataAttributeAccessor();
		bac.addMetadataAttribute(ba);
		AttributeAccessor aa =bac;
		//取得属性
		Integer[] in=(Integer[])aa.getAttribute("list");
		for(int i : in){
			System.out.println(i);
		}
		//设置属性
		aa.setAttribute("aaa", new BeanMetadataAttribute("aaa",111));
		aa.setAttribute("bbb", new BeanMetadataAttribute("bbb",222));
		aa.setAttribute("ccc", 333);
		//属性是否存在
		System.out.println(aa.hasAttribute("aaa"));
		System.out.println(aa.getAttribute("aaa"));
		System.out.println(aa.getAttribute("ccc"));
		//移除属性
		aa.removeAttribute("aaa");
		System.out.println(aa.hasAttribute("aaa"));
	}

}
