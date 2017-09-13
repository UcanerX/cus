/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年6月8日</p>
 *  <p> Created by 钟乳石</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.framework.mvc.mysql.service.impl.BaseServiceImpl;
import com.sunshine.mobileapp.api.order.dao.OrderStatusDao;
import com.sunshine.mobileapp.api.order.entity.OrderStatus;
import com.sunshine.mobileapp.api.order.service.OrderStatusService;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.ordermanager.service.impl
 * @ClassName: OrderStatusServiceImpl
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年6月8日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Service
public class OrderStatusServiceImpl extends BaseServiceImpl<OrderStatus, String> implements OrderStatusService {

	@Autowired
	private OrderStatusDao orderstatusDao;

	@Override
	protected BaseDao<OrderStatus, String> getDao() {
		return orderstatusDao;
	}

}
