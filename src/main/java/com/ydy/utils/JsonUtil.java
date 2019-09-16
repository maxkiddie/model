/**
 * 
 */
package com.ydy.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 上午11:50:06
 */
public class JsonUtil {

	public static String toJson(Object object) {
		return JSON.toJSONString(object);
	}

	public static <T> T toObject(String jsonString, Class<T> clazz) {
		return JSONObject.parseObject(jsonString, clazz);
	}
}
