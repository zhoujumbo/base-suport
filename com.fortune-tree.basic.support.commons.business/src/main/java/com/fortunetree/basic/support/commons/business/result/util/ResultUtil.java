package com.fortunetree.basic.support.commons.business.result.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fortunetree.basic.support.commons.business.json.JsonClothProcessor;
import com.fortunetree.basic.support.commons.business.json.util.JsonUtil;
import com.fortunetree.basic.support.commons.business.crud.pojo.Page;
import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

public class ResultUtil {

	private static final String JSON_NAME = "json";
	private static final String RESULT_PAGE_NAME = "/result.jsp";

	public static ModelAndView success(Page page,
			JsonClothProcessor clothProcessor) {
		List<JSONObject> jsonObjList = wearCloth(page.getRows(), clothProcessor);
		page.setRows(jsonObjList);
		return success(page);
	}

	public static ModelAndView success(Object obj) {
		JSONObject jsonObj = JsonUtil.toJSONObject(obj);
		return success(jsonObj.toString());
	}

	public static ModelAndView success(String jsonStr) {
		return new ModelAndView(RESULT_PAGE_NAME, JSON_NAME, jsonStr);
	}

	private static List<JSONObject> wearCloth(List<?> list,
			JsonClothProcessor clothProcessor) {
		if (list == null) {
			list = Collections.emptyList();
		}
		List<JSONObject> jsonObjList = new ArrayList<JSONObject>(list.size());
		for (Object pojo : list) {
			jsonObjList.add(clothProcessor.wearCloth(pojo,
					JsonUtil.toJSONObject(pojo)));
		}
		return jsonObjList;
	}

}
