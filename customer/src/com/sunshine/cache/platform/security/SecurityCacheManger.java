/**
 * <html>
 * <body>
 *  <P> Copyright 2017 阳光康众</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年7月1日</p>
 *  <p> Created by 于策/yu.ce@foxmail.com</p>
 *  </body>
 * </html>
 */
package com.sunshine.cache.platform.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.sunshine.cache.CacheConstant;
import com.sunshine.cache.CacheConstant.CacheKeys;
import com.sunshine.common.GlobalConstant;
import com.sunshine.framework.cache.redis.RedisLock;
import com.sunshine.framework.cache.redis.RedisService;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.platform.security.entity.Resource;

/**
 * @Project ChuFangLiuZhuan_PlatForm
 * @Package com.sunshine.cache.platform
 * @ClassName SecurityCacheManger.java
 * @Description
 * @JDK version used 1.8
 * @Author 于策/yu.ce@foxmail.com
 * @Create Date 2017年7月1日
 * @modify By
 * @modify Date
 * @Why&What is modify
 * @Version 1.0
 */
@Service
public class SecurityCacheManger {
	private static Logger logger = LoggerFactory.getLogger(SecurityCacheManger.class);
	private RedisService redisService = SpringContextHolder.getBean(RedisService.class);
	private static final String CHECK_INIT_CACHE_FILED = "security.init";

	public void initCache() {
		boolean isNeed = isNeedInitCache();
		if (isNeed) {
			// 使用redis实现的分布式锁 保证集群时启动时的多并发
			RedisLock redisLock = new RedisLock(redisService.getRedisPool());
			boolean isLock = false;
			try {
				do {
					isLock = redisLock.singleLock(CHECK_INIT_CACHE_FILED, CacheConstant.REDIS_LOCKED_DEF_TIME);
				} while (!isLock);
				logger.info("event:初始化权限数据,得到分布式锁,锁键-{}", CHECK_INIT_CACHE_FILED.concat(CacheConstant.REDIS_LOCK_KEY_PREFIX));
				isNeed = isNeedInitCache();
				// 写入cache version
				redisService.hset(CacheConstant.DATA_CACHE_VERSION, CHECK_INIT_CACHE_FILED, builCacheVersion());

				if (isNeed) {
					// 初始化逻辑
				}
			} finally {
				redisLock.singleUnlock(CHECK_INIT_CACHE_FILED);
				logger.info("event:初始化权限数据完成,释放分布式锁,锁键-{}", CHECK_INIT_CACHE_FILED.concat(CacheConstant.REDIS_LOCK_KEY_PREFIX));
			}
		}
	}

	public List<Resource> querySubResourceByParentId(String parentId) {
		List<Resource> resourceList = null;
		String jsonData = redisService.hget(getResourceParentCacheKey(), parentId);
		if (CacheConstant.isHadValue(jsonData)) {
			resourceList = JSON.parseArray(jsonData, Resource.class);
		} else {
			resourceList = new ArrayList<Resource>();
		}
		return resourceList;
	}

	/**
	 * @Description 更新资源下子资源缓存
	 * @param parentId
	 * @param newResources
	 * @param opType
	 *            CacheConstant.CACHE_OP_UPDATE CacheConstant.CACHE_OP_ADD CacheConstant.CACHE_OP_DEL
	 * @date 2017年7月3日
	 */
	public void updateParentResouceCache(String parentId, Resource resource, String opType) {
		List<Resource> cacheResources = querySubResourceByParentId(parentId);
		if (CacheConstant.CACHE_OP_ADD.equalsIgnoreCase(opType)) {
			cacheResources.add(resource);
			redisService.hset(getResourceParentCacheKey(), parentId, JSON.toJSONString(cacheResources));
		} else if (CacheConstant.CACHE_OP_UPDATE.equalsIgnoreCase(opType)) {
			Map<String, Resource> resourceMap = convertListToMap(cacheResources);
			resourceMap.put(resource.getId(), resource);
			redisService.hset(getResourceParentCacheKey(), parentId, JSON.toJSONString(resourceMap.values()));
		} else if (CacheConstant.CACHE_OP_DEL.equalsIgnoreCase(opType)) {
			Map<String, Resource> resourceMap = convertListToMap(cacheResources);
			resourceMap.remove(resource.getId());
			redisService.hset(getResourceParentCacheKey(), parentId, JSON.toJSONString(resourceMap.values()));
		} else {

		}
	}

