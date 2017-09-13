package com.sunshine.mobileapp.api.service.entity;

import com.sunshine.common.formatter.support.FieldFormatterManager;
import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.service.entity
 * @ClassName: LoginController
 * @Description: <p>服务基础信息表</p>
 * @JDK version used: 
 * @Author: 胡椒
 * @Create Date: 2016年9月18日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class ServiceInfoManage extends BaseSQLEntity {

	private static final long serialVersionUID = -5207332355248989813L;

	/**
	 * 服务名称
	 */
	private String serviceName;

	/**
	 * 服务介绍logo
	 */
	private String serviceLogo;

	/**
	 * 服务排序
	 */
	private double sortNo;

	/**
	 * 服务介绍
	 */
	private String serviceIntroduce;

	/**
	 * 流程介绍
	 */
	private String serviceStepIntroduce;

	/**
	 * 服务状态
	 */
	private int serviceState;

	/**
	 * 可开通职称 以配置表中的aptTitleIds为准
	 */
	@Deprecated
	private String allowableTitiles;

	/**
	 * 是否删除
	 */
	private boolean isDelete;

	/**
	 * 备注
	 */
	private String remark;

	//新增配置表字段不入库
	/**
	 * 可开通职称ID列表,逗号分隔
	 */
	private String aptTitleIds;
	/**
	 * 可否自定义价格
	 */
	private boolean isPriceDefinable;

	private String isPriceDefinableLabel;

	/**
	 * 收费形式：1-一次性；2 - 按时长
	 */
	private int chargeForm;

	/**
	 * 可选单价列表,数值以逗号分隔
	 */
	private String priceList;

	/**
	 * 购买时长列表,数值以逗号分隔
	 */
	private String buyTimeList;

	/**
	 * 逾期日期单位, 1-年，2-月，3-周，4-日，5-时
	 */
	private int expiredUnit;

	private String expiredUnitLabel;

	/**
	 * 逾期日期计数
	 */
	private int expiredCount;

	/**
	 * 0-停用，1-启用
	 */
	private int specState;

	/**
	 * 停用/启用标签
	 */
	private String specStateLabel;

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the serviceLogo
	 */
	public String getServiceLogo() {
		return serviceLogo;
	}

	/**
	 * @param serviceLogo the serviceLogo to set
	 */
	public void setServiceLogo(String serviceLogo) {
		this.serviceLogo = serviceLogo;
	}

	/**
	 * @return the sortNo
	 */
	public double getSortNo() {
		return sortNo;
	}

	/**
	 * @param sortNo the sortNo to set
	 */
	public void setSortNo(double sortNo) {
		this.sortNo = sortNo;
	}

	/**
	 * @return the serviceIntroduce
	 */
	public String getServiceIntroduce() {
		return serviceIntroduce;
	}

	/**
	 * @param serviceIntroduce the serviceIntroduce to set
	 */
	public void setServiceIntroduce(String serviceIntroduce) {
		this.serviceIntroduce = serviceIntroduce;
	}

	/**
	 * @return the serviceStepIntroduce
	 */
	public String getServiceStepIntroduce() {
		return serviceStepIntroduce;
	}

	/**
	 * @param serviceStepIntroduce the serviceStepIntroduce to set
	 */
	public void setServiceStepIntroduce(String serviceStepIntroduce) {
		this.serviceStepIntroduce = serviceStepIntroduce;
	}

	/**
	 * @return the serviceState
	 */
	public int getServiceState() {
		return serviceState;
	}

	/**
	 * @param serviceState the serviceState to set
	 */
	public void setServiceState(int serviceState) {
		this.serviceState = serviceState;
	}

	/**
	 * @return the isDelete
	 */
	public boolean isDelete() {
		return isDelete;
	}

	/**
	 * @param isDelete the isDelete to set
	 */
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the allowableTitiles
	 */
	public String getAllowableTitiles() {
		return allowableTitiles;
	}

	/**
	 * @param allowableTitiles the allowableTitiles to set
	 */
	public void setAllowableTitiles(String allowableTitiles) {
		this.allowableTitiles = allowableTitiles;
	}

	/**
	 * @return the aptTitleIds
	 */
	public String getAptTitleIds() {
		return aptTitleIds;
	}

	/**
	 * @param aptTitleIds the aptTitleIds to set
	 */
	public void setAptTitleIds(String aptTitleIds) {
		this.aptTitleIds = aptTitleIds;
	}

	/**
	 * @return the isPriceDefinable
	 */
	public boolean isPriceDefinable() {
		return isPriceDefinable;
	}

	/**
	 * @return the isPriceDefinable
	 */
	public boolean getIsPriceDefinable() {
		return isPriceDefinable;
	}

	/**
	 * @param isPriceDefinable the isPriceDefinable to set
	 */
	public void setPriceDefinable(boolean isPriceDefinable) {
		this.isPriceDefinable = isPriceDefinable;
		isPriceDefinableLabel = isPriceDefinable ? "是" : "否";
	}

	/**
	 * @return the isPriceDefinableLabel
	 */
	public String getIsPriceDefinableLabel() {
		isPriceDefinableLabel = isPriceDefinable ? "是" : "否";
		return isPriceDefinableLabel;
	}

	/**
	 * @param isPriceDefinableLabel the isPriceDefinableLabel to set
	 */
	public void setIsPriceDefinableLabel(String isPriceDefinableLabel) {
		this.isPriceDefinableLabel = isPriceDefinableLabel;
	}

	/**
	 * @return the chargeForm
	 */
	public int getChargeForm() {
		return chargeForm;
	}

	/**
	 * @param chargeForm the chargeForm to set
	 */
	public void setChargeForm(int chargeForm) {
		this.chargeForm = chargeForm;
	}

	/**
	 * @return the priceList
	 */
	public String getPriceList() {
		return priceList;
	}

	/**
	 * @param priceList the priceList to set
	 */
	public void setPriceList(String priceList) {
		this.priceList = priceList;
	}

	/**
	 * @return the buyTimeList
	 */
	public String getBuyTimeList() {
		return buyTimeList;
	}

	/**
	 * @param buyTimeList the buyTimeList to set
	 */
	public void setBuyTimeList(String buyTimeList) {
		this.buyTimeList = buyTimeList;
	}

	/**
	 * @return the expiredUnit
	 */
	public int getExpiredUnit() {
		return expiredUnit;
	}

	/**
	 * @param expiredUnit the expiredUnit to set
	 */
	public void setExpiredUnit(int expiredUnit) {
		this.expiredUnit = expiredUnit;
		this.expiredUnitLabel = FieldFormatterManager.getInstance().getDicFormatter().format(this, "expiredUnit", expiredUnit);
	}

	/**
	 * @return the expiredCount
	 */
	public int getExpiredCount() {
		return expiredCount;
	}

	/**
	 * @return the expiredUnitLabel
	 */
	public String getExpiredUnitLabel() {
		return expiredUnitLabel;
	}

	/**
	 * @param expiredUnitLabel the expiredUnitLabel to set
	 */
	public void setExpiredUnitLabel(String expiredUnitLabel) {
		this.expiredUnitLabel = expiredUnitLabel;
	}

	/**
	 * @param expiredCount the expiredCount to set
	 */
	public void setExpiredCount(int expiredCount) {
		this.expiredCount = expiredCount;
	}

	/**
	 * @return the specState
	 */
	public int getSpecState() {
		return specState;
	}

	/**
	 * @param specState the specState to set
	 */
	public void setSpecState(int specState) {
		this.specState = specState;
		this.specStateLabel = FieldFormatterManager.getInstance().getDicFormatter().format(this, "specState", specState);
	}

	/**
	 * @return the specStateLabel
	 */
	public String getSpecStateLabel() {
		return specStateLabel;
	}

	/**
	 * @param specStateLabel the specStateLabel to set
	 */
	public void setSpecStateLabel(String specStateLabel) {
		this.specStateLabel = specStateLabel;
	}

}
