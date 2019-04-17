package com.coderbd.springbasic.qualifier;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();

        // Scan beans
        context.scan("com.coderbd.springbasic.qualifier");
        context.refresh();
        VehicleService vehicle = context.getBean(VehicleService.class);
        vehicle.service();

        context.close();
    }
}
/*
The @Qualifier annotation is used to resolve the autowiring conflict, when there are multiple beans of same type.

The @Qualifier annotation can be used on any class annotated with @Component or on method annotated with @Bean. This annotation can also be applied on constructor arguments or method parameters.

In this post, we will show you how the @Qualifier annotation is used in the Spring application.

Consider the following Vehicle interface.
 */