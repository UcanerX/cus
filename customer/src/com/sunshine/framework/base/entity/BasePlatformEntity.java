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
package com.sunshine.framework.base.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.sunshine.common.entity.msgpush.MsgPushEntity;
import com.sunshine.common.vo.MessagePushParamsVo;

/**
 * @Project: easy_health 
 * @Package: com.sunshine.framework.mvc.entity
 * @ClassName: BasePlatformEntity
 * @Description: <p>后台系统entity基类</p>
 * @JDK version used: 
 * @Author: 申姜
 * @Create Date: 2016年9月13日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class BasePlatformEntity  extends MsgPushEntity{
	private static final long serialVersionUID = 303955536205314666L;
	/**
	 * 创建人id
	 */
	protected String cp;
	/**
	 * 创建人名称
	 */
	protected String cpName;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date ct;
	/**
	 * 修改人id
	 */
	protected String ep;

	/**
	 * 修改人名称
	 */
	protected String epName;
	/**
	 * 修改时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date et;

	/**
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}

	/**
	 * @param cp
	 *            the cp to set
	 */
	public void setCp(String cp) {
		this.cp = cp == null ? null : cp.trim();
	}

	/**
	 * @return the ct
	 */
	public Date getCt() {
		return ct;
	}

	/**
	 * @param ct
	 *            the ct to set
	 */
	public void setCt(Date ct) {
		this.ct = ct;
	}

	/**
	 * @return the ep
	 */
	public String getEp() {
		return ep;
	}

	/**
	 * @param ep
	 *            the ep to set
	 */
	public void setEp(String ep) {
		this.ep = ep == null ? null : ep.trim();
	}

	/**
	 * @return the et
	 */
	public Date getEt() {
		return et;
	}

	/**
	 * @param et
	 *            the et to set
	 */
	public void setEt(Date et) {
		this.et = et;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName == null ? null : cpName.trim();
	}

	public String getEpName() {
		return epName;
	}

	public void setEpName(String epName) {
		this.epName = epName == null ? null : epName.trim();
	}

	@Override
	public MessagePushParamsVo convertMessagePushParams() {
		return null;
	}

}
