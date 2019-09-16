/**
 * 
 */
package com.ydy.exception;

import com.ydy.ienum.base.IErrorEnum;

/**
 * @author xuzhaojie
 *
 *         2018年8月3日 上午11:54:23
 */
public class RemoteApiException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IErrorEnum errorEnum;

	public RemoteApiException(String message) {
		super(message);
	}

	/**
	 * @return the errorEnum
	 */
	public IErrorEnum getErrorEnum() {
		return errorEnum;
	}

	/**
	 * @param errorEnum
	 *            the errorEnum to set
	 */
	public void setErrorEnum(IErrorEnum errorEnum) {
		this.errorEnum = errorEnum;
	}

}
