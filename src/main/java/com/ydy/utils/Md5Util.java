/**
 * 
 */
package com.ydy.utils;

import java.security.MessageDigest;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 上午10:10:31
 */
public class Md5Util {
	private final static char[] hexDigits = "0123456789abcdef".toCharArray();
	public static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x00 };

	/**
	 * 对字符串md5加密(小写+字母)
	 *
	 * @param str
	 *            传入要加密的字符串
	 * @return MD5加密后的字符串
	 */
	public static String getMD5(String origin) {
		String resultString = null;
		try {
			resultString = origin;
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] byteArray = md.digest(resultString.getBytes("UTF-8"));
			return byteArray2String(byteArray);
		} catch (Exception ex) {
			return null;
		}
	}

	public static String byteArray2String(byte[] byteArray) {
		StringBuffer resultSb = new StringBuffer(33);
		for (int i = 0; i < byteArray.length; i++) {

			int value = byteArray[i];
			if (value < 0) {
				value += 256;
			}
			int d1 = value / 16;
			int d2 = value % 16;
			resultSb.append(hexDigits[d1]).append(hexDigits[d2]);
		}
		return resultSb.toString();
	}
}
