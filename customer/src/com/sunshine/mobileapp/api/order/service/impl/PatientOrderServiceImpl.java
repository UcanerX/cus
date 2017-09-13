package com.sunshine.mobileapp.api.order.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunshine.cache.component.DoctorAvailableAppointmentTimeCache;
import com.sunshine.cache.component.DoctorServiceSettingCache;
import com.sunshine.common.OrderConstant;
import com.sunshine.common.exception.IllegalDateException;
import com.sunshine.common.exception.OrderException;
import com.sunshine.common.utils.OrderNoGenerator;
import com.sunshine.common.utils.WeekDay;
import com.sunshine.common.utils.WeekDayUtils;
import com.sunshine.framework.utils.DateUtils;
import com.sunshine.mobileapp.api.order.entity.Order;
import com.sunshine.mobileapp.api.order.planning.AutoOrderScheduler;
import com.sunshine.mobileapp.api.order.planning.AvailableTime;
import com.sunshine.mobileapp.api.order.planning.OrderTimePlan;
import com.sunshine.mobileapp.api.order.planning.ServiceOrderTimePlan;
import com.sunshine.mobileapp.api.order.service.DoctorServiceInfoService;
import com.sunshine.mobileapp.api.order.service.OrderStatusService;
import com.sunshine.mobileapp.api.order.service.PatientOrderService;
import com.sunshine.mobileapp.api.patient.dao.PatientUserDao;
import com.sunshine.mobileapp.api.serviceinfo.entity.DoctorServiceInfo;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.common.datas.cache.component
 * @ClassName: DoctorAvailableTimeConfigCache
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 医生咨询服务订单Service类
 * @Create Date: 2017年7月12日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */

@Service
public class PatientOrderServiceImpl extends OrderServiceImpl implements PatientOrderService {
	private static final Logger logger = LoggerFactory.getLogger(PatientOrderService.class);
	
	@Autowired
	private DoctorServiceInfoService doctorServiceInfoService;
	
	@Autowired
	private PatientUserDao patientUserDao;
	
	@Autowired
	private DoctorServiceSettingCache doctorServiceSettingCache;
	
	@Autowired
	private DoctorAvailableAppointmentTimeCache doctorAvailableAppointmentTimeCache;
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	private AutoOrderScheduler scheduler = new AutoOrderScheduler();
	
	/**
	 * 生成订单
	 */
	public Order generateOrder(Order unsavedOrder, String appointmentDate, String appointmentTime) {
		
		try {
			long diffTime = DateUtils.DateDiffInMillis(new Date(), DateUtils.getAppointmentEndTime(appointmentDate, appointmentTime));
			if (diffTime < OrderConstant.APPOINTMENT_LEAST_ACCEPT_TIME_IN_MILLIS || diffTime > OrderConstant.APPOINTMENT_DAYS_IN_FUTURE_IN_MILLIS) {
				throw new IllegalDateException("预约日期时间不在规定的时间内[" + appointmentDate + " " + appointmentTime + "]", -1);
			}
			
			Date appointmentDateObj = DateUtils.StringToDate(appointmentDate);
			DoctorServiceInfo setting = null;
			setting = doctorServiceSettingCache.getDoctorServiceConsultSetting(unsavedOrder.getDoctorId(), unsavedOrder.getServiceId());
			
			unsavedOrder.setPrice(Double.valueOf(setting.getPrice()));
			Double fee = unsavedOrder.getBuyTime() > 0 ? unsavedOrder.getPrice() * unsavedOrder.getBuyTime() : unsavedOrder.getPrice();
			unsavedOrder.setOrderFee(fee);
			unsavedOrder.setCt(new Date());
			unsavedOrder.setEt(new Date());
			
			int buyTime = unsavedOrder.getBuyTime();
			// 排班
			if (buyTime > 0) {
				
				if (setting.getLeastBuyTime() > buyTime) {
					throw new IllegalDateException("购买时长小于最小时长[" + setting.getLeastBuyTime() + "]" , -1);
				}
				WeekDay weekDay = WeekDayUtils.getWeekDay(appointmentDate);
				
				// 集群部署使用分布式锁，后续优化锁的范围 by 甘松
				synchronized(weekDay) {
					List<AvailableTime> timeList = doctorAvailableAppointmentTimeCache.getAvailableAppointmentTime(appointmentDateObj, unsavedOrder.getDoctorId());
					if (timeList == null) {
						timeList = doctorAvailableAppointmentTimeCache.setAppointmentTimeAtFirstTime(appointmentDateObj, unsavedOrder.getDoctorId());
					}
					
					if (timeList != null) {
						
						Date[] dates = DateUtils.parseDates(appointmentTime);
						validateAppointmentTime(dates[0], dates[1]);
						OrderTimePlan plan = scheduler.schedule(
								new ServiceOrderTimePlan(dates[0].getTime(), dates[1].getTime(), buyTime * 60 * 1000), timeList);
						
						if (plan != null) {
							unsavedOrder.setOrderNo(OrderNoGenerator.genOrderNo(1, 1, 1));
							unsavedOrder.setStartTime(new Date(plan.getServiceStartTime()));
							unsavedOrder.setEndTime(new Date(plan.getServiceEndTime()));
							
							this.insert(unsavedOrder);
//							orderStatusService.insert(
//									new OrderStatus(unsavedOrder.getOrderNo(), OrderStatusEnum.UNRECEIVE.getOrderStatus(), null, new Date()));
							// 更新剩余预约时间缓存
							doctorAvailableAppointmentTimeCache.updateAvailableAppointmentTime(appointmentDateObj, unsavedOrder.getDoctorId(), timeList);
							return unsavedOrder;
						} else {
							throw new IllegalDateException("医生[" + unsavedOrder.getDoctorName() + "] " 
									+  appointmentDate + "排班已满或没有排班!", -1);
						}
					} else {
						throw new IllegalDateException("医生[" + unsavedOrder.getDoctorName() + "] " 
								+  appointmentDate + "没有排班!", -1);
					}
				}
			} else {
				String[] timePoints = appointmentTime.split("-");
				Date startTime = DateUtils.getBaseTime(timePoints[0] + ":00");
				Date endTime = DateUtils.getBaseTime(timePoints[1]  + ":00");
				validateAppointmentTime(startTime, endTime);
				unsavedOrder.setStartTime(startTime);
				unsavedOrder.setEndTime(endTime);
				
				if (startTime == null || endTime == null) {
					throw new IllegalDateException("预约时间段无法解析[" + appointmentTime + "]");
				}
				unsavedOrder.setOrderNo(OrderNoGenerator.genOrderNo(1, 1, 1));
				this.insert(unsavedOrder);
//				orderStatusService.insert(
//						new OrderStatus(unsavedOrder.getOrderNo(), OrderStatusEnum.UNRECEIVE.getOrderStatus(), null, new Date()));
				return unsavedOrder;
			}
			
		} catch (Exception e) {
			if (e instanceof OrderException) {
				throw (OrderException)e;
			} else {
				throw new RuntimeException("生成订单失败", e);
			}
		}
	}
	
	private void validateAppointmentTime(Date startTime, Date endTime) {
		if (startTime.getTime() >= endTime.getTime()) {
			throw new IllegalDateException("预约时间段开始时间大于结束时间");
		}
	}
}
