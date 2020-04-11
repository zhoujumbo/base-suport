package com.fortunetree.basic.support.commons.business.crud.pojo;

import java.io.Serializable;

import org.json.JSONObject;

import com.fortunetree.basic.support.commons.business.json.ToJson;
import com.fortunetree.basic.support.commons.business.json.util.JsonUtil;

public class BasePojo implements ToJson, Serializable {

	@Override
	public JSONObject toJSONObject() {
		return JsonUtil.toJSONObject(this);
	}

}
