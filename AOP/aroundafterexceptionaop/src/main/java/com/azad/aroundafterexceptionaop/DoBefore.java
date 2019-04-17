package com.azad.aroundafterexceptionaop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class DoBefore implements MethodBeforeAdvice {
public void before(Method method,Object[] arg,Object target)throws Throwable{
    System.out.println("DoBeforeMethod...........................!");
}
}
