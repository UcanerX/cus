package com.sunshine.mobileapp.api.service.service;

import java.util.List;
import java.util.Map;

import com.sunshine.framework.base.service.BaseService;
import com.sunshine.mobileapp.api.service.entity.ServicePriceManage;


/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.service.service
 * @ClassName: LoginController
 * @Description: <p>服务价格表Service</p>
 * @JDK version used: 
 * @Author: 胡椒
 * @Create Date: 2016年9月18日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface ServicePriceManageService extends BaseService<ServicePriceManage, String> {

	/**
	 * 查询服务价格列表
	 * @return
	 */
	public abstract List<ServicePriceManage> getServicePriceList();

	/**
	 * 更新服务价格表的服务状态
	 * @param params
	 * @return
	 */
	public abstract void updatePriceInfoStatus(ServicePriceManage entity, Map<String, Object> params);

	/**
	 * 通过主键查询
	 */
	public abstract ServicePriceManage findByPkId(Map<String, Object> params);

	/**
	 * 批量修改服务价格状态
	 */
	public void batchUpdateStatus(Map<String, Object> map);

	public List<ServicePriceManage> getServicePricesByServiceId(String serviceId);

}
