package com.wxqts.platform.bean;

import java.io.Serializable;

/**
 * @author zhoulong E-mail:zhoulong@163.com
 * @date 2018年4月24日
 */
public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String personUniCode;
	private String username;
	private String password;
	private String salt;
	private int userId;

	public String getPersonUniCode() {
		return personUniCode;
	}

	public void setPersonUniCode(String personUniCode) {
		this.personUniCode = personUniCode;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCredentialsSalt() {
		return username + salt;
	}
}
