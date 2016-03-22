package com.zghw.spring.demo.demo.factory;

import org.springframework.beans.BeanMetadataAttribute;
import org.springframework.beans.BeanMetadataAttributeAccessor;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.core.style.StylerUtils;

/**
 * 定义bean对象的属性数据结构及属性管理、拷贝等
 * @author zghw
 *
 */
public class PropertyValueTest {
	static void f(Object obj){
		System.out.println(obj);
	}
	public static void main(String[] args) {
		//AttributeAccessor定义了访问属性访问器
		//AttributeAccessorSupport使用hash表来管理属性访问器
		//BeanMetadataElement接口携带了包含bean元数据元素的source对象
		//BeanMetadataAttribute类持有bean对象中一个key/value属性，跟踪source对象
		//BeanMetadataAttributeAccessor继承了AttributeAccessorSupport类，实现了BeanMetadataElement接口，
		//它不仅拥有属性访问器，它把属性值设置为BeanMetadataAttribute类型，把key设置为BeanMetadataAttribute的key，它还可以跟踪source对象
		//PropertyValue继承自BeanMetadataAttributeAccessor
		//保存bean对象中的信息及值，使用一个对象，而不是让所有属性保存在Map对象中，它允许了更大的灵活性，并且能都处理索引属性来优化
		//它可以是简单的包含属性，不需要进行解析后放入
		
		//PropertyValues接口持有一个或多个PropertyValue对象,通常更新指定的bean。
		//MutablePropertyValues实现了PropertyValues接口。
		//把所有PropertyValue放入了List集合中，可以当作集合来管理PropertyValue对象，
		//它提供了构造器可以对来自List Map PropertyValues等持有PropertyValue对象的集合中进行深度拷贝。
		MyBean mb=new MyBean();
		mb.setName("zghw");
		mb.setAge(27);
		//设置属性值
		BeanMetadataAttribute bmeName=new BeanMetadataAttribute("name",mb.getName());
		BeanMetadataAttribute bmeAge=new BeanMetadataAttribute("age",mb.getAge());
		//设置bean对象
		bmeName.setSource(mb);
		getBeanAttributeInfo(bmeName);
		
		BeanMetadataAttributeAccessor bmaa=new BeanMetadataAttributeAccessor();
		f(StylerUtils.style(bmaa.attributeNames()));
		bmaa.setSource(mb);
		//设置属性值
		bmaa.setAttribute("name",mb.getName());
		f(StylerUtils.style(bmaa.attributeNames()));
		//添加一个属性对象
		bmaa.addMetadataAttribute(bmeAge);
		//取的一个属性对象
		f(bmaa.getMetadataAttribute("age"));
		//取的所有属性名称
		f(StylerUtils.style(bmaa.attributeNames()));
		//删除一个属性
		bmaa.removeAttribute("age");
		f(StylerUtils.style(bmaa.attributeNames()));
		//判断一个属性对象是否存在
		f("age属性是否存在:"+bmaa.hasAttribute("age"));
		PropertyValue pv1=new PropertyValue("name",mb.getName());
		//pv2对pv1进行了深度拷贝
		PropertyValue pv2=new PropertyValue(pv1);
		//拷贝PV1并替换值为新的对象
		PropertyValue pv3=new PropertyValue(pv1,"xxxx");
		f("PV1值："+pv1.getValue());
		f("pv3值："+pv3.getValue());
		f("是否以及设置转换值："+pv1.isConverted());
		pv1.setConvertedValue("zzzz");
		f("是否以及设置转换值："+pv1.isConverted());
		f("是否以及设置转换值："+pv2.isConverted());
		f("得到转换值："+pv1.getConvertedValue());
		f("得到值："+pv1.getValue());
		
		MutablePropertyValues mpv =new MutablePropertyValues();
		MutablePropertyValues mpvOld =new MutablePropertyValues();
		mpv=mpv.add("test1", "test1Value");
		f(mpv.size());
		mpv=mpv.addPropertyValue(pv1);
		mpvOld =new MutablePropertyValues(mpv);
		f(mpv.size());
		//添加一个PropertyValue
		mpv.addPropertyValue("test2", "test2Value");
		f(mpv.size());
		f("改变过的属性对象 比较当前集合中的属性值和给定集合中的属性不同的属性集合："+mpv.changesSince(mpvOld));
		//添加一个Map对象
		mpv=mpv.addPropertyValues(System.getenv());
		f(mpv.size());
		MutablePropertyValues mpv1 =new MutablePropertyValues();
		mpv1.add("mvp1", "mmmmvvvp");
		//添加一个PropertyValues集合MutablePropertyValues
		mpv.addPropertyValues(mpv1);
		f(mpv.size());
		f("通过属性名得到值："+mpv.get("test2"));
		f("通过属性名得到PropertyValue对象："+mpv.getPropertyValue("test2").getSource());
		f("得到属性值数组："+StylerUtils.style(mpv.getPropertyValues()));
		//设置当前属性被处理过的 比如使用了setter方法
		mpv.registerProcessedProperty("name");
		//取消被处理过标志
		mpv.clearProcessedProperty("name");
		f("是否包含该属性："+mpv.contains("test2"));
		f("该属性是否被转换过："+mpv.isConverted());
		mpv.setConverted();
		f("该属性是否被转换过："+mpv.isConverted());
		mpv.removePropertyValue("test2");
		mpv.removePropertyValue("test1");
		f("是否包含该属性："+mpv.contains("test2"));
	}
	private static void getBeanAttributeInfo(BeanMetadataAttribute bme) {
		f("对象"+bme.getSource());
		f("属性名："+bme.getName());
		f("属性值："+bme.getValue());
	}

}
