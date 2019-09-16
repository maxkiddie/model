/**
 * 
 */
package com.ydy.utils;

import java.util.Arrays;

/**
 * @author xuzhaojie
 *
 *         2018年12月5日 下午3:51:26
 */
public class MathUtil {

	public static String[] trimNumberUnit(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		StringBuilder sb = new StringBuilder("");
		str = str.trim().replace(" ", "");// 去掉所有空格
		int size = str.length();
		char ch;
		String[] res = new String[2];
		for (int index = 0; index < size; index++) {
			ch = str.charAt(index);
			if ((ch >= '0' && ch <= '9') || ch == '.') {
				sb.append(ch);
				continue;
			}
			res[0] = sb.toString();
			res[1] = str.substring(index, size);
			break;
		}
		return res;
	}

	public static Integer doubleToInt(Double d) {
		if (d == null)
			return null;
		return d.intValue();
	}

	public static String trimNumber(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		StringBuilder sb = new StringBuilder("");
		str = str.trim().replace(" ", "");// 去掉所有空格
		int size = str.length();
		char ch;
		for (int index = 0; index < size; index++) {
			ch = str.charAt(index);
			if ((ch >= '0' && ch <= '9') || ch == '.') {
				sb.append(ch);
				continue;
			}
			break;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(Arrays.asList(trimNumber("1   5.2  1  g")));
		System.out.println(Arrays.asList(trimNumber("0. 95  k g")));
		System.out.println(Arrays.asList(trimNumber("1 5 . 2克")));
		System.out.println(Arrays.asList(trimNumber("15  袋    ")));
	}
}
