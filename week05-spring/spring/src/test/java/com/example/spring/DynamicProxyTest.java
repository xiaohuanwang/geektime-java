package com.example.spring;


import com.example.spring.dynamicproxy.ExampleService;
import com.example.spring.dynamicproxy.ExampleServiceImpl;
import com.example.spring.dynamicproxy.ProxyHandler;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {

    @Test
    public void test() {
        ExampleServiceImpl exampleService = new ExampleServiceImpl();
        ClassLoader classLoader = exampleService.getClass().getClassLoader();
        Class[] interfaces = exampleService.getClass().getInterfaces();
        InvocationHandler handler = new ProxyHandler(exampleService);

        ExampleService proxy = (ExampleService) Proxy.newProxyInstance(classLoader, interfaces, handler);
        proxy.info();
    }
}
