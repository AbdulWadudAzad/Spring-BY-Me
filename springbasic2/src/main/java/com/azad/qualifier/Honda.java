package com.azad.qualifier;

import org.springframework.stereotype.Component;

@Component(value = "hondaBean")
public class Honda implements Vehicle {

    @Override
    public void start() {
        System.out.println("Strated Honda");

    }

    @Override
    public void stop() {
        System.out.println("Stoped Honda");

    }
}
