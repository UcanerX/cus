package cn.ucaner.study.jvm.classloading;

/**
 * Created by brian on 17/3/19.
 */
public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}
