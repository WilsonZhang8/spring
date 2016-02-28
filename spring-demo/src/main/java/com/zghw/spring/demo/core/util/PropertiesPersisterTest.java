package com.zghw.spring.demo.core.util;

import java.io.*;
import java.util.Properties;

import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * 用处：加载属性文件转换为Properties为一个接口对象 以便可以使用模式
 * 属性文件的输入和输出
 * @author zghw
 *
 */
public class PropertiesPersisterTest {

	public static void main(String[] args) throws IOException {
		PropertiesPersister pp = new DefaultPropertiesPersister();
		Properties properties = new Properties();
		InputStream is = new FileInputStream("/home/zghw/a.txt");
		Reader rd =new BufferedReader(new StringReader("bb=cc cc=aa aa=bb"));
		OutputStream os =new FileOutputStream("/home/zghw/b.txt");
		StringWriter writer = new StringWriter();
		//加载字节流到properties中
		pp.load(properties, is);
		System.out.println(properties);
		//加载字符流到properties中
		pp.load(properties, rd);
		System.out.println(properties);
		//读取内容到字节流中
		pp.store(properties, os, "第一");
		System.out.println(properties);
		//读取内容到字符流中
		pp.store(properties, writer, "第二");
		System.out.println(properties);
		System.out.println(writer.getBuffer());
		
	}

}
