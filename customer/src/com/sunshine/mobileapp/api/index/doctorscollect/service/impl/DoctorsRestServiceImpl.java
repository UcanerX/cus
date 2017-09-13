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
import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.framework.mvc.mysql.service.impl.BaseServiceImpl;
import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.RestStatusEnum;
import com.sunshine.mobileapp.api.index.doctorscollect.dao.DoctorUserDao;
import com.sunshine.mobileapp.api.index.doctorscollect.service.DoctorsRestService;
import com.sunshine.mobileapp.api.index.doctorscollect.vo.DoctorsParamsVo;
import com.sunshine.mobileapp.api.index.doctorscollect.vo.PageParamsVo;
import com.sunshine.mobileapp.api.serviceinfo.dao.DoctorServiceInfoDao;
import com.sunshine.mobileapp.api.serviceinfo.entity.DoctorServiceInfo;

/**
* @Package：com.sunshine.mobileapp.api.index.doctorscollect.service.impl   
* @ClassName：DoctorsRestServiceImpl   
* @Description：   <p> 名医汇 服务接口实现类</p>
* @Author： -
* @CreatTime：2017年9月6日 上午11:08:22   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
@Path("v1/doctors")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class DoctorsRestServiceImpl extends BaseServiceImpl<DoctorServiceInfo, String> implements DoctorsRestService {

	@Autowired
	private DoctorUserDao doctorUserDao;

	@Autowired
	private DoctorServiceInfoDao doctorServiceInfoDao;

	@Override
	protected BaseDao<DoctorServiceInfo, String> getDao() {
		return doctorServiceInfoDao;
	}

	@GET
	@Path("/doctorHomePageDetail/{doctorId : .*}")
	@Override
	public RestResponse doctorHomePageDetail(@PathParam("doctorId") String doctorId) {
		RestResponse response = new RestResponse();
		DoctorsParamsVo doctorDetail;
		try {
			doctorDetail = doctorUserDao.findDoctorDetailById(doctorId);
			if (doctorDetail != null) {
				List<DoctorServiceInfo> serviceList = doctorServiceInfoDao.getServiceListByUserId(doctorDetail.getId());
				doctorDetail.setServiceList(serviceList);
				response.setData(doctorDetail);
			}
			response.setStatus(RestStatusEnum.OK);
		} catch (Exception e) {
			response.setStatus(RestStatusEnum.ERROR);
			e.printStackTrace();
		}
		return response;
	}

	@GET
	@Path("/findFamousDoctorList/{pageNum : .*}/{pageSize:.*}")
	public RestResponse findFamousDoctorList(@PathParam("pageNum") String pageNum, @PathParam("pageSize") String pageSize) {
		RestResponse response = new RestResponse();
		List<DoctorsParamsVo> famousDoctorList;
		try {
			Integer pageNumInt = ( pageNum == null || "".equals(pageNum) ) ? 1 : Integer.parseInt(pageNum);
			Integer pageSizeInt = ( pageSize == null || "".equals(pageSize) ) ? 5 : Integer.parseInt(pageSize);
			;
			PageHelper.startPage(pageNumInt, pageSizeInt);
			famousDoctorList = doctorUserDao.findFamousDoctorList();
			PageInfo<DoctorsParamsVo> pageFamousDoctor = new PageInfo<DoctorsParamsVo>(famousDoctorList);
			DoctorsParamsVo dpv = new DoctorsParamsVo();
			List<DoctorsParamsVo> doctorParamsList = new ArrayList<DoctorsParamsVo>();
			List<DoctorsParamsVo> list = pageFamousDoctor.getList();
			for (DoctorsParamsVo doctorsParamsVo : list) {
				List<DoctorServiceInfo> serviceListByUserIdList = doctorServiceInfoDao.getServiceListByUserId(doctorsParamsVo.getId());
				doctorsParamsVo.setServiceList(serviceListByUserIdList);
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
	@Path("findDoctorListByDept/{deptId:.*}/{pageNum : .*}/{pageSize:.*}")
	@Override
	public RestResponse findDoctorListByDept(@PathParam("deptId") String deptId, @PathParam("pageNum") String pageNum,
			@PathParam("pageSize") String pageSize) {
		RestResponse response = new RestResponse();
		try {
			Integer pageNumInt = ( pageNum == null || "".equals(pageNum) ) ? 1 : Integer.parseInt(pageNum);
			Integer pageSizeInt = ( pageSize == null || "".equals(pageSize) ) ? 5 : Integer.parseInt(pageSize);
			;
			PageHelper.startPage(pageNumInt, pageSizeInt);
			List<DoctorsParamsVo> famousDoctorList;
			famousDoctorList = doctorUserDao.findDoctorListByDept(deptId);
			PageInfo<DoctorsParamsVo> pageFamousDoctor = new PageInfo<DoctorsParamsVo>(famousDoctorList);
			DoctorsParamsVo dpv = new DoctorsParamsVo();
			List<DoctorsParamsVo> doctorParamsList = new ArrayList<DoctorsParamsVo>();
			List<DoctorsParamsVo> list = pageFamousDoctor.getList();
			for (DoctorsParamsVo doctorsParamsVo : list) {
				List<DoctorServiceInfo> serviceListByUserIdList = doctorServiceInfoDao.getServiceListByUserId(doctorsParamsVo.getId());
				doctorsParamsVo.setServiceList(serviceListByUserIdList);
				doctorParamsList.add(doctorsParamsVo);
			}
			dpv.setPageList(doctorParamsList);
			dpv.setTotalRows(pageFamousDoctor.getTotal());
			response.setStatus(RestStatusEnum.OK);
			response.setData(dpv);
		} catch (Exception e) {
			response.setCode(500);
			response.setStatus(RestStatusEnum.ERROR);
			e.printStackTrace();
		}
		return response;
	}

	@GET
	@Path("findDoctorListByDeptAndCity/{deptId:.*}/{cityCode:.*}/{pageNum : .*}/{pageSize:.*}")
	@Override
	public RestResponse findDoctorListByDeptAndCity(@PathParam("deptId") String deptId, @PathParam("cityCode") String cityCode,
			@PathParam("pageNum") String pageNum, @PathParam("pageSize") String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("deptId", deptId);
		params.put("cityCode", cityCode);
		//排序：按职称顺序排列，同职称内的按首字母排序
		params.put("sort", "");
		RestResponse response = new RestResponse();
		List<DoctorsParamsVo> doctorListByDeptAndCityList;
		try {

			Integer pageNumInt = ( pageNum == null || "".equals(pageNum) ) ? 1 : Integer.parseInt(pageNum);
			Integer pageSizeInt = ( pageSize == null || "".equals(pageSize) ) ? 5 : Integer.parseInt(pageSize);
			;
			PageHelper.startPage(pageNumInt, pageSizeInt);
			doctorListByDeptAndCityList = doctorUserDao.findDoctorListByDeptAndCity(params);
			PageInfo<DoctorsParamsVo> pageFamousDoctor = new PageInfo<DoctorsParamsVo>(doctorListByDeptAndCityList);
			DoctorsParamsVo dpv = new DoctorsParamsVo();
			List<DoctorsParamsVo> doctorParamsList = new ArrayList<DoctorsParamsVo>();
			List<DoctorsParamsVo> list = pageFamousDoctor.getList();
			for (DoctorsParamsVo doctorsParamsVo : list) {
				List<DoctorServiceInfo> serviceListByUserIdList = doctorServiceInfoDao.getServiceListByUserId(doctorsParamsVo.getId());
				doctorsParamsVo.setServiceList(serviceListByUserIdList);
				doctorParamsList.add(doctorsParamsVo);
			}
			dpv.setPageList(doctorParamsList);
			dpv.setTotalRows(pageFamousDoctor.getTotal());
			response.setStatus(RestStatusEnum.OK);
			response.setData(dpv);
			return response;
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
		String pageNum = pageParamsVo.getPageNum();
		String pageSize = pageParamsVo.getPageSize();
		RestResponse response = new RestResponse();
		List<DoctorsParamsVo> famousDoctorList;
		try {
			Integer pageNumInt = ( pageNum == null || "".equals(pageNum) ) ? 1 : Integer.parseInt(pageNum);
			Integer pageSizeInt = ( pageSize == null || "".equals(pageSize) ) ? 5 : Integer.parseInt(pageSize);
			;
			PageHelper.startPage(pageNumInt, pageSizeInt);
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

}
