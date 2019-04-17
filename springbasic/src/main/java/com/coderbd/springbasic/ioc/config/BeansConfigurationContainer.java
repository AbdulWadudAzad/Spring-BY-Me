package com.coderbd.springbasic.ioc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfigurationContainer {

    @Bean
    public A myA() {
        return new A();
    }
}
