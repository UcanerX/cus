package com.sunshine.cache.component;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.sunshine.cache.CacheConstant;
import com.sunshine.framework.cache.redis.RedisService;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.mobileapp.api.patient.entity.InquireUser;

/**
* @Package：com.sunshine.cache.component   
* @ClassName：InquireUserCache   
* @Description：   <p> 问诊人临时缓存 </p>
* @Author： - DaoDou 
* @CreatTime：2017年9月13日 下午3:34:28   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
public class InquireUserCache {
	
	private RedisService redisSvc = SpringContextHolder.getBean(RedisService.class);
	
	/**
	 *  添加问诊人的临时缓存 C端id:“问诊人基本信息”
	 * @param userId  C端用户的id
	 * @return 
	 */
	public boolean addInquireUserTempCache(String userId,InquireUser inquireUser) {
		boolean result = false;
		if (!StringUtils.isEmpty(userId)) {
			String field = userId;
			String value = JSON.toJSONString(inquireUser);
			if (StringUtils.isNotBlank(field) && StringUtils.isNotBlank(value)) {
				long setValue = redisSvc.hset(getCacheKey(), field, value);
				if (setValue == 1l) {
					result = true;
				}
			}
		}
		return result;
	}
	
	public boolean updateInquireUserTempCache(String userId,InquireUser inquireUser) {
		boolean result = false;
		if (!StringUtils.isEmpty(userId)) {
			String field = userId;
			String value = JSON.toJSONString(inquireUser);
			if (StringUtils.isNotBlank(field) && StringUtils.isNotBlank(value)) {
				long setValue = redisSvc.hset(getCacheKey(), field, value);
				if (setValue == 1l) {
					result = true;
				}
			}
		}
		return result;
	}
	
	
	/**
	 * 根据C端id  查询问诊人信息
	 * @param userId
	 * @return
	 */
	public InquireUser getInquireUserCacheByUserId(String userId) {
		InquireUser inquireUser = null;
		if (!StringUtils.isEmpty(userId)) {
			String value = redisSvc.hget(getCacheKey(), userId);
			if (StringUtils.isNotBlank(value) && !CacheConstant.CACHE_KEY_NOT_EXIST.equals(value)) {
				inquireUser = JSON.parseObject(value, InquireUser.class);
			}
		}
		return inquireUser;
	}
	
	private String getCacheKey() {
		//INQUIRE_USER_TEMP   临时缓存 填写后 提示上一次的缓存信息
		return CacheConstant.INQUIRE_USER_TEMP_KEY_PREFIX;
	}
}
