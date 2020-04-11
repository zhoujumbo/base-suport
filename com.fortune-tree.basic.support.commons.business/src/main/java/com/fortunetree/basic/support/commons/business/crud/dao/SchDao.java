package com.fortunetree.basic.support.commons.business.crud.dao;

import com.fortunetree.basic.support.commons.business.mybatis.query.ConditionQuery;
import com.fortunetree.basic.support.commons.business.crud.pojo.BasePojo;

import java.util.List;

/**
 * 持久层查询通用父接口,适用仅需查询功能需求
 * 
 * @author zy.wu
 * 
 */
public interface SchDao<T extends BasePojo> {

	/**
	 * 根据条件查询数据库记录列表
	 * 
	 * @param query
	 * @return
	 */
	List<T> query(ConditionQuery query);

	/**
	 * 根据条件查询数据库记录条数
	 * 
	 * @param query
	 * @return
	 */
	int queryCnt(ConditionQuery query);
}
