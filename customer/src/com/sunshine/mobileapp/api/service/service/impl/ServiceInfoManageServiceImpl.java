package com.sunshine.mobileapp.api.service.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunshine.framework.base.dao.BaseDao;
import com.sunshine.framework.base.service.impl.BaseServiceImpl;
import com.sunshine.mobileapp.api.service.dao.ServiceInfoManageDao;
import com.sunshine.mobileapp.api.service.entity.ServiceInfoManage;
import com.sunshine.mobileapp.api.service.service.ServiceInfoManageService;


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
public class ServiceInfoManageServiceImpl extends BaseServiceImpl<ServiceInfoManage, String> implements ServiceInfoManageService {

	@Autowired
	private ServiceInfoManageDao serviceInfoManageDao;
	
	@Override
	protected BaseDao<ServiceInfoManage, String> getDao() {
		return serviceInfoManageDao;
	}

	@Override
	public List<ServiceInfoManage> getServiceInfoList() {
		return serviceInfoManageDao.getServiceInfoList();
	}

	@Override
	public String addOrUpdate(ServiceInfoManage entity, String type) {
		return serviceInfoManageDao.addOrUpdate(entity, type);
	}
	
	@Override
	public ServiceInfoManage findByPkId(Map<String, Object> params) {
		return serviceInfoManageDao.findByPkId(params);
	}

	@Override
	public void updateServiceStatus(ServiceInfoManage entity,Map<String, Object> params) {
		serviceInfoManageDao.updateServiceStatus(entity,params);
	}

	@Override
	public void batchUpdateStatus(Map<String, Object> map) {
		serviceInfoManageDao.batchUpdateStatus(map);
	}

}
