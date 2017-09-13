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

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sunshine.common.vo.DictonaryBean;
import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;
import com.sunshine.framework.utils.DateUtils;
import com.sunshine.mobileapp.api.order.vo.OrderStatusEnum;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.ordermanager.entity
 * @ClassName: Order
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年6月6日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class Order extends BaseSQLEntity{
	
	private static final long serialVersionUID = 8045756937165691250L;

	private String orderNo;
	/**
	 * 服务类型
	 */
	private String serviceId;

	/**
	 * 服务名称
	 */
	private String serviceName;
	/**
	 * 服务消息对应的code
	 * 图文消息： TEST_DHWZ
	 * 电话咨询： TEST_DHWZ
	 */
	private String serviceMsgCode;
	private String doctorId;
	private String doctorName;
	private String doctorImg;

	private String provinceCode;
	private String provinceName;
	private String cityCode;
	private String cityName;
	private String areaCode;
	private String areaName;

	/**
	 * 转诊订单
	 */
	private String parentOrder;
	private String userId;
	private String userName;
	private String userImg;
	private int orderType;
	private Date appointmentTime;
				   
	private String appointmentTimeStr; 
	private Integer buyTime;

	private Date startTime;
	private Date endTime;
	private Date actualStartTime;
	private Date actualEndTime;
	private Double  price;
	private Double  orderFee;
	private String orderDesc;
	private String imageUrls;
	private String doctorPhone;
	private String userPhone;
	private Date expireDate;

	/**
	 * 平台编码
	 */
	private String platformCode;

	/**
	 * 订单状态
	 */
	private Integer orderStatus;
	
	/**
	 * 订单预约日期时间戳
	 */
	private long appointmentTimestamp;
	/**
	 * 订单结束时间戳
	 */
	private long endTimestamp;
	private String orderStatusLabel;
	private String platformCodeLabel;
	private String appointmentDateLabel;
	/**
	 * 医生端和患者端对于相同的订单状态可能显示的中文名称不同
	 */
	private String doctorOrderStatusLabel;
	private String patientOrderStatusLabel;
	/**
	 * 是否已可服务
	 */
	private boolean servable = false;

	private String inquireId;

	private String inquireName;
	private Integer inqueireAge;
	private Integer inquireSex;
	

	private String tradeMode;
	private String tradeOrderNo;
	private String refundOrderNo;
	private Date payTime;
	private Date refundTime;
	private Integer payStatus;

	public String getInquireName() {
		return inquireName;
	}
	public void setInquireName(String inquireName) {
		this.inquireName = inquireName;
	}
	public Integer getInqueireAge() {
		return inqueireAge;
	}
	public void setInqueireAge(Integer inqueireAge) {
		this.inqueireAge = inqueireAge;
	}
	public Integer getInquireSex() {
		return inquireSex;
	}
	public void setInquireSex(Integer inquireSex) {
		this.inquireSex = inquireSex;
	}
	
	public String getInquireId() {
		return inquireId;
	}
	public void setInquireId(String inquireId) {
		this.inquireId = inquireId;
	}
	public String getTradeMode() {
		return tradeMode;
	}
	public void setTradeMode(String tradeMode) {
		this.tradeMode = tradeMode;
	}
	public String getTradeOrderNo() {
		return tradeOrderNo;
	}
	public void setTradeOrderNo(String tradeOrderNo) {
		this.tradeOrderNo = tradeOrderNo;
	}
	public String getRefundOrderNo() {
		return refundOrderNo;
	}
	public void setRefundOrderNo(String refundOrderNo) {
		this.refundOrderNo = refundOrderNo;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
		if (appointmentTime != null) {
			this.appointmentDateLabel = DateUtils.formatDate(appointmentTime);
			appointmentTimestamp = appointmentTime.getTime();
		}
	}

	public String getAppointmentDateLabel() {
		return appointmentDateLabel;
	}
	public void setAppointmentDateLabel(String appointmentDateLabel) {
		this.appointmentDateLabel = appointmentDateLabel;
	}
	public Integer getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(Integer buyTime) {
		this.buyTime = buyTime;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getOrderFee() {
		return orderFee;
	}
	public void setOrderFee(Double orderFee) {
		this.orderFee = orderFee;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	public String getDoctorPhone() {
		return doctorPhone;
	}

	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		/*	this.platformCodeLabel=FieldFormatterManager.getInstance().getDicFormatter()
				.format(this, "platformCode",null);*/
		this.platformCodeLabel = DictonaryBean.getPlatformCodeDic().get(platformCode);
		this.platformCode = platformCode;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatusLabel=DictonaryBean.getOrderStatusDic().get(orderStatus);
		this.orderStatus = orderStatus;
		if (OrderStatusEnum.UNRECEIVE.getOrderStatus() == orderStatus) {
			this.doctorOrderStatusLabel = "待接诊";
		} else if (OrderStatusEnum.UNSERVICE.getOrderStatus() == orderStatus) {
			this.doctorOrderStatusLabel = "待通话";
		} else if (OrderStatusEnum.SERVICING.getOrderStatus() == orderStatus) {
			this.doctorOrderStatusLabel = "服务中";
		} else if (OrderStatusEnum.REJECTED.getOrderStatus() == orderStatus) {
			this.doctorOrderStatusLabel = "已结束";
		} else if (OrderStatusEnum.CANCELED.getOrderStatus() == orderStatus) {
			this.doctorOrderStatusLabel = "已取消";
		} else if (OrderStatusEnum.TIMEOUT.getOrderStatus() == orderStatus) {
			this.doctorOrderStatusLabel = "已过期";
		} else if (OrderStatusEnum.TRANSED.getOrderStatus() == orderStatus) {
			this.doctorOrderStatusLabel = "已完成";
		} else if (OrderStatusEnum.NOTRANS.getOrderStatus() == orderStatus) {
			this.doctorOrderStatusLabel = "已完成";
		} else if (OrderStatusEnum.FINISHED.getOrderStatus() == orderStatus) {
			this.doctorOrderStatusLabel = "交易结束";
		}
	}
	
	public boolean isTerminated() {
		boolean ret = false;
		if (OrderStatusEnum.REJECTED.getOrderStatus() == orderStatus 
				|| OrderStatusEnum.CANCELED.getOrderStatus() == orderStatus 
				|| OrderStatusEnum.TIMEOUT.getOrderStatus() == orderStatus 
				|| OrderStatusEnum.TRANSED.getOrderStatus() == orderStatus || OrderStatusEnum.NOTRANS.getOrderStatus() == orderStatus 
				|| OrderStatusEnum.FINISHED.getOrderStatus() == orderStatus) {
			ret = true;
		}
		return ret;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getParentOrder() {
		return parentOrder;
	}

	public void setParentOrder(String parentOrder) {
		this.parentOrder = parentOrder;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getPlatformCodeLabel() {
		return platformCodeLabel;
	}

	public void setPlatformCodeLabel(String platformCodeLabel) {
		this.platformCodeLabel = platformCodeLabel;
	}

	public String getOrderStatusLabel() {
		return orderStatusLabel;
	}

	public void setOrderStatusLabel(String orderStatusLabel) {
		this.orderStatusLabel = orderStatusLabel;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAppointmentTimeStr() {
		return appointmentTimeStr;
	}

	public void setAppointmentTimeStr(String appointmentTimeStr) {
		this.appointmentTimeStr = appointmentTimeStr;
	}

	public boolean getServable() {
		return servable;
	}

	public void setServable(boolean servable) {
		this.servable = servable;
	}

	public String getDoctorOrderStatusLabel() {
		return doctorOrderStatusLabel;
	}

	public void setDoctorOrderStatusLabel(String doctorOrderStatusLabel) {
		this.doctorOrderStatusLabel = doctorOrderStatusLabel;
	}

	public String getPatientOrderStatusLabel() {
		return patientOrderStatusLabel;
	}

	public void setPatientOrderStatusLabel(String patientOrderStatusLabel) {
		this.patientOrderStatusLabel = patientOrderStatusLabel;
	}

	public Date getActualStartTime() {
		return actualStartTime;
	}

	public void setActualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}

	public Date getActualEndTime() {
		return actualEndTime;
	}

	public void setActualEndTime(Date actualEndTime) {
		this.actualEndTime = actualEndTime;
		if (actualEndTime != null) {
			endTimestamp = actualEndTime.getTime();
		}
	}

	public String getServiceMsgCode() {
		return serviceMsgCode;
	}

	public void setServiceMsgCode(String serviceMsgCode) {
		this.serviceMsgCode = serviceMsgCode;
	}

	public long getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(long endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	/**
	 * 获得预约时间
	 * @return
	 */
	public String getAppointmentDateTime() {
		try {
			if (appointmentTime != null && startTime != null) {
				SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat timeFormate = new SimpleDateFormat("HH:mm");
				return dateFormate.format(appointmentTime) + " " + timeFormate.format(startTime);
			}
		} catch (Exception e) {
		}
		return "";
	}

	public long getAppointmentTimestamp() {
		return appointmentTimestamp;
	}

	public void setAppointmentTimestamp(long appointmentTimestamp) {
		this.appointmentTimestamp = appointmentTimestamp;
	}
	
	public String getDoctorImg() {
		return doctorImg;
	}
	public void setDoctorImg(String doctorImg) {
		this.doctorImg = doctorImg;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
}
