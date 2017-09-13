/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年9月6日</p>
 *  <p> Created by 钟乳石</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.order.service;

import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.order.entity.Order;
import com.sunshine.mobileapp.api.order.vo.OrderParamVo;

/**
 * @Project: easy_health_client2 
 * @Package: com.sunshine.mobileapp.api.order.service
 * @ClassName: orderService
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年9月6日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public interface OrderRestService {

  public RestResponse findOrderByPage(OrderParamVo vo);
  
  public RestResponse generatorOrder(Order paramOrder);


	/**
	 * @param order
	 * @return
	 */
	RestResponse cancle(Order order);
	/**
	 * @param id
	 * @return
	 */
	RestResponse findOrderDetail(String id);

	/**
	 * @param id
	 * @return
	 */
	RestResponse findOrderStatusRecord(String id);

	/**
	 * @param vo
	 * @return
	 */
	RestResponse pay(OrderParamVo vo);

	/**
	 * @param vo
	 * @return
	 */
	RestResponse findServiceOrderByPage(OrderParamVo vo);
}
