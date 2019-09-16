/**
 * 
 */
package com.ydy.vo.other;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuzhaojie
 *
 *         2019年5月6日 上午11:59:30
 */
public class ErrorVo extends BaseVo {
	private Map<String, String> errors = new HashMap<String, String>();

	public void putError(String key, String value) {
		this.errors.put(key, value);
	}

	/**
	 * @return the errors
	 */
	public Map<String, String> getErrors() {
		return errors;
	}

	/**
	 * @param errors
	 *            the errors to set
	 */
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}
