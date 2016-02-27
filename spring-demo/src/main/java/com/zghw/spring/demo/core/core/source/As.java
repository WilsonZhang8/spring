package com.zghw.spring.demo.core.core.source;

import org.springframework.core.annotation.AliasFor;

public @interface As {
	@AliasFor
	public String value();
}
