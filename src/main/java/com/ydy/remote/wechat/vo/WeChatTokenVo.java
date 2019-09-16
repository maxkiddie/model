/**
 * 
 */
package com.ydy.remote.wechat.vo;

import java.util.Date;

/**
 * @author xuzhaojie
 *
 *         2019年6月3日 下午5:20:34
 */
public class WeChatTokenVo {
	private String accessToken;
	private Long expiresIn;
	private Date createTime;

	public WeChatTokenVo() {
		this.createTime = new Date();
	}

	public boolean isExpire() {
		Long now = System.currentTimeMillis();
		Long validDate = getCreateTime().getTime() + ((expiresIn - 100L) * 1000);// -100秒是为了获取token的网络延迟
		return now > validDate;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken
	 *            the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the expiresIn
	 */
	public Long getExpiresIn() {
		return expiresIn;
	}

	/**
	 * @param expiresIn
	 *            the expiresIn to set
	 */
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

}
