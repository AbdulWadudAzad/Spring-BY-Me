package com.coderbd.springbasic.ioc.annotation;

import org.springframework.stereotype.Component;

@Component
public class A {
    public A() {
        System.out.println("Object of A is created");
    }
}
