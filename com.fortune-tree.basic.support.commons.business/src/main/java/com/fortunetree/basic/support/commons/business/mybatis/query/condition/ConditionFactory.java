package com.fortunetree.basic.support.commons.business.mybatis.query.condition;

import java.util.List;

public class ConditionFactory {

	public static Condition buildSqlCondition(String sql) {
		return new WithoutValueCondition(sql);
	}

	public static Condition buildBetweenCondition(String column,
			Object beginValue, Object endValue) {
		return new BetweenValueCondition(column, beginValue, endValue);
	}

	public static Condition buildListCondition(String tableAlias,
			String column, String equal, List<Object> list) {
		return new ListValueCondition(tableAlias, column, equal, list);
	}

	public static Condition buildListCondition(String tableAlias,
			String column, String equal, Object[] list) {
		return new ListValueCondition(tableAlias, column, equal, list);
	}

	public static Condition buildSingleValueCondition(String tableAlias,
			String field, String equal, Object value) {
		return new SingleValueCondition(tableAlias, field, equal, value);
	}
}