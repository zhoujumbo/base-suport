package com.fortunetree.basic.support.commons.business.crud.bo;

import com.fortunetree.basic.support.commons.business.crud.dao.CrudDao;
import com.fortunetree.basic.support.commons.business.crud.pojo.BasePojo;

/**
 * 业务层增、删、改、查通用父类
 * 
 * @author zy.wu
 * 
 */
public class CrudBo<T extends BasePojo, Dao extends CrudDao<T>> extends
		SchBo<T, Dao> {

	/**
	 * 查询数据
	 * @param id
	 * @return
	 */
//	public T get(Serializable id) {
//		return dao.get(id);
//	}

	/**
	 * 新增数据
	 * 
	 * @param query
	 */
//	public int save(T query) {
//		return dao.save(query);
//	}

	/**
	 * 删除数据
	 * 
	 * @param id
	 */
//	public int delete(Serializable id) {
//		return dao.delete(id);
//	}

	/**
	 * 修改数据
	 * 
	 * @param query
	 */
//	public int update(T query) {
//		return dao.update(query);
//	}
}
