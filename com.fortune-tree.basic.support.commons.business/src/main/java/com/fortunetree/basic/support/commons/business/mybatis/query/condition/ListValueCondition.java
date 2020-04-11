package com.fortunetree.basic.support.commons.business.mybatis.query.condition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fortunetree.basic.support.commons.business.mybatis.query.ConditionQuery;

public class ListValueCondition extends Condition {
	private String column;
	private String equal;
	private List<Object> value = new ArrayList<Object>();

	public ListValueCondition(String condition, String equal, List<Object> value) {
		this.column = condition;
		this.equal = equal;
		this.value = value;
	}

	public ListValueCondition(String condition, String equal, Object[] value) {
		this.column = condition;
		this.equal = equal;
		ArrayList<Object> list = new ArrayList<Object>();
		for (Object obj : value) {
			list.add(obj);
		}
		this.value = list;
	}

	public ListValueCondition(String tableAlias, String condition,
			String equal, List<Object> value) {
		this(condition, equal, value);
		this.tableAlias = tableAlias;
	}

	public ListValueCondition(String tableAlias, String condition,
			String equal, Object[] value) {
		this(condition, equal, value);
		this.tableAlias = tableAlias;
	}

	public String getColumn() {
		return this.column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getEqual() {
		return this.equal;
	}

	public void setEqual(String equal) {
		this.equal = equal;
	}

	public List<Object> getValue() {
		return this.value;
	}

	public void setValue(List<Object> value) {
		this.value = value;
	}

	public void addConditionToQuery(ConditionQuery imp) {
		imp.addListValueCondition(this);
	}

	public void addValue(Object value) {
		this.value.add(value);
	}

	public String toSql() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.column);
		sb.append(" ");
		sb.append(this.equal);
		sb.append(" (");
		boolean first = true;
		for (Iterator<Object> localIterator = this.value.iterator(); localIterator
				.hasNext();) {
			Object v = localIterator.next();
			if (!first) {
				sb.append(",");
				first = false;
			}
			sb.append("'");
			sb.append(v);
			sb.append("'");
		}

		sb.append(")");
		return sb.toString();
	}
}