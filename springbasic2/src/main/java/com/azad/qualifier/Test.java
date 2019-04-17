package com.azad.qualifier;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.scan("com.azad.qualifier");
        context.refresh();
        VehicleService vehicle = context.getBean(VehicleService.class);
        vehicle.service();

        context.close();
    }

}
