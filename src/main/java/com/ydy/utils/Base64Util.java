package com.ydy.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

	public static String encode(String str) {
		return Base64.encodeBase64String(str.getBytes());
	}

	public static String decode(String str) {
		try {
			return new String(Base64.decodeBase64(str.getBytes()), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
