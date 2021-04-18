package com.example.spring.assembling.code;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author wangxiaohuan
 * @Date 5:54 下午 2021/4/18
   No such property: code for class: Script1
 * @return
 */
@Configuration
public class CodeConfig {

    @Bean
    public CodeExample codeExample() {
        return new CodeExample();
    }
}
