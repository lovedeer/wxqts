package com.wxqts.platform.service;
/**
* @author zhoulong  E-mail:zhoulong@163.com
* @date 2018年4月17日
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxqts.platform.bean.MemberBean;
import com.wxqts.platform.dao.AppBeanDao;
import com.wxqts.platform.dao.MemberBeanDao;

@Service("memberService")
public class MemberService {

	private MemberBeanDao userDao;
	private AppBeanDao appDao;

	@Autowired
	public void setMemberDao(MemberBeanDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setAppDao(AppBeanDao appDao) {
		this.appDao = appDao;
	}

	/**
	 * 注册一个新用户
	 *
	 * @param user
	 *            用户
	 */
	public void register(MemberBean user) throws Exception {
		MemberBean u = this.getMemberByUsername(user.getUsername());
		if (u != null) {
			throw new RuntimeException("用户名已经存在");
		} else {
			userDao.save(user);
		}
	}

	/**
	 * 更新用户
	 *
	 * @param user
	 *            用户
	 */
	public void update(MemberBean user) {
		userDao.update(user);
	}

	/**
	 * 根据用户名/密码查询 Member对象
	 *
	 * @param userName
	 *            用户名
	 * @return Member
	 */
	public MemberBean getMemberByUsername(String userName) {
		return userDao.getMemberByUsername(userName);
	}

	/**
	 * 根据userId加载Member对象
	 *
	 * @param userId
	 *            用户ID
	 * @return Member
	 */
	public MemberBean getMemberById(int userId) {
		return userDao.get(userId);
	}

	/**
	 * 根据用户名为条件，执行模糊查询操作
	 *
	 * @param userName
	 *            查询用户名
	 * @return 所有用户名前导匹配的userName的用户
	 */
	public List<MemberBean> queryMemberByUsername(String userName) {
		return userDao.queryMemberByUsername(userName);
	}

	/**
	 * 获取所有用户
	 *
	 * @return 所有用户
	 */
	public List<MemberBean> getAllMembers() {
		return userDao.queryAllMembers();
	}

	
	/**
	 * 根据用户名获取所有可以访问的应用列表
	 * @param username 用户名
	 * @return 应用列表
	 */
	public List<String> getAppUrlByUsername(String username){
		return appDao.getAppUrlByUsername(username);
	}

}
