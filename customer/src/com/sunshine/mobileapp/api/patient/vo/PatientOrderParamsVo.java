/**
 * <html>
 * <body>
 *  <P> Copyright 2014-2017 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年8月31日</p>
 *  <p> Created by wumingzi/yu.ce@foxmail.com</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.patient.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

/**
* @Package：com.sunshine.mobileapp.api.patient.vo   
* @ClassName：OrderParamsVo   
* @Description：   <p>下单传输vo</p>
* @Author： - DaoDou 
* @CreatTime：2017年9月13日 下午2:53:21   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientOrderParamsVo extends BaseSQLEntity {

	private static final long serialVersionUID = 6742541571584470842L;
	
	/**
	 * 问诊人的账号 (手机号)
	 */
	private String account;
	
	/**
	 * 医生的手机号(如果没有的话会提示 没有该医生)
	 */
	private String doctor;
	
	/**
	 * 服务的id 图文咨询或者是电话咨询
	 */
	private String serviceId;
	
	/**
	 * 服务的名字
	 */
	private String serviceName;
	
	/**
	 * 预约的时间 eg:2017-09-10
	 */
	private String appointmentDate;
	
	/**
	 * 预约的时间段 eg:"10:00-11:00"
	 */
	private String appointmentTime;
	
	/**
	 * 购买的时长(电话咨询)
	 */
	private Integer buyTime;
	
	/**
	 * 问诊人姓名
	 */
	private String inquireName;
	
	/**
	 * 问诊人性别
	 */
	private Integer inquireSex;
	
	/**
	 * 问诊人年龄
	 */
	private Integer inquireAge;
	
	/**
	 * 问诊人的病历或者检查照片(非必填) 
	 */
	private String imageKeys;
	
	/**
	 * 病情的描述  (必填)
	 */
	private String orderDesc;
	
	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

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

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public Integer getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Integer buyTime) {
		this.buyTime = buyTime;
	}

	public String getImageKeys() {
		return imageKeys;
	}

	public void setImageKeys(String imageKeys) {
		this.imageKeys = imageKeys;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	
	public String getInquireName() {
		return inquireName;
	}

	public void setInquireName(String inquireName) {
		this.inquireName = inquireName;
	}

	public Integer getInquireSex() {
		return inquireSex;
	}

	public void setInquireSex(Integer inquireSex) {
		this.inquireSex = inquireSex;
	}

	public Integer getInquireAge() {
		return inquireAge;
	}

	public void setInquireAge(Integer inquireAge) {
		this.inquireAge = inquireAge;
	}

	public PatientOrderParamsVo() {
		super();
	}

}
