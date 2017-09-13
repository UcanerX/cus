package com.sunshine.mobileapp.api.service.service;

import java.util.Map;

import com.sunshine.framework.mvc.service.BaseService;
import com.sunshine.mobileapp.api.service.entity.ServiceSpec;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.service.service
 * @ClassName: LoginController
 * @Description: <p>服务规格Service</p>
 * @JDK version used: 
 * @Author: 甘松
 * @Create Date: 2017年6月12日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface ServiceSpecService extends BaseService<ServiceSpec, String> {
	public void batchUpdateStatus(Map<String, Object> map);
}
