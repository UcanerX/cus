package com.sunshine.framework.filter.auth;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.RestStatusEnum;

@Priority(5000)
public class AuthRequestFilter implements ContainerRequestFilter, ContainerResponseFilter {
	private static final Logger logger = LoggerFactory.getLogger(AuthRequestFilter.class);

	/**
	 * 响应输入
	 */

	public void filter(ContainerRequestContext requestContext) throws IOException {

		//System.out.println(requestContext.getUriInfo().getRequestUri());
		//System.out.println(requestContext.getUriInfo().getAbsolutePath());
		//System.out.println(requestContext.getUriInfo().getPath());
		//System.out.println(requestContext.getUriInfo().getBaseUri());

		//		http://127.0.0.1:9091/services/v1/register/user/13800990090/123456
		//			http://127.0.0.1:9091/services/v1/register/user/13800990090/123456
		//			/services/v1/register/user/13800990090/123456
		//			http://127.0.0.1:9091/

		Map resMap = checkHeaderParam(requestContext);
		Boolean isValid = (Boolean) resMap.get("isValidHeader");
		//if (isValid.booleanValue()) {
		RestResponse restResponse = null;
		String method = requestContext.getMethod();
		System.out.println("method=" + method);
		String account = (String) requestContext.getHeaders().getFirst("Account");
		String authorization = (String) requestContext.getHeaders().getFirst("Authorization");
		String configAuthCode = "aba65148a5e844538322e597f6ac6cef";
		//		if (StringUtils.isEmpty(configAuthCode)) {
		//			restResponse = new RestResponse();
		//			restResponse.setStatus(RestStatusEnum.ERROR);
		//			restResponse.setMsg("系统未授权,请联系API提供方");
		//		} else {
		//			try {
		//				String verifyAuthorCode = null;
		//
		//				verifyAuthorCode = getMd5String32(configAuthCode.concat(account));
		//
		//				if (! ( authorization.equals(verifyAuthorCode) )) {
		//					restResponse = new RestResponse();
		//					restResponse.setStatus(RestStatusEnum.FAIL);
		//					restResponse.setMsg("权限验证失败!");
		//				}
		//			} catch (NoSuchAlgorithmException e) {
		//				restResponse = new RestResponse();
		//				restResponse.setStatus(RestStatusEnum.ERROR);
		//				restResponse.setMsg("系统错误,请联系API提供方");
		//				logger.error("error:configAuthCode:{},sortParam:{}生成MD5失败", configAuthCode, account);
		//			}
		//		}

		if (restResponse != null)
			requestContext.abortWith(Response.status(401).entity(restResponse).build());
		//	} else {
		//		requestContext.abortWith(Response.status(401).entity(resMap.get("inValidResp")).build());
		//	}
	}

	private Map<String, Object> checkHeaderParam(ContainerRequestContext requestContext) {
		Map resMap = new HashMap();
		String account = (String) requestContext.getHeaders().getFirst("Account");
		String msg = null;
		Boolean isValid = Boolean.valueOf(true);
		if (StringUtils.isEmpty(account)) {
			isValid = Boolean.valueOf(false);
			msg = "Headers中缺失参数:".concat("Account");
		}

		if (isValid.booleanValue()) {
			String authorization = (String) requestContext.getHeaders().getFirst("Authorization");
			if (StringUtils.isEmpty(authorization)) {
				msg = "Headers中缺失参数:".concat("Authorization");
				isValid = Boolean.valueOf(false);
			}
		}
		resMap.put("isValidHeader", isValid);
		if (! ( isValid.booleanValue() )) {
			RestResponse restResponse = new RestResponse();
			restResponse.setStatus(RestStatusEnum.ERROR);
			restResponse.setMsg(msg);
			resMap.put("inValidResp", restResponse);
		}
		return resMap;
	}

	private static String getMd5String32(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		byte[] b = md.digest();

		StringBuffer buf = new StringBuffer();
		for (int offset = 0; offset < b.length; ++offset) {
			int i = b[offset];

			if (i < 0) {
				i += 256;
			}
			if (i < 16) {
				buf.append("0");
			}
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}

	/** (non-Javadoc)
	 * 响应输出
	 */
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "X-Requested-With,Content-Type,Content-Length,Authorization");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,PATCH,OPTIONS");
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");

	}
}