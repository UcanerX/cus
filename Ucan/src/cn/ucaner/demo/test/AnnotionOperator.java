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
package cn.ucaner.demo.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.ucaner.demo.anno.SayHiAnnotation;
import cn.ucaner.demo.annodemo.SayHiEmlement;

public class AnnotionOperator {
    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
        SayHiEmlement element = new SayHiEmlement(); // 初始化一个实例，用于方法调用
        Method[] methods = SayHiEmlement.class.getDeclaredMethods(); // 获得所有方法
        for (Method method : methods) {
            SayHiAnnotation annotationTmp = null;
            if((annotationTmp = method.getAnnotation(SayHiAnnotation.class))!=null){ // 检测是否使用了我们的注解
            	System.out.println("If:"+method.getName());
                method.invoke(element,annotationTmp.paramValue(),"22"); // 如果使用了我们的注解，我们就把注解里的"paramValue"参数值作为方法参数来调用方法
            }else{
            	System.out.println("Else:"+method.getName());
                method.invoke(element, "Rose",""); // 如果没有使用我们的注解，我们就需要使用普通的方式来调用方法了
            }
        }
    }
}