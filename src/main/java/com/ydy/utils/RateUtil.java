/**
 * 
 */
package com.ydy.utils;

import java.math.BigDecimal;

/**
 * @author xuzhaojie
 *
 *         2019年6月4日 下午12:28:57
 */
public class RateUtil {

	public static BigDecimal calculate(BigDecimal sourceTotal, BigDecimal rate) {
		return BigDecimalUtil.div4bit(sourceTotal, rate);
	}

	public static void main(String[] args) {
		System.out.println(calculate(new BigDecimal(1500.0), new BigDecimal("8201.21")));
	}
}
