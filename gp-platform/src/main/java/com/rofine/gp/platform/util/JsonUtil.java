package com.rofine.gp.platform.util;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static String toJson(Object object) throws Exception {
		// 将一个Java对象转换成JSON
		StringWriter writer = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(writer, object);
		return writer.toString();
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
