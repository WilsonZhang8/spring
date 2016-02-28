package com.zghw.spring.demo.core.core;

import org.springframework.core.ControlFlow;
import org.springframework.core.ControlFlowFactory;

/**
 * 栈轨迹信息查询 判断 类 方法 及输入值是否在栈的轨迹中
 * 用处：查找方法调用即栈轨迹，可以做异常处理等
 * @author zghw
 *
 */
public class ControlFlowTest {
	static void f(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) {
		/**
		 * 演示了java方法栈追踪的信息
		 */
		new ControlFlowTest().new A().methodA1();
		ControlFlow cf = ControlFlowFactory.createControlFlow();
		boolean underA = cf.under(A.class);
		boolean underB1 = cf.under(B.class, "methodB1");
		boolean underC2 = cf.under(C.class, "methodC2");
		// 因为在main中就返回false 看C类中的under效果
		System.out.println("==========现在在主类的main方法中================");
		System.out.println("是否经过类A：" + underA);
		System.out.println("是否经过类B下的methodB1方法：" + underB1);
		System.out.println("是否经过类C下的methodC2方法：" + underC2);
	}

	class C {
		public void methodC1() {
			methodC2();
			ControlFlow cf = ControlFlowFactory.createControlFlow();
			boolean underA = cf.under(A.class);
			boolean underB1 = cf.under(B.class, "methodB1");
			boolean underC2 = cf.under(C.class, "methodC2");
			boolean underTokenA2 = cf.underToken("methodA2");
			boolean underTokenC2 = cf.underToken("methodC2");
			System.out.println("==========现在在C类的methodC1方法中================");
			System.out.println("是否经过类A：" + underA);
			System.out.println("是否经过类B下的methodB1方法：" + underB1);
			System.out.println("是否经过类C下的methodC2方法：" + underC2);
			System.out.println("在栈中是否有标记的token信息 methodA2：" + underTokenA2);
			System.out.println("在栈中是否有标记的token信息 methodC2：" + underTokenC2);
		}

		public void methodC2() {
			// 创建一个栈追踪数组 循环栈元素
			StackTraceElement[] stacks = new Throwable().getStackTrace();
			for (StackTraceElement stack : stacks) {
				System.out.println("----------------------------");
				System.out.println("文件名：" + stack.getFileName());
				System.out.println("类名：" + stack.getClassName());
				System.out.println("方法名：" + stack.getMethodName());
				System.out.println("行号：" + stack.getLineNumber());
			}
			System.out.println("#############################");
			new Throwable().printStackTrace();
			System.out.println("#############################");
		}
	}

	class A {
		public void methodA1() {
			methodA2();
		}

		public void methodA2() {
			new B().methodB1();
			;
		}
	}

	class B {
		public void methodB1() {
			new C().methodC1();
		}
	}
}
