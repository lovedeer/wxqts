package com.wxqts.platform.constant;

/**
 * @author zhoulong E-mail:zhoulong1588@163.com
 * @date 2018年4月24日
 */
public class SsoConstants {
	/**
	 * 用户session的key
	 */
	public static final String USER_SESSION_KEY = "username";

	/**
	 * 应用跳转的参数名
	 */
	public static final String REDIRECT_APP_URL_KEY = "redirectUrl";

	/**
	 * 登录地址
	 */
	public static final String LOGIN_URL = "/login";

	/**
	 * 默认登录成功地址
	 */
	public static final String ON_SUCCESS_URL = "/index";

	/**
	 * 无应用访问权限时request设置的属性键
	 */
	public static final String NO_PERMIT_KEY = "noPermit";

	/**
	 * 错误信息的键名
	 */
	public static final String ERROR_MSG_KEY = "errorMsg";

	/**
	 * 返回成功字符串
	 */
	public static final String ON_SUCCESS = "success";

	/**
	 * 返回错误字符串
	 */
	public static final String ON_FAIL = "fail";

	/**
	 * token键名
	 */
	public static final String TOKEN_NAME = "token";

	/**
	 * 加密密钥
	 */
	public static final String SECRET = "secret";

	/**
	 * token过期键名
	 */
	public static final String EXPIRE = "exp";

	/**
	 * token 中的payload键名
	 */
	public static final String PAYLOAD = "payload";

	/**
	 * token cache键名
	 */
	public static final String TOKEN_CACHE = "tokenCache";

	/**
	 * 密码散列算法
	 */
	public static final String ALGORITHM_NAME = "SHA-256";
	
}
