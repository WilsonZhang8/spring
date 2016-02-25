package com.zghw.spring.demo.core.core.source;

/**
 * 元组 它是将一组对象直接打包存储于其中一个单一对象。这个容器对象允许 读取其中元素，但是不允许向其中存放新的对项目。也可叫做数据传递对象或信使
 * 主要用于仅一次方法调用就能返回多个对象。 元祖可以具有任意长度，同时，元组中地对象可以是任意不同的类型。不过我们希望能够为
 * 每一个对象指明其类型，并且从容器中读取出来时，能够得到正确的类型。要处理不同长度的问题， 我们需要创建多个不同的元祖
 * 
 * @author zghw
 *
 * @param <A>
 * @param <B>
 */
public class TwoTuple<A, B> {
	// 这里没有first和second没有使用private 并且没有设置set和get方法
	// 而是采用了public final ，因为只为了读取first 和second
	// 不用于修改，要向修改需要重新构造新对象放入，final保证了不能修改
	public final A first;
	public final B second;

	public TwoTuple(A first, B second) {
		this.first = first;
		this.second = second;
	}

	public String toString() {
		return "first " + " second ";
	}
}
