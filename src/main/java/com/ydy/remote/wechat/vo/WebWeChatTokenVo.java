/**
 * 
 */
package com.ydy.remote.wechat.vo;

/**
 * @author xuzhaojie
 *
 *         2019年6月3日 下午6:27:02
 * 
 *         { "access_token":"ACCESS_TOKEN", "expires_in":7200,
 *         "refresh_token":"REFRESH_TOKEN", "openid":"OPENID", "scope":"SCOPE" }
 */
public class WebWeChatTokenVo extends WeChatTokenVo {

	private String refreshToken;
	private String openid;
	private String scope;

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken
	 *            the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * @return the openid
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * @param openid
	 *            the openid to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope
	 *            the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

}
