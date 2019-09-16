/**
 * 
 */
package com.ydy.vo.token;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 上午9:37:28
 */
public abstract class TokenVo {
	private Long timestamp;

	/**
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
