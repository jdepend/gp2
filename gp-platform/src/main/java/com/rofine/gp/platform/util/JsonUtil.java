package com.rofine.gp.platform.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	public static String toJson(Object object){
		JSONObject json = JSONObject.fromObject(object);
		return json.toString();
	}

	public static <T> T toObject(String json, Class<T> type) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		return (T) JSONObject.toBean(jsonObject, type);
	}

	public static String toJsonArray(List list) {
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}

	public static <T> List<T> toArray(String json, Class<T> type) {
		JSONArray jsonarray = JSONArray.fromObject(json);
		return (List<T>) JSONArray.toCollection(jsonarray, type);
	}
}
