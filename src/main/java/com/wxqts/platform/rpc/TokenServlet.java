package com.wxqts.platform.rpc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;

/**
 * @author zhoulong E-mail:zhoulong1588@163.com
 * @date 2018年4月23日 
 */
@WebServlet(name="tokenServlet",urlPatterns="/rpc") 
public class TokenServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private JsonRpcServer rpcServer = null;

    public TokenServlet() {
        super();
        rpcServer = new JsonRpcServer(new ObjectMapper(), new JsonRpcTokenServiceImpl(), JsonRpcTokenService.class);
    }

    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	rpcServer.handle(request, response);
    }
}
