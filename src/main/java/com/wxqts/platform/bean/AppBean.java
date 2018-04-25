package com.wxqts.platform.bean;

import java.io.Serializable;

/**
 * @author zhoulong E-mail:zhoulong@163.com
 * @date 2018年4月24日
 */
public class AppBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String appName;
	private int appId;
	private String appUrl;
	private String appDesc;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

}
