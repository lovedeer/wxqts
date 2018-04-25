package com.wxqts.platform.jwt;

import java.io.Serializable;

import com.wxqts.platform.bean.MemberBean;

/**
 * @author zhoulong E-mail:zhoulong@163.com
 * @date 2018年4月20日
 * @desc 主要用来生成 JWT token
 */
public class UserSubject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private int userId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public UserSubject() {
	};

	public UserSubject(MemberBean user) {
		username = user.getUsername();
		userId = user.getUserId();
	}

}
