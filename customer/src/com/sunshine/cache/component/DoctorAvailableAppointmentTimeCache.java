package com.sunshine.cache.component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.sunshine.cache.CacheConstant;
import com.sunshine.common.OrderConstant;
import com.sunshine.common.service.config.DoctorAvailableTimeConfig;
import com.sunshine.common.utils.WeekDay;
import com.sunshine.common.utils.WeekDayUtils;
import com.sunshine.framework.cache.redis.RedisService;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.mobileapp.api.order.planning.AvailableTime;
import com.sunshine.mobileapp.api.order.service.DoctorServiceInfoService;
import com.sunshine.mobileapp.api.serviceinfo.dao.DoctorServiceInfoDao;
import com.sunshine.mobileapp.api.serviceinfo.entity.DoctorServiceInfo;

/**
* @Package：com.sunshine.cache.component   
* @ClassName：DoctorAvailableAppointmentTimeCache   
* @Description：   <p> 医生剩余预约时间缓存</p>
* @Author： - DaoDou 
* @CreatTime：2017年9月11日 下午2:51:04   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
public class DoctorAvailableAppointmentTimeCache {
	
	private static final Logger logger = LoggerFactory.getLogger(DoctorAvailableAppointmentTimeCache.class);
	
	private RedisService redisSvc = SpringContextHolder.getBean(RedisService.class);
	
	@Autowired
	private DoctorAvailableTimeConfigCache doctorAvailableTimeConfigCache;
	
	@Autowired
	private DoctorServiceInfoService doctorServiceInfoService;
	
	/*@Autowired
	private DoctorServiceInfoDao doctorServiceInfoDao;*/
	
	@Autowired
	private DoctorServiceSettingCache doctorServiceSettingCache;
	
	
	private String getDateStr(Date appointmentDate) {
		return new SimpleDateFormat("yyyyMMdd").format(appointmentDate);
	}
	
	public void updateAvailableAppointmentTime(Date appointmentDate, DoctorAvailableTimeConfig config) {
		Assert.notNull(appointmentDate, "医生剩余预约时间缓存，预约日期不能为空");
		Assert.notNull(config, "医生剩余预约时间缓存，缓存对象不能为空");
		Assert.notNull(config.getDoctorId(), "医生剩余预约时间缓存，缓存对象医生Id不能为空");
		List<AvailableTime> list = config.getAvailableTimeList(WeekDayUtils.getWeekDay(appointmentDate));
		updateAvailableAppointmentTime(appointmentDate, config.getDoctorId(), list);
		
	}
	
	public void updateAvailableAppointmentTime(Date appointmentDate, String doctorId, List<AvailableTime> timeList) {
		Assert.notNull(appointmentDate, "医生剩余预约时间缓存，预约日期不能为空");
		Assert.notNull(timeList, "医生剩余预约时间缓存，缓存对象不能为空");
		Assert.notNull(doctorId, "医生剩余预约时间缓存，缓存对象医生Id不能为空");
		
		redisSvc.setex(getCacheKey(doctorId, getDateStr(appointmentDate)), 
				OrderConstant.APPOINTMENT_TIME_ALIVE_IN_SECONDS, 
				DoctorAvailableTimeConfig.toJSONString(timeList));
		
	}
	
	public void cleanAppointmentTimeByWeekDay(WeekDay weekDay, String doctorId, String serviceId, String source, String current) {
		Assert.notNull(weekDay, "清理医生剩余预约时间缓存，预约日期不能为空");
		Assert.notNull(doctorId, "清理医生剩余预约时间缓存，缓存对象医生Id不能为空");
		
		List<Date> matchedDates = WeekDayUtils.getDatesOfWeekDay(weekDay, OrderConstant.APPOINTMENT_DAYS_IN_FUTURE_IN_DAYS);
		List<String> removedKeys = new LinkedList<String>();
		for (Date appointmentDate : matchedDates) {
			removedKeys.add(getCacheKey(doctorId, getDateStr(appointmentDate)));
		}
		if (removedKeys.size() > 0) {
			redisSvc.del(removedKeys.toArray(new String[removedKeys.size()]));
		}
		printLog(doctorId, serviceId, weekDay, source, current);
	}
	
	/**
	 * 
	 * @param appointmentDate 预约日期
	 * @param doctorId
	 * @return 没初始化返回null，无排班返回空集合
	 */
	public List<AvailableTime> getAvailableAppointmentTime(Date appointmentDate, String doctorId) {
		Assert.notNull(appointmentDate, "医生剩余预约时间缓存，预约日期不能为空");
		Assert.notNull(doctorId, "医生剩余预约时间缓存，缓存对象医生Id不能为空");
		
		String value = redisSvc.get(getCacheKey(doctorId, getDateStr(appointmentDate)));
		List<AvailableTime> list = null;
		if (!CacheConstant.CACHE_KEY_NOT_EXIST.equalsIgnoreCase(value) && !StringUtils.isEmpty(value)) {
			list = DoctorAvailableTimeConfig.parse(WeekDayUtils.getWeekDay(appointmentDate), value);
		}
		
		return list;
	}
	
	public List<AvailableTime> setAppointmentTimeAtFirstTime(Date appointmentDate, String doctorId) {
		Assert.notNull(appointmentDate, "医生剩余预约时间缓存，预约日期不能为空");
		Assert.notNull(doctorId, "医生剩余预约时间缓存，缓存对象医生Id不能为空");
		
		logger.info("首次缓存异常剩余预约时间，docotorId:" + doctorId);
		DoctorAvailableTimeConfig config = doctorAvailableTimeConfigCache.getAvailableTimeConfig(doctorId);
		List<AvailableTime> list = null;
		if (config != null) {
			WeekDay weekDay = WeekDayUtils.getWeekDay(appointmentDate);
			String dateStr = getDateStr(appointmentDate);
			list = config.getAvailableTimeList(weekDay);
			String jsonList = DoctorAvailableTimeConfig.toJSONString(list);
			if (jsonList == null) {
				jsonList = "[]";
			}
			long ret = redisSvc.setnx(getCacheKey(config.getDoctorId(), dateStr), jsonList);
			if (ret > 0) {
				redisSvc.expire(getCacheKey(config.getDoctorId(), dateStr), OrderConstant.APPOINTMENT_TIME_ALIVE_IN_SECONDS);
			} else {
				list = getAvailableAppointmentTime(appointmentDate, doctorId);
			}
		} else {
			logger.info("无法从缓存获取医生排班时间配置对象, doctorId:" + doctorId);
			/*List<DoctorServiceInfo> doctorServiceInfoList = doctorServiceInfoDao.getServiceListByUserId(doctorId);*/
			List<DoctorServiceInfo> doctorServiceInfoList = doctorServiceInfoService.getServiceListByUserId(doctorId);
			if (doctorServiceInfoList != null) {
				for (DoctorServiceInfo s : doctorServiceInfoList) {
					if (doctorServiceSettingCache.shouldCacheConfig(s)) {
						config = DoctorAvailableTimeConfig.buildAvailableTimeConfig(s);
						doctorAvailableTimeConfigCache.updateAvailableTimeConfigCache(config);
						WeekDay weekDay = WeekDayUtils.getWeekDay(appointmentDate);
						String dateStr = getDateStr(appointmentDate);
						list = config.getAvailableTimeList(weekDay);
						String jsonList = DoctorAvailableTimeConfig.toJSONString(list);
						if (jsonList == null) {
							jsonList = "[]";
						}
						long ret = redisSvc.setnx(getCacheKey(config.getDoctorId(), dateStr), jsonList);
						if (ret > 0) {
							redisSvc.expire(getCacheKey(config.getDoctorId(), dateStr), OrderConstant.APPOINTMENT_TIME_ALIVE_IN_SECONDS);
						} 
					}
				}
			}
			
		}
		return list;
	}
	
	private void printLog(String doctorId, String serviceId, WeekDay weekDay, String source, String current) {
		logger.info("医生[{}]服务[{}]星期[{}]排班时间配置发生变更，配置从:{}变更为:{}, 清除对应日期剩余的预约时间", doctorId, serviceId, weekDay.getCode(), source, current);
	}
	
	/**
	 * 
	 * @param doctorId
	 * @param appointmentDate - eg. 20170112
	 * @return
	 */
	private String getCacheKey(String doctorId, String appointmentDate) {
		return CacheConstant.DOCTOR_APPOINTMENT_TIME_KEY_PREFIX.concat(CacheConstant.CACHE_KEY_SPLIT_CHAR)
				.concat(appointmentDate)
				.concat(CacheConstant.CACHE_KEY_SPLIT_CHAR).concat(doctorId);
	}
}
