package com.zghw.spring.demo.beans.test;

public class BeanFactoryTest {

	public static void main(String[] args) {
		/**
		 * BeanFactory 访问spring bean的容器的根接口
		 * 这个接口的实现是通过一个数字代表一个bean对象，唯一一个String名称
		 * 根据bean上的定义，工厂将返回一个包含对象的实例（原型设计模式）
		 * 或一个共享实例（在工厂允许实例是一个单例时，更好的选择时单例模式）
		 * 返回那个实例类型依赖于bean工厂的配置 API也一样，
		 * Spring 2.0以来,更多的范围可以根据具体的应用程序上下文(例如“请求”和“会话”范围在web环境中)。
		 * 关键在于BeanFactory是应用程序组件注册中心和应用程序组件配置中心(单个对象不再需要读取属性文件
		 * 注意,最好还是依靠依赖注入(“推”配置)来配置应用程序对象通过setter方法或构造函数,而不是使用任何形式的“拉动”配置像BeanFactory查找。)
		 * Spring的依赖注入的功能是使用这个BeanFactory接口及其实现的子接口
		 * 
		 */
	}

}
