/**
* <html>
*  <body>
*   <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
*   <p> All rights reserved.</p>
*   <p> Created on 2016年10月19日</p>
*   <p> Created by 姓名</p>
*  </body>
* </html>
*/
package com.sunshine.mobileapp.api.index.doctorscollect.entity;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

public class DoctorUserApply extends BaseSQLEntity {
	
	private static final long serialVersionUID = 5173070045199328297L;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String userName;
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
	 * 审批状态(0暂时保存，1审核中，2审批通过，3审批不通过)
	 */
	private Integer status;
	/**
	 * 审批人
	 */
	private String approver;
	/**
	 * 审批时间
	 */
	private Date approvalTime;
	/**
	 * 日志
	 */
	private String log;

	/**
	 * 用于显示页面最后一级的科室名称 不入库
	 */
	private String deptName;
	/**
	 * 擅长 不入库
	 */
	private String specialty;

	/**
	 * 执业经历 不入库
	 */
	private String workExperience;
	
	/**
	 * 医学背景 履历  不入库
	 */
	private String medicalResume;
	/**
	 * 账号
	 */
	private String account;

	/**
	 * 是否最后审批通过(0否,1是)
	 */
	private int isLast;
	/**
	 * 进入认证申请的入口类型 不入库
	 */
	private int applyEntranceType;
	
	/**
	 * 进入公共页面地址（用来记录返回页面） 不入库
	 */
	protected String headUrl;
	
	//当前用户的启停用状态  0 未知  1启用 2停用
    private int userStatus;
    
    
    private String availableService;
    
    private String fullAddress;
	
	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public String getMedicalResume() {
		return medicalResume;
	}

	public void setMedicalResume(String medicalResume) {
		this.medicalResume = medicalResume;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public int getIsLast() {
		return isLast;
	}

	public void setIsLast(int isLast) {
		this.isLast = isLast;
	}

	public int getApplyEntranceType() {
		return applyEntranceType;
	}

	public void setApplyEntranceType(int applyEntranceType) {
		this.applyEntranceType = applyEntranceType;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param code
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
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
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param code
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the approver
	 */
	public String getApprover() {
		return approver;
	}

	/**
	 * @param code
	 *            the approver to set
	 */
	public void setApprover(String approver) {
		this.approver = approver == null ? null : approver.trim();
	}

	/**
	 * @return the approvalTime
	 */
	public Date getApprovalTime() {
		return approvalTime;
	}

	/**
	 * @param code
	 *            the approvalTime to set
	 */
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	/**
	 * @return the log
	 */
	public String getLog() {
		return log;
	}

	/**
	 * @param code
	 *            the log to set
	 */
	public void setLog(String log) {
		this.log = log == null ? null : log.trim();
	}

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

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAvailableService() {
		return availableService;
	}

	public void setAvailableService(String availableService) {
		this.availableService = availableService;
	}

	
	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public DoctorUser convertToUser() {
		DoctorUser entity = new DoctorUser();
		entity.setId(this.getUserId());
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
		//背景 经历  医学背景
		entity.setSpecialty(this.getSpecialty());
		entity.setWorkExperience(this.getWorkExperience());
		entity.setMedicalResume(this.getMedicalResume());
		return entity;
	}

}