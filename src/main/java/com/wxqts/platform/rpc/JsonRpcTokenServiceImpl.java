package com.wxqts.platform.rpc;

import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.wxqts.platform.jwt.JwtUtil;

/**
 * @author zhoulong E-mail:zhoulong1588@163.com
 * @date 2018年4月23日
 */
@Service
@AutoJsonRpcServiceImpl
public class JsonRpcTokenServiceImpl implements JsonRpcTokenService {

	@Override
	public String verifyToken(String token) {
		return String.valueOf(JwtUtil.verify(token));
	}
}
