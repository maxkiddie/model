/**
 * 
 */
package com.ydy.remote.wechat.exception;

/**
 * @author xuzhaojie
 *
 *         2019年6月3日 下午5:18:53
 */
public class WeChatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long code;
	private String msg;

	/**
	 * @return the code
	 */
	public Long getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @param code
	 * @param msg
	 */
	public WeChatException(Long code, String msg) {
		super(msg + "(" + code + ")");
		this.code = code;
		this.msg = msg;
	}

	public WeChatException(String name) {
		super(name);
	}

}
