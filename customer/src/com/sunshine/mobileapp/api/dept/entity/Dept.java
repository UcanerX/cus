/**
* <html>
*  <body>
*   <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
*   <p> All rights reserved.</p>
*   <p> Created on 2016年10月4日</p>
*   <p> Created by 姓名</p>
*  </body>
* </html>
*/
package com.sunshine.mobileapp.api.dept.entity;

import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

/**
 * 
 * @Project: easy_health 
 * @Package: com.sunshine.platform.dept.entity
 * @ClassName: Dept
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 申姜
 * @Create Date: 2016年9月18日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class Dept extends BaseSQLEntity{

	private static final long serialVersionUID = -1623598685753067309L;

	/**
	 * 医院ID
	 */
	private String hospitalId;

	/**
	 * 医院编码
	 */
	private String hospitalCode;

	/**
	 * 医院名称
	 */
	private String hospitalName;

	/**
	 * 医院类型编码
	 */
	private String hospitalTypeCode;

	/**
	 * 医院类型名称
	 */
	private String hospitalTypeName;

	/**
	 * 医院级别编码
	 */
	private String hospitalLevelCode;

	/**
	 * 医院级别名称
	 */
	private String hospitalLevelName;

	/**
	 * 医院所在省份编码
	 */
	private String hospitalProvinceCode;

	/**
	 * 医院所在省份名称
	 */
	private String hospitalProvinceName;

	/**
	 * 医院所在市编码
	 */
	private String hospitalCityCode;

	/**
	 * 医院所在市名称
	 */
	private String hospitalCityName;

	/**
	 * 医院所在区编码
	 */
	private String hospitalAreaCode;

	/**
	 * 医院所在区名称
	 */
	private String hospitalAreaName;

	/**
	 * 父科室ID
	 */
	private String parentId;

	/**
	 * 父科室代码
	 */
	private String parentCode;

	/**
	 * 父科室名称
	 */
	private String parentName;

	/**
	 * 科室分类代码
	 */
	private String classCode;

	/**
	 * 科室分类名称
	 */
	private String className;

	/**
	 * 科室代码
	 */
	private String code;

	/**
	 * 科室名称
	 */
	private String name;

	/**
	 * 1:一级科室,2:二级科室
	 */
	private Integer level;

	/**
	 * 科室电话
	 */
	private String telephone;

	/**
	 * 科室介绍
	 */
	private String introduction;

	/**
	 * 科室位置
	 */
	private String location;

	/**
	 * 1:启用.2:停用
	 */
	private Integer status;

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
	* @return the hospitalCode
	*/
	public String getHospitalCode() {
		return hospitalCode;
	}

	/**
	* @param code
	*            the hospitalCode to set
	*/
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode == null ? null : hospitalCode.trim();
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

	/**
	* @return the hospitalTypeCode
	*/
	public String getHospitalTypeCode() {
		return hospitalTypeCode;
	}

	/**
	* @param code
	*            the hospitalTypeCode to set
	*/
	public void setHospitalTypeCode(String hospitalTypeCode) {
		this.hospitalTypeCode = hospitalTypeCode == null ? null : hospitalTypeCode.trim();
	}

	/**
	* @return the hospitalTypeName
	*/
	public String getHospitalTypeName() {
		return hospitalTypeName;
	}

	/**
	* @param code
	*            the hospitalTypeName to set
	*/
	public void setHospitalTypeName(String hospitalTypeName) {
		this.hospitalTypeName = hospitalTypeName == null ? null : hospitalTypeName.trim();
	}

	/**
	* @return the hospitalLevelCode
	*/
	public String getHospitalLevelCode() {
		return hospitalLevelCode;
	}

	/**
	* @param code
	*            the hospitalLevelCode to set
	*/
	public void setHospitalLevelCode(String hospitalLevelCode) {
		this.hospitalLevelCode = hospitalLevelCode == null ? null : hospitalLevelCode.trim();
	}

	/**
	* @return the hospitalLevelName
	*/
	public String getHospitalLevelName() {
		return hospitalLevelName;
	}

	/**
	* @param code
	*            the hospitalLevelName to set
	*/
	public void setHospitalLevelName(String hospitalLevelName) {
		this.hospitalLevelName = hospitalLevelName == null ? null : hospitalLevelName.trim();
	}

	/**
	* @return the hospitalProvinceCode
	*/
	public String getHospitalProvinceCode() {
		return hospitalProvinceCode;
	}

	/**
	* @param code
	*            the hospitalProvinceCode to set
	*/
	public void setHospitalProvinceCode(String hospitalProvinceCode) {
		this.hospitalProvinceCode = hospitalProvinceCode == null ? null : hospitalProvinceCode.trim();
	}

	/**
	* @return the hospitalProvinceName
	*/
	public String getHospitalProvinceName() {
		return hospitalProvinceName;
	}

	/**
	* @param code
	*            the hospitalProvinceName to set
	*/
	public void setHospitalProvinceName(String hospitalProvinceName) {
		this.hospitalProvinceName = hospitalProvinceName == null ? null : hospitalProvinceName.trim();
	}

	/**
	* @return the hospitalCityCode
	*/
	public String getHospitalCityCode() {
		return hospitalCityCode;
	}

	/**
	* @param code
	*            the hospitalCityCode to set
	*/
	public void setHospitalCityCode(String hospitalCityCode) {
		this.hospitalCityCode = hospitalCityCode == null ? null : hospitalCityCode.trim();
	}

	/**
	* @return the hospitalCityName
	*/
	public String getHospitalCityName() {
		return hospitalCityName;
	}

	/**
	* @param code
	*            the hospitalCityName to set
	*/
	public void setHospitalCityName(String hospitalCityName) {
		this.hospitalCityName = hospitalCityName == null ? null : hospitalCityName.trim();
	}

	/**
	* @return the hospitalAreaCode
	*/
	public String getHospitalAreaCode() {
		return hospitalAreaCode;
	}

	/**
	* @param code
	*            the hospitalAreaCode to set
	*/
	public void setHospitalAreaCode(String hospitalAreaCode) {
		this.hospitalAreaCode = hospitalAreaCode == null ? null : hospitalAreaCode.trim();
	}

	/**
	* @return the hospitalAreaName
	*/
	public String getHospitalAreaName() {
		return hospitalAreaName;
	}

	/**
	* @param code
	*            the hospitalAreaName to set
	*/
	public void setHospitalAreaName(String hospitalAreaName) {
		this.hospitalAreaName = hospitalAreaName == null ? null : hospitalAreaName.trim();
	}

	/**
	* @return the parentId
	*/
	public String getParentId() {
		return parentId;
	}

	/**
	* @param code
	*            the parentId to set
	*/
	public void setParentId(String parentId) {
		this.parentId = parentId == null ? null : parentId.trim();
	}

	/**
	* @return the parentCode
	*/
	public String getParentCode() {
		return parentCode;
	}

	/**
	* @param code
	*            the parentCode to set
	*/
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode == null ? null : parentCode.trim();
	}

	/**
	* @return the parentName
	*/
	public String getParentName() {
		return parentName;
	}

	/**
	* @param code
	*            the parentName to set
	*/
	public void setParentName(String parentName) {
		this.parentName = parentName == null ? null : parentName.trim();
	}

	/**
	* @return the classCode
	*/
	public String getClassCode() {
		return classCode;
	}

	/**
	* @param code
	*            the classCode to set
	*/
	public void setClassCode(String classCode) {
		this.classCode = classCode == null ? null : classCode.trim();
	}

	/**
	* @return the className
	*/
	public String getClassName() {
		return className;
	}

	/**
	* @param code
	*            the className to set
	*/
	public void setClassName(String className) {
		this.className = className == null ? null : className.trim();
	}

	/**
	* @return the code
	*/
	public String getCode() {
		return code;
	}

	/**
	* @param code
	*            the code to set
	*/
	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	/**
	* @return the name
	*/
	public String getName() {
		return name;
	}

	/**
	* @param code
	*            the name to set
	*/
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	* @return the level
	*/
	public Integer getLevel() {
		return level;
	}

	/**
	* @param code
	*            the level to set
	*/
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	* @return the telephone
	*/
	public String getTelephone() {
		return telephone;
	}

	/**
	* @param code
	*            the telephone to set
	*/
	public void setTelephone(String telephone) {
		this.telephone = telephone == null ? null : telephone.trim();
	}

	/**
	* @return the introduction
	*/
	public String getIntroduction() {
		return introduction;
	}

	/**
	* @param code
	*            the introduction to set
	*/
	public void setIntroduction(String introduction) {
		this.introduction = introduction == null ? null : introduction.trim();
	}

	/**
	* @return the location
	*/
	public String getLocation() {
		return location;
	}

	/**
	* @param code
	*            the location to set
	*/
	public void setLocation(String location) {
		this.location = location == null ? null : location.trim();
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
}