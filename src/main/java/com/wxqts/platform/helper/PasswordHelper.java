package com.wxqts.platform.helper;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.wxqts.platform.bean.MemberBean;
import com.wxqts.platform.constant.SsoConstants;

/**
 * @author zhoulong E-mail:zhoulong@163.com
 * @date 2018年4月23日
 */
public class PasswordHelper {

	/**
	 * 返回散列密码
	 * 
	 * @param user
	 *            用户实例
	 * @param hashIterations
	 *            迭代次数
	 */
	public static void encryptPassword(MemberBean user, int hashIterations) {
		RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		user.setSalt(randomNumberGenerator.nextBytes().toHex());

		String newPassword = new SimpleHash(
				// 加密算法
				SsoConstants.ALGORITHM_NAME,
				// 密码
				user.getPassword(),
				// salt盐 username + salt
				ByteSource.Util.bytes(user.getCredentialsSalt()),
				// 迭代次数
				hashIterations).toHex();

		user.setPassword(newPassword);
	}

	public static void main(String[] args) {
		MemberBean user = new MemberBean();
		user.setUsername("zhoulong");
		user.setPassword("123456");
		PasswordHelper.encryptPassword(user, 1);
		System.out.println(user.getSalt());
		System.out.println(user.getPassword());

	}
}
