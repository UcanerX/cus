package com.sunshine.mobileapp.api.service.dao;

import java.util.Map;

import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.mobileapp.api.service.entity.ServiceSpec;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.service.dao
 * @ClassName: LoginController
 * @Description: <p>服务基础信息表Dao</p>
 * @JDK version used: 
 * @Author: 胡椒
 * @Create Date: 2016年9月18日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface ServiceSpecDao extends BaseDao<ServiceSpec, String> {
	/**
	 * 更新服务状态
	 * @param params
	 * @return
	 */
	public void batchUpdateStatus(Map<String, Object> map);
}
