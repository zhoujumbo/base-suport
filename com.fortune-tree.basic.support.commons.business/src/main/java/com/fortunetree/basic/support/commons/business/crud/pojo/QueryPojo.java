package com.fortunetree.basic.support.commons.business.crud.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fortunetree.basic.support.commons.business.mybatis.query.ConditionQuery;
import com.fortunetree.basic.support.commons.business.mybatis.query.condition.Condition;
import com.fortunetree.basic.support.commons.business.mybatis.util.ConditionUtil;

public class QueryPojo {

	public ConditionQuery buildConditionQuery() {
		ConditionQuery query = this.newConditionQuery();
		query.addAll(this.toConditions());
		if (null != this.queryParamMap) {
			query.addAllParam(this.queryParamMap);
		}
		return query;
	}

	protected ConditionQuery newConditionQuery() {
		return new ConditionQuery();
	}

	protected List<Condition> toConditions() {
		return ConditionUtil.getConditions(this);
	}

	public void appendQueryParam(String key, Object value) {
		if (null == this.queryParamMap) {
			this.queryParamMap = new HashMap<String, Object>();
		}
		this.queryParamMap.put(key, value);
	}

	private Map<String, Object> queryParamMap = null;
}
