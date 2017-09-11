package com.sunshine.mobileapp.api.service.entity;

import com.sunshine.framework.base.entity.BasePlatformEntity;


/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.service.entity
 * @ClassName: LoginController
 * @Description: <p>服务价格表</p>
 * @JDK version used: 
 * @Author: 胡椒
 * @Create Date: 2016年9月18日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class ServicePriceManage extends BasePlatformEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5207332355248989813L;

	/**
	 * 服务id
	 */
	private String serviceId;

	/**
	 * 服务名称
	 */
	private String serviceName;

	/**
	 * 职称
	 */
	private String doctorTitle;

	/**
	 * 服务状态
	 */
	private String serviceState;

	/**
	 * 计费类型
	 */
	private String priceType;

	/**
	 * 价格
	 */
	private String price;

	/**
	 * 时长
	 */
	private String priceMinute;

	/**
	 * 时量
	 */
	private String priceAmount;

	/**
	 * 作废时间限制
	 */
	private String limitTime;

	/**
	 * 备注
	 */
	private String remark;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDoctorTitle() {
		return doctorTitle;
	}

	public void setDoctorTitle(String doctorTitle) {
		this.doctorTitle = doctorTitle;
	}

	public String getServiceState() {
		return serviceState;
	}

	public void setServiceState(String serviceState) {
		this.serviceState = serviceState;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPriceMinute() {
		return priceMinute;
	}

	public void setPriceMinute(String priceMinute) {
		this.priceMinute = priceMinute;
	}

	public String getPriceAmount() {
		return priceAmount;
	}

	public void setPriceAmount(String priceAmount) {
		this.priceAmount = priceAmount;
	}

	public String getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(String limitTime) {
		this.limitTime = limitTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
