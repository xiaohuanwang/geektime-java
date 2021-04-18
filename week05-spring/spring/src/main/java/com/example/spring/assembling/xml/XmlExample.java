package com.example.spring.assembling.xml;

import org.springframework.stereotype.Component;

/**
 * @program: xml方式，Bean装配
 * @description:
 * @author: 王小欢
 * @create: 2021-04-18 17:40
 **/
@Component
public class XmlExample {
    public XmlExample() {
        System.out.println("Construct Example");
    }

    public void info() {
        System.out.println("Auto wiring example");
    }
}
