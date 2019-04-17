package com.azad.aroundafterexceptionaop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class DoAfterReterning implements AfterReturningAdvice {
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("DoAfterReterning.....................!");
    }
}