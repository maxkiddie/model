/**
 * 
 */
package com.ydy.constant;

/**
 * redis静态常量
 * 
 * @author xuzhaojie
 *
 *         2018年12月3日 上午10:08:46
 */
public class RedisConstant {

	public static final int THIRDPARTY_SYSTEM_CACHE_EXPIRE_TIME = 3600;// 第三方系统缓存时间,秒

	public static final String THIRDPARTY_PRE = "SYS:";// 第三方系统前缀

	public static final String REQUEST_SESSION = "REQS:";// http表单提交会话

	public static final String CODE_PRE = "CODE:";// http,code验证码

	public static final int CODE_EXPIRE_TIME = 60;// 验证码存活时间,秒

	public static final int FORM_REPEAT_EXPIRE_TIME = 3;// http会话表单过期时间
}
