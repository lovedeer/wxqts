package com.wxqts.platform.service;

import java.util.List;

import com.wxqts.platform.bean.GsjCodeBean;

/**
 * @author daijt
 * @time 2017年12月27日 下午4:29:43
 * @desc 
 * 
 */
public interface SuccService {
	/**
	 * 根据单位名称查询社会统一信用代码
	 * @param unitName
	 * @return
	 */
	public List<GsjCodeBean> query(String unitName);
}
