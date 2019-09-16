/**
 * 
 */
package com.ydy.constant;

/**
 * 系统静态常量
 * 
 * @author xuzhaojie
 *
 *         2018年12月3日 上午10:30:45
 */
public class SystemConstant {

	public static String HTTP_HOST = "";

	public static String FROM_EMAIL = "";

	public static final String MANAGER_DEFAULT_PWD = "123456a";// 管理员cookies

	public static final String DEFAULT_CODE = "1234";// 管理员cookies

	public static final String ADM_TOKEN = "ADM_TOKEN";// 管理员token的key
	public static final String USER_TOKEN = "USER_TOKEN";// 用户token的key

	public static final String SESSION_CODE = "V_CODE";// 验证码key
	public static final String SESSION_EMAIL = "E_M";// 邮件key

	public static final String LOCAL_COIN_TYPE = "USD";// 本地币种，美元

	public static final Integer NOT_DELETE = 0;// 没删除状态

	public static final Integer HAS_DELETE = 1;// 已删除状态

	// 接口关闭状态
	public static final Integer SWITCH_STATUS_CLOSED = 0;

	public static final Integer SWITCH_STATUS_OPEN = 1;

	// 状态可用
	public static final Short USE_STATUS_ON = 1;

	public static final Short USE_STATUS_OFF = 0;
	// 是否上架
	public static final Integer SPU_ON = 1;

	public static final Integer SPU_OFF = 0;

	// 邀请码是否已经使用
	public static final Integer USE_YES = 1;

	public static final Integer USE_NO = 0;
	// 是否会员
	// 是会员
	public static final Integer VIP_YES = 1;
    //不是会员
	public static final Integer VIP_NO = 0;
	// 想成为会员
	public static final Integer VIP_WANT = -1;
	// 是否置顶
	public static final Integer UP_ON = 1;

	public static final Integer UP_OFF = 0;
	// 是否加急
	public static final Integer URGENT_ON = 1;

	public static final Integer URGENT_OFF = 0;
	// 男女
	public static final Integer GENDER_MAN = 1;

	public static final Integer GENDER_MALE = 0;

	public static final String SUPER_ADMIN = "kiddie";

	public static final String SUPER_ADMIN_PWD = "17df6be9b6527091345d11f802af61e8";

}
