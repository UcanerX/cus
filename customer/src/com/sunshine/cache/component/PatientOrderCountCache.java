/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年9月7日</p>
 *  <p> Created by 钟乳石</p>
 *  </body>
 * </html>
 */
package com.sunshine.cache.component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sunshine.cache.CacheConstant;
import com.sunshine.framework.cache.redis.RedisService;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;

import redis.clients.jedis.Jedis;

/**
 * @Project: easy_health_client2 
 * @Package: com.sunshine.cache.component
 * @ClassName: PatientOrderCountCache
 * @Description: <p>用户订单数量提供缓存</p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年9月7日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class PatientOrderCountCache {
   private RedisService redisSvc = SpringContextHolder.getBean(RedisService.class);
	
   /**
    * 未支付
    */
   public static final String UNPAID = "unpaid";
	    
   /**
    * 待接诊
    */
   public static final String UNRECEIVE = "unreceive";
   
   /**
    * 已完成
    */
   public static final String SERVICING = "servicing";
   
   public static final Map<String,String> defaultMap =new HashMap<String,String>(){
		   {
			   put(UNPAID,"0");
			   put(UNRECEIVE,"0");
			   put(SERVICING,"0");
		   }
	   };
   
   public void incrUnpaid(String patientId ,int increment){
	   redisSvc.hincrBy(getCacheKey(patientId), UNPAID, increment);
   }
   
   public void incrUnreceive(String patientId ,int increment){
	   redisSvc.hincrBy(getCacheKey(patientId), UNRECEIVE, increment);
   }
   
   public void incrServicing(String patientId ,int increment){
	   redisSvc.hincrBy(getCacheKey(patientId), SERVICING, increment);
   }
   
  
   
   public Map<String,String> getAll(String patientId){
	   Map<String,String> map = redisSvc.hgetAll(getCacheKey(patientId));
	   if(map.size()<3){
		   Iterator<String> iterator = defaultMap.keySet().iterator();
		   while(iterator.hasNext()){
			   String key = iterator.next();
			   if(!map.containsKey(key)){
				   map.put(key, defaultMap.get(key));
			   }
		   }
	   }
	   return map;
   }
   
   public void set(String patientId , Map<String,String> map){
	   redisSvc.hmset(patientId, map);
   }
   private String getCacheKey(String patientId) {
		return CacheConstant.PATIENT_ORDER_COUNT.concat(CacheConstant.CACHE_KEY_SPLIT_CHAR).concat(patientId);
  }
}
