package com.example.spring.assembling.code;

import org.springframework.stereotype.Component;

/**
 * @description:Java代码方式，Bean装配
 * @author wangxiaohuan
 * @Date 5:54 下午 2021/4/18
   No such property: code for class: Script1
 * @return
 */
@Component
public class CodeExample {

    public CodeExample() {
        System.out.println("Construct Example");
    }

    public void info() {
        System.out.println("Auto wiring example");
    }
}
