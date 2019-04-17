package com.azad;

public class Student {
    private int id;
    private String name;
    private Address address;
    private Department department;

    public Student(int id, String name, Address address, Department department) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", department=" + department +
                '}';
    }
}
