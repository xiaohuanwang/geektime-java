package com.example.spring;

import com.example.spring.assembling.annotation.AnnotaionExample;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @program: spring
 * @description:
 * @author: 王小欢
 * @create: 2021-04-18 17:38
 **/
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AnnotaionExample.class)
public class AnnotaionTest {
    @Autowired
    private AnnotaionExample annotaionExample;

    @Test
    public void test() {
        annotaionExample.info();
    }
}
