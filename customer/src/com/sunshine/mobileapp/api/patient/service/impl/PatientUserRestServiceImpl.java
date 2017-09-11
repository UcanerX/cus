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

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
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
import com.sunshine.framework.utils.MD5Utils;
import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.RestStatusEnum;
import com.sunshine.mobileapp.api.patient.dao.PatientUserDao;
import com.sunshine.mobileapp.api.patient.entity.PatientUser;
import com.sunshine.mobileapp.api.patient.service.PatientUserRestService;
import com.sunshine.mobileapp.api.utils.ValidationUtil;

/**
 * @Project: easy_health_client2 
 * @Package: com.sunshine.patient.service.impl
 * @ClassName: PatientUserImpl
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年9月6日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Path("v1/patientUser")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class PatientUserRestServiceImpl implements PatientUserRestService{
	
	private static final Logger logger = LoggerFactory.getLogger(PatientUserRestServiceImpl.class);
	
/*	private  PatientOrderCountCache patientOrderCountCache = SpringContextHolder.getBean(PatientOrderCountCache.class);*/
	
	@Autowired
	private PatientUserDao patientUserDao;
	
	
	/**
	 * 增加用户
	 */
	@POST
	@Path("add")
	@Override
	public RestResponse add(PatientUser patientUser) {
		RestResponse response = new RestResponse();
		Map<String, Object> validResMap = ValidationUtil.notBlank(patientUser, new String[] { "account", "password" });
		Boolean isValid = Boolean.valueOf(validResMap.get(ValidationUtil.CHECK_IS_VALID).toString());
		if(isValid){
			try {
				patientUser.setPassword(MD5Utils.getMd5String16(patientUser.getPassword()));
				patientUser.setCt(new Date());
				patientUserDao.insert(patientUser);
				response.setStatus(RestStatusEnum.OK);
			} catch (NoSuchAlgorithmException e) {
				logger.error("",e);
				e.printStackTrace();
				response.setStatus(RestStatusEnum.ERROR);
				response.setMsg("");
			}catch(Exception e){
				logger.error("",e);
				response.setStatus(RestStatusEnum.ERROR);
				response.setMsg("新增用户失败!");
			}
		
		}else{
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg(validResMap.get(ValidationUtil.CHECK_RES_MSG).toString());
		}
		return response;
	}
	
	/**
	 * 查询用户信息
	 */
	@GET
	@Path("findById/{patientUserId:.*}")
	@Override
	public RestResponse findById(@PathParam("patientUserId") String patientUserId){
		RestResponse response = new RestResponse();
		if(StringUtils.isNotEmpty(patientUserId)){
			PatientUser patientUser = patientUserDao.findById(patientUserId);
			response.setStatus(RestStatusEnum.OK);
			response.setData(patientUser);
		}else{
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg("patientUserId不能为空!");
		}
		return response;
	}
	
	/**
	 * 更新用户信息
	 */
	@POST
	@Path("update")
	@Override
	public RestResponse update( PatientUser patientUser){
		RestResponse response = new RestResponse();
		Map<String, Object> validResMap = ValidationUtil.notBlank(patientUser, new String[] { "id"});
		Boolean isValid = Boolean.valueOf(validResMap.get(ValidationUtil.CHECK_IS_VALID).toString());
		if(isValid){
			patientUser.setPassword(null);
			patientUserDao.update(patientUser);
			response.setStatus(RestStatusEnum.OK);
		}else{
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg("patientUserId不能为空!");
		}
		return response;
	}
	
	
	/**
	 * 查询用户订单数量
	 * @param patientId
	 * @return
	 */
	@GET
	@Path("getOrderCount/{patientId:.*}")
	@Override
	public RestResponse getOrderCount(@PathParam("patientId") String patientId){
		RestResponse response = new RestResponse();
		response.setStatus(RestStatusEnum.OK);
	/*	patientOrderCountCache.incrUnpaid(patientId, 1);
		Map<String,String> map =patientOrderCountCache.getAll(patientId);*/
		Map<String,String> map = new HashMap<String,String>();
		map.put("unpaid", "0");
		response.setData(map);
		return response; 
	}
	
}



	
	
	
	
	