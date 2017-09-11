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
package com.sunshine.mobileapp.api.index.serviceinfo.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.sunshine.framework.mvc.mysql.dao.impl.BaseDaoImpl;
import com.sunshine.mobileapp.api.index.serviceinfo.dao.DoctorServiceInfoDao;
import com.sunshine.mobileapp.api.index.serviceinfo.entity.DoctorServiceInfo;


/**
 * @Project: easy_health 
 * @Package: com.sunshine.doctor.service.dao.impl
 * @ClassName: DoctorServiceInfoDaoImpl
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: MuGua
 * @Create Date: 2016年10月25日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */

@Repository(value = "doctorServiceInfoDao")
public class DoctorServiceInfoDaoImpl extends BaseDaoImpl<DoctorServiceInfo, String> implements DoctorServiceInfoDao {
	private final static String SQLNAME_GET_SERVICE_LIST_BY_USER_ID = "getServiceListByUserId";
	private final static String SQLNAME_GET_SERVICE_LIST_BY_USER_IDS = "getServiceListByUserIds";
	private final static String SQLNAME_GET_DOCTOR_SERVICE_INFO_BY_SERVICE_ID_AND_USER_ID = "getDoctorServiceInfoByServiceIdAndUserId";
	private final static String SQLNAME_FIND_SERVICEINFO_BY_STATUS = "findServiceInfoByStatus";
	
	@Override
	public List<DoctorServiceInfo> getServiceListByUserId(String userId) {
		Assert.notNull(userId);
		return sqlSession.selectList(getSqlName(SQLNAME_GET_SERVICE_LIST_BY_USER_ID), userId);
	}
	
	@Override
	public List<DoctorServiceInfo> getServiceListByUserIds(List<String> userIdList) {
		Assert.notNull(userIdList);
		return sqlSession.selectList(getSqlName(SQLNAME_GET_SERVICE_LIST_BY_USER_IDS), userIdList);
	}

	@Override
	public DoctorServiceInfo getDoctorServiceInfoByServiceIdAndUserId(Map params) {
		return sqlSession.selectOne(getSqlName(SQLNAME_GET_DOCTOR_SERVICE_INFO_BY_SERVICE_ID_AND_USER_ID), params);
	}
	
	@Override
	public List<DoctorServiceInfo> findServiceInfoByStatus(Integer status) {
		return sqlSession.selectList(getSqlName(SQLNAME_FIND_SERVICEINFO_BY_STATUS), status);
	}

}
