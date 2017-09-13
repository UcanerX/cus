/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年9月10日</p>
 *  <p> Created by 钟乳石</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.order.vo;

/**
 * @Project: easy_health_client2 
 * @Package: com.sunshine.mobileapp.api.order.vo
 * @ClassName: OrderStatusRecourdEnum
 * @Description: <p>订单记录表</p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年9月10日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public enum OrderStatusRecourdEnum {

	CREATE_TIME(0,"创建订单") , // 创建订单
	UNPAID_CANCLED(1,"取消订单") , //医生不可见
	PAY(2,"支付成功")  , // 支付时间
	UNRECEIVE(3,"待接单"), //待接诊
	CANCLED(4,"取消"), // 用户取消
	REJECT(5,"已拒绝"), //医生拒绝
	RECEIVE(6,"已接诊"),// 医生接诊
	UNSERVICE(7,"待咨询") , //待咨询
	SERVICING(8,"咨询开始"),
	SERVICED(9,"咨询结束") ,
	FINISHED(10,"交易成功"),
    RETURN_FEE(12,"已退款");
    
	
	private int status;
	private String label;
	private OrderStatusRecourdEnum(int orderStatus,String label) {
			this.status = orderStatus;
			this.label = label;
	}
	public int getStatus() {
		return status;
	}
	public String getLable(){
		return label;
	}
}
