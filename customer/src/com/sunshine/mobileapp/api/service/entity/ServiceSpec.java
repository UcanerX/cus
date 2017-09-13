package com.sunshine.mobileapp.api.service.entity;

import com.sunshine.common.formatter.support.FieldFormatterManager;
import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.platform.service.entity
 * @ClassName: LoginController
 * @Description: <p>服务规格</p>
 * @JDK version used: 
 * @Author: 甘松
 * @Create Date: 2017年6月12日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class ServiceSpec extends BaseSQLEntity {

	private static final long serialVersionUID = -5207332355248989813L;

	private String id;

	/**
	 * 服务ID
	 */
	private String serviceId;

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
	 * 是否已删除
	 */
	private boolean isDelete;

	/**
	 * 排序值
	 */
	private double sortNo;

	/**
	 * 关联显示字段
	 */
	private String serviceName;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
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

}
