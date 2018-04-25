package com.wxqts.platform.shiro.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;

/**
 * @author zhoulong E-mail:zhoulong@163.com
 * @date 2018年4月19日
 */
public class AppPermissionResolver implements PermissionResolver {

	@Override
	public Permission resolvePermission(String permissionString) {
		return new AppPermission(permissionString);
	}

}
