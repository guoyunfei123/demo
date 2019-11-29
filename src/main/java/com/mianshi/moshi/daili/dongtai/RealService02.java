package com.mianshi.moshi.daili.dongtai;

/**
 * 目标类02
 */
public class RealService02 implements Service{


    @Override
    public String doSomeThing() {
        System.out.println("RealService01  执行了");
        return "rrrr";
    }
}
