package com.fortunetree.basic.support.commons.business.mybatis.query.condition;

import com.fortunetree.basic.support.commons.business.mybatis.query.ConditionQuery;

public class WithoutValueCondition extends Condition {
	private String sql;

	public WithoutValueCondition(String sql) {
		this.sql = sql;
	}

	public void addConditionToQuery(ConditionQuery imp) {
		imp.addWithoutValueCondition(this);
	}

	public String toString() {
		return this.sql;
	}

	public String getColumn() {
		return null;
	}

	public String toSql() {
		return this.sql;
	}
}