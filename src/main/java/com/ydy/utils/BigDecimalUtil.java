/**
 * 
 */
package com.ydy.utils;

import java.math.BigDecimal;

/**
 * @author xuzhaojie
 *
 *         2019年6月4日 下午2:08:39
 */
public class BigDecimalUtil {
	public static BigDecimal add(BigDecimal v1, BigDecimal v2) {// v1 + v2
		return v1.add(v2);
	}

	public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
		return v1.subtract(v2);
	}

	public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
		return v1.multiply(v2);
	}

	public static BigDecimal discount(BigDecimal v1, BigDecimal v2) {
		return v1.multiply(div8bit(v2, new BigDecimal(100)));
	}

	public static BigDecimal addDiscount(BigDecimal v1, BigDecimal v2) {
		return v1.add(v1.multiply(div8bit(v2, new BigDecimal(100))));
	}

	public static BigDecimal reDiscount(BigDecimal v1, BigDecimal v2) {
		BigDecimal b = div8bit(new BigDecimal(100 + v2.doubleValue()), new BigDecimal(100));
		return div8bit(v1, b);
	}

	public static BigDecimal div8bit(BigDecimal v1, BigDecimal v2) {
		// 2 = 保留小数点后两位 ROUND_HALF_UP = 四舍五入
		return v1.divide(v2, 8, BigDecimal.ROUND_HALF_UP);// 应对除不尽的情况
	}

	public static BigDecimal div4bit(BigDecimal v1, BigDecimal v2) {
		// 2 = 保留小数点后两位 ROUND_HALF_UP = 四舍五入
		return v1.divide(v2, 4, BigDecimal.ROUND_CEILING);// 应对除不尽的情况
	}

	public static BigDecimal div2bit(BigDecimal v1, BigDecimal v2) {
		// 2 = 保留小数点后两位 ROUND_HALF_UP = 四舍五入
		return v1.divide(v2, 2, BigDecimal.ROUND_HALF_UP);// 应对除不尽的情况
	}

	public static Long percent(BigDecimal v1, BigDecimal v2) {
		return v1.divide(v2, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).longValue();// 应对除不尽的情况
	}

	public static void main(String[] args) {
		BigDecimal actualPay = new BigDecimal(200);
		actualPay = addDiscount(actualPay, new BigDecimal(3));
		System.out.println(actualPay);
		System.out.println(reDiscount(actualPay, new BigDecimal(3)));

		System.out.println(div4bit(new BigDecimal("1.23421"), new BigDecimal(1)));
	}
}
