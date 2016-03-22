package com.zghw.spring.demo.demo.factory;

public class XMLBeanReaderTest {

	public static void main(String[] args) {
		/**
		 * DocumentLoader接口 xml文档加载器 策略模式加载xml文档对象
		 *  
		 * 它加在xml为一个文档的方法需要三个参数 
		 * 第一个参数 资源输入流 这个客户端给定 
		 * 第二个参数 EntityResolver 实体解析器 
		 * 第三个参数 errorHandler 错误处理器 
		 * 第四个参数 验证模式 是验证DTD还是XSD 
		 * 第五个参数 XML解析器是否应该被XML名称空间感知的
		 * 
		 * 第一个参数通过包装客户端给的url成为一个inputstream
		 * 第二个参数是替换网络的url systemId 为本地的systemId
		 * spring实现EntityResolver接口有以下类：
		 * BeansDtdResolver ：它为了转化带有.dtd的systemId,从访问网络的url转换为本地的url的dtd文件
		 * PluggableSchemaResolver:它为了转化带有.xsd的systemId,从访问网络的url转换为本地的url的.xsd文件
		 * 它把META-INF/spring.schemas循环放入map中 然后比较你定义的xsd是否在map中存在，如果存在则取出对应的url
		 * 把它转换为输入流放入Inputsource中
		 * 1.DelegatingEntityResolver 使用委托的方式，根据xml文件的systemId的后缀.dtd还是.xsd判断用
		 * BeansDtdResolver 还是PluggableSchemaResolver来处理。
		 * ResourceEntityResolver继承了DelegatingEntityResolver 主要解决好像是空systemId路径是默认当前系统的路径
		 * 第三个参数 ：使用默认的SimpleSaxErrorHandler 就是抛异常
		 * 第四个参数 ：验证是DTD还是XSD，默认自动验证，使用了XmlValidationModeDetector来自动验证，判断是否包含Document。
		 * 第五个参数 ：XML解析器是否应该被XML名称空间感知的。默认设置是“假”。
		 *  * 就是前一个是publicID，后一个是SystemId，是一一对应的
		 * http://www.springframework.org/schema/beans publicID
		 * http://www.springframework.org/schema/beans/spring-beans-2.5.xsd SystemId
		 * 
		 *  DefaultDocumentLoader 默认实现 DocumentLoader它使用了标准的JAXP-configured来解析XML文档
		 *   它设置了自动感知.xsd的文档
		 * 
		 */
	}

}
