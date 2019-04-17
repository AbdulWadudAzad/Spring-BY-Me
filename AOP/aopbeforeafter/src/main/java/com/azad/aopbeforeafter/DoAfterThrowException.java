package com.azad.aopbeforeafter;

import org.springframework.aop.ThrowsAdvice;

public class DoAfterThrowException implements ThrowsAdvice {
    public void afterThrowing(IllegalAccessException e)throws Throwable{
        System.out.println("DoAfterThrowException.................!");
    }
}
