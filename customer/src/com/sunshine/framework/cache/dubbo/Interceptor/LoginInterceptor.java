/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年9月6日</p>
 *  <p> Created by x-lan</p>
 *  </body>
 * </html>
 */
package com.sunshine.framework.cache.dubbo.Interceptor;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.fastjson.JSON;
import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.RestStatusEnum;

/**
 * @Project: easy_health_client 
 * @Package: com.sunshine.framework.cache.dubbo
 * @ClassName: LoginInterceptor
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: x-lan
 * @Create Date: 2017年9月6日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class LoginInterceptor implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		HttpServletRequest request = (HttpServletRequest) RpcContext.getContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) RpcContext.getContext().getResponse();
		Enumeration headerNames = request.getHeaderNames();
		String authorization = "";
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			if ("authorization".equals(key)) {
				authorization = request.getHeader(key);
			}
		}
		System.out.println(authorization);
		if (StringUtils.isBlank(authorization)) {

			RestResponse app = new RestResponse();
			app.setStatus(RestStatusEnum.FAIL);
			//app.setCode(app.getStatus().getValue());
			app.setMsg("token已过期");
			app.setData(null);
			print(JSON.toJSONString(app), response);
			//return new RpcResult();

			//response.setContentType("text/html; charset=utf-8");
			//response.setStatus(401);
			return new RpcResult();
		} else {
			return invoker.invoke(invocation);
		}

	}

	protected void print(String str, HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			response.setContentType("text/html; charset=utf-8");
			writer = response.getWriter();
			writer.print(str);
		} catch (Exception e) {

		} finally {
			writer.close();
		}
	}
}
