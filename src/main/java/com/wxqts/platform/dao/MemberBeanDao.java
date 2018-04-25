package com.wxqts.platform.dao;
/**
* @author 周龙  E-mail:zhoulong@163.com
* @version 创建时间：2018年4月24日 
*/

import java.util.List;

import com.wxqts.platform.bean.MemberBean;



public interface MemberBeanDao {

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username 用户名
	 * 
	 * @return MemberBean 用户
	 */
	MemberBean getMemberByUsername(String username);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username 用户名
	 * 
	 * @return List<MemberBean> 用户列表
	 */
	List<MemberBean> queryMemberByUsername(String username);

	
	/** 查找所有用户
	 * @return 所有用户列表
	 */
	List<MemberBean> queryAllMembers();

	/**添加用户
	 * @param t 用户对象
	 */
	void save(MemberBean t);

	/**更新用户
	 * @param t 用户对象
	 */
	void update(MemberBean t);

	/**删除用户
	 * @param t 用户对象
	 */
	void remove(MemberBean t);

	/**根据用户ID查找用户
	 * @param MemberBeanId 用户ID
	 * @return MemberBean 用户
	 */
	MemberBean get(int MemberBeanId);
}