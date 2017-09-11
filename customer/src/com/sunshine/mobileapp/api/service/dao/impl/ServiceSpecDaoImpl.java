package com.sunshine.mobileapp.api.service.dao.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.sunshine.framework.base.dao.impl.BaseDaoImpl;
import com.sunshine.framework.exception.SystemException;
import com.sunshine.mobileapp.api.service.dao.ServiceSpecDao;
import com.sunshine.mobileapp.api.service.entity.ServiceSpec;

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
@Repository(value = "serviceSpecDaoImpl")
public class ServiceSpecDaoImpl extends BaseDaoImpl<ServiceSpec, String> implements ServiceSpecDao {

	private static Logger logger = LoggerFactory.getLogger(ServiceSpecDaoImpl.class);
	private final static String SQLNAME_BATCH_UPDATE_STATUS = "batchUpdateStatus";
	
	@Override
	public void batchUpdateStatus(Map<String, Object> map) {
		try {
			Assert.notNull(map);
			sqlSession.update(getSqlName(SQLNAME_BATCH_UPDATE_STATUS), map);
		} catch (Exception e) {
			logger.error(String.format("批量修改服务规格状态出错！语句：%s", getSqlName(SQLNAME_BATCH_UPDATE_STATUS)), e);
			throw new SystemException(String.format("批量修改服务规格状态出错！语句：%s", getSqlName(SQLNAME_BATCH_UPDATE_STATUS)), e);
		}
		
	}
}
