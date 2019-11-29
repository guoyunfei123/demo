package com.mianshi.moshi.singleton;

/**
 * 懒汉式使用同步锁锁住了整个方法，效率较低
 */
public class EHanShi01 {

    private static EHanShi01 eHanShi01 = null;

    private EHanShi01(){}

    /**  提供公共的获取方法,因为不是在类加载时就创建对象，因此存在线程安全问题，使用synchronized关键字保证线程安全，效率降低*/
    public static synchronized EHanShi01 getInstance(){
        if(eHanShi01 == null){
            eHanShi01 = new EHanShi01();
        }
        return eHanShi01;
    }
}
