package com.zghw.spring.demo.core.core.source;

import java.lang.annotation.RetentionPolicy;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zghw.spring.demo.core.AttributeAccessor;
import com.zghw.spring.demo.core.core.annonation.Fruit;
@Controller
@Fruit(value="苹果",price=123,names={"green","red"},list=List.class,aw=@Autowired(required=false),policy=RetentionPolicy.CLASS)
public class TypeClass implements AttributeAccessor{
	private String typeField;
	protected class InnerClassx {
	      public InnerClassx() {
	         System.out.println("Inner Classx");
	      }
	   }
}
