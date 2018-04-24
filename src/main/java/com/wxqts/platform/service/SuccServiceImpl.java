package com.wxqts.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxqts.platform.bean.GsjCodeBean;
import com.wxqts.platform.dao.SuccDao;

/**
 * @author daijt
 * @time 2017年12月27日 下午4:31:09
 * @desc
 * 
 */
@Service("succService")
public class SuccServiceImpl implements SuccService {

	@Autowired
	private SuccDao succDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wxqts.platform.service.SuccService#query(java.lang.String)
	 */
	@Override
	public List<GsjCodeBean> query(String unitName) {
		return succDao.query(unitName);
	}

}
