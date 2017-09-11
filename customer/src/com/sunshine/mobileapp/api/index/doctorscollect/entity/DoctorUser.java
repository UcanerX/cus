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
package com.sunshine.mobileapp.api.index.doctorscollect.entity;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.sunshine.common.DoctorConstant;
import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

/**
* @Package：com.sunshine.mobileapp.api.index.doctorscollect.entity   
* @ClassName：DoctorUser   
* @Description：   <p> TODO</p>
* @Author： - DaoDou 
* @CreatTime：2017年9月6日 上午11:58:47   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
public class DoctorUser extends BaseSQLEntity  {

	private static final long serialVersionUID = 1176629029485257339L;

	/**
	 * 账号
	 */
	private String account;
	
	private String oldAccount;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 用户密码
	 */
	private String password;
	
   /**
    * 身份证号码
    */
	private String idCard;

	/**
	 * 邀请码
	 */
	private String inviteCode;

	/**
	 * 注册日期
	 */
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private Date ct;
	
	/**
	 * 性别：1-男 2-女
	 */
	private Integer sex;

	/**
	 * 用户头像
	 */
	private String userImg;

	/**
	 * 医师执业证书正面
	 */
	private String certifiedFrontImg;

	/**
	 * 医师执业证书反面
	 */
	private String certifiedBackImg;

	/**
	 * 医师手持工牌照
	 */
	private String licenseImg;

	/**
	 * 医院Id
	 */
	private String hospitalId;

	/**
	 * 医院名称
	 */
	private String hospitalName;
	/**
	 * 分院Id
	 */
	private String branchId;
	/**
	 * 分院名称
	 */
	private String branchName;

	/**
	 * 一级科室Id
	 */
	private String deptLevelOneId;

	/**
	 * 一级科室名称
	 */
	private String deptLevelOne;

	/**
	 * 二级科室Id
	 */
	private String deptLevelTwoId;

	/**
	 * 二级科室名称
	 */
	private String deptLevelTwo;

	/**
	 * 职称Id
	 */
	private String titleId;

	/**
	 * 职称
	 */
	private String title;

	/**
	 * 职位
	 */
	private String position;

	/**
	 * 服务设置 0-关闭 1-开通
	 */
	private Integer serviceSetting;

	/**
	 * 擅长
	 */
	private String specialty;

	/**
	 * 执业经历
	 */
	private String workExperience;
	
	/**
	 * 医学背景 履历
	 */
	private String medicalResume;
	/**
	 * 二维码
	 */
	private String qrCode;

	/**
	 * 医院的简介 不入库
	 */
	private String hospitalIntroduced;
	
	//当前用户的启停用状态  0 未知  1启用 2停用
	private int userStatus;

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * 用于显示页面最后一级的科室名称 不入库
	 */
	private String deptName;

	public String getDeptName() {
		if (StringUtils.isBlank(deptName)) {
			if (StringUtils.isNotBlank(this.deptLevelTwo)) {
				deptName = this.deptLevelTwo;
			} else {
				deptName = this.deptLevelOne;
			}
		}
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getCt() {
		return ct;
	}

	public void setCt(Date ct) {
		this.ct = ct;
	}

	
	public String getMedicalResume() {
		return medicalResume;
	}

	public void setMedicalResume(String medicalResume) {
		this.medicalResume = medicalResume == null ? null : medicalResume.trim();
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param code
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param code
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param code
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * @return the inviteCode
	 */
	public String getInviteCode() {
		return inviteCode;
	}

	/**
	 * @param inviteCode the inviteCode to set
	 */
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @param code
	 *            the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * @return the userImg
	 */
	public String getUserImg() {
		return userImg;
	}

	/**
	 * @param code
	 *            the userImg to set
	 */
	public void setUserImg(String userImg) {
		this.userImg = userImg == null ? null : userImg.trim();
	}

	/**
	 * @return the certifiedFrontImg
	 */
	public String getCertifiedFrontImg() {
		return certifiedFrontImg;
	}

	/**
	 * @param code
	 *            the certifiedFrontImg to set
	 */
	public void setCertifiedFrontImg(String certifiedFrontImg) {
		this.certifiedFrontImg = certifiedFrontImg == null ? null : certifiedFrontImg.trim();
	}

	/**
	 * @return the certifiedBackImg
	 */
	public String getCertifiedBackImg() {
		return certifiedBackImg;
	}

	/**
	 * @param code
	 *            the certifiedBackImg to set
	 */
	public void setCertifiedBackImg(String certifiedBackImg) {
		this.certifiedBackImg = certifiedBackImg == null ? null : certifiedBackImg.trim();
	}

	/**
	 * @return the licenseImg
	 */
	public String getLicenseImg() {
		return licenseImg;
	}

	/**
	 * @param code
	 *            the licenseImg to set
	 */
	public void setLicenseImg(String licenseImg) {
		this.licenseImg = licenseImg == null ? null : licenseImg.trim();
	}

	/**
	 * @return the hospitalId
	 */
	public String getHospitalId() {
		return hospitalId;
	}

	/**
	 * @param code
	 *            the hospitalId to set
	 */
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId == null ? null : hospitalId.trim();
	}

	/**
	 * @return the hospitalName
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * @param code
	 *            the hospitalName to set
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName == null ? null : hospitalName.trim();
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the deptLevelOneId
	 */
	public String getDeptLevelOneId() {
		return deptLevelOneId;
	}

	/**
	 * @param code
	 *            the deptLevelOneId to set
	 */
	public void setDeptLevelOneId(String deptLevelOneId) {
		this.deptLevelOneId = deptLevelOneId == null ? null : deptLevelOneId.trim();
	}

	/**
	 * @return the deptLevelOne
	 */
	public String getDeptLevelOne() {
		return deptLevelOne;
	}

	/**
	 * @param code
	 *            the deptLevelOne to set
	 */
	public void setDeptLevelOne(String deptLevelOne) {
		this.deptLevelOne = deptLevelOne == null ? null : deptLevelOne.trim();
	}

	/**
	 * @return the deptLevelTwoId
	 */
	public String getDeptLevelTwoId() {
		return deptLevelTwoId;
	}

	/**
	 * @param code
	 *            the deptLevelTwoId to set
	 */
	public void setDeptLevelTwoId(String deptLevelTwoId) {
		this.deptLevelTwoId = deptLevelTwoId == null ? null : deptLevelTwoId.trim();
	}

	/**
	 * @return the deptLevelTwo
	 */
	public String getDeptLevelTwo() {
		return deptLevelTwo;
	}

	/**
	 * @param code
	 *            the deptLevelTwo to set
	 */
	public void setDeptLevelTwo(String deptLevelTwo) {
		this.deptLevelTwo = deptLevelTwo == null ? null : deptLevelTwo.trim();
	}

	/**
	 * @return the titleId
	 */
	public String getTitleId() {
		return titleId;
	}

	/**
	 * @param code
	 *            the titleId to set
	 */
	public void setTitleId(String titleId) {
		this.titleId = titleId == null ? null : titleId.trim();
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param code
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param code
	 *            the position to set
	 */
	public void setPosition(String position) {
		this.position = position == null ? null : position.trim();
	}

	/**
	 * @return the serviceSetting
	 */
	public Integer getServiceSetting() {
		return serviceSetting;
	}

	/**
	 * @param code
	 *            the serviceSetting to set
	 */
	public void setServiceSetting(Integer serviceSetting) {
		this.serviceSetting = serviceSetting;
	}

	/**
	 * @return the specialty
	 */
	public String getSpecialty() {
		return specialty;
	}

	/**
	 * @param code
	 *            the specialty to set
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty == null ? null : specialty.trim();
	}

	/**
	 * @return the workExperience
	 */
	public String getWorkExperience() {
		return workExperience;
	}

	/**
	 * @param code
	 *            the workExperience to set
	 */
	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience == null ? null : workExperience.trim();
	}

	/**
	 * @return the qrCode
	 */
	public String getQrCode() {
		return qrCode;
	}

	/**
	 * @param code
	 *            the qrCode to set
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode == null ? null : qrCode.trim();
	}

	public String getHospitalIntroduced() {
		return hospitalIntroduced;
	}

	public void setHospitalIntroduced(String hospitalIntroduced) {
		this.hospitalIntroduced = hospitalIntroduced;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DoctorUser [account=" + account + ", userName=" + userName + ", password=" + password + ", sex=" + sex + ", title=" + title
				+ ", position=" + position + ", specialty=" + specialty + ", workExperience=" + workExperience + ", qrCode=" + qrCode + ", ct=" + ct
				+ "]";
	}

	public DoctorUserApply convertApply() {
		DoctorUserApply entity = new DoctorUserApply();
		entity.setUserId(this.getId());
		entity.setUserName(this.getUserName());
		entity.setSex(this.getSex());
		entity.setUserImg(this.getUserImg());
		entity.setHospitalId(this.getHospitalId());
		entity.setHospitalName(this.getHospitalName());
		entity.setBranchId(this.getBranchId());
		entity.setBranchName(this.getBranchName());
		entity.setDeptLevelOne(this.getDeptLevelOne());
		entity.setDeptLevelOneId(this.getDeptLevelOneId());
		entity.setDeptLevelTwo(this.getDeptLevelTwo());
		entity.setDeptLevelTwoId(this.getDeptLevelTwoId());
		entity.setTitle(this.getTitle());
		entity.setTitleId(this.getTitleId());
		if (StringUtils.isNotBlank(this.getUserImg())) {
			entity.setStatus(DoctorConstant.APPLY_STATUS_AUDITED);
		} else {
			entity.setStatus(DoctorConstant.APPLY_STATUS_NOT_APPLY);
		}
		return entity;
	}

	public String getOldAccount() {
		return oldAccount;
	}

	public void setOldAccount(String oldAccount) {
		this.oldAccount = oldAccount;
	}
}
