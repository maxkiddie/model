/**
 * 
 */
package com.ydy.configuration;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author xuzhaojie
 *
 *         2019年6月3日 下午4:57:45
 */
//@Component
public class WeChatConfig {
	@Value("${wechat_AppId}")
	private String appId;
	@Value("${wechat_AppSecret}")
	private String appSecret;
	@Value("${redirectUri}")
	private String redirectUri;

	/**
	 * @return the redirectUri
	 */
	public String getRedirectUri() {
		return redirectUri;
	}

	/**
	 * @param redirectUri
	 *            the redirectUri to set
	 */
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 *            the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @return the appSecret
	 */
	public String getAppSecret() {
		return appSecret;
	}

	/**
	 * @param appSecret
	 *            the appSecret to set
	 */
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

}
