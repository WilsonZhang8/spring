package com.zghw.spring.demo.core.core;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import com.zghw.spring.demo.core.core.source.TypeClass;
import com.zghw.spring.demo.core.core.source.TypeClassImple;
/**
 * 核心功能 类型转换 有了此功能妈妈再也不怕我转不开了
 * 
 * 我是变形金刚
 * 
 * @author zghw
 *
 */
public class ConvertTest {
	static void f(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) {
		ConfigurableConversionService ccs =new DefaultConversionService();
		Map<String, String> map =System.getenv();
		Properties prop=System.getProperties();
		ArrayList str=ccs.convert(map, ArrayList.class);
		String pop=ccs.convert(prop, String.class);
		prop=ccs.convert(pop, Properties.class);
		f(str);
		f(pop);
		f(prop);
		
		// f(TypeClassImple.class.isAssignableFrom(AttributeAccessor.class));
		// 来自超类或类本身 判断给定的对象是否可以转化为此对象 及向上转型
		f(TypeClassImple.class.isAssignableFrom(TypeClass.class));
		f(TypeClass.class.isAssignableFrom(TypeClassImple.class));
		f(TypeClassImple.class.isAssignableFrom(TypeClassImple.class));
		// 转换器 注册一组转换器对象
		// 每个转换器有源类型和目标类型者可以认为是一个对象，这个源类型和目标对象确定一个转换器类型
		// 对于简单的类型转换可以绑定到一个泛型接口上，使用泛型参数来表示转换的源类型和目标类型
		// spring使用了Converter<S, T>泛型接口 S代表源类型，T代表了目标类型：
		// 例如：Converter<String, Character> 代表把String类型转换为Character类型
		// 1.主要转换器如下： 主要是基本类型转换 或Object和string转换
		// Number<==>Number
		// Number<==>String
		// Character<==>String
		// Number<==>Character
		// Boolean<==>String
		// Eunm<==>String
		// Properties<==>String
		// 使用了工厂来创建转换器ConverterFactory
		// 但Converter<S, T>不能集合类型或对象与对象进行转换，
		// spring定义了一个通用的转换接口GenericConverter
		// GenericConverter把源类型和目标类型作为一个对象ConvertiblePair，让接口的类给定源类型和目标类型及ConvertiblePair对象
		// 2.主要转换器如下： 主要是聚合类型之间的转换 、对象与对象之间的转换
		// Array<==>Array
		// Array<==>Collection
		// Collection<==>Collection
		// Map<==>Map
		// Array<==>String 它们会循环集合 然后对集合的对象使用其他转换器进行转换
		// Array<==>Object
		// Collection<===>String
		// Collection<==>Object
		// Object<==>Object
		// 当然你也可以扩展自己的转换器
		// 使用源类型 和 目标类型，允许Converter
		// 、ConverterFactory和GenericConverter有条件的去执行一些事情
		// 常用于有选择地匹配自定义转换逻辑基于字段或类级别的存在特点,比如注释或方法。例如,当从一个字符串字段转换成日期字段,实现可能会返回true,如果目标字段也被与@DateTimeFormat注释。
		// 基于字段或类是源类型或是目标类型，基本这个特点，我们可以选择型的匹配。ConditionalConverter
		// 接口的match方法可以用来做这些事情。

		// 由Converter、ConverterFactory、GenericConverter和
		// ConditionalConverter产生的转换器
		// 我们可以做各种类型转换器，但是我们开发了一个自动转换系统，给出源类型和目标类型，自动的转换类型
		// 由这么多转换器，我们怎么自动的使用这些转换器
		// 首先我们定义一个转换器集合，选择自己需要的转换器放入到转换器集合，当我们放入后发现转换器接口不相同，
		// 集合中可能包含Converter接口生成的转换器，或ConditionalConverter或GenericConverter转换器，
		// 这里就使用了适配器模式，把所有的接口都适配成相同的GenericConverter接口
		// Converter适配成GenericConverter接口
		// 把Converter中的类型转换为Converter<Object,Object> 使用此可以转换一个ConvertiblePair对象
		// 使用通一的ConvertiblePair对象来管理源类型和目标类型，同时
		// 适配的convert()方法由Converter来使用

		// 是现在集合中的元素都像一样的GenericConverter转换器，但我们如何使用源类型和目标类型来定位转换器呢？
		// 使用字典 key就是我们统一过的ConvertiblePair对象 value就是我们加入的转换器集合(一般有一个)
		// 我们在注册转换器到集合中时对适配后的转换器，使用ConvertiblePair作为键，如果由多个转换器则加入一个集合中
		// 这样我们就形成了一个字典 我们通过ConvertiblePair对象就能查询到我们的需要的转换器集合(一般有一个)
		// ConvertiblePair对象包含了Class<?> sourceType, Class<?> targetType两个值
		// 把用户给定的源类型和目标类型组合成ConvertiblePair对象
		// 对源类型及父类接口循环 与目标类型 、父类及接口形成每个ConvertiblePair对象，最大化防治错过向上转换
		// 查找注册表中的ConvertiblePair对象的值
		// 如果存在，则使用该转换器。
		//GenericConverter
		
		//TypeDescriptor 类型转换源目标类型或目标类型包含的其他信息容器，例如字段类型可能包含注解信息，
		//但我们在开发转换器时，在match比较时我们可能会使用到注解信息，TypeDescriptor携带了这些信息，而不是简单的类型
		//它里面使用ResolvableType来实现的
		//下面我们来看一下spring如何来做转换服务的
		//ConversionService接口 主要功能类型转换
		//1.判断两个类型能否转换
		//2.把对象转换为其他对象
		//当然这里的源类型和目标类型都被封装到了TypeDescriptor中统一访问
		
		//ConverterRegistry接口为ConversionService注册和管理转换器，主要提供了如下功能
		//添加各种转换器，
		//移除转换器
		//ConfigurableConversionService接口继承ConverterRegistry和ConversionService
		//空实现 用来。。。看不懂spring说的好处 
		//GenericConversionService
		//实现了大部分功能 它通过适配不同类型转换器，使用ConvertiblePair作为中间访问值key，
		//快速访问到转换器然后进行转换，具体实现看上面的解释
		//它把需要放入的转换器交给了子类或使用者
		//DefaultConversionService 默认的实现类型转换服务
		//它注册转换器到转换服务中
	}

}
