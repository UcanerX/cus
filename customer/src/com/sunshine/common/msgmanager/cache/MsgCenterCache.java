/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年4月11日</p>
 *  <p> Created by 申姜</p>
 *  </body>
 * </html>
 */
package com.sunshine.common.msgmanager.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.sunshine.cache.CacheConstant;
import com.sunshine.common.msgmanager.entity.MsgCenter;
import com.sunshine.common.msgmanager.entity.MsgCenterContent;
import com.sunshine.common.msgmanager.entity.MsgCenterFunction;
import com.sunshine.common.msgmanager.service.MsgCenterContentService;
import com.sunshine.common.msgmanager.service.MsgCenterFunctionService;
import com.sunshine.common.msgmanager.service.MsgCenterService;
import com.sunshine.framework.cache.redis.RedisService;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.framework.exception.SystemException;

/**
 * 个人中心消息配置缓存
 * @Project: sunshine_health 
 * @Package: com.sunshine.common.datas.cache.component
 * @ClassName: MsgCenterCache
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 申姜
 * @Create Date: 2016年4月11日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class MsgCenterCache {

	private static Logger logger = LoggerFactory.getLogger(MsgCenterCache.class);

	private RedisService redisSvc = SpringContextHolder.getBean(RedisService.class);
	private MsgCenterService msgCenterService = SpringContextHolder.getBean(MsgCenterService.class);
	private MsgCenterContentService msgCenterContentService = SpringContextHolder.getBean(MsgCenterContentService.class);
	private MsgCenterFunctionService msgCenterFunctionService = SpringContextHolder.getBean(MsgCenterFunctionService.class);
	private static boolean isHasInit = false;

	public MsgCenterCache() {
		super();
	}

	/**
	 * 初始化个人中心消息
	 * @throws SystemException
	 */
	public void initCache() throws SystemException {
		if (!isHasInit) {
			logger.info("init msgCenter config infos start.................");
			List<MsgCenter> msgCenters = msgCenterService.findAll();
			List<String> ids = new ArrayList<String>();
			Map<String, String> idsMap = new HashMap<String, String>();
			for (MsgCenter msgCenter : msgCenters) {
				ids.add(msgCenter.getId());
				idsMap.put(msgCenter.getId(), msgCenter.getId());
			}

			if (ids != null && ids.size() > 0) {
				List<MsgCenterContent> msgCenterContents = msgCenterContentService.findByIds(ids);
				Map<String, List<MsgCenterContent>> tempMap = new HashMap<String, List<MsgCenterContent>>();
				for (MsgCenterContent msgCenterContent : msgCenterContents) {
					if (idsMap.containsKey(msgCenterContent.getMsgId())) {
						if (tempMap.containsKey(msgCenterContent.getMsgId())) {
							List<MsgCenterContent> tempCenterContents = tempMap.get(msgCenterContent.getMsgId());
							tempCenterContents.add(msgCenterContent);
							tempMap.put(msgCenterContent.getMsgId(), tempCenterContents);
						} else {
							List<MsgCenterContent> tempCenterContents = new ArrayList<MsgCenterContent>();
							tempCenterContents.add(msgCenterContent);
							tempMap.put(msgCenterContent.getMsgId(), tempCenterContents);
						}
					}
				}

				List<MsgCenterFunction> msgCenterFunctions = msgCenterFunctionService.findByIds(ids);
				Map<String, List<MsgCenterFunction>> tempFunMap = new HashMap<String, List<MsgCenterFunction>>();
				for (MsgCenterFunction msgCenterFunction : msgCenterFunctions) {
					if (idsMap.containsKey(msgCenterFunction.getMsgId())) {
						if (tempFunMap.containsKey(msgCenterFunction.getMsgId())) {
							List<MsgCenterFunction> tempCenterFunctions = tempFunMap.get(msgCenterFunction.getMsgId());
							tempCenterFunctions.add(msgCenterFunction);
							tempFunMap.put(msgCenterFunction.getMsgId(), tempCenterFunctions);
						} else {
							List<MsgCenterFunction> tempCenterFunctions = new ArrayList<MsgCenterFunction>();
							tempCenterFunctions.add(msgCenterFunction);
							tempFunMap.put(msgCenterFunction.getMsgId(), tempCenterFunctions);
						}
					}
				}

				for (MsgCenter msgCenter : msgCenters) {
					if (tempMap.containsKey(msgCenter.getId())) {
						msgCenter.setMsgCenterContents(tempMap.get(msgCenter.getId()));
					}
					if (tempFunMap.containsKey(msgCenter.getId())) {
						msgCenter.setMsgCenterFunctions(tempFunMap.get(msgCenter.getId()));
					}
				}
				if (!CollectionUtils.isEmpty(msgCenters)) {
					Map<String, String> map = new HashMap<String, String>();
					String cacheKey = getMsgCenterCacheKey();
					for (MsgCenter msgCenter : msgCenters) {
						map.put(msgCenter.getMsgCode(), JSON.toJSONString(msgCenter));
					}
					redisSvc.del(cacheKey);
					redisSvc.hmset(cacheKey, map);
				}
				isHasInit = true;
				logger.info("init msgCenter config infos end...................");
			} else {
				logger.info("初始化个人中心消息-----id为空");
			}
		}
	}

	/**
	 * 根据消息code和医院ID获取个人中心消息模板
	 * @param hospitalId
	 * @param msgCode
	 * @return
	 */
	public MsgCenter getMsgCenter(String msgCode) {
		MsgCenter msgCenter = null;
		String cacheKey = getMsgCenterCacheKey();
		String jsonStr = redisSvc.hget(cacheKey, msgCode);
		if (!CacheConstant.CACHE_KEY_NOT_EXIST.equals(jsonStr)) {
			msgCenter = JSON.parseObject(jsonStr, MsgCenter.class);
		}
		return msgCenter;
	}

	/**
	 * 判断缓存对象是否存在
	 * @param hospitalId
	 * @param msgCode
	 * @return
	 */
	public boolean existsMsgCenter(String msgCode) {
		String cacheKey = getMsgCenterCacheKey();
		return redisSvc.hexists(cacheKey, msgCode);
	}

	/**
	 * 添加/更新个人中心模板
	 * @param msgCenter
	 */
	public void updateMsgCenter(MsgCenter msgCenter) {
		if (msgCenter != null) {
			String cacheKey = getMsgCenterCacheKey();
			redisSvc.hset(cacheKey, msgCenter.getMsgCode(), JSON.toJSONString(msgCenter));
		}
	}

	/**
	 * 删除个人中心消息模板
	 * @param hospitalId
	 * @param msgCode
	 */
	public void deleteMsgCenter(String msgCode) {
		String cacheKey = getMsgCenterCacheKey();
		redisSvc.hdel(cacheKey, msgCode);
	}

	/**
	 * redis hash存储结构中的cache key
	 * @return
	 */
	private String getMsgCenterCacheKey() {
		return CacheConstant.CACHE_MSG_CENTER_KEY_FREFIX;
	}

}
