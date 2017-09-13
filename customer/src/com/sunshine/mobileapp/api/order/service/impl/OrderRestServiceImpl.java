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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sunshine.cache.component.DoctorOrderCountCache;
import com.sunshine.cache.component.PatientOrderCountCache;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.RestStatusEnum;
import com.sunshine.mobileapp.api.index.doctorscollect.dao.DoctorUserDao;
import com.sunshine.mobileapp.api.index.doctorscollect.entity.DoctorUser;
import com.sunshine.mobileapp.api.order.dao.OrderDao;
import com.sunshine.mobileapp.api.order.dao.OrderStatusDao;
import com.sunshine.mobileapp.api.order.entity.Order;
import com.sunshine.mobileapp.api.order.entity.OrderStatus;
import com.sunshine.mobileapp.api.order.service.OrderRestService;
import com.sunshine.mobileapp.api.order.vo.OrderNoGenerator;
import com.sunshine.mobileapp.api.order.vo.OrderParamVo;
import com.sunshine.mobileapp.api.order.vo.OrderStatusEnum;
import com.sunshine.mobileapp.api.order.vo.OrderStatusRecourdEnum;
import com.sunshine.mobileapp.api.patient.dao.PatientUserDao;
import com.sunshine.mobileapp.api.patient.entity.PatientUser;
import com.sunshine.mobileapp.api.serviceinfo.dao.DoctorServiceInfoDao;
import com.sunshine.mobileapp.api.serviceinfo.entity.DoctorServiceInfo;
import com.sunshine.mobileapp.api.utils.ValidationUtil;

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
	
	@Autowired
	private OrderStatusDao orderStatusDao;
	
	@Autowired
	private PatientUserDao patientUserDao;
	
	@Autowired
	private DoctorUserDao doctorUserDao;
	
	@Autowired
	private DoctorServiceInfoDao doctorServiceInfoDao;
	
	
	
	private  PatientOrderCountCache patientOrderCountCache = SpringContextHolder.getBean(PatientOrderCountCache.class);
	
	private DoctorOrderCountCache  doctorOrderCountCache = SpringContextHolder.getBean(DoctorOrderCountCache.class);
	
	
	/**
	 * 模拟生成订单
	 * @param order
	 * @return
	 * 
	 * userId 
	 * doctorId
	 * serviceId
	 * 
	 */
	
	@POST
	@Path("generatorOrder")
	@Override
	public RestResponse generatorOrder(Order paramOrder){
		
		// String userId,String doctorId ,String serviceId
		String userId = paramOrder.getUserId();
		String doctorId = paramOrder.getDoctorId();
		String docserviceId = paramOrder.getServiceId();
		
		Order order = new Order();
		PatientUser user = patientUserDao.findById(userId);
		DoctorUser doctor = doctorUserDao.findById(doctorId);
		DoctorServiceInfo docServiceInfo = doctorServiceInfoDao.findById(docserviceId);
		String serviceId =  docServiceInfo.getServiceId();

		order.setUserId(userId);
		order.setUserName(user.getUserName());
		order.setUserPhone(user.getAccount());
		order.setUserImg(user.getUserImg());
		
		order.setInqueireAge(20);
		order.setInquireSex(1);
		order.setInquireName("测试问诊人");
		order.setDoctorId(doctorId);
		order.setDoctorName(doctor.getUserName());
		order.setDoctorPhone(doctor.getAccount());
		order.setDoctorImg(doctor.getUserImg());
		
		order.setServiceId(serviceId);
		order.setAppointmentTime(paramOrder.getAppointmentTime());
		order.setAppointmentTimeStr(paramOrder.getAppointmentTimeStr());
		if("0cf405818513477a82f09f69c70195c8".equals(serviceId)){//电话咨询
			order.setBuyTime(paramOrder.getBuyTime());
			order.setOrderFee(Double.valueOf(docServiceInfo.getPrice()));
			Double fee = paramOrder.getPrice() * paramOrder.getBuyTime() ;
			order.setOrderFee(Double.valueOf(fee.floatValue()));
			order.setOrderDesc("电话咨询服务!");
		}else{
			order.setBuyTime(0);
			order.setOrderFee(Double.valueOf(docServiceInfo.getPrice()));
			order.setOrderDesc("图文咨询服务");
		}
		
		order.setPrice(Double.valueOf(docServiceInfo.getPrice()));
		order.setOrderNo(OrderNoGenerator.genOrderNo(1, 1, 1));
		order.setOrderStatus(OrderStatusEnum.UNPAID.getOrderStatus());

		order.setCt(new Date());
		order.setEt(new Date());
			
		orderDao.insert(order);
	
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrderId(order.getId());
		orderStatus.setChangeTime(new Date());
		orderStatus.setStatus(OrderStatusRecourdEnum.CREATE_TIME.getStatus());
		orderStatusDao.insert(orderStatus);

		//用户待支付数量
		patientOrderCountCache.incrUnpaid(userId, 1);
		
		RestResponse response = new RestResponse();
		response.setStatus(RestStatusEnum.OK);
		response.setData(order);
		
		return response;
		
	}
	
	
	/**
	 * 模拟支付接口
	 * @return
	 */
	@POST
	@Path("pay")
	@Override
	public RestResponse pay(OrderParamVo vo){	
		String orderId = vo.getOrderId();
		Order o = orderDao.findById(orderId);
		String userId = o.getUserId();
		String doctorId = o.getDoctorId();
		
		Order order =new Order();
		order.setId(orderId);
		order.setTradeMode("test");
		order.setTradeOrderNo("001");
		order.setPayTime(new Date());
		order.setPayStatus(2);// 已支付
		order.setEt(new Date());
		order.setOrderStatus(OrderStatusEnum.UNRECEIVE.getOrderStatus());
		
		orderDao.update(order);
	
		
		/**
		 * 记录状态变更
		 */
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrderId(order.getId());
		orderStatus.setChangeTime(new Date());
		orderStatus.setStatus(OrderStatusRecourdEnum.PAY.getStatus());
		orderStatus.setDoctorShow(0); //医生不可见
		orderStatusDao.insert(orderStatus);
		
		OrderStatus orderStatus2 = new OrderStatus();
		orderStatus2.setOrderId(order.getId());
		orderStatus2.setChangeTime(new Date());
		orderStatus2.setStatus(OrderStatusRecourdEnum.UNRECEIVE.getStatus());
		orderStatusDao.insert(orderStatus2);
		
		/**
		 * 更新待处理数据
		 */
		//用户待支付数量,待接诊数量变更
		patientOrderCountCache.incrUnpaid(userId, -1);
		patientOrderCountCache.incrUnreceive(userId, 1);
		
		//医生订单待处理数据
		doctorOrderCountCache.incrUnreceive(doctorId, 1);
		
		RestResponse response = new RestResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("patientOrderCount", patientOrderCountCache.getAll(userId));
		map.put("doctorOrderCount", doctorOrderCountCache.getAll(doctorId));
		map.put("order", orderDao.findById(orderId));
		response.setData(map);
		return response;
	}
	
    
	/**
	 * 
	 * code
	 * unpaid
	 * unreceive
	 * servicing ，uncervicing
	 * finished
	 * returnFee
	 * 
	 * 订单查询 
	 */
	@POST
	@Path("findOrderByPage")
	@Override
	public RestResponse findOrderByPage(OrderParamVo vo ){
		int pageNum = vo.getPageNum();
		int pageSize = vo.getPageSize();
		RestResponse response = new RestResponse();
		
		// 验证参数
		Map<String, Object> validResMap = ValidationUtil.notBlank(vo, new String[] {  "patientId", "code", });
		Boolean isValid = Boolean.valueOf(validResMap.get(ValidationUtil.CHECK_IS_VALID).toString());
		if(!isValid){
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg(validResMap.get(ValidationUtil.CHECK_RES_MSG).toString());
			return response;
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", vo.getPatientId());
			List<Integer> orderStatusList = new ArrayList<Integer>();
		String code = vo.getCode();
		if("unpaid".equals(code)){
			
			params.put("orderStatus",OrderStatusEnum.UNPAID.getOrderStatus() );
			
		}else if("unreceive".equals(code)){
			
			params.put("orderStatus",OrderStatusEnum.UNRECEIVE.getOrderStatus());
			
		}else if("servicing".equals(code)){
			
			orderStatusList.add(OrderStatusEnum.UNSERVICE.getOrderStatus());
			orderStatusList.add(OrderStatusEnum.SERVICING.getOrderStatus());
			params.put("orderStatusList", orderStatusList);
			
		}else if("finished".equals(code)){
			
			orderStatusList.add(OrderStatusEnum.FINISHED.getOrderStatus());
			orderStatusList.add(OrderStatusEnum.UNPAIDCANCLE.getOrderStatus());
			orderStatusList.add(OrderStatusEnum.TRANSED.getOrderStatus());
			params.put("orderStatusList", orderStatusList);
			
		}else if("returnFee".equals(code)){
			
			orderStatusList.add(OrderStatusEnum.REJECTED.getOrderStatus());
			orderStatusList.add(OrderStatusEnum.NOTRANS.getOrderStatus());
			orderStatusList.add(OrderStatusEnum.CANCELED.getOrderStatus());
			orderStatusList.add(OrderStatusEnum.TIMEOUT.getOrderStatus());
			orderStatusList.add(OrderStatusEnum.SERVIC_EXCEPTION.getOrderStatus());
			params.put("orderStatusList", orderStatusList);
			
		}else{
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg("code传参错误!");
			return response;
		}
		
		PageInfo<Order> pageInfo = orderDao.findListByPage(params, new Page<Order>(pageNum, pageSize));
		response.setStatus(RestStatusEnum.OK);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageNum", pageInfo.getPageNum());
		map.put("pageSize", pageInfo.getPageSize());
		map.put("total", pageInfo.getTotal());
		map.put("list", pageInfo.getList());
		response.setData(map);
		return response;
	}
	
	// Arrays.asList(OrderStatusEnum.SERVICING.getOrderStatus(), OrderStatusEnum.UNSERVICE.getOrderStatus()))

	/**
	 * 取消订单
	 */
	@POST
	@Path("cancle")
	@Override
	public RestResponse cancle(Order order){
		RestResponse response = new RestResponse();
		// 验证参数
		Map<String, Object> validResMap = ValidationUtil.notBlank(order, new String[] { "id", "userId", "doctorId", "orderStatus" });
		Boolean isValid = Boolean.valueOf(validResMap.get(ValidationUtil.CHECK_IS_VALID).toString());
		if(!isValid){
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg(validResMap.get(ValidationUtil.CHECK_RES_MSG).toString());
			return response;
		}
		
		int orderStatus = order.getOrderStatus();
		String userId = order.getUserId();
		String doctorId = order.getDoctorId() ;
		String orderId = order.getId();
		String serviceName = order.getServiceName();
		
		// 是否已经支付
		boolean isPaid = false;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", orderId);
		params.put("userId", userId);
		params.put("doctorId", doctorId);
		if(OrderStatusEnum.UNPAID.getOrderStatus() == orderStatus ){
			params.put("orderStatus", OrderStatusEnum.UNPAIDCANCLE.getOrderStatus());
			params.put("oldOrderStatus" , orderStatus);
		}else if(OrderStatusEnum.UNRECEIVE.getOrderStatus() == orderStatus){
			params.put("orderStatus" , OrderStatusEnum.CANCELED.getOrderStatus());
			params.put("oldOrderStatus" , orderStatus);
			isPaid = true;
		}else{
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg("当前状态的订单不能取消!");
			return response;
		}
		
		int updateCount = orderDao.updateOrderSatus(params);
		if(updateCount > 0 ){
			// 插入日志记录
			OrderStatus record = new OrderStatus();
			record.setOrderId(orderId);
			record.setChangeTime(new Date());
			
			if(isPaid){
				record.setStatus(OrderStatusRecourdEnum.CANCLED.getStatus());
				//用户待接诊数量减少1
				patientOrderCountCache.incrUnreceive(userId, -1);
				//医生待接单数量减少1
				doctorOrderCountCache.incrUnreceive(doctorId, -1);
				
				//医生预约时间   
				if(serviceName.indexOf("电话") != -1){ // 电话质询
					/*
					 *    释放  医生的时间 待做
					 * */
				}
			}else{ // 未支付
				record.setStatus(OrderStatusRecourdEnum.UNPAID_CANCLED.getStatus());
				record.setDoctorShow(0);//对医生不可见
				patientOrderCountCache.incrUnpaid(userId, -1);
			}
			
			orderStatusDao.insert(record);
			response.setStatus(RestStatusEnum.OK);
		}else{
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg("取消失败，订单不存在或者");
		}
		
		return response;
	}
	
	/**
	 * 查询订单详情
	 */
	@GET
	@Path("findOrderDetail/{orderId:.*}")
	@Override
	public RestResponse findOrderDetail( @PathParam("orderId") String id){
		RestResponse response = new RestResponse();
		if(id == null || "".equalsIgnoreCase(id.toString())){
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg("订单号不能为空!");
			return response;
		}
		Order order = orderDao.findById(id);
		response.setStatus(RestStatusEnum.OK);
		response.setData(order);
		return response;
	}
	
	/**
	 * 查询订单状态变更
	 */
	@GET
	@Path("findOrderStatus/{orderId:.*}")
	@Override
	public RestResponse findOrderStatusRecord( @PathParam("orderId") String id){
		RestResponse response = new RestResponse();
		if(id == null || "".equalsIgnoreCase(id.toString())){
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg("订单号不能为空!");
			return response;
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orderId", id);
//		List<OrderStatus> orderStatus = orderStatusDao.findListByParams(params);
		response.setStatus(RestStatusEnum.OK);
//		response.setData(orderStatus);
		return response;
	}
	
	

	/**
	 * 服务待质询和
	 * @param vo
	 * @return
	 */
	@POST
	@Path("findServiceOrderByPage")
	@Override
	public RestResponse findServiceOrderByPage(OrderParamVo vo ){
		int pageNum = vo.getPageNum();
		int pageSize = vo.getPageSize();
		RestResponse response = new RestResponse();
		
		// 验证参数
		Map<String, Object> validResMap = ValidationUtil.notBlank(vo, new String[] {  "patientId", "code", });
		Boolean isValid = Boolean.valueOf(validResMap.get(ValidationUtil.CHECK_IS_VALID).toString());
		if(!isValid){
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg(validResMap.get(ValidationUtil.CHECK_RES_MSG).toString());
			return response;
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", vo.getPatientId());
		List<Integer> orderStatusList = new ArrayList<Integer>();
		String code = vo.getCode();
        if("servicing".equals(code)){
			orderStatusList.add(OrderStatusEnum.UNSERVICE.getOrderStatus());
			orderStatusList.add(OrderStatusEnum.SERVICING.getOrderStatus());
			params.put("orderStatusList", orderStatusList);
			
		}else if("finished".equals(code)){
			params.put("orderStatus", OrderStatusEnum.FINISHED.getOrderStatus());
		}else{
			response.setStatus(RestStatusEnum.ERROR);
			response.setMsg("code传参错误!");
			return response;
		}
		
		PageInfo<Order> pageInfo = orderDao.findListByPage(params, new Page<Order>(pageNum, pageSize));
		response.setStatus(RestStatusEnum.OK);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("total",pageInfo.getTotal());
		map.put("list", pageInfo.getList());
		response.setData(map);
		return response;
	}
}
