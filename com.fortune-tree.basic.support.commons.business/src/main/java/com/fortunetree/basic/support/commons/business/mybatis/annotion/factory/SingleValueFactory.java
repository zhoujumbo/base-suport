package com.fortunetree.basic.support.commons.business.mybatis.annotion.factory;

import java.lang.annotation.Annotation;

import com.fortunetree.basic.support.commons.business.mybatis.annotion.SingleValue;
import com.fortunetree.basic.support.commons.business.mybatis.query.condition.Condition;
import com.fortunetree.basic.support.commons.business.mybatis.query.condition.ConditionFactory;

public class SingleValueFactory extends AnnotationConditionFactory {
	public Condition build(Annotation annotation, Object value) {
		if (value == null)
			return null;
		SingleValue singleValue = (SingleValue) annotation;
		return ConditionFactory.buildSingleValueCondition(
				singleValue.tableAlias(), singleValue.column(),
				singleValue.equal(), value);
	}
}