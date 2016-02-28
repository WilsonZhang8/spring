package com.zghw.spring.demo.core.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.springframework.util.ResourceUtils;

/**
 * 用处： 用于io资源操作 比如 classpath--URL-->File
 * 资源管理器使用示例
 * @author zghw
 *
 */
public class ResourceUtilsTest {
	static void f(Object obj) {
		System.out.println(obj);
	}
	public static void main(String[] args) throws FileNotFoundException {
		//判断一个字符串是否是url地址
		boolean b=ResourceUtils.isUrl("classpath:application.xml");
		f(b);
		b=ResourceUtils.isUrl("http://www.baidu.com");
		f(b);
		//给定一个string路径，得到一个URL对象，如果是classpath下的路径
		//根据类加载器得到classpath的URL
		//如果是URL可以解析的路径则返回
		//否则尝试file路径返回URL对象
		URL url=ResourceUtils.getURL("classpath:com/zghw/spring/demo/core/util/resource.txt");
		url=ResourceUtils.getURL("http://www.baidu.com");
		url=ResourceUtils.getURL("/home/zghw/a.txt");
		//Error找不到资源文件
		//url=ResourceUtils.getURL("classpath:com/zghw/xxxx");
		//根据上面的规则生成对应的文件对象
		File f=ResourceUtils.getFile("classpath:com/zghw/spring/demo/core/util/resource.txt");
		f=ResourceUtils.getFile(url);
		//f=ResourceUtils.getFile("http://www.baidu.com");无法生成文件
		f=ResourceUtils.getFile("/home/zghw/a.txt");
		
	}

}
