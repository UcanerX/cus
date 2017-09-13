package com.sunshine.mobileapp.api.service.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.sunshine.framework.exception.SystemException;
import com.sunshine.framework.mvc.mysql.dao.impl.BaseDaoImpl;
import com.sunshine.mobileapp.api.service.dao.ServicePriceManageDao;
import com.sunshine.mobileapp.api.service.entity.ServicePriceManage;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.service.dao.impl
 * @ClassName: LoginController
 * @Description: <p>服务价格表DaoImpl</p>
 * @JDK version used: 
 * @Author: 胡椒
 * @Create Date: 2016年9月18日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Repository(value = "servicePriceManagedDao")
public class ServicePriceManagedDaoImpl extends BaseDaoImpl<ServicePriceManage, String> implements ServicePriceManageDao {
	private static Logger logger = LoggerFactory.getLogger(ServicePriceManagedDaoImpl.class);

	private final static String SQLNAME_GET_SERVICE_PRICE_LIST = "getServicePriceList";
	private final static String SQLNAME_UPDATE_PRICE_INFO_STATUS = "updatePriceInfoStatus";
	private final static String SQLNAME_FIND_BY_PK_ID = "findByPkId";
	private final static String SQLNAME_BATCH_UPDATE_STATUS = "batchUpdateStatus";
	private final static String SQLNAME_GET_SERVICE_PRICE_BY_SERVICE_ID = "getServicePricesByServiceId";

	@Override
	public List<ServicePriceManage> getServicePriceList() {
		try {
			return sqlSession.selectList(getSqlName(SQLNAME_GET_SERVICE_PRICE_LIST));
		} catch (Exception e) {
			logger.error(String.format("查询服务价格列表出错！语句：%s", getSqlName(SQLNAME_GET_SERVICE_PRICE_LIST)), e);
			throw new SystemException(String.format("查询服务价格列表出错！语句：%s", getSqlName(SQLNAME_GET_SERVICE_PRICE_LIST)), e);
		}
	}

	@Override
	public void updatePriceInfoStatus(ServicePriceManage entity, Map<String, Object> params) {
		Assert.notNull(entity);
		try {
			sqlSession.update(getSqlName(SQLNAME_UPDATE_PRICE_INFO_STATUS), params);
		} catch (Exception e) {
			logger.error(String.format("更新对象出错！语句：%s", getSqlName(SQLNAME_UPDATE_PRICE_INFO_STATUS)), e);
			throw new SystemException(String.format("更新对象出错！语句：%s", getSqlName(SQLNAME_UPDATE_PRICE_INFO_STATUS)), e);
		}
	}

	@Override
	public ServicePriceManage findByPkId(Map<String, Object> params) {
		Assert.notNull(params);
		ServicePriceManage servicePriceManage = null;
		try {
			if (servicePriceManage == null) {
				servicePriceManage = sqlSession.selectOne(getSqlName(SQLNAME_FIND_BY_PK_ID), params);
			}
		} catch (Exception e) {
			logger.error(String.format("查询对象出错！语句：%s", getSqlName(SQLNAME_FIND_BY_PK_ID)), e);
			throw new SystemException(String.format("查询对象出错！语句：%s", getSqlName(SQLNAME_FIND_BY_PK_ID)), e);
		}
		return servicePriceManage;
	}

	@Override
	public void batchUpdateStatus(Map<String, Object> map) {
		try {
			Assert.notNull(map);
			sqlSession.update(getSqlName(SQLNAME_BATCH_UPDATE_STATUS), map);
		} catch (Exception e) {
			logger.error(String.format("批量修改服务价格状态出错！语句：%s", getSqlName(SQLNAME_BATCH_UPDATE_STATUS)), e);
			throw new SystemException(String.format("批量修改服务价格状态出错！语句：%s", getSqlName(SQLNAME_BATCH_UPDATE_STATUS)), e);
		}
	}

	@Override
	public List<ServicePriceManage> getServicePricesByServiceId(String serviceId) {
		try {
			Assert.notNull(serviceId);
			return sqlSession.selectList(getSqlName(SQLNAME_GET_SERVICE_PRICE_BY_SERVICE_ID), serviceId);
		} catch (Exception e) {
			logger.error(String.format("查询服务价格列表出错！语句：%s", getSqlName(SQLNAME_GET_SERVICE_PRICE_BY_SERVICE_ID)), e);
			throw new SystemException(String.format("查询服务价格列表出错！语句：%s", getSqlName(SQLNAME_GET_SERVICE_PRICE_BY_SERVICE_ID)), e);
		}
	}
}
