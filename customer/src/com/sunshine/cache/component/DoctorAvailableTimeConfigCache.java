package com.sunshine.cache.component;

import org.apache.commons.lang3.StringUtils;

import com.sunshine.cache.CacheConstant;
import com.sunshine.common.service.config.DoctorAvailableTimeConfig;
import com.sunshine.framework.cache.redis.RedisService;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.common.datas.cache.component
 * @ClassName: DoctorAvailableTimeConfigCache
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 医生排班时间缓存对象
 * @Create Date: 2017年7月12日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class DoctorAvailableTimeConfigCache {
	private RedisService redisSvc = SpringContextHolder.getBean(RedisService.class);
	
	/**
	 * 多实例集群部署，每次从缓存获取 + 分布式锁 by 甘松
	 * 
	 * @param doctorId
	 * @return
	 */
	public DoctorAvailableTimeConfig getAvailableTimeConfig(String doctorId) {
		DoctorAvailableTimeConfig config = null;
		
		if (!StringUtils.isEmpty(doctorId)) {
			String value = redisSvc.hget(getCacheKey(), doctorId);
			if (!StringUtils.isEmpty(value) && !value.equals(CacheConstant.CACHE_KEY_NOT_EXIST)) {
				config = DoctorAvailableTimeConfig.parse(value);
			}
		} 
		return config;
	}
	
	public void updateAvailableTimeConfigCache(DoctorAvailableTimeConfig config) {
		if (config != null && !StringUtils.isEmpty(config.getDoctorId())) {
			redisSvc.hset(getCacheKey(), config.getDoctorId(), config.toJSONString());
		}
	}
	
	private String getCacheKey() {
		return CacheConstant.DOCTOR_AVAILABLE_TIME_KEY_PREFIX;
	}
}
