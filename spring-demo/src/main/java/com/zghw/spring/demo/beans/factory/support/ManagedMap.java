package com.zghw.spring.demo.beans.factory.support;

import java.util.LinkedHashMap;

import com.zghw.spring.demo.beans.Mergeable;
import com.zghw.spring.demo.beans.*;
public class ManagedMap <K, V> extends LinkedHashMap<K, V> implements Mergeable, BeanMetadataElement {

}
