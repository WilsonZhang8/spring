package com.zghw.spring.demo.core.core.source;

public class BridgeMethod<T> {
	private T item;

	public BridgeMethod(T item) {
		this.item = item;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}
	
}
