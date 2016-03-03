package com.zghw.spring.demo.core.core;

import java.util.*;

import org.springframework.core.CollectionFactory;

import com.zghw.spring.demo.core.core.source.ApproximableMap;
import com.zghw.spring.demo.core.core.source.ApproximableList;

/**
 * 通过集合类型创建常用的集合对象
 * 用处：使用Map.class List.class等常用集合类型生产对象Map List等集合对象 
 * @author zghw
 *
 */
public class CollectionFactoryTest {
	enum Fruit {
		APPLE, ORANGE, BANAN
	}

	public static void main(String[] args) {
		// 通过集合类型创建常用的集合对象
		Map<Object, Object> hm = CollectionFactory
				.createMap(HashMap.class, 100);
		Map<Object, Object> hm1 = CollectionFactory.createMap(
				LinkedHashMap.class, 100);
		Map<Object, Object> hm2 = CollectionFactory.createMap(EnumMap.class,
				Fruit.class, 100);
		CollectionFactory.createCollection(List.class, 100);
		Collection<Object> c1 = CollectionFactory.createCollection(
				ArrayList.class, 100);
		Collection<Object> c2 = CollectionFactory.createCollection(
				LinkedList.class, 100);
		Collection<Object> c3 = CollectionFactory.createCollection(
				EnumSet.class, Fruit.class, 100);
		CollectionFactory.isApproximableCollectionType(List.class);
		// 判断一个map list类型是否是JDK还是自定义的
		System.out.println(CollectionFactory
				.isApproximableMapType(ApproximableMap.class));
		System.out.println(CollectionFactory
				.isApproximableMapType(HashMap.class));
		System.out.println(CollectionFactory
				.isApproximableCollectionType(ApproximableList.class));
		System.out.println(CollectionFactory
				.isApproximableCollectionType(LinkedList.class));

	}

}
