/**
 * <html>
 * <body>
 *  <P> Copyright 2014-2017 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年8月31日</p>
 *  <p> Created by wumingzi/yu.ce@foxmail.com</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.register.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.RestStatusEnum;
import com.sunshine.mobileapp.api.dto.RegParamsVo;
import com.sunshine.mobileapp.api.register.service.RegisterRestService;
import com.sunshine.mobileapp.api.utils.ValidationUtil;
import com.sunshine.platform.security.entity.User;

/**
 * @Project YiChenTong_Server
 * @Package com.sunshine.mobileapp.api.register.service.impl
 * @ClassName RegisterRestServiceImpl.java
 * @Description
 * @JDK version used 1.8
 * @Author 于策/yu.ce@foxmail.com
 * @Create Date 2017年8月31日
 * @modify By
 * @modify Date
 * @Why&What is modify
 * @Version 1.0
 */
@Path("v1/register")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class RegisterRestServiceImpl implements RegisterRestService {

	@GET
	@Path("user/{account : .*}/{id:.*}")
	@Override
	public RestResponse getUser(@PathParam("account") String account, @PathParam("id") String id) {
		RestResponse response = new RestResponse();
		User user = new User();
		user.setId(id);
		user.setName("于策");
		response.setStatus(RestStatusEnum.OK);
		response.setData(user);
		return response;
	}

	@POST
	@Path("getHospitals")
	@Override
	public RestResponse getHospitals(RegParamsVo vo) {
		// TODO Auto-generated method stub
		RestResponse response = new RestResponse();

		// 验证参数
		Map<String, Object> validResMap = ValidationUtil.notBlank(vo, new String[] { "appId", "appCode", "cityCode", "account" });
		Boolean isValid = Boolean.valueOf(validResMap.get(ValidationUtil.CHECK_IS_VALID).toString());
		if (isValid) {
			// 获取定位城市名称
			String cityCode = vo.getCityCode();
			String appId = vo.getAppId();
			String appCode = vo.getAppCode();
			String account = vo.getAccount();

			// 查询医院列表
			List<User> hospitals = new ArrayList<User>();

			User user = new User();
			user.setAccount(account);
			user.setId(appId);
			user.setName(appCode);
			hospitals.add(user);
			response.setStatus(RestStatusEnum.OK);
			response.setData(hospitals);
		} else {
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg(validResMap.get(ValidationUtil.CHECK_RES_MSG).toString());
		}

		return response;
	}
}
