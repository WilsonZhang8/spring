package com.zghw.spring.demo.core.core.style;

import java.util.*;

import org.springframework.core.style.StylerUtils;

/**
 * 格式化输出对象
 * @author zghw
 *
 */
public class ToStringStylerTest {
	static void f(Object obj) {
		System.out.println(obj);
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String str=null;
		f("null == "+StylerUtils.style(str));
		str="asdfasd";
		f("str == "+StylerUtils.style(str));
		Map<String,String> map =new HashMap<String,String>();
		map.put("key1", "value1");
		map.put("key3", "value3");
		map.put("key2", "value2");
		f("map == "+StylerUtils.style(map));
		
		List<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<5;i++){
			list.add(i);
		}
		f("list == "+StylerUtils.style(list));
		
		Set<Class> set=new HashSet<Class>();
		set.addAll(Arrays.asList(List.class,Object.class,Thread.class));
		f("set == "+StylerUtils.style(set));
		
		int[] array =new int[3];
		array[0]=1;
		array[1]=2;
		array[2]=3;
		f("array == "+StylerUtils.style(array));
		
		
		
	}

}
