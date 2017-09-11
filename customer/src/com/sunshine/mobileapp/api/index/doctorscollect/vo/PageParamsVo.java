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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
* @Package：com.sunshine.mobileapp.api.index.doctorscollect.vo   
* @ClassName：PageParamsVo   
* @Description：   <p> page分页传输</p>
* @Author： - DaoDou 
* @CreatTime：2017年9月7日 下午4:27:28   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PageParamsVo  implements Serializable {

	private static final long serialVersionUID = -2109496013797992357L;

	/**
	 * 起始页码
	 */
	private String pageNum;
	
	/**
	 * 页大小
	 */
	private String pageSize;

	
	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public PageParamsVo() {
		super();
	}
}
