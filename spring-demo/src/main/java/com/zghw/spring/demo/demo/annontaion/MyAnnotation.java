package com.zghw.spring.demo.demo.annontaion;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Role;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Lazy(false)
@Primary
@DependsOn("facde")
@Role(0)
@Description("set annotation")
@Scope(scopeName="sc",proxyMode=ScopedProxyMode.DEFAULT)
@Component
@ComponentScan
@Import(MyAnnotation.class)
@ImportResource(locations="application.xml",reader=XmlBeanDefinitionReader.class)
/**
 * 多个路径，分成字段，使用环境资源解析value路径，转换为resource，添加到环境中。
 * @author zghw
 *
 */
@PropertySource(name="aa",value="a.properties,b.properties",ignoreResourceNotFound=true)
public class MyAnnotation {

}
