package com.fortunetree.basic.support.commons.business.crud.bo;

import com.fortunetree.basic.support.commons.business.crud.dao.SchDao;
import com.fortunetree.basic.support.commons.business.crud.pojo.BasePojo;
import com.fortunetree.basic.support.commons.business.crud.pojo.Page;
import com.fortunetree.basic.support.commons.business.crud.pojo.QueryPojo;
import com.fortunetree.basic.support.commons.business.mybatis.query.ConditionQuery;
import com.fortunetree.basic.support.commons.business.mybatis.query.condition.Condition;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 业务层查询通用父类,适用仅需查询功能需求
 * 
 * @author zy.wu
 * 
 */
public class SchBo<T extends BasePojo, Dao extends SchDao<T>> {
	protected Dao dao;

	/**
	 * 注入相应持久层操作对象
	 * 
	 * @param dao
	 */
	@Autowired
	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public Page list(Page page) {
		ConditionQuery query = page.buildConditionQuery();
		return this.queryAndSetPage(page, query);
	}

	public Page list(Page page, List<Condition> appendConditions) {
		ConditionQuery query = page.buildConditionQuery();
		if (null != appendConditions && appendConditions.size() > 0) {
			query.addAll(appendConditions);
		}
		return this.queryAndSetPage(page, query);
	}

	public Page list(Page page, Condition appendCondition) {
		ConditionQuery query = page.buildConditionQuery();
		if (null != appendCondition) {
			query.add(appendCondition);
		}
		return this.queryAndSetPage(page, query);
	}

	private Page queryAndSetPage(Page page, ConditionQuery query) {
		int cnt = dao.queryCnt(query);
		page.setTotal(cnt);
		if (cnt > 0) {
			List<T> newsList = dao.query(query);
			page.setRows(newsList);
		}
		return page;
	}

	public List<T> query(QueryPojo queryPojo) {
		ConditionQuery query = queryPojo.buildConditionQuery();
		return dao.query(query);
	}

	public int queryCnt(QueryPojo queryPojo) {
		ConditionQuery query = queryPojo.buildConditionQuery();
		return dao.queryCnt(query);
	}

	public List<T> query(List<Condition> conditions) {
		ConditionQuery query = new ConditionQuery();
		if (null != conditions && conditions.size() > 0) {
			query.addAll(conditions);
		}
		return dao.query(query);
	}

	public int queryCnt(List<Condition> conditions) {
		ConditionQuery query = new ConditionQuery();
		if (null != conditions && conditions.size() > 0) {
			query.addAll(conditions);
		}
		return dao.queryCnt(query);
	}

	public List<T> query(Condition condition) {
		ConditionQuery query = new ConditionQuery();
		query.add(condition);
		return dao.query(query);
	}

	public int queryCnt(Condition condition) {
		ConditionQuery query = new ConditionQuery();
		query.add(condition);
		return dao.queryCnt(query);
	}

	public List<T> query(QueryPojo queryPojo, List<Condition> appendConditions) {
		ConditionQuery query = queryPojo.buildConditionQuery();
		if (null != appendConditions && appendConditions.size() > 0) {
			query.addAll(appendConditions);
		}
		return dao.query(query);
	}

	public List<T> query(QueryPojo queryPojo, Condition appendCondition) {
		ConditionQuery query = queryPojo.buildConditionQuery();
		if (null != appendCondition) {
			query.add(appendCondition);
		}
		return dao.query(query);
	}

	public int queryCnt(QueryPojo queryPojo, List<Condition> appendConditions) {
		ConditionQuery query = queryPojo.buildConditionQuery();
		if (null != appendConditions && appendConditions.size() > 0) {
			query.addAll(appendConditions);
		}
		return dao.queryCnt(query);
	}

	public int queryCnt(QueryPojo queryPojo, Condition appendCondition) {
		ConditionQuery query = queryPojo.buildConditionQuery();
		if (null != appendCondition) {
			query.add(appendCondition);
		}
		return dao.queryCnt(query);
	}


}
