package com.mianshi.moshi.singleton;

/**
 * DCL懒汉式(双重检测锁模式)
 * 由于JVM底层内部模型的原因，偶尔会出现问题，因此不建议使用
 */
public class EHanShi02 {

    /**
     *  //只提供一个实例，并不创建对象
     *     //使用避免指令重排带来的线程安全问题
     *     //volatile：对于同一个变量，在一个线程中值发生了改变，则在另一个线程中立即生效，可以大幅度避免下面的问题，不排除极端情况
      */
    private static volatile EHanShi02 eHanShi02;

    private EHanShi02(){}

    /**
     *提供公共的获取方法,因为不是在类加载时就创建对象(因为类加载的不会有线程安全问题的)，因此存在线程安全问题，使用同步代码块提高效率
     *     //现在不需要对整个方法进行同步，缩小了锁的范围，只有第一次会进入创建对象的方法，提高了效率
     *     //当第一个线程执行到创建对象的方法时，但还未出方法返回，此时第二个线程进入，发现instance不为空，但第一个线程此时还未出去，可能发送意想不到的安全问题
     * @return
     */
    public static EHanShi02 getInstance(){
        if(eHanShi02 == null){
            synchronized (EHanShi02.class){
                if(eHanShi02 == null){
                    eHanShi02 = new EHanShi02();
                }
            }
        }
        return eHanShi02;
    }
}
