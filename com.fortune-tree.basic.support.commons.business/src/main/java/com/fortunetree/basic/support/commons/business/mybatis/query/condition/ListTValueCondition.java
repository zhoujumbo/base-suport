package com.fortunetree.basic.support.commons.business.mybatis.query.condition;
/**
 * 扩展ListValueCondition类
 * 可自行指定参数集合类型
 */

import com.fortunetree.basic.support.commons.business.mybatis.query.ConditionQuery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTValueCondition<T> extends Condition {
	private String column;
	private String equal;
	private List<T> value = new ArrayList<>();

	public ListTValueCondition(String condition, String equal, List<T> value) {
		this.column = condition;
		this.equal = equal;
		this.value = value;
	}

	public ListTValueCondition(String condition, String equal, T[] value) {
		this.column = condition;
		this.equal = equal;
		ArrayList<T> list = new ArrayList<>();
		for (T obj : value) {
			list.add(obj);
		}
		this.value = list;
	}

	public ListTValueCondition(String tableAlias, String condition,
                               String equal, List<T> value) {
		this(condition, equal, value);
		this.tableAlias = tableAlias;
	}

	public ListTValueCondition(String tableAlias, String condition,
                               String equal, T[] value) {
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

	public List<T> getValue() {
		return this.value;
	}

	public void setValue(List<T> value) {
		this.value = value;
	}

	public void addConditionToQuery(ConditionQuery imp) {
		imp.addListValueCondition(this);
	}

	public void addValue(T value) {
		this.value.add(value);
	}

	public String toSql() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.column);
		sb.append(" ");
		sb.append(this.equal);
		sb.append(" (");
		boolean first = true;
		for (Iterator<T> localIterator = this.value.iterator(); localIterator
				.hasNext();) {
			T v = localIterator.next();
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