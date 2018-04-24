package com.wxqts.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxqts.platform.bean.GsjCodeBean;
import com.wxqts.platform.service.SuccService;

/**
 * 
 * @author daijt 社会统一信用代码：Social unified credit code(succ)
 */

@Controller
@RequestMapping("/succ")
public class SuccController {
	@Autowired
	private SuccService succService;

	@RequestMapping("/query")
	@ResponseBody
	public List<GsjCodeBean> query(String unitName) {
		List<GsjCodeBean> res = succService.query(unitName);
		return res;
	}
}
