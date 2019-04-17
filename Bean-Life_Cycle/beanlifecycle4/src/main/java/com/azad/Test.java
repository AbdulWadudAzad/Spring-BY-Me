package com.azad;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beanCongig.xml");

        ctx.getBean("myArrayService", MyAwareService.class);

        ctx.close();
    }
}
