package com.azad;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Employee {
    private int id;
    private String position;
    private Address address;

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", address=" + address +
                '}';
    }

    @PostConstruct
    public void init() {
        System.out.println("I'm initialized");
    }

    @PreDestroy
    public void cleanUp() {
        System.out.println("I'm closed");
    }
}
