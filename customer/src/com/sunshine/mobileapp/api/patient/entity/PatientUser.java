/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年9月13日</p>
 *  <p> Created by 申姜</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.patient.entity;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.common.patient.entity
 * @ClassName: PatientUser
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 申姜
 * @Create Date: 2016年9月13日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class PatientUser extends BaseSQLEntity {

	private static final long serialVersionUID = -2914116734582875540L;
	
	private String account;
	
	private String userName;
	
	/**
	 * 记录曾用手机号
	 */
	private String oldAccount;
	
	private String openId;
	
	private int sex;
	
	private Integer age;
	
	private float height;
	
	private float weight;
	

	
	private String provinceCode;
	private String provinceName;
	private String cityCode;
	private String cityName;
	private String areaCode;
	private String areaName;
	private String channel;
	private Integer status;
	private String birthday;
	private String idCard;
	
	private String userImg;
	
	@JsonIgnore
	private String password;
	
	/**
	 * 咨询次数
	 */
	private String inquiryTimes;
	
	/**
	 * 咨询费用
	 */
	private String inquiryFees;
	


	private String lastLoginTime;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getOldAccount() {
		return oldAccount;
	}
	public void setOldAccount(String oldAccount) {
		this.oldAccount = oldAccount;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		if(age == null || age == 0 ){
			if (birthday != null && !birthday.equals("")){
				int currentYear = Calendar.getInstance().get(Calendar.YEAR);
				int birthYear = Integer.parseInt(birthday.substring(0, 4));
				age = currentYear - birthYear;
			}else {
				age = 0;
			}
		}
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getInquiryTimes() {
		return inquiryTimes;
	}
	public void setInquiryTimes(String inquiryTimes) {
		this.inquiryTimes = inquiryTimes;
	}
	public String getInquiryFees() {
		return inquiryFees;
	}
	public void setInquiryFees(String inquiryFees) {
		this.inquiryFees = inquiryFees;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
