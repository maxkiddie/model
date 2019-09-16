/**
 * 
 */
package com.ydy.filter.base;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 * @author xuzhaojie
 *
 *         2018年9月21日 下午4:23:38
 */
public abstract class BaseFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}
