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
package com.sunshine.mobileapp.api.register.service;

import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.dto.RegParamsVo;

/**
 * @Project YiChenTong_Server
 * @Package com.sunshine.mobileapp.api.register.service
 * @ClassName RegisterRestService.java
 * @Description
 * @JDK version used 1.8
 * @Author 于策/yu.ce@foxmail.com
 * @Create Date 2017年8月31日
 * @modify By
 * @modify Date
 * @Why&What is modify
 * @Version 1.0
 */
public interface RegisterRestService {
	/**
	 * @Description 获取可挂号的医院列表
	 * @param vo
	 * @return
	 * @date 2017年8月31日
	 */
	RestResponse getHospitals(RegParamsVo vo);

	RestResponse getUser(String account, String id);

	/**
	 * @Description 获取曾挂号医院列表
	 * @param vo
	 * @return
	 * @date 2017年8月31日
	 * 
	 *       RestResponse getHadRegHospitalByUser(RegParamsVo vo);
	 */
}
