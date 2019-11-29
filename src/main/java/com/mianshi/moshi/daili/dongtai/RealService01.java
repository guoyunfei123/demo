package com.mianshi.moshi.daili.dongtai;

/**
 * 目标类01
 */
public class RealService01 implements Service{


    @Override
    public String doSomeThing() {
        System.out.println("RealService01  执行了");
        return "ssss";
    }
}
