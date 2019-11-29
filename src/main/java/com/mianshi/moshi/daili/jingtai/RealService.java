package com.mianshi.moshi.daili.jingtai;

/**
 * 定义了代理对象所代表的目标对象。
 */
public class RealService implements Service{


    @Override
    public void doSomeThing() {
        System.out.println("目标对象执行了");
    }
}
