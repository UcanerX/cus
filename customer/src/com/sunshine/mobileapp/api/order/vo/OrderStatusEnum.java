/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年6月12日</p>
 *  <p> Created by 钟乳石</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.order.vo;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.ordermanager
 * @ClassName: OrderStatusEnum
 * @Description: <p>订单状态</p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年6月12日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */



public enum OrderStatusEnum {
	/* 管理后台，医生状态，病人状态*/
   UNPAID(0,"待支付","待支付","待支付"),
   UNRECEIVE(1,"待接诊","待接诊","待接诊"),
   UNSERVICE(2,"待服务","待服务","待服务"),
   SERVICING(3,"服务中","服务中","服务中"),
   FINISHED(4,"已完成","已完成","已完成"),
   SERVIC_EXCEPTION(5,"服务异常","服务异常","服务异常"),
   REJECTED(6,"医生已拒绝","已拒绝 ","医生已拒绝"),//已拒绝 
   TRANSED(7,"已转诊","已拒绝","已拒绝"),
   NOTRANS(8,"拒转诊","已拒绝","已拒绝"), // 可以退款了
   CANCELED(9,"用户已取消","用户已取消","已取消"), //用户在医生接诊前取消订单
   TIMEOUT(10,"已过期","已过期","已过期"),// 预约时间到了还没有进行接诊
   UNPAIDCANCLE(11,"已取消","已取消","已取消");
/*   USER_CANCLE_RETURNFEEING(11,"用户取消退费中","用户已取消","退费中"),//用户取消而退款
   DOC_REJECT_RETURNFEEING(12,"医生拒绝退款中","已拒绝","退费中"), // 医生拒绝而退款
   TIMEOUT_RETURNFEEING(13,"已过期退款中","已过期","退费中"), // 
   //服务异常退款  
   USER_CANCLE_RETURNFEEED(14,"已退费","用户已取消","已退费"),  // 
   DOC_REJECT_RETURNFEEED(15,"已退费","已拒绝","已退费"),  // 
   TIMEOUT_RETURNFEEED(16,"已退费","已过期","已退费");
   */
   private int orderStatus;
   private String orderStatusLabel;
   private String docOrderStatusLabel;
   
   private OrderStatusEnum(int orderStatus, String orderStatusLabel,
		   String docOrderStatus,String patientOrderStatus) {
		this.orderStatus = orderStatus;
		this.orderStatusLabel = orderStatusLabel;
	}

	public int getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public String getOrderStatusLabel() {
		return orderStatusLabel;
	}
	
	public void setOrderStatusLabel(String orderStatusLabel) {
		this.orderStatusLabel = orderStatusLabel;
	}
	
	public  OrderStatusEnum getStatusEnum(Integer status) {
		if(OrderStatusEnum.UNRECEIVE.getOrderStatus() == status) {
			return OrderStatusEnum.UNRECEIVE;
		}
		if(OrderStatusEnum.UNSERVICE.getOrderStatus() == status) {
			return OrderStatusEnum.UNSERVICE;
		}
		if(OrderStatusEnum.SERVICING.getOrderStatus() == status) {
			return OrderStatusEnum.SERVICING;
		}
		if(OrderStatusEnum.FINISHED.getOrderStatus() == status) {
			return OrderStatusEnum.FINISHED;
		}
		if(OrderStatusEnum.SERVIC_EXCEPTION.getOrderStatus() == status) {
			return OrderStatusEnum.SERVIC_EXCEPTION;
		}
		if(OrderStatusEnum.REJECTED.getOrderStatus() == status) {
			return OrderStatusEnum.REJECTED;
		}
		if(OrderStatusEnum.NOTRANS.getOrderStatus() == status) {
			return OrderStatusEnum.NOTRANS;
		}
		if(OrderStatusEnum.NOTRANS.getOrderStatus() == status) {
			return OrderStatusEnum.NOTRANS;
		}
		if(OrderStatusEnum.CANCELED.getOrderStatus() == status) {
			return OrderStatusEnum.CANCELED;
		}
		if(OrderStatusEnum.TIMEOUT.getOrderStatus() == status) {
			return OrderStatusEnum.TIMEOUT;
		}
		return null;
	}
	   
   
}
