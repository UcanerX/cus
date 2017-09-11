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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sunshine.common.utils.WeekDay;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.common.service.order
 * @ClassName: AutoOrderScheduler
 * @Description: <p>订单执行时间编排器</p>
 * @JDK version used: 
 * @Author: 甘松
 * @Create Date: 2017-6-27
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class AutoOrderScheduler {
	Map<WeekDay, List<AvailableTime>> timeMap = new HashMap<WeekDay, List<AvailableTime>>();
	
	public OrderTimePlan schedule(OrderTimePlan plan, List<AvailableTime> timeList) {
//		System.out.print("可用排班时间：");
//		this.toString(timeList);
		for (AvailableTime t : timeList) {
			if (t.allocate(plan)) {
				break;
			}
		}
//		System.out.print("剩余排班时间：");
//		this.toString(timeList);
		
		return plan.isScheduled() ? plan : null;
	}
	
	public String toString(List<AvailableTime> list) {
		List<String> result = new ArrayList<String>();
		for (AvailableTime t : list) {
			result.add(t.toString() + " ");
		}
		Collections.sort(result);
		
//		System.out.println(result);
		
		return null;
	}
}
