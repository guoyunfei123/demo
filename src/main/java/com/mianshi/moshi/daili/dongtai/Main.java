package com.mianshi.moshi.daili.dongtai;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {

        // 该classLoader 必须是和加载该service接口的classLoader是同一个classLoader
        Service proxyService = (Service)Proxy.newProxyInstance(Service.class.getClassLoader(),new Class[]{Service.class},new ProxyService(new RealService01()));

        Service proxyService1 = (Service)Proxy.newProxyInstance(Service.class.getClassLoader(),new Class[]{Service.class},new ProxyService(new RealService02()));

        System.out.println(proxyService.doSomeThing());
        System.out.println(proxyService1.doSomeThing());
    }

}
