/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2016年4月2日</p>
 *  <p> Created by 申姜</p>
 *  </body>
 * </html>
 */
package com.sunshine.framework.base.entity;

import java.io.Serializable;

/**
 * entity基类
 * @Package: com.sunshine.framework.mvc.entity
 * @ClassName: BaseEntity
 * @Statement: <p>
 *             </p>
 * @JDK version used:
 * @Author: 申姜
 * @Create Date: 2016-4-2
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 2368417877456900821L;
	/**
	 * 主键ID
	 */
	protected String id;

	public BaseEntity() {
		super();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
}
