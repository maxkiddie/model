/**
 * 
 */
package com.ydy.exception;

import java.util.Map;

/**
 * @author xuzhaojie
 *
 *         2018年8月3日 上午11:54:23
 */
public class ValidateException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, String> errors;

	public ValidateException(String message) {
		this(message, null);
	}

	public ValidateException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidateException( Map<String, String> errors) {
		this.errors = errors;
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
