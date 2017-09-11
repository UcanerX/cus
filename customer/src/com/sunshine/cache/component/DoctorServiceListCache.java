/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年10月31日</p>
 *  <p> Created by Allen</p>
 *  </body>
 * </html>
 */
package com.sunshine.cache.component;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.sunshine.cache.CacheConstant;
import com.sunshine.framework.cache.redis.RedisService;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.mobileapp.api.index.serviceinfo.entity.DoctorServiceInfo;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.common.datas.cache.component
 * @ClassName: DoctorServiceListCache
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: MuGua
 * @Create Date: 2016年10月31日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class DoctorServiceListCache {
	
	private RedisService redisSvc = SpringContextHolder.getBean(RedisService.class);

	/**
	 * 获取医生服务列表缓存Key
	 */
	public String getDoctorServiceListCacheKey() {

		return CacheConstant.CACHE_DOCTOR_SERVICE_LIST_KEY_PREFIX;

	}

	/**
	 * 添加医生服务列表缓存
	 * @param doctorServiceInfos
	 * @param user
	 * @return
	 */
	public boolean addDoctorServiceList(List<DoctorServiceInfo> doctorServiceInfos, String userId) {
		boolean result = false;
		if (doctorServiceInfos != null) {
			String field = userId;
			String value = JSON.toJSONString(doctorServiceInfos);
			if (StringUtils.isNotBlank(field) && StringUtils.isNotBlank(value)) {
				long setValue = redisSvc.hset(getDoctorServiceListCacheKey(), field, value);
				if (setValue == 1l) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * 获取医生端app服务列表
	 * @param userId
	 * @return
	 */
	public List<DoctorServiceInfo> getDoctorServiceList(String userId) {
		List<DoctorServiceInfo> infos = null;
		String value = redisSvc.hget(getDoctorServiceListCacheKey(), userId);
		if (StringUtils.isNotBlank(value) && !value.equals(CacheConstant.CACHE_KEY_NOT_EXIST)) {
			infos = JSON.parseArray(value, DoctorServiceInfo.class);
		}
		return infos;
	}

	/**
	 * 删除所有用户的服务列表
	 */
	public void deleteAllUserDoctorServiceList() {
		String key = getDoctorServiceListCacheKey();
		redisSvc.del(key);

	}
}
