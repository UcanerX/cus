package com.sunshine.cache.component;

import org.apache.commons.lang3.StringUtils;

import com.sunshine.cache.CacheConstant;
import com.sunshine.framework.cache.redis.RedisService;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;

/**
* @Package：com.sunshine.cache.component   
* @ClassName：UserAliasNameCache   
* @Description：   <p> 用户备注名称缓存</p>
* @Author： - DaoDou 
* @CreatTime：2017年9月11日 上午11:04:18   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
public class UserAliasNameCache {
	
	private RedisService redisSvc = SpringContextHolder.getBean(RedisService.class);
	
	public String getUserAliasNameCache(String doctorId, String userId) {
		if (!StringUtils.isEmpty(doctorId) && !StringUtils.isEmpty(userId)) {
			String value = redisSvc.hget(getCacheKey(), doctorId.concat(CacheConstant.CACHE_KEY_SPLIT_CHAR).concat(userId));
			if (StringUtils.isNotBlank(value) && !CacheConstant.CACHE_KEY_NOT_EXIST.equals(value)) {
				return value;
			}
		}
		return null;
		
	}
	
	public void updateUserAliasNameCache(String doctorId, String userId, String alias) {
		if (!StringUtils.isEmpty(doctorId) && !StringUtils.isEmpty(userId) && !StringUtils.isEmpty(alias)) {
			redisSvc.hset(getCacheKey(), doctorId.concat(CacheConstant.CACHE_KEY_SPLIT_CHAR).concat(userId), alias);
		}
	}
	
	private String getCacheKey() {
		return CacheConstant.DOCTOR_USER_ALIAS_NAME_KEY_PREFIX;
	}
}
