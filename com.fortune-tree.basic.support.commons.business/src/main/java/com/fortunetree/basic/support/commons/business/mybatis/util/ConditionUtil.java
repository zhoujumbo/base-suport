package com.fortunetree.basic.support.commons.business.mybatis.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.fortunetree.basic.support.commons.business.mybatis.annotion.factory.AnnotationConditionFactory;
import com.fortunetree.basic.support.commons.business.mybatis.query.condition.Condition;

public class ConditionUtil {

	public static List<Condition> getConditions(Object target) {
		if (target == null) {
			return null;
		}
		List<Condition> ret = new ArrayList<Condition>();
		String methodName = null;
		try {
			Method[] methods = target.getClass().getMethods();
			for (Method method : methods) {
				methodName = method.getName();
				Annotation[] annotations = method.getAnnotations();
				if ((methodName.startsWith("get"))
						&& (AnnotationConditionFactory.couldBuild(annotations))) {
					Object value = method.invoke(target, new Object[0]);
					for (Annotation annotation : annotations) {
						Condition condition = AnnotationConditionFactory
								.buildCondition(annotation, value);
						if (condition != null)
							ret.add(condition);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(methodName);
			e.printStackTrace();
		}
		return ret;
	}
}
