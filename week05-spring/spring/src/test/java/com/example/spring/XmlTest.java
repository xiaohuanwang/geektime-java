package com.example.spring;

import com.example.spring.assembling.xml.XmlExample;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: spring
 * @description:
 * @author: 王小欢
 * @create: 2021-04-18 17:41
 **/
public class XmlTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("BeanConfig.xml");
        XmlExample example = (XmlExample) context.getBean("XmlExample");
        example.info();
    }
}
