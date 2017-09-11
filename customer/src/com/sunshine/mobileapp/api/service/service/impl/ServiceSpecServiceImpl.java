package com.sunshine.mobileapp.api.service.service.impl;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunshine.framework.base.dao.BaseDao;
import com.sunshine.framework.base.service.impl.BaseServiceImpl;
import com.sunshine.mobileapp.api.service.dao.ServiceSpecDao;
import com.sunshine.mobileapp.api.service.entity.ServiceSpec;
import com.sunshine.mobileapp.api.service.service.ServiceSpecService;


/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.service.service.impl
 * @ClassName: LoginController
 * @Description: <p>服务基础信息表ServiceImpl</p>
 * @JDK version used: 
 * @Author: 胡椒
 * @Create Date: 2016年9月18日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Service
public class ServiceSpecServiceImpl extends BaseServiceImpl<ServiceSpec, String> implements ServiceSpecService {

	@Autowired
	private ServiceSpecDao serviceSpecDao;
	
	@Override
	protected BaseDao<ServiceSpec, String> getDao() {
		return serviceSpecDao;
	}
	@Override
	public void batchUpdateStatus(Map<String, Object> map) {
		serviceSpecDao.batchUpdateStatus(map);
	}

}
