package com.azad.autowire;

public class Address {
    private String housename;
    private String country;

    public Address() {
        System.out.println(" Object is created");
    }

    public String getHousename() {
        return housename;
    }

    public void setHousename(String housename) {
        this.housename = housename;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void show() {
        System.out.println("Show...........");
    }
}
