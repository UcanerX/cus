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

/**
 * @Project: easy_health_client2 
 * @Package: com.sunshine.cache.component
 * @ClassName: PatientOrderCountCache
 * @Description: <p>医生订单数量提供缓存</p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年9月10日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class DoctorOrderCountCache {
   private RedisService redisSvc = SpringContextHolder.getBean(RedisService.class);
	
   /**
    * 待接诊
    */
   public static final String UNRECEIVE = "unreceive";
	    
   /**
    * 待服务
    */
   public static final String SERVICING = "servicing";
   
   /**
    * 已完成
    */
   public static final String FINISHED = "finished";
   
   public static final Map<String,String> defaultMap =new HashMap<String,String>(){
		   {
			   put(UNRECEIVE,"0");
			   put(SERVICING,"0");
			   put(FINISHED,"0");
		   }
	   };
   
   public void incrUnreceive(String doctorId ,int increment){
	   redisSvc.hincrBy(getCacheKey(doctorId), UNRECEIVE, increment);
   }
   
   public void incrFinished(String doctorId ,int increment){
	   redisSvc.hincrBy(getCacheKey(doctorId), FINISHED, increment);
   }
   
   public void incrServicing(String doctorId ,int increment){
	   redisSvc.hincrBy(getCacheKey(doctorId), SERVICING, increment);
   }
   
  
   
   public Map<String,String> getAll(String doctorID){
	   Map<String,String> map = redisSvc.hgetAll(getCacheKey(doctorID));
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
   
   public void set(String doctorId , Map<String,String> map){
	   redisSvc.hmset(doctorId, map);
   }
   
   
   private String getCacheKey(String doctorId) {
		return CacheConstant.DOCTOR_ORDER_COUNT.concat(CacheConstant.CACHE_KEY_SPLIT_CHAR).concat(doctorId);
  }
}
