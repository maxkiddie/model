package com.ydy.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created By Donghua.Chen on 2018/1/9
 */
public class StringUtils {

	public static String UUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static boolean isEmpty(String str) {
		return org.springframework.util.StringUtils.isEmpty(str);
	}

	/**
	 * 判断是否为空 xuzhaojie -2016-9-13 上午9:56:52
	 */
	public static boolean isNull(Object... object) {
		for (Object obj : object) {
			if (obj == null || "".equals(obj.toString()))
				return true;
		}
		return false;
	}

	/**
	 * 判断是否为非空 xuzhaojie -2016-9-13 上午9:56:52
	 */
	public static boolean isNotNull(Object... object) {
		return !isNull(object);
	}

	public static void setParamEmptyToNull(Object object) {
		Class<?> clazz = object.getClass();
		Method[] methods = clazz.getMethods();
		Object[] objects = {};
		Object[] params = { null };
		Object result = null;
		for (Method m : methods) {
			if (m.getReturnType().equals(String.class) && m.getName().startsWith("get")) {
				try {
					result = m.invoke(object, objects);
					if (result != null && "".equals(result.toString())) {
						m = clazz.getMethod("set" + m.getName().substring(3), String.class);
						m.invoke(object, params);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
