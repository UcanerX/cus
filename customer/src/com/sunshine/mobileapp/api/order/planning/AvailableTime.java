/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017-6-27</p>
 *  <p> Created by sun001</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.order.planning;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sunshine.common.utils.WeekDay;


/**
 * @Project: easy_health 
 * @Package: com.sunshine.common.service.order
 * @ClassName: AvailableTime
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 甘松
 * @Create Date: 2017-6-27
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class AvailableTime implements Comparable<AvailableTime> {
	private WeekDay weekDay;
	private List<AvailableTime> availableTimeList;
	
	private long startTime;
	private long endTime;
	
	private boolean offsetHeadTime = false;
	private boolean offsetTailTime = false;
	
	private static final int offsetTimeLong = 5 * 60 * 1000;
	
	public AvailableTime(WeekDay weekDay, List<AvailableTime> availableTimeList, long startTime, long endTime) {
		this(weekDay,availableTimeList,startTime, endTime, false, false);
	}
	
	public AvailableTime(WeekDay weekDay, List<AvailableTime> availableTimeList, long startTime, long endTime, boolean offsetHeadTime, boolean offsetTailTime) {
		this.weekDay = weekDay;
		this.availableTimeList = availableTimeList;
		this.startTime = startTime;
		this.endTime = endTime;
		this.offsetHeadTime = offsetHeadTime;
		this.offsetTailTime = offsetTailTime;
	}
	
	/**
	 * 分布式部署，采用分布式锁
	 * @param plan
	 * @return
	 */
	public synchronized boolean allocate(OrderTimePlan plan) {
		long appointmentStartTime = plan.getAppointmentStartTime();
		long appointmentEndTime = plan.getAppointmentEndTime();
		long timeLong = plan.getTimeLong();
		long serviceStartTime = -1;
		long serviceEndTime = -1;
		
		if (startTime > 0 && timeLong > 0 && this.startTime + timeLong <= endTime) {// 时间是否足够
			if (startTime <= appointmentStartTime && appointmentEndTime <= endTime && appointmentStartTime + timeLong <= appointmentEndTime) {
				//	|_ _ _|   appointmentTime
				//| _ _ _ _ _ _|availableTime
				
				serviceStartTime = appointmentStartTime;
				serviceEndTime = appointmentStartTime + timeLong;
				
				//分隔时间
				if (startTime < serviceStartTime) {
					availableTimeList.add(new AvailableTime(this.weekDay, this.availableTimeList, startTime, serviceStartTime));
				}
				if (serviceEndTime < endTime) {
					availableTimeList.add(new AvailableTime(this.weekDay, this.availableTimeList, serviceEndTime, endTime));
				}
				
				startTime = -1;
				endTime = -1;
				plan.setScheduled(true);
				
			} else if (startTime <= appointmentStartTime  && appointmentStartTime < endTime) {
				
				//		|_ _ __ _ _|   appointmentTime
				//| _ _ _ _ _ _|availableTime
				if (appointmentStartTime + timeLong <= this.endTime) {
					serviceStartTime = endTime - timeLong;
					serviceEndTime = endTime;
					
					endTime = serviceStartTime;
					
					plan.setScheduled(true);
				} 
			} else if (appointmentStartTime <= startTime && startTime < appointmentEndTime) {
				//|_ _ __ _ _|   appointmentTime
				//     | _ _ _ _ _ _|availableTime
				if (startTime + timeLong <= endTime) {
					serviceStartTime = startTime;
					serviceEndTime = startTime + timeLong;
					
					startTime = serviceEndTime;
					plan.setScheduled(true);
				} 
			}
		}
		if (plan.isScheduled()) {
			plan.setServiceStartTime(serviceStartTime);
			plan.setServiceEndTime(serviceEndTime);
			
			// for debug
			/*SimpleDateFormat t = new SimpleDateFormat("HH:mm");
			System.out.println("订单预约时间：" 
					+ t.format(new Date(plan.getAppointmentStartTime()))
					+ "-"
					+ t.format(new Date(plan.getAppointmentEndTime())) 
					+ " 时长：" + plan.getTimeLong()/(60 * 1000) + "分钟");
			
			System.out.println("订单服务时间：" 
					+ t.format(new Date(plan.getServiceStartTime()))
					+ "-"
					+ t.format(new Date(plan.getServiceEndTime())));*/
		}
		return plan.isScheduled();
	}
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (startTime > 0) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(startTime);
			s.append(c.get(Calendar.HOUR_OF_DAY)).append(":").append(c.get(Calendar.MINUTE) > 9 ? c.get(Calendar.MINUTE) : "0" + c.get(Calendar.MINUTE));
			s.append("-");
			c.setTimeInMillis(endTime);
			s.append(c.get(Calendar.HOUR_OF_DAY)).append(":").append(c.get(Calendar.MINUTE) > 9 ? c.get(Calendar.MINUTE) : "0" + c.get(Calendar.MINUTE));
		}
		return s.toString();
	}

	@Override
	public int compareTo(AvailableTime o) {
		if (o == null) {
			throw new IllegalStateException("排序时出现空元素!");
		}
		if(startTime < o.startTime) {
			return -1;
		} else if(startTime > o.startTime) {
			return 1;
		}
		return 0;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getWeekDay() {
		return this.weekDay.getCode();
	}
	
	
}