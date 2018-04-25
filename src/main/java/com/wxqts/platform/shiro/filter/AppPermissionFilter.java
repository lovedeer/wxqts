package com.wxqts.platform.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wxqts.platform.constant.SsoConstants;

/**
 * @author zhoulong E-mail:zhoulong1588@163.com
 * @date 2018年4月19日
 */
public class AppPermissionFilter extends PermissionsAuthorizationFilter {
	private static final Logger logger = LoggerFactory.getLogger(AppPermissionFilter.class);

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {

		Subject subject = getSubject(request, response);
		String redirectUrlKey = SsoConstants.REDIRECT_APP_URL_KEY + "=";
		String perm = WebUtils.toHttp(request).getQueryString().substring(redirectUrlKey.length());

		boolean isPermitted = true;
		if (perm != null) {
			if (!subject.isPermitted(perm)) {
				//无权限也返回true，好执行spring的filter
//				isPermitted = false;
				if (logger.isDebugEnabled()) {
					logger.debug("no permit on " + perm);
				}
				request.setAttribute(SsoConstants.NO_PERMIT_KEY, perm);
			}
			
		}

		return isPermitted;
	}
}
