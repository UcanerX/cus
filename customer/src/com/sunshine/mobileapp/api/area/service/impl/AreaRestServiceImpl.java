/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年9月7日</p>
 *  <p> Created by 钟乳石</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.area.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.sunshine.cache.component.AreaCache;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.mobileapp.api.RestResponse;
import com.sunshine.mobileapp.api.RestStatusEnum;
import com.sunshine.mobileapp.api.area.service.AreaRestService;

/**
 * @Project: easy_health_client2 
 * @Package: com.sunshine.mobileapp.api.area.service.impl
 * @ClassName: AreaRestServiceImpl
 * @Description: <p>地区rest接口</p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年9月7日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Path("v1/area")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class AreaRestServiceImpl implements AreaRestService{
	
	private  AreaCache areaCache = SpringContextHolder.getBean(AreaCache.class);
	
	@GET
	@Path("/getProvinces")
	public RestResponse getProvinces(){
		RestResponse response = new RestResponse();
		response.setStatus(RestStatusEnum.OK);
		response.setData(areaCache.getProvinceNames());
		return response;
		
	}
	
	@GET
	@Path( "getCitys/{provinceCode:.*}")
	public RestResponse getCityItems(@PathParam("provinceCode") String provinceCode){
		RestResponse response = new RestResponse();
		response.setStatus(RestStatusEnum.OK);
		response.setData(areaCache.getCityNames(provinceCode));
		return response;
	}
	
	@GET
	@Path("getAreas/{cityCode:.*}")
	public RestResponse getAreaItems(@PathParam("cityCode") String cityCode){
		RestResponse response = new RestResponse();
		response.setStatus(RestStatusEnum.OK);
		response.setData(areaCache.getAreaNames(cityCode));
		return response;
	}
}
