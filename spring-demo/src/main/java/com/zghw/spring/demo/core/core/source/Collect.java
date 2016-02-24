package com.zghw.spring.demo.core.core.source;

import java.util.List;

public interface Collect<T> {
	public List<T> add(T item);
}
