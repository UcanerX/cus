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
package com.sunshine.mobileapp.api.dept.service.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.RestStatusEnum;
import com.sunshine.mobileapp.api.dept.dao.DeptDao;
import com.sunshine.mobileapp.api.dept.entity.Dept;
import com.sunshine.mobileapp.api.dept.service.DeptRestService;

/**
* @Package：com.sunshine.mobileapp.api.hospital.service.impl   
* @ClassName：HospitalRestServiceImpl   
* @Description：   <p> 科室相关业务实现类</p>
* @Author： - DaoDou 
* @CreatTime：2017年9月6日 下午4:18:55   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
@Path("v1/dept")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class DeptRestServiceImpl implements DeptRestService{
	
	
	@Autowired
	private DeptDao deptDao;
	
	@GET
	@Path("/findDeptList")
	public RestResponse findDeptList() {
		RestResponse response = new RestResponse();
		response.setStatus(RestStatusEnum.OK);
		List<Dept> allDeptList = deptDao.findAllDeptList();
		response.setData(allDeptList);
		return response;
	}
}