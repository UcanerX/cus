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
package com.sunshine.mobileapp.api.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunshine.cache.component.DoctorServiceListCache;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.framework.mvc.mysql.service.impl.BaseServiceImpl;
import com.sunshine.mobileapp.api.index.serviceinfo.dao.DoctorServiceInfoDao;
import com.sunshine.mobileapp.api.index.serviceinfo.entity.DoctorServiceInfo;
import com.sunshine.mobileapp.api.order.service.DoctorServiceInfoService;

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

@Service(value = "doctorServiceInfoService")
public class DoctorServiceInfoServiceImpl extends BaseServiceImpl<DoctorServiceInfo, String> implements DoctorServiceInfoService {

	@Autowired
	DoctorServiceInfoDao doctorServiceInfoDao;
	
	@Autowired
	DoctorServiceListCache doctorServiceListCache = SpringContextHolder.getBean(DoctorServiceListCache.class);

	@Override
	protected BaseDao<DoctorServiceInfo, String> getDao() {
		// TODO Auto-generated method stub
		return doctorServiceInfoDao;
	}

	/**
	 * 根据启用状态获取医生服务列表
	 */
	@Override
	public List<DoctorServiceInfo> findServiceInfoByStatus(Integer status) {
		return doctorServiceInfoDao.findServiceInfoByStatus(status);
	}
	/**
	 * 获取医生服务列表
	 */
	@Override
	public List<DoctorServiceInfo> getServiceListByUserId(String userId) {
		return doctorServiceInfoDao.getServiceListByUserId(userId);
	}
	
	/**
	 * 获取医生服务列表
	 */
	@Override
	public List<DoctorServiceInfo> getServiceListByUserIds(List<String> userIdList) {
		return doctorServiceInfoDao.getServiceListByUserIds(userIdList);
	}

	/**
	 * 获取医生服务列表缓存
	 */
	@Override
	public List<DoctorServiceInfo> getServiceListCache(String userId) {
		List<DoctorServiceInfo> doctorServiceInfos = doctorServiceListCache.getDoctorServiceList(userId);
		if (doctorServiceInfos == null) {
			doctorServiceInfos = doctorServiceInfoDao.getServiceListByUserId(userId);
			doctorServiceListCache.addDoctorServiceList(doctorServiceInfos, userId);
		}
		return doctorServiceInfos;
	}

	/**
	 * 根据serviceId,userId 获取服务详情
	 */
	@Override
	public DoctorServiceInfo getDoctorServiceInfoByServiceIdAndUserId(Map params) {
		// TODO Auto-generated method stub
		return doctorServiceInfoDao.getDoctorServiceInfoByServiceIdAndUserId(params);
	}

}
