/**
 * <html>
 * <body>
 *  <P>  Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p>  All rights reserved.</p>
 *  <p> Created on 2015-6-20</p>
 *  <p> Created by Yuce</p>
 *  </body>
 * </html>
 */
package cn.ucaner.common.vo;

import java.io.Serializable;

/**
 * @Package: com.sunshine.mobileapp.common.vo
 * @ClassName: CommonParamsVo
 * @Statement:
 * 			<p>
 *             页面与后台的传值vo
 *             </p>
 * @JDK version used: 1.6
 * @Author:
 * @Create Date: 2016-10-20
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class HttpParamsVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5103525083759679435L;

	/**
	 * 登录用户的userId
	 */
	protected String userId;

	/**
	 * 登录用户的账号
	 */
	protected String account;

	/**
	 * 下一步的url跳转地址
	 */
	protected String nextUrl;

	/**
	 * 上一步的请求地址
	 */
	protected String backUrl;

	/**
	 * 当前页面的请求地址
	 */
	protected String curUrl;

	/**
	 * 是否是后退操作
	 */
	protected Boolean isBack;

	/**
	 * 是否是前进操作
	 */
	protected Boolean isHead;
	
	public HttpParamsVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取userId 当医院微信和医院支付宝时使用openId作为userId
	 * 
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getCurUrl() {
		return curUrl;
	}

	public void setCurUrl(String curUrl) {
		this.curUrl = curUrl;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Boolean getIsBack() {
		return isBack;
	}

	public void setIsBack(Boolean isBack) {
		this.isBack = isBack;
	}

	public Boolean getIsHead() {
		return isHead;
	}

	public void setIsHead(Boolean isHead) {
		this.isHead = isHead;
	}

	

}
