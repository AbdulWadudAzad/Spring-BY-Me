package com.coderbd.springbasic.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        Employee employee = context.getBean(Employee.class);
        employee.showDetails();

    }
}
