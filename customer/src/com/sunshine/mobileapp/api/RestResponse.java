/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年8月19日</p>
 *  <p> Created by Administrator</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

/**
 * @Project YiChenTong_Server
 * @Package com.sunshine.mobileapp.api
 * @ClassName Response.java
 * @Description 响应
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
public class RestResponse implements Serializable {

	private static final long serialVersionUID = 6757958172396531662L;
	/**
	 * 响应结果编码
	 */
	private RestStatusEnum status;

	/**
	 * 响应状态码
	 */
	private Integer code;
	/**
	 * 响应结果描述
	 */
	private String msg;
	/**
	 * 结果集
	 */
	private Object data;

	private String signCode;

	/**
	 * 
	 */
	public RestResponse() {
		super();
	}

	public RestResponse(RestStatusEnum status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	public RestStatusEnum getStatus() {
		return status;
	}

	public void setStatus(RestStatusEnum status) {
		this.status = status;
	}

	public String getMsg() {
		if (StringUtils.isBlank(msg)) {
			if (status != null && status == RestStatusEnum.OK) {
				msg = "成功";
			}
		}
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getSignCode() {
		return signCode;
	}

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	public Integer getCode() {
		if (status != null) {
			code = status.getValue();
		}
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
