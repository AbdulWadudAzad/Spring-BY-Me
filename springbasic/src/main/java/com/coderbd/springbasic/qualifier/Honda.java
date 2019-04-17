package com.coderbd.springbasic.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "hondaBean")
public class Honda implements Vehicle {
    @Override
    public void start() {
        System.out.println("Honda started");
    }

    @Override
    public void stop() {
        System.out.println("Honda stopped");
    }
}
