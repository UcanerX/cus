package com.sunshine.common.thread;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import org.slf4j.LoggerFactory;

import com.sunshine.framework.common.spring.ext.SpringContextHolder;

/**
 * @Package com.sunshine.common.thread
 * @ClassName QueryHashTableCallable
 * @Statement <p>子线程查询/更新hash子表</p>
 * @JDK version used: 1.7
 * @Author: 无名子
 * @Create Date: 2016-4-28
 * @modify-Author: 
 * @modify-Date:   
 * @modify-Why/What: 
 * @Version 1.0
 */
public class SubHashTableCallable implements Callable<Object> {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(SubHashTableCallable.class);

	/**
	 * 执行类的执行接口或者实现类
	 */
	private Class<?> springBeanClazz;

	/**
	 * 执行类的执行方法名
	 */
	private String methodName;

	/**
	 * 对应方法的参数
	 */
	private Object[] parameters;

	/**
	 * 对应方法参数的类型
	 */
	private Class<?>[] parameterTypes;

	public SubHashTableCallable(Class<?> springBeanClazz, String methodName, Object[] parameters, Class<?>[] parameterTypes) {
		super();
		this.springBeanClazz = springBeanClazz;
		this.methodName = methodName;
		this.parameters = parameters;
		this.parameterTypes = parameterTypes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		Object springBean = SpringContextHolder.getBean(springBeanClazz);
		Method method = springBeanClazz.getMethod(methodName, parameterTypes);
		return method.invoke(springBean, parameters);
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public Class<?> getSpringBeanClazz() {
		return springBeanClazz;
	}

	public void setSpringBeanClazz(Class<?> springBeanClazz) {
		this.springBeanClazz = springBeanClazz;
	}
}
