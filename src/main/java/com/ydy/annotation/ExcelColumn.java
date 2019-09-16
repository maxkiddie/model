/**
 * 
 */
package com.ydy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel导入输出列信息
 * 
 * @author xuzhaojie
 *
 *         2018年12月20日 下午4:56:44
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
	String value();// excel第一行的值

	int index();// 对应excel第几列
}
