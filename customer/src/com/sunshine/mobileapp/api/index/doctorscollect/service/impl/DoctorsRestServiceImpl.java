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
package com.sunshine.mobileapp.api.index.doctorscollect.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.RestStatusEnum;
import com.sunshine.mobileapp.api.index.doctorscollect.dao.DoctorUserDao;
import com.sunshine.mobileapp.api.index.doctorscollect.service.DoctorsRestService;
import com.sunshine.mobileapp.api.index.doctorscollect.vo.DoctorsParamsVo;
import com.sunshine.mobileapp.api.index.doctorscollect.vo.PageParamsVo;
import com.sunshine.mobileapp.api.index.serviceinfo.dao.DoctorServiceInfoDao;
import com.sunshine.mobileapp.api.index.serviceinfo.entity.DoctorServiceInfo;

/**
* @Package：com.sunshine.mobileapp.api.index.doctorscollect.service.impl   
* @ClassName：DoctorsRestServiceImpl   
* @Description：   <p> 名医汇 服务接口实现类</p>
* @Author： - DaoDou 
* @CreatTime：2017年9月6日 上午11:08:22   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
@Path("v1/doctors")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class DoctorsRestServiceImpl implements DoctorsRestService{
	
	@Autowired
	private DoctorUserDao doctorUserDao;
	
	@Autowired
	private DoctorServiceInfoDao doctorServiceInfoDao;

	@GET
	@Path("/doctorHomePageDetail/{doctorId : .*}")
	@Override
	public RestResponse doctorHomePageDetail(@PathParam("doctorId") String doctorId) {
		RestResponse response = new RestResponse();
		DoctorsParamsVo doctorDetail = doctorUserDao.findDoctorDetailById(doctorId);
		response.setData(doctorDetail);
		response.setStatus(RestStatusEnum.OK);
		return response;
	}
	
	@GET
	@Path("/findFamousDoctorList/{pageNum : .*}/{pageSize:.*}")
	public RestResponse findFamousDoctorList(@PathParam("pageNum") String pageNum, @PathParam("pageSize") String pageSize) {
		RestResponse response = new RestResponse();
		List<DoctorsParamsVo> famousDoctorList;
		try {
			Integer pageNumInt = (pageNum==null||"".equals(pageNum))?1:Integer.parseInt(pageNum);
			Integer pageSizeInt = (pageSize==null||"".equals(pageSize))?5:Integer.parseInt(pageSize);;
			PageHelper.startPage(pageNumInt,pageSizeInt);
	        famousDoctorList = doctorUserDao.findFamousDoctorList();
	        PageInfo<DoctorsParamsVo> pageFamousDoctor = new PageInfo<DoctorsParamsVo>(famousDoctorList);
	        DoctorsParamsVo dpv = new DoctorsParamsVo();
	        List<DoctorsParamsVo> doctorParamsList = new ArrayList<DoctorsParamsVo>();
	        List<DoctorsParamsVo> list = pageFamousDoctor.getList();
	        for (DoctorsParamsVo doctorsParamsVo : list) {
	        	List<DoctorServiceInfo> serviceListByUserIdList = doctorServiceInfoDao.getServiceListByUserId(doctorsParamsVo.getId());
	        	doctorsParamsVo.setObj(serviceListByUserIdList);
	        	doctorParamsList.add(doctorsParamsVo);
			}
	        dpv.setPageList(doctorParamsList);
	        dpv.setTotalRows(pageFamousDoctor.getTotal());
			response.setStatus(RestStatusEnum.OK);
			response.setData(dpv);
		} catch (Exception e) {
			response.setStatus(RestStatusEnum.ERROR);
			e.printStackTrace();
		}
		return response;
	}

	@GET
	@Path("findDoctorListByDept/{deptId:.*}")
	@Override
	public RestResponse findDoctorListByDept(@PathParam("deptId") String deptId) {
		RestResponse response = new RestResponse();
		List<DoctorsParamsVo> famousDoctorList;
		try {
			famousDoctorList = doctorUserDao.findDoctorListByDept(deptId);
			response.setStatus(RestStatusEnum.OK);
			response.setData(famousDoctorList);
		} catch (Exception e) {
			response.setStatus(RestStatusEnum.ERROR);
			e.printStackTrace();
		}
		return response;
	}

	@POST
	@Path("findFamousDoctorListPost")
	@Override
	public RestResponse findFamousDoctorListPost(PageParamsVo pageParamsVo) {
		/*Map<String, Object> validResMap = ValidationUtil.notBlank(pageParamsVo, new String[] { "pageNum", "pageSize"});
		Boolean isValid = Boolean.valueOf(validResMap.get(ValidationUtil.CHECK_IS_VALID).toString());
		if (isValid) {
			
		}*/
		String pageNum = pageParamsVo.getPageNum();
		String pageSize = pageParamsVo.getPageSize();
		RestResponse response = new RestResponse();
		List<DoctorsParamsVo> famousDoctorList;
		try {
			Integer pageNumInt = (pageNum==null||"".equals(pageNum))?1:Integer.parseInt(pageNum);
			Integer pageSizeInt = (pageSize==null||"".equals(pageSize))?5:Integer.parseInt(pageSize);;
			PageHelper.startPage(pageNumInt,pageSizeInt);
	        famousDoctorList = doctorUserDao.findFamousDoctorList();
	        PageInfo<DoctorsParamsVo> pageFamousDoctor = new PageInfo<DoctorsParamsVo>(famousDoctorList);
	        DoctorsParamsVo dpv = new DoctorsParamsVo();
	        dpv.setPageList(pageFamousDoctor.getList());
	        dpv.setTotalRows(pageFamousDoctor.getTotal());
			response.setStatus(RestStatusEnum.OK);
			response.setData(dpv);
		} catch (Exception e) {
			response.setStatus(RestStatusEnum.ERROR);
			e.printStackTrace();
		}
		return response;
	}

	
	@GET
	@Path("findDoctorListByDeptAndCity/{deptId:.*}/{cityCode:.*}")
	@Override
	public RestResponse findDoctorListByDeptAndCity(@PathParam("deptId") String deptId,@PathParam("cityCode")String cityCode) {
		Map<String, String>  params = new HashMap<String, String>();
		params.put("deptId", deptId);
		params.put("cityCode", cityCode);
		//排序：按职称顺序排列，同职称内的按首字母排序
		params.put("sort", "");
		RestResponse response = new RestResponse();
		try {
			List<DoctorsParamsVo> doctorListByDeptAndCityList = doctorUserDao.findDoctorListByDeptAndCity(params);
			response.setStatus(RestStatusEnum.OK);
			response.setData(doctorListByDeptAndCityList);
			return response;
		} catch (Exception e) {
			response.setStatus(RestStatusEnum.ERROR);
			e.printStackTrace();
		}
		return response;
	}

}
