package com.sunshine.cache.component;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.sunshine.cache.CacheConstant;
import com.sunshine.common.GlobalConstant;
import com.sunshine.framework.cache.redis.RedisService;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.mobileapp.api.service.entity.ServiceInfoManage;
import com.sunshine.mobileapp.api.service.service.ServiceInfoManageService;

/**
* @Package：com.sunshine.cache.component   
* @ClassName：SysServiceCache   
* @Description：   <p> 服务类型信息缓存</p>
* @Author： - DaoDou 
* @CreatTime：2017年9月11日 上午11:03:47   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
public class SysServiceCache {
	
	private RedisService redisSvc = SpringContextHolder.getBean(RedisService.class);
	
	@Autowired
	private ServiceInfoManageService serviceInfoManageService;
	
	public void initCache() {
		List<ServiceInfoManage> allService = serviceInfoManageService.findAll();
		
		for (ServiceInfoManage service : allService) {
			redisSvc.hset(getCacheKey(), service.getId(), JSON.toJSONString(service));
		}
	}
	
	public ServiceInfoManage getServiceCache(String serviceId) {
		ServiceInfoManage service = null;
		if (!StringUtils.isEmpty(serviceId)) {
			String value = redisSvc.hget(getCacheKey(), serviceId);
			if (StringUtils.isNotBlank(value) && !CacheConstant.CACHE_KEY_NOT_EXIST.equals(value)) {
				service = JSON.parseObject(value, ServiceInfoManage.class);
			}
		}
		return service;
	}
	
	public ServiceInfoManage getPhoneConsultService() {
		Map<String, String> map = redisSvc.hgetAll(getCacheKey());
		ServiceInfoManage service = null;
		if (map != null) {
			for (String value : map.values()) {
				if (StringUtils.isNotBlank(value) && !CacheConstant.CACHE_KEY_NOT_EXIST.equals(value)) {
					service = JSON.parseObject(value, ServiceInfoManage.class);
					if (service.getServiceName() != null && service.getServiceName().contains("电话咨询")) {
						break;
					}
				}
			}
		}
		return service;
	}
	
	public ServiceInfoManage getImageTextConsultService() {
		Map<String, String> map = redisSvc.hgetAll(getCacheKey());
		ServiceInfoManage service = null;
		if (map != null) {
			for (String value : map.values()) {
				if (StringUtils.isNotBlank(value) && !CacheConstant.CACHE_KEY_NOT_EXIST.equals(value)) {
					service = JSON.parseObject(value, ServiceInfoManage.class);
					if (service.getServiceName() != null && service.getServiceName().contains("图文咨询")) {
						break;
					}
				}
			}
		}
		return service;
	}
	
	public int getConsultServiceType(ServiceInfoManage service) {
		Assert.notNull(service, "服务对象不能为空");
		Assert.notNull(service.getId(), "服务对象ID不能为空");
		if (service.getServiceName() != null && service.getServiceName().contains("电话咨询")) {
			return GlobalConstant.SERVICE_TYPE_PHONE_CALL;
		} else if (service.getServiceName() != null && service.getServiceName().contains("图文咨询")) {
			return GlobalConstant.SERVICE_TYPE_TEXT_IMAGE;
		} else {
			throw new IllegalArgumentException("未知服务类型");
		}
	}
	
	/**
	 * 
	 * @param serviceId
	 * @return 返回-1如果未找到对应的service
	 */
	public int getConsultServiceType(String serviceId) {
		if (!StringUtils.isEmpty(serviceId)) {
			ServiceInfoManage service = this.getServiceCache(serviceId);
			return getConsultServiceType(service);
			
		} else {
			return -1;
		}
	}
	
	private String getCacheKey() {
		return CacheConstant.SYS_SERVICE_LIST_KEY_PREFIX;
	}
}
