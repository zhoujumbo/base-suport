package com.fortunetree.basic.support.commons.business.mybatis.query.condition;

import com.fortunetree.basic.support.commons.business.mybatis.query.ConditionQuery;

public class BetweenValueCondition extends Condition {
	private String column;
	private Object beginValue;
	private Object endValue;

	public BetweenValueCondition(String column, Object beginValue,
			Object endValue) {
		this.column = column;
		this.beginValue = beginValue;
		this.endValue = endValue;
	}

	public String getColumn() {
		return this.column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Object getBeginValue() {
		return this.beginValue;
	}

	public void setBeginValue(Object beginValue) {
		this.beginValue = beginValue;
	}

	public Object getEndValue() {
		return this.endValue;
	}

	public void setEndValue(Object endValue) {
		this.endValue = endValue;
	}

	public void addConditionToQuery(ConditionQuery imp) {
		imp.addBetweenValueCondition(this);
	}

	public String toSql() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.column);
		sb.append(" between ");
		sb.append("'");
		sb.append(this.beginValue);
		sb.append("' and '");
		sb.append(this.endValue);
		sb.append("'");
		return sb.toString();
	}
}