package com.zghw.spring.demo.core.core.source;

import java.util.LinkedList;
import java.util.List;

public class Lis implements Collect<User> {
	private final List<User> list = new LinkedList<User>();
	public List<User> add(User item) {
		list.add(item);
		return list;
	}

}
