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
 * @ClassName: OrderTimePlan
 * @Description: <p>订单计划</p>
 * @JDK version used: 
 * @Author: 甘松
 * @Create Date: 2017-6-27
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface OrderTimePlan {
	
	public long getAppointmentStartTime();
	
	public long getAppointmentEndTime();
	
	public long getTimeLong();
	
	public void setServiceStartTime(long serviceStartTime);
	
	public void setServiceEndTime(long serviceEndTime);
	
	public long getServiceStartTime();
	
	public long getServiceEndTime();
	
	public boolean isScheduled();
	
	public void setScheduled(boolean isScheduled);
}
