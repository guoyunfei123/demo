package com.mianshi.moshi.daili.dongtai;

import javax.xml.bind.SchemaOutputResolver;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 此类是动态代理的一部分，还不是真正的代理类，该类是协助真正的代理类去工作
 */
public class ProxyService implements InvocationHandler {

    private Object target;

    public ProxyService (Object target) {
        this.target = target;
    }

    /**
     * 该方法在目标类的方法被执行的时候，会被调用
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("对目标方法执行前的增强");
        // 此代码是真正调用目标方法
        Object object = method.invoke(target,args);
        System.out.println("对目标方法执行后的增强");
        return object;
    }
}
