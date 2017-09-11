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
  public RestResponse findOrder(String id);
  
/*  public RestResponse findUnpiadOrder(String patientId);*/
/*  public RestResponse getOrderCount(String patientId);*/
}
