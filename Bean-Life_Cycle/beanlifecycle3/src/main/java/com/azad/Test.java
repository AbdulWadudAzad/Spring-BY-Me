package com.azad;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beanConfig.xml");
        Employee e = (Employee) context.getBean("emp");
        System.out.println(e);
        context.close();
    }

}
