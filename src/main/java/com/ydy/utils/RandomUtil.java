package com.ydy.utils;

public class RandomUtil {
	public static int getRandomInt(int num1, int num2) {
		int n = num1 + (int) (Math.random() * (num2 - num1));
		return n;
	}

	public static void main(String args[]) {
		for (int i = 0; i <= 100; i++)
			System.out.println(RandomUtil.getRandomInt(0, 7)); // 产生的随机数包括1，不包括7
	}

}
