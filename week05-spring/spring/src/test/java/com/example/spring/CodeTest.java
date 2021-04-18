package com.example.spring;

import com.example.spring.assembling.code.CodeConfig;
import com.example.spring.assembling.code.CodeExample;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: spring
 * @description:
 * @author: 王小欢
 * @create: 2021-04-18 17:54
 **/
public class CodeTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(CodeConfig.class);
        CodeExample example = (CodeExample) configApplicationContext.getBean("codeExample");
        example.info();
    }
}
