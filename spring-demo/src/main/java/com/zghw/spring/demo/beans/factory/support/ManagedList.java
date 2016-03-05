package com.zghw.spring.demo.beans.factory.support;

import java.util.ArrayList;

import com.zghw.spring.demo.beans.Mergeable;
import com.zghw.spring.demo.beans.*;

public class ManagedList<E> extends ArrayList<E> implements Mergeable, BeanMetadataElement  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
