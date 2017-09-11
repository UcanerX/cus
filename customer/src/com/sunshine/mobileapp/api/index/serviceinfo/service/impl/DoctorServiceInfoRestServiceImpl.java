/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年10月25日</p>
 *  <p> Created by Allen</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.index.serviceinfo.service.impl;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.RestStatusEnum;
import com.sunshine.mobileapp.api.index.serviceinfo.dao.DoctorServiceInfoDao;
import com.sunshine.mobileapp.api.index.serviceinfo.entity.DoctorServiceInfo;
import com.sunshine.mobileapp.api.index.serviceinfo.service.DoctorServiceInfoRestService;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.doctor.service.service.impl
 * @ClassName: DoctorServiceInfoServiceImpl
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: MuGua
 * @Create Date: 2016年10月25日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Path("v1/service")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class DoctorServiceInfoRestServiceImpl  implements DoctorServiceInfoRestService {

	@Autowired
	DoctorServiceInfoDao doctorServiceInfoDao;

	@GET
	@Path("/getServiceListByUserId/{userId : .*}")
	@Override
	public RestResponse getServiceListByUserId(@PathParam("userId") String userId) {
		RestResponse response = new RestResponse();
		List<DoctorServiceInfo> serviceListByUserIdList;
		try {
			serviceListByUserIdList = doctorServiceInfoDao.getServiceListByUserId(userId);
			response.setStatus(RestStatusEnum.OK);
			response.setData(serviceListByUserIdList);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(RestStatusEnum.ERROR);
		}
		
		return response;
	}

	@Override
	public List<DoctorServiceInfo> getServiceListByUserIds(
			List<String> userIdList) {
		
		return null;
	}

	@Override
	public List<DoctorServiceInfo> getServiceListCache(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoctorServiceInfo getDoctorServiceInfoByServiceIdAndUserId(Map params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DoctorServiceInfo> findServiceInfoByStatus(Integer status) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
