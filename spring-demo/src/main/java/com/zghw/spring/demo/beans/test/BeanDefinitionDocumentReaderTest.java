package com.zghw.spring.demo.beans.test;

import org.springframework.beans.factory.parsing.BeanEntry;
import org.springframework.beans.factory.parsing.ConstructorArgumentEntry;
import org.springframework.beans.factory.parsing.FailFastProblemReporter;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.beans.factory.parsing.ParseState;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.beans.factory.parsing.ProblemReporter;
import org.springframework.beans.factory.parsing.PropertyEntry;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;


public class BeanDefinitionDocumentReaderTest {

	public static void main(String[] args) {
		/**
		 * 
		 *################## BeanDefinitionDocumentReader  ####
		 * BeanDefinitionDocumentReader接口从xmldocument对象中读取bean对象
		 * 读取的bean对象保存在了beanDefinition注册表中
		 * 接口方法：
		 * void registerBeanDefinitions(Document doc, XmlReaderContext readerContext)
		 * 读取Document文档注册，保存在了beanDefinition注册表中
		 * DefaultBeanDefinitionDocumentReader实现了BeanDefinitionDocumentReader
		 * 1.设置readerContext
			2.开始document文档解析，查找根元素
			3.首先使用XmlReaderContext创建BeanDefinitionParserDelegate委托对象,初始化委托对象，读取root属性，填充默认的DocumentDefaultsDefinition
				1.default-lazy-init	默认为false
				2.default-merge	默认为false
				3.default-autowire 默认为no
				4.设置default-dependency-check
				5.存在就设置default-autowire-candidates
				6.存在就设置default-init-method
				7.存在就设置default-destroy-method
			
			4.查询
			   如果root不是默认的命名空间：
				则通过该root元素查找到namespaceURI,通过namespaceURI查找到NamespaceHandler，然后调用parse解析为BeanDefinition对象返回。
			
			如果是默认的命名空间 
				8.存在就取profile设置环境
			循环root下的子元素
			  如果子元素不是默认的命名空间则像上面一样处理
			  如果子元素是默认命名空间
			 <beans>下的节点：	
				1.import 
				2.alias
				3.bean
				4.beans  重复之前的步骤
			
			1.import节点
			resource属性,解析location有占位符时，e.g. "${user.dir}"
			如果resource的资源定位正确，则reader.loadBeanDefinitions加载
			2.alias节点
			name属性：
			alias属性：
			注册器注册
			
			3.bean元素
			属性：
			1.id
			2.name
			3.class
			4.parent
			开始创建AbstractBeanDefinition，仅仅设置了父类名称和class类型
			5.scope
			6.abstract
			7.lazy-init 默认就是用root<beans>下的
			8.autowire 0不自动装配 1 byname 2 bytype 3.by constructor
			9.dependency-check
			10.有就设置depends-on
			11.autowire-candidate
			12.存在就设置primary
			13.有就设置init-method没有就看root是否有
			14.destroy-method有就设置看root是否有
			15.factory-method工厂方法
			16.factory-bean
			
			bean子节点元素
			1.description
			2.meta 属性：1.key 2.value 放入了BeanMetadataAttributeAccessor中 0-*
			3.lookup-method 属性：1.name 2.bean 创建LookupOverride对象	0-*
			4.replaced-method 属性：1.name 2.replacer 创建ReplaceOverride对象
			5.arg-type 属性match
			6.constructor-arg 属性1.index 2.type 3.name
			7.property 属性1.name 这个复杂 有空在看
			8.qualifier 属性1.type2.value 子元素：attribute属性1.key2.value
			这样一个bean就组合好了返回这个BeanDefinitionHolder
			如果定制的要包装bean的则包装
			最后一步就是注册BeanDefinition

		 * #################### ReaderContext  XmlReaderContext######################
		 * ReaderContext 定义读取容器，主要用于保存读取状态及资源
		 * ProblemReporter 用于解析document出现问题时问题反馈
		 * ReaderEventListener spring只提供了EmptyReaderEventListener空实现，如果需要你可以自定义
		 * SourceExtractor 这是个提取：解析document后返回的原生bean定义，如果你需要则可以实现这个接口，
		 * 放入到ReaderContext中，读取信息后就可以使用这些资源了。就像访问器一样。
		 *  spring提供了NullSourceExtractor空实现，PassThroughSourceExtractor简单实现返回了对象资源。
		 *  如果需要你可以自定义
		 *  XmlReaderContext类继承自ReaderContext
		 *  它还加入xml中命名空间的解析器NamespaceHandlerResolver和XmlBeanDefinitionReader实现类
		 *  
		 *  ####################### NamespaceHandlerResolver ####################
		 *  NamespaceHandlerResolver接口给定一个命名空间的URL解析本地实现了NamespaceHandler的处理器
		 *  DefaultNamespaceHandlerResolver类实现了NamespaceHandlerResolver
		 *  默认的会查询所有的jar包下的META-INF/spring.handlers文件，解析为map key--命名空间URL value-class类名（转换为对象判断是否是处理器实例），
		 *  然后使用命名空间URL可以查询里面对应的NamespaceHandler
		 *  当然你也可以使用DefaultNamespaceHandlerResolver的构造器DefaultNamespaceHandlerResolver(ClassLoader, String)
		 *  来为自己构造
		 *  
		 *  #######################  NamespaceHandler #####################
		 *  NamespaceHandler接口用来处理spring的xml文件命名空间
		 *  比如：在xml文中引入了bean工厂不认识，也就是你定制的命名空间，在beans元素下使用了该
		 *  命名空间下的元素，则你需要提供一个实现了NamespaceHandler接口的处理器，来处理该
		 *  命名空间下的元素。
		 *  SimpleConstructorNamespaceHandler 直接继承自 BeanDefinitionParser
		 *  用于解析构造器参数 这样简便的定义
		 *  <bean id="author" class="..TestBean" c:name="Enescu" c:work-ref="compositions"/>
		 *  
		 *  SimplePropertyNamespaceHandler 直接继承自BeanDefinitionParser
		 *  解析属性值 简便定义
		 *  <bean id="rob" class="..TestBean" p:name="Rob Harrop" p:spouse-ref="sally"/>
		 *  
		 *  开发人员编写自己的定制元素扩展通常不会直接实现这个接口,而是利用所提供的NamespaceHandlerSupport类。
		 *  接口方法：
		 *  1.初始化方法，用于在需要定制的NamespaceHandler实现类时进行初始化
		 *  2.调用parse方法解析元素为一个BeanDefinition
		 *  3.装饰一个节点或属性为BeanDefinitionHolder
		 *  NamespaceHandlerSupport抽象类，使用了final map 作为注册表
		 *  定义了三个注册表
		 *  1.key-elementName(元素的名称) value -BeanDefinitionParser（命名处理器对应元素的转化器）
		 *  2.key-elementName(元素的名称) value -BeanDefinitionDecorator（命名处理器对应元素的包装器）
		 *  3.key-attrName(属性的名称) value -BeanDefinitionDecorator（命名处理器对应元素属性的包装器）
		 *  让实现的子类通过以下方法注入到注册表中,这个设计有点意思。
		 *  registerBeanDefinitionParser
		 *  registerBeanDefinitionDecorator
		 *  registerBeanDefinitionDecoratorForAttribute 
		 *  子类要实现初始化方法。
		 *  在查找处理器时，通过解析后的命名空间名称查找到对应的处理器，使用转化器进行转换为BeanDefinition
		 *  实现 可以参考ContextNamespaceHandler
		 *  UtilNamespaceHandler 实现了NamespaceHandlerSupport
		 *  它使用初始化方法 把key “constant” "property-path" "list" "set" "map" "properties"元素
		 *  及内部类实现了AbstractSingleBeanDefinitionParser的对象，来注册到父类注册表中
		 #######################  BeanDefinitionParser ##########################
		 *  BeanDefinitionParser接口用来转换xml文件中的元素为一个BeanDefinition
		 *  
		 * ############################# ProblemReporter #################
		 * Location在解析资源出问题时可以得到本地资源位置，
		 * ParseState 是一个轨迹栈，在解析深层次xml元素时，如果出错需要记录一层一层的错误记录。
		 * 使用ParseState把元素压入栈，用完出栈，在出现问题时，从Stack中读取统计的栈信息以树的形式。
		 * 元素继承ParseState的内部接口Entry
		 * 其中BeanEntry、ConstructorArgumentEntry、PropertyEntry、和QualiflerEntry
		 * 每当读取这些元素就把相应的元素入栈和出栈
		 * Problem 对象使用ParseState Location组合信息输出
		 * ProblemReporter接口接受一个Problem定义了问题的性质
		 * 1.致命的
		 * 2.错误
		 * 3.提醒
		 * FailFastProblemReporter类实现了ProblemReporter接口
		 * 对于致命和错误的抛出BeanDefinitionParsingException（Problem）
		 * 提醒的使用了日志记录
		
		 */
		Resource resource =new DefaultResourceLoader().getResource("spring-demo.xml");
		Location location=new Location(resource);
		ParseState ps =new ParseState();
		ps.push(new BeanEntry("i'm bean1"));
		ps.push(new ConstructorArgumentEntry(2));
		ps.push(new PropertyEntry("i'm PropertyEntry3"));
		ps.push(new BeanEntry("i'm bean4"));
		System.out.println(ps.toString());
		ps.pop();
		System.out.println(ps.toString());
		Problem problem =new Problem("出错了",location,ps);
		System.out.println(problem.toString());
		System.out.println(location.getResource());
		ProblemReporter pr = new FailFastProblemReporter();
		pr.warning(problem);
		pr.fatal(problem);
		pr.error(problem);
		// * ############################################################################
	}

}
