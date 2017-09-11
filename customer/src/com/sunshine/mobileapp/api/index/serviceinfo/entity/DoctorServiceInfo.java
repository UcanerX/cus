/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年10月17日</p>
 *  <p> Created by MuGua</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.index.serviceinfo.entity;

import java.util.Calendar;
import java.util.Date;

import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;
import com.sunshine.framework.utils.DateUtils;

public class DoctorServiceInfo extends BaseSQLEntity {

	private static final long serialVersionUID = 4614817702512707622L;

	/**
	 * 服务ID
	 */
	private String serviceId;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 服务状态 0-未开通 1- 已开通
	 */
	private Integer status;

	/**
	 * 服务价格ID
	 * @deprecated
	 */
	private String priceId;
	
	/**
	 * 服务单价 add by 甘松
	 */
	private float price;
	
	/**
	 * 服务最短购买时长  add by 甘松
	 */
	private int leastBuyTime;

	/**
	 * 周一服务时间
	 */
	private String stMon;

	/**
	 * 周二服务时间
	 */
	private String stTue;

	/**
	 * 周三服务时间
	 */
	private String stWed;

	/**
	 * 周四服务时间
	 */
	private String stThu;

	/**
	 * 周五服务时间
	 */
	private String stFri;

	/**
	 * 周六服务时间
	 */
	private String stSat;

	/**
	 * 周日服务时间
	 */
	private String stSun;

	/**
	 * 服务名称  不入库
	 */
	private String serviceName;
	/**
	 * 服务介绍   不入库
	 */
	private String serviceIntroduce;

	/**
	 * 服务标示   不入库
	 */
	private String serviceLogo;

	/**
	* @return the serviceId
	*/
	public String getServiceId() {
		return serviceId;
	}

	/**
	* @param code
	*            the serviceId to set
	*/
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId == null ? null : serviceId.trim();
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
	* @return the priceId
	*/
	public String getPriceId() {
		return priceId;
	}

	/**
	* @param code
	*            the priceId to set
	*/
	public void setPriceId(String priceId) {
		this.priceId = priceId == null ? null : priceId.trim();
	}

	/**
	* @return the stMon
	*/
	public String getStMon() {
		return stMon;
	}

	/**
	* @param code
	*            the stMon to set
	*/
	public void setStMon(String stMon) {
		this.stMon = stMon == null ? null : stMon.trim();
	}

	/**
	* @return the stTue
	*/
	public String getStTue() {
		return stTue;
	}

	/**
	* @param code
	*            the stTue to set
	*/
	public void setStTue(String stTue) {
		this.stTue = stTue == null ? null : stTue.trim();
	}

	/**
	* @return the stWed
	*/
	public String getStWed() {
		return stWed;
	}

	/**
	* @param code
	*            the stWed to set
	*/
	public void setStWed(String stWed) {
		this.stWed = stWed == null ? null : stWed.trim();
	}

	/**
	* @return the stThu
	*/
	public String getStThu() {
		return stThu;
	}

	/**
	* @param code
	*            the stThu to set
	*/
	public void setStThu(String stThu) {
		this.stThu = stThu == null ? null : stThu.trim();
	}

	/**
	* @return the stFri
	*/
	public String getStFri() {
		return stFri;
	}

	/**
	* @param code
	*            the stFri to set
	*/
	public void setStFri(String stFri) {
		this.stFri = stFri == null ? null : stFri.trim();
	}

	/**
	* @return the stSat
	*/
	public String getStSat() {
		return stSat;
	}

	/**
	* @param code
	*            the stSat to set
	*/
	public void setStSat(String stSat) {
		this.stSat = stSat == null ? null : stSat.trim();
	}

	/**
	* @return the stSun
	*/
	public String getStSun() {
		return stSun;
	}

	/**
	* @param code
	*            the stSun to set
	*/
	public void setStSun(String stSun) {
		this.stSun = stSun == null ? null : stSun.trim();
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
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the leastBuyTime
	 */
	public int getLeastBuyTime() {
		return leastBuyTime;
	}

	/**
	 * @param leastBuyTime the leastBuyTime to set
	 */
	public void setLeastBuyTime(int leastBuyTime) {
		this.leastBuyTime = leastBuyTime;
	}
	
	/**
	 * 
	 * @param config
	 * @param appointmentDate yyyy-MM-dd
	 * @return
	 */
	public static String getTimeList(DoctorServiceInfo config, String appointmentDate) {
		Date date = DateUtils.StringToDateYMD(appointmentDate);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		
		String ret = null;
		
		switch(c.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.SUNDAY:
				ret = config.getStSun();
				break;
			case Calendar.MONDAY:
				ret = config.getStMon();
				break;
			case Calendar.TUESDAY:
				ret = config.getStTue();
				break;
			case Calendar.WEDNESDAY:
				ret = config.getStWed();
				break;
			case Calendar.THURSDAY:
				ret = config.getStThu();
				break;
			case Calendar.FRIDAY:
				ret = config.getStFri();
				break;
			case Calendar.SATURDAY:
				ret = config.getStSat();
				break;
			default:
				throw new IllegalArgumentException("未知日期");
		}
		return ret;
	}

}