package com.mianshi.moshi.daili.custom;

public class Main {

    public static void main(String[] args) {

        // 该classLoader 必须是和加载该service接口的classLoader是同一个classLoader
        Service proxyService = (Service)MyProxy.newProxyInstance(new MyClassLoader(),new Class[]{Service.class},new ProxyService(new RealService01()));
        System.out.println(proxyService.doSomeThing());
    }

}
