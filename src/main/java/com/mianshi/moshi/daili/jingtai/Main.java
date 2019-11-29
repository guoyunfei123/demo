package com.mianshi.moshi.daili.jingtai;

public class Main {

    public static void main(String[] args) {
        ProxyService proxyService = new ProxyService(new RealService());
        proxyService.doSomeThing();
    }
}
