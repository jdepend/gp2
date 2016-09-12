package com.rofine.gp.platform.util;

import java.io.StringWriter;

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

}
