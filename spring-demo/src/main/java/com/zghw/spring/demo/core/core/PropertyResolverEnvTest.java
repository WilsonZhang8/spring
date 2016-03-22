package com.zghw.spring.demo.core.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.env.*;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePropertySource;

import com.zghw.spring.demo.core.util.User;

/**
 * 解析任何底层来源的属性Property
 * 
 * @author zghw
 *
 */
public class PropertyResolverEnvTest {
	static void f(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) throws IOException {

		// ######### PropertyResolver ################

		// ##PropertyResolver接口定义以下方法主要为取值、value值转换类型、value值占位符替换
		// 判断属性值是否存在
		// 得到key对应的属性值转换为字符串，如果不能转换为字符串，则返回null
		// 得到key对应的属性值转换为字符串，如果不能转换为字符串，则设置默认字符串
		// 得到key对应的属性值转换为给定的对象，如果不能转换为给定的对象，则返回null
		// 得到key对应的属性值转换为给定的对象，如果不能转换为给定的对象，则设置默认对象
		// 得到key对应的属性值转换为给定的Class类型，如果不能转换为给定的对象，则返回null
		// 设置必须存在值对应的名称
		// 得到key对应要求存在的属性值，如果不存在则抛异常
		// 对返回的value值文本进行占位符替换
		// 对返回要求存在的value值文本进行占位符替换

		// ########## ConfigurablePropertyResolver ################

		// ##配置接口ConfigurablePropertyResolver接口继承了PropertyResolver接口
		// ##提供了访问和定制ConversionService设施将属性值从一种类型转换为另一种形式时使用。及占位符的设置。
		// 定义了以下接口方法
		// 设置类型转换服务 ConfigurableConversionService
		// 取得类型转换服务
		// 设置占位符前缀
		// 设置占位符后缀
		// 设置属性值分隔符
		// 设置是否解析占位符内部的占位符
		// 验证设置的必须存在的属性值是否存在

		// ########### AbstractPropertyResolver ################
		// ##抽象类AbstractPropertyResolver 实现ConfigurablePropertyResolver接口

		// 设置类型转换服务 默认的DefaultConversionService
		// 取得类型转换服务 返回DefaultConversionService
		// 设置占位符前缀 默认的 ${
		// 设置占位符后缀 默认的 }
		// 设置属性值分隔符 默认的 :
		// 设置是否解析占位符内部的占位符 false
		// 设置一些必须存在的属性名称 使用了final标志Set集合存储
		// 验证设置的必须存在的属性值是否存在 检查set集合中的key在属性对象中是否有不为null的

		// ##抽象类AbstractPropertyResolver使用了模板方法，实现了PropertyResolver接口中定义部分方法
		// ##它实现的下列方法都调用了子类具体实现的方法，
		// 得到key对应的属性值转换为字符串，如果不能转换为字符串，则设置默认字符串
		// 得到key对应的属性值转换为给定的对象，如果不能转换为给定的对象，则设置默认对象
		// 设置必须存在值对应的名称
		// 得到key对应要求存在的属性值，如果不存在则抛异常
		// 对返回的value值文本进行占位符替换 首先通过配置占位符查找到占位符里对应的key，使用key查找value并替换
		// 对返回要求存在的value值文本进行占位符替换
		// ########### PropertySourcesPropertyResolver ################

		// ##PropertySourcesPropertyResolver实现了抽象类AbstractPropertyResolver
		// 使用接受一个PropertySources对象来作为属性源，PropertySources对象继承了Iterable接口，是迭代器模式
		// 它持有PropertySource对象集合，循环集合对象进行查找、类型转换等，
		// 实现如下如下方式：
		// 判断属性值是否存在
		// 得到key对应的属性值转换为字符串，如果不能转换为字符串，则返回null
		// 得到key对应的属性值转换为给定的对象，如果不能转换为给定的对象，则返回null
		// 得到key对应的属性值转换为给定的Class类型，如果不能转换为给定的对象，则返回null

		// ######################总结#######################
		// 使用了模板方法。
		// 结构上实现了属性配置和取值转换分离，低耦合，高内聚

		Properties properties = System.getProperties();// 同时
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		user.setAge(11);
		user.setStr("sdfsd");
		User user1 = new User();
		user1.setAge(11);
		user1.setStr("sdfsd");
		map.put("uu1", user);
		map.put("uu2", user1);
		map.put("uu3", null);
		//MapPropertySource的 把map对象放入进入当成PropertySource
		MapPropertySource mps = new MapPropertySource("mapPS", map);
		//PropertiesPropertySource 把Properties放入当成PropertySource
		PropertiesPropertySource pps = new PropertiesPropertySource(
				"propertiesPS", properties);
		String location = "classpath:test.properties";
		String name = "resourcePS";
		Resource resource = new DefaultResourceLoader().getResource(location);
		//把一个Resource资源对象当成一个PropertySource
		ResourcePropertySource rps = new ResourcePropertySource(name, resource);
		//MutablePropertySources 用来管理包含多个PropertySources
		MutablePropertySources propertySources = new MutablePropertySources();
		// /放入一个MapPropertySource mps
		propertySources.addFirst(mps);
		// 在mps前加入一个ResourcePropertySource rps
		propertySources.addBefore("mapPS", rps);
		// 在尾部加入PropertiesPropertySource pps
		propertySources.addLast(pps);
		//创建一个P解析器
		ConfigurablePropertyResolver crp = new PropertySourcesPropertyResolver(
				propertySources);
		for (PropertySource ps : propertySources) {
			f(ps.getSource());
		}
		// getPropertyResolverInfo(crp);
		// 对返回要求存在的value值文本进行占位符替换
		// 属性配置信息
		// f(crp.getConversionService());
		// crp.setConversionService(conversionService);
		crp.setIgnoreUnresolvableNestedPlaceholders(false);
		crp.setPlaceholderPrefix("[");
		crp.setPlaceholderSuffix("]");
		crp.setValueSeparator(",");
		f("设置必须存在值对应的名称:");
		crp.setRequiredProperties("alias", "name");
		crp.validateRequiredProperties();
		getPropertyResolverInfo(crp);
	}

	// 属性解析信息
	private static void getPropertyResolverInfo(ConfigurablePropertyResolver crp) {
		f("判断non是否存在:" + crp.containsProperty("non"));
		f("得到key对应的属性值转换为字符串" + crp.getProperty("user.dir"));
		f("得到key对应的属性值转换为字符串" + crp.getProperty("uu3", "defaultnon")
				+ " 转换后不会改变值：" + crp.getProperty("uu3"));
		f("得到key对应的属性值转换为给定的对象" + crp.getProperty("uu1", User.class));
		f("得到key对应的属性值转换为给定的对象"
				+ ((User) crp.getProperty("uu1", Object.class, new User()))
						.getAge());
		f("得到key对应的属性值转换为给定的Class类型"
				+ crp.getPropertyAsClass("uu2", User.class));

		f("得到key对应要求存在的属性值，如果不存在则抛异常");
		crp.validateRequiredProperties();
		f("对返回的value值文本进行占位符替换"
				+ crp.resolvePlaceholders("zgsdsfdfsd ${user.dir} sdfsdfsd sd [user.dir]"));
		f("对返回要求存在的value值文本进行占位符替换"
				+ crp.resolveRequiredPlaceholders("sdfsd1223= ${alias${alias}} =dsfsd sdsd2323 [alias[alias]]"));
	}

}
