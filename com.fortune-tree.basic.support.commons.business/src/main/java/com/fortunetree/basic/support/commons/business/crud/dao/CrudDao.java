package com.fortunetree.basic.support.commons.business.crud.dao;

import com.fortunetree.basic.support.commons.business.crud.pojo.BasePojo;

/**
 * 持久层增、删、改、查通用父接口
 * 
 * @author zy.wu
 * 
 */
public interface CrudDao<T extends BasePojo> extends SchDao<T> {

	/**
	 * 根据主键ID获得数据库对应记录
	 *
	 * @param id
	 * @return
	 */
//	T get(Serializable id);

	/**
	 * 新增数据库记录
	 * 
	 * @param query
	 * @return 包含<selectKey>语句返回值为主键,否则为null
	 */
//	int save(T query);

	/**
	 * 删除数据库指定主键记录
	 * 
	 * @param id
	 * @return 删除的行数
	 */
//	int delete(Serializable id);

	/**
	 * 更新数据库记录
	 * 
	 * @param query
	 * @return 更新的行数
	 */
//	int update(T query);

}
