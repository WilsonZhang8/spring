package com.zghw.spring.demo.demo.factory;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.PropertyEditorRegistrySupport;
import org.springframework.beans.propertyeditors.ByteArrayPropertyEditor;
import org.springframework.beans.propertyeditors.ClassEditor;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
/**
 * 属性编辑器注册服务
 * @author zghw
 *
 */
public class PropertyEditorTest {
	static void f(Object obj) {
		System.out.println(obj);
	}

	static void fl(Object obj) {
		System.out.print(obj);
	}

	public static void main(String[] args) throws IOException {

		/**
		 * PropertyEditorRegistry接口定义了PropertyEditor编辑器的注册服务
		 * 主要功能为注册定制的PropertyEditor编辑器和查找PropertyEditor编辑器
		 * 以PropertyEditor的实现类Class为key，编辑器为value放入字典中。
		 * 对于集合和数组中对应的元素则以该元素的属性路径及propertyPath为key，
		 * value为（该集合实现了Class为key，该元素的编辑器为value)放入字典中, propertyPath=items[n]
		 * 对于集合每个元素都有单一的注册编辑器，不注册集合或数组相同元素为同一编辑器
		 * 如果要注册items[n].quantity中的所有元素则使用items.quantity
		 * 
		 * 
		 * PropertyEditorRegistrySupport类实现了PropertyEditorRegistry接口
		 * 它提供了默认编辑器注册和定制编辑器 
		 * 它使用了5种注册表 
		 * 1.默认注册表
		 * 包含了大量spring定义的属性编辑器，有基本包装类型，集合及常用的类属性编辑器 
		 * 2.覆盖默认的注册表
		 * 如果默认注册表种的编辑器和自定义的编辑器有重复则优先使用自定义的 
		 * 3.定制注册表
		 * key为编辑器实现PropertyEditor的Class类，value为实现对象 
		 * 4.定制propertyPath注册表
		 * propertyPath是集合对象中元素的属性路径作为key 比如：item[5]
		 * (key为编辑器实现PropertyEditor的Class类，value为实现对象)
		 * (使用CustomEditorHolder对象持有)作为propertyPath的value. 
		 * 5.用于缓存定制注册表
		 * 加快注册表访问速度，专门为定制注册表做了一个缓存。
		 * 
		 * 它给子类提供了注册默认编辑器的方法 
		 * 它提供了转换类型服务 
		 * 它提供了用户可以激活默认编辑器的功能，默认没有激活 
		 * 它通过给定的Class编辑器和propertyPath路径来注册到不同的注册表中(放入map中)
		 * 它通过给定的Class和propertyPath查找注册表中的编辑器返回
		 * 
		 */
		List list = new ArrayList();
		list.add(1111);
		list.add("sdfsdfsd");
		list.add("2015-09-01 13:25:33");
		PropertyEditorRegistrySupport per = new PropertyEditorRegistrySupport();
		// 先注册一个集合编辑器
		per.registerCustomEditor(Collection.class, new CustomCollectionEditor(
				List.class));
		// 注册一个日期编辑器，作为编辑集合中第3个元素"2015-09-01 13:25:33"为Date对象
		per.registerCustomEditor(DateEditor.class, "list[2]",
				new PropertyEditorTest().new DateEditor());
		// 查找到集合编辑器
		CustomCollectionEditor cce = (CustomCollectionEditor) per
				.findCustomEditor(Collection.class, null);
		// 查找到日期编辑器
		DateEditor dee = (DateEditor) per.findCustomEditor(DateEditor.class,
				"list[2]");
		// 设置集合值
		cce.setValue(list);
		List ls = (List) cce.getValue();

		// 设置时间编辑器的值为集合中第三个元素的值
		dee.setAsText((String) ls.get(2));
		// 取的编辑后的值
		Date date = (Date) dee.getValue();
		f("集合中第三个元素转换后的值为Date类型：" + date);
		/**
		 * 以上的例子只是使用，spring封装了自动的转换的功能，结合了TypeCoverter
		 * TypeConverter接口定义了类型转换的方法，通常的（但不需要的）子类也要实现PropertyEditorRegistry
		 * 转换通常使用PropertyEditor的setAsText或者使用spring的ConversionService转换服务
		 * 
		 */

		/**
		 * PropertyEditor属性编辑器是通过把string字符串当作某个对象
		 * 例如："2015-09-01"是一个字符串，通过PropertyEditor定义一个编辑器实现String类型转为Date类型
		 */
		DateEditor de = new PropertyEditorTest().new DateEditor();
		de.setAsText("2015-09-01 13:25:33");
		date = (Date) de.getValue();
		System.out.println(date.toGMTString());

		// 使用spring的编辑器

		// 把字符串当成byte[]
		Resource resource = new UrlResource("http://www.baidu.com");
		ByteArrayPropertyEditor bape = new ByteArrayPropertyEditor();
		bape.setAsText("zhang散");
		f(bape.getAsText());
		byte[] bt1 = (byte[]) bape.getValue();
		for (byte b : bt1) {
			fl(b + " , ");
		}
		// 把string当作Class<?>
		ClassEditor ce = new ClassEditor();
		ce.setAsText("com.zghw.spring.demo.beans.test.PropertyEditorTest");
		Class<?> pe = (Class<?>) ce.getValue();
		f(pe.getName());

	}

	// 首先继承PropertyEditorSupport类
	class DateEditor extends PropertyEditorSupport {
		// 把字符串转换为对象
		@Override
		public void setAsText(String text) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdf.parse(text);
				// 设置对象
				setValue(date);
			} catch (ParseException e) {
				System.out.println("sd");
			}
		}
	}

}
