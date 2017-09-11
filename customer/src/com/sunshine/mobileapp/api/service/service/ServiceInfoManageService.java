package com.sunshine.mobileapp.api.service.service;

import java.util.List;
import java.util.Map;

import com.sunshine.framework.base.service.BaseService;
import com.sunshine.mobileapp.api.service.entity.ServiceInfoManage;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.service.service
 * @ClassName: LoginController
 * @Description: <p>服务基础信息表Service</p>
 * @JDK version used: 
 * @Author: 胡椒
 * @Create Date: 2016年9月18日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface ServiceInfoManageService extends BaseService<ServiceInfoManage, String>{
	
	/**
	 * 查询服务信息列表
	 * @return
	 */
	public abstract List<ServiceInfoManage> getServiceInfoList();
	
	/**
	 * 添加或更新服务信息列表
	 * @param entity
	 * @param type
	 * @return
	 */
	public abstract String addOrUpdate(ServiceInfoManage entity, String type);
	
	/**
	 * 通过主键查询
	 */
	public abstract ServiceInfoManage findByPkId(Map<String, Object> params);
	
	/**
	 * 更新服务状态
	 * @param params
	 * @return
	 */
	public abstract void updateServiceStatus (ServiceInfoManage entity,Map<String, Object> params);
	
	/**
	 * 批量修改服务状态
	 */
	public void batchUpdateStatus(Map<String, Object> map);
	
}
