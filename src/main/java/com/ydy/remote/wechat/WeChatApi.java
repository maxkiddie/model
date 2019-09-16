/**
 * 
 */
package com.ydy.remote.wechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydy.remote.wechat.exception.WeChatException;
import com.ydy.remote.wechat.vo.WeChatTokenVo;
import com.ydy.remote.wechat.vo.WeChatUserInfo;
import com.ydy.remote.wechat.vo.WebWeChatTokenVo;
import com.ydy.utils.HttpUtil;

/**
 * @author xuzhaojie
 *
 *         2019年6月3日 下午4:44:50
 */
public class WeChatApi {

	private final static Logger log = LoggerFactory.getLogger(WeChatApi.class);

	public static String appId = "wxac2e97087946bf97";
	public static String appSecret = "dbcb9d51af648df33ad745aab30cfc45";
	public static String redirectUri = "http://www.baidu.com";

	private static String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=${APPID}&secret=${APPSECRET}";

	private static String GET_WEB_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=${APPID}&redirect_uri=${REDIRECT_URI}&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

	private static String GET_WEB_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=${APPID}&secret=${APPSECRET}&code=${code}&grant_type=authorization_code";

	private static String GET_WEB_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=${ACCESS_TOKEN}&openid=${OPENID}&lang=zh_CN";

	public static WeChatTokenVo getAccessToken() {
		String url = GET_ACCESS_TOKEN_URL.replace("${APPID}", appId).replace("${APPSECRET}", appSecret);
		String result = HttpUtil.doGet(url);
		if (result == null || "".equals(result)) {
			log.error("微信远程网络出现错误:" + GET_ACCESS_TOKEN_URL);
			throw new WeChatException("微信远程网络出现错误");
		}
		JSONObject json = JSONObject.parseObject(result);
		if (json.containsKey("errcode")) {
			Long errcode = json.getLong("errcode");
			String msg = json.getString("errmsg");
			log.error("微信请求错误,返回结果:" + result);
			throw new WeChatException(errcode, msg);
		}
		WeChatTokenVo vo = new WeChatTokenVo();
		vo.setAccessToken(json.getString("access_token"));
		vo.setExpiresIn(json.getLong("expires_in"));
		return vo;
	}

	public static String getLoginUrl() {
		String url = GET_WEB_CODE.replace("${APPID}", appId).replace("${REDIRECT_URI}", redirectUri);
		return url;
	}

	public static WebWeChatTokenVo getWebAccessToken(String code) {
		if (code == null || "".equals(code)) {
			log.error("微信登录code是必填参数");
			throw new WeChatException("微信登录code是必填参数");
		}
		String url = GET_WEB_ACCESS_TOKEN_URL.replace("${APPID}", appId).replace("${APPSECRET}", appSecret)
				.replace("${code}", code);
		String result = HttpUtil.doGet(url);
		if (result == null || "".equals(result)) {
			log.error("微信远程网络出现错误:" + GET_WEB_ACCESS_TOKEN_URL);
			throw new WeChatException("微信远程网络出现错误");
		}
		JSONObject json = JSONObject.parseObject(result);
		if (json.containsKey("errcode")) {
			Long errcode = json.getLong("errcode");
			String msg = json.getString("errmsg");
			log.error("微信请求错误,返回结果:" + result);
			throw new WeChatException(errcode, msg);
		}
		WebWeChatTokenVo vo = new WebWeChatTokenVo();
		vo.setAccessToken(json.getString("access_token"));
		vo.setExpiresIn(json.getLong("expires_in"));
		vo.setRefreshToken(json.getString("refresh_token"));
		vo.setOpenid(json.getString("openid"));
		vo.setScope(json.getString("scope"));
		return vo;
	}

	public static WeChatUserInfo getWebAccessToken(WebWeChatTokenVo webWeChatTokenVo) {
		if (webWeChatTokenVo == null || webWeChatTokenVo.getAccessToken() == null
				|| webWeChatTokenVo.getOpenid() == null) {
			log.error("微信用户信息获取请求参数缺失");
			throw new WeChatException("微信用户信息获取请求参数缺失");
		}
		String url = GET_WEB_USER_INFO.replace("${ACCESS_TOKEN}", webWeChatTokenVo.getAccessToken())
				.replace("${OPENID}", webWeChatTokenVo.getOpenid());
		String result = HttpUtil.doGet(url);
		if (result == null || "".equals(result)) {
			log.error("微信远程网络出现错误:" + GET_WEB_USER_INFO);
			throw new WeChatException("微信远程网络出现错误");
		}
		log.info("微信返回:" + result);
		JSONObject json = JSONObject.parseObject(result);
		if (json.containsKey("errcode")) {
			Long errcode = json.getLong("errcode");
			String msg = json.getString("errmsg");
			log.error("微信请求错误,返回结果:" + result);
			throw new WeChatException(errcode, msg);
		}
		WeChatUserInfo info = JSONObject.parseObject(result, WeChatUserInfo.class);
		return info;
	}

	public static void main(String[] args) {
		System.out.println(JSON.toJSON(getLoginUrl()));
		System.out.println(JSON.toJSON(getWebAccessToken(getWebAccessToken("011GvPOT1C1jM41prRNT1XQOOT1GvPO1"))));
	}
}
