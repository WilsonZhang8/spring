package com.zghw.spring.demo.core.core;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.core.style.StylerUtils;

/**
 * PropertySource分别从Map、Properties、环境变量、资源文件、JDNI和合并多个PropertySource来实现
 * 把资源Resource转换为Properties对象
 * 合并PropertySource 使用MutablePropertySources集合管理PropertySource
 */
public class PropertySourceTest {
	static void f(Object obj) {
		System.out.println(obj);
	}
	public static void main(String[] args) throws IOException {
		/**
		 * PropertySource 抽象类主要是一对name/value值
		 * 它对euqals方法进行了重写 主要比较name属性是否相等 用于在集合中比较PropertySource对象
		 * 其中named()方法返回了一个值名称，value为空的PropertySource对象用于集合比较
		 * 它定了抽象方法public abstract Object getProperty(String name);
		 * 子类来实现得到拥有的value对象
		 * 这是模板方法的实现
		 * JndiPropertySource实现了PropertySource抽象类
		 * jndi的lookfor来查找属性值
		 * EnumerablePropertySource继承PropertySource 它是一个抽象类
		 * 它定义了value是一个可数的对象，比如一个map集合
		 * 它定了抽象方法public abstract String[] getPropertyNames();
		 * 子类来实现得到value对象的集合名称
		 * EnumerablePropertySource子类分三种：
		 * 1.组合PropertySource的CompositePropertySource
		 * 2.常用map类型的MapPropertySource
		 * 3.来自命令参数CommandLinePropertySource
		 * 其中 MapPropertySource 下的子类 有PropertiesPropertySource和SystemEnvironmentPropertySource
		 * PropertiesPropertySource用于来自Properties对象key/value 它有一个子类ResourcePropertySource
		 * ResourcePropertySource可以把Resource资源文件转换为PropertySource资源，它可以使用不同的Resource实现类
		 * 来创建
		 * SystemEnvironmentPropertySource 它处理环境参数转化为PropertySource对象，比如System.getenv()中的参数
		 * 
		 * 以上PropertySource分别从比较PropertySource、Map、Properties、环境变量、资源文件、JDNI和合并多个PropertySource来实现。
		 */
		Properties properties=System.getProperties();//同时
		Map<String, Object> map=(Map)System.getenv();
		f("\n#######################  MapPropertySource Map对象 ##########################################");
		MapPropertySource mps=new MapPropertySource("mapPS",map);
		getPropertiesSourceInfo(mps);
		getEnumerablePropertiesSourceInfo(mps);
		f("\n#########################  PropertiesPropertySource属性对象 ########################################");
		PropertiesPropertySource pps=new PropertiesPropertySource("propertiesPS",properties);
		getPropertiesSourceInfo(pps);
		getEnumerablePropertiesSourceInfo(pps);
		f("\n########################## SystemEnvironmentPropertySource系统环境变量 #######################################");
		SystemEnvironmentPropertySource seps=new SystemEnvironmentPropertySource("envPS",map);
		seps=new SystemEnvironmentPropertySource("envPS",(Map)properties);
		getPropertiesSourceInfo(seps);
		getEnumerablePropertiesSourceInfo(seps);
		f("\n##########################  CommandLinePropertySource命令行 #######################################");
		CommandLinePropertySource<?> cps =new SimpleCommandLinePropertySource("commandPS",args);
		//在命令行中配置好属性 --key=value 这种格式配置 
		getPropertiesSourceInfo(cps);
		getEnumerablePropertiesSourceInfo(cps);
		f("\n########################## ResourcePropertySource资源文件 #######################################");
		//多种方式加载资源
		//ResourcePropertySource(String name, EncodedResource resource)
		//ResourcePropertySource(EncodedResource resource)
		//ResourcePropertySource(String name, Resource resource)
		//ResourcePropertySource(Resource resource)
		//ResourcePropertySource(String name, String location, ClassLoader classLoader)
		//ResourcePropertySource(String location, ClassLoader classLoader)
		//ResourcePropertySource(String name, String location)
		//ResourcePropertySource(String location)
		String location="classpath:test.properties";
		String name="resourcePS";
		ClassLoader cl =PropertySourceTest.class.getClassLoader();
		Resource  resource =new DefaultResourceLoader().getResource(location);
		EncodedResource encodedRes=new EncodedResource(resource);
		//Charset charset=encodedRes.getCharset();
		ResourcePropertySource rps =new ResourcePropertySource(name,resource);
		rps =new ResourcePropertySource(resource);
		f(rps.getName());
		rps =new ResourcePropertySource(name,location,cl);
		rps =new ResourcePropertySource(name,location);
		rps =new ResourcePropertySource(location);
		rps =new ResourcePropertySource(encodedRes);
		rps =new ResourcePropertySource(name,encodedRes);
		
		
		getPropertiesSourceInfo(rps);
		getEnumerablePropertiesSourceInfo(rps);
		f("\n####################### PropertiesLoaderUtils 加Resource到Properties中 ###################################");
		Properties prop=PropertiesLoaderUtils.loadProperties(encodedRes);
		prop=PropertiesLoaderUtils.loadProperties(resource);
		prop =new Properties();
		//把文件填充到该属性中
		PropertiesLoaderUtils.fillProperties(prop, resource);
		PropertiesLoaderUtils.fillProperties(prop, encodedRes);
		for(Entry<Object, Object> obj:prop.entrySet()){
			f(obj.getKey() +" :" +obj.getValue());
		}
		f("\n############################### CompositePropertySource 合并多个资源属性##################################");
		CompositePropertySource csp = new CompositePropertySource("compositePS");
		csp.addPropertySource(mps);
		csp.addPropertySource(pps);
		csp.addFirstPropertySource(rps);//加载到最前面
		csp.addPropertySource(seps);
		csp.addPropertySource(cps);
		getPropertiesSourceInfo(csp);
		getEnumerablePropertiesSourceInfo(csp);
		
		f("\n######################### MutablePropertySources 管理多个可变的PropertySources #################################");
		//使用了链表方式
		MutablePropertySources propertySources= new MutablePropertySources();
		///放入一个MapPropertySource mps
		propertySources.addFirst(mps);
		//在mps前加入一个ResourcePropertySource rps
		propertySources.addBefore("mapPS", rps);
		//在rps后放入一个CommandLinePropertySource<?> cps 
		propertySources.addAfter(name, cps);
		//在尾部加入PropertiesPropertySource pps
		propertySources.addLast(pps);
		for(PropertySource propertySource :propertySources){
			f("-------------------"+propertySource.getName()+"-------------------------");
			getPropertiesSourceInfo(propertySource);
		}
		f("PropertySource的个数："+propertySources.size());
		//移除mapPS
		propertySources.remove("mapPS");
		//使用resourcePS替换propertiesPS
		propertySources.replace("propertiesPS", rps);
		f("移除后PropertySource的个数："+propertySources.size());
		for(PropertySource propertySource :propertySources){
			f("-------------------"+propertySource.getName()+"-------------------------");
			getPropertiesSourceInfo(propertySource);
		}
		f("propertiesPS优先级："+propertySources.precedenceOf(pps));
		f("resourcePS优先级："+propertySources.precedenceOf(rps));
		f("commandPS优先级："+propertySources.precedenceOf(cps));
		f("mapPS优先级不存在返回-1："+propertySources.precedenceOf(mps));
		f("\n##########################################################");
	}
	//PropertySource中定义的方法
	private static void getPropertiesSourceInfo(PropertySource<?> mps) {
		f("PropertySource的名称："+mps.getName());
		f("得到PropertySource包含的对象："+mps.getSource());
		f("是否包含给定的name对应的值："+mps.containsProperty("user.dir"));
		f("得到给定的name对应的值："+mps.getProperty("PATH"));
		f("得到给定的name对应的PropertySource(返回的PropertySource的value值为空)："+mps.named(mps.getName()));
	}
	//EnumerablePropertySource中定义的方法
	private static void getEnumerablePropertiesSourceInfo(EnumerablePropertySource mps) {
		String[] names=mps.getPropertyNames();
		f("得到集合所有名称数组："+StylerUtils.style(names));
		for(String name : names){
			System.out.print(name + "="+mps.getProperty(name)+" , ");
		}
		f("");
	}
}
