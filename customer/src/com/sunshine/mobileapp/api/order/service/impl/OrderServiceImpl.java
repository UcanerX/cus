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
package com.sunshine.mobileapp.api.order.service.impl;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sunshine.cache.component.SysServiceCache;
import com.sunshine.common.MessageCentorConstant;
import com.sunshine.framework.mvc.mysql.dao.BaseDao;
import com.sunshine.framework.mvc.mysql.service.impl.BaseServiceImpl;
import com.sunshine.mobileapp.api.index.serviceinfo.entity.DoctorServiceInfo;
import com.sunshine.mobileapp.api.order.dao.OrderDao;
import com.sunshine.mobileapp.api.order.dao.OrderStatusDao;
import com.sunshine.mobileapp.api.order.entity.Order;
import com.sunshine.mobileapp.api.order.entity.OrderStatus;
import com.sunshine.mobileapp.api.order.service.DoctorServiceInfoService;
import com.sunshine.mobileapp.api.order.service.OrderService;
import com.sunshine.mobileapp.api.order.vo.OrderStatusEnum;


/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.ordermanager.service.impl
 * @ClassName: OrderServiceImpl
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年6月6日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, String> implements OrderService {

	@Autowired
	protected OrderDao orderDao;
	
	@Autowired 
	private OrderStatusDao orderStatusDao;
	
	@Autowired
	private DoctorServiceInfoService doctorServiceInfoService;
	
	@Autowired
	private SysServiceCache sysServiceCache;

	@Override
	protected BaseDao<Order, String> getDao() {
		return orderDao;
	}
	
	@Override
	public String insert(Order order) {
		String orderId = orderDao.insert(order);
		//插入订单状态
		OrderStatus orderStatus = new OrderStatus(orderId,OrderStatusEnum.UNRECEIVE.getOrderStatus(),
				OrderStatusEnum.UNRECEIVE.getOrderStatusLabel(),new Date());
		orderStatusDao.insert(orderStatus);
	/*	//推送消息
		MsgPushServiceImpl  msgPush = SpringContextHolder.getBean(MsgPushServiceImpl.class);
		msgPush.msgPush(MsgPushConstant.MSG_LIBRARY_TYPE_NOTICE, MsgPushConstant.RECEIVE_TYPE_DOCTOR, 
				order.getServiceMsgCode(),order.getDoctorPhone(),createMsgByOrder(order));*/
		return orderId;
	}
	
	public Map<String, Serializable> createMsgByOrder(Order order){
		DecimalFormat format = new DecimalFormat("0.##"); //00.#
		Map<String, Serializable> map = new HashMap<String,Serializable>();
		map.put("patientName", order.getUserName());
		map.put("patientImg","#");
		if(MessageCentorConstant.YS_TWZX_MESSAGE_CODE.equals(order.getServiceMsgCode())){
			String orderDesc= order.getOrderDesc();
			if(orderDesc != null && orderDesc.length()>20){
				map.put("descript", orderDesc.substring(0, 20));
			}else{
				map.put("descript", order.getOrderDesc());
			}
		}
		if(MessageCentorConstant.YS_DHZX_MESSAGE_CODE .equals(order.getServiceMsgCode())){
			map.put("buyTime",order.getBuyTime());
		}
		
		map.put("inquiryTime", order.getAppointmentDateTime());
		map.put("totalAmount",format.format(order.getOrderFee()));
		map.put("orderId",order.getId());
		map.put("msgCategory",MessageCentorConstant.SERVICE_MESSAGE);//服务消息
		
		return map;
	}


	@Override
	public  PageInfo<String>  findTransDoc(Map<String, Object> params, Page<String> page) {
		return orderDao.findTransDoc(params,page);
	}
	
	public List<Map<String,Object>>  findDocInfo(List<String> params){
		return orderDao.findDocInfo(params);
	}
	
	public String insertTransTreatment(Map<String,Object> param, Order newOrder){
		//更新原来的订单状态
	//	orderDao.updateByParams(param);
		orderDao.insert(newOrder);
		return newOrder.getOrderNo();
	}


	@Override
	public int countWaitDeal(String doctorId) {
		return orderDao.countWaitDeal(doctorId);
	}


	@Override
	public int countService(String doctorId) {
		return orderDao.countService(doctorId);
	}
	
	@Override
	public int countOver(String doctorId) {
		return orderDao.countOver(doctorId);
	}

	@Override
	public int updateOrderStatus(String orderId,Integer orderStatus,Integer oldOrderStatus, String doctorId, String serviceId){
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id", orderId);
		params.put("orderStatus",orderStatus);
		params.put("oldOrderStatus",oldOrderStatus);
		params.put("doctorId",doctorId);
		params.put("et", new Date());
		// 加入服务类型作为控制参数，图文订单和电话订单 接单和拒诊 SQL逻辑不通 by gansong 
		params.put("serviceType", sysServiceCache.getConsultServiceType(serviceId));
		// end
		int row = orderDao.updateOrderSatus(params);
		if(row > 0){
			OrderStatus status = new OrderStatus(orderId,orderStatus,new Date());
			orderStatusDao.insert(status);
		}
		return row;
	}
	
	
/*	public void  updateOrderStatus(String orderId,String doctorId,OrderStatusEnum orderStatusEnum){
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("conditionDoctorId", doctorId);
		params.put("id", orderId);
		params.put("orderStatus",orderStatusEnum.getOrderStatus());
		params.put("et", new Date());
		orderDao.updateByParams(params);
		
		OrderStatus orderStatus = new OrderStatus(orderId,
		orderStatusEnum.getOrderStatus(),
		orderStatusEnum.getOrderStatusLabel(),new Date());
		orderStatusDao.insert(orderStatus);
	}*/

	public Order generateOrder(Order unsavedOrder) {
		unsavedOrder = new Order();
		
		String doctorId = "f2aa86e50fc545bdb0a5b600b3a868c2";
		List<DoctorServiceInfo> enabledDoctorServSettings = doctorServiceInfoService.getServiceListByUserId(doctorId);
		
		if (enabledDoctorServSettings != null) {
			DoctorServiceInfo setting = enabledDoctorServSettings.get(0);
			String availableTimeStr = setting.getStMon();
			if (!StringUtils.isEmpty(availableTimeStr)) {
				String[] availableTimeArr = availableTimeStr.split("|");
				for (String s : availableTimeArr) {
					System.out.println(s);
				}
			}
		}
//		unsavedOrder.setOrderNo(OrderNoGenerator.genOrderNo(1, 1, 1));
		
		return null;
	}
}
