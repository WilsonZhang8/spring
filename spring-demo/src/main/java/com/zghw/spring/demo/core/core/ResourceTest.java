package com.zghw.spring.demo.core.core;

import java.io.IOException;

import org.springframework.core.io.*;
import org.springframework.util.StreamUtils;
/**
 * 通过 File URL PATH(nio) 及 classpath等方式取的资源信息Resource
 * 用处：加载io资源同一访问接口，屏蔽实现
 * @author zghw
 *
 */
public class ResourceTest {
	static void f(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) throws IOException {
		String fileName="/home/zghw/testdoc/HttpResponse.class";
		f("\n#################### File方式查找资源信息 #############################");
		// 使用文件file方式创建一个Resource源，使用java.io.File实现的
		Resource resource = new FileSystemResource(
				fileName);
		// 使用相对路径访问资源
		// resource=resource.createRelative("/prettify.js");
		// 使用上一级目录访问
		resource = resource.createRelative("../aa.txt");
		getResourceInfo(resource);
		f("\n#################### URL查找资源信息 #############################");
		// 支持使用file访问本地资源
		//resource = new UrlResource("file:/home/zghw/testdoc/HttpResponse.class");
		//使用了URL方式访问网络资源 底层使用了java.net.URL实现
		resource = new UrlResource("http://www.bai.com");
		getResourceInfo(resource);
		f("\n#################### classpath方式查找资源信息 #############################");
		//默认是当前classpath路径下  它使用类加载或class对象产生URL然后在转化为File对象
		resource = new ClassPathResource("/uml/TestInterface.mgi",ResourceTest.class);
		getResourceInfo(resource);
		//也可以使用自定义的类加载器
		resource = new ClassPathResource("/uml/TestInterface.mgi",ResourceTest.class.getClassLoader());
		f("\n##################### Path 方式 java7nio方式查找资源信息  ###############################");
		resource = new PathResource(fileName);
		getResourceInfo(resource);
		f("############################# 字节资源 仅用作字节流资源不会产生File 及 URL对象 #####################");
		byte[] resba=StreamUtils.copyToByteArray(resource.getInputStream());
		resource =new ByteArrayResource(resba);
		f("文件的内容长度：" + resource.contentLength());
		f("输入流对象：" + resource.getInputStream());
		
		f("\n############################# 输出流没有多少用处  #####################");
		//如果你没有其他可用的资源器那么可以使用此它 它和字节ByteArrayResource常一起用，它的isOpen是打开的。
		resource =new InputStreamResource(resource.getInputStream());
		
		f("\n############################# 用于描述文件 没有输入流  #####################");
		resource =new DescriptiveResource("only descript");
	}
	//输出资源信息
	public static void getResourceInfo(Resource resource) throws IOException {
		f("输入流对象：" + resource.getInputStream());
		f("文件流是否已经打开：" + resource.isOpen());
		f("文件是否存在：" + resource.exists());
		f("文件内容是否可读：" + resource.isReadable());
		try {
			f("文件对象：" + resource.getFile());
		} catch (Exception e) {
		}
		f("文件名：" + resource.getFilename());
		f("文件的内容长度：" + resource.contentLength());
		f("文件的最后修改时间：" + resource.lastModified());
		f("文件的URI：" + resource.getURI().toString());
		f("文件的URL：" + (resource.getURL().toString()));
		f("文件的描述：" + resource.getDescription());
		f("使用相对路径查找资源对象：" + resource.createRelative("prettify.js"));
	}

}
