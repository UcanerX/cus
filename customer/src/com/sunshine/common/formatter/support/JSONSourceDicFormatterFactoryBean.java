/**
 * <html>
 * <body>
 *  <P> Copyright 2014 广东天泽阳光康众医疗投资管理有限公司. 粤ICP备09007530号-15</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017-5-23</p>
 *  <p> Created by sun001</p>
 *  </body>
 * </html>
 */
package com.sunshine.common.formatter.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.sunshine.common.formatter.FieldFormatter;

/**
 * @Project: easyHealth 
 * @Package: com.sunshine.common.formatter.support
 * @ClassName: JSONSourceDicFormatterFactoryBean
 * @Description: <p></p>
 * @JDK version used: 
 * @Author: 甘松
 * @Create Date: 2017-5-23
 * @modify By:
 * @modify Date:
 * @Why&What is modify:
 * @Version: 1.0
 */
@Component
public class JSONSourceDicFormatterFactoryBean implements FactoryBean<FieldFormatter>, ApplicationContextAware, InitializingBean {
	private String jsonSource = "classpath:dictionary.json";
	private ApplicationContext ctx;
	private JSONSourceDictionaryFieldFormatter f;
	
	@Override
	public FieldFormatter getObject() throws Exception {
		return f;
	}

	@Override
	public Class<?> getObjectType() {
		return JSONSourceDictionaryFieldFormatter.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public String getJsonSource() {
		return jsonSource;
	}

	public void setJsonSource(String jsonSource) {
		this.jsonSource = jsonSource;
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.ctx = context;
		
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		f = new JSONSourceDictionaryFieldFormatter();
		f.load(ctx.getResource(jsonSource).getInputStream());
		FieldFormatterManager.getInstance().registerFormatter(JSONSourceDictionaryFieldFormatter.class.getName(), f);
	}
}
