/**
 * <html>
 * <body>
 *  <P>  Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p>  All rights reserved.</p>
 *  <p> Created on 2016-4-6</p>
 *  <p> Created by 无名子</p>
 *  </body>
 * </html>
 */
package com.sunshine.common.entity.msgpush;

import com.sunshine.common.vo.MessagePushParamsVo;
import com.sunshine.framework.mvc.mysql.entity.BaseSQLEntity;

/**
 * @Package com.sunshine.common.entity.mobileapp.base
 * @ClassName MsgPushEntity
 * @Statement <p>消息推送类基类</p>
 * @JDK version used: 1.7
 * @Author: 无名子
 * @Create Date: 2016-4-19
 * @modify-Author: 
 * @modify-Date:   
 * @modify-Why/What: 
 * @Version 1.0
 */
public abstract class MsgPushEntity extends BaseSQLEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2396831268481366046L;

	public abstract MessagePushParamsVo convertMessagePushParams();

}
