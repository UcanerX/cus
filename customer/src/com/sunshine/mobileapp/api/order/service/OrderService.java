/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年6月6日</p>
 *  <p> Created by 钟乳石</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.order.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sunshine.framework.mvc.service.BaseService;
import com.sunshine.mobileapp.api.order.entity.Order;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.ordermanager.service
 * @ClassName: OrderService
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年6月6日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface OrderService extends BaseService<Order, String> {
	public PageInfo<String> findTransDoc(Map<String, Object> params, Page<String> page);

	public List<Map<String, Object>> findDocInfo(List<String> params);

	public String insertTransTreatment(Map<String, Object> param, Order newOrder);

	public int countWaitDeal(String doctorId);

	public int countService(String doctorId);

	public int countOver(String doctorId);

	public int updateOrderStatus(String orderId, Integer orderStatus, Integer oldOrderStatus, String doctorId, String serviceId);

	public Order generateOrder(Order unsavedOrder);
}
