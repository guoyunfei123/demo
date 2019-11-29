package com.mianshi.moshi.singleton;

/**
 * @see https://www.cnblogs.com/chiclee/p/9097772.html
 * 严格意义上来说以上四种方式实现的单例模式都不是线程安全的，因为反射机制的存在，
 * 反射可以破坏私有属性，并且通过反射创建对象.
 * 举个例子，通过反射破坏上面的静态内部类方式实现的单例模式
 */
public enum  EnumSingleton {

    INSTANCE;

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        EnumSingleton singleton = EnumSingleton.getInstance();
        System.out.println(singleton);
    }
}
