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
package com.sunshine.mobileapp.api.order.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.RestStatusEnum;
import com.sunshine.mobileapp.api.order.dao.OrderDao;
import com.sunshine.mobileapp.api.order.entity.Order;
import com.sunshine.mobileapp.api.order.service.OrderRestService;

/**
 * @Project: easy_health_client2 
 * @Package: com.sunshine.mobileapp.api.order.service.impl
 * @ClassName: orderRestServiceImpl
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年9月6日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Path("v1/order")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class OrderRestServiceImpl implements OrderRestService {
	
	@Autowired
	private OrderDao orderDao;
	/*	
	private OrderStatusDao orderStatusDao;
	
	private  PatientOrderCountCache patientOrderCountCache = SpringContextHolder.getBean(PatientOrderCountCache.class);
*/	
	@GET
	@Path("/{id:.*}")
	@Override
	public RestResponse findOrder( @PathParam("id") String id) {
		// TODO Auto-generated method stub
		Order order = orderDao.findById(id);
		RestResponse response = new RestResponse();
	
		response.setStatus(RestStatusEnum.OK);
		response.setData(order);
		return response;
	}
	
	
/*	public RestResponse findOrder( String patientId,int orderStatus) {
		RestResponse response = new RestResponse();
		if(StringUtils.isBlank(patientId)){
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg("patientId不能为空!");
			return response;
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userId", patientId);
		paramMap.put("orderStatus",orderStatus);
		try{
			List<Order> orderList =orderDao.findListByParams(paramMap);
			response.setStatus(RestStatusEnum.OK);
			response.setData(orderList);
		}catch (Exception e){
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg("查询数据库错误");
		}
		return response;
	} */
    

	/**
	 * 查询待支付接口
	 * @param patientId
	 * @return
	 *//*
	@GET
	@Path("findUnpaidOrder/{patientId:.*}")
//	@Override
	public RestResponse findUnpiadOrder( @PathParam("patientId") String patientId) {
		return findOrder(patientId,OrderStatusEnum.UNPAID.getOrderStatus());
	}*/

	/* (non-Javadoc)
	 * @see com.sunshine.mobileapp.api.order.service.OrderRestService#findOrder(java.lang.String)
	 */
/*	@Override
	public RestResponse findOrder(String id) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	/**
	 * 咨询中
	 * @return
	 */
	/*public RestResponse findServiceOrder(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orderStatusList", Arrays.asList(OrderStatusEnum.SERVICING.getOrderStatus(), OrderStatusEnum.UNSERVICE.getOrderStatus()));
	    
	}*/

	/**
	 * 取消
	 * @param patientId
	 * @param orderId
	 * @return
	 */
/*	@POST
	public RestResponse cancleOrder( @PathParam("patientId") String patientId , String orderId){
		patientOrderCountCache.incrUnpaid(patientId, -1);
		Order order = new Order();
		orderDao.update(order);
		return null;
	}*/
	
	/*@GET
	@Path("findUnpaidOrder/{patientId:.*}")
//	@Override
	public RestResponse findUnReceiveOrder( @PathParam("patientId") String patientId) {
		return findOrder(patientId,OrderStatusEnum.UNPAID.getOrderStatus());
	}
	
	
	@GET
	@Path("findUnpaidOrder/{patientId:.*}")
//	@Override
	public RestResponse findServiceOrder( @PathParam("patientId") String patientId) {
		return findOrder(patientId,OrderStatusEnum.UNPAID.getOrderStatus());
	}
	
	@GET
	@Path("findUnpaidOrder/{patientId:.*}")
//	@Override
	public RestResponse findServiceOrder1( @PathParam("patientId") String patientId) {
		return findOrder(patientId,OrderStatusEnum.UNPAID.getOrderStatus());
	}*/
	
	

	
	
	
/*	public RestResponse findOrder(){
		//orderDao.findListByParams();
	}*/
	
	
/*	@POST
	@Path("/{addOrder:.*}")*/
/*	public RestResponse generatorOrder(Order order){
		String userId = order.getUserId();
		String doctorId = order.getDoctorId();

		// 查询用户信息
		PatientUser user = null;
		
		// 查询 医生信息
		
		//生成订单
		orderDao.insert(order);
		//未支付数增加1
		patientOrderCountCache.incrUnpaid(userId, 1);
		//
		OrderStatus orderStatus = new OrderStatus();
		
		orderStatusDao.insert(orderStatus);
		
		return null;
	}*/
	


}
