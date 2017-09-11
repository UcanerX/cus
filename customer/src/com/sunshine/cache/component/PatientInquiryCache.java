/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年8月30日</p>
 *  <p> Created by 钟乳石</p>
 *  </body>
 * </html>
 */
package com.sunshine.cache.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sunshine.cache.CacheConstant;
import com.sunshine.framework.cache.redis.impl.RedisServiceImpl;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.common.datas.cache.component
 * @ClassName: PatientConsultCache
 * @Description: <p>咨询次数和咨询金额缓存</p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年8月30日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class PatientInquiryCache {
	
	private RedisServiceImpl redisSvc = SpringContextHolder.getBean(RedisServiceImpl.class);
	 
    public static final String INQUIRY_TIMES = "inquiryTimes";
    
    public static final String INQUIRY_FEES = "inquiryFees";
    
    public static final String LAST_LOGINTIME = "lastLoginTime" ;
    
    
 
    
    /**
     * 批量获取 用户的 咨询次数,咨询金额，登陆时间
     * @param key
     * @param field
     */
   public List<List<String>> pipelineGetInquiry(List<String> keys ,String ...fields){ 	
    	Jedis jedis = redisSvc.getRedisClient();	
		if (jedis == null) {
			return null;
		}
		
    	Pipeline p = jedis.pipelined();
    	List<Response<List<String>>> responseList = new ArrayList<Response<List<String>>>();
    	
		for(int i = 0 ; i < keys.size() ;i++){
			responseList.add(p.hmget(getCacheKey(keys.get(i)), fields));
		}
		p.sync();
		List<List<String>> result =  new ArrayList<List<String>>();
		for(int i=0 ; i<responseList.size() ; i++){
			result.add(responseList.get(i).get());
		}
		return result;
    }
	
			
   public void incrInquiryTimes(String patientId ,int increment){
	   redisSvc.hincrBy(getCacheKey(patientId), INQUIRY_TIMES, increment);
   }
   
   public void incrInquiryFees(String patientId ,int increment ){
	   redisSvc.hincrBy(getCacheKey(patientId), INQUIRY_FEES, increment);
   }
	
   public void setLastLoginTime(String patientId ,String datetime){
	   redisSvc.hset(getCacheKey(patientId), LAST_LOGINTIME , datetime);
   }
	
   /**
    * 返回用户
    * @param patientId
    * @return
    */
   public Map<String,String> getInquiry(String patientId){
	   return redisSvc.hgetAll(getCacheKey(patientId));
   }
	
   private String getCacheKey(String patientId) {
		return CacheConstant.PATIENT_INQUIRY_STATISTIC_PREFIX.concat(CacheConstant.CACHE_KEY_SPLIT_CHAR).concat(patientId);
   }
	
   /* private String getLastLogintimeKey(String patientId){
    	return CacheConstant.PATIENT_LAST_LOGINTIME.concat(CacheConstant.CACHE_KEY_SPLIT_CHAR).concat(patientId);
    }*/
}
