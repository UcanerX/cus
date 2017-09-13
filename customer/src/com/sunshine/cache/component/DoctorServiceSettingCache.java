/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年10月25日</p>
 *  <p> Created by Allen</p>
 *  </body>
 * </html>
 */
package com.sunshine.cache.component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.sunshine.cache.CacheConstant;
import com.sunshine.common.service.config.DoctorAvailableTimeConfig;
import com.sunshine.common.utils.WeekDay;
import com.sunshine.framework.cache.redis.RedisService;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.mobileapp.api.order.service.DoctorServiceInfoService;
import com.sunshine.mobileapp.api.serviceinfo.dao.DoctorServiceInfoDao;
import com.sunshine.mobileapp.api.serviceinfo.entity.DoctorServiceInfo;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.common.datas.cache.common
 * @ClassName: DoctorServiceCache
 * @Description: <p>医生启用的服务（图文，电话咨询等）配置缓存</p>
 * @JDK version used: 
 * @Author: MuGua
 * @Create Date: 2016年10月25日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class DoctorServiceSettingCache {
	
	private static final Logger logger = LoggerFactory.getLogger(DoctorServiceSettingCache.class);

	private RedisService redisSvc = SpringContextHolder.getBean(RedisService.class);
	
	@Autowired
	private DoctorServiceInfoService doctorServiceInfoService;
	
	/*@Autowired
	private DoctorServiceInfoDao doctorServiceInfoDao;*/
	
	@Autowired
	private DoctorAvailableTimeConfigCache doctorAvailableTimeConfigCache;
	
	@Autowired
	private DoctorAvailableAppointmentTimeCache doctorAvailableAppointmentTimeCache;

	/**
	 * 获取医生电话咨询设置缓存Key
	 */

	private String getDoctorServiceConsultSettingCacheKey() {

		return CacheConstant.CACHE_DOCTOR_SERVICE_SETTING_CONSULT_KEY_PREFIX;
	}
	
	private String getFieldName(String doctorId, String serviceId) {
		if (!StringUtils.isEmpty(doctorId) && !StringUtils.isEmpty(serviceId)) {
			String field = doctorId.concat(":").concat(serviceId);
			return field;
		}
		return null;
	}

	public void initCache() {
		List<DoctorServiceInfo> enabledServiceInfoList = doctorServiceInfoService.findServiceInfoByStatus(1);
		// 加载的数据较多，可改进为懒加载或异步加载缩短应用启动时间 by 甘松
		for (DoctorServiceInfo entity : enabledServiceInfoList) {
			setDoctorServiceConsultSetting(entity);
			if (shouldCacheConfig(entity)) {
				doctorAvailableTimeConfigCache.updateAvailableTimeConfigCache(DoctorAvailableTimeConfig.buildAvailableTimeConfig(entity));
			}
		}
	}
	/**
	 * 目前电话咨询配置才缓存
	 * 
	 * @param entity
	 * @return
	 */
	public boolean shouldCacheConfig(DoctorServiceInfo entity) {
		if (!StringUtils.isEmpty(entity.getStMon())
				|| !StringUtils.isEmpty(entity.getStTue())
				|| !StringUtils.isEmpty(entity.getStWed())
				|| !StringUtils.isEmpty(entity.getStThu())
				|| !StringUtils.isEmpty(entity.getStSat())
				|| !StringUtils.isEmpty(entity.getStSun())) {
			return true;
		}
		return false;
	}
	/**
	 * 添加医生咨询服务设置缓存
	 */
	public boolean setDoctorServiceConsultSetting(DoctorServiceInfo entity) {
		boolean result = false;
		if (entity != null) {
			// key = 医生ID:服务ID
			String field = getFieldName(entity.getUserId(), entity.getServiceId());
			String value = JSON.toJSONString(entity);
			if (StringUtils.isNotBlank(field) && StringUtils.isNotBlank(value)) {
				long setValue = redisSvc.hset(getDoctorServiceConsultSettingCacheKey(), field, value);
				if (setValue == 1l) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * 更新医生咨询服务设置缓存
	 */
	public void updateDoctorServiceConsultSetting(DoctorServiceInfo entity) {
		String field = getFieldName(entity.getUserId(), entity.getServiceId());
		String value = JSON.toJSONString(entity);
		if (StringUtils.isNotBlank(field) && StringUtils.isNotBlank(value)) {
			redisSvc.hset(getDoctorServiceConsultSettingCacheKey(), field, value);
		}
	}

	/**
	 * 获取医生咨询服务设置缓存
	 */
	public DoctorServiceInfo getDoctorServiceConsultSetting(String userId, String serviceId) {
		DoctorServiceInfo entity = null;
		String field = getFieldName(userId, serviceId);
		if (field != null) {
			String value = redisSvc.hget(getDoctorServiceConsultSettingCacheKey(), field);
			if (StringUtils.isNotBlank(value) && !value.equals(CacheConstant.CACHE_KEY_NOT_EXIST)) {
				entity = JSON.parseObject(value, DoctorServiceInfo.class);
			}
		}
		return entity;
	}
	
	/**
	 * 获取医生咨询服务设置缓存
	 */
	public List<DoctorServiceInfo> getAllDoctorServiceConsultSetting(String doctorId) {
		List<DoctorServiceInfo> list = new LinkedList<DoctorServiceInfo>();
		if (doctorId != null) {
			Map<String, String> map = redisSvc.hgetAll(getDoctorServiceConsultSettingCacheKey());
			if (map != null && !map.isEmpty()) {
				for(String value : map.values()) {
					list.add(JSON.parseObject(value, DoctorServiceInfo.class));
				}
			}
		}
		return list;
	}

	/**
	 * 更新医生电话咨询设置时间缓存，只更新变动的时间
	 */
	public void updateDoctorServiceConsultSettingTime(DoctorServiceInfo entity) {
		DoctorServiceInfo cache = null;
		String field = getFieldName(entity.getUserId(), entity.getServiceId());
		String value = redisSvc.hget(getDoctorServiceConsultSettingCacheKey(), field);
		boolean change = false;
		if (StringUtils.isNotBlank(value) && !value.equals(CacheConstant.CACHE_KEY_NOT_EXIST)) {
			String source = null;
			cache = JSON.parseObject(value, DoctorServiceInfo.class);
			if (entity.getStMon() != null) {
				source = cache.getStMon();
				cache.setStMon(entity.getStMon());
				
				doctorAvailableAppointmentTimeCache.cleanAppointmentTimeByWeekDay(
						WeekDay.Monday,
						entity.getUserId(), 
						entity.getServiceId(), 
						source,
						entity.getStMon());
				change = true;
			}
			if (entity.getStTue() != null) {
				source = cache.getStTue();
				cache.setStTue(entity.getStTue());
				
				doctorAvailableAppointmentTimeCache.cleanAppointmentTimeByWeekDay(
						WeekDay.Tuesday,
						entity.getUserId(), 
						entity.getServiceId(), 
						source,
						entity.getStTue());
				change = true;
			}
			if (entity.getStWed() != null) {
				source = cache.getStWed();
				cache.setStWed(entity.getStWed());
				
				doctorAvailableAppointmentTimeCache.cleanAppointmentTimeByWeekDay(
						WeekDay.Wednesday,
						entity.getUserId(), 
						entity.getServiceId(), 
						source,
						entity.getStWed());
				change = true;
			}
			if (entity.getStThu() != null) {
				source = cache.getStThu();
				cache.setStThu(entity.getStThu());
				
				doctorAvailableAppointmentTimeCache.cleanAppointmentTimeByWeekDay(
						WeekDay.Thursday,
						entity.getUserId(), 
						entity.getServiceId(), 
						source,
						entity.getStThu());
				change = true;
			}
			if (entity.getStFri() != null) {
				source = cache.getStFri();
				cache.setStFri(entity.getStFri());
				
				doctorAvailableAppointmentTimeCache.cleanAppointmentTimeByWeekDay(
						WeekDay.Friday,
						entity.getUserId(), 
						entity.getServiceId(), 
						source,
						entity.getStFri());
				change = true;
			}
			if (entity.getStSat() != null) {
				source = cache.getStSat();
				cache.setStSat(entity.getStSat());
				
				doctorAvailableAppointmentTimeCache.cleanAppointmentTimeByWeekDay(
						WeekDay.Saturday,
						entity.getUserId(), 
						entity.getServiceId(), 
						source,
						entity.getStSat());
				change = true;
			}
			if (entity.getStSun() != null) {
				source = cache.getStSun();
				cache.setStSun(entity.getStSun());
				
				doctorAvailableAppointmentTimeCache.cleanAppointmentTimeByWeekDay(
						WeekDay.Sunday,
						entity.getUserId(), 
						entity.getServiceId(), 
						source,
						entity.getStSun());
				change = true;
			}
			String json = JSON.toJSONString(cache);
			redisSvc.hset(getDoctorServiceConsultSettingCacheKey(), field, json);
			// 清理具体日期剩余预约时间后，更改医生可用时间配置对象缓存
			if (change && cache != null) {
				doctorAvailableTimeConfigCache.updateAvailableTimeConfigCache(DoctorAvailableTimeConfig.buildAvailableTimeConfig(cache));
			}
		}
	}
	
	
}
