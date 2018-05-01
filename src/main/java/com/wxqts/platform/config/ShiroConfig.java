package com.wxqts.platform.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wxqts.platform.constant.SsoConstants;
import com.wxqts.platform.shiro.filter.AppPermissionFilter;
import com.wxqts.platform.shiro.filter.SsoFormAuthenticationFilter;
import com.wxqts.platform.shiro.permission.AppPermissionResolver;
import com.wxqts.platform.shiro.realm.UserRealm;

/**
 * @author zhoulong E-mail:zhoulong@163.com
 * @date 2018年4月24日
 */
@Configuration
public class ShiroConfig {

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager,
			SsoFormAuthenticationFilter ssoFormAuthenticationFilter, AppPermissionFilter appPermissionFilter) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 添加自定义拦截器
		Map<String, Filter> filterMap = new LinkedHashMap<>();
		filterMap.put("authc", ssoFormAuthenticationFilter);
		filterMap.put("perms", appPermissionFilter);
		shiroFilterFactoryBean.setFilters(filterMap);

		// 拦截路径
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("/rpc", "anon");
		map.put("/login", "authc");
		map.put("/app", "authc, perms");
		map.put("/index", "authc");
		map.put("/manage", "anon");
		map.put("/user", "anon");

		shiroFilterFactoryBean.setLoginUrl(SsoConstants.LOGIN_URL);
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl(SsoConstants.ON_SUCCESS_URL);

		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}

	/**
	 * 会话ID生成器
	 * 
	 * @return
	 */
	@Bean(name = "sessionIdGenerator")
	public JavaUuidSessionIdGenerator javaUuidSessionIdGenerator() {
		return new JavaUuidSessionIdGenerator();
	}

	/**
	 * 会话验证调度器
	 * 
	 * @param sessionManager
	 * @return
	 */
	@Bean(name = "sessionValidationScheduler")
	public QuartzSessionValidationScheduler quartzSessionValidationScheduler(DefaultWebSessionManager sessionManager) {
		QuartzSessionValidationScheduler quartzSessionValidationScheduler = new QuartzSessionValidationScheduler();
		quartzSessionValidationScheduler.setSessionValidationInterval(1800000);
		quartzSessionValidationScheduler.setSessionManager(sessionManager);
		return quartzSessionValidationScheduler;
	}

	/**
	 * cookie对象;
	 * 
	 * @return
	 */
	@Bean(name = "sessionIdCookie")
	public SimpleCookie simpleCookie() {
		SimpleCookie simpleCookie = new SimpleCookie("sid");
		simpleCookie.setMaxAge(1800);
		simpleCookie.setHttpOnly(true);
		return simpleCookie;
	}

	/**
	 * 配置Session DAO的操作处理
	 * 
	 * @param sessionIdGenerator
	 * @return
	 */
	@Bean
	public EnterpriseCacheSessionDAO SessionDAO(SessionIdGenerator sessionIdGenerator) {
		EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
		sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
		sessionDAO.setSessionIdGenerator(sessionIdGenerator);
		return sessionDAO;
	}

	@Bean(name = "sessionManager")
	public DefaultWebSessionManager defaultWebSessionManager(EnterpriseCacheSessionDAO sessionDAO,
			SimpleCookie sessionIdCookie) {
		DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
		defaultWebSessionManager.setGlobalSessionTimeout(1800000);
		defaultWebSessionManager.setDeleteInvalidSessions(true);
		// defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
		// defaultWebSessionManager.setSessionValidationScheduler(sessionValidationScheduler);
		defaultWebSessionManager.setSessionDAO(sessionDAO);
		defaultWebSessionManager.setSessionIdCookieEnabled(true);
		defaultWebSessionManager.setSessionIdCookie(sessionIdCookie);
		return defaultWebSessionManager;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager(UserRealm userRealm, DefaultWebSessionManager sessionManager,
			EhCacheManager cacheManager, AppPermissionResolver appPermissionResolver) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm);
		securityManager.setCacheManager(cacheManager);
		securityManager.setSessionManager(sessionManager);
		userRealm.setPermissionResolver(appPermissionResolver);

		return securityManager;
	}

	/**
	 * realm
	 * 
	 * @param matcher
	 * @param ehCacheManager
	 * @return
	 */
	@Bean(name = "userRealm")
	public UserRealm myAuthRealm(HashedCredentialsMatcher matcher, EhCacheManager ehCacheManager) {
		UserRealm userRealm = new UserRealm();
		// 设置密码凭证匹配器
		userRealm.setCredentialsMatcher(matcher);
		// 设置缓存管理器
		userRealm.setCacheManager(ehCacheManager);
		userRealm.setCachingEnabled(true);
		userRealm.setAuthenticationCacheName("authenticationCache");
		userRealm.setAuthenticationCachingEnabled(true);
		userRealm.setAuthorizationCacheName("authorizationCache");
		userRealm.setAuthorizationCachingEnabled(true);

		return userRealm;
	}

	/**
	 * 缓存管理器
	 * 
	 * @return
	 */
	@Bean(name = "ehCacheManager")
	public EhCacheManager ehCacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:shiro-ehcache.xml");
		return cacheManager;
	}

	/**
	 * 密码匹配凭证管理器
	 * 
	 * @return
	 */
	@Bean(name = "hashedCredentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName(SsoConstants.ALGORITHM_NAME);
		hashedCredentialsMatcher.setHashIterations(1);
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}

	@Bean
	public SsoFormAuthenticationFilter ssoFormAuthenticationFilter() {
		return new SsoFormAuthenticationFilter();
	}

	@Bean
	public AppPermissionFilter appPermissionFilter() {
		return new AppPermissionFilter();
	}

	@Bean
	public AppPermissionResolver appPermissionResolver() {
		return new AppPermissionResolver();
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
}
