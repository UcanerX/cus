package com.sunshine.mobileapp.api.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Project YiChenTong_Server
 * @Package com.sunshine.mobileapp.api.dto
 * @ClassName RegParamsVo.java
 * @Description 挂号参数请求对象
 * @JDK version used 1.8
 * @Author 于策/yu.ce@foxmail.com
 * @Create Date 2017年8月31日
 * @modify By
 * @modify Date
 * @Why&What is modify
 * @Version 1.0
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RegParamsVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1164872315132577613L;

	private String appId;

	private String appCode;

	private String cityCode;

	private String account;

	private String userId;

	public RegParamsVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegParamsVo(String appId, String appCode, String cityCode, String account, String userId) {
		super();
		this.appId = appId;
		this.appCode = appCode;
		this.cityCode = cityCode;
		this.account = account;
		this.userId = userId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
