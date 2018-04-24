package com.wxqts.platform.dao;

import java.util.List;

import com.wxqts.platform.bean.GsjCodeBean;

/**
 * @author daijt
 * @time 2017年12月27日 下午4:29:05
 * @desc 
 * 
 */
public interface SuccDao {
	public List<GsjCodeBean> query(String unitName);
}
