package com.azad;

public class SimpleBean {
    private final String DEFAULT_NAME = "Azad";
    private String name = null;
    private int age = Integer.MIN_VALUE;

    public SimpleBean() {
    }

    public SimpleBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getDEFAULT_NAME() {
        return DEFAULT_NAME;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void init() {
        System.out.println("Initializing bean");
        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }
        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("You must set the age property of an" + SimpleBean.class);
        }
    }
}
