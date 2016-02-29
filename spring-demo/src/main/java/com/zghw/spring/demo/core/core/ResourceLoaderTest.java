package com.zghw.spring.demo.core.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * 
 * 资源加载器 简单的加载方式 默认加载(包含了classpath url)、文件加载、classpath加载 
 * 一般资源位置符合如下规则
 *  1.Must support fully qualified URLs, e.g. "file:C:/test.dat".
 *  2.Must support  classpath pseudo-URLs, e.g. "classpath:test.dat". 
 *  3.Should support relative file paths, e.g. "WEB-INF/test.dat". (This will be implementation-specific,
 * typically provided by an ApplicationContext implementation.)
 * 
 * @author zghw
 *
 */
public class ResourceLoaderTest {
	static void f(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) throws IOException {
		/**
		 * DefaultResourceLoader资源加载器 可以通过设置class类加载器 如果没有设置 则会先从当前线程取的类加载器
		 * 如果没有则获取默认加载器 1.如果资源位置字符串以“/”开头，就使用classpath容器(如：
		 * 是典型的ApplicationContext容器或Tomcat容器)资源查找到Resource
		 * 2.如果资源位置字符串以“classpath:”开头，则使用classpath方式加载
		 * 3.如果以上都不是，则使用URL方式加载资源Resource 4.如果URL方式无法加载，则使用classpath容器加载
		 * 还加载不了就抛异常了
		 */
		ResourceLoader rl = new DefaultResourceLoader();
		// 必须加上 file: 才能读文件类型
		Resource resource = rl
				.getResource("file:/home/zghw/testdoc/HttpResponse.class");
		// URL方式
		resource = rl.getResource("http://www.bai.com");
		// classpath容器方式读取
		resource = rl.getResource("/uml/TestInterface.mgi");
		resource = rl.getResource("/uml/TestInterface.mgi");
		// 可以加上这个前缀标志 classpath： 标准的classpath加载
		resource = rl.getResource("classpath:/uml/TestInterface.mgi");

		// ResourceTest.getResourceInfo(resource);
		/**
		 * ClassRelativeResourceLoader继承了DefaultResourceLoader
		 * 它通过构造函数参数Class<?>取的类加载 可以自动得到classpath location可以为相对于classpath下的路径
		 */
		rl = new ClassRelativeResourceLoader(ResourceLoaderTest.class);
		// 必须加上 file: 才能读文件类型
		resource = rl.getResource("file:/home/zghw/testdoc/HttpResponse.class");
		// URL方式
		resource = rl.getResource("http://www.bai.com");
		// classpath容器方式读取
		resource = rl.getResource("/uml/TestInterface.mgi");
		resource = rl.getResource("/uml/TestInterface.mgi");
		// 可以加上这个前缀标志 classpath： 标准的classpath加载
		resource = rl.getResource("classpath:/uml/TestInterface.mgi");
		// ResourceTest.getResourceInfo(resource);
		/**
		 * FileSystemResourceLoader继承了DefaultResourceLoader 它使用的不是classpath加载器
		 * 而是文件系统容器，使用它可以加载servlet容器 加入的location会当成系统文件根路径
		 * 如果以斜杠开头则会解释为相对于当前VM工作目录 
		 * 单斜杠表示了当前VM工作目录 即
		 * System.getProperty("user.dir") 
		 * 所以加载时要注意vm工作目录下的文件是否存在或者使用//代替
		 */
		rl = new FileSystemResourceLoader();
		// 必须加上 file: 才能读文件类型 在容器中加载 所以相当于不认识外部了
		f("得到当前工作目录：" + System.getProperty("user.dir"));
		resource = rl.getResource("file:/home/zghw/testdoc/HttpResponse.class");
		// 双斜杠用来表示操作系统绝对位置
		resource = rl.getResource("//home/zghw/testdoc/HttpResponse.class");
		resource = rl.getResource("springDemo.txt");
		// resource =resource.createRelative("/HttpResponse.class");
		ResourceTest.getResourceInfo(resource);
		// URL方式
		resource = rl.getResource("http://www.bai.com");

		// classpath容器方式读取
		// 加载不到对象 resource = rl.getResource("/uml/TestInterface.mgi");

		// 加载不到对象 resource = rl.getResource("/uml/TestInterface.mgi");

		// 可以加上这个前缀标志 classpath： 标准的classpath加载
		resource = rl.getResource("classpath:/uml/TestInterface.mgi");
		f("当前classpath：" + ResourceLoaderTest.class.getResource(""));
		f("当前VM工作路径：" + System.getProperty("user.dir"));
		f("前面有/的可以加载 到文件流中："
				+ new FileInputStream(new File(
						"/home/zghw/testdoc/HttpResponse.class")));
		f("前面没有/的不可以加载到文件流中 因为它查找的是 vm的 user.dir下的 该路径，该路径下没有这个文件\n"
				+ new FileInputStream(new File(
						"home/zghw/testdoc/HttpResponse.class")));
	}

}
