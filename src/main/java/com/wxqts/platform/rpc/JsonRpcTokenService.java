package com.wxqts.platform.rpc;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

/**
 * @author zhoulong E-mail:zhoulong1588@163.com
 * @date 2018年4月23日
 */
@JsonRpcService("/rpc")
public interface JsonRpcTokenService {

	/**
	 * 验证token正确性
	 * 
	 * @param token
	 * @return
	 */
	public String verifyToken(@JsonRpcParam(value = "token") String token);
}
