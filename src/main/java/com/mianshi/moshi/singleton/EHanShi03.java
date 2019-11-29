package com.mianshi.moshi.singleton;

/**
 * 静态内部类式（使用静态内部类解决了线程安全问题，并实现了延时加载）
 * 由于JVM底层内部模型的原因，偶尔会出现问题，因此不建议使用
 */
public class EHanShi03 {

    private EHanShi03(){}

    private static class EHanShi03_Inner{

        private static final EHanShi03 eHanShi03 = new EHanShi03();
    }

    /**
     * //不会在外部类初始化时就直接加载，只有当调用了getInstance方法时才会静态加载，线程安全，final保证了在内存中只有一份
     * @return
     */
    public static EHanShi03 getInstance(){
        return EHanShi03_Inner.eHanShi03;
    }
}
