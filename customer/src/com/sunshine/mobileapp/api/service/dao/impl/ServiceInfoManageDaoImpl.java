package com.sunshine.mobileapp.api.service.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.sunshine.framework.base.dao.impl.BaseDaoImpl;
import com.sunshine.framework.exception.SystemException;
import com.sunshine.mobileapp.api.service.dao.ServiceInfoManageDao;
import com.sunshine.mobileapp.api.service.entity.ServiceInfoManage;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.service.dao.impl
 * @ClassName: LoginController
 * @Description: <p>服务基础信息表DaoImpl</p>
 * @JDK version used: 
 * @Author: 胡椒
 * @Create Date: 2016年9月18日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Repository(value = "serviceInfoManageDao")
public class ServiceInfoManageDaoImpl extends BaseDaoImpl<ServiceInfoManage, String> implements ServiceInfoManageDao{

	private static Logger logger = LoggerFactory.getLogger(ServiceInfoManageDaoImpl.class);
	
	private final static String SQLNAME_GET_SERVICE_INFO_LIST = "getServiceInfoList";
	private final static String SQLNAME_FIND_BY_PK_ID = "findByPkId";
	private final static String SQLNAME_UPDATE_SERVICE_STATUS = "updateServiceStatus";
	private final static String SQLNAME_BATCH_UPDATE_STATUS = "batchUpdateStatus";
	
	@Override
	public List<ServiceInfoManage> getServiceInfoList() {
		try {
			return sqlSession.selectList(getSqlName(SQLNAME_GET_SERVICE_INFO_LIST));
		} catch (Exception e) {
			logger.error(String.format("查询服务列表出错！语句：%s", getSqlName(SQLNAME_GET_SERVICE_INFO_LIST)), e);
			throw new SystemException(String.format("查询服务列表出错！语句：%s", getSqlName(SQLNAME_GET_SERVICE_INFO_LIST)), e);
		}
	}

	@Override
	public String addOrUpdate(ServiceInfoManage entity, String type) {
		Assert.notNull(entity);
		try {
			if ("update".equals(type)) {
				super.update(entity);
			} else {
				super.insert(entity);
			}
		} catch (Exception e) {
			logger.error(String.format("添加对象出错！语句：%s", getSqlName("insert")), e);
			throw new SystemException(String.format("添加对象出错！语句：%s", getSqlName("insert")), e);
		}
		return null;
	}
	
	@Override
	public ServiceInfoManage findByPkId(Map<String, Object> params) {
		Assert.notNull(params);
		ServiceInfoManage serviceInfoManage = null;
		try {
			if (serviceInfoManage == null) {
				serviceInfoManage = sqlSession.selectOne(getSqlName(SQLNAME_FIND_BY_PK_ID), params);
			}
		} catch (Exception e) {
			logger.error(String.format("查询对象出错！语句：%s", getSqlName(SQLNAME_FIND_BY_PK_ID)), e);
			throw new SystemException(String.format("查询对象出错！语句：%s", getSqlName(SQLNAME_FIND_BY_PK_ID)), e);
		}
		return serviceInfoManage;
	}

	@Override
	public void updateServiceStatus(ServiceInfoManage entity,Map<String, Object> params) {
		Assert.notNull(entity);
		try {
			sqlSession.update(getSqlName(SQLNAME_UPDATE_SERVICE_STATUS), params);
		} catch (Exception e) {
			logger.error(String.format("更新对象出错！语句：%s", getSqlName(SQLNAME_UPDATE_SERVICE_STATUS)), e);
			throw new SystemException(String.format("更新对象出错！语句：%s", getSqlName(SQLNAME_UPDATE_SERVICE_STATUS)), e);
		}
	}

	@Override
	public void batchUpdateStatus(Map<String, Object> map) {
		try {
			Assert.notNull(map);
			sqlSession.update(getSqlName(SQLNAME_BATCH_UPDATE_STATUS), map);
		} catch (Exception e) {
			logger.error(String.format("批量修改服务基础信息状态出错！语句：%s", getSqlName(SQLNAME_BATCH_UPDATE_STATUS)), e);
			throw new SystemException(String.format("批量修改服务基础信息状态出错！语句：%s", getSqlName(SQLNAME_BATCH_UPDATE_STATUS)), e);
		}
		
	}
}