	/**
	 * @Description 更新资源下按钮缓存
	 * @param parentId
	 * @param newResourceButtons
	 * @param opType
	 *            CacheConstant.CACHE_OP_UPDATE CacheConstant.CACHE_OP_ADD CacheConstant.CACHE_OP_DEL
	 * @date 2017年7月3日
	 */
	public void updateResouceButtonCache(String parentId, List<Resource> newResourceButtons, String opType) {
		if (CacheConstant.CACHE_OP_ADD.equalsIgnoreCase(opType)) {
			redisService.hset(getResourceParentCacheKey(), parentId, JSON.toJSONString(newResourceButtons));
		} else if (CacheConstant.CACHE_OP_UPDATE.equalsIgnoreCase(opType)) {
			redisService.hset(getResourceParentCacheKey(), parentId, JSON.toJSONString(newResourceButtons));
		} else if (CacheConstant.CACHE_OP_DEL.equalsIgnoreCase(opType)) {
			redisService.hdel(getResourceParentCacheKey(), parentId);
		} else {

		}
	}

	/**
	 * @Description
	 * @param roleResourceMap
	 *            key为roleId ,value为reources
	 * @param opType
	 *            CacheConstant.CACHE_OP_UPDATE CacheConstant.CACHE_OP_ADD CacheConstant.CACHE_OP_DEL
	 * @date 2017年7月3日
	 */
	public void updateRoleResouceCache(String roleId, List<Resource> resourceList, String opType) {
		if (CacheConstant.CACHE_OP_ADD.equalsIgnoreCase(opType)) {
			redisService.hset(getRoleResourceCacheKey(), roleId, JSON.toJSONString(resourceList));
		} else if (CacheConstant.CACHE_OP_UPDATE.equalsIgnoreCase(opType)) {
			redisService.hset(getRoleResourceCacheKey(), roleId, JSON.toJSONString(resourceList));
		} else if (CacheConstant.CACHE_OP_DEL.equalsIgnoreCase(opType)) {
			redisService.hdel(getRoleResourceCacheKey(), roleId);
		} else {

		}
	}

	public void delRoleResouceCache(String... roleIds) {
		redisService.hdel(getResourceParentCacheKey(), roleIds);
	}

	private Map<String, Resource> convertListToMap(List<Resource> resourceList) {
		Map<String, Resource> resourceMap = new HashMap<String, Resource>();
		for (Resource resource : resourceList) {
			resourceMap.put(resource.getId(), resource);
		}
		return resourceMap;
	}

	/**
	 * @Description 是否需要初始化缓存
	 * @return
	 * @date 2017年7月1日
	 */
	private Boolean isNeedInitCache() {
		boolean isNeed = false;
		String redisCacheVersion = redisService.hget(CacheConstant.DATA_CACHE_VERSION, CHECK_INIT_CACHE_FILED);
		boolean isHadValue = CacheConstant.isHadValue(redisCacheVersion);

		String currentCacheVersion = builCacheVersion();
		// 没有版本值或 当前缓存版本与redis服务器缓存版本不一致时 初始化缓存到redis缓存服务器
		if (!isHadValue || !redisCacheVersion.equalsIgnoreCase(currentCacheVersion)) {
			isNeed = true;
		}

		return isNeed;
	}

	/**
	 * @Description 根据角色列表查询所拥有的资源
	 * @param roleIds
	 * @return
	 * @date 2017年7月2日
	 */
	public List<Resource> queryResourceByRoleIds(Collection<String> roleIds) {
		List<Resource> allResources = new ArrayList<Resource>();
		if (!CollectionUtils.isEmpty(roleIds)) {
			String[] roleIdArray = new String[roleIds.size()];
			List<String> jsonDatas = redisService.hmget(getRoleResourceCacheKey(), roleIds.toArray(roleIdArray));
			for (String jsonData : jsonDatas) {
				if (CacheConstant.isHadValue(jsonData)) {
					List<Resource> resourceList = JSON.parseArray(jsonData, Resource.class);
					allResources.addAll(resourceList);
				}
			}
		}
		return allResources;
	}

	/**
	 * @Description 缓存版本
	 * @return
	 * @date 2017年7月1日
	 */
	private String builCacheVersion() {
		return CHECK_INIT_CACHE_FILED.concat(GlobalConstant.getServerVersion());
	}

	public String getResourceParentCacheKey() {
		return CacheKeys.RESOURCE_PARENT_CACHE_KEY.toString();
	}

	public String getRoleResourceCacheKey() {
		return CacheKeys.ROLE_RESOURCE_CACHE_KEY.toString();
	}
}
