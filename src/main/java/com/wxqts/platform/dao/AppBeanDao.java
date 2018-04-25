package com.wxqts.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxqts.platform.bean.AppBean;


/**
 * @author zhoulong E-mail:zhoulong@163.com
 * @date 2018年4月24日
 */
public interface AppBeanDao {

	/**
	 * 根据应用id查找
	 * 
	 * @param AppBeanId
	 *            应用Id
	 * @return AppBean
	 */
	AppBean getAppBeanByAppBeanId(int AppBeanId);

	/**
	 * 根据应用地址查找
	 * 
	 * @param AppBeanUrl
	 *            应用地址
	 * @return AppBean
	 */
	AppBean getAppBeanByAppBeanUrl(String AppBeanUrl);

	/**
	 * 根据用户名查找允许访问的所有应用集合
	 * 
	 * @param username
	 *            用户名
	 * @return 应用集合
	 */
	List<String> getAppUrlByUsername(@Param("username") String username);
}
