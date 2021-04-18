package com.example.spring.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description:
 * @author wangxiaohuan
 * @Date 5:22 下午 2021/4/18
   No such property: code for class: Script1
 * @return 
 */
public class ProxyHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(object, args);
        after();
        return null;
    }

    private Object object;

    public ProxyHandler(Object object) {
        this.object = object;
    }

    private void before() {
        System.out.println("proxy before method");
    }

    private void after() {
        System.out.println("proxy after method");
    }
}
