package com.zghw.spring.demo.core.core;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.core.serializer.Deserializer;
import org.springframework.core.serializer.Serializer;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.core.style.StylerUtils;
import org.springframework.util.StreamUtils;

/**
 * 序列化和泛序列化工具 使用了策略模式
 * @author zghw
 *
 */
public class SerializerTest {
	static void f(Object obj) {
		System.out.println(obj);
	}
	public static void main(String[] args) throws IOException {
		Map<String,String> map =new HashMap<String,String>();
		map.put("key1", "value1");
		map.put("key3", "value3");
		map.put("key2", "value2");
		f("序列号前：map == "+StylerUtils.style(map));
		
		List<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<5;i++){
			list.add(i);
		}
		Serializer ser=new DefaultSerializer();
		//基本的序列化方式
		OutputStream outputStreama = new FileOutputStream(new File("/home/zghw/aa.txt"));
		ser.serialize(map, outputStreama);
		outputStreama.close();
		//基本的泛序列方式
		InputStream isa = new FileInputStream(new File("/home/zghw/aa.txt"));
		//可以设置类加载器
		Deserializer deser=new DefaultDeserializer(SerializerTest.class.getClassLoader()); 
		Map<String,String> m=(Map<String,String>)deser.deserialize(isa);
		f("序列后：map == "+StylerUtils.style(m));
		isa.close();
		//使用byte[]转化对象
		//使用了策略模式，很简单的转化对象
		f("list == "+StylerUtils.style(list));
		SerializingConverter sc=new SerializingConverter(ser);
		byte[] bb=sc.convert(list);
		DeserializingConverter dsc=new DeserializingConverter();
		List<String> lis=(List<String>)dsc.convert(bb);
		f("反序列化后 list == "+StylerUtils.style(list));
	}

}
