package com.sunshine.mobileapp.api.service.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.framework.mvc.mysql.service.impl.BaseServiceImpl;
import com.sunshine.mobileapp.api.service.dao.ServicePriceManageDao;
import com.sunshine.mobileapp.api.service.entity.ServicePriceManage;
import com.sunshine.mobileapp.api.service.service.ServicePriceManageService;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.service.service.impl
 * @ClassName: LoginController
 * @Description: <p>服务价格表ServiceImpl</p>
 * @JDK version used: 
 * @Author: 胡椒
 * @Create Date: 2016年9月18日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Service("servicePriceManageService")
public class ServicePriceManageServiceImpl extends BaseServiceImpl<ServicePriceManage, String> implements ServicePriceManageService {

	@Autowired
	private ServicePriceManageDao servicePriceManagedDao;

	@Override
	protected BaseDao<ServicePriceManage, String> getDao() {
		return servicePriceManagedDao;
	}

	@Override
	public List<ServicePriceManage> getServicePriceList() {
		return servicePriceManagedDao.getServicePriceList();
	}

	@Override
	public void updatePriceInfoStatus(ServicePriceManage entity, Map<String, Object> params) {
		servicePriceManagedDao.updatePriceInfoStatus(entity, params);
	}

	@Override
	public ServicePriceManage findByPkId(Map<String, Object> params) {
		return servicePriceManagedDao.findByPkId(params);
	}

	@Override
	public void batchUpdateStatus(Map<String, Object> map) {
		servicePriceManagedDao.batchUpdateStatus(map);
	}

	@Override
	public List<ServicePriceManage> getServicePricesByServiceId(String serviceId) {
		// TODO Auto-generated method stub
		return servicePriceManagedDao.getServicePricesByServiceId(serviceId);
	}
}
