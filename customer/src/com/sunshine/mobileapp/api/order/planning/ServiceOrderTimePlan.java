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

/**
 * @Project: easy_health 
 * @Package: com.sunshine.common.service.order
 * @ClassName: ServiceOrderTimePlan
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 甘松
 * @Create Date: 2017-6-27
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class ServiceOrderTimePlan implements OrderTimePlan {
	private long appointmentStartTime;
	private long appointmentEndTime;
	private long timeLong;
	
	private long serviceStartTime;
	private long serviceEndTime;
	
	private boolean isScheduled = false;
	
	public ServiceOrderTimePlan(long appointmentStartTime, long appointmentEndTime, long timeLong) {
		this.appointmentStartTime = appointmentStartTime;
		this.appointmentEndTime = appointmentEndTime;
		this.timeLong = timeLong;
	}
	@Override
	public long getAppointmentStartTime() {
		return appointmentStartTime;
	}

	@Override
	public long getAppointmentEndTime() {
		return appointmentEndTime;
	}

	@Override
	public long getTimeLong() {
		return timeLong;
	}
	
	@Override
	public void setServiceStartTime(long serviceStartTime) {
		this.serviceStartTime = serviceStartTime;
	}
	
	@Override
	public void setServiceEndTime(long serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
	}
	@Override
	public long getServiceStartTime() {
		return this.serviceStartTime;
	}
	@Override
	public long getServiceEndTime() {
		return this.serviceEndTime;
	}
	@Override
	public boolean isScheduled() {
		return this.isScheduled;
	}
	@Override
	public void setScheduled(boolean isScheduled) {
		this.isScheduled = isScheduled;
	}
}
