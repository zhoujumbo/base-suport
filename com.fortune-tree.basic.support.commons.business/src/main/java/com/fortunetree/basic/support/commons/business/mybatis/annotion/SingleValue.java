package com.fortunetree.basic.support.commons.business.mybatis.annotion;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ java.lang.annotation.ElementType.CONSTRUCTOR,
		java.lang.annotation.ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SingleValue {
	public abstract String column();

	public String equal() default "=";

	public String tableAlias() default "t";
}