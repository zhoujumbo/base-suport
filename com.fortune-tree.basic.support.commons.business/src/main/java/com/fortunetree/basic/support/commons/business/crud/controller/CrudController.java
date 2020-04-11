package com.fortunetree.basic.support.commons.business.crud.controller;

import com.fortunetree.basic.support.commons.business.crud.bo.CrudBo;
import com.fortunetree.basic.support.commons.business.crud.pojo.BasePojo;
import com.fortunetree.basic.support.commons.business.crud.dao.CrudDao;

public class CrudController<Pojo extends BasePojo, Bo extends CrudBo<Pojo, ? extends CrudDao<Pojo>>>
		extends SchController<Pojo, Bo> {

}
