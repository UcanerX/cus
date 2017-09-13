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
package com.sunshine.mobileapp.api.order.entity;

import java.util.Date;

import com.sunshine.common.vo.DictonaryBean;
import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.ordermanager.entity
 * @ClassName: OrderStatus
 * @Description: <p>订单状态</p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年6月8日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class OrderStatus extends BaseSQLEntity {
	private static final long serialVersionUID = 4874034686123585881L;
	private String orderId;
	private Integer status;
	private String statusLabel;
	private Date changeTime;

	private Integer doctorShow = 1;

	public OrderStatus() {
	}

	public OrderStatus(String orderId, int status, Date changeTime) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.changeTime = changeTime;
	}

	public OrderStatus(String orderId, int status, String statusLabel, Date changeTime) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.statusLabel = statusLabel;
		this.changeTime = changeTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.statusLabel = DictonaryBean.getOrderStatusDic().get(status);
		this.status = status;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

	public String getStatusLabel() {
		return statusLabel;
	}

	public void setStatusLabel(String statusLabel) {
		this.statusLabel = statusLabel;
	}

	public Integer getDoctorShow() {
		return doctorShow;
	}

	public void setDoctorShow(Integer doctorShow) {
		this.doctorShow = doctorShow;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
