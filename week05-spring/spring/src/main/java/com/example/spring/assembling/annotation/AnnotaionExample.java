package com.example.spring.assembling.annotation;

import org.springframework.stereotype.Component;

/**
 * @program: 自动注解方式，Bean装配
 * @description:
 * @author: 王小欢
 * @create: 2021-04-18 17:36
 **/
@Component
public class AnnotaionExample {

    public AnnotaionExample() {
        System.out.println("Construct Example");
    }

    public void info() {
        System.out.println("Auto wiring example");
    }
}
