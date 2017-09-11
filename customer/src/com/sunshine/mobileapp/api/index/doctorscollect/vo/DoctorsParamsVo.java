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
package com.sunshine.mobileapp.api.index.doctorscollect.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

/**
* @Package：com.sunshine.mobileapp.api.login.dto   
* @ClassName：LoginParamsVo   
* @Description：   <p> 名医汇 医生列表 传输vo</p>
* @Author： - DaoDou 
* @CreatTime：2017年9月6日 上午9:28:37   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 * @param <T>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DoctorsParamsVo<T> extends BaseSQLEntity implements Serializable {
	
	private static final long serialVersionUID = 6742541571584470842L;
	
	private Object obj;
	
	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	/**
	 * pageList数据
	 */
	private List<T> pageList;
	
	/**
	 * 总记录数
	 */
	private Long totalRows;

	/*医生头像、医生姓名、医生科室、医生等级、医生所在执业医院、医生开通的咨询标签、擅长、价格；*/
	
	/**
	 * 用户ID
	 */
	private String id;
	
	/**
	 * 医生头像
	 */
	private String userImg;
	
	/**
	 * 医生姓名
	 */
	private String userName;

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
	 * 价格  最低
	 */
	private String price;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}

	public Long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
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

	public String getDeptLevelOneId() {
		return deptLevelOneId;
	}

	public void setDeptLevelOneId(String deptLevelOneId) {
		this.deptLevelOneId = deptLevelOneId;
	}

	public String getDeptLevelOne() {
		return deptLevelOne;
	}

	public void setDeptLevelOne(String deptLevelOne) {
		this.deptLevelOne = deptLevelOne;
	}

	public String getDeptLevelTwoId() {
		return deptLevelTwoId;
	}

	public void setDeptLevelTwoId(String deptLevelTwoId) {
		this.deptLevelTwoId = deptLevelTwoId;
	}

	public String getDeptLevelTwo() {
		return deptLevelTwo;
	}

	public void setDeptLevelTwo(String deptLevelTwo) {
		this.deptLevelTwo = deptLevelTwo;
	}

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public String getMedicalResume() {
		return medicalResume;
	}

	public void setMedicalResume(String medicalResume) {
		this.medicalResume = medicalResume;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public DoctorsParamsVo() {
		super();
	}
	
}
