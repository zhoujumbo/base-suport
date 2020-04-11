package com.fortunetree.basic.support.commons.business.json.util;

import com.fortunetree.basic.support.commons.business.json.ToJson;
import com.fortunetree.basic.support.commons.business.json.annotion.JsonData;
import com.fortunetree.basic.support.commons.business.json.annotion.NotJsonData;
import com.fortunetree.basic.support.commons.business.util.DateUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;

public class JsonUtil {
	private static DecimalFormat dfm = new DecimalFormat("##0.00");

	public static JSONObject toJSONObject(Object target) {
		if (target == null)
			return null;
		String methodName = null;
		JSONObject jsObject = new JSONObject();
		try {
			Method[] methods = target.getClass().getMethods();
			for (Method method : methods) {
				methodName = method.getName();
				if (!methodName.equals("getClass")
						&& methodName.startsWith("get")
						&& !method.isAnnotationPresent(NotJsonData.class)
						&& method.getParameterTypes().length == 0) {
					String field = methodName.substring(3, 4).toLowerCase()
							+ methodName.substring(4);
					Object value = method.invoke(target, new Object[] {});
					boolean isJsonData = false;
					if (method.isAnnotationPresent(JsonData.class)) {
						isJsonData = true;
						JsonData setJs = method.getAnnotation(JsonData.class);
						if (setJs.field() != null && !"".equals(setJs.field())) {
							field = setJs.field();
						}
					}
					if (value instanceof Collection) {
						if (isJsonData) {
							Collection<?> set = (Collection<?>) value;
							JSONArray jsonArray = new JSONArray();
							for (Object obj : set) {
								if (obj instanceof ToJson) {
									JSONObject jsonObj = ((ToJson) obj)
											.toJSONObject();
									jsonArray.put(jsonObj);
								} else {
									jsonArray.put(obj);
								}
							}
							jsObject.put(field, jsonArray);
						}
					} else if (value instanceof Number) {
						if (value instanceof Double) {
							jsObject.put(field, dfm.format(value));
						} else {
							jsObject.put(field, value);
						}
					} else if (value instanceof Date) {
						String strDate = DateUtil.ymdhmsFormat((Date) value);
						jsObject.put(field, strDate);
					} else if (value instanceof ToJson) {
						jsObject.put(field, ((ToJson) value).toJSONObject());
					} else {
						jsObject.put(field, value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsObject;
	}

	public static JSONArray toJSONArray(Collection<?> target) {
		if (target == null) {
			return null;
		}
		JSONArray jsonArray = new JSONArray();
		for (Object obj : target) {
			jsonArray.put(toJSONObject(obj));
		}
		return jsonArray;
	}

	public static JSONArray toJSONArray(Object[] target) {
		if (target == null) {
			return null;
		}
		JSONArray jsonArray = new JSONArray();
		for (Object obj : target) {
			jsonArray.put(toJSONObject(obj));
		}
		return jsonArray;
	}
}
