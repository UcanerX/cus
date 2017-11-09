/**
 * <html>
 * <body>
 *  <P> Copyright 1994 JsonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 19941115</p>
 *  <p> Created by Jason</p>
 *  </body>
 * </html>
 */
package cn.ucaner.tools.aop.aspects;

import java.lang.reflect.Method;

import cn.ucaner.tools.aop.Aspect;

/**
* @Package：cn.ucaner.tools.aop.aspects   
* @ClassName：SimpleAspect   
* @Description：   <p> 简单切面类，不做任何操作<br>
* 				可以继承此类实现自己需要的方法即可</p>
* @Author： - DaoDou 
* @CreatTime：2017-11-3 上午9:39:05   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
public class SimpleAspect extends Aspect{
	
	public SimpleAspect(Object target) {
		super(target);
	}

	@Override
	public boolean before(Object target, Method method, Object[] args) {
		//继承此类后实现此方法
		return true;
	}

	@Override
	public boolean after(Object target, Method method, Object[] args) {
		//继承此类后实现此方法
		return true;
	}

	@Override
	public boolean afterException(Object target, Method method, Object[] args, Throwable e) {
		//继承此类后实现此方法
		return true;
	}

}
