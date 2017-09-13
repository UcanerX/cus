/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年9月8日</p>
 *  <p> Created by 钟乳石</p>
 *  </body>
 * </html>
 */
package com.sunshine.mobileapp.api.common;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

/**
 * @Project: easy_health_client2 
 * @Package: com.sunshine.mobileapp.api.common
 * @ClassName: ResponseFilter
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 钟乳石
 * @Create Date: 2017年9月8日
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
public class ResponseFilter implements ContainerResponseFilter{


	@Override
	public void filter(ContainerRequestContext arg0, ContainerResponseContext containerResponseContext) throws IOException {
		// TODO Auto-generated method stub
		 containerResponseContext.getHeaders().add("Access-Control-Allow-Origin", "*");  
         containerResponseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type,x-requested-with,Authorization,Access-Control-Allow-Origin");  
         containerResponseContext.getHeaders().add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");  
	}

}
