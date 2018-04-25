package com.wxqts.platform.shiro.permission;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.Permission;

/**
 * @author zhoulong E-mail:zhoulong@163.com
 * @date 2018年4月19日
 */
public class AppPermission implements Permission {
	private List<String> appList;

	public AppPermission(List<String> list) {
		appList = list;
	}

	public AppPermission(String... list) {
		appList = new ArrayList<String>();
		for (String appUrl : list) {
			appList.add(appUrl);
		}
	}

	@Override
	public boolean implies(Permission p) {
		if (!(p instanceof AppPermission)) {
			return false;
		}
		AppPermission permission = (AppPermission) p;
		List<String> list = permission.appList;
		for (String appUrl : list) {
			if (!appList.contains(appUrl)) {
				return false;
			}
		}
		return true;
	}

}
