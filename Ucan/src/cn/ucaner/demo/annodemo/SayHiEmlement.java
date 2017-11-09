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
package cn.ucaner.demo.annodemo;

import cn.ucaner.demo.anno.SayHiAnnotation;

/**
 * 要使用SayHiAnnotation的元素所在类
 * 由于我们定义了只有方法才能使用我们的注解，我们就使用多个方法来进行测试
 * @author Jason
 *
 */
public class SayHiEmlement {

    // 普通的方法
    public void SayHiDefault(String name,String age){
        System.out.println("Hi, " + name+age);
    }
    
    // 使用注解并传入参数的方法
    @SayHiAnnotation(paramValue="Jack")
    public void SayHiAnnotation(String name,String age){
        System.out.println("Hi, " + name+age);
    }
    
    // 使用注解并使用默认参数的方法
    @SayHiAnnotation
    public void SayHiAnnotationDefault(String name,String age){
        System.out.println("Hi, " + name + age);
    }
}