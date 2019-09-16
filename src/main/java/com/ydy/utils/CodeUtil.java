package com.ydy.utils;

public class CodeUtil {
	private static final String VERIFY_CODES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	/**
	 * 使用指定源生成验证码
	 * 
	 * @param verifySize 验证码长度
	 * @param sources    验证码字符源
	 * @return
	 */
	public static String generateVerifyCode(int verifySize) {
		int codesLen = VERIFY_CODES.length();
		int random = 0;
		StringBuilder verifyCode = new StringBuilder(verifySize);
		for (int i = 0; i < verifySize; i++) {
			random = RandomUtil.getRandomInt(0, codesLen);
			verifyCode.append(VERIFY_CODES.charAt(random));
		}
		return verifyCode.toString();
	}

	public static void main(String[] args) {
		System.out.println(generateVerifyCode(8));
	}
}
