package com.fortunetree.basic.support.commons.business.mybatis.annotion.factory;

import java.lang.annotation.Annotation;

import com.fortunetree.basic.support.commons.business.mybatis.query.condition.Condition;

public class ConditionValueFactory extends AnnotationConditionFactory {
	public Condition build(Annotation annotation, Object value) {
		return (Condition) value;
	}
}
