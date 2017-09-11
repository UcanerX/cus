/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年9月6日</p>
 *  <p> Created by 钟乳石</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.patient.service.impl;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.RestStatusEnum;
import com.sunshine.mobileapp.api.patient.dao.InquireUserDao;
import com.sunshine.mobileapp.api.patient.entity.InquireUser;
import com.sunshine.mobileapp.api.patient.service.InquireUserRestService;
import com.sunshine.mobileapp.api.utils.ValidationUtil;

/**
* @Package：com.sunshine.mobileapp.api.patient.service.impl   
* @ClassName：InquireUserRestServiceImpl   
* @Description：   <p> 问诊人相关操作接口</p>
* @Author： - DaoDou 
* @CreatTime：2017年9月10日 下午4:08:56   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
@Path("v1/inquireUser")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class InquireUserRestServiceImpl implements InquireUserRestService{
	
	private static final Logger logger = LoggerFactory.getLogger(InquireUserRestServiceImpl.class);
	
	@Autowired
	private InquireUserDao inquireUserDao;
	
	@POST
	@Path("add")
	@Override
	public RestResponse add(InquireUser InquireUser) {
		RestResponse response = new RestResponse();
		Map<String, Object> validResMap = ValidationUtil.notBlank(InquireUser, new String[] { "account", "password" });
		Boolean isValid = Boolean.valueOf(validResMap.get(ValidationUtil.CHECK_IS_VALID).toString());
		if(isValid){
			try {
				inquireUserDao.insert(InquireUser);
				response.setStatus(RestStatusEnum.OK);
			}catch(Exception e){
				logger.error("inquireUser：add",e);
				response.setStatus(RestStatusEnum.ERROR);
				response.setMsg("添加失败!");
			}
		
		}else{
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg(validResMap.get(ValidationUtil.CHECK_RES_MSG).toString());
		}
		return response;
	}

	
	@GET
	@Path("findById/{inquiretUserId:.*}")
	@Override
	public RestResponse findById(@PathParam("inquiretUserId") String inquiretUserId){
		RestResponse response = new RestResponse();
		if(StringUtils.isNotEmpty(inquiretUserId)){
			InquireUser inquireUser = inquireUserDao.findById(inquiretUserId);
			response.setStatus(RestStatusEnum.OK);
			response.setData(inquireUser);
		}else{
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg("patientUserId不能为空!");
		}
		return response;
	}
	

	@POST
	@Path("update")
	@Override
	public RestResponse update(InquireUser inquireUser){
		RestResponse response = new RestResponse();
		Map<String, Object> validResMap = ValidationUtil.notBlank(inquireUser, new String[] { "id"});
		Boolean isValid = Boolean.valueOf(validResMap.get(ValidationUtil.CHECK_IS_VALID).toString());
		if(isValid){
			inquireUserDao.update(inquireUser);
			response.setStatus(RestStatusEnum.OK);
		}else{
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg("inquiretUserId不能为空!");
		}
		return response;
	}
	
}