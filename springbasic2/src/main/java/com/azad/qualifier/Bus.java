package com.azad.qualifier;

import org.springframework.stereotype.Component;

@Component(value = "busBean")
public class Bus implements Vehicle {

    @Override
    public void start() {
        System.out.println("Strated Bus");
    }

    @Override
    public void stop() {
        System.out.println("Stoped Bus");
    }
}
